Live Score Sample
==============

This project is a demo project that implements a library the simulation sport score updates.

The main focus of the project is the live-score library. 

# Installation
The library can be included into any Gradle based project by adding the project as a local dependency to your
dependency closure.

```kotlin
dependencies {
    ...
    implementation(project(":live_score"))
}
```

## How to use the library
The library provides the _ILiveMatchScoreBoard_ API that allows the user to start and finish matches.

The ScoreBoard class implements this API. The ScoreBoard class can be used as follows:

```java
        var scoreBoard = new ScoreBoard();
```

### Start a match
The ScoreBoard provides a _startMatch_ method that accepts an _ILiveMatch_ object which will be used to start, update and finish a match.

```java
        var scoreBoard = new ScoreBoard();
        var liveMatch = new LiveMatch(new Team(0, "Mexico"), new Team(0, "Canada"));
        scoreBoard.startMatch(liveMatch);
```

### Update Scores

The ScoreBoard allows users to be able to update a running match score by calling the _updateScore_ method.

```java
        var scoreBoard = new ScoreBoard();
        var liveMatch = new LiveMatch(new Team(0, "Mexico"), new Team(0, "Canada"));
        scoreBoard.startMatch(liveMatch);
        scoreBoard.updateScore(liveMatch, 0, 5);
```

### Get Match Summary

The ScoreBoard object also allows the user to get the summary of all running matches.


```java
        var scoreBoard = new ScoreBoard();
        var liveMatch = new LiveMatch(new Team(0, "Mexico"), new Team(0, "Canada"));
        scoreBoard.startMatch(liveMatch);
        scoreBoard.updateScore(liveMatch, 0, 5);
        var summary = scoreBoard.getLiveFixturesSummary();
```

The _getLiveFixturesSummary()_ method returns the summary of all running matches

### Finish a match
The ScoreBoard API allows the user to be able to finish a match. Once a match is finished it is removed from the
list of running or live matches.


```java
        var scoreBoard = new ScoreBoard();
        var liveMatch = new LiveMatch(new Team(0, "Mexico"), new Team(0, "Canada"));
        scoreBoard.startMatch(liveMatch);
        scoreBoard.updateScore(liveMatch, 0, 5);
        var summary = scoreBoard.getLiveFixturesSummary();
        scoreBoard.finishMatch(liveMatch);
```
