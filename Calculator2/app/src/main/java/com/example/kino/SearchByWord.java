package com.example.kino;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class SearchByWord extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_word);
    }

    public void search(View view){
        TextView t = findViewById(R.id.word);
        Global.word = t.getText().toString();

        Intent intent = new Intent(this, SearchByWordList.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        switch (id){
            case R.id.searchByWord:
                Intent intent = new Intent(this, SearchByWord.class);
                startActivity(intent);
                return true;
            case R.id.searchByActor:
                Intent intent1 = new Intent(this, SearchByActor.class);
                startActivity(intent1);
                return true;
            case R.id.searchByCountry:
                Intent intent2 = new Intent(this, SearchByCountry.class);
                startActivity(intent2);
                return true;
            case R.id.goHome:
                Intent intent3 = new Intent(this, MainPage.class);
                startActivity(intent3);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}