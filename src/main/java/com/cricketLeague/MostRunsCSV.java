package com.cricketLeague;

import com.opencsv.bean.CsvBindByName;

public class MostRunsCSV {
    @CsvBindByName(column = "Avg",required = true)
    public double battingAverage;

    @CsvBindByName(column = "SR",required = true)
    public double strikeRate;

    @CsvBindByName(column = "4s",required = true)
    public int fours;

    @CsvBindByName(column = "6s",required = true)
    public int sixes;
}
