package com.cmoney.testlab.viewmodel;

import android.app.Application;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.lifecycle.AndroidViewModel;

import com.android.volley.toolbox.ImageLoader;
import com.cmoney.testlab.R;
import com.cmoney.testlab.model.PictureDownloader;
import com.cmoney.testlab.model.SinglePicture;

public class SinglePictureViewModel extends AndroidViewModel {
    private PictureDownloader pictureDownloader;

    public final ObservableBoolean isLoading = new ObservableBoolean(false);
    public final ObservableField<SinglePicture> picture = new ObservableField<>();

    public SinglePictureViewModel(@NonNull Application application) {
        super(application);
        pictureDownloader = new PictureDownloader(application);
    }

    public void Request(int id) {
        isLoading.set(true);
        pictureDownloader.RequestSinglePicture(id, new PictureDownloader.OnFinishCallback() {
            @Override
            public void onSuccess(SinglePicture result) {
                isLoading.set(false);
                picture.set(result);
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }

    @BindingAdapter("imageUrl")
    public static void loadImage(ImageView view, String url) {
        if (url == null) return;
        PictureDownloader downloader = new PictureDownloader(view.getContext());
        ImageLoader imageLoader = downloader.getImageLoader();
        ImageLoader.ImageListener listener = ImageLoader.getImageListener(view, R.drawable.ic_launcher_background, R.drawable.ic_launcher_foreground);
//        imageLoader.get(url, listener); // 原始網址壞去，使用新方法
        imageLoader.get(PictureDownloader.getPicAlternativeUrl(url), listener);
    }
}
