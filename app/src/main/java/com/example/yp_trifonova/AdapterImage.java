package com.example.yp_trifonova;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class AdapterImage extends BaseAdapter {

    private Context mContext;
    List<MaskaImage> maskList;

    public AdapterImage(Context mContext, List<MaskaImage> maskList) {
        this.mContext = mContext;
        this.maskList = maskList;
    }

    @Override
    public int getCount() {
        return maskList.size();
    }

    @Override
    public Object getItem(int i) {
        return maskList.get(i);
    }

    @Override
    public long getItemId(int i)
    {
        return maskList.get(i).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = View.inflate(mContext,R.layout.maska_image_profile,null);

        ImageView Image = v.findViewById(R.id.foto);
        TextView dateCreat = v.findViewById(R.id.Time);



        MaskaImage maskProfileImage  = maskList.get(position);

        if(maskProfileImage.getImageProfile().exists()){

            Bitmap myBitmap = BitmapFactory.decodeFile(maskProfileImage.getImageProfile().getAbsolutePath());
            Image.setImageBitmap(myBitmap);
        }
        dateCreat.setText(maskProfileImage.getData());

        return v;
    }

}
