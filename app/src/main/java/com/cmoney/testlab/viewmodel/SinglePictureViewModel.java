package com.cmoney.testlab.viewmodel;

import androidx.databinding.ObservableField;

import com.cmoney.testlab.model.PictureDownloader;
import com.cmoney.testlab.model.SinglePicture;

public class SinglePictureViewModel {
    private PictureDownloader pictureDownloader;

    public final ObservableField<SinglePicture> picture = new ObservableField<>();

    public SinglePictureViewModel() {
        pictureDownloader = new PictureDownloader();
    }

    public void Request(int id) {
        pictureDownloader.RequestSinglePicture(id, new PictureDownloader.OnFinishCallback() {
            @Override
            public void onSuccess(SinglePicture result) {
                picture.set(result);
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }

}
