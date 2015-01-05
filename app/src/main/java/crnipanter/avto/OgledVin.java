package crnipanter.avto;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tomaz on 30.12.2014.
 */
public class OgledVin extends Activity {
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_AVTO = "avto";

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
    private static final String TAG_IDOGLASA = "idOglas";
    private static String idoglas = "";

    private ProgressDialog pDialog;
    private static final String url = "http://avto.host56.com/DodajKomentar.php";
    JSONParser jsonParser = new JSONParser();
    JSONArray avto = null;
    TextView Naslov;
    TextView Opis;
    TextView izpisVIN;
    TextView KriticneTocke;
    TextView ZgodovinaProdaje;
    TextView Komentarji;
    TextView Letnik;
    TextView PrikazKomentarjev;
    Button DodajKomentar;
    TextView NapisKomentar;

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
        DodajKomentar = (Button) findViewById(R.id.DodajKomentar);
        NapisKomentar = (TextView) findViewById(R.id.NapisKomentar);

        Intent i = getIntent();

        idoglas=i.getStringExtra(TAG_IDOGLASA);

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

            DodajKomentar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   if(NapisKomentar.getText().toString()!="") {
                       new DodajKoment().execute();
                        NapisKomentar.setText("");
                       
                   }
                }
            });
    }
    class DodajKoment extends AsyncTask<String, String, String> {

        protected void onPreExecute(){
            super.onPreExecute();
            pDialog = new ProgressDialog(OgledVin.this);
            pDialog.setMessage("Dodajam Komentar...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String[] args) {
            String id = NapisKomentar.getText().toString();

            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("komentar", id));
            params.add(new BasicNameValuePair("idoglasa", idoglas));

            JSONObject json = jsonParser.makeHttpRequest(url,"GET",params);

            Log.d("Response ", json.toString());

            try{
                int success = json.getInt(TAG_SUCCESS);

                if(success == 1){
                    pDialog.dismiss();

                }else {

                }
            }catch(JSONException e){
                e.printStackTrace();
            }
            return null;
        }
        private void onPostExecute(){
            pDialog.dismiss();
        }
    }



}