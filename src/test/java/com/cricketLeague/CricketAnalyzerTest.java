package com.cricketLeague;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

public class CricketAnalyzerTest {

    private static final String IPL_2019_MOST_RUNS_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostRuns.csv";

    @Test
    public void givenIPLMostRunsCSVFile_ReturnsTopBattingAverage_InIPL2019() {
        CricketAnalyser cricketAnalyser = new CricketAnalyser();
        cricketAnalyser.getCricketDataFile(IPL_2019_MOST_RUNS_FILE_PATH);
        String sortedCricketData = cricketAnalyser.getSortedCricketData();
        MostRunsCSV[] mostRunCsv = new Gson().fromJson(sortedCricketData, MostRunsCSV[].class);
        Assert.assertEquals(83.2,mostRunCsv[0].battingAverage,0.0 );
    }



}
