//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public abstract class RestService {
    private static String BASE_URL = "http://localhost:8080/webapp/api";

    public RestService() {
    }

    public abstract void run();

    protected String GET(String resource) {
        try {
            URL e = new URL(BASE_URL + resource);
            HttpURLConnection conn = (HttpURLConnection)e.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            if(conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder output = new StringBuilder();

            String line;
            while((line = br.readLine()) != null) {
                output.append(line);
            }

            conn.disconnect();
            return output.toString();
        } catch (MalformedURLException var7) {
            var7.printStackTrace();
        } catch (IOException var8) {
            var8.printStackTrace();
        }

        return "";
    }

    protected String POST(String resource, String input) {
        try {
            URL e = new URL(BASE_URL + resource);
            HttpURLConnection conn = (HttpURLConnection)e.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            OutputStream os = conn.getOutputStream();
            os.write(input.getBytes());
            os.flush();
            if(conn.getResponseCode() != 201) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder output = new StringBuilder();

            String line;
            while((line = br.readLine()) != null) {
                output.append(line);
            }

            conn.disconnect();
            return output.toString();
        } catch (MalformedURLException var9) {
            var9.printStackTrace();
        } catch (IOException var10) {
            var10.printStackTrace();
        }

        return "";
    }
}
