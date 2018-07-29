package com.example.hritik.sqlite_with_recycler;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class Main2Activity extends AppCompatActivity {
    private AlertDialog.Builder builder;
    private AlertDialog alertDialog;
    private DataBaseHandler dataBaseHandler;
    private EditText name,religion;
    private int length=0;
    public  static Button saveButton;
    private List<BioData> bioDataList;
    private Button showRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        dataBaseHandler=new DataBaseHandler(this);
//        if(dataBaseHandler.BioDataLength()==0) {
//            dataBaseHandler.addBioData(new BioData("Hritik", "Hindu"));
//        }


        setSupportActionBar(toolbar);


        bioDataList=dataBaseHandler.getAllBiodata();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
              createPopUp();

            }
        });
        showRecyclerView=(Button)findViewById(R.id.recyclerShow);
        showRecyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dataBaseHandler.BioDataLength()!=0){
                startActivity(new Intent(Main2Activity.this,MainActivity.class));}
                else {
                    Toast.makeText(Main2Activity.this,"Nothing to display",Toast.LENGTH_LONG).show();
                }
            }
        });




    }


    private void createPopUp()
    {
        builder=new AlertDialog.Builder(this);
        View view1=getLayoutInflater().inflate(R.layout.add_popup,null);
        name=(EditText)view1.findViewById(R.id.editText);
        religion=(EditText)view1.findViewById(R.id.editText2);
        saveButton=(Button)view1.findViewById(R.id.saveToDb);
        builder.setCancelable(true);
        builder.setView(view1);
        alertDialog= builder.create();
        alertDialog.show();
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Main2Activity.this,"Added",Toast.LENGTH_SHORT).show();
                dataBaseHandler.addBioData(new BioData(name.getText().toString(),religion.getText().toString()));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        alertDialog.dismiss();
                    }
                },500);

            }
        });

    }



}
