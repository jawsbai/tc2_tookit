package toolkit.net.ws.frame;

import toolkit.bytearray.BoundedByteArray;
import toolkit.bytearray.ByteArrayHelper;
import toolkit.net.ws.ReceiveChannel;

import java.util.ArrayList;

public class FrameReceiver {
    private boolean _fin;
    private int _opCode;
    private int _payload;
    private boolean _hasMask;
    private byte[] _masks;

    private int _step = 0;

    public FrameReceiver() {

    }

    private Frame readFrame(ReceiveChannel channel) {
        if (_step == 0 && channel.length() >= 2) {
            _step = 1;

            BoundedByteArray bba = channel.read(2);
            byte b = bba.getByte(0);
            _fin = (b & 0x80) == 0x80;
            _opCode = b & 0x0f;

            b = bba.getByte(1);
            _hasMask = (b & 0x80) == 0x80;
            _payload = b & 0x7f;


//            Console.log("=====", _fin, _opCode, _hasMask, _payload);
        }

        if (_step == 1) {
            if (_payload == 126) {
                if (channel.length() >= 2) {
                    _step = 2;
                    BoundedByteArray bba = channel.read(2);
                    _payload = ByteArrayHelper.readUInt16(bba.bytes, bba.off);
                }
            } else if (_payload == 127) {
                if (channel.length() >= 8) {
                    _step = 2;
                    BoundedByteArray bba = channel.read(8);
                    _payload = ByteArrayHelper.readUInt64(bba.bytes, bba.off);
                }
            } else {
                _step = 2;
            }
        }

        if (_step == 2) {
            if (_hasMask) {
                if (channel.length() >= 4) {
                    _step = 3;
                    BoundedByteArray bba = channel.read(4);
                    _masks = new byte[]{bba.getByte(0), bba.getByte(1), bba.getByte(2), bba.getByte(3)};
                }
            } else {
                _step = 3;
            }
        }

        if (_step == 3 && channel.length() >= _payload) {
            _step = 0;
            BoundedByteArray baa = channel.read(_payload);
            if (baa != null && _hasMask) {
                for (int i = 0; i < baa.len; i++) {
                    baa.bytes[baa.off + i] = (byte) (baa.bytes[baa.off + i] ^ _masks[i % 4]);
                }
            }
            return new Frame(_fin, _opCode, _payload, baa);
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
