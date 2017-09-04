package com.example.fileshare;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private String mCurrentPhotoPath;
    private ImageView mIvPhoto;
    private Button button;
    private static final int REQUEST_CODE_TAKE_PHOTO = 9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mIvPhoto = (ImageView) findViewById(R.id.imageView);
        button = (Button) findViewById(R.id.button);
        takePhoto();
    }

    public void takePhoto(){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {

                    String filename = new SimpleDateFormat("yyyyMMdd-HHmmss", Locale.CHINA)
                            .format(new Date()) + ".png";
                    File file = new File(Environment.getExternalStorageDirectory(), filename);
                    mCurrentPhotoPath = file.getAbsolutePath();

                    Uri fileUri;
                    if(Build.VERSION.SDK_INT >= 24){
                        fileUri = FileProvider.getUriForFile(MainActivity.this, "com.zhy.android7.fileprovider", file);
                    }else {
                        fileUri = Uri.fromFile(file);
                    }
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
                    startActivityForResult(takePictureIntent, REQUEST_CODE_TAKE_PHOTO);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE_TAKE_PHOTO) {
            mIvPhoto.setImageBitmap(BitmapFactory.decodeFile(mCurrentPhotoPath));
        }
    }
}
