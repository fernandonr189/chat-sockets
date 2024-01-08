import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientThread extends Thread{
    private final String host;

    private final String message;
    private final int port;
    public ClientThread(String host, int port, String message) {
        this.port = port;
        this.host = host;
        this.message = message;
    }
    @Override
    public void run() {
        try {
            Socket socket = new Socket(host, port);
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            dataOutputStream.writeUTF(message);
            dataOutputStream.close();
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
