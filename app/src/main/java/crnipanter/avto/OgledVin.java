package crnipanter.avto;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Tomaz on 30.12.2014.
 */
public class OgledVin extends Activity {

    private static final String TAG_VIN = "vin";
    private static final String TAG_MODEL = "model";
    private static final String TAG_ZNAMKA = "znamka";
    private static final String TAG_LETNIK = "letnik";
    private static final String TAG_PRENOVA = "prenova";
    private static final String TAG_OKVARE = "okvare";
    private static final String TAG_POMAN = "pomanjkljivosti";
    private static final String TAG_KAROSERIJA = "karoserija";
    private static final String TAG_NOTRANJOST = "notranjost";
    private static final String TAG_ELEKTRONIKA = "elektronika";
    private static final String TAG_VPOKLICI = "vpoklici";
    private static final String TAG_PREDNOSTI = "prednosti";
    private static final String TAG_SLABOSTI = "slabosti";
    TextView Naslov;
    TextView Opis;
    TextView izpisVIN;
    TextView KriticneTocke;
    TextView ZgodovinaProdaje;
    TextView Komentarji;
    TextView Letnik;
    TextView PrikazKomentarjev;

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
        Letnik = (TextView) findViewById(R.id.ogledLetnik);
        PrikazKomentarjev=(TextView) findViewById(R.id.PrikazKomentarjev);

        Intent i = getIntent();


        String passedArg = i.getStringExtra(TAG_ZNAMKA);
        Naslov.setText(passedArg);

        passedArg = i.getStringExtra(TAG_VIN);
        izpisVIN.setText(passedArg);



        passedArg = i.getStringExtra(TAG_MODEL);
        Opis.setText(passedArg);

        passedArg = i.getStringExtra(TAG_LETNIK);
        Letnik.setText(passedArg);


        passedArg =
                "<b><br>Prenova: </b>" + i.getStringExtra(TAG_PRENOVA) +
                        "<b><br>Okvare: </b>" + i.getStringExtra(TAG_OKVARE) +
                        "<b><br>Pomanjkljivosti: </b>" + i.getStringExtra(TAG_POMAN) +
                        "<b><br>Karoserija: </b>" + i.getStringExtra(TAG_KAROSERIJA) +
                        "<b><br>Notranjost: </b>" + i.getStringExtra(TAG_NOTRANJOST) +
                        "<b><br>Elektronika: </b>" + i.getStringExtra(TAG_ELEKTRONIKA) +
                        "<b><br>Vpoklici: </b>" + i.getStringExtra(TAG_VPOKLICI) +
                        "<b><br>Prednosti: </b>" + i.getStringExtra(TAG_PREDNOSTI) +
                        "<b><br>Slabosti: </b>" + i.getStringExtra(TAG_SLABOSTI);
        KriticneTocke.setText(Html.fromHtml(passedArg));



    }
}