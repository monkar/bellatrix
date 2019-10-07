package com.monkeds.bellatrix.logger;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.File;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by monked on 10/6/19.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JobLoggerTest {

    Map logParams;


    @Before
    public void setUp() throws Exception {
        logParams = new HashMap();
        logParams.put("logName","MyLogSample");

        logParams.put("userName","root");
        logParams.put("password","P@ssw0rd");
        logParams.put("dbms","mysql");
        logParams.put("serverName","localhost");
        logParams.put("portNumber","3306");
        logParams.put("database","bellatrix");

        //logParams.put("logFileFolder","/Users/monked/monkar/bellatrix/logproject/bellatrix/logs");
        logParams.put("logFileFolder","D:\\emedina\\logs");
        logParams.put("logFileName","MyLogSample.txt");


    }

    @After
    public void tearDown() throws Exception {
        logParams = null;
    }


    @Test(expected = Exception.class)
    public void test1_invalidConfiguredException() throws Exception{
        JobLogger jobLogger = new JobLogger(false,false,false, logParams);

    }

    @Test(expected = Exception.class)
    public void test2_invalidConfiguredFileException() throws Exception{
        logParams.remove("logFileFolder");
        JobLogger jobLogger = new JobLogger(false,true,false, logParams);
    }

    @Test(expected = Exception.class)
    public void test3_invalidConfiguredDataBaseException() throws Exception{
        logParams.remove("database");
        JobLogger jobLogger = new JobLogger(false,false,true, logParams);

    }


    @Test
    public void test4_consoleLog() throws Exception {
        JobLogger jobLogger = new JobLogger(true,false,false, logParams);
        jobLogger.logMessage("This is a info message");
        jobLogger.logWarning("This is a warning message");
        jobLogger.logError("This is a error message");
    }

    @Test
    public void test5_fileLog() throws Exception {
        JobLogger jobLogger = new JobLogger(false,true,false, logParams);
        jobLogger.logMessage("This is a info message");
        jobLogger.logWarning("This is a warning message");
        jobLogger.logError("This is a error message");

        File file = new File(logParams.get("logFileFolder") + "/"+logParams.get("logFileName"));
        assertTrue(file.exists());
    }

    @Test
    public void test6_databaseLog() throws Exception {
        JobLogger jobLogger = new JobLogger(false,false,true, logParams);
        jobLogger.logMessage("This is a info message");
        jobLogger.logWarning("This is a warning message");
        jobLogger.logError("This is a error message");
        assertEquals(countRecords(),3);
    }



    private int countRecords(){
        int numberOfRows =0;
        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            String jdbcConnection = "jdbc:"
                    + logParams.get("dbms") + "://"
                    + logParams.get("serverName") + ":"
                    + logParams.get("portNumber") + "/"
                    + logParams.get("database") + "?user="
                    + logParams.get("userName") + "&password="
                    + logParams.get("password")
                    + "&serverTimezone=UTC";

            conn = DriverManager.getConnection(jdbcConnection);
            String query = "SELECT count(1) FROM mylog";
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                numberOfRows = rs.getInt(1);

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                pstmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return numberOfRows;
    }
}
