package com.example.victordias.ong.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.victordias.ong.R;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    TextView tvDoador,tvOng;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        tvDoador = (TextView) findViewById(R.id.tvDoador);
        tvOng = (TextView) findViewById(R.id.tvOng);

        tvOng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });



    }
}
