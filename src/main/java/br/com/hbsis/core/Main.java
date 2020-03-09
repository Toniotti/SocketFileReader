package br.com.hbsis.core;

import org.springframework.beans.factory.annotation.Value;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.UUID;

public class Main {
    private static int numberThread = 0;

    @Value("${server.port:5501}")
    private static final int serverPort = 0;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(serverPort);
        while (true) {
            if (numberThread < 5) {
                Socket socket = serverSocket.accept();
                new Thread(() -> {
                    numberThread++;
                    receive(socket);
                    numberThread--;
                }).start();
            }
            continue;
        }
    }

    private static void receive(Socket socket) {
        String systemSeparator = System.getProperty("file.separator");
        String filePath = System.getProperty("user.home") + systemSeparator + "logfiles" + systemSeparator;
        String fileName = "log_" + UUID.randomUUID() + ".txt";
        System.out.println(filePath + fileName);
        new File(filePath).mkdir();
        File file = new File(filePath + fileName);
        byte[] bytes = new byte[16 * 1024];
        try (OutputStream out = new FileOutputStream(file)) {
            InputStream in = socket.getInputStream();
            int count;
            while ((count = in.read(bytes)) > 0) {
                out.write(bytes, 0, count);
            }
        } catch (Exception e) {
        }
    }
}
