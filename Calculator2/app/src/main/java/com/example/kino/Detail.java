package com.example.kino;

import static com.example.kino.MainPage.filmsList;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

public class Detail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        JSONObject f = filmsList.get(Global.positionGen);

        try {
            ((TextView)findViewById(R.id.nameRuD)).setText(f.getString("nameRu"));
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        try {
            ((TextView)findViewById(R.id.yearD)).setText(f.getString("year"));
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        try {
            Picasso.with(this).load(f.getString("posterUrlPreview")).into(((ImageView)findViewById(R.id.posterD)));
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        try {
            ((TextView)findViewById(R.id.lengthD)).setText(f.getString("filmLength"));
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        try {
            ((TextView)findViewById(R.id.ratingD)).setText(f.getString("rating"));
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}