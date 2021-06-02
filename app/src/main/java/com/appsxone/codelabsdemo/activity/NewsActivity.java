package com.appsxone.codelabsdemo.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.appsxone.codelabsdemo.R;
import com.appsxone.codelabsdemo.adapter.NewsAdapter;
import com.appsxone.codelabsdemo.model.NewsModel;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class NewsActivity extends AppCompatActivity {
    RecyclerView rvNews;
    ArrayList<NewsModel> newsModelArrayList = new ArrayList<>();
    String id, title, summary, link, published;
    ProgressDialog progressDialog;
    SwipeRefreshLayout pullToRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setTitle("News List");
        rvNews = findViewById(R.id.rvNews);
        rvNews.setHasFixedSize(true);
        rvNews.setLayoutManager(new LinearLayoutManager(this));
        progressDialog = new ProgressDialog(this);
        pullToRefresh = findViewById(R.id.pullToRefresh);

        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getNewsList();
                pullToRefresh.setRefreshing(false);
            }
        });

    }

    private void getNewsList() {
        newsModelArrayList.clear();
        progressDialog.setMessage("Loading..");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
        final int DEFAULT_TIMEOUT = 1000000000;
        AsyncHttpClient client = new AsyncHttpClient();
        client.setTimeout(DEFAULT_TIMEOUT);
        client.get("https://api.first.org/data/v1/news", new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody) {
                        progressDialog.dismiss();
                        try {
                            String content = new String(responseBody);
                            Log.d("onSuccess", "Success: " + content);
                            JSONObject jsonObject = new JSONObject(content);
                            if (jsonObject.getString("status").equals("OK")) {
                                JSONArray jsonArray = jsonObject.getJSONArray("data");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    id = jsonArray.getJSONObject(i).getString("id");
                                    title = jsonArray.getJSONObject(i).getString("title");
                                    link = jsonArray.getJSONObject(i).getString("link");
                                    published = jsonArray.getJSONObject(i).getString("published");
                                    summary = jsonArray.getJSONObject(i).optString("summary");
                                    newsModelArrayList.add(new NewsModel(id, title, summary, link, published));
                                }
                                NewsAdapter adapter = new NewsAdapter(getApplicationContext(), newsModelArrayList);
                                rvNews.setAdapter(adapter);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody, Throwable error) {
                        progressDialog.dismiss();
                        try {
                            String content = new String(responseBody);
                            Log.d("onFailure", "Failure: " + content);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
    }

    @Override
    protected void onStart() {
        super.onStart();
        getNewsList();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

}