import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerThread extends Thread{

    private Socket socket;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    public ServerThread(Socket socket) {
        this.socket = socket;
        try {
            this.dataInputStream = new DataInputStream(socket.getInputStream());
            this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void desconectar() {
        try {
            socket.close();
        }
        catch (IOException ex) {
            System.out.println("ERROR: Ha ocurrido un error al cerrar la conexion");
            throw new RuntimeException(ex);
        }
    }


    @Override
    public void run() {
        String message = "";
        try {
            message = dataInputStream.readUTF();
        }
        catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        if(!message.isEmpty()) {
            System.out.println(message);
        }
        desconectar();
    }
}
