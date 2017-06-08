package com.android.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.numberprogressbar.NumberProgressBar;
import com.daimajia.numberprogressbar.OnProgressBarListener;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

public class BuyClothesActivity extends AppCompatActivity implements OnProgressBarListener {
    private TextView txtTakePhoto;
    private static int REQUEST_CAMERA = 1;
    private String uuidPath;
    private String mFilePath;
    private ImageView img;
    private Timer timer;
    private NumberProgressBar bnp;
    private ImageView img1;
    private ImageView img2;
    private ImageView img3;
    private ImageView img4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_clothes);

        bnp = (NumberProgressBar)findViewById(R.id.numberbar1);
        bnp.setOnProgressBarListener(this);
        img = (ImageView) findViewById(R.id.img_photo);
        txtTakePhoto = (TextView) findViewById(R.id.txt_takephoto);
        img1=(ImageView)findViewById(R.id.img1);
        img2=(ImageView)findViewById(R.id.img2);
        img3=(ImageView)findViewById(R.id.img3);
        img4=(ImageView)findViewById(R.id.img4);

        uuidPath = UUID.randomUUID().toString();
        mFilePath = Environment.getExternalStorageDirectory().getPath();// 获取SD卡路径
        mFilePath = mFilePath + "/" + uuidPath + ".png";// 指定路径
        //启动相机
        txtTakePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                Uri photoUri = Uri.fromFile(new File(mFilePath));
                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                startActivityForResult(intent, REQUEST_CAMERA);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CAMERA) {
                Picasso.with(BuyClothesActivity.this).load(new File(mFilePath)).resize(200, 200).centerCrop().into(img);

                timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                bnp.incrementProgressBy(1);
                            }
                        });
                    }
                }, 500, 100);
            }
        }
    }
    @Override
    public void onProgressChange(int current, int max) {
        if(current == max) {
            Toast.makeText(getApplicationContext(),"计算完成！", Toast.LENGTH_SHORT).show();
//            bnp.setProgress(0);
            timer.cancel();
            Picasso.with(BuyClothesActivity.this).load(R.drawable.buy4).into(img1);
            Picasso.with(BuyClothesActivity.this).load(R.drawable.buy2).into(img2);
            Picasso.with(BuyClothesActivity.this).load(R.drawable.buy3).into(img3);
            Picasso.with(BuyClothesActivity.this).load(R.drawable.buy1).into(img4);
        }
    }
}
