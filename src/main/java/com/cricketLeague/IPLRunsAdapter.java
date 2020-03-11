package com.cricketLeague;

import java.io.IOException;
import java.util.Map;

public class IPLRunsAdapter extends CricketAdapter {
    @Override
    public Map<String, CricketDTO> getCricketData(String... csvFilePath) throws IOException {
        Map<String,CricketDTO> cricketCsvMap = super.getCricketData(MostRunsCSV.class,csvFilePath[0]);
        return cricketCsvMap;
    }
}
