package com.android.ui;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Operating Clothes_table
 * Created by HarryWang on 2017/4/30.
 */

public class ClothesDAO {
    private DBHelper dbHelper=null;
    private SQLiteDatabase db=null;
    private ContentValues values=null;
    /**
     * Clothes表字段数组
     */
    String[] columns={DBInfo.Table.TYPE
            ,DBInfo.Table.RATING
            ,DBInfo.Table.LIKEMATCH
            ,DBInfo.Table.COLOR,DBInfo.Table.KEYWORDS
            ,DBInfo.Table.SEASON, DBInfo.Table.PHOTO
            };

    public ClothesDAO(Context context){
        dbHelper=new DBHelper(context);
    }

//    public static ClothesDAO _clothesDao;
//    public  static ClothesDAO getClothesDao(Context context){
//        if(_clothesDao==null)
//        {
//            _clothesDao=new ClothesDAO(context);
//        }
//        return _clothesDao;
//    }


    /**
     *新增衣服信息
     * @param clothes
     * @return
     */
    public long insertClothes(Clothes clothes){
        //获得SQLiteDatebase进行数据库操作
        db=dbHelper.getWritableDatabase();
        //参数绑定对象
        values=new ContentValues();
        values.put(DBInfo.Table.TYPE,clothes.getType());
        values.put(DBInfo.Table.COLOR,clothes.getColor());
        values.put(DBInfo.Table.KEYWORDS,clothes.getKeywords());
        values.put(DBInfo.Table.SEASON,clothes.getSeason());
        values.put(DBInfo.Table.PHOTO,clothes.getPhoto());
        values.put(DBInfo.Table.UPORDOWN,clothes.getUpordown());
        values.put(DBInfo.Table.RATING,clothes.getRating());
        values.put(DBInfo.Table.LIKEMATCH,0);

        //进行插入操作，返回行号
        long rowId=db.insert(DBInfo.Table.CLOTHES_TABLE,DBInfo.Table.TYPE,values);
        //释放资源
        db.close();

        return rowId;
    }

    /**
     * 修改likematch属性
     */
    public void updateClothes(String Photopath,Boolean check){
        //获得SQLiteDatebase进行数据库操作
        db=dbHelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        int che=0;
        if (check)
            che=1;
        values.put("likematch",che);
        db.update(DBInfo.Table.CLOTHES_TABLE,values,"photo=?",new String[]{Photopath});
        //释放资源
        db.close();
    }


    /**
     * 根据衣服类型获得衣服信息
     * @param type
     * @return
     */
    public List<Clothes> findClothesByType(String type){
        db=dbHelper.getReadableDatabase();
        dbHelper.getReadableDatabase();
        List<Clothes>clothesList=null;
        Clothes clothes=null;
        Cursor cursor = db.rawQuery("select * from clothes where type=?", new String[]{type});

        if(cursor!=null&&cursor.getCount()>0){
            clothesList=new ArrayList<Clothes>(cursor.getCount());
            while(cursor.moveToNext()){
                clothes.setType(cursor.getString(cursor.getColumnIndex(DBInfo.Table.TYPE)));
                clothes.setColor(cursor.getString(cursor.getColumnIndex(DBInfo.Table.COLOR)));
                clothes.setKeywords(cursor.getString(cursor.getColumnIndex(DBInfo.Table.KEYWORDS)));
                clothes.setSeason(cursor.getString(cursor.getColumnIndex(DBInfo.Table.SEASON)));

                clothesList.add(clothes);
            }
        }
        //游标关闭
        cursor.close();
        db.close();
        return clothesList;

    }


    /**
     * 获得所有衣服信息
     * @return
     */
    public List<Clothes> findAllClothes(){
        db=dbHelper.getReadableDatabase();
        dbHelper.getReadableDatabase();
        List<Clothes>clothesList=null;
        Clothes clothes=null;
        Cursor cursor= db.query(DBInfo.Table.CLOTHES_TABLE,columns,null,null,null,null,null);
//        int checkCount = cursor.getCount();
        if(cursor!=null&&cursor.getCount()>0){
            clothesList=new ArrayList<Clothes>(cursor.getCount());
            while(cursor.moveToNext()){
                clothes=new Clothes();

                clothes.setType(cursor.getString(cursor.getColumnIndex(DBInfo.Table.TYPE)));
                clothes.setColor(cursor.getString(cursor.getColumnIndex(DBInfo.Table.COLOR)));
                clothes.setKeywords(cursor.getString(cursor.getColumnIndex(DBInfo.Table.KEYWORDS)));
                clothes.setSeason(cursor.getString(cursor.getColumnIndex(DBInfo.Table.SEASON)));
                clothes.setPhoto(cursor.getString(cursor.getColumnIndex(DBInfo.Table.PHOTO)));


                clothesList.add(clothes);
            }
        }
        //游标关闭
        cursor.close();
        db.close();
        return clothesList;
    }

