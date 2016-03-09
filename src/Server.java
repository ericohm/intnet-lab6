import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class Server {

	public static void main(String[] args) {

        System.setProperty("javax.net.ssl.keyStore","/Users/Nina/git/intnet-lab6/lab6keystore");
        System.setProperty("javax.net.ssl.trustStore","/Users/Nina/git/intnet-lab6/lab6truststore");
        System.setProperty("javax.net.ssl.keyStorePassword","ninaeric");
        System.setProperty("javax.net.ssl.trustStorePassword","ninaeric");

		SSLServerSocketFactory ssf = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
		System.out.println("Stöder:");

		for (int i = 0; i < ssf.getSupportedCipherSuites().length; i++)
			System.out.println(ssf.getSupportedCipherSuites()[i]);

		SSLServerSocket serverSocket = null;
		try {
			serverSocket = (SSLServerSocket) ssf.createServerSocket(1234);
			String[] cipher = { "SSL_DH_anon_WITH_RC4_128_MD5" };
			serverSocket.setEnabledCipherSuites(cipher);
			System.out.println("Vald:");

			for (int i = 0; i < serverSocket.getEnabledCipherSuites().length; i++)
				System.out.println(serverSocket.getEnabledCipherSuites()[i]);

            // Listen for connections, send them a message and close them down
			while(true) {
                System.out.println("Listening for connections...");
				SSLSocket socket = (SSLSocket) serverSocket.accept();
                PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
                out.println("Hello World!");
                out.flush();
                socket.close();
			}

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}