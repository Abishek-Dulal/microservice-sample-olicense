package com.optimagrowth.licenseserver.service;

import org.apache.catalina.User;

/**
 * @author abishek on 2022-04-03
 */
public class UserContextHolder {
    private static final ThreadLocal<UserContext> userContext = new ThreadLocal<UserContext>();


    public static final void setContext(UserContext context) {
        userContext.set(context);
    }
    public static final UserContext createEmptyContext(){
        return new UserContext();
    }

    public static final UserContext getContext(){
        UserContext context = userContext.get();
        if (context == null) {
            context = createEmptyContext();
            userContext.set(context);
        }
        return userContext.get();
    }


}
