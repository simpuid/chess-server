package com.chess.server.chessboard.pieces;

import com.chess.server.chessboard.ChessBoard;
import com.chess.server.common.Position;
import com.chess.server.common.moves.Move;
import com.chess.server.common.moves.MoveNormal;
import com.chess.server.common.moves.MoveUpgrade;

public class Pawn extends Piece {
    public Pawn(Color color, int pieceID, Position boxID, PieceType type) {
        this.color = color;
        this.pieceID = pieceID;
        this.boxID = boxID;
        this.type = type;
        this.moveCount = 0;
    }

    public boolean checkValid(MoveNormal move, ChessBoard chessBoard) {
        int xs = move.source.x;
        int ys = move.source.y;
        int xd = move.destination.x;
        int yd = move.destination.y;
        if (move.color == Color.WHITE) {
            if ((xd == xs) && (yd == (ys + 1))) {
                if (chessBoard.boxArray[xs][ys + 1].piece != null)
                    return false;
                else return true;
            }
            if ((xd == xs) && (yd == (ys + 2))) {
                if (chessBoard.pieceArray[move.pieceId].moveCount != 0)
                    return false;
                else {
                    if (chessBoard.boxArray[xs][ys + 2].piece != null)
                        return false;
                    else return true;
                }
            }
            if ((xd == (xs + 1)) && (yd == (ys + 1))) {
                if (chessBoard.boxArray[xs + 1][ys + 1].piece == null) {
                    if (ys != 4)
                        return false;
                    else {
                        if (chessBoard.boxArray[xs + 1][ys].piece == null)
                            return false;
                        else {
                            if (chessBoard.boxArray[xs + 1][ys].piece.color == Color.WHITE)
                                return false;
                            else {
                                if (chessBoard.boxArray[xs + 1][ys].piece.color == Color.BLACK) {
                                    if (chessBoard.boxArray[xs + 1][ys].piece.moveCount != 1)
                                        return false;
                                    else return true;
                                }
                            }
                        }
                    }
                } else {
                    if (chessBoard.boxArray[xs + 1][ys + 1].piece.color == Color.WHITE)
                        return false;
                    else {
                        if (chessBoard.boxArray[xs + 1][ys + 1].piece.color == Color.BLACK)
                            return true;
                    }
                }
            }
            if ((xd == (xs - 1)) && (yd == (ys + 1))) {
                if (chessBoard.boxArray[xs - 1][ys + 1].piece == null) {
                    if (ys != 4)
                        return false;
                    else {
                        if (chessBoard.boxArray[xs - 1][ys].piece == null)
                            return false;
                        else {
                            if (chessBoard.boxArray[xs - 1][ys].piece.color == Color.WHITE)
                                return false;
                            else {
                                if (chessBoard.boxArray[xs - 1][ys].piece.color == Color.BLACK) {
                                    if (chessBoard.boxArray[xs - 1][ys].piece.moveCount != 1)
                                        return false;
                                    else return true;
                                }
                            }
                        }
                    }
                } else {
                    if (chessBoard.boxArray[xs - 1][ys + 1].piece.color == Color.WHITE)
                        return false;
                    else {
                        if (chessBoard.boxArray[xs - 1][ys + 1].piece.color == Color.BLACK)
                            return true;
                    }
                }
            }
            return false;
        }
        if (move.color == Color.BLACK) {
            if ((xd == xs) && (yd == (ys - 1))) {
                if (chessBoard.boxArray[xs][ys - 1].piece != null)
                    return false;
                else return true;
            }
            if ((xd == xs) && (yd == (ys - 2))) {
                if (chessBoard.pieceArray[move.pieceId].moveCount != 0)
                    return false;
                else {
                    if (chessBoard.boxArray[xs][ys - 2].piece != null)
                        return false;
                    else return true;
                }
            }
            if ((xd == (xs - 1)) && (yd == (ys - 1))) {
                if (chessBoard.boxArray[xs - 1][ys - 1].piece == null) {
                    if (ys != 3)
                        return false;
                    else {
                        if (chessBoard.boxArray[xs - 1][ys].piece == null)
                            return false;
                        else {
                            if (chessBoard.boxArray[xs - 1][ys].piece.color == Color.BLACK)
                                return false;
                            else {
                                if (chessBoard.boxArray[xs - 1][ys].piece.color == Color.WHITE) {
                                    if (chessBoard.boxArray[xs - 1][ys].piece.moveCount != 1)
                                        return false;
                                    else return true;
                                }
                            }
                        }
                    }
                } else {
                    if (chessBoard.boxArray[xs - 1][ys - 1].piece.color == Color.BLACK)
                        return false;
                    else {
                        if (chessBoard.boxArray[xs - 1][ys - 1].piece.color == Color.WHITE)
                            return true;
                    }
                }
            }
            if ((xd == (xs + 1)) && (yd == (ys - 1))) {
                if (chessBoard.boxArray[xs + 1][ys - 1].piece == null) {
                    if (ys != 3)
                        return false;
                    else {
                        if (chessBoard.boxArray[xs + 1][ys].piece == null)
                            return false;
                        else {
                            if (chessBoard.boxArray[xs + 1][ys].piece.color == Color.BLACK)
                                return false;
                            else {
                                if (chessBoard.boxArray[xs + 1][ys].piece.color == Color.WHITE) {
                                    if (chessBoard.boxArray[xs + 1][ys].piece.moveCount != 1)
                                        return false;
                                    else return true;
                                }
                            }
                        }
                    }
                } else {
                    if (chessBoard.boxArray[xs + 1][ys - 1].piece.color == Color.BLACK)
                        return false;
                    else {
                        if (chessBoard.boxArray[xs + 1][ys - 1].piece.color == Color.WHITE)
                            return true;
                    }
                }
            }
            return false;
        }
        return false;
    }

