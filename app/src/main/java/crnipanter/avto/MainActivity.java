package crnipanter.avto;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends Activity {


    Button Prijava;
    Button IskanjeVin;
    Button Oglasi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Prijava = (Button) findViewById(R.id.Prijava);
        IskanjeVin = (Button) findViewById(R.id.IskanjeVIN);
        Oglasi = (Button) findViewById(R.id.Oglasi);

        IskanjeVin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent IVIN= new Intent("android.intent.action.ISKANJEVIN");
                startActivity(IVIN);
            }
        });

        Prijava.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Pri = new Intent("android.intent.action.PRIJAVA");
                startActivity(Pri);
            }
        });

        Oglasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ogl=new Intent("android.intent.action.OGLASI");
                startActivity(ogl);
            }
        });



    }





}