package com.ogieben.sportradar;

import com.ogieben.live_score.MatchScreen;

public class Main {
    public static void main(String[] args) {
        var liveScore = new MatchScreen();
        liveScore.showLiveMatches();
    }
}
