package com.ogieben.live_score.interfaces.match;

public interface ILiveMatch<S> {
    boolean startMatch();
    S stopMatch();
    boolean updateScore(int homeTeamNewScore, int awayTeamNewScore);
    S getCurrentScore();
    boolean isMatchLive();
    String getMatchID();
    int getTotalMatchScore();
}
