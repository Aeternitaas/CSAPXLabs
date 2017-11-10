package lecture;

import java.net.Socket;
import java.net.ServerSocket;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.UnknownHostException;

public class Client {
    public static void main( String[] args ) {
        Socket serverConnection = null;

        try {
            serverConnection = new Socket( "localhost", Server.PORT );
            PrintWriter serverOut = new PrintWriter( serverConnection.getOutputStream() );
            serverOut.println( "Hey!" );
            serverOut.flush();          // Clean entirety of output buffer.
            try {
                Thread.sleep( 2000 );
            } catch ( InterruptedException e ) {
                assert false; 
                System.out.println("This should not happen.");
            }
        } catch ( UnknownHostException e ) {
            System.out.println(e);
        } catch ( IOException e ) {
            System.out.println(e);
        } finally {
            try {
                if (serverConnection != null) {
                    serverConnection.close(); 
                }
            } catch ( IOException e ) {
                System.out.println(e);
            }
        }
    }
}
