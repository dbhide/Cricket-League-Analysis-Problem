package com.cricketLeague;

import com.google.gson.Gson;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class CricketAnalyser {
    List<MostRunsCSV> IPLCSVList;
    Map<SortedField,Comparator<MostRunsCSV>> sortedMap;

    public CricketAnalyser() {
        this.IPLCSVList = new ArrayList<>();

        this.sortedMap = new HashMap<>();
        Comparator<MostRunsCSV> avgStrike = Comparator.comparing(res -> res.battingAverage);
        this.sortedMap.put(SortedField.AVGSTRIKE, avgStrike.thenComparing(res -> res.strikeRate));
        this.sortedMap.put(SortedField.AVERAGE,Comparator.comparing(IplFields -> IplFields.battingAverage));
        this.sortedMap.put(SortedField.STRIKERATE,Comparator.comparing(IplFields -> IplFields.strikeRate));
        this.sortedMap.put(SortedField.MAXIMUM_HIT, Comparator.comparing(IplFields -> IplFields.fours + IplFields.sixes));
        this.sortedMap.put(SortedField.BESTSTRIKE, new BatsmanComparator().thenComparing(res -> res.strikeRate));
    }

    public int getCricketDataFile(String csvFilePath) {
        try(Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));){
            CsvToBeanBuilder<MostRunsCSV> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
            csvToBeanBuilder.withType(MostRunsCSV.class);
            csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
            CsvToBean<MostRunsCSV> csvToBean = csvToBeanBuilder.build();
            Iterator<MostRunsCSV> IplCSVIterator = csvToBean.iterator();
            int totalEntries=0;
            while (IplCSVIterator.hasNext()){
                totalEntries++;
                MostRunsCSV runs = IplCSVIterator.next();
                IPLCSVList.add(runs);
            }
            return totalEntries;
        }catch (IOException e){
            throw  new CricketAnalyserException(e.getMessage(),CricketAnalyserException.ExceptionType.CRICKET_CSVFILE_PROBLEM);
        }
    }

    public String getSortedCricketData(SortedField sortedField) {
        if(IPLCSVList == null || IPLCSVList.size() == 0){
            throw new CricketAnalyserException("No Data",CricketAnalyserException.ExceptionType.CRICKET_DATA_NOT_FOUND);
        }
        this.sort(IPLCSVList,this.sortedMap.get(sortedField));
        Collections.reverse(IPLCSVList);
        String sortedStateCensus=new Gson().toJson(IPLCSVList);
        return sortedStateCensus;
    }

    private void sort(List<MostRunsCSV> IPLCSVList, Comparator<MostRunsCSV> IplComparator) {
        for(int i=0;i<IPLCSVList.size()-1;i++){
            for(int j=0;j<IPLCSVList.size()-i-1;j++){
                MostRunsCSV run1 = IPLCSVList.get(j);
                MostRunsCSV run2 = IPLCSVList.get(j+1);
                if(IplComparator.compare(run1,run2)>0){
                    IPLCSVList.set(j,run2);
                    IPLCSVList.set(j+1,run1);
                }
            }
        }
    }
}