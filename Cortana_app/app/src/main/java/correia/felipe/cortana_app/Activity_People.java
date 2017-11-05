package correia.felipe.cortana_app;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
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
import correia.felipe.cortana_app.models.People;

/**
 * Created by Felipe on 25/10/2017.
 */

public class Activity_People extends AppCompatActivity {

//    public static final String URL = "https://swapi.co/api/people/1";

    TextView tvname, tvheight, tvmass, tvhair_color, tvskin_color, tveye_color, tvbirth_year, tvgender,
             tvhome, tvcreated, tvedited;
    ListView lvfilms, lvspecies, lvvehicles, lvstarships;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);


        tvname = (TextView) findViewById(R.id.tv_name);
        tvheight = (TextView) findViewById(R.id.tv_height);
        tvmass = (TextView) findViewById(R.id.tv_mass);
        tvhair_color = (TextView) findViewById(R.id.tv_hair_color);
        tvskin_color = (TextView) findViewById(R.id.tv_skin_color);
        tveye_color = (TextView) findViewById(R.id.tv_eye_color);
        tvbirth_year = (TextView) findViewById(R.id.tv_birth_year);
        tvgender = (TextView) findViewById(R.id.tv_gender);
        tvcreated = (TextView) findViewById(R.id.tv_created);
        tvedited = (TextView) findViewById(R.id.tv_edited);
        tvhome = (TextView) findViewById(R.id.tv_homeworld);

        lvfilms = (ListView) findViewById(R.id.lv_films);
        lvspecies = (ListView) findViewById(R.id.lv_species);
        lvvehicles = (ListView) findViewById(R.id.lv_vehicles);
        lvstarships = (ListView) findViewById(R.id.lv_starships);

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

    private void populateList(String response) throws JSONException, IOException {
        JSONObject jsonO = new JSONObject(response);
        final ArrayList<String> items_films = new ArrayList<String>();
        final JSONArray films = jsonO.getJSONArray("films");
        Log.d("Parser", "JSONArray films: " + films);
        for(int i = 0; i< films.length(); i++){
            String json_data = films.getString(i);
            Log.d("Parser", "JSONArray films: " + json_data);
            String title = Get_Title_API.getTitle(json_data);
            Log.d("Parser", "JSONArray film_name: " + title);
            items_films.add(title);
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


        final ArrayAdapter<String> mArrayAdapter_films = new ArrayAdapter<String>(this,
                R.layout.list_item, items_films);
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
                lvfilms.setAdapter(mArrayAdapter_films);
                lvspecies.setAdapter(mArrayAdapter_species);
                lvvehicles.setAdapter(mArrayAdapter_vehicles);
                lvstarships.setAdapter(mArrayAdapter_starships);
            }
        });
    }


    private void populate(String response) throws JSONException, IOException {
        JSONObject jsonO = new JSONObject(response);

        final People item = new People();

        final String name = jsonO.getString("name");
        Log.d("Parser", "JSONObject name: " + name);
        final String height = jsonO.getString("height");
        Log.d("Parser", "JSONObject height: " + height);
        final String mass = jsonO.getString("mass");
        Log.d("Parser", "JSONObject mass: " + mass);
        final String hair_color = jsonO.getString("hair_color");
        Log.d("Parser", "JSONObject hair_color: " + hair_color);
        final String skin_color = jsonO.getString("skin_color");
        Log.d("Parser", "JSONObject skin_color: " + skin_color);
        final String eye_color = jsonO.getString("eye_color");
        Log.d("Parser", "JSONObject eye_color: " + eye_color);
        final String birth_year = jsonO.getString("birth_year");
        Log.d("Parser", "JSONObject birth_year: " + birth_year);
        final String gender = jsonO.getString("gender");
        Log.d("Parser", "JSONObject gender: " + gender);
        final String created = jsonO.getString("created");
        Log.d("Parser", "JSONObject created: " + created);
        final String edited = jsonO.getString("edited");
        Log.d("Parser", "JSONObject edited: " + edited);
        final String url = jsonO.getString("url");
        Log.d("Parser", "JSONObject url: " + url);
        String homeworld_url = jsonO.getString("homeworld");
        Log.d("Parser", "JSONObject home_url: " + homeworld_url);
        String home = Get_Name_API.getName(homeworld_url);
        Log.d("Parser", "JSONObject home_name: " + home);
        item.setName(name);
        item.setHeight(height);
        item.setMass(mass);
        item.setHairColor(hair_color);
        item.setSkinColor(skin_color);
        item.setEyeColor(eye_color);
        item.setBirthYear(birth_year);
        item.setGender(gender);
        item.setCreated(created);
        item.setEdited(edited);
        item.setUrl(url);
        item.homeWorldUrl = home;

        Handler mHandler = new Handler(Looper.getMainLooper());
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                Log.d("Parser thread", "THREAD");
                setPopulate(tvname.getText().toString(),item.getName(),tvname);
                setPopulate(tvheight.getText().toString(),item.getHeight(),tvheight);
                setPopulate(tvmass.getText().toString(),item.getMass(),tvmass);
                setPopulate(tvhair_color.getText().toString(),item.getHairColor(),tvhair_color);
                setPopulate(tvskin_color.getText().toString(),item.getSkinColor(),tvskin_color);
                setPopulate(tveye_color.getText().toString(),item.getEyeColor(),tveye_color);
                setPopulate(tvbirth_year.getText().toString(),item.getBirthYear(),tvbirth_year);
                setPopulate(tvgender.getText().toString(),item.getGender(),tvgender);
                setPopulate(tvhome.getText().toString(),item.getHomeWorldUrl(),tvhome);

            }
        });
    }

    private void setPopulate(String text_1, String text_2, TextView tvtxt){
        String now;
        now = text_1 + text_2;
        tvtxt.setText(now);
    }
}

