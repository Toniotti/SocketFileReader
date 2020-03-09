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
                String fileLocation = System.getProperty("user.home")+systemSeparator+"logFiles"+systemSeparator+"log_" + UUID.randomUUID() + ".txt";
                File file = new File(fileLocation);
                file.createNewFile();
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
