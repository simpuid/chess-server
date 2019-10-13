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
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.security.PublicKey;

public class ClientBoard extends JFrame {

    private int lastclickButton;
    private boolean isSecondClick;
    private Color color;
    private int gameID;
    private ChessBoard chessBoard;
    private Client client;
    public boolean isTurn;

    public ClientBoard(com.chess.chessboard.pieces.Color color, int gameID, Client client, boolean isTurn) {
        initComponents();
        this.color = color;
        this.gameID = gameID;
        chessBoard = new ChessBoard();
        this.client = client;
        this.isTurn = isTurn;
        gameIDLabel.setText("GameID : " + gameID);
        updateBoard();
        disableBoard();
    }

    private void initComponents() {
        nameLabel = new JLabel();
        playerLabel1 = new JLabel();
        playerLabel2 = new JLabel();
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

        nameLabel.setFont(new Font("Ubuntu", 1, 24));
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        nameLabel.setText("Online Chess Game");
        getContentPane().add(nameLabel);
        nameLabel.setBounds(5, 5, 895, 35);

        playerLabel1.setFont(new Font("Ubuntu", 1, 18));
        playerLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        playerLabel1.setText("Player 1");
        getContentPane().add(playerLabel1);
        playerLabel1.setBounds(5, 45, 295, 30);

        playerLabel2.setFont(new Font("Ubuntu", 1, 18));
        playerLabel2.setHorizontalAlignment(SwingConstants.CENTER);
        playerLabel2.setText("Player 2");
        getContentPane().add(playerLabel2);
        playerLabel2.setBounds(305, 45, 295, 30);

        gameIDLabel.setFont(new Font("Ubuntu", 1, 18));
        gameIDLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gameIDLabel.setText("GameID : ");
        getContentPane().add(gameIDLabel);
        gameIDLabel.setBounds(605, 45, 295, 30);

        board.setFont(new Font("Ubuntu", 1, 18));
        board.setMaximumSize(new Dimension(800, 800));
        board.setMinimumSize(new Dimension(800, 800));
        board.setPreferredSize(new Dimension(800, 800));
        getContentPane().add(board);
        board.setBounds(55, 100, 800, 800);
        board.setLayout(new GridLayout(8, 8, 0, 0));

        for (int i = 7; i >= 0; i--) {
            for (int j = 0; j < 8; j++) {
                int value = (8 * i) + j;
                toggleButton[value].setFont(new Font("Ubuntu", 1, 18));
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

    public void enableBoard() {
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
        MoveNormal moveNormal = new MoveNormal(color, gameID, 0, chessBoard.getPiece(pos.x, pos.y).pieceID, new Position(source), new Position(destination));
        return moveNormal;
    }

    public void processResult(GameFinished result) {
        System.out.println(result.winner + " wins.");
        JDialog endDialog = new JDialog(this, "Game Finished");
        JLabel resultLabel = new JLabel(result.winner + " wins.");
        endDialog.add(resultLabel);
        JButton endButton = new JButton("OK");
        endDialog.add(endButton);
        endDialog.setSize(new Dimension(300, 100));
        resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
        endDialog.setLayout(new GridLayout(1, 2, 40, 1));
        resultLabel.setFont(new Font("Ubuntu", 1, 18));
        endButton.setFont((new Font("Ubuntu", 1, 18)));
        endButton.addActionListener(actionEvent -> dialogClose(endDialog));
        endDialog.setVisible(true);
    }

    public void dialogClose(JDialog dialog) {
        dialog.setVisible(false);
        dialog.dispose();
        client.disconnect();
    }

    public void processResult(StateChange result) {
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

    public void processResult(SetTurn result) {
        System.out.println("Recieved SetTurn");
        if (color == result.color) {
            isTurn = true;
            enableBoard();
        } else {
            isTurn = false;
            disableBoard();
        }
    }

    public void processResult(InvalidMove result) {
        System.out.println("Recieved InvalidMove");
        JOptionPane.showMessageDialog(null, color + " Invalid Move");
//        JDialog moveDialog = new JDialog(this, "Invalid Move");
//        moveDialog.setSize(new Dimension(300, 100));
//        moveDialog.setLayout(new GridLayout(1, 1, 50, 10));
//        moveDialog.setVisible(true);
//
//        JLabel moveLabel = new JLabel("Invalid move.");
//        moveLabel.setHorizontalAlignment(SwingConstants.CENTER);
//        moveLabel.setFont(new Font("
//        Ubuntu", 1, 18));
//        moveDialog.add(moveLabel);
//
//        moveDialog.addFocusListener(new FocusAdapter() {
//            public void focusLost(FocusEvent evt) {
//                moveDialog.setVisible(false);
//                moveDialog.dispose();
//                enableBoard();
//            }
//        });
    }

    public void displayBoard() {
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
    private JLabel playerLabel1;
    private JLabel playerLabel2;
    private JLabel gameIDLabel;
    private JPanel board;
    private JToggleButton[] toggleButton;
}
