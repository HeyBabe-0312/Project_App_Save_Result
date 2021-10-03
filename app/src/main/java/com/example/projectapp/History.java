package com.example.projectapp;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class History extends AppCompatActivity {
    RecyclerView recyclerView;
    HistoryAdapter adapter;
    HistoryHelper historyHelper;
    List<HistoryModel> historyList;
    TextView col1;
    TextView col2;
    TextView col3;
    TextView col4;
    private EditText edNum;
    private int len;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        this.setTitle("HISTORY");
        historyHelper = new HistoryHelper(this,"ProjectCheck2.sql",null,1);
        //historyHelper.queryData("CREATE TABLE IF NOT EXISTS History(Id INTEGER PRIMARY KEY AUTOINCREMENT, Date VARCHAR(50), Pass INT, Error INT, Rate double)");
        recyclerView = findViewById(R.id.recycle_view);
        col1 = findViewById(R.id.col1);
        col2 = findViewById(R.id.col2);
        col3 = findViewById(R.id.col3);
        col4 = findViewById(R.id.col4);

        historyList = new ArrayList<>();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new HistoryAdapter(this,historyList);
        recyclerView.setAdapter(adapter);
        getList();
        col1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                historyList.clear();
                Cursor data = historyHelper.getData("SELECT * FROM History ORDER BY Date DESC");
                while(data.moveToNext()){
                    int id = data.getInt(0);
                    String date = data.getString(1);
                    int pass = data.getInt(2);
                    int error = data.getInt(3);
                    double rate = data.getDouble(4);
                    historyList.add(new HistoryModel(id, date, pass, error, rate));
                }
                Toast.makeText(History.this,"Sort by Date (DESC)",Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
            }
        });
        col2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor data = historyHelper.getData("SELECT * FROM History ORDER BY Pass DESC");
                historyList.clear();
                while(data.moveToNext()){
                    int id = data.getInt(0);
                    String date = data.getString(1);
                    int pass = data.getInt(2);
                    int error = data.getInt(3);
                    double rate = data.getDouble(4);
                    historyList.add(new HistoryModel(id, date, pass, error, rate));
                }
                Toast.makeText(History.this,"Sort by Pass (DESC)",Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
            }
        });
        col3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                historyList.clear();
                Cursor data = historyHelper.getData("SELECT * FROM History ORDER BY Error DESC");
                while(data.moveToNext()){
                    int id = data.getInt(0);
                    String date = data.getString(1);
                    int pass = data.getInt(2);
                    int error = data.getInt(3);
                    double rate = data.getDouble(4);
                    historyList.add(new HistoryModel(id, date, pass, error, rate));
                }
                Toast.makeText(History.this,"Sort by Error (DESC)",Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
            }
        });
        col4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                historyList.clear();
                Cursor data = historyHelper.getData("SELECT * FROM History ORDER BY Rate DESC");
                while(data.moveToNext()){
                    int id = data.getInt(0);
                    String date = data.getString(1);
                    int pass = data.getInt(2);
                    int error = data.getInt(3);
                    double rate = data.getDouble(4);
                    historyList.add(new HistoryModel(id, date, pass, error, rate));
                }
                Toast.makeText(History.this,"Sort by Rate (DESC)",Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_history, menu);
        return super.onCreateOptionsMenu(menu);
    }
    private void getList(){
        len = 0;
        Cursor data = historyHelper.getData("SELECT * FROM History ORDER BY Date DESC");
        historyList.clear();
        while(data.moveToNext()){
            int id = data.getInt(0);
            String date = data.getString(1);
            int pass = data.getInt(2);
            int error = data.getInt(3);
            double rate = data.getDouble(4);
            historyList.add(new HistoryModel(id, date, pass, error, rate));
            len += 1;
        }
        adapter.notifyDataSetChanged();
    }
    public void deleteTime(){
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.del_form);
        edNum = dialog.findViewById(R.id.edit_del);
        Button add5 = dialog.findViewById(R.id.btn_cong5);
        Button add10 = dialog.findViewById(R.id.btn_cong10);
        Button delAll = dialog.findViewById(R.id.btn_delall);
        Button del = dialog.findViewById(R.id.btn_del);
        String regex = "\\d+";
        add5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String num = edNum.getText().toString();
                if(num.matches(regex)){
                    int getnum = Integer.parseInt(num);
                    getnum += 5;
                    if(getnum>len) edNum.setText(""+len);
                    else edNum.setText(""+getnum);
                }
                else{
                    Toast.makeText(History.this,"Invalid Number",Toast.LENGTH_SHORT).show();
                    edNum.setText("0");
                }
            }
        });
        add10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String num = edNum.getText().toString();
                if(num.matches(regex)){
                    int getnum = Integer.parseInt(num);
                    getnum += 10;
                    if(getnum>len) edNum.setText(""+len);
                    else edNum.setText(""+getnum);
                }
                else{
                    Toast.makeText(History.this,"Invalid Number",Toast.LENGTH_SHORT).show();
                    edNum.setText("0");
                }
            }
        });
        delAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edNum.setText(""+len);
                if(len==0){  Toast.makeText(History.this,"Empty Data",Toast.LENGTH_SHORT).show(); }
                else{
                AlertDialog.Builder a = new AlertDialog.Builder(History.this);
                a.setMessage("ARE U SURE ?");
                a.setTitle("WARNING !");
                a.setNegativeButton("NOPE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                a.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(History.this,"Deleted all",Toast.LENGTH_SHORT).show();
                        historyHelper.queryData("DELETE FROM History");
                        dialog.dismiss();
                        getList();
                    }
                });
                a.show();
            }}
        });
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String num = edNum.getText().toString();
                if(num.matches(regex)){
                    if(len==0) {
                        edNum.setText("0");
                        Toast.makeText(History.this,"Empty Data",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        int getnum = Integer.parseInt(num);
                        if (getnum > len) edNum.setText("" + len);
                        else if (getnum == 0) Toast.makeText(History.this, "This's zero", Toast.LENGTH_SHORT).show();
                        else {
                            AlertDialog.Builder a = new AlertDialog.Builder(History.this);
                            a.setMessage("ARE U SURE ?");
                            a.setTitle("WARNING !");
                            a.setNegativeButton("NOPE", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            });
                            a.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @RequiresApi(api = Build.VERSION_CODES.O)
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Toast.makeText(History.this, "Deleted " + getnum, Toast.LENGTH_SHORT).show();
                                    historyHelper.queryData("DELETE FROM History WHERE Id IN (SELECT Id FROM History ORDER BY Date LIMIT " + getnum + ")");
                                    dialog.dismiss();
                                    getList();
                                }
                            });
                            a.show();
                        }
                    }
                }
                else{
                    Toast.makeText(History.this,"Invalid Number",Toast.LENGTH_SHORT).show();
                    edNum.setText("0");
                }
            }
        });
        dialog.show();
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int i = item.getItemId();
        if (i == R.id.mybutton){
            deleteTime();
        }
        else if (i == android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}