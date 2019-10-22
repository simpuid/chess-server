package com.chess.client;

import com.chess.chessboard.ChessBoard;
import com.chess.chessboard.pieces.Color;
import com.chess.chessboard.pieces.Piece;
import com.chess.common.Position;
import com.chess.common.moves.Move;
import com.chess.common.moves.MoveNormal;
import com.chess.common.results.*;

import javax.swing.*;
import java.awt.*;

class ClientBoard extends JFrame {

    private int lastclickButton;
    private boolean isSecondClick;
    private Color color;
    private int gameID;
    private ChessBoard chessBoard;
    private Client client;

    ClientBoard(com.chess.chessboard.pieces.Color color, int gameID, Client client) {
        initComponents();
        this.color = color;
        this.gameID = gameID;
        chessBoard = new ChessBoard();
        this.client = client;
        gameIDLabel.setText("GameID : " + gameID);
        updateBoard();
        disableBoard();
    }

    private void initComponents() {
        JLabel nameLabel = new JLabel();
        notifyLabel = new JLabel();
        gameIDLabel = new JLabel();
        JPanel board = new JPanel();
        toggleButton = new JToggleButton[64];
        for (int i = 0; i < 64; i++) {
            toggleButton[i] = new JToggleButton();
        }
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(905, 950));
        setMinimumSize(new Dimension(905, 950));
        setMaximumSize(new Dimension(905, 950));
        getContentPane().setLayout(null);
        setResizable(false);
        setTitle("Chess Game");
        setIconImage(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("mainicon.jpeg"))).getImage());

        nameLabel.setFont(new Font("Ubuntu", Font.BOLD, 24));
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        nameLabel.setText("Online Chess Game");
        getContentPane().add(nameLabel);
        nameLabel.setBounds(5, 5, 895, 35);

        notifyLabel.setFont(new Font("Ubuntu", Font.BOLD, 18));
        notifyLabel.setHorizontalAlignment(SwingConstants.CENTER);
        notifyLabel.setText("Welcome");
        notifyLabel.setForeground(new java.awt.Color(0, 0, 0));
        getContentPane().add(notifyLabel);
        notifyLabel.setBounds(5, 45, 445, 30);

        gameIDLabel.setFont(new Font("Ubuntu", Font.BOLD, 18));
        gameIDLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gameIDLabel.setText("GameID : ");
        getContentPane().add(gameIDLabel);
        gameIDLabel.setBounds(455, 45, 445, 30);

        board.setFont(new Font("Ubuntu", Font.BOLD, 18));
        board.setMaximumSize(new Dimension(800, 800));
        board.setMinimumSize(new Dimension(800, 800));
        board.setPreferredSize(new Dimension(800, 800));
        getContentPane().add(board);
        board.setBounds(55, 100, 800, 800);
        board.setLayout(new GridLayout(8, 8, 0, 0));
        for (int i = 7; i >= 0; i--) {
            for (int j = 0; j < 8; j++) {
                int value = (8 * i) + j;
                toggleButton[value].setFont(new Font("Ubuntu", Font.BOLD, 18));
                toggleButton[value].setHorizontalAlignment(SwingConstants.CENTER);
                toggleButton[value].addActionListener(actionEvent -> buttonClicked(value));
                if (i % 2 == 0) {
                    if (value % 2 == 0)
                        toggleButton[value].setBackground(new java.awt.Color(255, 255, 255));
                    else
                        toggleButton[value].setBackground(new java.awt.Color(0, 0, 0));
                } else {
                    if (value % 2 != 0)
                        toggleButton[value].setBackground(new java.awt.Color(255, 255, 255));
                    else
                        toggleButton[value].setBackground(new java.awt.Color(0, 0, 0));
                }
                board.add(toggleButton[value]);
            }
        }
        pack();
    }

    private void buttonClicked(int boxID) {
        if (!isSecondClick) {
            Position p = new Position(boxID);
            if ((chessBoard.boxArray[p.x][p.y].piece == null) || (chessBoard.boxArray[p.x][p.y].piece.color != color)) {
                disableBoard();
                enableBoard();
                return;
            }
            isSecondClick = true;
            lastclickButton = boxID;
        } else {
            Move move = generateMove(lastclickButton, boxID);
            client.sendMove(move);
            isSecondClick = false;
            disableBoard();
        }
    }

    private void disableBoard() {
        for (int i = 0; i < 64; i++) {
            toggleButton[i].setEnabled(false);
            toggleButton[i].setSelected(false);
        }
    }

    private void enableBoard() {
        for (int i = 0; i < 64; i++) {
            toggleButton[i].setEnabled(true);
        }
    }

    private void updateBoard() {
        for (int i = 0; i < 64; i++) {
            Position position = new Position(i);
            if ((chessBoard.boxArray[position.x][position.y].piece) != null) {
                Piece p = chessBoard.boxArray[position.x][position.y].piece;
                toggleButton[i].setIcon(p.icon);
            } else {
                toggleButton[i].setIcon(null);
            }
        }
    }

    private Move generateMove(int source, int destination) {
        Position pos = new Position(source);
        return new MoveNormal(color, gameID, 0, chessBoard.getPiece(pos.x, pos.y).pieceID, new Position(source), new Position(destination));
    }

    void processResult(GameFinished result) {
        System.out.println(result.winner + " wins.");
        if (result.winner == Color.BLACK) {
            notifyLabel.setText("Black wins");
        } else {
            notifyLabel.setText("White wins.");
        }
        JOptionPane.showMessageDialog(null, result.winner + " wins");
        client.disconnect();
    }

    void processResult(StateChange result) {
        for (int i = 0; i < result.deltas.size(); i++) {
            Delta d = result.deltas.get(i);
            Position p = chessBoard.pieceArray[d.pieceId].boxID;
            if (!p.isDead()) {
                chessBoard.boxArray[p.x][p.y].piece = null;
            }
            chessBoard.pieceArray[d.pieceId].boxID = new Position(d.positionId);
            Position p2 = new Position(d.positionId);
            if (!p2.isDead()) {
                chessBoard.boxArray[p2.x][p2.y].piece = chessBoard.pieceArray[d.pieceId];
            }
        }
        updateBoard();
    }

    void processResult(SetTurn result) {
        System.out.println("Recieved SetTurn");
        if (color == result.color) {
            enableBoard();
        } else {
            disableBoard();
        }
        if (result.color == Color.BLACK) {
            notifyLabel.setText("Black's Turn");
        } else {
            notifyLabel.setText("White's Turn");
        }
    }

    void processResult(InvalidMove result) {
        System.out.println("Recieved InvalidMove");
        if (result.color == color) {
            enableBoard();
        }
        if (result.color == Color.BLACK) {
            notifyLabel.setText("Black : Invalid Move");
        } else {
            notifyLabel.setText("White : Invalid Move");
        }
    }

    void displayBoard() {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClientBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> this.setVisible(true));
    }

    private JLabel notifyLabel;
    private JLabel gameIDLabel;
    private JToggleButton[] toggleButton;
}
