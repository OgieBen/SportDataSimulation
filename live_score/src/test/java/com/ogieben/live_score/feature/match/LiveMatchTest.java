package com.ogieben.live_score.feature.match;

import com.ogieben.live_score.model.team.Team;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

class LiveMatchTest {

    @Test
    void startMatch() {
        var testMatch = generateRandomOneMatch();
        testMatch.startMatch();
        assert testMatch.isMatchLive();
    }

    @Test
    void updateScore() {
        var testMatch = generateRandomOneMatch();
        testMatch.startMatch();
        testMatch.updateScore(3, 5);
        assert testMatch.getCurrentScore().homeTeamScore() == 3;
        assert testMatch.getCurrentScore().awayTeamScore() == 5;
    }

    @Test
    void getCurrentScore() {
        var testMatch = generateRandomOneMatch();
        testMatch.stopMatch();
        testMatch.updateScore(3, 5);
        assert testMatch.getTotalMatchScore() == 0;
    }


    @Test
    void stopMatch() {
        var testMatch = generateRandomOneMatch();
        testMatch.stopMatch();
        assert !testMatch.isMatchLive();
    }

    @Test
    void verify_that_scores_do_not_change_after_match() {
        var testMatch = generateRandomOneMatch();
        testMatch.stopMatch();
        testMatch.updateScore(14, 9);
        assert testMatch.getCurrentScore().homeTeamScore() == 0;
        assert testMatch.getCurrentScore().awayTeamScore() == 0;
        assert testMatch.getTotalMatchScore() == 0;
    }

    @Test
    void verify_that_scores_are_valid() {
        var testMatch = generateRandomOneMatch();
        var isUpdated = testMatch.updateScore(-14, -12);
        testMatch.stopMatch();
        assert testMatch.getCurrentScore().homeTeamScore() == 0;
        assert testMatch.getCurrentScore().awayTeamScore() == 0;
        assert !isUpdated;
    }

    @Test
    void verify_six_games_have_been_started() {
        var counter = 0;
        var randomlyGeneratedMatches = generateRandomMatches();
        randomlyStartAndUpdateFirstFiveMatches(randomlyGeneratedMatches);
        for (var matches : randomlyGeneratedMatches) {
            counter += matches.isMatchLive() ? 1 : 0;
        }

        assert counter == 5;
    }


    private static List<LiveMatch> generateRandomMatches() {
        var listOfTeams = new ArrayList<LiveMatch>();
        for (int i = 0; i < 100; i++) {
            var id = UUID.randomUUID().toString();
            var homeTeam = id.substring(0, 4);
            var awayTeam = id.substring(5, 8);
            var liveMatch = new LiveMatch(new Team(0, homeTeam), new Team(0, awayTeam));
            listOfTeams.add(liveMatch);
        }
        return listOfTeams;
    }

    private LiveMatch generateRandomOneMatch() {

        var id = UUID.randomUUID().toString();
        var homeTeam = id.substring(0, 4);
        var awayTeam = id.substring(5, 8);
        return new LiveMatch(new Team(0, homeTeam), new Team(0, awayTeam));
    }

    private void randomlyStartAndUpdateFiveMatches(List<LiveMatch> matches) {
        for (int i = 0; i < 50; i++) {
            Random randomNum = new Random();
            int randomIndex = randomNum.nextInt(100);
            var match = matches.get(randomIndex);
            match.startMatch();
            if (randomIndex % 2 == 0) {
                match.updateScore(match.getCurrentScore().homeTeamScore() + 1, match.getCurrentScore().awayTeamScore());
            } else {
                match.updateScore(match.getCurrentScore().homeTeamScore(), match.getCurrentScore().awayTeamScore() + 1);
            }
        }
    }

    private void randomlyStartAndUpdateFirstFiveMatches(List<LiveMatch> matches) {
        for (int i = 0; i < 5; i++) {
            Random randomNum = new Random();
            var match = matches.get(i);
            match.startMatch();
            if (i % 2 == 0) {
                match.updateScore(match.getCurrentScore().homeTeamScore() + 1, match.getCurrentScore().awayTeamScore());
            } else {
                match.updateScore(match.getCurrentScore().homeTeamScore(), match.getCurrentScore().awayTeamScore() + 1);
            }
        }
    }
}
