![logme-main](https://github.com/davidelorenzoli/logme/workflows/Java%20CI%20with%20Maven/badge.svg?branch=main)

# Logme

Logme is a logging framework, build on top of SLF4J, whose aim is to make logging easier and more structured.

## Advantages
The main advantages are:

* Separation from human readable message and its data context
* User-defined data context formatting, e.g. JSON, XML
* Log entries easier to write (and maintain) without messy string concatenations
* User-defined data appenders
* It leverages existing BlackRock adopted logging framework SLF4J and Log4j

## Basic Example
This is Logme simplest usage. The default data appender is used, i.e. DefaultAppender.

```java
Logme<DefaultAppender> logger = Factory.getLogger(LogmeTest.class);

logger.info("User successfully logged-in")
    .append("username", userId)
    .append("host", hostAddress)
    .log();
```

This will output a log entry, which in this case depends on log4j.xml settings:

```java
[2020-10-22 19:57:13,610] WARN - com.dl.funlog.FunLogTest.appTwoAppenderWarnTest
(FunLogTest.java:56) - User succesfully connected -> {username=davide, host=127.0.0.1}
```

## Appender Example
An appender provide to the logger a set of user-defined append methods. These are useful to simplify domain-specific logging.
For instance, when logging AppOne related applications, it's useful to create a custom
 appender
, e.g. SymphonyParameterAppender.

```java
public class AppOneAppender extends AbstractAppender<AppOneAppender> {
    public AppOneAppender username(String username) {
        super.append("username", username);

        return this;
    }

    public AppOneAppender url(String url) {
        super.append("url", url);

        return this;
    }
}
```

By doing so, methods `username` and `url` become available for everyone to use. This has the advantage that a log parameter key
(username, url, etc.) is hidden, and it is consistent for everyone.

```java
Logme<AppOneAppender> logger = LoggerFactory.getLogger(AladdinLoggerTest.class
, AppOneAppender.class);

logger.warn("Unable to connect to app")
    .username(username) // custom appender defined in SymphonyParameterAppender
    .url(url)
    .log();

logger.warn("Unable to connect to Symphony agent")
    .username(username)
    .username(ur)
    .append("host", hostAddress) // the generic appender is still available
    .log();
```

This will output a log entry, which in this case depends on log4j.xml settings:

```java
[2020-10-22 19:57:13,592] WARN - com.dl.funlog.FunLogTest.appTwoAppenderInfo(FunLogTest
.java:46) - Unable to connect to app -> {username=davide, url=http://127.0.0.1/appOne}

[2020-10-22 19:57:13,592] WARN - com.dl.funlog.FunLogTest.appTwoAppenderInfo(FunLogTest
.java:46) - Unable to connect to app -> {username=davide, url=http://127.0.0.1/appOne
, host=localhost}
```

## Mapped Diagnostic Context (MDC) Example
Logme supports 'out of the box'. The LogContext class contains only static methods. It lets the developer place information in a
diagnostic context that can be subsequently retrieved. The MDC manages contextual information on a per thread basis.

```java
Logme<DefaultAppender> logger = LoggerFactory.getLogger(LogmeTest.class);

LoggerContext.put("username", username);

logger.info("User successfully logged-in")
    .append("department", departmentId)
    .append("host", hostAddress)
    .log();

logger.info("User successfully logged-in")
    .append("Location", locationId)
    .append("host", hostAddress)
    .log();
```

This will output a log entry, which in this case depends on log4j.xml settings. Note that the information in the MDC (the user name) 
is always printed for each log entry:

```java
[2020-10-22 19:57:13,612] INFO - com.dl.funlog.FunLogTest.defaultAppenderInfo(FunLogTest.java:31) - User
 successfully logged-in -> {userName=davide, department=CSI, host=178.45.59.123}

[2020-10-22 19:57:13,612] INFO - com.dl.funlog.FunLogTest.defaultAppenderInfo(FunLogTest.java:31)- User
 successfully logged-in -> {userName=davide, location=London, host=178.45.59.123}
```
