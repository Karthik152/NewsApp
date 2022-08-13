package com.example.newspaper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newspaper.Model.NewsModel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity2 extends AppCompatActivity {

    ImageView Share,imag;
    ArrayList<NewsModel> newsarraylist;
    TextView description,ID,NAME,currenttime;
    WebView webView;
    Button saveimg;
    String id,name,descriptionn,language,country,url,category;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ID=findViewById(R.id.id);
        NAME=findViewById(R.id.name);
        description=findViewById(R.id.desc);
        webView=findViewById(R.id.webview);
        Share=findViewById(R.id.share);
        imag=findViewById(R.id.img);
        progressBar=findViewById(R.id.progress);
        saveimg=findViewById(R.id.saveimage);
        currenttime=findViewById(R.id.date1);


        saveimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContextWrapper cw = new ContextWrapper(getApplicationContext());
                File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
                File file = new File(directory, "mario" + ".png");
                System.out.println("fille12"+file);
//                imag.setImageDrawable(Drawable.createFromPath(file.toString()));
            }
        });


        imag.setDrawingCacheEnabled(true);

        imag.buildDrawingCache(true);
        Bitmap b = imag.getDrawingCache();
        System.out.println("bitmap"+b);

        if(!new File("/"+Environment.DIRECTORY_PICTURES).exists())
            Log.e("Error","/"+Environment.DIRECTORY_PICTURES+" Dont exist");

        File file = new File(Environment.DIRECTORY_PICTURES+"/myImage.jpg");
        try {
            file.createNewFile();
            FileOutputStream ostream = new FileOutputStream(file);
//            b.compress(Bitmap.CompressFormat.JPEG, 80, ostream);
            imag.setDrawingCacheEnabled(false);
            ostream.close();
//            Toast.makeText(MainActivity2.this, "Offer saved", 1).show();

        } catch (IOException e) {
            e.printStackTrace();
        }







       /* bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
        outStream.flush();
        outStream.close();*/
//
//        Date c = Calendar.getInstance().getTime();
//        currenttime.setText((CharSequence) c);
//        System.out.println("current"+currenttime);
//        System.out.println("Current time => " + c);


        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);

        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
        String formattedDate = df.format(c);
        System.out.println("dasda"+formattedDate);
        currenttime.setText(formattedDate);

        Share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Intent.ACTION_SEND);
                myIntent.setType("text/plain");
                String shareBody = "Your body is here";
                String shareSub = "Your subject";
                myIntent.putExtra(Intent.EXTRA_SUBJECT, shareBody);
                myIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(myIntent, "Share using"));
            }
        });


        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            if (bundle.containsKey("id")) {
                id = bundle.getString("id");
                ID.setText(id);
            }
            if (bundle.containsKey("description")) {
                descriptionn = bundle.getString("description");
                description.setText(descriptionn);

            }
            if (bundle.containsKey("language")) {
                language = bundle.getString("language");
            }

            if (bundle.containsKey("name")) {
                name = bundle.getString("name");
                NAME.setText(name);
            }
            if (bundle.containsKey("country")) {
                country = bundle.getString("country");
            }
            if (bundle.containsKey("category")) {
                category = bundle.getString("category");
            }
            if (bundle.containsKey("url")) {
                url = bundle.getString("url");
                System.out.println("urul"+url);
            }

        }

        webView.loadUrl(""+url);
        WebSettings webSettings=webView.getSettings();
        webSettings.setJavaScriptEnabled(true);



        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                progressBar.setVisibility(View.VISIBLE);
                super.onPageStarted(view, url, favicon);

            }

            @Override
            public void onPageFinished(WebView view, String url) {
                progressBar.setVisibility(View.GONE);
                super.onPageFinished(view, url);
            }
        });

        System.out.println("dhfuid"+id);

    }


    public static void addImageToGallery(final String filePath, final Context context)
    {

        ContentValues values = new ContentValues();

        values.put(MediaStore.Images.Media.DATE_TAKEN, System.currentTimeMillis());
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
        values.put(MediaStore.MediaColumns.DATA, filePath);

        context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
    }


    private static void scanFile(Context context, Uri imageUri){
        Intent scanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        scanIntent.setData(imageUri);
        context.sendBroadcast(scanIntent);

    }
}
