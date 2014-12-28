package crnipanter.avto;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Z1pp0 on 28.12.2014.
 */
public class IskanjeVin extends Activity {

    Button Iskanje;
    EditText Napis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.iskanje_vin);
        Iskanje= (Button) findViewById(R.id.IskanjeVIN);
        Napis= (EditText) findViewById(R.id.VpisVIN);

        Iskanje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //le baza
            }
        });
    }
}


