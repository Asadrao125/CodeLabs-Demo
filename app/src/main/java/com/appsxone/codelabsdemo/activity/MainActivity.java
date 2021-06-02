package com.appsxone.codelabsdemo.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.appsxone.codelabsdemo.R;

public class MainActivity extends AppCompatActivity {
    Button btnConverter, btnNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnConverter = findViewById(R.id.btnConverter);
        btnNews = findViewById(R.id.btnNews);

        btnConverter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ConverterActivity.class));
            }
        });

        btnNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), NewsActivity.class));
            }
        });

    }
}