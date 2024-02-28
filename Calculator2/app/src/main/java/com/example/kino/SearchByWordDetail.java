package com.example.kino;

import static com.example.kino.MainPage.filmsList;
import static com.example.kino.SearchByWordList.searchFilmsList;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

public class SearchByWordDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_word_detail);

        JSONObject f = searchFilmsList.get(Global.positionWord);

        try {
            ((TextView)findViewById(R.id.nameRuDW)).setText(f.getString("nameRu"));
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        try {
            ((TextView)findViewById(R.id.yearDW)).setText(f.getString("year"));
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        try {
            Picasso.with(this).load(f.getString("posterUrlPreview")).into(((ImageView)findViewById(R.id.posterDW)));
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        try {
            ((TextView)findViewById(R.id.DescDW)).setText(f.getString("description"));
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        try {
            ((TextView)findViewById(R.id.lengthDW)).setText(f.getString("filmLength"));
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        try {
            ((TextView)findViewById(R.id.ratingDW)).setText(f.getString("rating"));
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
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