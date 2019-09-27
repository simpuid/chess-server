package com.chess.chessboard.pieces;

import com.chess.chessboard.ChessBoard;
import com.chess.common.Position;
import com.chess.common.moves.MoveNormal;
import com.chess.common.moves.MoveUpgrade;
import com.chess.common.results.Delta;
import com.chess.common.results.StateChange;

public class Pawn extends Piece {
    public Pawn(Color color, int pieceID, Position boxID, PieceType type) {
        this.color = color;
        this.pieceID = pieceID;
        this.boxID = boxID;
        this.type = type;
        this.moveCount = 0;
    }

    private int sign(int x) {
        if (x == 0) return 0;
        return x > 0 ? 1 : -1;
    }
//    public boolean checkValid(MoveNormal move, ChessBoard chessBoard) {
//        int xs = move.source.x;
//        int ys = move.source.y;
//        int xd = move.destination.x;
//        int yd = move.destination.y;
//        if (move.color == Color.WHITE) {
//            if ((xd == xs) && (yd == (ys + 1))) {
//                if (chessBoard.boxArray[xs][ys + 1].piece != null)
//                    return false;
//                else return true;
//            }
//            if ((xd == xs) && (yd == (ys + 2))) {
//                if (chessBoard.pieceArray[move.pieceId].moveCount != 0)
//                    return false;
//                else {
//                    if (chessBoard.boxArray[xs][ys + 2].piece != null)
//                        return false;
//                    else return true;
//                }
//            }
//            if ((xd == (xs + 1)) && (yd == (ys + 1))) {
//                if (chessBoard.boxArray[xs + 1][ys + 1].piece == null) {
//                    if (ys != 4)
//                        return false;
//                    else {
//                        if (chessBoard.boxArray[xs + 1][ys].piece == null)
//                            return false;
//                        else {
//                            if (chessBoard.boxArray[xs + 1][ys].piece.color == Color.WHITE)
//                                return false;
//                            else {
//                                if (chessBoard.boxArray[xs + 1][ys].piece.color == Color.BLACK) {
//                                    if (chessBoard.boxArray[xs + 1][ys].piece.moveCount != 1)
//                                        return false;
//                                    else return true;
//                                }
//                            }
//                        }
//                    }
//                } else {
//                    if (chessBoard.boxArray[xs + 1][ys + 1].piece.color == Color.WHITE)
//                        return false;
//                    else {
//                        if (chessBoard.boxArray[xs + 1][ys + 1].piece.color == Color.BLACK)
//                            return true;
//                    }
//                }
//            }
//            if ((xd == (xs - 1)) && (yd == (ys + 1))) {
//                if (chessBoard.boxArray[xs - 1][ys + 1].piece == null) {
//                    if (ys != 4)
//                        return false;
//                    else {
//                        if (chessBoard.boxArray[xs - 1][ys].piece == null)
//                            return false;
//                        else {
//                            if (chessBoard.boxArray[xs - 1][ys].piece.color == Color.WHITE)
//                                return false;
//                            else {
//                                if (chessBoard.boxArray[xs - 1][ys].piece.color == Color.BLACK) {
//                                    if (chessBoard.boxArray[xs - 1][ys].piece.moveCount != 1)
//                                        return false;
//                                    else return true;
//                                }
//                            }
//                        }
//                    }
//                } else {
//                    if (chessBoard.boxArray[xs - 1][ys + 1].piece.color == Color.WHITE)
//                        return false;
//                    else {
//                        if (chessBoard.boxArray[xs - 1][ys + 1].piece.color == Color.BLACK)
//                            return true;
//                    }
//                }
//            }
//            return false;
//        }
//        if (move.color == Color.BLACK) {
//            if ((xd == xs) && (yd == (ys - 1))) {
//                if (chessBoard.boxArray[xs][ys - 1].piece != null)
//                    return false;
//                else return true;
//            }
//            if ((xd == xs) && (yd == (ys - 2))) {
//                if (chessBoard.pieceArray[move.pieceId].moveCount != 0)
//                    return false;
//                else {
//                    if (chessBoard.boxArray[xs][ys - 2].piece != null)
//                        return false;
//                    else return true;
//                }
//            }
//            if ((xd == (xs - 1)) && (yd == (ys - 1))) {
//                if (chessBoard.boxArray[xs - 1][ys - 1].piece == null) {
//                    if (ys != 3)
//                        return false;
//                    else {
//                        if (chessBoard.boxArray[xs - 1][ys].piece == null)
//                            return false;
//                        else {
//                            if (chessBoard.boxArray[xs - 1][ys].piece.color == Color.BLACK)
//                                return false;
//                            else {
//                                if (chessBoard.boxArray[xs - 1][ys].piece.color == Color.WHITE) {
//                                    if (chessBoard.boxArray[xs - 1][ys].piece.moveCount != 1)
//                                        return false;
//                                    else return true;
//                                }
//                            }
//                        }
//                    }
//                } else {
//                    if (chessBoard.boxArray[xs - 1][ys - 1].piece.color == Color.BLACK)
//                        return false;
//                    else {
//                        if (chessBoard.boxArray[xs - 1][ys - 1].piece.color == Color.WHITE)
//                            return true;
//                    }
//                }
//            }
//            if ((xd == (xs + 1)) && (yd == (ys - 1))) {
//                if (chessBoard.boxArray[xs + 1][ys - 1].piece == null) {
//                    if (ys != 3)
//                        return false;
//                    else {
//                        if (chessBoard.boxArray[xs + 1][ys].piece == null)
//                            return false;
//                        else {
//                            if (chessBoard.boxArray[xs + 1][ys].piece.color == Color.BLACK)
//                                return false;
//                            else {
//                                if (chessBoard.boxArray[xs + 1][ys].piece.color == Color.WHITE) {
//                                    if (chessBoard.boxArray[xs + 1][ys].piece.moveCount != 1)
//                                        return false;
//                                    else return true;
//                                }
//                            }
//                        }
//                    }
//                } else {
//                    if (chessBoard.boxArray[xs + 1][ys - 1].piece.color == Color.BLACK)
//                        return false;
//                    else {
//                        if (chessBoard.boxArray[xs + 1][ys - 1].piece.color == Color.WHITE)
//                            return true;
//                    }
//                }
//            }
//            return false;
//        }
//        return false;
//    }

