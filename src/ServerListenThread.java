import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerListenThread extends Thread{

    private ServerSocket serverSocket;
    public ServerListenThread(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    @Override
    public void run() {
        while(true) {
            Socket socket = null;
            try {
                socket = serverSocket.accept();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            new ServerThread(socket).start();
        }
    }
}
