package com.dl.logme.jvm;

/**
 * @author Davide Lorenzoli
 */
public class StackTrace {
    /**
     * @param className
     *            the class to find in the stack trace
     * @return the first occurrence of the given class in the stack trace
     */
    public static StackTraceElement findFirst(String className) {
        StackTraceElement caller = null;

        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();

        for (StackTraceElement stackTraceElement : stackTrace) {
            boolean isCaller = className.equals(stackTraceElement.getClassName());

            if (isCaller) {
                caller = stackTraceElement;
                break;
            }
        }

        return caller;
    }
}
