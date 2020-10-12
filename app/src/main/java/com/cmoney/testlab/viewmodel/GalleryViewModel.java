package com.cmoney.testlab.viewmodel;

import androidx.databinding.ObservableBoolean;
import androidx.lifecycle.MutableLiveData;

import com.cmoney.testlab.model.PictureDownloader;
import com.cmoney.testlab.model.SinglePicture;

import java.util.List;

public class GalleryViewModel {
    public final ObservableBoolean isLoading = new ObservableBoolean(false);

    private final MutableLiveData<List<SinglePicture>> pictureList = new MutableLiveData<>();

    private PictureDownloader pictureDownloader;

    public GalleryViewModel() {
        pictureDownloader = new PictureDownloader();
    }

    public MutableLiveData<List<SinglePicture>> getPictureList() {
        return pictureList;
    }

    public void FetchGallery() {
        isLoading.set(true);
        pictureDownloader.RequestPictureGallery(new PictureDownloader.OnGalleryFinishCallback() {
            @Override
            public void onSuccess(List<SinglePicture> result) {
                pictureList.setValue(result);
                isLoading.set(false);
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }

}
