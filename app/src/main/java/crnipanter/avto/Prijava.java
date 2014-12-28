package crnipanter.avto;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by Z1pp0 on 28.12.2014.
 */
public class Prijava extends Activity {
    EditText UpIme;
    EditText Geslo;
    Button Prijava;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prijava);
        UpIme= (EditText) findViewById(R.id.UpIme);
        Geslo= (EditText) findViewById(R.id.Geslo);
        Prijava= (Button) findViewById(R.id.Prijava);

        Prijava.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //čekiraj bazo
            }
        });

    }
}

