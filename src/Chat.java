import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

// 127.0.0.1

public class Chat {

    ArrayList<Client> clients = new ArrayList<>();
    ServerSocket serverSocket;

    Chat() throws IOException {
        // Создаем серверный сокет на порту 1234
        serverSocket = new ServerSocket(1234);
    }

    // Рассылаем полученную строку всем
    void sendAll(String message) {
        for (Client client : clients) {
            client.receive(message);
        }
    }

    public void run() {
        while (true) {
            System.out.println("Waiting...");

            try {

                // Ждем клиента
                Socket socket = serverSocket.accept();
                System.out.println("Client connected!");

                // Создаем клиента на своей стороне
                clients.add(new Client(socket, this));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
