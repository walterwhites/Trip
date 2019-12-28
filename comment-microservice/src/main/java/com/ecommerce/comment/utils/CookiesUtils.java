package com.ecommerce.comment.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

    public static void removeCookie(String cookieName, HttpServletResponse response) {
        Cookie cookie = new Cookie(cookieName, "");
        cookie.setMaxAge(0);
        cookie.setHttpOnly(true); // JS not able to read it
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}
