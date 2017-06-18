package toolkit.net.ws.frame;

import toolkit.bytearray.BoundedByteArray;
import toolkit.bytearray.ByteArrayHelper;
import toolkit.net.ws.ReceiveChannel;

import java.util.ArrayList;

public class FrameReceiver {
    private boolean fin;
    private int opCode;
    private int payload;
    private boolean hasMask;
    private byte[] masks;

    private int step = 0;

    public FrameReceiver() {

    }

    private Frame readFrame(ReceiveChannel channel) {
        if (step == 0 && channel.length() >= 2) {
            step = 1;

            BoundedByteArray bba = channel.read(2);
            byte b = bba.getByte(0);
            fin = (b & 0x80) == 0x80;
            opCode = b & 0x0f;

            b = bba.getByte(1);
            hasMask = (b & 0x80) == 0x80;
            payload = b & 0x7f;


//            Console.log("=====", fin, opCode, hasMask, payload);
        }

        if (step == 1) {
            if (payload == 126) {
                if (channel.length() >= 2) {
                    step = 2;
                    BoundedByteArray bba = channel.read(2);
                    payload = ByteArrayHelper.readUInt16(bba.bytes, bba.off);
                }
            } else if (payload == 127) {
                if (channel.length() >= 8) {
                    step = 2;
                    BoundedByteArray bba = channel.read(8);
                    payload = ByteArrayHelper.readUInt64(bba.bytes, bba.off);
                }
            } else {
                step = 2;
            }
        }

        if (step == 2) {
            if (hasMask) {
                if (channel.length() >= 4) {
                    step = 3;
                    BoundedByteArray bba = channel.read(4);
                    masks = new byte[]{bba.getByte(0), bba.getByte(1), bba.getByte(2), bba.getByte(3)};
                }
            } else {
                step = 3;
            }
        }

        if (step == 3 && channel.length() >= payload) {
            step = 0;
            BoundedByteArray baa = channel.read(payload);
            if (baa != null && hasMask) {
                for (int i = 0; i < baa.len; i++) {
                    baa.bytes[baa.off + i] = (byte) (baa.bytes[baa.off + i] ^ masks[i % 4]);
                }
            }
            return new Frame(fin, opCode, payload, baa);
        }

        return null;
    }

    public ArrayList<Frame> receive(ReceiveChannel channel) {
        ArrayList<Frame> list = new ArrayList<>();
        while (true) {
            Frame frame = readFrame(channel);
            if (frame == null) {
                break;
            }
            list.add(frame);
        }
        return list;
    }
}
