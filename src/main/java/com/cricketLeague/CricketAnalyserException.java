package com.cricketLeague;

public class CricketAnalyserException extends RuntimeException{
    enum ExceptionType {
        CRICKET_CSVFILE_PROBLEM,CRICKET_DATA_NOT_FOUND,IPL_FILE_PROBLEM;
    }

    ExceptionType type;

    public CricketAnalyserException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }
}
