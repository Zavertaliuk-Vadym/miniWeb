import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URISyntaxException;

public class Client {
    static String header = "<!DOCTYPE html>\n" +
            "<html>\n" +
            "<head>\n" +
            "<title>Page Title</title>\n" +
            "</head>\n" +
            "<body>\n"+
            "\n" +
            " <form action=\"\">\n" +
            "  <p><b>Введите вариант</b></p>\n" +
            "  <input type=\"text\" name=\"name\" \n<Br>\n" +
            "  <p><input type=\"submit\"></p>\n" +
            " </form>\n" +
            "";

    static String footer =
            "</body>\n" +
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
                String parameters = reader.readLine().split(" ")[1];
                if (parameters.equals("/?")) {
//                    builder.append(getViwe());
                    System.out.println(parameters);
                    builder.append("Hello World!");
                } else {
                    builder.append("Hello, " + parseLink(parameters)+"!");
                }
                builder.append(footer);
                socket.getOutputStream().write(builder.toString().getBytes("UTF-8"));
                builder.append(httpResponse);
                reader.close();


//                System.out.println(Arrays.toString(reader.readLine().split("/")));
//                builder.toString();
            }
        }
    }

    private static String parseLink(String parameters) {
        if (parameters.contains("name")) {
            return parseName(parameters);
        } else if (parameters.contains("calendar")) {
            return getCalendar();
        } else {
            return "World!";
        }
    }

    private static String getCalendar() {
        return "";
    }

    private static String parseName(String parameters) {
        int index = parameters.indexOf("=");
        index+=1;
        String result = parameters.substring(index);
        System.out.println(result);
        return result.isEmpty() ? "world" : result;
    }

}

