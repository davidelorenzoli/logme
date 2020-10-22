package com.dl.logme.appender;
import java.util.logging.LogRecord;

/**
 * @author Davide Lorenzoli
 */
public interface OnLogAction {
    void onLog(LogRecord logRecord);
}
