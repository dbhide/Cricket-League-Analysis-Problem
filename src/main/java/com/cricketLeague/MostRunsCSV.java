package com.cricketLeague;

import com.opencsv.bean.CsvBindByName;

public class MostRunsCSV {
    @CsvBindByName(column = "Avg",required = true)
    public double battingAverage;

    @CsvBindByName(column = "SR",required = true)
    public double strikeRate;
}
