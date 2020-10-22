package com.dl.logme;

import com.dl.logme.appender.AbstractAppender;

/**
 * This appender has properties typical of the Symphony environment.
 *
 * @author Davide Lorenzoli
 */
public class AppOneAppender extends AbstractAppender<AppOneAppender> {
    /**
     * @param username
     *            the user username
     */
    public AppOneAppender username(String username) {
        super.append("username", username);

        return this;
    }

    /**
     * @param url
     *            the target URL
     */
    public AppOneAppender url(String url) {
        super.append("url", url);

        return this;
    }
}
