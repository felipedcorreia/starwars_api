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
import correia.felipe.cortana_app.models.People;

public class Activity_Film extends AppCompatActivity {

//    public static final String URL = "https://swapi.co/api/films/1/";

    TextView tvtitle, tvepisode, tvdirector, tvproducer, tvrelease;
    ListView lvcharacters, lvplanets, lvstarships, lvvehicles, lvspecies;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film);


        tvtitle = (TextView) findViewById(R.id.tv_title);
        tvepisode = (TextView) findViewById(R.id.tv_episode);
        tvdirector = (TextView) findViewById(R.id.tv_director);
        tvproducer = (TextView) findViewById(R.id.tv_producer);
        tvrelease = (TextView) findViewById(R.id.tv_release);

        lvcharacters = (ListView) findViewById(R.id.lv_characters);
        lvplanets = (ListView) findViewById(R.id.lv_planets_films);
        lvstarships = (ListView) findViewById(R.id.lv_starships_films);
        lvvehicles = (ListView) findViewById(R.id.lv_vehicles_films);
        lvspecies = (ListView) findViewById(R.id.lv_species_films);

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

        final Film item = new Film();

        final String title = jsonO.getString("title");
        Log.d("Parser", "JSONObject title: " + title);
        final String episode = jsonO.getString("episode_id");
        Log.d("Parser", "JSONObject episode id: " + episode);
        final String director = jsonO.getString("director");
        Log.d("Parser", "JSONObject director: " + director);
        final String producer = jsonO.getString("producer");
        Log.d("Parser", "JSONObject producer: " + producer);
        final String release = jsonO.getString("release_date");
        Log.d("Parser", "JSONObject release_date: " + release);

        item.setTitle(title);
        item.setEpisodeId(episode);
        item.setDirector(director);
        item.setProducer(producer);
        item.setRelease(release);


        Handler mHandler = new Handler(Looper.getMainLooper());
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                Log.d("Parser thread", "THREAD");
                setPopulate(tvtitle.getText().toString(),item.getTitle(),tvtitle);
                setPopulate(tvepisode.getText().toString(),item.getEpisodeId(),tvepisode);
                setPopulate(tvdirector.getText().toString(),item.getDirector(),tvdirector);
                setPopulate(tvproducer.getText().toString(),item.getProducer(),tvproducer);
                setPopulate(tvrelease.getText().toString(),item.getRelease(),tvrelease);
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
        final ArrayList<String> items_characters = new ArrayList<String>();
        final JSONArray characters = jsonO.getJSONArray("characters");
        Log.d("Parser", "JSONArray characters: " + characters);
        for(int i = 0; i< characters.length(); i++){
            String json_data = characters.getString(i);
            Log.d("Parser", "JSONArray characters: " + json_data);
            String name = Get_Name_API.getName(json_data);
            Log.d("Parser", "JSONArray characters_name: " + name);
            items_characters.add(name);
        }

        final ArrayList<String> items_planets = new ArrayList<String>();
        final JSONArray planets = jsonO.getJSONArray("planets");
        Log.d("Parser", "JSONArray planets: " + planets);
        for(int i = 0; i< planets.length(); i++){
            String json_data = planets.getString(i);
            Log.d("Parser", "JSONArray planets: " + json_data);
            String name = Get_Name_API.getName(json_data);
            Log.d("Parser", "JSONArray planets_name: " + name);
            items_planets.add(name);
        }

        final ArrayList<String> items_species = new ArrayList<String>();
        final JSONArray species = jsonO.getJSONArray("species");
        Log.d("Parser", "JSONArray films: " + species);
        for(int i = 0; i< species.length(); i++){
            String json_data = species.getString(i);
            Log.d("Parser", "JSONArray species: " + json_data);
            String name = Get_Name_API.getName(json_data);
            Log.d("Parser", "JSONArray species_name: " + name);
            items_species.add(name);
        }

        final ArrayList<String> items_vehicles = new ArrayList<String>();
        final JSONArray vehicles = jsonO.getJSONArray("vehicles");
        Log.d("Parser", "JSONArray films: " + vehicles);
        for(int i = 0; i< vehicles.length(); i++){
            String json_data = vehicles.getString(i);
            Log.d("Parser", "JSONArray vehicles: " + json_data);
            String name = Get_Name_API.getName(json_data);
            Log.d("Parser", "JSONArray vehicles_name: " + name);
            items_vehicles.add(name);
        }

        final ArrayList<String> items_starchips = new ArrayList<String>();
        final JSONArray starchips = jsonO.getJSONArray("starships");
        Log.d("Parser", "JSONArray starchips: " + starchips);
        for(int i = 0; i< starchips.length(); i++){
            String json_data = starchips.getString(i);
            Log.d("Parser", "JSONArray starchips: " + json_data);
            String name = Get_Name_API.getName(json_data);
            Log.d("Parser", "JSONArray starchips_name: " + name);
            items_starchips.add(name);
        }


        final ArrayAdapter<String> mArrayAdapter_characters = new ArrayAdapter<String>(this,
                R.layout.list_item, items_characters);
        final ArrayAdapter<String> mArrayAdapter_planets = new ArrayAdapter<String>(this,
                R.layout.list_item, items_planets);
        final ArrayAdapter<String> mArrayAdapter_species = new ArrayAdapter<String>(this,
                R.layout.list_item, items_species);
        final ArrayAdapter<String> mArrayAdapter_vehicles = new ArrayAdapter<String>(this,
                R.layout.list_item, items_vehicles);
        final ArrayAdapter<String> mArrayAdapter_starships = new ArrayAdapter<String>(this,
                R.layout.list_item, items_starchips);

        Handler mHandler = new Handler(Looper.getMainLooper());
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                lvcharacters.setAdapter(mArrayAdapter_characters);
                lvplanets.setAdapter(mArrayAdapter_planets);
                lvspecies.setAdapter(mArrayAdapter_species);
                lvvehicles.setAdapter(mArrayAdapter_vehicles);
                lvstarships.setAdapter(mArrayAdapter_starships);
            }
        });
    }
}

