package com.websocket.demo;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.text.SimpleDateFormat;
import java.util.Date;

public class WebSocketServerDemo {

    static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) {
        WebSocketServer webSocketServer = new WebSocketServer() {
            @Override
            public void onOpen(WebSocket conn, ClientHandshake handshake) {
                System.out.println("welcome webSocket...");
            }

            @Override
            public void onClose(WebSocket conn, int code, String reason, boolean remote) {
                System.out.println("close webSocket...");
            }

            @Override
            public void onMessage(WebSocket conn, String message) {
                System.out.println("received client message: " + message);
                conn.send(simpleDateFormat.format(new Date()));
            }

            @Override
            public void onError(WebSocket conn, Exception ex) {
                ex.printStackTrace();
            }

            @Override
            public void onStart() {
                System.out.println("websocket start");
            }
        };
        webSocketServer.start();
    }
}
