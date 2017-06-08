package com.android.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.UUID;

public class InsertActivity extends AppCompatActivity {
    private static int REQUEST_CAMERA = 1;
    private String uuidPath;
    private String mFilePath;
    private TextView txtTakePhoto;
    private ImageView img;
    private TextView txtClassify;
    private TextView txtColor;
    private TextView txtKeywords;
    private TextView txtSeason;
    private TextView txtClassify11;
    private TextView txtClassify12;
    private TextView txtClassify42;
    private TextView txtClassify43;
    private TextView txtColor11;
    private TextView txtColor37;
    private TextView txtKeywords21;
    private TextView txtSeason11;
    private TextView txtSeason12;
    private Button btnEntry;
    private RatingBar rtb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        txtTakePhoto = (TextView) findViewById(R.id.txt_takephoto);
        btnEntry=(Button)findViewById(R.id.btn_entry);
        img = (ImageView) findViewById(R.id.img_photo);
        rtb=(RatingBar)findViewById(R.id.rtb);

        //用户选择结果记录标签实例化
        txtClassify=(TextView)findViewById(R.id.txt_classify);
        txtColor=(TextView)findViewById(R.id.txt_color);
        txtKeywords=(TextView)findViewById(R.id.txt_keywords);
        txtSeason=(TextView)findViewById(R.id.txt_season);

        //属性标签实例化
        txtClassify11=(TextView)findViewById(R.id.txt_classify11);
        txtClassify12=(TextView)findViewById(R.id.txt_classify12);
        txtClassify42=(TextView)findViewById(R.id.txt_classify42);
        txtClassify43=(TextView)findViewById(R.id.txt_classify43);
        txtColor11=(TextView)findViewById(R.id.txt_color11);
        txtColor37=(TextView)findViewById(R.id.txt_color37);
        txtKeywords21=(TextView)findViewById(R.id.txt_keywords21);
        txtSeason11=(TextView)findViewById(R.id.txt_season11);
        txtSeason12=(TextView)findViewById(R.id.txt_season12);

        //获取照片存储路径
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

        //提交信息
        btnEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Clothes clothes=new Clothes();
                clothes.setType(txtClassify.getText().toString());
                clothes.setColor(txtColor.getText().toString());
                clothes.setKeywords(txtKeywords.getText().toString());
                clothes.setSeason(txtSeason.getText().toString());
                clothes.setPhoto(mFilePath);
                clothes.setRating(rtb.getRating());
                if(txtClassify.getText().toString().equals("开衫")||txtClassify.getText().toString().equals("风衣")){
                    clothes.setUpordown("上装");
                }else{
                   clothes.setUpordown("下装");
                }
                ClothesDAO cloDAO=new ClothesDAO(v.getContext());
                cloDAO.insertClothes(clothes);
                Toast.makeText(InsertActivity.this, "单品录入成功！", Toast.LENGTH_SHORT).show();

            }
        });

        //获取选择结果(九项)
        txtClassify11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtClassify.setText(txtClassify11.getText());
            }
        });

        txtClassify12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtClassify.setText(txtClassify12.getText());
            }
        });

        txtClassify42.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtClassify.setText(txtClassify42.getText());
            }
        });

        txtClassify43.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtClassify.setText(txtClassify43.getText());
            }
        });

        txtColor11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtColor.setText("纯黑");
            }
        });

        txtColor37.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtColor.setText("军绿");
            }
        });

        txtKeywords21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtKeywords.setText(txtKeywords21.getText());
            }
        });

        txtSeason11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtSeason.setText(txtSeason11.getText());
            }
        });

        txtSeason12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtSeason.setText(txtSeason12.getText());
            }
        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CAMERA) {
                Picasso.with(InsertActivity.this).load(new File(mFilePath)).resize(200, 200).centerCrop().into(img);
            }
        }
    }
}