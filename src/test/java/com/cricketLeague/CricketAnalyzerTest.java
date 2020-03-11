package com.cricketLeague;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

public class CricketAnalyzerTest {

    private static final String IPL_2019_MOST_RUNS_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostRuns.csv";
    private static final String IPL_2019_MOST_WICKETS_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostWkts.csv";


    @Test
    public void givenIPLMostRunsCSVFile_ReturnsTopBattingAverage_InIPL2019() {
        CricketAnalyser cricketAnalyser = new CricketAnalyser();
        cricketAnalyser.getBattingDataFile(IPL_2019_MOST_RUNS_FILE_PATH);
        String sortedCricketData = cricketAnalyser.getSortedCricketData(SortedField.AVERAGE);
        CricketDTO[] mostRunCsv = new Gson().fromJson(sortedCricketData, CricketDTO[].class);
        Assert.assertEquals("MS Dhoni",mostRunCsv[0].player);
    }

    @Test
    public void givenIPLMostRunsCSVFile_ReturnsTopStrikingRate_InIPL2019() {
        CricketAnalyser cricketAnalyser = new CricketAnalyser();
        cricketAnalyser.getBattingDataFile(IPL_2019_MOST_RUNS_FILE_PATH);
        String sortedCricketData = cricketAnalyser.getSortedCricketData(SortedField.STRIKERATE);
        CricketDTO[] mostRunCsv = new Gson().fromJson(sortedCricketData, CricketDTO[].class);
        Assert.assertEquals("Ishant Sharma",mostRunCsv[0].player);
    }

    @Test
    public void givenIPLMostRunsCSVFile_ReturnsCricketer_WhoHitMaximum4sAnd6s_InIPL2019() {
        CricketAnalyser cricketAnalyser = new CricketAnalyser();
        cricketAnalyser.getBattingDataFile(IPL_2019_MOST_RUNS_FILE_PATH);
        String sortedCricketData = cricketAnalyser.getSortedCricketData(SortedField.MAXIMUM_HIT);
        CricketDTO[] mostRunCsv = new Gson().fromJson(sortedCricketData, CricketDTO[].class);
        Assert.assertEquals("Andre Russell",mostRunCsv[0].player);
    }

    @Test
    public void givenIPLMostRunsCSVFile_ReturnsCricketer_WhoHasMaximumStrikeRateWithMaximum4sAnd6s() {
        CricketAnalyser cricketAnalyser = new CricketAnalyser();
        cricketAnalyser.getBattingDataFile(IPL_2019_MOST_RUNS_FILE_PATH);
        String sortedCricketData = cricketAnalyser.getSortedCricketData(SortedField.BESTSTRIKE);
        CricketDTO[] mostRunCsv = new Gson().fromJson(sortedCricketData, CricketDTO[].class);
        Assert.assertEquals("Andre Russell",mostRunCsv[0].player);
    }

    @Test
    public void givenIPLMostRunsCSVFile_When_MaximumAverage_Returns_BestStrikeRate() {
        CricketAnalyser cricketAnalyser = new CricketAnalyser();
        cricketAnalyser.getBattingDataFile(IPL_2019_MOST_RUNS_FILE_PATH);
        String sortedCricketData = cricketAnalyser.getSortedCricketData(SortedField.AVGSTRIKE);
        CricketDTO[] mostRunCsv = new Gson().fromJson(sortedCricketData, CricketDTO[].class);
        Assert.assertEquals("MS Dhoni",mostRunCsv[0].player);
    }

    @Test
    public void givenIPLMostRunsCSVFile_WhenSortedOnAverage_ReturnsMaximumRuns() {
        CricketAnalyser cricketAnalyser = new CricketAnalyser();
        cricketAnalyser.getBattingDataFile(IPL_2019_MOST_RUNS_FILE_PATH);
        String sortedCricketData = cricketAnalyser.getSortedCricketData(SortedField.MAXRUNS);
        CricketDTO[] mostRunCsv = new Gson().fromJson(sortedCricketData, CricketDTO[].class);
        Assert.assertEquals("David Warner", mostRunCsv[0].player);
    }

    @Test
    public void givenIPLMostWicketsCSVFile_ReturnsTopBowlingAverage_InIPL2019() {
        CricketAnalyser cricketAnalyser = new CricketAnalyser();
        cricketAnalyser.getBowlingDataFile(IPL_2019_MOST_WICKETS_FILE_PATH);
        String sortedCricketData = cricketAnalyser.getSortedCricketData(SortedField.AVERAGE);
        CricketDTO[] mostWicketsCsv = new Gson().fromJson(sortedCricketData, CricketDTO[].class);
        Assert.assertEquals("Krishnappa Gowtham",mostWicketsCsv[0].player);
    }
}
