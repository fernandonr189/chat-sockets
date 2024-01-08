import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Main {

    public static final String HOST = "192.168.31.254";
    public static final int PORT = 8080;

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            ServerListenThread serverListenThread = new ServerListenThread(serverSocket);
            serverListenThread.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        while (true) {
            Scanner scanner = new Scanner(System.in);
            String message = scanner.nextLine();
            new ClientThread(HOST, PORT, message).start();
        }
    }
}