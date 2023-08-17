package com.ogieben.live_score.model.scores;

import com.ogieben.live_score.interfaces.scores.ILiveScores;
import com.ogieben.live_score.model.team.Team;

public class MatchScore implements ILiveScores {

    private final Team homeTeam;
    private final Team awayTeam;

    public MatchScore(Team homeTeam, Team awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
    }

    @Override
    public int homeTeamScore() {
        return homeTeam.getScore();
    }

    @Override
    public int awayTeamScore() {
        return awayTeam.getScore();
    }
}
