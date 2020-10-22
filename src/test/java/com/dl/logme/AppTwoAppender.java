package com.dl.logme;

import com.dl.logme.appender.AbstractAppender;

/**
 * This appender has properties typical of App Two environment.
 *
 * @author Davide Lorenzoli
 */
public class AppTwoAppender extends AbstractAppender<AppTwoAppender> {
    public AppTwoAppender username(String username) {
        super.append("username", username);

        return this;
    }
}
