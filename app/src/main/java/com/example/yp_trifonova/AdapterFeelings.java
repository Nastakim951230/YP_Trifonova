package com.example.yp_trifonova;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class AdapterFeelings extends BaseAdapter {

    private Context nContext;
    List<MaskaFeelings> maskList;

    public AdapterFeelings(Context nContext, List<MaskaFeelings> maskList) {
        this.nContext = nContext;
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

        View v = View.inflate(nContext,R.layout.maska_nastroenie,null);
        TextView title = v.findViewById(R.id.TextNastroenie);
        ImageView Image = v.findViewById(R.id.foto);

        MaskaFeelings maskaFeelings = maskList.get(position);
        title.setText(maskaFeelings.getTitle());


        Image.setImageURI(Uri.parse(maskaFeelings.getImage()));
        if(maskaFeelings.getImage().toString().equals("null"))
        {
            Image.setImageResource(R.drawable.picture);
        }
        else
        {

            Image.setImageURI(Uri.parse(maskaFeelings.getImage()));
        }
        return v;
    }
}
