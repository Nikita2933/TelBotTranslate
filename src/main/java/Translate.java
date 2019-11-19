//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

class Translate {
    private Setter setter = new Setter();
    String lang = "ru-en";
    String translangruen = "ru-en";
    String translangenru = "en-ru";
    String dictlangru = "en-ru";
    String dictlangruru = "ru-ru";
    String url = this.setter.urlTrans;
    String apiKey = this.setter.apikeytrans;


    String getJsonStringYandex(String ex) throws IOException {
        String requestUrl = url + apiKey + "&lang=" + this.lang + "&text=" + ex;
        URL url = new URL(requestUrl);
        HttpURLConnection httpConnection = (HttpURLConnection)url.openConnection();
        httpConnection.connect();
        int rc = httpConnection.getResponseCode();
        if (rc != 200) {
            return "beard";
        } else {
            BufferedReader buffReader = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));
            StringBuilder strBuilder = new StringBuilder();

            String line;
            while((line = buffReader.readLine()) != null) {
                strBuilder.append(line).append('\n');
            }

            return this.getTranslateFromJSON(strBuilder.toString());
        }
    }

    String getTranslateFromJSON(String str) {
        JSONParser parser = new JSONParser();
        JSONObject object = null;

        try {
            object = (JSONObject)parser.parse(str);
        } catch (ParseException var8) {
            var8.printStackTrace();
        }

        StringBuilder sb = new StringBuilder();

        assert object != null;

        JSONArray array = (JSONArray)object.get("text");

        for (Object s : array) {
            sb.append(s.toString()).append("\n");
        }

        return sb.toString();
    }
}


