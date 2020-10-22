package com.dl.logme.core;

import com.dl.logme.appender.LogParameter;
import org.slf4j.Logger;
import org.slf4j.MDC;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.LogRecord;

/**
 * @author Davide Lorenzoli
 */
public class LogWriter {
    private Map<String, String> loggingContext;
    private final Logger logger;

    public LogWriter(Logger logger) {
        this.logger = logger;
    }

    public void log(LogRecord logRecord) {
        saveLoggingContext();

        Level level = logRecord.getLevel();

        String message = logRecord.getMessage();
        populateMappedDiagnosticContext(logRecord);

        if (level == Level.INFO) {
            logger.info(message);
        } else if (level == Level.WARNING) {
            logger.warn(message);
        } else if (level == Level.SEVERE) {
            logger.error(message, logRecord.getThrown());
        }

        restoreLoggingContext();
    }

    private void saveLoggingContext() {
        loggingContext = LogContext.getCopyOfContextMap() == null ? new HashMap<>() : LogContext.getCopyOfContextMap();
    }

    private void restoreLoggingContext() {
        LogContext.setContextMap(loggingContext);
    }

    /* ************************************************************************
     *                          Private Methods
     * ***********************************************************************/

    /**
     * Populates the Mapped Diagnostic Context (MDC) which is then printed
     * according to the log output pattern
     *
     * @param logRecord
     *            the log record
     */
    private void populateMappedDiagnosticContext(LogRecord logRecord) {
        Arrays.stream(logRecord.getParameters())
            .filter(parameter -> parameter instanceof LogParameter)
            .map(LogParameter.class::cast)
            .forEach(logParameter -> MDC.put(logParameter.getName(), logParameter.getValue().toString()));
    }
}
