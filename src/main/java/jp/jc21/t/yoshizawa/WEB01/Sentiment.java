package jp.jc21.t.yoshizawa.WEB01;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

public class Sentiment {

	public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException {
		sentiLan message = getsentiLan("Stepover Toehold With Facelock");
		if (message != null) {
			System.out.println("positive:　" +   message.documents[0].confidenceScores.positive);
			System.out.println("Neutral:　" +    message.documents[0].confidenceScores.neutral);
			System.out.println("Negative:　" +   message.documents[0].confidenceScores.negative);
		}
	}

	static sentiLan getsentiLan(String s) throws IOException, URISyntaxException, InterruptedException {
		Gson gson = new Gson();

		String url = "https://r04jk3a01-text.cognitiveservices.azure.com/" + "text/analytics/v3.0/Sentiment";
		Map<String, String> map = new HashMap<>();
		map.put("Ocp-Apim-Subscription-Key", "30be10474efc4d4fae070cf4643ff113");
									
		sentiDocs doc = new sentiDocs();
		
		doc.id = "1";
		doc.text = s;

		sentiSource src = new sentiSource();
		src.documents = new sentiDocs[1];
		src.documents[0] = doc;

		String jsonData = new Gson().toJson(src);

		InetSocketAddress proxy = new InetSocketAddress("172.17.0.2", 80);

		JsonReader reader = WebApiConnector.postJsonReader(url, proxy, map, jsonData);
		sentiLan message = null;
		if (reader != null) {
			 message = gson.fromJson(reader, sentiLan.class);
			reader.close();
		}
		return message;
	}

}

class sentiLan {
	sentidocuments[] documents;
	String[] errors;
	String modelVersion;
}

class sentidocuments{
	ConfidenceScores confidenceScores;
	String[] warnings;
}

class ConfidenceScores {
	float positive;
	float neutral;
	float negative;
}

//class ConfidenceScores3{
	//float positive;
	//float Neutral;
	//float Negative;
//}

class sentiSource {
	 sentiDocs[] documents;
}

class sentiDocs {
	String id;
	String text;
}
