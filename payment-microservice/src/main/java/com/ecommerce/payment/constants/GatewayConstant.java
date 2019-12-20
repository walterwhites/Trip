package com.ecommerce.payment.constants;

public class GatewayConstant {
    public static final String HOST = "localhost";
    public static final String PORT = "9096";

    public static String getGatewayURL() {
        return HOST + ":" + PORT;
    }
}
