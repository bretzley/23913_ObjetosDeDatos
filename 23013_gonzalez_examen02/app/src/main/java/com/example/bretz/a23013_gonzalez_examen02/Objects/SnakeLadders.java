package com.example.bretz.a23013_gonzalez_examen02.Objects;

import java.util.List;

/**
 * Created by bretz on 10/27/2017.
 */

public class SnakeLadders {

    private List<String> snakes;
    private List<String> ladders;

    public SnakeLadders(List<String> snakes, List<String> ladders){
        this.snakes = snakes;
        this.ladders = ladders;
    }

    public List<String> getSnakes() {
        return snakes;
    }

    public void setSnakes(List<String> snakes) {
        this.snakes = snakes;
    }

    public List<String> getLadders() {
        return ladders;
    }

    public void setLadders(List<String> ladders) {
        this.ladders = ladders;
    }
}
