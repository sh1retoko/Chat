import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Client extends ConstructorClient implements Runnable {

    public Client(Socket socket, Chat chat) {
        super(socket, chat);

        // Запускаем поток
        new Thread(this).start();
    }

    // Получаем строку
    void receive(String message) {
        out.println(message);
    }

    @Override
    public void run() {
        try {

            // Получаем потоки ввода и вывода
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();

            // Создаем удобные средства ввода и вывода
            in = new Scanner(is);
            out = new PrintStream(os);

            // Читаем из сети и пишем в сеть
            out.println("Welcome to chat!");
            String input = in.nextLine();

            // Чат работает пока не будет написан: "exit"
            // Переход на след. строку
            while (!input.equals("exit")) {
                chat.sendAll(input);
                input = in.nextLine();
            }

            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
