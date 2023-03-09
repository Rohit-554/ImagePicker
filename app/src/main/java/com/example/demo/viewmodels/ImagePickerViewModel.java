package com.example.demo.viewmodels;

import android.app.Application;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class ImagePickerViewModel extends AndroidViewModel {
    private final MutableLiveData<Uri> selectedImage = new MutableLiveData<>();

    public ImagePickerViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Uri> getSelectedImage() {
        return selectedImage;
    }

    public void setSelectedImage(Uri imageUri) {
        selectedImage.setValue(imageUri);
    }
}
