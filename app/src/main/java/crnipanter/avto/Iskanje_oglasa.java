package crnipanter.avto;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * Created by Z1pp0 on 28.12.2014.
 */
public class Iskanje_oglasa extends Activity {
    Button isci;
    EditText znamka,model,letnik;
    Spinner znamka1,model1,letnik1;

    private static String url = "http://avto.host56.com/iskanje_oglasa.php";
    private static String getZnamka = "http://avto.host56.com/getZnamka.php";
    // Progress Dialog
    private ProgressDialog pDialog;
    // Creating JSON Parser object
    JSONParser jParser = new JSONParser();
    ArrayList<HashMap<String, String>> productsList;
    // url to get all products list


    JSONArray avto = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.iskanje_oglasa);


        znamka1 = (Spinner)findViewById(R.id.znamka);
        String[] items = new String[]{"1", "2", "three"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items);
        znamka1.setAdapter(adapter);

        model1 = (Spinner)findViewById(R.id.model);
        String[] it = new String[]{"1", "2", "three"};
        ArrayAdapter<String> adap = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items);
        model1.setAdapter(adapter);

        letnik1 = (Spinner)findViewById(R.id.letnik);
        String[] i = new String[]{"1", "2", "three"};
        ArrayAdapter<String> ada = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items);
        letnik1.setAdapter(adapter);
        //Create button
        isci= (Button) findViewById(R.id.IskanjeOglasaGumb);





    /*
     isci.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Iskanje_Oglasaa().execute();
            }
        });
    */

    }




    class Iskanje_Oglasaa extends AsyncTask<String, String, String> {


        @Override
        protected String doInBackground(String... params) {
            return null;
        }

        protected void onPreExecute(){
            super.onPreExecute();
            pDialog = new ProgressDialog(crnipanter.avto.Iskanje_oglasa.this);
            pDialog.setMessage("Iskanje oglasa...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }


    }


}