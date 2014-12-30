package crnipanter.avto;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Tomaz on 30.12.2014.
 */
public class OgledVin extends Activity {

    private static final String TAG_VIN = "vin";
    private static final String TAG_OPIS = "opis";
    private static final String TAG_NASLOV = "naslov";
    TextView Naslov;
    TextView Opis;
    TextView izpisVIN;
    TextView KriticneTocke;
    TextView ZgodovinaProdaje;
    TextView Komentarji;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.izpis_podatkov_vin);
        Naslov = (TextView) findViewById(R.id.Naslov);
        Opis = (TextView) findViewById(R.id.Opis);
        izpisVIN = (TextView) findViewById(R.id.izpisVIN);
        KriticneTocke = (TextView) findViewById(R.id.KriticneTocke);
        ZgodovinaProdaje = (TextView) findViewById(R.id.ZgodovinaProdaje);
        Komentarji = (TextView) findViewById(R.id.Komentarji);

        Intent i = getIntent();


        String passedArg = i.getStringExtra(TAG_NASLOV);
        Naslov.setText(passedArg);

        passedArg = i.getStringExtra(TAG_VIN);
        izpisVIN.setText(passedArg);

        passedArg = i.getStringExtra(TAG_NASLOV);
        Naslov.setText(passedArg);

        passedArg = i.getStringExtra(TAG_OPIS);
       Opis.setText(passedArg);


    }
}