package crnipanter.avto;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
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
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Z1pp0 on 28.12.2014.
 */
public class Prijava extends Activity {
    EditText UpIme;
    EditText Geslo;
    Button Prijava;
    private static final String TAG_SUCCESS = "success";
    private ProgressDialog pDialog;

    JSONParser jsonParser = new JSONParser();

    private static final String url = "http://avto.host56.com/prijava.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prijava);
        UpIme = (EditText) findViewById(R.id.UpIme);
        Geslo = (EditText) findViewById(R.id.Geslo);
        Prijava = (Button) findViewById(R.id.Prijava);

        Prijava.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new prijava().execute();
            }
        });
    }
        class prijava extends AsyncTask<String, String, String> {

            protected void onPreExecute(){
                super.onPreExecute();
                pDialog = new ProgressDialog(Prijava.this);
                pDialog.setMessage("Prijava v teku..");
                pDialog.setIndeterminate(false);
                pDialog.setCancelable(true);
                pDialog.show();
            }

            @Override
            protected String doInBackground(String[] args) {
                String Upim = UpIme.getText().toString();
                String UpGeslo = Geslo.getText().toString();

                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("UpIme", Upim));
                params.add(new BasicNameValuePair("UpGeslo", UpGeslo));

                JSONObject json = jsonParser.makeHttpRequest(url,"GET",params);

                Log.d("Response ", json.toString());

                try{
                    int success = json.getInt(TAG_SUCCESS);

                    if(success == 1){
                        pDialog.dismiss();

                    }else {
                        pDialog.dismiss();
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