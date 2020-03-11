package com.cricketLeague;

import java.util.Comparator;

public class BatsmanComparator implements Comparator<CricketDTO> {
    public int compare(CricketDTO player1, CricketDTO player2){
        return (player1.fours + player1.sixes) - (player2.fours + player2.sixes);
    }
}
