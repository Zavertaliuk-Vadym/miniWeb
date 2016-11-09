import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URISyntaxException;
import java.util.Arrays;

public class Client {
    static String header = "<!DOCTYPE html>\n" +
            "<html>\n" +
            "<head>\n" +
            "<title>Page Title</title>\n" +
            "</head>\n" +
            "<body>\n";
    static String footer = "</body>\n" +
            "</html>";

    public static void main(String args[]) throws IOException, URISyntaxException {
        int requestNumber = 0;
        while (true) {
            try (ServerSocket server = new ServerSocket(8080)) {
                System.out.println("Listening for connection on port 8080 ....");
                StringBuilder builder = new StringBuilder();


                Socket socket = server.accept();
                builder.append(header);
                requestNumber++;
                String httpResponse = "" + "Hello world! Times: " + requestNumber;

                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                System.out.println(Arrays.toString(reader.readLine().split("/")));
                builder.append(httpResponse);
                builder.append(footer);
                builder.toString();
                socket.getOutputStream().write(builder.toString().getBytes("UTF-8"));
                reader.close();
            }
        }
    }

}

