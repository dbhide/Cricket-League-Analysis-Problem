package com.cricketLeague;

import abc.CSVBuilderFactory;
import abc.ICSVBuilder;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class CricketAnalyser {

    List <CricketDTO> cricketDTOList;
    Map<SortedField,Comparator<CricketDTO>> sortedMap;
    Map<String,CricketDTO> cricketDTOMap;


    public CricketAnalyser() {
        this.cricketDTOList = new ArrayList<>();
        this.sortedMap = new HashMap<>();
        this.cricketDTOMap = new HashMap<>();

        Comparator<CricketDTO> avgStrike = Comparator.comparing(res -> res.average);
        this.sortedMap.put(SortedField.AVGSTRIKE, avgStrike.thenComparing(res -> res.strikeRate));
        this.sortedMap.put(SortedField.AVERAGE,Comparator.comparing(IplFields -> IplFields.average));
        this.sortedMap.put(SortedField.STRIKERATE,Comparator.comparing(IplFields -> IplFields.strikeRate));
        this.sortedMap.put(SortedField.MAXIMUM_HIT, Comparator.comparing(IplFields -> IplFields.fours + IplFields.sixes));
        this.sortedMap.put(SortedField.BESTSTRIKE, new BatsmanComparator().thenComparing(res -> res.strikeRate));
        Comparator<CricketDTO> maxRuns = Comparator.comparing(res -> res.runs);
        this.sortedMap.put(SortedField.MAXRUNS, maxRuns.thenComparing(res -> res.average));
    }

    public int getBattingDataFile(String csvFilePath) {
        try(Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));){
            ICSVBuilder icsvBuilder= CSVBuilderFactory.createCSVBuilder();
            Iterator<MostRunsCSV> mostRunsCSVIterator = icsvBuilder.getCSVIterator(reader,MostRunsCSV.class);
            while (mostRunsCSVIterator.hasNext()){
                MostRunsCSV mostRunCsv = mostRunsCSVIterator.next();
                this.cricketDTOMap.put(mostRunCsv.player,new CricketDTO(mostRunCsv));
            }
            cricketDTOList = cricketDTOMap.values().stream().collect(Collectors.toList());
            return cricketDTOList.size();
        }catch (IOException e){
            throw  new CricketAnalyserException(e.getMessage(),CricketAnalyserException.ExceptionType.CRICKET_CSVFILE_PROBLEM);
        }
    }

    public int getBowlingDataFile(String csvFilePath) {
        try(Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));){
            ICSVBuilder icsvBuilder= CSVBuilderFactory.createCSVBuilder();
            Iterator<MostWicketsCSV> mostWicketsCSVIterator = icsvBuilder.getCSVIterator(reader, MostWicketsCSV.class);
            while (mostWicketsCSVIterator.hasNext()){
                MostWicketsCSV mostWicketsCSV = mostWicketsCSVIterator.next();
                this.cricketDTOMap.put(mostWicketsCSV.bowlerName,new CricketDTO(mostWicketsCSV));
            }
            cricketDTOList = cricketDTOMap.values().stream().collect(Collectors.toList());
            return cricketDTOList.size();
        }catch (IOException e){
            throw  new CricketAnalyserException(e.getMessage(),CricketAnalyserException.ExceptionType.CRICKET_CSVFILE_PROBLEM);
        }
    }

    public String getSortedCricketData(SortedField sortedField) {
        if(cricketDTOList == null || cricketDTOList.size() == 0){
            throw new CricketAnalyserException("No Data",CricketAnalyserException.ExceptionType.CRICKET_DATA_NOT_FOUND);
        }
        this.sort(this.sortedMap.get(sortedField));
        Collections.reverse(cricketDTOList);
        String sortedStateCensus=new Gson().toJson(cricketDTOList);
        return sortedStateCensus;
    }

    private void sort(Comparator<CricketDTO> cricketComparator) {
        for(int i=0;i<cricketDTOList.size()-1;i++){
            for(int j=0;j<cricketDTOList.size()-i-1;j++){
                CricketDTO run1 = cricketDTOList.get(j);
                CricketDTO run2 = cricketDTOList.get(j+1);
                if(cricketComparator.compare(run1,run2)>0){
                    cricketDTOList.set(j,run2);
                    cricketDTOList.set(j+1,run1);
                }
            }
        }
    }
}