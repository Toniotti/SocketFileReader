package br.com.hbsis.core;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.UUID;

public class Main {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(5501);
            while (true) {
                Socket socket = serverSocket.accept();
                String fileLocation = "C:\\Users\\matheus.toniotti\\Documents\\teste\\log_" + UUID.randomUUID() + ".txt";
                File file = new File(fileLocation);
                file.createNewFile();
                byte[] bytes = new byte[16 * 1024];
                try (BufferedWriter bw = new BufferedWriter(new FileWriter(file)); InputStream in = socket.getInputStream()) {
                    bw.write(new String(bytes));
                } catch (Exception e) {
                    continue;
                }
            }
    }
}
