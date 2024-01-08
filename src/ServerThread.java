import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerThread extends Thread{

    private ServerSocket s_socket;
    public ServerThread(ServerSocket s_socket) {
        this.s_socket = s_socket;
    }


    @Override
    public void run() {
        while(true) {
            Socket socket;
            DataInputStream dataInputStream;
            String incoming;
            try {
                socket = s_socket.accept();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                dataInputStream = new DataInputStream(socket.getInputStream());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                incoming = dataInputStream.readUTF();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if(!incoming.isEmpty()) {
                System.out.println("RECIBIDO: " + incoming);
            }
        }
    }
}
