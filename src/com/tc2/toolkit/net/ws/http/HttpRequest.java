package com.tc2.toolkit.net.ws.http;

import com.tc2.toolkit.print.Console;

import java.util.HashMap;

public class HttpRequest {
    private final HashMap<String, String> _map;

    public final String method;
    public final String path;
    public final String version;

    public HttpRequest(String method, String path, String version,
                       HashMap<String, String> map) {
        this.method = method;
        this.path = path;
        this.version = version;
        this._map = map;
    }

    public String getValue(String key) {
        return _map.containsKey(key) ? _map.get(key) : "";
    }

    public String host() {
        return getValue("Host");
    }

    public String connection() {
        return getValue("Connection");
    }

    public String cacheControl() {
        return getValue("Cache-Control");
    }

    public String upgrade() {
        return getValue("Upgrade");
    }

    public String origin() {
        return getValue("Origin");
    }

    public String userAgent() {
        return getValue("User-Agent");
    }

    public String acceptEncoding() {
        return getValue("Accept-Encoding");
    }

    public String acceptLanguage() {
        return getValue("Accept-Language");
    }

    public String cookie() {
        return getValue("Cookie");
    }

    public String secWebSocketVersion() {
        return getValue("Sec-WebSocket-Version");
    }

    public String secWebSocketKey() {
        return getValue("Sec-WebSocket-Key");
    }

    public String secWebSocketExtensions() {
        return getValue("Sec-WebSocket-Extensions");
    }

    public static HttpRequest fromString(String text) {
        String[] lines = text.split("\r\n");
        String[] line1Arr = lines[0].split(" ");
        HashMap<String, String> map = new HashMap<>();

        for (int i = 1, len = lines.length; i < len; i++) {
            String[] line2Arr = lines[i].split(": ");
            String key = line2Arr[0];
            String value = line2Arr[1];
            if (!map.containsKey(key)) {
                map.put(key, value);
            } else {
                map.replace(key, value);
            }
        }

        return new HttpRequest(line1Arr[0], line1Arr[1], line1Arr[2], map);
    }
}
