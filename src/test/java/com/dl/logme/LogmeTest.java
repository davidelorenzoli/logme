package com.dl.logme;

import com.dl.logme.appender.DefaultAppender;
import com.dl.logme.core.LogContext;
import com.dl.logme.core.LogFactory;
import org.junit.Test;

import java.net.ConnectException;

/**
 * @author Davide Lorenzoli
 */
public class LogmeTest {
    private static final String USERNAME = "davide";
    private static final String CONNECTION_ID = "123123";
    private static final String DEPARTMENT = "";
    private static final String URL = "www.appone.com";

    /**
     * Default method
     * No need to create appender
     */
    @Test
    public void defaultAppenderInfo() {
        Logme<DefaultAppender> logger = LogFactory.getLogger(LogmeTest.class);

        logger.info("Hello")
            .append("username", "davide")
            .append("requestCount", 123512)
            .append("permissions", new String[] { "WRITE", "READ", "EXECUTE" })
            .log();
    }

    /**
     * Flexible method.
     * User defines 1 AladdinParameterAppender.class
     */
    @Test
    public void appTwoAppenderInfo() {
        Logme<AppTwoAppender> logger = LogFactory.getLogger(LogmeTest.class, AppTwoAppender.class);

        LogContext.put("Context", "My value");

        logger.info("User succesfully connected")
            .append("connection", "connectionId")
            .log();
    }

    @Test
    public void appTwoAppenderWarnTest() {
        Logme<AppTwoAppender> logger = LogFactory.getLogger(LogmeTest.class, AppTwoAppender.class);

        logger.warn("User succesfully connected")
            .username(USERNAME)
            .append("connection", CONNECTION_ID)
            .log();
    }

    @Test
    public void appOneAppenderWarn() {
        Logme<AppOneAppender> logger = LogFactory.getLogger(LogmeTest.class, AppOneAppender.class);

        logger.warn("User does not have department set")
            .username(USERNAME)
            .append("department", DEPARTMENT)
            .log();
    }

    @Test
    public void appOneAppenderError() {
        Logme<AppOneAppender> logger = LogFactory.getLogger(LogmeTest.class, AppOneAppender.class);

        logger.error("Unable to connect to Symphony agent", new ConnectException("Host seems to be down"))
            .username(USERNAME)
            .url(URL)
            .log();
    }
}
