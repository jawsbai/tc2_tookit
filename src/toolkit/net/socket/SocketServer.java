package toolkit.net.socket;

import toolkit.thread.ActiveObject;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public abstract class SocketServer {
    private ServerSocket _server;
    private ActiveObject _ao;

    private ActiveObject[] _recvAOs;
    private ActiveObject[] _sendAOs;

    public SocketServer(int port, int recvAOs, int sendAOs) throws IOException {
        _server = new ServerSocket(port);

        _recvAOs = new ActiveObject[recvAOs];
        _sendAOs = new ActiveObject[sendAOs];
        for (int i = 0; i < _recvAOs.length; i++) {
            _recvAOs[i] = new ActiveObject(1);
            _recvAOs[i].start();
        }
        for (int i = 0; i < _sendAOs.length; i++) {
            _sendAOs[i] = new ActiveObject(1);
            _sendAOs[i].start();
        }

        _ao = new ActiveObject(1);
        _ao.tick(this::onTick);
        _ao.start();
    }

    private boolean onTick() {
        try {
            accept(_server.accept());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private ActiveObject randomAOs(ActiveObject[] aos) {
        return aos[(int) (Math.random() * aos.length)];
    }

    private void accept(Socket socket) {
        onAccept(socket, randomAOs(_recvAOs), randomAOs(_sendAOs));
    }

    protected abstract void onAccept(Socket socket, ActiveObject recvAO, ActiveObject sendAO);
}
