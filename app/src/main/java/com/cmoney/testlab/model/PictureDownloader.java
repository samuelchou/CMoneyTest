package com.cmoney.testlab.model;


import android.os.Handler;

import java.util.ArrayList;
import java.util.List;

public class PictureDownloader {

    public void RequestSinglePicture(final int id, final OnFinishCallback callback) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                callback.onSuccess(new SinglePicture(id));
            }
        }, 3000);
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
