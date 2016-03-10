import javax.net.ssl.*;
import java.io.IOException;
import java.io.PrintStream;

public class Server {

	public static void main(String[] args) {

        // Set the keystore and truststore information. This can also be set in command line.
        System.setProperty("javax.net.ssl.keyStore","/Users/Nina/git/intnet-lab6/lab6keystore");
        System.setProperty("javax.net.ssl.trustStore","/Users/Nina/git/intnet-lab6/lab6truststore");
        System.setProperty("javax.net.ssl.keyStorePassword","ninaeric");
        System.setProperty("javax.net.ssl.trustStorePassword","ninaeric");

        // Server socket.
		SSLServerSocketFactory ssf = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
		SSLServerSocket serverSocket = null;

		try {

            // Set enabled ciphers.
			serverSocket = (SSLServerSocket) ssf.createServerSocket(1234);
            String[] ciphers = {"TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA"};
            serverSocket.setEnabledCipherSuites(ciphers);
			System.out.println("--- STARTED SERVER --- \nEnabled ciphers:");

			for (int i = 0; i < serverSocket.getEnabledCipherSuites().length; i++) {
                System.out.println(serverSocket.getEnabledCipherSuites()[i]);
            }

            // Listen for connections, send them a message and close them down.
			while(true) {
                System.out.println("Listening for connections...");
				SSLSocket socket = (SSLSocket) serverSocket.accept();
                System.out.println("Accepted connection from: " + socket.getRemoteSocketAddress());

                // Add a listener which fires when the handshake is completed between server and client.
                socket.addHandshakeCompletedListener(new HandshakeCompletedListener() {
                    @Override
                    public void handshakeCompleted(HandshakeCompletedEvent e) {
                        System.out.println("Completed handshake. Cipher suite: " + e.getCipherSuite());
                    }
                });

                // Try to send the client a message.
                try {
                    PrintStream response = new PrintStream(socket.getOutputStream());
                    response.println("HTTP/1.0 200 OK");
                    response.println("Server : Slask 0.1 Beta");
                    response.println("Content-Type: text/html");
                    response.println();
                    response.println("<!DOCTYPE html><head></head><body><p>Hello World!</p></body></html>");
                    System.out.println("Printed hello world to client.");
                } catch(Exception e) {
                    System.out.println("Could not print to client.");
                }

                socket.close();
			}

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}