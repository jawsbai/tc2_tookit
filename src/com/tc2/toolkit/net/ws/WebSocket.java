package com.tc2.toolkit.net.ws;

import com.tc2.toolkit.action.Action1;
import com.tc2.toolkit.net.socket.SocketServer;
import com.tc2.toolkit.thread.ActiveObject;

import java.io.IOException;
import java.net.Socket;

public class WebSocket extends SocketServer {
    private Action1<WSClient> _accept;

    public WebSocket(int port, int recvAOs, int sendAOs, Action1<WSClient> accept) throws IOException {
        super(port, recvAOs, sendAOs);

        _accept = accept;
    }

    @Override
    protected void onAccept(Socket socket, ActiveObject recvAO, ActiveObject sendAO) {
        _accept.invoke(new WSClient(socket, recvAO, sendAO));
    }
}
