package com.android.ui;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;


public class CommunityFragment extends Fragment {
        private Button btnBuy;
        private Button btnAdvice;


//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        Toast.makeText(getActivity(), "天气数据拉取成功！", Toast.LENGTH_SHORT).show();
//    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_community, container, false);
        btnBuy=(Button)v.findViewById(R.id.btn_buy);
        btnAdvice=(Button)v.findViewById(R.id.btn_advice);
        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getActivity(),BuyClothesActivity.class);
                startActivity(intent);
            }
        });

        btnAdvice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),AdviceActivity.class);
                startActivity(intent);
            }
        });

        WebView webview=(WebView) v.findViewById(R.id.webview);
        webview.setWebViewClient(new WebViewClient());
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setUseWideViewPort(true);
        webview.loadUrl("http://kidulty.com");

        return v;
    }


}
