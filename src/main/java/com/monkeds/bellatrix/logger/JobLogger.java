package com.monkeds.bellatrix.logger;

import com.monkeds.bellatrix.utils.DatabaseHandler;
import java.io.IOException;
import java.util.Map;
import java.util.logging.*;

/**
 * Created by monked on 10/6/19.
 */
public class JobLogger {


    private Logger logger;



    public JobLogger(boolean enableConsoleLog, boolean enableFileLog, boolean enableDBLog, Map<String,String> params)
            throws Exception {

        if (!enableConsoleLog && !enableFileLog && !enableDBLog) {
            throw new Exception("Invalid configuration.");
        }

        if (params.get("logName")==null) {
            throw new Exception("Log Name required.");
        }

        logger = initializeLogger(params);

        if(enableConsoleLog){
            this.startConsoleLog(logger);
        }

        if(enableFileLog){
            if (params.get("logFileFolder")==null || params.get("logFileName")==null) {
                throw new Exception("Invalid configuration for File Log.");
            }
            this.startFileLog(logger, params);
        }

        if(enableDBLog){
            if (params.get("userName")==null || params.get("password")==null
                    || params.get("dbms")==null || params.get("serverName")==null
                    || params.get("portNumber")==null || params.get("database")==null) {
                throw new Exception("Invalid configuration for DataBase Log.");
            }
            this.startDataBaseLog(logger, params);
        }
    }


    private Logger initializeLogger(Map params) throws Exception{
        logger = Logger.getLogger(params.get("logName").toString());
        for(Handler handler : logger.getHandlers()) {
            logger.removeHandler(handler);
        }
        logger.setUseParentHandlers(true);
        return logger;
    }

    private void startConsoleLog(Logger logger){
        logger.addHandler(new ConsoleHandler());
    }

    private void startFileLog(Logger logger, Map params) throws IOException {
        FileHandler fh = new FileHandler(params.get("logFileFolder") + "/"+params.get("logFileName"));
        logger.addHandler(fh);
    }

    private void startDataBaseLog(Logger logger, Map params){
        String jdbcConnection = "jdbc:"
                + params.get("dbms") + "://"
                + params.get("serverName") + ":"
                + params.get("portNumber") + "/"
                + params.get("database") + "?user="
                + params.get("userName") + "&password="
                + params.get("password")
                + "&serverTimezone=UTC";
        DatabaseHandler dh = new DatabaseHandler(jdbcConnection);
        logger.addHandler(dh);
    }




    public void logMessage(String message) throws Exception{
        if(isEmpty(message)){
            //throw new Exception("The message cannot be empty");
            return;
        }
        System.out.println("en info: "+logger.getHandlers().length);
        logger.log(Level.INFO, message);
    }

    public void logWarning(String message) throws Exception{
        if(isEmpty(message)){
           // throw new Exception("The message cannot be empty");
            return;
        }
        System.out.println("en warning: "+logger.getHandlers().length);
        logger.log(Level.WARNING, message);
    }

    public void logError(String message) throws Exception{
        if(isEmpty(message)){
            //throw new Exception("The message cannot be empty");
            return;
        }
        System.out.println("en error: "+logger.getHandlers().length);
        logger.log(Level.SEVERE, message);
    }







    //utilitario
    private boolean isEmpty( final String text ) {
        return text == null || text.isEmpty();
    }


}
