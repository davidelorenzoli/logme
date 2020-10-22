package com.dl.logme.core;

import com.dl.logme.Logme;
import com.dl.logme.appender.AbstractAppender;
import com.dl.logme.appender.DefaultAppender;
import com.dl.logme.log4j.Log4jPatternLayout;
import com.dl.logme.log4j.MDCPatternConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Davide Lorenzoli
 */
@SuppressWarnings("unchecked")
public class LogFactory {
    /**
     * Return a logger named corresponding to the class passed as parameter. The
     * {@link DefaultAppender} will be used.
     *
     * @param clazz
     *            the returned logger will be named after clazz
     * @return logger
     */
    public static Logme<DefaultAppender> getLogger(Class<?> clazz) {
        return LogFactory.getLogger(clazz, DefaultAppender.class);
    }

    /**
     * Return a logger named corresponding to the class passed as parameter
     *
     * @param clazz
     *            the returned logger will be named after clazz
     * @param appenderClass
     *            the specific log appender
     * @return logger
     */
    public static <APPENDER extends AbstractAppender<APPENDER>> Logme<APPENDER> getLogger(Class<?> clazz, Class<APPENDER> appenderClass) {
        Log4jPatternLayout.setMdcPatternConverter(new MDCPatternConverter());

        Logger logger = LoggerFactory.getLogger(clazz);
        APPENDER appender = getAppender(appenderClass);
        LogWriter logWriter = new LogWriter(logger);

        return new Logme<>(appender, logWriter);
    }

    /* ************************************************************************
     *                             Private Methods
     * ***********************************************************************/

    /**
     * @param appenderClass
     *            the appender class
     * @return the appender class instance, {@link DefaultAppender} if any error
     *         occurs
     */
    private static <APPENDER extends AbstractAppender<APPENDER>> APPENDER getAppender(Class<APPENDER> appenderClass) {
        APPENDER appender;

        try {
            appender = appenderClass.newInstance();
        } catch (Exception e) {
            appender = (APPENDER) new DefaultAppender();
        }

        return appender;
    }
}
