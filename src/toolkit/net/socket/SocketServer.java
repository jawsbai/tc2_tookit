package toolkit.net.socket;

import toolkit.thread.ActiveObject;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public abstract class SocketServer {
    private final ServerSocket server;
    private final ActiveObject ao;

    private final ActiveObject[] recvAOs;
    private final ActiveObject[] sendAOs;

    public SocketServer(int port, int recvAOs, int sendAOs) throws IOException {
        server = new ServerSocket(port);

        this.recvAOs = new ActiveObject[recvAOs];
        this.sendAOs = new ActiveObject[sendAOs];
        for (int i = 0; i < this.recvAOs.length; i++) {
            this.recvAOs[i] = new ActiveObject(1);
            this.recvAOs[i].start();
        }
        for (int i = 0; i < this.sendAOs.length; i++) {
            this.sendAOs[i] = new ActiveObject(1);
            this.sendAOs[i].start();
        }

        ao = new ActiveObject(1);
        ao.tick(this::onTick);
        ao.start();
    }

    private boolean onTick() {
        try {
            accept(server.accept());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private ActiveObject randomAOs(ActiveObject[] aos) {
        return aos[(int) (Math.random() * aos.length)];
    }

    private void accept(Socket socket) {
        onAccept(socket, randomAOs(recvAOs), randomAOs(sendAOs));
    }

    protected abstract void onAccept(Socket socket, ActiveObject recvAO, ActiveObject sendAO);
}
