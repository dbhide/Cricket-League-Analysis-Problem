package com.cricketLeague;

import java.util.Comparator;

public class BatsmanComparator implements Comparator<MostRunsCSV> {
    public int compare(MostRunsCSV player1, MostRunsCSV player2){
        return (player1.fours + player1.sixes) - (player2.fours + player2.sixes);
    }
}
