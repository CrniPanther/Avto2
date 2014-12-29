package crnipanter.avto;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


/**
 * Created by Z1pp0 on 28.12.2014.
 */
public class Iskanje_oglasa extends Activity {

    EditText oglas;
    Button iskanje;
    private final String dbName = "Android";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.iskanje_oglasa);
        oglas= (EditText) findViewById(R.id.VpisiIskaniOglas);
        iskanje =(Button) findViewById(R.id.IskanjeOglasaGumb);
        final SQLiteDatabase sampleDB = null;
        iskanje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
