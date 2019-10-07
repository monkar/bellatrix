package com.monkeds.bellatrix.base;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by monked on 10/3/19.
 */
public class JobLoggerTest {


    JobLogger jobLogger;
    Map dbParamsMap;


    @Before
    public void setUp() throws Exception {
        dbParamsMap = new HashMap();
        dbParamsMap.put("userName","root");
        dbParamsMap.put("password","P@ssw0rd");
        dbParamsMap.put("dbms","mysql");
        dbParamsMap.put("serverName","localhost");
        dbParamsMap.put("portNumber","3306");
        dbParamsMap.put("database","bellatrix");
        dbParamsMap.put("logFileFolder","/Users/monked/monkar/bellatrix/logproject/bellatrix/logs");

        jobLogger = new JobLogger(true,true,true,true,
                true, true, dbParamsMap);
    }

    @Test
    public void LogMessage() throws Exception {
        jobLogger.LogMessage("algo",false, false,true);
    }
}