package com.tc2.toolkit.net.ws.http;

import com.tc2.toolkit.bytearray.BoundedByteArray;
import com.tc2.toolkit.net.ws.ReceiveChannel;
import com.tc2.toolkit.utils.StringHelper;

public class HttpReceiver {
    private final static int R = '\r';
    private final static int N = '\n';

    public HttpReceiver() {
    }

    public HttpRequest receive(ReceiveChannel channel) {
        int len = channel.length();
        if (len > 4
                && channel.getByte(len - 4) == R
                && channel.getByte(len - 3) == N
                && channel.getByte(len - 2) == R
                && channel.getByte(len - 1) == N) {
            BoundedByteArray bba = channel.read(len);
            return HttpRequest.fromString(StringHelper.fromBytes(bba.bytes, bba.off, bba.len));
        }
        return null;
    }
}