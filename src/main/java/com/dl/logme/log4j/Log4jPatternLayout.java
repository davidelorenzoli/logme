package com.dl.logme.log4j;

import com.dl.logme.jvm.StackTrace;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.helpers.PatternConverter;
import org.apache.log4j.helpers.PatternParser;
import org.apache.log4j.spi.LocationInfo;
import org.apache.log4j.spi.LoggingEvent;
import org.slf4j.LoggerFactory;

/**
 * A flexible layout configurable with pattern string.
 * <p>
 * The goal of this class is to format a LogEvent and return the results. The format of the result depends on the
 * conversion pattern.
 * <p>
 * The conversion pattern is closely related to the conversion pattern of the printf function in C. A conversion pattern
 * is composed of literal text and format control expressions called conversion specifiers.
 * <p>
 * See the Log4j Manual for details on the supported pattern converters.
 */
public class Log4jPatternLayout extends PatternLayout {
    private static PatternConverter mdcPatternConverter;

    /**
     * Set the Mapped Diagnostic Context (MDC) patter converter. This have to be
     * invoked before {@link LoggerFactory#getLogger(Class)}
     *
     * @param mdcPatternConverter
     *         the MDC pattern converter
     */
    public static void setMdcPatternConverter(PatternConverter mdcPatternConverter) {
        Log4jPatternLayout.mdcPatternConverter = mdcPatternConverter;
    }

    /**
     * Creates a PatternParser
     */
    @Override
    protected PatternParser createPatternParser(String pattern) {
        return new Log4jPatterParser(pattern, mdcPatternConverter);
    }

    @Override
    public String format(LoggingEvent loggingEvent) {
        StackTraceElement caller = StackTrace.findFirst(loggingEvent.getLoggerName());

        LocationInfo locationInfo;

        if (caller != null) {
            locationInfo = new LocationInfo(
                    caller.getFileName(),
                    caller.getClassName(),
                    caller.getMethodName(),
                    Integer.toString(caller.getLineNumber()));
        } else {
            loggingEvent.getLogger().warn("Logger name not found in stack trace, using default location info");
            locationInfo = loggingEvent.getLocationInformation();
        }

        LoggingEvent updatedLoggingEvent = new LoggingEvent(
                loggingEvent.getLoggerName(),
                loggingEvent.getLogger(),
                loggingEvent.getTimeStamp(),
                loggingEvent.getLevel(),
                loggingEvent.getMessage(),
                loggingEvent.getThreadName(),
                loggingEvent.getThrowableInformation(),
                loggingEvent.getNDC(),
                locationInfo,
                loggingEvent.getProperties());

        return super.format(updatedLoggingEvent);
    }
}
