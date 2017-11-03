package correia.felipe.cortana_app.Connection;

/**
 * Created by Felipe on 28/10/2017.
 */

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import correia.felipe.cortana_app.models.People;
import correia.felipe.cortana_app.models.Response;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class LoadJSONTask{
//public class LoadJSONTask extends AsyncTask<String, Void, Response> {
//
//public LoadJSONTask(Listener listener) {
//
//mListener = listener;
//}
//
//public interface Listener {
//
//void onLoaded(List<People> peopleList);
//
//void onError();
//}
//
//private Listener mListener;
//
//@Override
//protected Response doInBackground(String... strings) {
//try {
//
//String stringResponse = loadJSON(strings[0]);
//Gson gson = new Gson();
//
//return gson.fromJson(stringResponse, Response.class);
//
//// HttpClient httpclient = new DefaultHttpClient();
//// HttpGet httpGet = new HttpGet(params[0]);
////HttpResponse httpResponse = httpclient.execute(httpGet);
//
//
////String response = streamToString(httpResponse.getEntity().getContent());
////Log.d("doInBackground", "Response: " + response);
//} catch (IOException e) {
//e.printStackTrace();
//return null;
//} catch (JsonSyntaxException e) {
//e.printStackTrace();
//return null;
//}
//}
//
//@Override
//protected void onPostExecute(Response response) {
//
//if (response != null) {
//
//mListener.onLoaded(response.results);
//
//} else {
//
//mListener.onError();
//}
//}
//
//private String loadJSON(String jsonURL) throws IOException {
//
//URL url = new URL(jsonURL);
//HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//conn.setReadTimeout(10000);
//conn.setConnectTimeout(15000);
//conn.setRequestMethod("GET");
//conn.setDoInput(true);
//conn.connect();
//
//BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//String line;
//StringBuilder response = new StringBuilder();
//
//
//while ((line = in.readLine()) != null) {
//
//response.append(line);
//}
//
//in.close();
//Log.d("LOADJSON", "response: " + response);
//return response.toString();
//}
}
