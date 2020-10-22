package com.dl.logme.appender;

public class AppenderFactory {
    /**
     * @param appenderClass
     *            the appender class
     * @return the appender class instance, {@link DefaultAppender} if any error occurs
     */
    public static <APPENDER extends AbstractAppender<APPENDER>> APPENDER getAppender(Class<APPENDER> appenderClass) {
        APPENDER appender;

        try {
            appender = appenderClass.newInstance();
        } catch (Exception e) {
            appender = (APPENDER) new DefaultAppender();
        }

        return appender;
    }
}
