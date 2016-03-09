import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;

public class Server {

	public static void main(String[] args) {

		SSLServerSocketFactory ssf = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
		System.out.println("Stöder:");

		for (int i = 0; i < ssf.getSupportedCipherSuites().length; i++)
			System.out.println(ssf.getSupportedCipherSuites()[i]);

		SSLServerSocket ss = null;
		try {
			ss = (SSLServerSocket) ssf.createServerSocket(1234);
			String[] cipher = { "SSL_DH_anon_WITH_RC4_128_MD5" };
			ss.setEnabledCipherSuites(cipher);
			System.out.println("Vald:");

			for (int i = 0; i < ss.getEnabledCipherSuites().length; i++)
				System.out.println(ss.getEnabledCipherSuites()[i]);

			SSLSocket s = (SSLSocket) ss.accept();
			BufferedReader infil = new BufferedReader(new InputStreamReader(s.getInputStream()));
			String rad = null;
			while ((rad = infil.readLine()) != null)
				System.out.println(rad);
			infil.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}