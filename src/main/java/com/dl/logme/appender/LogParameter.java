package com.dl.logme.appender;

/**
 * @author Davide Lorenzoli
 */
public class LogParameter {
    private final String name;
    private final Object value;

    public LogParameter(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }

    @Override
    public String toString() {
        return name + "=" + value;
    }
}
