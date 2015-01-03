package crnipanter.avto;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Z1pp0 on 28.12.2014.
 */
public class OddajaOglasa extends Activity{

    Button oddaj;
    EditText znamka,model,vin,letnik;


    //Progress
    private ProgressDialog pDialog;

  JSONParser jsonParser = new JSONParser();

    //url do skripte
private static String url = "http://avto.host56.com/insert_car_letnik.php";

    //JSON Node names
    private static final String TAG_SUCCESS = "success";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.oddajaoglasa);

        //EditText
        znamka= (EditText) findViewById(R.id.NaslovOglasa);
        model= (EditText) findViewById(R.id.OpisOglasa);
        vin= (EditText) findViewById(R.id.VIN);
        letnik = (EditText) findViewById(R.id.letnik);

        //Create button
        oddaj= (Button) findViewById(R.id.OddajOgl);



        oddaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new OddajaOglasaa().execute();
            }
        });

    }

    class OddajaOglasaa extends AsyncTask<String, String, String> {



        protected void onPreExecute(){
            super.onPreExecute();
            pDialog = new ProgressDialog(crnipanter.avto.OddajaOglasa.this);
            pDialog.setMessage("Oddaja oglasa...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... args) {
            String insertnaslov = znamka.getText().toString();
            String insertopis = model.getText().toString();
            String insertVIN = vin.getText().toString();
            String insertLetnik = letnik.getText().toString();


            //Building parameters
            List<NameValuePair> params;
            params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("znamka", insertnaslov));
            params.add(new BasicNameValuePair("model", insertopis));
            params.add(new BasicNameValuePair("vin", insertVIN));
            params.add(new BasicNameValuePair("letnik", insertLetnik));


            //getting JSON object
            JSONObject json = jsonParser.makeHttpRequest(url,"GET",params);

            Log.d("Create response", json.toString());

            //check if everything OK
            try{
                int success = json.getInt(TAG_SUCCESS);

                if(success == 1){
                    Intent i = new Intent("android.intent.action.USPESNOODDAN");
                    startActivity(i);
                  finish();
                } else {
                    //failed to insert into table
                }
            } catch (JSONException e){
                e.printStackTrace();
            }

            return null;
        }

        private void onPostExecute(){
            pDialog.dismiss();
        }




    }
}
