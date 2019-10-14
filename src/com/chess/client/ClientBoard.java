package com.chess.client;

import com.chess.chessboard.ChessBoard;
import com.chess.chessboard.pieces.Color;
import com.chess.chessboard.pieces.*;
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
        nameLabel = new JLabel();
        whiteLabel = new JLabel();
        blackLabel = new JLabel();
        gameIDLabel = new JLabel();
        board = new JPanel();
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
        setIconImage(new ImageIcon("mainicon.jpeg").getImage());

        nameLabel.setFont(new Font("Ubuntu", Font.BOLD, 24));
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        nameLabel.setText("Online Chess Game");
        getContentPane().add(nameLabel);
        nameLabel.setBounds(5, 5, 895, 35);

        whiteLabel.setFont(new Font("Ubuntu", Font.BOLD, 18));
        whiteLabel.setHorizontalAlignment(SwingConstants.CENTER);
        whiteLabel.setText("Welcome");
        whiteLabel.setForeground(new java.awt.Color(0, 0, 0));
        whiteLabel.setBackground(new java.awt.Color(255, 255, 255));
        whiteLabel.setBorder(BorderFactory.createLineBorder(java.awt.Color.black));
        whiteLabel.setOpaque(true);
        getContentPane().add(whiteLabel);
        whiteLabel.setBounds(5, 45, 295, 30);

        blackLabel.setFont(new Font("Ubuntu", Font.BOLD, 18));
        blackLabel.setHorizontalAlignment(SwingConstants.CENTER);
        blackLabel.setText("Welcome");
        blackLabel.setOpaque(true);
        blackLabel.setForeground(new java.awt.Color(255, 255, 255));
        whiteLabel.setBorder(BorderFactory.createLineBorder(java.awt.Color.white));
        blackLabel.setBackground(new java.awt.Color(0, 0, 0));
        getContentPane().add(blackLabel);
        blackLabel.setBounds(305, 45, 295, 30);

        gameIDLabel.setFont(new Font("Ubuntu", Font.BOLD, 18));
        gameIDLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gameIDLabel.setText("GameID : ");
        getContentPane().add(gameIDLabel);
        gameIDLabel.setBounds(605, 45, 295, 30);

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
                toggleButton[value].setText("Box" + value);
                toggleButton[value].addActionListener(actionEvent -> buttonClicked(value));
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
            toggleButton[boxID].setBackground(new java.awt.Color(255, 255, 51));
        } else {
            toggleButton[boxID].setBackground(new java.awt.Color(255, 255, 51));
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
            toggleButton[i].setBackground(new java.awt.Color(0.5f, 0.5f, 0.5f));
        }
    }

    private String getDisplayName(Piece piece) {
        if (piece instanceof Rook)
            return "rook";
        if (piece instanceof Bishop)
            return "bishop";
        if (piece instanceof Knight)
            return "knight";
        if (piece instanceof King)
            return "king";
        if (piece instanceof Pawn)
            return "pawn";
        if (piece instanceof Queen)
            return "queen";
        return "";
    }

    private void updateBoard() {
        for (int i = 0; i < 64; i++) {
            Position position = new Position(i);
            toggleButton[i].setBackground(new java.awt.Color(0.5f, 0.5f, 0.5f));
            if ((chessBoard.boxArray[position.x][position.y].piece) != null) {
                Piece p = chessBoard.boxArray[position.x][position.y].piece;
                toggleButton[i].setText(getDisplayName(p));
                if (p.color == Color.BLACK) {
                    toggleButton[i].setForeground(new java.awt.Color(0f, 0f, 0f));
                } else {
                    toggleButton[i].setForeground(new java.awt.Color(1f, 1f, 1f));
                }
            } else {
                toggleButton[i].setText("");
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
            blackLabel.setText("You win.");
            blackLabel.setBackground(new java.awt.Color(0, 255, 0));
        } else {
            whiteLabel.setText("You win.");
            whiteLabel.setBackground(new java.awt.Color(0, 255, 0));
        }
        JOptionPane.showMessageDialog(null, result.winner + " wins");
        client.disconnect();
    }

    void processResult(StateChange result) {
        System.out.println("test 3");
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
            blackLabel.setText("Your turn");
            blackLabel.setForeground(new java.awt.Color(255, 255, 0));
            whiteLabel.setText("Please Wait");
            whiteLabel.setForeground(new java.awt.Color(0, 255, 0));
        } else {
            whiteLabel.setText("Your turn");
            whiteLabel.setForeground(new java.awt.Color(255, 255, 0));
            blackLabel.setText("Please Wait");
            blackLabel.setForeground(new java.awt.Color(0, 255, 0));
        }
    }

    void processResult(InvalidMove result) {
        System.out.println("Recieved InvalidMove");
        if (result.color == color) {
            enableBoard();
        }
        if (result.color == Color.BLACK) {
            blackLabel.setText("Invalid Move");
            blackLabel.setForeground(new java.awt.Color(255, 0, 0));
            whiteLabel.setText("Please Wait");
            whiteLabel.setForeground(new java.awt.Color(0, 255, 0));
        } else {
            whiteLabel.setText("Invalid Move");
            whiteLabel.setForeground(new java.awt.Color(255, 0, 0));
            blackLabel.setText("Please Wait");
            blackLabel.setForeground(new java.awt.Color(0, 255, 0));
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
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> this.setVisible(true));
    }

    private JLabel nameLabel;
    private JLabel whiteLabel;
    private JLabel blackLabel;
    private JLabel gameIDLabel;
    private JPanel board;
    private JToggleButton[] toggleButton;
}
