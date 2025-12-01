package com.personal.newsStream.helper;

import java.util.Map;

/**
 * prakhar: 29/11/25
 *
 * @since 16
 */
public class Helper {

    public static boolean isProducerMessageValid(Object messageMap) {
        if ((messageMap instanceof Map) && ((Map) messageMap).containsKey("message") && !((String)((Map) messageMap).get("message")).isEmpty()) {
            return true;
        }
        return false;
    }

}
