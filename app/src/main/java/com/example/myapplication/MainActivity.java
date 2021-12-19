package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.AsyncTaskLoader;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // JSON_URL = "https://run.mocky.io/v3/7f2e66f5-4141-4a91-b24a-2a17da18f623";

    private static String JSON_URL = "https://run.mocky.io/v3/7f2e66f5-4141-4a91-b24a-2a17da18f623"; // link json yang digunakan, data sama seperti yang di berikan oleh bapak
    /**
     * untuk link jsonnya, saya conversi lagi menjadi Array dengan menggunakan
     * data Json yang diberikan oleh bapak di link Elearning.
     */

    List<itemActivity> programList;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        programList = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerview);

        GetData getData = new GetData();
        getData.execute();
    } //OnCreate

    public class GetData extends AsyncTask<String, String, String> {


        @Override
        protected String doInBackground(String... strings) {
            String current = "";
            try{
                URL url;
                HttpURLConnection urlConnection = null;

                try {
                    url = new URL(JSON_URL);
                    urlConnection = (HttpURLConnection) url.openConnection();

                    InputStream is = urlConnection.getInputStream();
                    InputStreamReader isr = new InputStreamReader(is);

                    int data = isr.read();
                    while (data != -1) {
                        current += (char) data;
                        data = isr.read();
                    }
                    return current;

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    if (urlConnection != null) {
                        urlConnection.disconnect();
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            return current;
        } //doInBackground

        @Override
        protected void onPostExecute(String s) {


            try {
                JSONObject jsonObject = new JSONObject(s);
                JSONArray jsonArray = jsonObject.getJSONArray("Main");

                for (int i = 0; i < jsonArray.length(); i++) {

                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                    itemActivity item = new itemActivity();

                    item.setLogo(jsonObject1.getString("logo"));
                    item.setTitle(jsonObject1.getString("title"));
                    item.setLink(jsonObject1.getString("read_more"));
                    item.setDesc(jsonObject1.getString("description"));
                    item.setExmple(jsonObject1.getString("hello_world"));

                    programList.add(item);

                } // for loop

            } catch (JSONException e) {
                e.printStackTrace();
            } // catch

            putDataIntoRecycleView(programList);
        }
    } // GetData Method

    private void putDataIntoRecycleView(List<itemActivity> programList) {
        itemAdapter adapter = new itemAdapter(this, programList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(adapter);
    } //putDataIntoRecycleView
} //Main