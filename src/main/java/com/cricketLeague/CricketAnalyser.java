package com.cricketLeague;

import abc.CSVBuilderFactory;
import abc.ICSVBuilder;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

public class CricketAnalyser {

    List<CricketDTO> cricketDTOList;
    Map<SortedField, Comparator<CricketDTO>> sortedMap;
    Map<String, CricketDTO> cricketDTOMap;

    public enum IPL {RUNS, WICKETS};

    public CricketAnalyser() {
        this.cricketDTOList = new ArrayList<>();
        this.sortedMap = new HashMap<>();
        this.cricketDTOMap = new HashMap<>();

        Comparator<CricketDTO> avgStrike = Comparator.comparing(res -> res.average);
        this.sortedMap.put(SortedField.AVGSTRIKE, avgStrike.thenComparing(res -> res.strikeRate));

        this.sortedMap.put(SortedField.AVERAGE, Comparator.comparing(IplFields -> IplFields.average));
        this.sortedMap.put(SortedField.STRIKERATE, Comparator.comparing(IplFields -> IplFields.strikeRate));
        this.sortedMap.put(SortedField.ECONOMY, Comparator.comparing(IplFields -> IplFields.economy));
        this.sortedMap.put(SortedField.STRIKE_WITH_WICKETS, new BowlerComparator().thenComparing(IplFields -> IplFields.strikeRate));
        this.sortedMap.put(SortedField.MAXIMUM_HIT, Comparator.comparing(IplFields -> IplFields.fours + IplFields.sixes));
        this.sortedMap.put(SortedField.BESTSTRIKE, new BatsmanComparator().thenComparing(res -> res.strikeRate));

        Comparator<CricketDTO> avgBowl = Comparator.comparing(res -> res.average);
        this.sortedMap.put(SortedField.BOWLING_AVERAGE, avgBowl.thenComparing(res -> res.strikeRate));

        Comparator<CricketDTO> maxWickets = Comparator.comparing(res -> res.totalWickets);
        this.sortedMap.put(SortedField.MAXIMUM_WICKETS, maxWickets.thenComparing(res -> res.average));

        Comparator<CricketDTO> maxRuns = Comparator.comparing(res -> res.runs);
        this.sortedMap.put(SortedField.MAXRUNS, maxRuns.thenComparing(res -> res.average));
    }

    public int getCricketDataFile(IPL ipl, String... csvFilePath) throws IOException {
        cricketDTOMap = CricketAdapterFactory.getCricketData(ipl, csvFilePath);
        cricketDTOList = cricketDTOMap.values().stream().collect(Collectors.toList());
        return cricketDTOMap.size();
    }


        public String getSortedCricketData(SortedField sortedField) {
            if (cricketDTOList == null || cricketDTOList.size() == 0) {
                throw new CricketAnalyserException("No Data", CricketAnalyserException.ExceptionType.CRICKET_DATA_NOT_FOUND);
            }
            this.sort(this.sortedMap.get(sortedField));
            Collections.reverse(cricketDTOList);
            String sortedIplData = new Gson().toJson(cricketDTOList);
            return sortedIplData;
        }

        private void sort (Comparator<CricketDTO> cricketComparator) {
            for (int i = 0; i < cricketDTOList.size() - 1; i++) {
                for (int j = 0; j < cricketDTOList.size() - i - 1; j++) {
                    CricketDTO run1 = cricketDTOList.get(j);
                    CricketDTO run2 = cricketDTOList.get(j + 1);
                    if (cricketComparator.compare(run1, run2) > 0) {
                        cricketDTOList.set(j, run2);
                        cricketDTOList.set(j + 1, run1);
                    }
                }
            }
        }
    }