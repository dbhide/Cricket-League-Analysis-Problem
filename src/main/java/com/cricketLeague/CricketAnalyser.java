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

    public CricketAnalyser() {
        this.IPLCSVList = new ArrayList<>();
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

    public String getSortedCricketData() {

        if(IPLCSVList == null || IPLCSVList.size() == 0){
            throw new CricketAnalyserException("No Data",CricketAnalyserException.ExceptionType.CRICKET_DATA_NOT_FOUND);
        }
        Comparator<MostRunsCSV> IplComparator = Comparator.comparing(IPL -> IPL.battingAverage);
        this.sort(IPLCSVList,IplComparator);
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