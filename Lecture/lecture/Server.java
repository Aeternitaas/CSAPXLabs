package lecture;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static final int PORT = 1025;

    public static void main(String[] args) {
        ServerSocket service = null;
        Socket client = null;
        try {
            service = new ServerSocket( Server.PORT );
            client = service.accept();

            BufferedReader in = new BufferedReader( new InputStreamReader( client.getInputStream() ) );
            String message = in.readLine();

            System.out.println( message );
        } catch ( IOException io) {
            System.out.println( io );
        } finally {
            try {
                if (service != null) {
                    service.close();
                }
                if (client != null) {
                    client.close();
                }
            } catch ( IOException io ) {
                io.printStackTrace();
                System.out.println( io );
            }
        }
    }        
}
