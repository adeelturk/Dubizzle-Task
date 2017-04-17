package com.turktech.dubizzletask.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.turktech.dubizzletask.MainApplication;
import com.turktech.dubizzletask.R;
import com.turktech.dubizzletask.common.Utils;
import com.turktech.dubizzletask.enums.ScreenSizeCategory;
import com.turktech.dubizzletask.model.FlickrImage;

/**
 * Created by Adeel Turk on 14/04/2017.
 */

public class GalleryRowViewHolder extends RecyclerView.ViewHolder {


    private ImageView galleryImage;
    private TextView name;

    public RelativeLayout container;

    public  GalleryRowViewHolder(View v){

        super(v);

        galleryImage=(ImageView)v.findViewById(R.id.galleryImage);
        name=(TextView) v.findViewById(R.id.name);


        container=(RelativeLayout)v.findViewById(R.id.container);
    }



    public void invalidate(FlickrImage data){


        if(Utils.getScreenSize()== ScreenSizeCategory.LARGE_SCREEN.val||
                Utils.getScreenSize()== ScreenSizeCategory.EXTRA_LARGE_SCREEN.val ){

            if(data.urlMedium.trim().length()>0) {
                Glide.with(MainApplication.context).load(data.urlMedium).into(galleryImage);
            }else{

                Glide.with(MainApplication.context).load(data.url).into(galleryImage);
            }

        }else{

            Glide.with(MainApplication.context).load(data.url).into(galleryImage);
        }

        if(name.getText().toString().trim().length()>0) {
            name.setText(data.title);
        }else{
            name.setText("Untitled");

        }
    }
}
