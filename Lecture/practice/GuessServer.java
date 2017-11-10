package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class GuessServer {

    public static final int PORT = 5309;
    public static final int MAX_NUM = 100;
    public static final String GAME_OVER = "You got it! Congratulations.";

    public static void main( String[] args ) {
        Random ranNumGen = new Random();
        int targetNum = ranNumGen.nextInt( MAX_NUM );
        networkIO( targetNum );
    }

    private static void consoleIO( int targetNum ) {
        try (
                BufferedReader in = new BufferedReader(
                        new InputStreamReader( System.in ) );
                PrintWriter out = new PrintWriter( System.out, true );
        ) {
            guessingGame( targetNum, in, out );
        }
        catch( IOException ioe ) {
            System.out.println( "Got an exception." );
            System.out.println( ioe.getMessage() );
            System.out.println( ioe );
        }
    }

    private static void networkIO( int targetNum ) {
        System.out.println( "SH! The secret number is " + targetNum + '.');
        System.out.println( "My port number is " + GuessServer.PORT + '.' );
        ServerSocket service = null;
        Socket clientConnection = null;
        try {
            service = new ServerSocket( GuessServer.PORT );
            clientConnection = service.accept();
            BufferedReader in = new BufferedReader(
                                    new InputStreamReader(
                                            clientConnection.getInputStream()
                                    )
            );
            PrintWriter out = new PrintWriter(
                    clientConnection.getOutputStream(), true
            );
            guessingGame( targetNum, in, out );
        }
        catch( IOException ioe ) {
            System.out.println( "Got an exception." );
            System.out.println( ioe.getMessage() );
            System.out.println( ioe );
        }
        finally {
            try {
                if ( clientConnection != null ) clientConnection.close();
                if ( service != null ) service.close();
            }
            catch( IOException ioe ) {
                System.out.println( "Exception raised while closing sockets" );
                System.out.println( ioe );
            }
        }
    }

    private static void guessingGame(
            int target, BufferedReader userIn, PrintWriter gameOut )
            throws IOException {
        gameOut.print(
                "I am thinking of a number between 0 and " + MAX_NUM + ". " );
        int guess;
        do {
            gameOut.println( "What is your guess?" );
            String guessString = userIn.readLine();
            guess = Integer.parseInt( guessString );
            if ( guess < target ) gameOut.print( "Too low. " );
            else if ( guess > target ) gameOut.print( "Too high. " );
        } while ( guess != target );
        gameOut.println( GAME_OVER );
    }
}
