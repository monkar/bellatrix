package com.monkeds.bellatrix.logger;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by monked on 10/3/19.
 */
public class MyJobLoggerTest {

    Map logParams;


    @Before
    public void setUp() throws Exception {
        logParams = new HashMap();
        logParams.put("logName","MyLogt");

        logParams.put("userName","root");
        logParams.put("password","P@ssw0rd");
        logParams.put("dbms","mysql");
        logParams.put("serverName","localhost");
        logParams.put("portNumber","3306");
        logParams.put("database","bellatrix");

        logParams.put("logFileFolder","/Users/monked/monkar/bellatrix/logproject/bellatrix/logs");
        logParams.put("logFileName","myLogm.txt");


    }

    @After
    public void tearDown() throws Exception {
        logParams = null;
    }


    @Test(expected = Exception.class)
    public void invalidConfiguredException() throws Exception{
        MyJobLogger jobLogger = new MyJobLogger(false,false,false, logParams);

    }

    @Test(expected = Exception.class)
    public void invalidConfiguredFileException() throws Exception{
        logParams.remove("logFileFolder");
        MyJobLogger jobLogger = new MyJobLogger(false,true,false, logParams);

    }

    @Test(expected = Exception.class)
    public void invalidConfiguredDataBaseException() throws Exception{
        logParams.remove("database");
        MyJobLogger jobLogger = new MyJobLogger(false,true,false, logParams);

    }

    //@Test
    public void emptyMessageTest() throws Exception {

    }

    //@Test
    public void logMessageTest() throws Exception {
        MyJobLogger jobLogger = new MyJobLogger(true,true,true, logParams);

    }

    //@Test
    public void logWarningTest() throws Exception {
        MyJobLogger jobLogger = new MyJobLogger(true,true,true, logParams);
        jobLogger.logWarning("Hola amigos3");
    }

    //@Test
    public void logErrorTest() throws Exception {
        MyJobLogger jobLogger = new MyJobLogger(true,true,true, logParams);
        jobLogger.logError("Hola amigos");
    }
}
