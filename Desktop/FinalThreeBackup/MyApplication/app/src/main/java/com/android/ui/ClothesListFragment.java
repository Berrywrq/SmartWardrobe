package com.android.ui;

import android.app.ListFragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;


public class ClothesListFragment extends ListFragment {
//    private ArrayList<Clothes> ClothesList;
    private ArrayList<Clothes> ClothesList;

    public ClothesListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ClothesDAO cloDAO=new ClothesDAO(getActivity().getBaseContext());
//        ClothesList=(ArrayList<Clothes>)cloDAO.findAllClothes();
        ClothesList=(ArrayList<Clothes>)cloDAO.findAllClothes();
        ClothesAdapter adapter=new ClothesAdapter(ClothesList);
        setListAdapter(adapter);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = super.onCreateView(inflater, container, savedInstanceState);
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_clothes_list, container, false);
        return  v;

    }

    private class ClothesAdapter extends ArrayAdapter<Clothes> {

        public ClothesAdapter(ArrayList<Clothes> Clothes) {
            super(getActivity(), 0, Clothes);
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView==null){
                convertView=getActivity().getLayoutInflater()
                        .inflate(R.layout.list_item_clothes,null);
            }

            Clothes c=getItem(position);

            ImageView imgPhoto=(ImageView)convertView.findViewById(R.id.img_photo);
            String pathUp=c.getPhoto();
            Picasso.with(getActivity()).load(new File(pathUp)).resize(200,200).centerCrop().into(imgPhoto);

            TextView txtType=(TextView)convertView.findViewById(R.id.txt_type);
            txtType.setText(c.getType());
            TextView txtColor=(TextView)convertView.findViewById(R.id.txt_color);
            txtColor.setText(c.getColor());
            TextView txtKeywords=(TextView)convertView.findViewById(R.id.txt_keywords);
            txtKeywords.setText(c.getKeywords());
            TextView txtSeason=(TextView)convertView.findViewById(R.id.txt_season);
            txtSeason.setText(c.getSeason());
            return convertView;
        }



    }
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Clothes c=((ClothesAdapter)getListAdapter()).getItem(position);
    }
}
