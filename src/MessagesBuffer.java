import java.util.ArrayList;

public class MessagesBuffer {

    ArrayList<String> messages;

    private boolean isEmpty;

    public MessagesBuffer() {
        isEmpty = true;
        messages = new ArrayList<>();
    }

    public synchronized String consume() {
        while(isEmpty) {
            try {
                wait();
            }
            catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        }
        String message = messages.getFirst();
        messages.removeFirst();
        if(messages.isEmpty()) {
            isEmpty = true;
        }
        notifyAll();
        return message;
    }
    public synchronized void produce(String newMessage) {
        messages.add(newMessage);
        isEmpty = false;
        notifyAll();
    }
}
