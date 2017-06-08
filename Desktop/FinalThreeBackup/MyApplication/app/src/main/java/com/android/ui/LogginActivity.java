package com.android.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class LogginActivity extends AppCompatActivity {
    private Button btnLogIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loggin);
        getSupportActionBar().hide();

        btnLogIn=(Button)findViewById(R.id.btn_login);

        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LogginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(LogginActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
