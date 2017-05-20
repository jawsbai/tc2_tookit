package com.tc2.toolkit.net.socket;

import com.tc2.toolkit.thread.ActiveObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public abstract class SocketClient {
    private final Socket _socket;
    private final ActiveObject _recvAO;
    private final ActiveObject _sendAO;
    private final int _bufferLength;
    private boolean _isClosed;
    private boolean _canReceive = false;

    public SocketClient(Socket socket, int bufferLength, ActiveObject recvAO, ActiveObject sendAO) {
        _socket = socket;
        _bufferLength = bufferLength;
        _recvAO = recvAO;
        _sendAO = sendAO;

        _recvAO.tick(this::onTick);
    }

    public final String ip() {
        return _socket.getInetAddress().toString();
    }

    public final int port() {
        return _socket.getPort();
    }

    public final boolean isClosed() {
        return _isClosed;
    }

    public final synchronized void close() {
        if (!_isClosed) {
            try {
                _isClosed = true;
                _socket.close();
                onClosed();
            } catch (IOException ignored) {
            }
        }
    }

    public final void beginReceive() {
        _canReceive = true;
    }

    private boolean onTick() {
        if (_canReceive) {
            try {
                InputStream input = _socket.getInputStream();
                if (input.available() > 0) {
                    byte[] buffer = new byte[_bufferLength];
                    int len = input.read(buffer);
                    onReceive(buffer, len);
                }
            } catch (IOException e) {
                e.printStackTrace();
                close();
            }
        }
        return _isClosed;
    }

    protected abstract void onReceive(byte[] bytes, int len);

    protected abstract void onClosed();

    public final void send(byte[] bytes) {
        _sendAO.invoke(() -> {
            if (!_isClosed) {
                try {
                    OutputStream output = _socket.getOutputStream();
                    output.write(bytes);
                } catch (IOException e) {
                    e.printStackTrace();
                    close();
                }
            }
        });
    }
}

