package com.tc2.toolkit.net.ws.frame;

import com.tc2.toolkit.bytearray.BoundedByteArray;

public class Frame {
    public final boolean fin;
    public final int opCode;
    public final int payload;
    public final BoundedByteArray body;

    public Frame(boolean fin, int opCode, int payload, BoundedByteArray body) {
        this.fin = fin;
        this.opCode = opCode;
        this.payload = payload;
        this.body = body;
    }
}
