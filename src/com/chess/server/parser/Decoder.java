//package com.chess.server;
//
//public class Decoder {
//    public Move decode(String input){
//        try
//        {
//            String[] tokens = input.split(" ",7);
//            if (tokens.length != 7)
//                return null;
//            if (tokens[0] != "move")
//                return null;
//            int gameID = Integer.parseInt(tokens[1]);
//            MoveType moveType = MoveType.NORMAL;
//            if (tokens[2] == "normal")
//                moveType = MoveType.NORMAL;
//            else if (tokens[2] == "special")
//                moveType = MoveType.SPECIAL;
//            else if (tokens[2] == Mo)
//        }
//        catch (Exception e)
//        {
//            return null;
//        }
//    }
//}
