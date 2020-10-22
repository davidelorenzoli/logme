package com.dl.logme.appender;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.LogRecord;

/**
 * This appender provides the base methods extending appender have to leverage
 *
 * @param <APPENDER>
 *            the appender type, e.g. {@link DefaultAppender}
 *
 * @author Davide Lorenzoli
 */
@SuppressWarnings("unchecked")
public abstract class AbstractAppender<APPENDER> {
    private OnLogAction onLogAction;
    private LogRecord logRecord;
    private final List<LogParameter> logParameters;

    public AbstractAppender() {
        this.logParameters = new ArrayList<>();
    }

    public APPENDER setMessage(Level level, String message) {
        logParameters.clear();
        logRecord = new LogRecord(level, message);
        return (APPENDER) this;
    }

    public APPENDER setMessage(Level level, String message, Throwable throwable) {
        logParameters.clear();
        logRecord = new LogRecord(level, message);
        logRecord.setThrown(throwable);
        return (APPENDER) this;
    }

    public APPENDER append(String name, Object value) {
        logParameters.add(new LogParameter(name, value));
        return (APPENDER) this;
    }

    public void log() {
        if (onLogAction != null) {
            logRecord.setParameters(logParameters.toArray());
            onLogAction.onLog(logRecord);
        }
    }

    /**
     * @param onLogAction
     *            the listened invoked when {@link AbstractAppender#log()} is called
     */
    public void onLogListener(OnLogAction onLogAction) {
        this.onLogAction = onLogAction;
    }
}
