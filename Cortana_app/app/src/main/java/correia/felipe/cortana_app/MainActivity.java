package correia.felipe.cortana_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class MainActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    private ZXingScannerView zXingScannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void scan(View view){
        zXingScannerView = new ZXingScannerView(getApplicationContext());
        setContentView(zXingScannerView);
        zXingScannerView.setResultHandler(this);
        zXingScannerView.startCamera();
    }

    @Override
    protected void onPause() {
        super.onPause();
        zXingScannerView.stopCamera();
    }

    @Override
    public void handleResult(Result result) {
        Toast.makeText(getApplicationContext(), result.getText(), Toast.LENGTH_SHORT).show();
        zXingScannerView.resumeCameraPreview(this);
        String api = result.getText();
        if(api.contains("people")) {
            Intent go = new Intent(MainActivity.this, Activity_People.class);
            go.putExtra("api", result.getText());
            startActivity(go);
        }else if(api.contains("planets")) {
            Intent go = new Intent(MainActivity.this, Activity_Planets.class);
            go.putExtra("api", result.getText());
            startActivity(go);
        } else if(api.contains("films")) {
            Intent go = new Intent(MainActivity.this, Activity_Film.class);
            go.putExtra("api", result.getText());
            startActivity(go);
        }else if(api.contains("species")) {
            Intent go = new Intent(MainActivity.this, Activity_Specie.class);
            go.putExtra("api", result.getText());
            startActivity(go);
        }else if(api.contains("vehicles")) {
            Intent go = new Intent(MainActivity.this, Activity_Vehicle.class);
            go.putExtra("api", result.getText());
            startActivity(go);
        }else if(api.contains("starships")) {
            Intent go = new Intent(MainActivity.this, Activity_Starship.class);
            go.putExtra("api", result.getText());
            startActivity(go);
        }
    }
}
