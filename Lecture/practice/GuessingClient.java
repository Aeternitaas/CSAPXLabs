package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class GuessingClient {

    public static void main( String[] args ) {
        if ( args.length != 2 ) {
            System.err.println(
                    "Usage: java GuessingClient host server-port" );
            System.exit( 1 );
        }
        try (
                Socket serverConnection =
                        new Socket( args[ 0 ], Integer.parseInt( args[ 1 ] ) );
                BufferedReader userIn =
                        new BufferedReader(
                                new InputStreamReader( System.in )
                        )
        ) {
            BufferedReader gameIn =
                    new BufferedReader(
                            new InputStreamReader(
                                    serverConnection.getInputStream()
                            )
                    );
            PrintWriter gameOut =
                    new PrintWriter( serverConnection.getOutputStream(), true );
            String prompt = gameIn.readLine();
            while ( !prompt.equals( GuessServer.GAME_OVER ) ) {
                System.out.println( prompt );
                String line = userIn.readLine();
                gameOut.println( line );
                prompt = gameIn.readLine();
            }
            System.out.println( prompt );
        }
        catch( NumberFormatException nfe ) {
            System.err.println( "server-port must be an integer" );
        }
        catch( IOException ioe ) {
            System.out.println( ioe.getMessage() );
        }
    }
}
