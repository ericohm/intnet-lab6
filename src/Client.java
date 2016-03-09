import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class Client {
	public static void main(String[] args) {
		SSLSocketFactory sf = (SSLSocketFactory) SSLSocketFactory.getDefault();
/*
		for (int i = 0; i < sf.getSupportedCipherSuites().length; i++)
			System.out.println(sf.getSupportedCipherSuites()[i]);
*/
		HttpsURLConnection.setDefaultSSLSocketFactory(sf);
		SSLSocket s = null;
		try {
			s = (SSLSocket) sf.createSocket("127.0.0.1", 1234);
		} catch (MalformedURLException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("SERVER'S SUPPORTED CIPHERS");
		for (int i = 0; i < s.getSupportedCipherSuites().length; i++) {
			System.out.println(s.getSupportedCipherSuites()[i]);
		}
		String[] cipher = { "SSL_DH_anon_WITH_RC4_128_MD5" };
		s.setEnabledCipherSuites(cipher);
		System.out.println("SERVER'S ENABLED CIPHERS");
		for (int i = 0; i < s.getEnabledCipherSuites().length; i++)
			System.out.println(s.getEnabledCipherSuites()[i]);
		PrintWriter utfil = null;
		try {
			utfil = new PrintWriter(s.getOutputStream());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		utfil.println("Hej");
		utfil.close();
	}
}