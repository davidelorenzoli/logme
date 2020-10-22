package com.dl.logme.log4j;

import org.apache.log4j.helpers.PatternConverter;
import org.apache.log4j.spi.LoggingEvent;

/**
 * Mapped Diagnostic Context pattern converter
 */
public class MDCPatternConverter extends PatternConverter {
    /**
     * Derived pattern converters must override this method in order to convert conversion specifiers in the correct way.
     *
     * @return the default implementation: <code>event.getProperties().toString()</code>
     */
    @Override
    protected String convert(LoggingEvent event) {
        return event.getProperties().toString();
    }
}
