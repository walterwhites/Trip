package com.ecommerce.loginmicroservice.utils;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

public final class DebugUtils {

    private static String PURPLE = "\033[0;35m";  // PURPLE
    private static String RED = "\u001B[31m";  // RED
    private static String RESET = "\u001B[0m";

    public static class ZipkinDebug {

        private static String url = "http://localhost:9411/zipkin/traces/";

        public static void displayTraceUrl(HttpServletRequest request) {
            String traceId = request.getHeader("x-b3-traceid");
            System.out.println(PURPLE + "DebugUtils:ZipkinDebug -> " + url + traceId + RESET);
        }
    }

    public static class RequestInfo {

        public static void displayAllRequestHeaders(HttpServletRequest request) {
            Enumeration<String> headerNames = request.getHeaderNames();
            System.out.println(RED + "DebugUtils:RequestInfo -> " + RESET);
            headerNames.asIterator().forEachRemaining(header -> {
                System.out.println("Header Name:" + header + "   " + "Header Value:" + request.getHeader(header));
            });
        }

        public static String getRequestHeader(HttpServletRequest request, String headerName) {
            return request.getHeader(headerName);
        }

        public static void displayRequestHeader(HttpServletRequest request, String headerName) {
            String headerValue = getRequestHeader(request, headerName);
            System.out.println(RED + "DebugUtils:RequestInfo -> Header Name:" + headerName + "   " + "Header Value:" + headerValue + RESET);
        }
    }
}