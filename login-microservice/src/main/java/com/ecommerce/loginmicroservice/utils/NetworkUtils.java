package com.ecommerce.loginmicroservice.utils;

import com.ecommerce.loginmicroservice.constants.PatternConstants;
import com.ecommerce.loginmicroservice.responseDTO.NetworkResponseDTO;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.Objects;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NetworkUtils {

    public static Function<String, String> getClientMACAddress = (ip) -> {
        Pattern macpt = null;

        String OS = System.getProperty("os.name").toLowerCase();
        String[] cmd;
        if (OS.contains("win")) {
            macpt = Pattern.compile(PatternConstants.NetworkConstants.PATTERN_REGEX_FOR_WINDOWS);
            String[] a = {PatternConstants.NetworkConstants.ARP, PatternConstants.NetworkConstants.ARP_A, ip};
            cmd = a;
        } else {
            macpt = Pattern.compile(PatternConstants.NetworkConstants.PATTERN_REGEX_FOR_MAC_OR_LINUX);
            String[] a = {PatternConstants.NetworkConstants.ARP, ip};
            cmd = a;
        }
        BufferedReader reader = null;

        try {
            Process p = Runtime.getRuntime().exec(cmd);
            p.waitFor();

            System.out.println(p.getInputStream());
            reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = reader.readLine();
            while (line != null) {
                Matcher m = macpt.matcher(line);
                if (m.find())
                    return m.group();
                line = reader.readLine();
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "";
    };

    public static Function<NetworkResponseDTO, NetworkResponseDTO> getLocalHostAddress = (network) -> {

        StringBuilder sb = new StringBuilder();
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface iface = interfaces.nextElement();
                if (iface.isLoopback() || !iface.isUp() || iface.isVirtual() || iface.isPointToPoint())
                    continue;
                Enumeration<InetAddress> addresses = iface.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress addr = addresses.nextElement();
                    if (Inet4Address.class == addr.getClass())
                        network.setIpAddress(addr.getHostAddress());
                    if (Objects.isNull(network.getMacAddress())) {
                        NetworkInterface networkInterface = NetworkInterface.getByInetAddress(addr);
                        byte[] mac = networkInterface.getHardwareAddress();
                        for (int i = 0; i < mac.length; i++) {
                            sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
                            network.setMacAddress(sb.toString());
                        }
                    }
                }
            }
            return network;
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
    };

    public static Function<HttpServletRequest, NetworkResponseDTO> getDeviceAddresses = (request) -> {
        NetworkResponseDTO network = new NetworkResponseDTO();
        String remoteAddr = "";
        if (!Objects.isNull(request)) {
            remoteAddr = request.getHeader(PatternConstants.NetworkConstants.REQUEST_HEADER);
            if (remoteAddr == null || "".equals(remoteAddr))
                remoteAddr = request.getRemoteAddr();
        }
        if (remoteAddr.equals(PatternConstants.NetworkConstants.LOCALHOST_IPV6_ADDRESS)) {
            getLocalHostAddress.apply(network);
        } else {
            network.setIpAddress(remoteAddr);
            network.setMacAddress(getClientMACAddress.apply(remoteAddr));
        }
        return network;
    };
}
