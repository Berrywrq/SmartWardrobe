package com.android.ui;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import cn.pedant.SweetAlert.SweetAlertDialog;


public class ControlFragment extends Fragment {
    private Button BtnFold; //折叠
    private Button BtnDisinfect; //杀菌
    private Button BtnDehumidification; //除湿

    public ControlFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_control, container, false);

        BtnFold=(Button)v.findViewById(R.id.btn1);
        BtnDisinfect=(Button)v.findViewById(R.id.btn2);
        BtnDehumidification=(Button)v.findViewById(R.id.btn3);

        BtnFold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SweetAlertDialog(getActivity(), SweetAlertDialog.SUCCESS_TYPE)
                        .setTitleText("Succeed!")
                        .setContentText("将为您自动折叠！")
                        .show();
            }
        });
        BtnDisinfect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Options!")
                        .setContentText("请确认操作")
                        .setCancelText("自动杀菌")
                        .setConfirmText("杀菌30秒")
                        .showCancelButton(true)
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                new SweetAlertDialog(getActivity(), SweetAlertDialog.SUCCESS_TYPE)
                                        .setTitleText("Succeed!")
                                        .setContentText("即将开始杀菌！")
                                        .show();
                                sweetAlertDialog.cancel();
                            }
                        })
                        .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                new SweetAlertDialog(getActivity(), SweetAlertDialog.SUCCESS_TYPE)
                                        .setTitleText("Succeed!")
                                        .setContentText("即将开始杀菌！")
                                        .show();
                                sDialog.cancel();
                            }
                        })
                        .show();

            }
        });
        BtnDehumidification.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("Options!")
                            .setContentText("请确认操作")
                            .setCancelText("自动除湿")
                            .setConfirmText("除湿30秒")
                            .showCancelButton(true)
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    new SweetAlertDialog(getActivity(), SweetAlertDialog.SUCCESS_TYPE)
                                            .setTitleText("Succeed!")
                                            .setContentText("即将开始除湿！")
                                            .show();
                                    sweetAlertDialog.cancel();
                                }
                            })
                            .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {
                                    new SweetAlertDialog(getActivity(), SweetAlertDialog.SUCCESS_TYPE)
                                            .setTitleText("Succeed!")
                                            .setContentText("即将开始除湿！")
                                            .show();
                                    sDialog.cancel();
                                }
                            })
                            .show();
                }
        });
        return v;
    }
}
