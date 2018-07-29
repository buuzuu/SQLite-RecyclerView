package com.example.hritik.sqlite_with_recycler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DataBaseHandler dataBaseHandler=new DataBaseHandler(this);
        recyclerView=(RecyclerView)findViewById(R.id.recyclerID);

//        dataBaseHandler.addBioData(new BioData("Emilia Clarke","Christian"));
//        dataBaseHandler.addBioData(new BioData("Hritik Gupta","Hindu"));
//        dataBaseHandler.addBioData(new BioData("Gurpreet Kaur","Sikh"));
//        dataBaseHandler.addBioData(new BioData("Hritik Gupta","Hindu"));


        Log.d("ADD","Successfully Added");

//          dataBaseHandler.delete(i);
        List<BioData> bioDataList=dataBaseHandler.getAllBiodata();
//       BioData bioData= dataBaseHandler.getBioData(2);
//        Log.d("Fetched..",bioData.getId()+"  "+bioData.getName()+"  "+bioData.getReligion());
        Log.d("Deleted","Successfully Deleted");


      dataBaseHandler.updateBioData(new BioData("Vikas","Jewish"),4);
        for (BioData bioData:bioDataList)
        {

            String log= "Fetched--"+ bioData.getId()+"  Name--"+bioData.getName()+"  Religion--"+bioData.getReligion();
            Log.d("Fetching..",log);

        }
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new RecyclerAdapter(MainActivity.this,bioDataList));

    }
}
