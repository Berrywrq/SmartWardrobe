package com.android.ui;

import android.app.ListActivity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;

import static com.android.ui.R.id.checkBox;

public class ClothesListActivity extends ListActivity {
    private ArrayList<ClothesMatch> ClothesList;
    private CheckBox checkBox;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ClothesDAO cloDAO=new ClothesDAO(this.getBaseContext());
//        ClothesList=(ArrayList<Clothes>)cloDAO.findAllClothes();
        ClothesList=(ArrayList<ClothesMatch>)cloDAO.MatchClothes();
        ClothesAdapter adapter=new ClothesAdapter(ClothesList);
        setListAdapter(adapter);

    }

    private class ClothesAdapter extends ArrayAdapter<ClothesMatch> {

        public ClothesAdapter(ArrayList<ClothesMatch> Clothes) {
            super(ClothesListActivity.this, 0, Clothes);
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView==null){
                convertView=ClothesListActivity.this.getLayoutInflater()
                        .inflate(R.layout.list_item_clothesmatch,null);
            }

            ClothesMatch c=getItem(position);

            ImageView imgUpPhoto=(ImageView)convertView.findViewById(R.id.img_up_photo);
            ImageView imgDownPhoto=(ImageView)convertView.findViewById(R.id.img_down_photo);
            String pathUp=c.getTopPhoto();
            String pathDown=c.getBottomPhoto();

            Picasso.with(ClothesListActivity.this).load(new File(pathUp)).resize(200,200).centerCrop().into(imgUpPhoto);
            Picasso.with(ClothesListActivity.this).load(new File(pathDown)).resize(200,200).centerCrop().into(imgDownPhoto);

            TextView txtUpType=(TextView)convertView.findViewById(R.id.txt_up_type);
            TextView txtDownType=(TextView)convertView.findViewById(R.id.txt_down_type);
            txtUpType.setText(c.getTopType());
            txtDownType.setText(c.getBottomType());
            TextView txtUpColor=(TextView)convertView.findViewById(R.id.txt_up_color);
            TextView txtDownColor=(TextView)convertView.findViewById(R.id.txt_down_color);
            txtUpColor.setText(c.getTopColor());
            txtDownColor.setText(c.getBottomColor());
            TextView txtUpKeywords=(TextView)convertView.findViewById(R.id.txt_up_keywords);
            TextView txtDownKeywords=(TextView)convertView.findViewById(R.id.txt_down_keywords);
            txtUpKeywords.setText(c.getTopKeywords());
            txtDownKeywords.setText(c.getBottomKeywords());
            TextView txtUpSeason=(TextView)convertView.findViewById(R.id.txt_up_season);
            TextView txtDownSeason=(TextView)convertView.findViewById(R.id.txt_down_season);
            txtUpSeason.setText(c.getTopSeason());
            txtDownSeason.setText(c.getBottomSeason());

            RatingBar rtbUp=(RatingBar)convertView.findViewById(R.id.rtb_up);
            RatingBar rtbDown=(RatingBar)convertView.findViewById(R.id.rtb_down);
            rtbUp.setRating(c.getToprating());
            rtbDown.setRating(c.getBottomrating());

            checkBox=(CheckBox)convertView.findViewById(R.id.checkBox);

            boolean check=false;
            if (c.getToplikematch()==1)
                check=true;
            checkBox.setChecked(check);
            return convertView;
        }

    }
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        ClothesMatch c=((ClothesAdapter)getListAdapter()).getItem(position);
        checkBox=(CheckBox)l.getChildAt(position-l.getFirstVisiblePosition()).findViewById(R.id.checkBox);
        boolean flag=checkBox.isChecked();
        if (!flag) {
            checkBox.setChecked(true);
        }
        if (flag) {
            checkBox.setChecked(false);
        }
        flag=checkBox.isChecked();
        ClothesDAO cloDAO=new ClothesDAO(this.getBaseContext());
        cloDAO.updateClothes(c.getTopPhoto(),flag);
    }

}
