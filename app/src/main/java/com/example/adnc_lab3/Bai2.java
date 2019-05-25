package com.example.adnc_lab3;

import android.database.Cursor;
import android.net.Uri;
import android.provider.Browser;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.service.media.MediaBrowserService;
import android.support.v4.content.CursorLoader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DialogTitle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class Bai2 extends AppCompatActivity {
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai2);
        listView=findViewById(R.id.lvv);

        displayAllMedia();

    }

    public void displayAllMedia(){
        String []projection={
                MediaStore.MediaColumns.DISPLAY_NAME,
                MediaStore.MediaColumns.DATE_ADDED,
                MediaStore.MediaColumns.MIME_TYPE,
        };
        CursorLoader loader= new CursorLoader(Bai2.this, MediaStore.Video.Media.EXTERNAL_CONTENT_URI,projection,null,null,null);
        Cursor cursor = loader.loadInBackground();
        cursor.moveToFirst();
        while(cursor.isAfterLast()==false)
        {
            String s = cursor.getString(0)+"-"+cursor.getString(1)+"-"+cursor.getString(2);
            Toast.makeText(getApplicationContext(),s, Toast.LENGTH_SHORT).show();
            cursor.moveToNext();
        }
        cursor.close();
    }
}
