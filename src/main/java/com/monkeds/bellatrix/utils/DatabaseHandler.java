package com.monkeds.bellatrix.utils;

import java.sql.*;
import java.util.Date;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

/**
 * Created by monked on 10/6/19.
 */
public class DatabaseHandler extends Handler{

    private Connection connection;
    private String jdbcConnection;

    public DatabaseHandler(String jdbcConnection) {
        this.jdbcConnection = jdbcConnection;
    }

    public void publish(LogRecord record) {
        try {

            PreparedStatement ps;
            String SQL = "INSERT INTO mylog " +
                    " (type, logger, class, method, message, date) " +
                    " values(?,?,?,?,?,?)";

            connection = DriverManager.getConnection(jdbcConnection);
            ps = connection.prepareStatement(SQL);

            ps.setString(1, record.getLevel().getName());
            ps.setString(2, record.getLoggerName());
            ps.setString(3, record.getSourceClassName());
            ps.setString(4, record.getSourceMethodName());
            ps.setString(5, record.getMessage());
            ps.setTimestamp(6, new Timestamp((new Date()).getTime()));
            ps.executeUpdate();
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
