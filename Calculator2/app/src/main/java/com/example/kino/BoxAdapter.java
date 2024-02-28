package com.example.kino;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class BoxAdapter extends BaseAdapter {
    Context ctx;
    LayoutInflater lInflater;
    ArrayList<JSONObject> objects;

    public BoxAdapter(Context ctx, ArrayList<JSONObject> objects) {
        this.ctx = ctx;
        this.objects = objects;
        lInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    JSONObject getProduct(int position){
        return ((JSONObject) getItem(position));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view==null){
            view = lInflater.inflate(R.layout.item, parent, false);
        }
        JSONObject f = getProduct(position);
        try {
            TextView t = (TextView) view.findViewById(R.id.nameRu);
            t.setText(f.getString("nameRu"));
            t.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Global.positionGen = position;
                    Intent intent = new Intent(parent.getContext(), Detail.class);
                    parent.getContext().startActivity(intent);
                }
            });

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        try {
            ((TextView)view.findViewById(R.id.year)).setText(f.getString("year"));
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        try {
            Picasso.with(ctx).load(f.getString("posterUrlPreview")).into(((ImageView)view.findViewById(R.id.poster)));
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return view;
    }
}
