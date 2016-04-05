package com.parse.starter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.parse.ParseObject;
import com.squareup.picasso.Picasso;

import java.util.List;


public class ProfileActivityAdapter extends ArrayAdapter<ParseObject> {


        private Context mContext;
        private List<ParseObject> mImages;

        public ProfileActivityAdapter(Context context, List<ParseObject> images){
            super(context, R.layout.activity_myphoto, images);

            mContext = context;
            mImages = images;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final ViewHolder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(mContext).inflate(
                        R.layout.activity_myphoto, null);
                holder = new ViewHolder();
                holder.homeImage = (ImageView) convertView
                        .findViewById(R.id.imageViewHome);
                convertView.setTag(holder);
            } else {

                holder = (ViewHolder) convertView.getTag();

            }


            ParseObject object = mImages.get(position);
            //get the image, picasso is a library which is used for populating images

            Picasso.with(getContext().getApplicationContext()).load(object.getParseFile("imageContent").getUrl()).noFade().into(holder.homeImage);



            return convertView;

        }


        public static class ViewHolder {
            ImageView homeImage;

        }
    }




