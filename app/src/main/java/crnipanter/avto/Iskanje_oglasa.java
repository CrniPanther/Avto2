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
import android.widget.TextView;

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

    private static final String TAG_DO = "do";
    private static final String TAG_ZNAMKA = "znamka";
    private static final String TAG_MODEL = "model";
    private static final String TAG_OD = "od";
    int letos = 2015;
    Button isci;
    TextView znamka,model,letnikod, letnikdo;
    Spinner znamka1,model1,letnik1, letnik2;

    private static String url = "http://avto.host56.com/iskanje_oglasa.php";
    private static String urlZnamka = "http://avto.host56.com/getZnamka_novo.php";


    JSONArray products = null;
    JSONParser jsonParser = new JSONParser();

    // Progress Dialog
    private ProgressDialog pDialog;
    // Creating JSON Parser object
    JSONParser jParser = new JSONParser();
    ArrayList<HashMap<String, String>> znamkeList;



    static JSONArray znamke = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.iskanje_oglasa);

        znamka = (TextView) findViewById(R.id.textZnamka);
        model = (TextView) findViewById(R.id.textModel);
        letnikod = (TextView) findViewById(R.id.textLetnik);
        letnikdo = (TextView) findViewById(R.id.textLetnikDo);

        znamka1 = (Spinner) findViewById(R.id.znamka);


  String[] items = new String[]{"Renault","Acura","Audi","Alfa Romeo","Aston Martin",
          "BMW","Buick","Cadillac","Chevrolet","Chrysler","Datsun",
          "Dodge","FAW","Ferrari","Fiat","Ford","GEO","GMC","Honda",
          "Hummer","Hyundai","Infiniti","Izusu","Jaguar","Jeep","Lamborghini",
          "Land Rover","Lincoln","Maserati","Mazda","Mercedes Benz","Mercury","Villager",
          "MG","Mini","Mitsubishi","Nissan","Oldsmobile","Peugeot","Plymouth","Pontiac","Porsche",
          "Rambler","Renault","Saab","Seat","Smart","Suzuki","Toyota","Volkswagen","Volvo","Austin Morris",
          "Bentley","Bugatti","Caterham","China Motors","Citroen","Corvette","Dacia","Daewoo","Daihatsu","Kia","Lada",
          "Lancia","Lexus","Lotus","Opel","Panther","Rolls Royce","Rover","Skoda","SsangYong","Subaru","TVR","Talbot",
          "Tata","Tazzari","Trabant","Triumph","Uaz","Ueec","Vauxhall","Wartburg","Wiesmann","Zotye","Aixam","Alpina",
          "Ariel","Austin Healey","Autobianchi","Cobra","Landwind"};


            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items);
            znamka1.setAdapter(adapter);

            model1 = (Spinner) findViewById(R.id.model);

            items = new String[]{"Clio", "Modus"};
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



    }

