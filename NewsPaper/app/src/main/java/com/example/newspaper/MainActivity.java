package com.example.newspaper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.newspaper.Adapter.NewsListAdapter;
import com.example.newspaper.Model.NewsModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<NewsModel> newsarraylist;
    private NewsListAdapter newslistAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recycler);

        getapi();
    }

    private void getapi() {

        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        String url = "https://newsapi.org/v2/sources?apiKey=d29d58aab88d4ea0b04ddb245a230068";


        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println("responsee65"+response);
                if (response != null) {
                    try {
                        JSONObject jo1 = new JSONObject(String.valueOf(response));
                        JSONArray ja2 = jo1.getJSONArray("sources");

                        newsarraylist=new ArrayList<>();
                        for (int i=0;i<10;i++){
                            JSONObject ja1=ja2.getJSONObject(i);
                            String id =ja1.getString("id");
                            System.out.println("idiid"+id);
                            String name=ja1.getString("name");
                            String description=ja1.getString("description");
                            String url=ja1.getString("url");
                            String category=ja1.getString("category");
                            String language=ja1.getString("language");
                            String  country=ja1.getString("country");
                            newsarraylist.add(new NewsModel(id,name,description,language,country,url,category));

                        }
                        setRecyclerView();


                    }catch (Exception e){

                    }

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("error12"+error);
                Toast.makeText(MainActivity.this, "Failed to get data", Toast.LENGTH_SHORT).show();

            }

        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                System.out.println();
                params.put("User-Agent","Mozilla/5.0");
                return params;
            }
        };
        // queue to fetch all the json data.
        queue.add(jsonObjectRequest);
    }



    private void setRecyclerView() {

        ArrayList<NewsModel>data =new ArrayList<>(newsarraylist);
        newslistAdapter=new NewsListAdapter( newsarraylist);

        LinearLayoutManager manager=new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);


        recyclerView.setLayoutManager(manager);

        recyclerView.setAdapter(newslistAdapter);


        newslistAdapter.setOnClick(new NewsListAdapter.OnItemClicked() {
            @Override
            public void onItemClick(int itemId, int position, View view) {
                Intent intent =new Intent(MainActivity.this,MainActivity2.class);
                intent.putExtra("id",newsarraylist.get(position).getId());
                intent.putExtra("description",newsarraylist.get(position).getDescriptionn());
                intent.putExtra("language",newsarraylist.get(position).getLanguage());
                intent.putExtra("name",newsarraylist.get(position).getName());
                intent.putExtra("country",newsarraylist.get(position).getCountry());
                intent.putExtra("category",newsarraylist.get(position).getCategory());
                intent.putExtra("url",newsarraylist.get(position).getUrl());
                startActivity(intent);

            }
        });




    }



}