package com.cricketLeague;

import abc.CSVBuilderFactory;
import abc.ICSVBuilder;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.StreamSupport;

public abstract class CricketAdapter extends RuntimeException{
    public abstract Map<String,CricketDTO> getCricketData(String...csvFilePath) throws IOException;

    public <E> Map<String,CricketDTO> getCricketData(Class<E> cricketCSVClass, String csvFilePath) throws IOException {
        Map<String,CricketDTO> cricketMap = new HashMap<>();
        try(Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));){
            ICSVBuilder icsvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<E> cricketCsvIterator = icsvBuilder.getCSVIterator(reader,cricketCSVClass);
            Iterable<E> cricketCsvIterable = ()->cricketCsvIterator;

            if(cricketCSVClass.getName().equals("com.cricketLeague.MostRunsCSV")){
                StreamSupport.stream(cricketCsvIterable.spliterator(),false)
                        .map(MostRunsCSV.class::cast)
                        .forEach(mostRunsCsv -> cricketMap.put(mostRunsCsv.player,new CricketDTO(mostRunsCsv)));
            }
            else if(cricketCSVClass.getName().equals("com.cricketLeague.MostWicketsCSV")){
                StreamSupport.stream(cricketCsvIterable.spliterator(),false)
                        .map(MostWicketsCSV.class::cast)
                        .forEach(mostWicketsCsv -> cricketMap.put(mostWicketsCsv.bowlerName,new CricketDTO(mostWicketsCsv)));
            }
            return cricketMap;
        }catch (IOException e){
            throw  new CricketAnalyserException(e.getMessage(),CricketAnalyserException.ExceptionType.IPL_FILE_PROBLEM);
        }
    }
}
