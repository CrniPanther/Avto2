package crnipanter.avto;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Z1pp0 on 28.12.2014.
 */
public class OddajaOglasa extends Activity{

    Button oddaj;
    EditText naslov;
    EditText opis;
    EditText vin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.oddajaoglasa);
        naslov= (EditText) findViewById(R.id.NaslovOglasa);
        opis= (EditText) findViewById(R.id.OpisOglasa);
        vin= (EditText) findViewById(R.id.VIN);
        oddaj= (Button) findViewById(R.id.OddajOgl);

        oddaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}
