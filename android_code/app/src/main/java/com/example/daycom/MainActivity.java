package com.example.daycom;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //this.setContentView(R.layout.activity_main);
        View.OnClickListener NewAct = new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Button b = (Button) v;
                switch (v.getId()){
                    case R.id.aboutBT : startActivity(new Intent(MainActivity.this,AboutUsActivity.class));
                    break;
                    case R.id.catalogBT: startActivity(new Intent(MainActivity.this, CategorysActivity.class));
                    break;
                    case R.id.checkStatusBT: startActivity(new Intent(MainActivity.this, RepairActivity.class));
                    break;
                    default: Toast.makeText(getApplicationContext(), "Ошибка при нажатии кнопки", Toast.LENGTH_LONG).show();
                }
            }
        };
        Button a = (Button) findViewById(R.id.aboutBT);
        Button ca = (Button) findViewById(R.id.catalogBT);
        Button ch = (Button) findViewById(R.id.checkStatusBT);
        a.setOnClickListener(NewAct);
        ca.setOnClickListener(NewAct);
        ch.setOnClickListener(NewAct);
        //ca.callOnClick();
    }
}
