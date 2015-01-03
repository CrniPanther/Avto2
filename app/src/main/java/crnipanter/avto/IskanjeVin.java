package crnipanter.avto;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Z1pp0 on 28.12.2014.
 */
public class IskanjeVin extends Activity {

    Button Iskanje;
    EditText Napis;

    String vin;

    private ProgressDialog pDialog;

    JSONParser jsonParser = new JSONParser();

    private static final String url = "http://avto.host56.com/vin_novo.php";

    private static final String TAG_SUCCESS = "success";
    private static final String TAG_AVTO = "avto";
    private static final String TAG_ZNAMKA = "znamka";
    private static final String TAG_MODEL = "model";
    private static final String TAG_VIN = "vin";
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



    JSONArray avto = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.iskanje_vin);
        Iskanje = (Button) findViewById(R.id.IskanjeVIN);
        Napis = (EditText) findViewById(R.id.VpisVIN);

        Iskanje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new IsciVin().execute();
            }
        });
    }
            class IsciVin extends AsyncTask<String, String, String>{

        protected void onPreExecute(){
            super.onPreExecute();
            pDialog = new ProgressDialog(IskanjeVin.this);
            pDialog.setMessage("Iščem oglas...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String[] args) {
            String id = Napis.getText().toString();

            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("vin", id));

            JSONObject json = jsonParser.makeHttpRequest(url,"GET",params);

            Log.d("Response ", json.toString());

            try{
                int success = json.getInt(TAG_SUCCESS);

                if(success == 1){

                    avto = json.getJSONArray(TAG_AVTO);

                    JSONObject c = avto.getJSONObject(0);


                    String znamka = c.getString(TAG_ZNAMKA);
                    String model = c.getString(TAG_MODEL);
                    String vin = c.getString(TAG_VIN);
                    String letnik = c.getString(TAG_LETNIK);
                    String prenova = c.getString(TAG_PRENOVA);
                    String elektronika = c.getString(TAG_ELEKTRONIKA);
                    String okvare = c.getString(TAG_OKVARE);
                    String pomanjkljivosti = c.getString(TAG_POMAN);
                    String notranjost = c.getString(TAG_NOTRANJOST);
                    String karoserija = c.getString(TAG_KAROSERIJA);
                    String prednosti = c.getString(TAG_PREDNOSTI);
                    String slabosti = c.getString(TAG_SLABOSTI);
                    String vpoklici = c.getString(TAG_VPOKLICI);

                    Intent i = new Intent("android.intent.action.OGLEDVIN");
                    i.putExtra(TAG_ZNAMKA, znamka);
                    i.putExtra(TAG_MODEL, model);
                    i.putExtra(TAG_VIN, vin);
                    i.putExtra(TAG_LETNIK, letnik);
                    i.putExtra(TAG_PRENOVA, prenova);
                    i.putExtra(TAG_ELEKTRONIKA, elektronika);
                    i.putExtra(TAG_OKVARE, okvare);
                    i.putExtra(TAG_POMAN, pomanjkljivosti);
                    i.putExtra(TAG_NOTRANJOST, notranjost);
                    i.putExtra(TAG_KAROSERIJA,karoserija);
                    i.putExtra(TAG_PREDNOSTI,prednosti);
                    i.putExtra(TAG_SLABOSTI,slabosti);
                    i.putExtra(TAG_VPOKLICI,vpoklici);
                    startActivity(i);
                    finish();

                }else {
                    Intent i = new Intent("android.intent.action.NOTFOUND");
                    startActivity(i);
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


