import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Main {

    public static final String HOST = "192.168.31.254";
    public static final int PORT = 8080;

    public static void main(String[] args) {
        MessagesBuffer outgoing = new MessagesBuffer();
        ServerSocket serverSocket;
        System.out.println("Starting server...");
        try {
            serverSocket = new ServerSocket(PORT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ServerListenThread serverListenThread = new ServerListenThread(serverSocket);
        serverListenThread.start();

        while (true) {
            Scanner scanner = new Scanner(System.in);
            String message = scanner.nextLine();
            new ClientThread(HOST, message).start();
        }
    }
}