    @Override
    public boolean checkValid(MoveNormal move, ChessBoard chessBoard) {
        int sx = move.source.x, sy = move.source.y;
        int dx = move.destination.x, dy = move.destination.y;
        int absX = Math.abs(dx - sx), absY = Math.abs(dy - sy);
        int signX = sign(dx - sx), signY = sign(dy - sy);
        if (signY == 0)
            return false;
        if (signY == 1 && color == Color.BLACK)
            return false;
        if (signY == -1 && color == Color.WHITE)
            return false;
        if (absY > 2)
            return false;
        if (absY == 2) {
            if (absX != 0)
                return false;
            if (moveCount != 0)
                return false;
        }
        if (absX != 0) {
            if (chessBoard.getPiece(sx + signX, sy + signY) != null) {
                return chessBoard.getPiece(sx + signX, sy + signY).color != color;
            }
            if (chessBoard.getPiece(sx + signX, sy) == null)
                return false;
            if (!(chessBoard.getPiece(sx + signX, sy) instanceof Pawn))
                return false;
            if (chessBoard.getPiece(sx + signX, sy).moveCount != 1)
                return false;
            if (chessBoard.getPiece(sx + signX, sy).color == color)
                return false;
            return sy == (7 + signY) / 2;
        } else {
            return chessBoard.getPiece(dx, dy) == null;
        }
    }

