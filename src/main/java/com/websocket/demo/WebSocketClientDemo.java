package com.websocket.demo;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Timer;
import java.util.TimerTask;

public class WebSocketClientDemo {

    public static void main(String[] args) throws URISyntaxException {
        WebSocketClient webSocketClient = new WebSocketClient(new URI("ws://localhost:80")) {
            @Override
            public void onOpen(ServerHandshake handshakedata) {
                String httpStatusMessage = handshakedata.getHttpStatusMessage();
                System.out.println("httpStatusMessage: " + httpStatusMessage);
            }

            @Override
            public void onMessage(String message) {
                System.out.println("received server message: " + message);
            }

            @Override
            public void onClose(int code, String reason, boolean remote) {
                System.out.println("close code: " + code);
                System.out.println("close reason: " + reason);
                System.out.println("close remote: " + remote);
            }

            @Override
            public void onError(Exception ex) {
                ex.printStackTrace();
            }
        };
        webSocketClient.connect();

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            int count = 0;
            @Override
            public void run() {
                if (webSocketClient.isOpen()) {
                    webSocketClient.send(String.valueOf(count++));
                }
            }
        }, 1000, 1000);
    }
}
