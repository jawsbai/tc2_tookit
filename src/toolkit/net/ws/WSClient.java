package toolkit.net.ws;

import toolkit.bytearray.ByteArrayHelper;
import toolkit.net.socket.SocketClient;
import toolkit.net.ws.frame.Frame;
import toolkit.net.ws.frame.FrameReceiver;
import toolkit.net.ws.http.HttpReceiver;
import toolkit.net.ws.http.HttpRequest;
import toolkit.print.Console;
import toolkit.thread.ActiveObject;
import toolkit.util.SHA1;
import toolkit.helper.StringHelper;

import java.net.Socket;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Objects;

public class WSClient extends SocketClient {
    private static final String GUID = "258EAFA5-E914-47DA-95CA-C5AB0DC85B11";

    private static final int OPCODE_DATA_FRAME = 0x0;//表示附加数据帧
    private static final int OPCODE_TEXT_FRAME = 0x1;//表示文本数据帧
    private static final int OPCODE_BIN_FRAME = 0x2;//表示二进制数据帧
    //    private static final int OPCODE_UN1=      0x3-7;//暂时无定义，为以后的非控制帧保留
    private static final int OPCODE_CLOSE = 0x8;//表示连接关闭
    private static final int OPCODE_PING = 0x9;//表示ping
    private static final int OPCODE_PNG = 0xA;//表示pong
//    private static final int OPCODE_       0xB-F暂时无定义，为以后的控制帧保留

    private HttpReceiver httpReceiver = new HttpReceiver();
    private FrameReceiver frameReceiver;
    private HttpRequest request;
    private ReceiveChannel channel = new ReceiveChannel();
    private ArrayList<Frame> frames = new ArrayList<>();

    public WSClient(Socket socket, ActiveObject recvAO, ActiveObject sendAO) {
        super(socket, 1024, recvAO, sendAO);
    }

    @Override
    protected void onReceive(byte[] bytes, int len) {
//        Console.log("=========onReceive", len);

        channel.receive(bytes, len);

        if (frameReceiver != null) {
            ArrayList<Frame> frames = frameReceiver.receive(channel);
            onFrame(frames);
        } else if (httpReceiver != null) {
            HttpRequest request = httpReceiver.receive(channel);
            if (request != null) {
                this.request = request;
                httpReceiver = null;
                onHttpRequest();
            }
        }
    }

    private void onHttpRequest() {
        if (Objects.equals(request.upgrade(), "Upgrade")
                || Objects.equals(request.secWebSocketKey(), "")
                || Objects.equals(request.secWebSocketKey(), "")) {
            close();
        } else {
            frameReceiver = new FrameReceiver();
            send(StringHelper.toBytes(responseText(request)));
        }
    }

    private void onFrame(ArrayList<Frame> frames) {
        for (Frame frame : frames) {
            Console.log("frame", frame.fin, frame.opCode, frame.payload);

            if (frame.opCode == OPCODE_CLOSE) {
                close();
                break;
            }

            if (frame.fin) {
                if (this.frames.size() > 0) {
                    this.frames.add(frame);

                    int count = 0;
                    int index = 0;
                    for (Frame f : this.frames) {
                        count += f.payload;
                    }
                    byte[] bytes = new byte[count];
                    for (Frame f : this.frames) {
                        System.arraycopy(f.body.bytes, f.body.off, bytes, index, f.body.len);
                        index += f.body.len;
                    }
//                    Console.log(count, bytes.length);
//                    Console.log(bytes);

                    this.frames.clear();
                } else {
                    sendFrame(1, 2, new byte[]{1, 2, 3, 4, 5, 6});
                }
            } else {
                this.frames.add(frame);
            }
        }
    }

    private String responseText(HttpRequest request) {
        byte[] sha1 = SHA1.encode(StringHelper.toBytes(request.secWebSocketKey() + GUID));
        String accept = Base64.getEncoder().encodeToString(sha1);
        return "HTTP/1.1 101 Switching Protocols\r\n" +
                "Upgrade: websocket\r\n" +
                "Connection: Upgrade\r\n" +
                "Sec-WebSocket-Accept:" + accept + "\r\n" +
                "\r\n";
    }

    public void sendFrame(int FIN, int opCode, byte[] bytes) {
        send(encodeFrame(FIN, opCode, bytes));
    }

    private byte[] encodeFrame(int fin, int opCode, byte[] bytes) {
        byte[] buffer;
        int len = bytes.length;
        int hlen = 2;
        if (len < 126) {
            buffer = new byte[len + hlen];
            buffer[1] = (byte) len;
        } else if (len < 65536) {
            hlen += 2;
            buffer = new byte[len + hlen];
            buffer[1] = 126;
            ByteArrayHelper.writeUInt16(buffer, 2, len);
        } else {
            hlen += 8;
            buffer = new byte[len + hlen];
            buffer[1] = 127;
            ByteArrayHelper.writeUInt64(buffer, 2, len);
            buffer[2] = 0;
            buffer[3] = 0;
            buffer[4] = 0;
            buffer[5] = 0;
        }
        buffer[0] = (byte) ((fin << 7) + opCode);
        System.arraycopy(bytes, 0, buffer, hlen, len);
        return buffer;
    }

    @Override
    protected void onClosed() {
        Console.log("onClosed");
    }
}