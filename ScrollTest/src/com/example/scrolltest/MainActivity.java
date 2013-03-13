package com.example.scrolltest;

 
import java.util.ArrayList;
import java.util.List;
import java.security.SecureRandom;
import java.math.BigInteger;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
 
public class MainActivity extends Activity
{
    /** Called when the activity is first created. */
    ListView list;
    private List<String> List_file;
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List_file =new ArrayList<String>();
        list = (ListView)findViewById(R.id.mylist);
 
        CreateListView();
    }
    private void CreateListView()
    {
    	 // _jmeno,String _prijmeni, String _ulice, String _cislo, String _mesto, int _tel, int _PSC, String _email
    	 SecureRandom random = new SecureRandom();
         List_file.add((new P("Jiri", "Adam","Elisky Krasnohorske", "1283/21" , "Havirov", 591126141, 73601, "jadam@seznam.cz")).toString());
         for (int i =0; i < 50; i++){
         List_file.add((new P( new BigInteger(50, random).toString(32),  new BigInteger(50, random).toString(32), new BigInteger(50, random).toString(32), "" +Math.abs(random.nextInt()%100) + "/" + Math.abs(random.nextInt()%100),  new BigInteger(50, random).toString(32), Math.abs(random.nextInt()), Math.abs(random.nextInt()), (new BigInteger(15, random).toString(32)+"@seznam.cz"))).toString());
         }
         //Create an adapter for the listView and add the ArrayList to the adapter.
         list.setAdapter(new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1,List_file));
         list.setOnItemClickListener(new OnItemClickListener()
           {
                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3)
                {
                    //args2 is the listViews Selected index
                }
           });
    }
}