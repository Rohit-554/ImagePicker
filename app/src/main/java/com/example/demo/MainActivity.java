package com.example.demo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.demo.viewmodels.ImagePickerViewModel;

public class MainActivity extends AppCompatActivity {
    private static final int IMAGE_PICKER_REQUEST = 1;
    private static ImageView imageView = null;
    private ImagePickerViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = new ViewModelProvider(this).get(ImagePickerViewModel.class);

        imageView = findViewById(R.id.image_view);
        Button selectImageButton = findViewById(R.id.select_image_button);
        viewModel.getSelectedImage().observe(this, new Observer<Uri>() {
            @Override
            public void onChanged(Uri uri) {
                Glide.with(MainActivity.this)
                        .load(uri)
                        .into(imageView);
            }
        });
        selectImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, IMAGE_PICKER_REQUEST);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == IMAGE_PICKER_REQUEST && resultCode == RESULT_OK && data != null) {
            Uri imageUri = data.getData();

            Glide.with(this)
                    .load(imageUri)
                    .into(imageView);

            viewModel.setSelectedImage(imageUri);
        }
    }

}