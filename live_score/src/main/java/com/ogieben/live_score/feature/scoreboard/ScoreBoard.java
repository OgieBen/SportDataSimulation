package com.ogieben.live_score.feature.scoreboard;

import com.ogieben.live_score.feature.match.LiveMatch;
import com.ogieben.live_score.interfaces.match.ILiveMatch;
import com.ogieben.live_score.interfaces.match.ILiveMatchScoreBoard;
import com.ogieben.live_score.interfaces.scores.ILiveScores;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class ScoreBoard implements ILiveMatchScoreBoard<ILiveMatch<ILiveScores>, ILiveScores> {

    private final ArrayList<ILiveMatch<ILiveScores>> scoreBoard = new ArrayList<>();

    @Override
    public boolean startMatch(ILiveMatch<ILiveScores> match) {
        scoreBoard.add(match);
        return match.startMatch();
    }

    @Override
    public ILiveScores finishMatch(ILiveMatch<ILiveScores> match) {
        var index = scoreBoard.indexOf(match);
        if (index > -1) {
            var scores = match.stopMatch();
            scoreBoard.remove(match);
            return scores;
        }
        return null;
    }

    @Override
    public ILiveScores updateScore(ILiveMatch<ILiveScores> match, int homeScore, int awayScore) {
        var index = scoreBoard.indexOf(match);
        if (index > -1) {
            var isUpdated = match.updateScore(homeScore, awayScore);
            return isUpdated ? match.getCurrentScore() : null;
        }
        return null;
    }

    @Override
    public Collection<ILiveMatch<ILiveScores>> getLiveFixturesSummary() {
        scoreBoard.sort((_liveMatchOne, _liveMatchTwo) -> {
            var liveMatchOneTotalMatchScore = _liveMatchOne.getTotalMatchScore();
            var liveMatchTwoTotalMatchScore = _liveMatchTwo.getTotalMatchScore();
            var matchOneStartTime = ((LiveMatch) _liveMatchOne).getStartTime();
            var matchTwoStartTime = ((LiveMatch) _liveMatchTwo).getStartTime();
            if (liveMatchOneTotalMatchScore == liveMatchTwoTotalMatchScore) {
                if (matchOneStartTime.isEqual(matchTwoStartTime)) return 0;
                return matchOneStartTime.isAfter(matchTwoStartTime) ? 1 : -1;
            }
            if (liveMatchOneTotalMatchScore > liveMatchTwoTotalMatchScore) return 1;
            return -1;
        });
        Collections.reverse(scoreBoard);
        return scoreBoard;
    }
}
