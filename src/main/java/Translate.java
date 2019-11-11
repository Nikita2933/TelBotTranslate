import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

class Translate {
    private Setter setter = new Setter();

    String getJsonStringYandex(String text) throws IOException {
        String apiKey = setter.apikey;
        String requestUrl = "https://translate.yandex.net/api/v1.5/tr.json/translate?key="
                + apiKey + "&lang=" + "ru-en" + "&text=" + URLEncoder.encode(text, "UTF-8");

        URL url = new URL(requestUrl);
        HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
        httpConnection.connect();
        int rc = httpConnection.getResponseCode();

        if (rc == 200) {
            String line;
            BufferedReader buffReader = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));
            StringBuilder strBuilder = new StringBuilder();
            while ((line = buffReader.readLine()) != null) {
                strBuilder.append(line).append('\n');
            }

            return getTranslateFromJSON(strBuilder.toString());
        }

        return "beard";
    }

    private String getTranslateFromJSON(String str) {
        JSONParser parser = new JSONParser();
        JSONObject object = null;
        try {
            object = (JSONObject) parser.parse(str);
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
        StringBuilder sb = new StringBuilder();
        assert object != null;
        JSONArray array = (JSONArray) object.get("text");
        for (Object s : array) {
            sb.append(s.toString()).append("\n");
        }
        return sb.toString();
    }
}
