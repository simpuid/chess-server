package com.chess.client;

import com.chess.server.common.moves.Move;
import com.chess.server.common.moves.MoveNormal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.chess.server.chessboard.pieces.Color;
public class ClientBoard extends JFrame {

    private int lastclickButton;
    private boolean isSecondClick;
    private Color color;
    private int gameID;

    public ClientBoard(com.chess.server.chessboard.pieces.Color color, int gameID) {
        initComponents();
        this.color = color;
        this.gameID = gameID;
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
        gameIDLabel.setText("GameID");
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
                toggleButton[value].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        buttonClicked(actionEvent, value);
                    }
                });
                board.add(toggleButton[value]);
            }
        }
        pack();

    }

    private void buttonClicked(ActionEvent actionEvent, int boxID) {
        if (!isSecondClick) {
            isSecondClick = true;
            lastclickButton = boxID;
            toggleButton[boxID].setText("First");
        } else {
            toggleButton[boxID].setText("Second");
            disableBoard();
        }
    }

    private void disableBoard() {
        for (int i = 0; i < 64; i++) {
            toggleButton[i].setEnabled(false);
        }
    }

    private Move generateMove(int source, int destination) {
        return null;
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
