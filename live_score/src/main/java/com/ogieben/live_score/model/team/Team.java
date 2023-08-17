package com.ogieben.live_score.model.team;

public class Team {
    private int score;
    private String name;

    public Team(int score, String name) {
        this.score = score;
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }
}
