package toolkit.net.ws;

import toolkit.lang.Action1;
import toolkit.net.socket.SocketServer;
import toolkit.thread.ActiveObject;

import java.io.IOException;
import java.net.Socket;

public class WebSocket extends SocketServer {
    private Action1<WSClient> accept;

    public WebSocket(int port, int recvAOs, int sendAOs, Action1<WSClient> accept) throws IOException {
        super(port, recvAOs, sendAOs);

        this.accept = accept;
    }

    @Override
    protected void onAccept(Socket socket, ActiveObject recvAO, ActiveObject sendAO) {
        accept.invoke(new WSClient(socket, recvAO, sendAO));
    }
}
