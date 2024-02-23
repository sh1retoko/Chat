import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class ConstructorClient {
    Socket socket;
    Scanner in;
    PrintStream out;
    Chat chat;

    public ConstructorClient(Socket socket, Chat chat) {
        this.socket = socket;
        this.chat = chat;
    }
}
