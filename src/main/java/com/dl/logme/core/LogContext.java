package com.dl.logme.core;

import org.slf4j.MDC;

import java.util.Map;

/**
 * @author Davide Lorenzoli
 */
public class LogContext {
    /**
     * Put a diagnostic context value (the <code>val</code> parameter) as
     * identified with the <code>key</code> parameter into the current thread's
     * diagnostic context map. The <code>key</code> parameter cannot be null.
     * The <code>val</code> parameter can be null only if the underlying
     * implementation supports it.
     * <p>
     * This method delegates all work to the MDC of the underlying logging
     * system.
     *
     * @param key
     *            non-null key
     * @param value
     *            value to put in the map
     *
     * @throws IllegalArgumentException
     *             in case the "key" parameter is null
     */
    public static void put(String key, String value) {
        MDC.put(key, value);
    }

    /**
     * Return a copy of the current thread's context map, with keys and values
     * of type String. Returned value may be null.
     *
     * @return A copy of the current thread's context map. May be null.
     */
    public static Map<String, String> getCopyOfContextMap() {
        return MDC.getCopyOfContextMap();
    }

    /**
     * Set the current thread's context map by first clearing any existing map
     * and then copying the map passed as parameter. The context map passed as
     * parameter must only contain keys and values of type String.
     *
     * @param contextMap
     *            must contain only keys and values of type String
     */
    public static void setContextMap(Map<String, String> contextMap) {
        MDC.setContextMap(contextMap);
    }
}
