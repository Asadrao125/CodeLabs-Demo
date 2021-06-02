package com.asadrao.codelabsdemo.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.asadrao.codelabsdemo.R;
import com.asadrao.codelabsdemo.adapter.ConverterAdapter;
import com.asadrao.codelabsdemo.model.ConverterModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ConverterActivity extends AppCompatActivity {
    FloatingActionButton fabAdd;
    ArrayList<ConverterModel> converterModelArrayList = new ArrayList<>();
    RecyclerView rvConverter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converter);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setTitle("Integer To Roman");
        fabAdd = findViewById(R.id.fabAdd);
        rvConverter = findViewById(R.id.rvConverter);
        rvConverter.setLayoutManager(new LinearLayoutManager(this));
        rvConverter.setHasFixedSize(true);

        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
    }

    public void showDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_input, null);
        dialogBuilder.setView(dialogView);
        AlertDialog alertDialog = dialogBuilder.create();

        EditText edtSingleInput = (EditText) dialogView.findViewById(R.id.edtSingleInput);
        EditText edtRangeFrom = (EditText) dialogView.findViewById(R.id.edtRangeFrom);
        EditText edtRangeTo = (EditText) dialogView.findViewById(R.id.edtRangeTo);
        Button btnBuyNow2 = dialogView.findViewById(R.id.btnOk);

        btnBuyNow2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String single = edtSingleInput.getText().toString().trim();
                String from = edtRangeFrom.getText().toString().trim();
                String to = edtRangeTo.getText().toString().trim();
                if (!TextUtils.isEmpty(single)) {
                    converterModelArrayList.clear();
                    converterModelArrayList.add(new ConverterModel(1, intToRoman(Integer.parseInt(single))));
                    setListData(converterModelArrayList);
                } else {
                    if (!TextUtils.isEmpty(from) && !TextUtils.isEmpty(to)) {
                        int i = Integer.parseInt(from);
                        int j = Integer.parseInt(to);
                        converterModelArrayList.clear();
                        for (int k = i; k <= j; k++) {
                            converterModelArrayList.add(new ConverterModel(k, intToRoman(k)));
                            setListData(converterModelArrayList);
                        }
                    }
                }

                alertDialog.dismiss();
            }
        });

        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.show();
    }

    private void setListData(ArrayList<ConverterModel> converterModelArrayList) {
        ConverterAdapter converterAdapter = new ConverterAdapter(ConverterActivity.this, converterModelArrayList);
        rvConverter.setAdapter(converterAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    public static String intToRoman(int num) {
        StringBuilder sb = new StringBuilder();
        int times = 0;
        String[] romans = new String[]{"I", "IV", "V", "IX", "X", "XL", "L",
                "XC", "C", "CD", "D", "CM", "M"};
        int[] ints = new int[]{1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500,
                900, 1000};
        for (int i = ints.length - 1; i >= 0; i--) {
            times = num / ints[i];
            num %= ints[i];
            while (times > 0) {
                sb.append(romans[i]);
                times--;
            }
        }
        return sb.toString();
    }

}