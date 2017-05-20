package com.tc2.toolkit;

import com.tc2.toolkit.net.ws.WebSocket;
import com.tc2.toolkit.print.Console;
import com.tc2.toolkit.promise.Promise;
import com.tc2.toolkit.net.socket.SocketServer;
import com.tc2.toolkit.thread.ActiveObject;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        socketTest();
    }

    public static void socketTest() {
        try {
            new WebSocket(7788, 1, 1, client -> {
                Console.log(client);

                client.beginReceive();
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void aoTest() {

        ActiveObject ao = new ActiveObject(1);
        ao.start();

//        ao.timeout(() -> {
//            Console.log("22222222222");
//        }, 1000);
//
//        ao.interval(() -> {
//            Console.log("ssssssssssssss");
//        }, 1000);

        ao.invoke(() -> {
            Promise[] promises = {
                    new Promise<>("ssssssss")
            };
            Promise.all(promises).then(() -> {
                Console.log("all");
            }).catch_(e -> {
                Console.log("all", e);
            }).finish(() -> {
                Console.log("all finish");
            });


            new Promise<>("1111111").then(result -> {
                Console.log("p1.then", result);

                byte[] bs = {};

//                Console.log(bs[100]);

            }).catch_(e -> {
                Console.log("catch_");
                Console.log(e);
            }).then(result -> {
                Console.log("ssssssssssssssss222222", result);
            }).finish(() -> {
                Console.log("finish");
            }).then(() -> {
                Console.log("ssssssssssss");
            });
        });
    }
}
