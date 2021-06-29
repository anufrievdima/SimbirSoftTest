import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        String text = getText("https://www.simbirsoft.com/");
        Map<String, Integer> map = countMap(text);
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
    }

    public static String getText(String url) throws IOException {
        Document document = Jsoup.connect(url).get();
        return document.body().text();
    }

    public static Map<String, Integer> countMap(String text) {
        String[] split = text.split("[\\s-\\\\t,;.?!:@\\\\[\\\\](){}_*/]");

        Map<String, Integer> map = new HashMap<>();
        for (String s : split) {
            map.put(s, map.getOrDefault(s, 0) + 1);
        }
        return map;
        //{' ', ',', '.', '! ', '?','"', ';', ':', '[', ']', '(', ')', '\n', '\r', '\t'}
        //'"'
    }
}
