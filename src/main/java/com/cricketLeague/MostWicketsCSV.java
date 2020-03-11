package com.cricketLeague;

import com.opencsv.bean.CsvBindByName;

public class MostWicketsCSV {
    @CsvBindByName(column = "PLAYER",required = true)
    public String bowlerName;

    @CsvBindByName(column = "Avg",required = true)
    public double bowlingAverage;

    @CsvBindByName(column = "4w",required = true)
    public int fourWickets;

    @CsvBindByName(column = "5w",required = true)
    public int fiveWickets;

    @CsvBindByName(column = "SR",required = true)
    public double strikeRate;

    @CsvBindByName(column = "Econ",required = true)
    public double economy;

    @CsvBindByName(column = "Wkts",required = true)
    public int totalWickets;

}