    /**
     * 获取搭配组
     */
    public List<ClothesMatch> MatchClothes(){
        db=dbHelper.getReadableDatabase();
        dbHelper.getReadableDatabase();
        List<ClothesMatch>clothesList=null;
        ClothesMatch clothes=null;

        Cursor cursorUp = db.rawQuery("select * from clothes where upordown='上装'",new String[]{});
        Cursor cursorDown = db.rawQuery("select * from clothes where upordown='下装'",new String[]{});

        if(cursorUp!=null&&cursorUp.getCount()>0){
            clothesList=new ArrayList<ClothesMatch>(cursorUp.getCount()+cursorDown.getCount());
            while(cursorUp.moveToNext()&&cursorDown.moveToNext()){
                clothes=new ClothesMatch();
                clothes.setTopType(cursorUp.getString(cursorUp.getColumnIndex(DBInfo.Table.TYPE)));
                clothes.setTopColor(cursorUp.getString(cursorUp.getColumnIndex(DBInfo.Table.COLOR)));
                clothes.setTopKeywords(cursorUp.getString(cursorUp.getColumnIndex(DBInfo.Table.KEYWORDS)));
                clothes.setTopSeason(cursorUp.getString(cursorUp.getColumnIndex(DBInfo.Table.SEASON)));
                clothes.setTopPhoto(cursorUp.getString(cursorUp.getColumnIndex(DBInfo.Table.PHOTO)));
                clothes.setTopupordown(cursorUp.getString(cursorUp.getColumnIndex(DBInfo.Table.UPORDOWN)));
                clothes.setToprating(cursorUp.getFloat(cursorUp.getColumnIndex(DBInfo.Table.RATING)));
                clothes.setToplikematch(cursorUp.getInt(cursorUp.getColumnIndex(DBInfo.Table.LIKEMATCH)));
                clothes.setId(cursorUp.getInt(cursorUp.getColumnIndex(DBInfo.Table.ID)));

                clothes.setBottomType(cursorDown.getString(cursorDown.getColumnIndex(DBInfo.Table.TYPE)));
                clothes.setBottomColor(cursorDown.getString(cursorDown.getColumnIndex(DBInfo.Table.COLOR)));
                clothes.setBottomKeywords(cursorDown.getString(cursorDown.getColumnIndex(DBInfo.Table.KEYWORDS)));
                clothes.setBottomSeason(cursorDown.getString(cursorDown.getColumnIndex(DBInfo.Table.SEASON)));
                clothes.setBottomPhoto(cursorDown.getString(cursorDown.getColumnIndex(DBInfo.Table.PHOTO)));
                clothes.setBottomupordown(cursorDown.getString(cursorDown.getColumnIndex(DBInfo.Table.UPORDOWN)));
                clothes.setBottomrating(cursorDown.getFloat(cursorDown.getColumnIndex(DBInfo.Table.RATING)));

                clothesList.add(clothes);
            }
            /**
             * 实例化列表项内容时无搭配的物品存在空引用问题，暂时注释
             * 即舍掉无配对的衣物
             */
//            if (cursorUp.moveToNext()==true&&cursorDown.moveToNext()==false){
//                while(cursorUp.moveToNext()){
//                    clothes=new ClothesMatch();
//                    clothes.setTopType(cursorUp.getString(cursorUp.getColumnIndex(DBInfo.Table.TYPE)));
//                    clothes.setTopColor(cursorUp.getString(cursorUp.getColumnIndex(DBInfo.Table.COLOR)));
//                    clothes.setTopKeywords(cursorUp.getString(cursorUp.getColumnIndex(DBInfo.Table.KEYWORDS)));
//                    clothes.setTopSeason(cursorUp.getString(cursorUp.getColumnIndex(DBInfo.Table.SEASON)));
//                    clothes.setTopPhoto(cursorUp.getString(cursorUp.getColumnIndex(DBInfo.Table.PHOTO)));
//                    clothes.setTopupordown(cursorUp.getString(cursorUp.getColumnIndex(DBInfo.Table.UPORDOWN)));
//
//                    clothesList.add(clothes);
//                }
//            }
//            if (cursorUp.moveToNext()==false&&cursorDown.moveToNext()==true){
//                while(cursorDown.moveToNext()){
//                    clothes=new ClothesMatch();
//                    clothes.setBottomType(cursorDown.getString(cursorDown.getColumnIndex(DBInfo.Table.TYPE)));
//                    clothes.setBottomColor(cursorDown.getString(cursorDown.getColumnIndex(DBInfo.Table.COLOR)));
//                    clothes.setBottomKeywords(cursorDown.getString(cursorDown.getColumnIndex(DBInfo.Table.KEYWORDS)));
//                    clothes.setBottomSeason(cursorDown.getString(cursorDown.getColumnIndex(DBInfo.Table.SEASON)));
//                    clothes.setBottomPhoto(cursorDown.getString(cursorDown.getColumnIndex(DBInfo.Table.PHOTO)));
//                    clothes.setBottomupordown(cursorDown.getString(cursorDown.getColumnIndex(DBInfo.Table.UPORDOWN)));
//
//                    clothesList.add(clothes);
//                }
//            }
        }
        //游标关闭
        cursorUp.close();
        cursorDown.close();
        db.close();
        return clothesList;
    }
}
