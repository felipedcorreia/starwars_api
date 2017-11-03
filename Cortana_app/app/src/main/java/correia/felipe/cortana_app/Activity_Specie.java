package correia.felipe.cortana_app;

import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import correia.felipe.cortana_app.Connection.Get_Name_API;
import correia.felipe.cortana_app.Connection.Get_Title_API;
import correia.felipe.cortana_app.Connection.HttpConnection;
import correia.felipe.cortana_app.models.Film;
import correia.felipe.cortana_app.models.Specie;

public class Activity_Specie extends AppCompatActivity {

//    public static final String URL = "https://swapi.co/api/species/1/";

    TextView tvname, tvclassification, tvdesignation, tvaverage_height, tvskin_color,
             tvhair_color, tveye_color, tvaverage_lifespan, tvhomeworld, tvlanguage;
    ListView lvpeople, lvfilm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_species);

        tvname = (TextView) findViewById(R.id.tv_name_specie);
        tvclassification = (TextView) findViewById(R.id.tv_classification);
        tvdesignation = (TextView) findViewById(R.id.tv_designation);
        tvaverage_height = (TextView) findViewById(R.id.tv_average_height);
        tvskin_color = (TextView) findViewById(R.id.tv_skin_colors_specie);
        tvhair_color = (TextView) findViewById(R.id.tv_hair_colors_specie);
        tveye_color = (TextView) findViewById(R.id.tv_eye_colors_specie);
        tvaverage_lifespan = (TextView) findViewById(R.id.tv_average_lifespan);
        tvhomeworld = (TextView) findViewById(R.id.tv_homeworld_specie);
        tvlanguage = (TextView) findViewById(R.id.tv_language);

        lvpeople = (ListView) findViewById(R.id.lv_people_specie);
        lvfilm = (ListView) findViewById(R.id.lv_films_specie);

        final String URL = getIntent().getStringExtra("api");

        new Thread() {
            public void run() {
                try {
                    String response = HttpConnection.getSetDataWeb(URL);
                    Log.d("ACTIVITY", "Resposta: " + response);
                    populate(response);

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        new Thread() {
            public void run() {
                try {
                    String response = HttpConnection.getSetDataWeb(URL);
                    Log.d("ACTIVITY", "Resposta: " + response);
                    populateList(response);

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private void populate(String response) throws JSONException, IOException {
        JSONObject jsonO = new JSONObject(response);

        final Specie item = new Specie();

        final String name = jsonO.getString("name");
        Log.d("Parser", "JSONObject name: " + name);
        final String classification = jsonO.getString("classification");
        Log.d("Parser", "JSONObject classification: " + classification);
        final String designation = jsonO.getString("designation");
        Log.d("Parser", "JSONObject designation: " + designation);
        final String average_height = jsonO.getString("average_height");
        Log.d("Parser", "JSONObject average height: " + average_height);
        final String skin_colors = jsonO.getString("skin_colors");
        Log.d("Parser", "JSONObject skin_colors: " + skin_colors);
        final String hair_colors = jsonO.getString("hair_colors");
        Log.d("Parser", "JSONObject hair_colors: " + hair_colors);
        final String eye_colors = jsonO.getString("eye_colors");
        Log.d("Parser", "JSONObject eye_colors: " + eye_colors);
        final String average_lifespan = jsonO.getString("average_lifespan");
        Log.d("Parser", "JSONObject average_lifespan: " + average_lifespan);
        String homeworld_url = jsonO.getString("homeworld");
        Log.d("Parser", "JSONObject home_url: " + homeworld_url);
        String home = Get_Name_API.getName(homeworld_url);
        Log.d("Parser", "JSONObject home_name: " + home);
        final String language = jsonO.getString("language");
        Log.d("Parser", "JSONObject language: " + language);



        item.setName(name);
        item.setClassification(classification);
        item.setDesignation(designation);
        item.setavArage_height(average_height);
        item.setSkin_color(skin_colors);
        item.setHair_color(hair_colors);
        item.setEye_color(eye_colors);
        item.setAverage_lifespan(average_lifespan);
        item.setHomeworld(home);
        item.setLanguage(language);



        Handler mHandler = new Handler(Looper.getMainLooper());
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                Log.d("Parser thread", "THREAD");
                setPopulate(tvname.getText().toString(),item.getName(),tvname);
                setPopulate(tvclassification.getText().toString(),item.getClassification(),tvclassification);
                setPopulate(tvdesignation.getText().toString(),item.getDesignation(),tvdesignation);
                setPopulate(tvaverage_height.getText().toString(),item.getavArage_height(),tvaverage_height);
                setPopulate(tvskin_color.getText().toString(),item.getSkin_color(),tvskin_color);
                setPopulate(tvhair_color.getText().toString(),item.getHair_color(),tvhair_color);
                setPopulate(tveye_color.getText().toString(),item.getEye_color(),tveye_color);
                setPopulate(tvaverage_lifespan.getText().toString(),item.getAverage_lifespan(),tvaverage_lifespan);
                setPopulate(tvhomeworld.getText().toString(),item.getHomeworld(),tvhomeworld);
                setPopulate(tvlanguage.getText().toString(),item.getLanguage(),tvlanguage);


            }
        });
    }

    private void setPopulate(String text_1, String text_2, TextView tvtxt){
        String now;
        now = text_1 + text_2;
        tvtxt.setText(now);
    }

    private void populateList(String response) throws JSONException, IOException {
        JSONObject jsonO = new JSONObject(response);
        final ArrayList<String> items_peoples = new ArrayList<String>();
        final JSONArray peoples = jsonO.getJSONArray("people");
        Log.d("Parser", "JSONArray peoples: " + peoples);
        for(int i = 0; i< peoples.length(); i++){
            String json_data = peoples.getString(i);
            Log.d("Parser", "JSONArray peoples: " + json_data);
            String name = Get_Name_API.getName(json_data);
            Log.d("Parser", "JSONArray peoples_name: " + name);
            items_peoples.add(name);
        }

        final ArrayList<String> items_films = new ArrayList<String>();
        final JSONArray films = jsonO.getJSONArray("films");
        Log.d("Parser", "JSONArray films: " + films);
        for(int i = 0; i< films.length(); i++){
            String json_data = films.getString(i);
            Log.d("Parser", "JSONArray films: " + json_data);
            String title = Get_Title_API.getTitle(json_data);
            Log.d("Parser", "JSONArray films_title: " + title);
            items_films.add(title);
        }



        final ArrayAdapter<String> mArrayAdapter_peoples = new ArrayAdapter<String>(this,
                R.layout.list_item, items_peoples);
        final ArrayAdapter<String> mArrayAdapter_films = new ArrayAdapter<String>(this,
                R.layout.list_item, items_films);


        Handler mHandler = new Handler(Looper.getMainLooper());
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                lvpeople.setAdapter(mArrayAdapter_peoples);
                lvfilm.setAdapter(mArrayAdapter_films);

            }
        });
    }
}
