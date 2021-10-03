package com.example.projectapp;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.projectapp.databinding.ActivityMainBinding;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private HistoryHelper historyHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);

        historyHelper = new HistoryHelper(this,"ProjectCheck2.sql",null,1);
        historyHelper.queryData("CREATE TABLE IF NOT EXISTS History(Id INTEGER PRIMARY KEY AUTOINCREMENT, Date Datetime, Pass INT, Error INT, Rate Double)");
        binding.imgBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(binding.txtPass.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this,"Start Check",Toast.LENGTH_SHORT).show();
                    binding.txtPass.setText("0");
                    binding.txtFalse.setText("0");
                }else{
                    AlertDialog.Builder a = new AlertDialog.Builder(MainActivity.this);
                    a.setMessage("U want a new result?");
                    a.setTitle("WARNING !");
                    a.setNegativeButton("NOPE", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    a.setPositiveButton("SURE", new DialogInterface.OnClickListener() {
                        @RequiresApi(api = Build.VERSION_CODES.O)
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(MainActivity.this,"Start Check",Toast.LENGTH_SHORT).show();
                            binding.txtPass.setText("0");
                            binding.txtFalse.setText("0");
                        }
                    });
                    a.show();
                }
            }
        });
        binding.imgBtnReload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txt = binding.txtPass.getText().toString();
                String txt0 = binding.txtFalse.getText().toString();
                if((txt=="0"&&txt0=="0")||(txt0==""&&txt=="")){
                    Toast.makeText(MainActivity.this,"No Result",Toast.LENGTH_SHORT).show();
                }
                else {
                    String currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                    String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
                    int pass = Integer.parseInt(binding.txtPass.getText().toString());
                    int error = Integer.parseInt(binding.txtFalse.getText().toString());
                    historyHelper.queryData("INSERT INTO History VALUES (null,'"+currentDate+" "+currentTime+"',"+pass+","+error+","+Math.round(pass*100.0/(pass+error)*100.00)/100.00+")");
                    Toast.makeText(MainActivity.this, "Saved Result", Toast.LENGTH_SHORT).show();
                    binding.txtPass.setText("");
                    binding.txtFalse.setText("");
                }
            }
        });
        binding.imagePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!binding.txtPass.getText().toString().isEmpty()) {
                    int p = Integer.parseInt(binding.txtPass.getText().toString()) + 1;
                    binding.txtPass.setText(p + "");
                }
                else {
                    Toast.makeText(MainActivity.this,"U should click add new result",Toast.LENGTH_SHORT).show();
                }
            }
        });
        binding.imageError.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!binding.txtFalse.getText().toString().isEmpty()){
                    int p = Integer.parseInt(binding.txtFalse.getText().toString())+1;
                    binding.txtFalse.setText(p+"");
                }
                else {
                    Toast.makeText(MainActivity.this,"U should click add new result",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int i = item.getItemId();
        if (i == R.id.mybutton){
            Intent intent = new Intent(MainActivity.this,History.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

}