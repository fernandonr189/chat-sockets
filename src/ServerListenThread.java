import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerListenThread extends Thread{

    private final ServerSocket serverSocket;
    public ServerListenThread(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    @Override
    public void run() {
        while(true) {
            try {
                Socket socket = serverSocket.accept();
                new ServerThread(socket).start();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
