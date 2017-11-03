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
import correia.felipe.cortana_app.models.Planet;

public class Activity_Planets extends AppCompatActivity {
//    public static final String URL = "https://swapi.co/api/planets/1";

    TextView tvname, tvrotation, tvorbital, tvdiameter, tvclimate, tvgravity, tvterrain,
            tvsurface, tvpopulation;
    ListView lvresidents, lvfilms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planets);

        tvname = (TextView) findViewById(R.id.tv_name_planet);
        tvrotation = (TextView) findViewById(R.id.tv_rotation);
        tvorbital = (TextView) findViewById(R.id.tv_orbital);
        tvdiameter = (TextView) findViewById(R.id.tv_diameter);
        tvclimate = (TextView) findViewById(R.id.tv_climate);
        tvgravity = (TextView) findViewById(R.id.tv_gravity);
        tvterrain = (TextView) findViewById(R.id.tv_terrain);
        tvsurface = (TextView) findViewById(R.id.tv_surface);
        tvpopulation = (TextView) findViewById(R.id.tv_population);

        lvresidents = (ListView) findViewById(R.id.lv_residents);
        lvfilms = (ListView) findViewById(R.id.lv_films_planet);

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

        final Planet item = new Planet();

        final String name = jsonO.getString("name");
        Log.d("Parser", "JSONObject name: " + name);
        final String rotation = jsonO.getString("rotation_period");
        Log.d("Parser", "JSONObject rotation period: " + rotation);
        final String orbital = jsonO.getString("orbital_period");
        Log.d("Parser", "JSONObject orbital_period: " + orbital);
        final String diameter = jsonO.getString("diameter");
        Log.d("Parser", "JSONObject diameter: " + diameter);
        final String climate = jsonO.getString("climate");
        Log.d("Parser", "JSONObject climate: " + climate);
        final String gravity = jsonO.getString("gravity");
        Log.d("Parser", "JSONObject gravity: " + gravity);
        final String terrain = jsonO.getString("terrain");
        Log.d("Parser", "JSONObject terrain: " + terrain);
        final String surface = jsonO.getString("surface_water");
        Log.d("Parser", "JSONObject surface_water: " + surface);
        final String population = jsonO.getString("population");
        Log.d("Parser", "JSONObject population: " + population);

        item.setName(name);
        item.setRotationPeriod(rotation);
        item.setOrbitalPeriod(orbital);
        item.setDiameter(diameter);
        item.setClimate(climate);
        item.setGravity(gravity);
        item.setTerrain(terrain);
        item.setSurfaceWater(surface);
        item.setPopulation(population);

        Handler mHandler = new Handler(Looper.getMainLooper());
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                Log.d("Parser thread", "THREAD");
                setPopulate(tvname.getText().toString(),item.getName(),tvname);
                setPopulate(tvrotation.getText().toString(),item.getRotationPeriod(),tvrotation);
                setPopulate(tvorbital.getText().toString(),item.getOrbitalPeriod(),tvorbital);
                setPopulate(tvdiameter.getText().toString(),item.getDiameter(),tvdiameter);
                setPopulate(tvclimate.getText().toString(),item.getClimate(),tvclimate);
                setPopulate(tvgravity.getText().toString(),item.getGravity(),tvgravity);
                setPopulate(tvterrain.getText().toString(),item.getTerrain(),tvterrain);
                setPopulate(tvsurface.getText().toString(),item.getSurfaceWater(),tvsurface);
                setPopulate(tvpopulation.getText().toString(),item.getPopulation(),tvpopulation);

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
        final ArrayList<String> items_residents = new ArrayList<String>();
        final JSONArray residents = jsonO.getJSONArray("residents");
        Log.d("Parser", "JSONArray residents: " + residents);
        for(int i = 0; i< residents.length(); i++){
            String json_data = residents.getString(i);
            Log.d("Parser", "JSONArray residents: " + json_data);
            String name = Get_Name_API.getName(json_data);
            Log.d("Parser", "JSONArray residents_name: " + name);
            items_residents.add(name);
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



        final ArrayAdapter<String> mArrayAdapter_residents = new ArrayAdapter<String>(this,
                R.layout.list_item, items_residents);
        final ArrayAdapter<String> mArrayAdapter_films = new ArrayAdapter<String>(this,
                R.layout.list_item, items_films);

        Handler mHandler = new Handler(Looper.getMainLooper());
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                lvresidents.setAdapter(mArrayAdapter_residents);
                lvfilms.setAdapter(mArrayAdapter_films);
            }
        });
    }
}
