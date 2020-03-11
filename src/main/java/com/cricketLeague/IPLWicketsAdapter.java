package com.cricketLeague;

import java.io.IOException;
import java.util.Map;

public class IPLWicketsAdapter extends CricketAdapter {

    @Override
    public Map<String, CricketDTO> getCricketData(String... csvFilePath) throws IOException {
        Map<String,CricketDTO> cricketDTOMap = super.getCricketData(MostWicketsCSV.class,csvFilePath[0]);
        return cricketDTOMap;
    }
}
