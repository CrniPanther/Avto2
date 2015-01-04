package crnipanter.avto;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
    private static final String TAG_UPKOMENTAR = "idUporabnik";
    private static final String TAG_KOMENTAR = "komentar";
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
        String upk =i.getStringExtra(TAG_UPKOMENTAR);
        String upkom =i.getStringExtra(TAG_KOMENTAR);
        String up = "";
        int kjeJeOstal=0;
     glavna:   for(int a=0;a<upkom.length();a++){
           if(a==0){
             notranja:  for (int l=0;l<upk.length();l++){
                 up = up+upk.charAt(l);
                   if(upk.charAt(l)==' ') {
                       passedArg= "<br><b>"+up+": ";
                       kjeJeOstal=l+1;
                       break notranja;
                   }
               }
           }

           if(upkom.charAt(a)=='&'&&upkom.charAt(a+1)=='%'&&upkom.charAt(a+2)=='d'&&upkom.charAt(a+3)=='r'&&upkom.charAt(a+4)=='%'&&upkom.charAt(a+5)=='&' ){
                passedArg=passedArg+"<br><br>";
               if(a+6==upkom.length()) break glavna;
               a=a+6;
               druga: for(int j=kjeJeOstal;kjeJeOstal<upk.length();j++){
                   passedArg=passedArg+upk.charAt(j);
                   if(upk.charAt(j)==' ') {
                       passedArg= passedArg+": ";
                       kjeJeOstal=j+1;
                       break druga;
                   }
               }
           }
            passedArg= passedArg + upkom.charAt(a);
        }


        PrikazKomentarjev.setText(Html.fromHtml(passedArg));



    }
}