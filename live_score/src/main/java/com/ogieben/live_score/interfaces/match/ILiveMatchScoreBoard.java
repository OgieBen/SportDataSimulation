package com.ogieben.live_score.interfaces.match;

import java.util.Collection;

public interface ILiveMatchScoreBoard<M, S> {
    boolean startMatch(M match);
    S finishMatch(M match);
    S updateScore(M match, int homeScore, int awayScore);
    Collection<M> getLiveFixturesSummary();
}
