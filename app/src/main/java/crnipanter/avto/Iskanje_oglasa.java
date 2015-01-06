package crnipanter.avto;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * Created by Z1pp0 on 28.12.2014.
 */
public class Iskanje_oglasa extends Activity {


    private static final String TAG_SUCCESS = "success";
    private static final String TAG_ZNAMKA = "znamka";
    private static final String TAG_MODEL = "model";
    private static final String TAG_AVTO = "avto";
    private static final String TAG_OD = "od";
    private static final String TAG_DO = "do";
    int letos = 2015;
    Button isci;
    TextView znamka,model,letnikod, letnikdo;
    Spinner znamka1,model1,letnik1, letnik2;

    private static String url = "http://avto.host56.com/iskanje_oglasa.php";
    private static String urlZnamka = "http://avto.host56.com/getZnamka.php";
    private static String urlModel = "http://avto.host56.com/getModel.php";
    String isciTo = "";
    ArrayAdapter<String> adapter;
    String [] items,b, bb;
    String[] models = {"-- izberite znamko --"};

    int a, aa;
    JSONArray products = null;



    // Creating JSON Parser object
    JSONParser jParser = new JSONParser();
    ArrayList<String> znamkeList;
    boolean t=true;
    boolean tt=true;
    boolean onCreateBool = true;



    class LoadAllProducts extends AsyncTask<String, String, String> {

        protected String doInBackground(String... args) {
            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            // getting JSON string from URL
            params.add(new BasicNameValuePair("znamka", getIntent().getStringExtra(TAG_ZNAMKA)));

            JSONObject json = jParser.makeHttpRequest(urlZnamka, "GET", params);

            // Check your log cat for JSON reponse
            Log.d("All Products: ", json.toString());

            try {
                // Checking for SUCCESS TAG
                int success = json.getInt(TAG_SUCCESS);

                if (success == 1) {
                    // products found
                    // Getting Array of Products
                    products = json.getJSONArray(TAG_AVTO);

                    items=new String[products.length()];
                    a=products.length();

                    // looping through All Products
                    for (int i = 0; i < products.length(); i++) {
                        JSONObject c = products.getJSONObject(i);

                        // Storing each json item in variable
                        items[i] = c.getString(TAG_ZNAMKA);




                        // adding HashList to ArrayList
                        //znamkeList.add(name);
                    }

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            t=false;
            return null;
        }







    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.iskanje_oglasa);

        znamka = (TextView) findViewById(R.id.textZnamka);
        model = (TextView) findViewById(R.id.textModel);
        letnikod = (TextView) findViewById(R.id.textLetnik);
        letnikdo = (TextView) findViewById(R.id.textLetnikDo);

        znamka1 = (Spinner) findViewById(R.id.znamka);





        new LoadAllProducts().execute();

        while(t){}


          adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items);
            znamka1.setAdapter(adapter);

        znamka1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

               if(!onCreateBool) {
                   isciTo = znamka1.getSelectedItem().toString();
                   System.out.println("Isci to:" + isciTo);
                   System.out.println("Klicem execute:" + isciTo);
                   new LoadAllModels().execute();
                   System.out.println("Konec execute, start while");
                   while (tt) {
                   }
                   tt = true;
                   //System.out.println("konec while");
               } else {onCreateBool = false;}
               // System.out.println("Zdej bom naredu adapter");
                adapter = new ArrayAdapter<String>(Iskanje_oglasa.this, android.R.layout.simple_spinner_item, models);
             //   System.out.println("Zdej bom spremenu modele");
                model1.setAdapter(adapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }});

            model1 = (Spinner) findViewById(R.id.model);





            letnik1 = (Spinner) findViewById(R.id.odLetnik);


            int dolzina = letos - 1970;
            String[] letnice = new String[dolzina + 1];

        int j = 0;
        for (int i = 1970; i <= letos; i++) {

            letnice[j] = Integer.toString(i);
            j++;
        }

            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, letnice);
            letnik1.setAdapter(adapter);

            letnik2 = (Spinner) findViewById(R.id.doLetnik);


            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, letnice);
            letnik2.setAdapter(adapter);


        //Create button
        isci = (Button) findViewById(R.id.IskanjeOglasaGumb);
          isci.setOnClickListener(new View.OnClickListener() {
                        @Override
                       public void onClick(View v) {

                             Intent i = new Intent("android.intent.action.REZ");
                             String text = model1.getSelectedItem().toString();
                              String text2 = znamka1.getSelectedItem().toString();
                             String text3 = letnik1.getSelectedItem().toString();
                              String text4 = letnik2.getSelectedItem().toString();
                              i.putExtra(TAG_ZNAMKA,text);
                              i.putExtra(TAG_MODEL,text2);
                              i.putExtra(TAG_OD,text3);
                               i.putExtra(TAG_DO,text4);
                               startActivity(i);
                          }

    		        });
    }


    class LoadAllModels extends AsyncTask<String, String, String> {

        protected String doInBackground(String... args) {
            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            // getting JSON string from URL
            params.add(new BasicNameValuePair("znamka", isciTo));

            JSONObject json = jParser.makeHttpRequest(urlModel, "GET", params);

            // Check your log cat for JSON reponse
            Log.d("Vsi modeli: ", json.toString());

            try {
                // Checking for SUCCESS TAG
                int success = json.getInt(TAG_SUCCESS);

                if (success == 1) {
                    // products found
                    // Getting Array of Products
                    products = json.getJSONArray(TAG_AVTO);
                 //   System.out.println("Success je 1");
                    models=new String[products.length()];
                    aa=products.length();

                    // looping through All Products
                    for (int i = 0; i < products.length(); i++) {

                        JSONObject c = products.getJSONObject(i);

                        // Storing each json item in variable
                        models[i] = c.getString(TAG_MODEL);


                    }


                } else {models = new String[1]; models[0] = "-- model ne obstaja --";}
            } catch (JSONException e) {
                e.printStackTrace();
            }
            tt=false;
            return null;
        }







    }
}




