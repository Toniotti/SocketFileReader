import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(5501);
        String fileLocation = "/home/files/log.txt";
        while (true) {
            Socket socket = serverSocket.accept();
            File file = new File(fileLocation);
            file.createNewFile();
            byte[] bytes = new byte[16 * 1024];
            try (OutputStream out = new FileOutputStream(file); InputStream in = socket.getInputStream()) {
                int count;
                while ((count = in.read(bytes)) > 0) {
                    out.write(bytes, 0, count);
                }
            }
        }
    }
}
