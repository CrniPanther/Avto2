package crnipanter.avto;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Tomaz on 30.12.2014.
 */
public class NotFound extends Activity {

    TextView notfound;
    Button home;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.not_found);

        notfound = (TextView) findViewById(R.id.notfound);
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
