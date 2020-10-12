package com.cmoney.testlab.model;


import android.os.Handler;

public class PictureDownloader {

    public void RequestSinglePicture(final int id, final OnFinishCallback callback) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                callback.onSuccess(new SinglePicture(id));
            }
        }, 3000);
    }

    public interface OnFinishCallback {
        void onSuccess(SinglePicture result);

        void onFail(String msg);
    }
}