    public boolean checkValidUpgrade(MoveUpgrade moveUpgrade, ChessBoard chessBoard) {
        int xs = moveUpgrade.source.x;
        int ys = moveUpgrade.source.y;
        int xd = moveUpgrade.destination.x;
        int yd = moveUpgrade.destination.y;
        if (moveUpgrade.color == Color.WHITE) {
            if ((xd == xs) && (yd == (ys + 1))) {
                return chessBoard.boxArray[xs][ys + 1].piece == null;
            }
            if ((xd == (xs + 1)) && (yd == (ys + 1))) {
                if (chessBoard.boxArray[xs + 1][ys + 1].piece == null) {
                    return false;
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
                    return false;
                } else {
                    if (chessBoard.boxArray[xs - 1][ys + 1].piece.color == Color.WHITE)
                        return false;
                    else {
                        return chessBoard.boxArray[xs - 1][ys + 1].piece.color == Color.BLACK;
                    }
                }
            }
            return false;
        }
        if (moveUpgrade.color == Color.BLACK) {
            if ((xd == xs) && (yd == (ys - 1))) {
                return chessBoard.boxArray[xs][ys - 1].piece == null;
            }
            if ((xd == (xs - 1)) && (yd == (ys - 1))) {
                if (chessBoard.boxArray[xs - 1][ys - 1].piece == null) {
                    return false;
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
                    return false;
                } else {
                    if (chessBoard.boxArray[xs + 1][ys - 1].piece.color == Color.BLACK)
                        return false;
                    else {
                        return chessBoard.boxArray[xs + 1][ys - 1].piece.color == Color.WHITE;
                    }
                }
            }
            return false;
        }
        return false;
    }

    public StateChange movePiece(MoveNormal move, ChessBoard chessBoard) {
        this.boxID = move.destination;
        int xd = move.destination.x;
        int yd = move.destination.y;
        int xs = move.source.x;
        int ys = move.source.y;
        StateChange change = new StateChange();
        this.moveCount = this.moveCount + 1;
        if (chessBoard.boxArray[xd][yd].piece == null) {
            chessBoard.boxArray[xd][yd].piece = chessBoard.pieceArray[move.pieceId];
            chessBoard.boxArray[xs][ys].piece = null;
            Delta delta = new Delta(move.pieceId, xd + yd * 8);
            change.deltas.add(delta);
            if (move.color == Color.BLACK) {
                int pid;
                if ((xd == xs + 1) && (yd == ys - 1)) {
                    pid = chessBoard.boxArray[xd][ys].piece.pieceID;
                    chessBoard.pieceArray[pid].boxID = new Position(64);
                    chessBoard.boxArray[xd][ys].piece = null;
                    change.deltas.add(new Delta(pid, 64));
                }
                if ((xd == xs - 1) && (yd == ys - 1)) {
                    pid = chessBoard.boxArray[xd][ys].piece.pieceID;
                    chessBoard.pieceArray[pid].boxID = new Position(64);
                    chessBoard.boxArray[xd][ys].piece = null;
                    change.deltas.add(new Delta(pid, 64));
                }
            }
            if (move.color == Color.WHITE) {
                int pid;
                if ((xd == xs + 1) && (yd == ys + 1)) {
                    pid = chessBoard.boxArray[xd][ys].piece.pieceID;
                    chessBoard.pieceArray[pid].boxID = new Position(64);
                    chessBoard.boxArray[xd][ys].piece = null;
                    change.deltas.add(new Delta(pid, 64));
                }
                if ((xd == xs - 1) && (yd == ys + 1)) {
                    pid = chessBoard.boxArray[xd][ys].piece.pieceID;
                    chessBoard.pieceArray[pid].boxID = new Position(64);
                    chessBoard.boxArray[xd][ys].piece = null;
                    change.deltas.add(new Delta(pid, 64));
                }
            }
        } else {
            int pid = chessBoard.boxArray[xd][yd].piece.pieceID;
            chessBoard.pieceArray[pid].boxID = new Position(64);
            change.deltas.add(new Delta(pid, 64));
            chessBoard.boxArray[xd][yd].piece = chessBoard.pieceArray[move.pieceId];
            change.deltas.add(new Delta(move.pieceId, xd + yd * 8));
            chessBoard.boxArray[xs][ys].piece = null;
        }
        return change;
    }
}
