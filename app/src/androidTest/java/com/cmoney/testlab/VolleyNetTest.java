package com.cmoney.testlab;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class VolleyNetTest {

    private String baseUrl = "https://jsonplaceholder.typicode.com/photos";

    @Test
    public void TestForConnection() throws InterruptedException {
        final String formerTag = "TestForConnection: ";
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        RequestQueue queue = Volley.newRequestQueue(appContext);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                baseUrl + "/17", null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println(formerTag + "Response is " + response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(formerTag + "FAILED. Error: " + error.toString());
            }
        });

        System.out.println(formerTag + "Start Request....");
        queue.add(jsonObjectRequest);
        Thread.sleep(10000);
        System.out.println("Test finished after sleeping.");

    }

}
