package com.monkeds.bellatrix.utils;

import java.sql.*;
import java.util.Date;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

/**
 * Created by monked on 10/3/19.
 */
public class DatabaseHandler extends Handler{

    private Connection connection;
    private String jdbcConection;

    public DatabaseHandler(String jdbcConection) {
        this.jdbcConection = jdbcConection;
    }

    public void publish(LogRecord record) {
        try {

            PreparedStatement preparedStatement;
            String SQL = "INSERT INTO mylog " +
                    " (type, logger, class, method, message, date) " +
                    " values(?,?,?,?,?,?)";

            connection = DriverManager.getConnection(jdbcConection);
            preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setString(1, record.getLevel().getName());
            preparedStatement.setString(2, record.getLoggerName());
            preparedStatement.setString(3, record.getSourceClassName());
            preparedStatement.setString(4, record.getSourceMethodName());
            preparedStatement.setString(5, record.getMessage());
            preparedStatement.setTimestamp(6, new Timestamp((new Date()).getTime()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void flush() {
        //TODO: implementar
    }

    public void close() throws SecurityException {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                //TODO: manejo de error
            }
        }
    }
}
