import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientThread extends Thread{
    private Socket socket;
    private String host;
    private MessagesBuffer messagesBuffer;

    public ClientThread(String host, MessagesBuffer messagesBuffer) {
        this.host = host;
        this.messagesBuffer = messagesBuffer;
        try {
            this.socket = new Socket(host, 8080);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        while(true) {
            try {
                this.socket = new Socket(host, 8080);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            DataOutputStream dataOutputStream;

            try {
                dataOutputStream = new DataOutputStream(socket.getOutputStream());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            String message = messagesBuffer.consume();

            try {
                dataOutputStream.writeUTF(message);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
