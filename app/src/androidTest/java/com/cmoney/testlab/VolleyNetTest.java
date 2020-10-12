package com.cmoney.testlab;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.cmoney.testlab.model.SinglePicture;
import com.google.gson.Gson;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.assertEquals;

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

    @Test
    public void TestGson() {
        String jsonString = "{\n" +
                "  \"albumId\": 1,\n" +
                "  \"id\": 17,\n" +
                "  \"title\": \"natus doloribus necessitatibus ipsa\",\n" +
                "  \"url\": \"https://via.placeholder.com/600/9c184f\",\n" +
                "  \"thumbnailUrl\": \"https://via.placeholder.com/150/9c184f\"\n" +
                "}";
        SinglePicture picture = new Gson().fromJson(jsonString, SinglePicture.class);
        assertEquals(17, picture.getId());
        assertEquals("natus doloribus necessitatibus ipsa", picture.getContent());
        assertEquals("https://via.placeholder.com/600/9c184f", picture.getUrl());
        assertEquals("https://via.placeholder.com/150/9c184f", picture.getThumbUrl());
    }



    @Test
    public void TestGsonArray() {
        String jsonString = "[\n" +
                "  {\n" +
                "    \"albumId\": 1,\n" +
                "    \"id\": 1,\n" +
                "    \"title\": \"accusamus beatae ad facilis cum similique qui sunt\",\n" +
                "    \"url\": \"https://via.placeholder.com/600/92c952\",\n" +
                "    \"thumbnailUrl\": \"https://via.placeholder.com/150/92c952\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"albumId\": 1,\n" +
                "    \"id\": 2,\n" +
                "    \"title\": \"reprehenderit est deserunt velit ipsam\",\n" +
                "    \"url\": \"https://via.placeholder.com/600/771796\",\n" +
                "    \"thumbnailUrl\": \"https://via.placeholder.com/150/771796\"\n" +
                "  }" +
                "]";
        SinglePicture[] pictures = new Gson().fromJson(jsonString, SinglePicture[].class);
        assertEquals(2, pictures[1].getId());
        assertEquals("accusamus beatae ad facilis cum similique qui sunt", pictures[0].getContent());
        assertEquals("https://via.placeholder.com/600/771796", pictures[1].getUrl());
        assertEquals("https://via.placeholder.com/150/92c952", pictures[0].getThumbUrl());
    }

}
