package com.android.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.daimajia.numberprogressbar.NumberProgressBar;
import com.daimajia.numberprogressbar.OnProgressBarListener;
import com.squareup.picasso.Picasso;

import java.util.Timer;
import java.util.TimerTask;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class AdviceActivity extends AppCompatActivity implements OnProgressBarListener {
    private Button btnAnalyze;
    private NumberProgressBar bnp;
    private Timer timer;
    private ImageView img1;
    private ImageView img2;
    private ImageView img3;
    private ImageView img4;
    private ImageView img5;
    private ImageView img6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advice);

        img1=(ImageView)findViewById(R.id.img1);
        img2=(ImageView)findViewById(R.id.img2);
        img3=(ImageView)findViewById(R.id.img3);
        img4=(ImageView)findViewById(R.id.img4);
        img5=(ImageView)findViewById(R.id.img5);
        img6=(ImageView)findViewById(R.id.img6);
        bnp = (NumberProgressBar)findViewById(R.id.numberbar1);
        bnp.setOnProgressBarListener(this);
        btnAnalyze=(Button)findViewById(R.id.btn_analyze);
        btnAnalyze.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
        });
    }

    @Override
    public void onProgressChange(int current, int max) {
        if(current == max) {
            Toast.makeText(getApplicationContext(),"计算完成！", Toast.LENGTH_SHORT).show();
//            bnp.setProgress(0);
            timer.cancel();
            new SweetAlertDialog(this)
                    .setTitleText("Completed！")
                    .setContentText("分析发现，您喜欢运动装/深色调")
                    .show();
            Picasso.with(AdviceActivity.this).load(R.drawable.advice1).into(img1);
            Picasso.with(AdviceActivity.this).load(R.drawable.advice3).into(img2);
            Picasso.with(AdviceActivity.this).load(R.drawable.advice6).into(img3);
            Picasso.with(AdviceActivity.this).load(R.drawable.advice2).into(img4);
            Picasso.with(AdviceActivity.this).load(R.drawable.advice4).into(img5);
            Picasso.with(AdviceActivity.this).load(R.drawable.advice5).into(img6);
        }
    }
}
