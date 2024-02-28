package com.example.kino;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainPage extends AppCompatActivity {

    static ArrayList<JSONObject> filmsList = new ArrayList<>();
    BoxAdapter boxAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        RequestQueue volleyQueue = Volley.newRequestQueue(MainPage.this);

        String url = "https://kinopoiskapiunofficial.tech/api/v2.2/films/top";

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, null,
                (Response.Listener<JSONObject>) response ->{
                    try {                        
                        JSONArray jaFilms = response.getJSONArray("films");

                        filmsList.clear();
                        for(int i=0;i<jaFilms.length();i++){
                            filmsList.add(jaFilms.getJSONObject(i));
                        }

                        boxAdapter = new BoxAdapter(this, filmsList);
                        ListView lvMain = (ListView) findViewById(R.id.lvMain);
                        lvMain.setAdapter(boxAdapter);


                    }catch (JSONException e){
                        e.printStackTrace();
                    }
                },
                (Response.ErrorListener) error->{
                    Toast.makeText(MainPage.this, error.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                })
        {
            /** Passing some request headers* */
            @Override
            public Map getHeaders() throws AuthFailureError {
                HashMap headers = new HashMap();
                headers.put("Content-Type", "application/json");
                headers.put("X-API-KEY", "6caff405-5aa3-4e8f-a76c-417e4abf73ae");
                return headers;
            }
        };
        volleyQueue.add(jsonObjReq);
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
        }
        return super.onOptionsItemSelected(item);
    }
}

