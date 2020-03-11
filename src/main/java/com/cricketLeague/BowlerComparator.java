package com.cricketLeague;

import java.util.Comparator;

public class BowlerComparator implements Comparator<CricketDTO> {
    public int compare(CricketDTO player1, CricketDTO player2) {
        return (player1.fourWickets + player1.fiveWickets) - (player2.fourWickets + player2.fiveWickets);
    }
}