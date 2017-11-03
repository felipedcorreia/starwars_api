package correia.felipe.cortana_app.Connection;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import correia.felipe.cortana_app.models.People;

/**
 * Created by Felipe on 01/11/2017.
 */

public class Get_Name_API {

    public static String getName(String jsonURL) throws IOException {
        String server_response = null;
        String name = null;
        URL url;
        HttpURLConnection urlConnection = null;

        try {
            url = new URL(jsonURL);
            urlConnection = (HttpURLConnection) url.openConnection();

            int responseCode = urlConnection.getResponseCode();

            if(responseCode == HttpURLConnection.HTTP_OK){
                server_response = readStream(urlConnection.getInputStream());
                Log.d("HTTPCONNECTION", "Resposta: " + server_response);
                JSONObject jsonO = new JSONObject(server_response);
                name = jsonO.getString("name");
                Log.d("Parser", "JSONObject name: " + name);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return name;
    }

    private static String readStream(InputStream in) {
        BufferedReader reader = null;
        StringBuffer response = new StringBuffer();
        try {
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return response.toString();
    }
}

