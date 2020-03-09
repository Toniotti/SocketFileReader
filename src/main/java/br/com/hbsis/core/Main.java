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
            String systemSeparator = System.getProperty("file.separator");
            String filePath = System.getProperty("user.home")+systemSeparator+"logfiles"+systemSeparator;
            String fileName = "log_" + UUID.randomUUID() + ".txt";
            System.out.println(filePath+fileName);
            new File(filePath).mkdir();
            File file = new File(filePath+fileName);
            byte[] bytes = new byte[16 * 1024];
            try(OutputStream out = new FileOutputStream(file)){
                InputStream in = socket.getInputStream();
                int count;
                while ((count = in.read(bytes)) > 0) {
                    out.write(bytes, 0, count);
                }
            } catch (Exception e) {
                continue;
            }
        }
    }
}
