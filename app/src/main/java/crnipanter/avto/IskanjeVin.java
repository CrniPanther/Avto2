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

    private static final String url = "http://192.168.64.100/vin.php";

    private static final String TAG_SUCCESS = "success";
    private static final String TAG_AVTO = "avto";
    private static final String TAG_NASLOV = "naslov";
    private static final String TAG_OPIS = "opis";
    private static final String TAG_VIN = "vin";

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

           // Log.d("Response ", json.toString());

            try{
                int success = json.getInt(TAG_SUCCESS);

                if(success == 1){

                    avto = json.getJSONArray(TAG_AVTO);

                    JSONObject c = avto.getJSONObject(0);


                    String naslov = c.getString(TAG_NASLOV);
                    String opis = c.getString(TAG_OPIS);
                    String vin = c.getString(TAG_VIN);

                    Intent i = new Intent("android.intent.action.OGLEDVIN");
                    i.putExtra(TAG_NASLOV, naslov);
                    i.putExtra(TAG_OPIS, opis);
                    i.putExtra(TAG_VIN, vin);
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


