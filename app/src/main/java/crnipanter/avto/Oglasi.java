package crnipanter.avto;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Z1pp0 on 28.12.2014.
 */
public class Oglasi extends Activity{
    Button IskanjeOgl;
    Button OddajaOgl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.oglasi);
        IskanjeOgl=(Button) findViewById(R.id.IskanjeOglasa);
        OddajaOgl =(Button) findViewById(R.id.OddajaOglasa);

        IskanjeOgl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ogl=new Intent("android.intent.action.ISKANJEOGL");
                startActivity(ogl);
            }
        });

        OddajaOgl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //registriran up
                Intent ogl=new Intent("android.intent.action.ODDAJAOGL");
                startActivity(ogl);
            }
        });
    }
}
