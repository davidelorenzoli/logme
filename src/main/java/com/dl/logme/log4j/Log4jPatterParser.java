package com.dl.logme.log4j;

import org.apache.log4j.helpers.PatternConverter;
import org.apache.log4j.helpers.PatternParser;

/**
 * Most of the work of the {@link org.apache.log4j.PatternLayout} class is delegated to the {@link PatternParser} class.
 * <p>
 * It is this class that parses conversion patterns and creates a chained list of PatternConverters.
 */
public class Log4jPatterParser extends PatternParser {
    private static final String MDC_PATTERN_CONVERTER_CLASS_NAME = "org.apache.log4j.helpers.PatternParser$MDCPatternConverter";

    private final PatternConverter mdcPatternConverter;

    public Log4jPatterParser(String pattern, PatternConverter mdcPatternConverter) {
        super(pattern);

        this.mdcPatternConverter = mdcPatternConverter;
    }

    /**
     * Conversion specifiers in a conversion patterns are parsed to individual PatternConverters. Each of which is responsible
     * for converting an object in a converter specific manner.
     */
    @Override
    protected void addConverter(PatternConverter patternConverter) {
        boolean useCustomConverter = patternConverter != null &&
                                     patternConverter.getClass().getName().equals(MDC_PATTERN_CONVERTER_CLASS_NAME) &&
                                     mdcPatternConverter != null;

        if (useCustomConverter) {
            super.addConverter(mdcPatternConverter);
        } else {
            super.addConverter(patternConverter);
        }
    }
}