    public boolean checkValidUpgrade(MoveUpgrade moveUpgrade, ChessBoard chessBoard) {
        int xs = moveUpgrade.source.x;
        int ys = moveUpgrade.source.y;
        int xd = moveUpgrade.destination.x;
        int yd = moveUpgrade.destination.y;
        if (moveUpgrade.color == Color.WHITE) {
            if ((xd == xs) && (yd == (ys + 1))) {
                if (chessBoard.boxArray[xs][ys + 1].piece != null)
                    return false;
                else return true;
            }
            if ((xd == (xs + 1)) && (yd == (ys + 1))) {
                if (chessBoard.boxArray[xs + 1][ys + 1].piece == null) {
                    return false;
                }
                else {
                    if (chessBoard.boxArray[xs + 1][ys + 1].piece.color == Color.WHITE)
                        return false;
                    else {
                        if (chessBoard.boxArray[xs + 1][ys + 1].piece.color == Color.BLACK)
                            return true;
                    }
                }
            }
            if ((xd == (xs - 1)) && (yd == (ys + 1))) {
                if (chessBoard.boxArray[xs - 1][ys + 1].piece == null) {
                    return false;
                }
                else {
                    if (chessBoard.boxArray[xs - 1][ys + 1].piece.color == Color.WHITE)
                        return false;
                    else {
                        if (chessBoard.boxArray[xs - 1][ys + 1].piece.color == Color.BLACK)
                            return true;
                    }
                }
            }
            return false;
        }
        if (moveUpgrade.color == Color.BLACK) {
            if ((xd == xs) && (yd == (ys - 1))) {
                if (chessBoard.boxArray[xs][ys - 1].piece != null)
                    return false;
                else return true;
            }
            if ((xd == (xs - 1)) && (yd == (ys - 1))) {
                if (chessBoard.boxArray[xs - 1][ys - 1].piece == null) {
                    return false;
                }
                else {
                    if (chessBoard.boxArray[xs - 1][ys - 1].piece.color == Color.BLACK)
                        return false;
                    else {
                        if (chessBoard.boxArray[xs - 1][ys - 1].piece.color == Color.WHITE)
                            return true;
                    }
                }
            }
            if ((xd == (xs + 1)) && (yd == (ys - 1))) {
                if (chessBoard.boxArray[xs + 1][ys - 1].piece == null) {
                    return false;
                }
                else {
                    if (chessBoard.boxArray[xs + 1][ys - 1].piece.color == Color.BLACK)
                        return false;
                    else {
                        if (chessBoard.boxArray[xs + 1][ys - 1].piece.color == Color.WHITE)
                            return true;
                    }
                }
            }
            return false;
        }
        return false;
    }
}
