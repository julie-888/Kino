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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SearchByActorList extends AppCompatActivity {

    ArrayList<JSONObject> actorsList = new ArrayList<>();
    ActorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_actor_list);

        RequestQueue volleyQueue = Volley.newRequestQueue(SearchByActorList.this);

        String url = "https://kinopoiskapiunofficial.tech/api/v1/persons?name="+Global.actorName;

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, null,
                (Response.Listener<JSONObject>) response ->{
                    try {
                        JSONArray jaFilms = response.getJSONArray("items");

                        actorsList.clear();
                        for(int i=0;i<jaFilms.length();i++){
                            actorsList.add(jaFilms.getJSONObject(i));
                        }
                        adapter = new ActorAdapter(this, actorsList);
                        ListView lvActors = (ListView) findViewById(R.id.lvActors);
                        lvActors.setAdapter(adapter);

                    }catch (JSONException e){
                        e.printStackTrace();
                    }
                },
                (Response.ErrorListener) error->{
                    Toast.makeText(SearchByActorList.this, error.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                })
        {
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
            case R.id.goHome:
                Intent intent3 = new Intent(this, MainPage.class);
                startActivity(intent3);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}