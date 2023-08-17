package com.ogieben.live_score.feature.scoreboard;

import com.ogieben.live_score.feature.match.LiveMatch;
import com.ogieben.live_score.interfaces.match.ILiveMatch;
import com.ogieben.live_score.interfaces.scores.ILiveScores;
import com.ogieben.live_score.model.team.Team;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class ScoreBoardTest {

    @Test
    void startMatch() {
        var scoreBoard = new ScoreBoard();
        var liveMatch = new LiveMatch(new Team(0, "Mexico"), new Team(0, "Canada"));

        scoreBoard.startMatch(liveMatch);
        assert liveMatch.isMatchLive();
        scoreBoard.finishMatch(liveMatch);
    }

    @Test
    void finishMatch() {
        var scoreBoard = new ScoreBoard();
        var liveMatch = new LiveMatch(new Team(0, "Mexico"), new Team(0, "Canada"));
        var liveMatch2 = new LiveMatch(new Team(0, "Spain"), new Team(0, "Brazil"));

        scoreBoard.startMatch(liveMatch);
        scoreBoard.startMatch(liveMatch2);

        scoreBoard.updateScore(liveMatch,9,2);

        assert liveMatch.isMatchLive();
        assert liveMatch.getCurrentScore().awayTeamScore() == 2;
        assert liveMatch.getCurrentScore().homeTeamScore() == 9;
        assert liveMatch.getTotalMatchScore() == 11;

        scoreBoard.finishMatch(liveMatch);
        scoreBoard.finishMatch(liveMatch2);

        assert !liveMatch.isMatchLive();
        assert !liveMatch2.isMatchLive();
    }

    @Test
    void updateScore() {
        var scoreBoard = new ScoreBoard();
        var liveMatch = new LiveMatch(new Team(0, "Mexico"), new Team(0, "Canada"));

        scoreBoard.startMatch(liveMatch);
        scoreBoard.updateScore(liveMatch,9,2);

        assert liveMatch.isMatchLive();

        scoreBoard.finishMatch(liveMatch);

        assert liveMatch.getCurrentScore().awayTeamScore() == 2;
        assert liveMatch.getCurrentScore().homeTeamScore() == 9;
        assert liveMatch.getTotalMatchScore() == 11;
    }

    @Test
    void getLiveFixturesSummary() {
        var scoreBoard = new ScoreBoard();

        var liveMatch1 = new LiveMatch(new Team(0, "Mexico"), new Team(0, "Canada"));
        var liveMatch2 = new LiveMatch(new Team(0, "Spain"), new Team(0, "Brazil"));
        var liveMatch3 = new LiveMatch(new Team(0, "Germany"), new Team(0, "France"));
        var liveMatch4 = new LiveMatch(new Team(0, "Uruguay"), new Team(0, "Italy"));
        var liveMatch5 = new LiveMatch(new Team(0, "Argentina"), new Team(0, "Australia"));

        scoreBoard.startMatch(liveMatch1);
        scoreBoard.startMatch(liveMatch2);
        scoreBoard.startMatch(liveMatch3);
        scoreBoard.startMatch(liveMatch4);
        scoreBoard.startMatch(liveMatch5);

        scoreBoard.updateScore(liveMatch1, 0, 5);
        scoreBoard.updateScore(liveMatch2, 10, 2);
        scoreBoard.updateScore(liveMatch3, 2, 2);
        scoreBoard.updateScore(liveMatch4, 6, 6);
        scoreBoard.updateScore(liveMatch5, 3, 1);

        var summary = (ArrayList<ILiveMatch<ILiveScores>>) scoreBoard.getLiveFixturesSummary();

        assert summary.get(0).getTotalMatchScore() == 12;
        assert summary.get(0).getCurrentScore().awayTeamScore() == 6;
        assert summary.get(0).getCurrentScore().homeTeamScore() == 6;

        assert summary.get(1).getTotalMatchScore() == 12;
        assert summary.get(1).getCurrentScore().awayTeamScore() == 2;
        assert summary.get(1).getCurrentScore().homeTeamScore() == 10;

        assert summary.get(2).getTotalMatchScore() == 5;
        assert summary.get(2).getCurrentScore().awayTeamScore() == 5;
        assert summary.get(2).getCurrentScore().homeTeamScore() == 0;

        assert summary.get(3).getTotalMatchScore() == 4;
        assert summary.get(3).getCurrentScore().awayTeamScore() == 1;
        assert summary.get(3).getCurrentScore().homeTeamScore() == 3;

        assert summary.get(4).getTotalMatchScore() == 4;
        assert summary.get(4).getCurrentScore().awayTeamScore() == 2;
        assert summary.get(4).getCurrentScore().homeTeamScore() == 2;

    }

    @Test
    void verify_that_match_list_is_empty_after_the_last_game_ends(){
        var scoreBoard = new ScoreBoard();
        var liveMatch = new LiveMatch(new Team(0, "Mexico"), new Team(0, "Canada"));
        var liveMatch2 = new LiveMatch(new Team(0, "Spain"), new Team(0, "Brazil"));

        scoreBoard.startMatch(liveMatch);
        scoreBoard.startMatch(liveMatch2);

        scoreBoard.updateScore(liveMatch,9,2);

        assert liveMatch.isMatchLive();
        assert liveMatch.getCurrentScore().awayTeamScore() == 2;
        assert liveMatch.getCurrentScore().homeTeamScore() == 9;
        assert liveMatch.getTotalMatchScore() == 11;

        scoreBoard.finishMatch(liveMatch);
        scoreBoard.finishMatch(liveMatch2);

        var summary = (ArrayList<ILiveMatch<ILiveScores>>) scoreBoard.getLiveFixturesSummary();

        assert !liveMatch.isMatchLive();
        assert !liveMatch2.isMatchLive();
        assert summary.isEmpty();
    }
}
