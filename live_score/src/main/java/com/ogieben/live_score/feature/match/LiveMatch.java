package com.ogieben.live_score.feature.match;

import com.ogieben.live_score.interfaces.match.ILiveMatch;
import com.ogieben.live_score.interfaces.scores.ILiveScores;
import com.ogieben.live_score.model.scores.MatchScore;
import com.ogieben.live_score.model.team.Team;

import java.time.LocalDateTime;
import java.util.UUID;

public class LiveMatch implements ILiveMatch<ILiveScores> {
    private LocalDateTime startTime;
    private LocalDateTime finishTime;
    private final String matchId;
    private final Team homeTeam;
    private final Team awayTeam;
    private boolean isMatchLive = false;

    public LiveMatch(Team homeTeam, Team awayTeam) {
        this.matchId = UUID.randomUUID().toString();
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
    }

    @Override
    public boolean startMatch() {
        if(!isMatchLive) {
            startTime = LocalDateTime.now();
            return isMatchLive = true;
        }
        return false;
    }

    @Override
    public ILiveScores stopMatch() {
        isMatchLive = false;
        finishTime = LocalDateTime.now();
        return new MatchScore(this.homeTeam, this.awayTeam);
    }

    @Override
    public boolean updateScore(int homeTeamNewScore, int awayTeamNewScore) {
        if(homeTeamNewScore >= 0 && awayTeamNewScore >= 0){
            if(isMatchLive){
                this.homeTeam.setScore(homeTeamNewScore);
                this.awayTeam.setScore(awayTeamNewScore);
                return true;
            }
        }
        return false;
    }

    @Override
    public ILiveScores getCurrentScore() {
        return new MatchScore(this.homeTeam, this.awayTeam);
    }

    @Override
    public boolean isMatchLive() {
        return isMatchLive;
    }

    @Override
    public String getMatchID() {
        return matchId;
    }

    public String getMatchId(){
        return matchId;
    }

    @Override
    public int getTotalMatchScore(){
        return homeTeam.getScore() + awayTeam.getScore();
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getFinishTime() {
        return finishTime;
    }

    @Override
    public String toString() {
        return "LiveFixture{"
                + homeTeam.getName() + " " + homeTeam.getScore() +
                " : "
                +  awayTeam.getName() + " " + awayTeam.getScore() +
                ", isMatchLive=" + isMatchLive + '}';
    }
}
