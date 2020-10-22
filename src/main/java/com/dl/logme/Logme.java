package com.dl.logme;

import com.dl.logme.appender.AbstractAppender;
import com.dl.logme.core.LogWriter;

import java.util.logging.Level;

/**
 * @param <APPENDER>
 *            the appender to be use to add data to the log entry
 *
 * @author Davide Lorenzoli
 */
public class Logme<APPENDER extends AbstractAppender<APPENDER>> {
    private final APPENDER appender;

    public Logme(APPENDER appender, LogWriter logWriter) {
        this.appender = appender;
        this.appender.onLogListener(logWriter::log);
    }

    public APPENDER info(String message) {
        appender.setMessage(Level.INFO, message);
        return appender;
    }

    public APPENDER warn(String message) {
        appender.setMessage(Level.WARNING, message);
        return appender;
    }

    public APPENDER error(String message, Throwable throwable) {
        appender.setMessage(Level.SEVERE, message, throwable);
        return appender;
    }
}
