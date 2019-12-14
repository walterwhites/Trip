package com.ecommerce.clientui.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public final class CookiesUtils {

    public static String getCookie(HttpServletRequest req, String cookieName) {
        if (req.getCookies() == null) {
            return null;
        }
        for (Cookie c : req.getCookies()) {
            if (c.getName().equals(cookieName))
                return c.getValue();
        }
        return null;
    }
}
