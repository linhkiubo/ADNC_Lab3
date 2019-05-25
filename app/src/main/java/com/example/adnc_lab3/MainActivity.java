package com.example.adnc_lab3;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v4.content.CursorLoader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    Button btnChuyentrang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=findViewById(R.id.lvContact);
        btnChuyentrang=findViewById(R.id.btnchuyen);
        btnChuyentrang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Bai2.class);
                startActivity(intent);
            }
        });


        displayAllContact();

    }
    public void displayAllContact(){
        Uri uri =  Uri.parse("content://contacts/people");
        List<String> list = new ArrayList<String>();
        CursorLoader cursorLoader = new CursorLoader(MainActivity.this,uri,null ,null,null,null);
        Cursor cursor = cursorLoader.loadInBackground();
        cursor.moveToFirst();
        while (cursor.isAfterLast()==false){
            String chuoi ="";
            String cotNum= ContactsContract.Contacts._ID;
            int hangID=cursor.getColumnIndexOrThrow(cotNum);

            String cotName=ContactsContract.Contacts.DISPLAY_NAME;
            int hangName=cursor.getColumnIndex(cotName);

            String ten =cursor.getString(hangName);
            String stt= cursor.getString(hangID);

            chuoi="Số:"+stt+"-"+"Tên :"+ten;

            list.add(chuoi);
            cursor.moveToNext();
        }
        cursor.close();

        ArrayAdapter adapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_expandable_list_item_1,list);
        listView=findViewById(R.id.lvContact);
        listView.setAdapter(adapter);
    }


}
