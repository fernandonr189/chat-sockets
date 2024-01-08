import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientThread extends Thread{
    private Socket socket;
    private DataOutputStream dataOutputStream;
    private String host;

    private String message;
    public ClientThread(String host, String message) {
        this.host = host;
        this.message = message;
    }
    @Override
    public void run() {
        try {
            socket = new Socket(host, 8080);
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            dataOutputStream.writeUTF(message);
            dataOutputStream.close();
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
