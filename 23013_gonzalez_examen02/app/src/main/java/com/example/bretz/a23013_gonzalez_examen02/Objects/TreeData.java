package com.example.bretz.a23013_gonzalez_examen02.Objects;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * Created by bretz on 10/27/2017.
 */

public class TreeData {

    public static TreeMap<String, SnakeLadders> getData(ArrayList<Board> boards) {
        TreeMap<String, SnakeLadders> expandableListDetail = new TreeMap<>();

        for (Board b : boards) {
            String bString = "Id: " + b.getId() +"\nName:" + b.getBoardName() +"\nAuthor: "+ b.getAuthor();
            List<String> snakeStrings = new ArrayList<>();
            List<String> ladderStrings = new ArrayList<>();
            SnakeLadders snkld = new SnakeLadders(snakeStrings,ladderStrings);
            List<String> ladder = snkld.getLadders();
            List<String> snakes = snkld.getSnakes();
            snakeStrings = snakes;
            ladderStrings = ladder;

            for(Board b2 : boards) {
                if (b.getId().equals(b2.getId())) {
                    String snString = "Snakes: " + b2.getSnakes();
                    String ldString = "Ladders: " + b2.getLadders();
                    snakeStrings.add(snString);
                    ladderStrings.add(ldString);
                    snkld.setSnakes(snakeStrings);
                    snkld.setLadders(ladderStrings);
                }
            }
            expandableListDetail.put(bString, snkld);
        }
        return expandableListDetail;
    }


}
