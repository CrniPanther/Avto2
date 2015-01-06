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
    int letos = 2015;
    Button isci;
    TextView znamka,model,letnikod, letnikdo;
    Spinner znamka1,model1,letnik1, letnik2;

    private static String url = "http://avto.host56.com/iskanje_oglasa.php";
    private static String urlZnamka = "http://avto.host56.com/getZnamka.php";

    String [] items,b;
    int a;
    JSONArray products = null;



    // Creating JSON Parser object
    JSONParser jParser = new JSONParser();
    ArrayList<String> znamkeList;
    boolean t=true;




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


      /* String [] items = new String[znamkeList.toArray().length];
        items=znamkeList.toArray(items);*/

 /* String[] items = new String[znamkeList.size()];
        items=znamkeList.toArray();*/ /*new String[]{"Renault","Acura","Audi","Alfa Romeo","Aston Martin",
          "BMW","Buick","Cadillac","Chevrolet","Chrysler","Citroen","Datsun",
          "Dodge","FAW","Ferrari","Fiat","Ford","GEO","GMC","Honda",
          "Hummer","Hyundai","Infiniti","Izusu","Jaguar","Jeep","Lamborghini",
          "Land Rover","Lincoln","Maserati","Mazda","Mercedes Benz","Mercury","Villager",
          "MG","Mini","Mitsubishi","Nissan","Oldsmobile","Peugeot","Plymouth","Pontiac","Porsche",
          "Rambler","Renault","Saab","Seat","Smart","Suzuki","Toyota","Volkswagen","Volvo","Austin Morris",
          "Bentley","Bugatti","Caterham","China Motors","Corvette","Dacia","Daewoo","Daihatsu","Kia","Lada",
          "Lancia","Lexus","Lotus","Opel","Panther","Rolls Royce","Rover","Skoda","SsangYong","Subaru","TVR","Talbot",
          "Tata","Tazzari","Trabant","Triumph","Uaz","Ueec","Vauxhall","Wartburg","Wiesmann","Zotye","Aixam","Alpina",
          "Ariel","Austin Healey","Autobianchi","Cobra","Landwind"};*/
        znamkeList=new ArrayList<String>();
        znamkeList.size();




        for (int i=0;i<a;i++) {
            znamkeList.add(items[i]);
            Log.d("All Products: ", items[i]+"--");
        }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, znamkeList);
            znamka1.setAdapter(adapter);

            model1 = (Spinner) findViewById(R.id.model);

            items = new String[]{"Clio", "Modus", "Berlingo"};
            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items);
            model1.setAdapter(adapter);

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






    }










}

