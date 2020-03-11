package com.cricketLeague;

import java.io.IOException;
import java.util.Map;

public class CricketAdapterFactory {
    public static Map<String, CricketDTO> getCricketData(CricketAnalyser.IPL ipl, String...csvFilePath) throws IOException {
        if(ipl.equals(CricketAnalyser.IPL.RUNS)){
            return new IPLRunsAdapter().getCricketData(csvFilePath);
        }
        else if(ipl.equals(CricketAnalyser.IPL.WICKETS)){
            return new IPLWicketsAdapter().getCricketData(csvFilePath);
        }
        else
            throw new CricketAnalyserException("NO Records Found",CricketAnalyserException.ExceptionType.CRICKET_DATA_NOT_FOUND);
    }
}
