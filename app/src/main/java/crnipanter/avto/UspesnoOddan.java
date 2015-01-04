package crnipanter.avto;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Tomaz on 30.12.2014.
 */
public class UspesnoOddan extends Activity{

    TextView textView3;
    Button home;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.uspesno_oddan);

        textView3 = (TextView) findViewById(R.id.textView3);
        home = (Button) findViewById(R.id.home);



        home.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(in);

            }
        });
    }

}