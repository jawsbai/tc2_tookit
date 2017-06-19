package toolkit.net.socket;

import toolkit.thread.ActiveObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public abstract class SocketClient {
    private final Socket socket;
    private final ActiveObject recvAO;
    private final ActiveObject sendAO;
    private final int bufferLength;
    private boolean closed;
    private boolean canReceive = false;

    public SocketClient(Socket socket, int bufferLength, ActiveObject recvAO, ActiveObject sendAO) {
        this.socket = socket;
        this.bufferLength = bufferLength;
        this.recvAO = recvAO;
        this.sendAO = sendAO;

        this.recvAO.tick(this::onTick);
    }

    public final String getIp() {
        return socket.getInetAddress().toString();
    }

    public final int getPort() {
        return socket.getPort();
    }

    public final boolean isClosed() {
        return closed;
    }

    public final synchronized void close() {
        if (!closed) {
            try {
                closed = true;
                socket.close();
                onClosed();
            } catch (IOException ignored) {
            }
        }
    }

    public final void beginReceive() {
        canReceive = true;
    }

    private boolean onTick() {
        if (canReceive) {
            try {
                InputStream input = socket.getInputStream();
                if (input.available() > 0) {
                    byte[] buffer = new byte[bufferLength];
                    int len = input.read(buffer);
                    onReceive(buffer, len);
                }
            } catch (IOException e) {
                e.printStackTrace();
                close();
            }
        }
        return closed;
    }

    protected abstract void onReceive(byte[] bytes, int len);

    protected abstract void onClosed();

    public final void send(byte[] bytes) {
        sendAO.invoke(() -> {
            if (!closed) {
                try {
                    OutputStream output = socket.getOutputStream();
                    output.write(bytes);
                } catch (IOException e) {
                    e.printStackTrace();
                    close();
                }
            }
        });
    }
}

