package br.com.hbsis.core;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.UUID;

public class Main {
    public static void main(String[] args) throws IOException {
        try {
            ServerSocket serverSocket = new ServerSocket(5501);
            while (true) {
                String fileLocation = "/home/logs/log" + UUID.randomUUID() + ".txt";
                Socket socket = serverSocket.accept();
                File file = new File(fileLocation);
                file.createNewFile();
                byte[] bytes = new byte[16 * 1024];
                try (OutputStream out = new FileOutputStream(file); InputStream in = socket.getInputStream()) {
                    int count;
                    while ((count = in.read(bytes)) > 0) {
                        out.write(bytes, 0, count);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
