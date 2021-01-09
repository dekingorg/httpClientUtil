package org.deking.utils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.Builder;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

public class httpClientUtil {
	private String[] headers;

	public String[] getHeaders() {
		return headers;
	}

	public httpClientUtil setHeaders(String[] headers) {
		this.headers = headers;
		return this;
	}

	public httpClientUtil setHeaders(String headerKey, String headerValue) {
		this.headers = new String[] { headerKey, headerValue };
		return this;
	}

	public Builder setHeadersFormHashMap(Builder b, HashMap<String, String> h) {
		Iterator<Entry<String, String>> iterator = h.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<String, String> entry = iterator.next();
			b.setHeader(entry.getKey(), entry.getValue());
		}
		return b;
	}

	public Builder setHeaders(Builder b) {
		if (headers != null) {
			for (int i = 0; i < headers.length; i += 2) {
				b.setHeader(headers[i], headers[i + 1]);
			}
		}
		return b;
	}

	public final String get(Object url) throws IOException, InterruptedException, URISyntaxException {

		return HttpClient.newHttpClient()
				.send(setHeaders(HttpRequest.newBuilder().uri(url instanceof URI ? (URI) url : new URI((String) url)))
						.GET().build(), HttpResponse.BodyHandlers.ofString())
				.body();

	}

	public final int getCode(Object url) throws IOException, InterruptedException, URISyntaxException {

		return HttpClient.newHttpClient()
				.send(setHeaders(HttpRequest.newBuilder().uri(url instanceof URI ? (URI) url : new URI((String) url)))
						.GET().build(), HttpResponse.BodyHandlers.ofString())
				.statusCode();

	}

	public final String post(Object url, String data) throws IOException, InterruptedException, URISyntaxException {

		return HttpClient.newHttpClient()
				.send(setHeaders(HttpRequest.newBuilder().uri(url instanceof URI ? (URI) url : new URI((String) url)))
						.POST(HttpRequest.BodyPublishers.ofString(data.toString())).build(),
						HttpResponse.BodyHandlers.ofString())
				.body();

	} 
}
