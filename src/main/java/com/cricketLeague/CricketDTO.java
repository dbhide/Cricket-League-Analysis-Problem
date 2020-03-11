package com.cricketLeague;

public class CricketDTO {
    public double average;
    public double strikeRate;
    public int runs;
    public int fours;
    public int sixes;
    public int fourWickets;
    public int fiveWickets;
    public String player;

    public CricketDTO(MostRunsCSV mostRunsCSV) {
       runs = mostRunsCSV.runs;
        average = mostRunsCSV.battingAverage;
       strikeRate = mostRunsCSV.strikeRate;
       fours = mostRunsCSV.fours;
       sixes = mostRunsCSV.sixes;
       player=mostRunsCSV.player;
    }

    public CricketDTO(MostWicketsCSV mostWicketsCSV) {
        average = mostWicketsCSV.bowlingAverage;
        fourWickets = mostWicketsCSV.fourWickets;
        fiveWickets = mostWicketsCSV.fiveWickets;
        player=mostWicketsCSV.bowlerName;
        strikeRate=mostWicketsCSV.strikeRate;
    }
}