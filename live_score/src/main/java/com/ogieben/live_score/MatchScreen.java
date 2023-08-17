package com.ogieben.live_score;

import com.ogieben.live_score.feature.match.LiveMatch;
import com.ogieben.live_score.feature.scoreboard.ScoreBoard;
import com.ogieben.live_score.model.team.Team;

public class MatchScreen {
    public void showLiveMatches() {
        var scoreBoard = new ScoreBoard();

        var liveMatch = new LiveMatch(new Team(0, "Mexico"), new Team(0, "Canada"));
        var liveMatch2 = new LiveMatch(new Team(0, "Spain"), new Team(0, "Brazil"));
        var liveMatch3 = new LiveMatch(new Team(0, "Germany"), new Team(0, "France"));
        var liveMatch4 = new LiveMatch(new Team(0, "Uruguay"), new Team(0, "Italy"));
        var liveMatch5 = new LiveMatch(new Team(0, "Argentina"), new Team(0, "Australia"));

        scoreBoard.startMatch(liveMatch);
        scoreBoard.startMatch(liveMatch2);
        scoreBoard.startMatch(liveMatch3);
        scoreBoard.startMatch(liveMatch4);
        scoreBoard.startMatch(liveMatch5);

        scoreBoard.updateScore(liveMatch, 0, 5);
        scoreBoard.updateScore(liveMatch2, 10, 2);
        scoreBoard.updateScore(liveMatch3, 2, 2);
        scoreBoard.updateScore(liveMatch4, 6, 6);
        scoreBoard.updateScore(liveMatch5, 3, 1);

        System.out.println("\n Match Summary During Game: \n");

        for (var match : scoreBoard.getLiveFixturesSummary()) {
            System.out.println(match);
        }

        scoreBoard.finishMatch(liveMatch);
        scoreBoard.finishMatch(liveMatch2);
        scoreBoard.finishMatch(liveMatch3);
        scoreBoard.finishMatch(liveMatch4);
        scoreBoard.finishMatch(liveMatch5);

        System.out.println("\n Match Summary After Game: \n");

        for (var match : scoreBoard.getLiveFixturesSummary()) {
            System.out.println(match);
        }
    }
}
