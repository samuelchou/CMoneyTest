package com.cmoney.testlab.model;


import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.collection.LruCache;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

public class PictureDownloader {
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/photos";
    private static final String TAG = "PictureDownloader";

    private RequestQueue requestQueue;
    private BitmapCache bitmapCache = new BitmapCache();

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

    public ImageLoader getImageLoader() {
        return new ImageLoader(requestQueue, bitmapCache);
    }

    public void RequestPictureGallery(final OnGalleryFinishCallback callback) {
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                List<SinglePicture> pictureList = new ArrayList<>();
//                for (int i = 0; i < 100; i++) {
//                    pictureList.add(new SinglePicture(i));
//                }
//                callback.onSuccess(pictureList);
//            }
//        }, 3000);
        JsonArrayRequest arrayRequest = new JsonArrayRequest(BASE_URL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        SinglePicture[] pictures = new Gson().fromJson(response.toString(), SinglePicture[].class);
                        callback.onSuccess(Arrays.asList(pictures));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error != null) callback.onFail(error.getMessage());
                else callback.onFail("Null Error.");
            }
        });

        requestQueue.add(arrayRequest);
    }

    public interface OnFinishCallback {
        void onSuccess(SinglePicture result);

        void onFail(String msg);
    }

    public interface OnGalleryFinishCallback {
        void onSuccess(List<SinglePicture> result);

        void onFail(String msg);
    }

    public static class BitmapCache implements ImageLoader.ImageCache {

        private LruCache<String, Bitmap> mCache;

        public BitmapCache() {
            int maxSize = 10 * 1024 * 1024;
            mCache = new LruCache<String, Bitmap>(maxSize) {
                @Override
                protected int sizeOf(@NonNull String key, @NonNull Bitmap bitmap) {
                    return bitmap.getRowBytes() * bitmap.getHeight();
                }
            };
        }

        @Override
        public Bitmap getBitmap(String url) {
            return mCache.get(url);
        }

        @Override
        public void putBitmap(String url, Bitmap bitmap) {
            mCache.put(url, bitmap);
        }

    }

    public static String getPicAlternativeUrl(String originUrl) {
        // origin: https://via.placeholder.com/150/92c952
        // target: https://ipsumimage.appspot.com/150,92c952
        Log.d(TAG, "getPicAlternativeUrl: origin was " + originUrl);
        String endUrl = originUrl.split("m/")[1].replace("/",",");
        String alterUrl = "https://ipsumimage.appspot.com/" + endUrl;
        Log.d(TAG, "getPicAlternativeUrl: alter was " + alterUrl);
        return alterUrl;
    }
}
