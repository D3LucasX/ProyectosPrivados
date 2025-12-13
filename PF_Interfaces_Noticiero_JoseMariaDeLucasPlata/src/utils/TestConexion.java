package utils;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class TestConexion {
	public static boolean isConectado() {
		
		// API moderna de java que proporciona métodos mas seguros y asíncronos
		// por lo que la aplicación seguiria funcionando mientras se comprueba la
		// conexión.
		
		// Donde conseguí la información:
			//https://stackoverflow.com/questions/64550136/how-to-set-socket-timeout-in-java-http-client
		try {
	        HttpClient client = HttpClient.newBuilder()
	                .connectTimeout(Duration.ofSeconds(3))
	                .build();

	        HttpRequest request = HttpRequest.newBuilder()
	                .uri(new URI("https://www.google.com/"))
	                .method("HEAD", HttpRequest.BodyPublishers.noBody())// pide solo las cabezeras, mas rapido que un get
	                .timeout(Duration.ofSeconds(3))
	                .build();

	        HttpResponse<Void> response = client.send(request, HttpResponse.BodyHandlers.discarding());

	        int code = response.statusCode();
	        return (200 <= code && code <= 399);
	        // Si el codigo esta entre 200 y 299, es que si que hay conexión,
	        // A partir de 400 es cuando sería error

	    } catch (Exception e) {
	        return false;
		}
	}
}
