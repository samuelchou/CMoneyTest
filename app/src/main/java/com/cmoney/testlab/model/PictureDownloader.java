package com.cmoney.testlab.model;


import android.content.Context;
import android.os.Handler;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PictureDownloader {
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/photos";

    private RequestQueue requestQueue;

    @Deprecated
    public PictureDownloader() {
    }

    public PictureDownloader(RequestQueue requestQueue) {
        this.requestQueue = requestQueue;
    }

    public PictureDownloader(Context context) {
        this.requestQueue = Volley.newRequestQueue(context);
    }

    public void RequestSinglePicture(final int id, final OnFinishCallback callback) {
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                callback.onSuccess(new SinglePicture(id));
//            }
//        }, 3000);
        JsonObjectRequest objectRequest = new JsonObjectRequest(BASE_URL + "/" + id,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        callback.onSuccess(new Gson().fromJson(String.valueOf(response), SinglePicture.class));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (error != null) callback.onFail(error.getMessage());
                        else callback.onFail("Null error.");
                    }
                });
        requestQueue.add(objectRequest);
    }

    public void RequestPictureGallery(final OnGalleryFinishCallback callback) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                List<SinglePicture> pictureList = new ArrayList<>();
                for (int i = 0; i < 100; i++) {
                    pictureList.add(new SinglePicture(i));
                }
                callback.onSuccess(pictureList);
            }
        }, 3000);
    }

    public interface OnFinishCallback {
        void onSuccess(SinglePicture result);

        void onFail(String msg);
    }

    public interface OnGalleryFinishCallback {
        void onSuccess(List<SinglePicture> result);

        void onFail(String msg);
    }
}
