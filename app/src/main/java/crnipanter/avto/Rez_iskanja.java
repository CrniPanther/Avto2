package crnipanter.avto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class Rez_iskanja extends ListActivity {
String vin;
    // Progress Dialog
    private ProgressDialog pDialog;

    // Creating JSON Parser object
    JSONParser jParser = new JSONParser();

    ArrayList<HashMap<String, String>> productsList;

    // url to get all products list
    private static final String url = "http://avto.host56.com/vin_novo.php";
    private static String url_all_products = "http://avto.host56.com/getRezultat.php";

    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_ZNAMKA = "znamka";
    private static final String TAG_MODEL = "model";
    private static final String TAG_VIN = "vin";
    private static final String TAG_LETNIK = "letnik";
    private static final String TAG_OD = "od";
    private static final String TAG_AVTO = "avto";
    private static final String TAG_DO = "do";
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

    // products JSONArray
    JSONArray products = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_products);

        // Hashmap for ListView
        productsList = new ArrayList<HashMap<String, String>>();



        // Loading products in Background Thread
        new LoadAllProducts().execute();

        // Get listview
        ListView lv = getListView();

        // on seleting single product
        // launching OgledVin

        lv.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // getting values from selected ListItem
                vin = ((TextView) view.findViewById(R.id.vin)).getText()
                        .toString();
                new IsciVin().execute();
                // Starting new intent
                /*Intent in = new Intent(getApplicationContext(),
                        IskanjeVin.IsciVin.class);
                // sending pid to next activity
                in.putExtra(TAG_VIN, vin);

                // starting new activity and expecting some response back
                startActivityForResult(in, 100);*/
            }
        });

    }

    JSONParser jsonParser = new JSONParser();
    JSONArray avto = null;

    class IsciVin extends AsyncTask<String, String, String>{

        protected String doInBackground(String[] args) {


            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("vin", vin));

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
                    String upKomentar = c.getString(TAG_UPKOMENTAR);
                    String komentar = c.getString(TAG_KOMENTAR);
                    String idOglas = c.getString(TAG_IDOGLASA);


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
                    i.putExtra(TAG_UPKOMENTAR,upKomentar);
                    i.putExtra(TAG_KOMENTAR,komentar);
                    i.putExtra(TAG_IDOGLASA,idOglas);

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




    /**
     * Background Async Task to Load all product by making HTTP Request
     * */
    class LoadAllProducts extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Rez_iskanja.this);
            pDialog.setMessage("Iščem oglase...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        /**
         * getting All products from url
         *
         * */
        protected String doInBackground(String... args) {
            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            // getting JSON string from URL
            params.add(new BasicNameValuePair("znamka", getIntent().getStringExtra(TAG_MODEL)));
            params.add(new BasicNameValuePair("model", getIntent().getStringExtra(TAG_ZNAMKA)));
            params.add(new BasicNameValuePair("od", getIntent().getStringExtra(TAG_OD)));
            params.add(new BasicNameValuePair("do", getIntent().getStringExtra(TAG_DO)));
            JSONObject json = jParser.makeHttpRequest(url_all_products, "GET", params);

            // Check your log cat for JSON reponse
            Log.d("All Products: ", json.toString());

            try {
                // Checking for SUCCESS TAG
                int success = json.getInt(TAG_SUCCESS);

                if (success == 1) {
                    // products found
                    // Getting Array of Products
                    products = json.getJSONArray(TAG_AVTO);

                    // looping through All Products
                    for (int i = 0; i < products.length(); i++) {
                        JSONObject c = products.getJSONObject(i);

                        // Storing each json item in variable
                        String id = c.getString(TAG_VIN);
                        String name = c.getString(TAG_ZNAMKA);

                        // creating new HashMap
                        HashMap<String, String> map = new HashMap<String, String>();

                        // adding each child node to HashMap key => value
                        map.put(TAG_VIN, id);
                        map.put(TAG_ZNAMKA, name);


                        // adding HashList to ArrayList
                        productsList.add(map);
                    }
                } else {
                    // no products found
                    // Launch Add New product Activity
                    Intent i = new Intent(getApplicationContext(),
                            NotFound.class);
                    // Closing all previous activities
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }



        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog after getting all products
            pDialog.dismiss();
            // updating UI from Background Thread
            runOnUiThread(new Runnable() {
                public void run() {
                    /**
                     * Updating parsed JSON data into ListView
                     * */
                    ListAdapter adapter = new SimpleAdapter(
                            Rez_iskanja.this, productsList,
                            R.layout.list_item, new String[] { TAG_VIN,
                            TAG_ZNAMKA},
                            new int[] { R.id.vin, R.id.name });
                    // updating listview
                    setListAdapter(adapter);
                }
            });

        }

    }
}