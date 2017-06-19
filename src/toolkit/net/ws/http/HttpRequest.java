package toolkit.net.ws.http;

import java.util.HashMap;

public class HttpRequest {
    private final HashMap<String, String> map;

    public final String method;
    public final String path;
    public final String version;

    public HttpRequest(String method, String path, String version, HashMap<String, String> map) {
        this.method = method;
        this.path = path;
        this.version = version;
        this.map = map;
    }

    public String getValue(String key) {
        return map.getOrDefault(key, "");
    }

    public String getHost() {
        return getValue("Host");
    }

    public String getConnection() {
        return getValue("Connection");
    }

    public String getCacheControl() {
        return getValue("Cache-Control");
    }

    public String getUpgrade() {
        return getValue("Upgrade");
    }

    public String getOrigin() {
        return getValue("Origin");
    }

    public String getUserAgent() {
        return getValue("User-Agent");
    }

    public String getAcceptEncoding() {
        return getValue("Accept-Encoding");
    }

    public String getAcceptLanguage() {
        return getValue("Accept-Language");
    }

    public String getCookie() {
        return getValue("Cookie");
    }

    public String getSecWebSocketVersion() {
        return getValue("Sec-WebSocket-Version");
    }

    public String getSecWebSocketKey() {
        return getValue("Sec-WebSocket-Key");
    }

    public String getSecWebSocketExtensions() {
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
