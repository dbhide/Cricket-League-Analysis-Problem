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
        String sortedCricketData = cricketAnalyser.getSortedCricketData(SortedField.AVERAGE);
        MostRunsCSV[] mostRunCsv = new Gson().fromJson(sortedCricketData, MostRunsCSV[].class);
        Assert.assertEquals(83.2,mostRunCsv[0].battingAverage,0.0 );
    }

    @Test
    public void givenIPLMostRunsCSVFile_ReturnsTopStrikingRate_InIPL2019() {
        CricketAnalyser cricketAnalyser = new CricketAnalyser();
        cricketAnalyser.getCricketDataFile(IPL_2019_MOST_RUNS_FILE_PATH);
        String sortedCricketData = cricketAnalyser.getSortedCricketData(SortedField.STRIKERATE);
        MostRunsCSV[] mostRunCsv = new Gson().fromJson(sortedCricketData, MostRunsCSV[].class);
        Assert.assertEquals(333.33,mostRunCsv[0].strikeRate,0.0 );
    }

    @Test
    public void givenIPLMostRunsCSVFile_ReturnsCricketer_WhoHitMaximum4sAnd6s_InIPL2019() {
        CricketAnalyser cricketAnalyser = new CricketAnalyser();
        cricketAnalyser.getCricketDataFile(IPL_2019_MOST_RUNS_FILE_PATH);
        String sortedCricketData = cricketAnalyser.getSortedCricketData(SortedField.MAXIMUM_HIT);
        MostRunsCSV[] mostRunCsv = new Gson().fromJson(sortedCricketData, MostRunsCSV[].class);
        Assert.assertEquals(83,mostRunCsv[0].fours + mostRunCsv[0].sixes);
    }

    @Test
    public void givenIPLMostRunsCSVFile_ReturnsCricketer_WhoHasMaximumStrikeRateWithMaximum4sAnd6s() {
        CricketAnalyser cricketAnalyser = new CricketAnalyser();
        cricketAnalyser.getCricketDataFile(IPL_2019_MOST_RUNS_FILE_PATH);
        String sortedCricketData = cricketAnalyser.getSortedCricketData(SortedField.BESTSTRIKE);
        MostRunsCSV[] mostRunCsv = new Gson().fromJson(sortedCricketData, MostRunsCSV[].class);
        Assert.assertEquals("Andre Russell",mostRunCsv[0].player);
    }

    @Test
    public void givenIPLMostRunsCSVFile_When_MaximumAverage_Returns_BestStrikeRate() {
        CricketAnalyser cricketAnalyser = new CricketAnalyser();
        cricketAnalyser.getCricketDataFile(IPL_2019_MOST_RUNS_FILE_PATH);
        String sortedCricketData = cricketAnalyser.getSortedCricketData(SortedField.AVGSTRIKE);
        MostRunsCSV[] mostRunCsv = new Gson().fromJson(sortedCricketData, MostRunsCSV[].class);
        Assert.assertEquals("MS Dhoni",mostRunCsv[0].player);
    }
}
