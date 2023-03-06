package com.example.yp_trifonova;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class AdapterQuotes extends BaseAdapter {

    private Context nContext;
    List<QueteMaska> maskList;

    public AdapterQuotes(Context nContext, List<QueteMaska> maskList) {
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

        View v = View.inflate(nContext,R.layout.maska_zagolovka,null);

        TextView title = v.findViewById(R.id.name_zagolovok);
        ImageView Image = v.findViewById(R.id.Imgpfoto);
        TextView description = v.findViewById(R.id.kratko_zagolovok);

        QueteMaska maskQuote = maskList.get(position);
        title.setText(maskQuote.getTitle());


        Image.setImageURI(Uri.parse(maskQuote.getImage()));
        if(maskQuote.getImage().toString().equals("null"))
        {
            Image.setImageResource(R.drawable.picture);
        }
        else
        {
            //Image.setImageURI(Uri.parse(maskQuote.getImage()));
        }
        description.setText(maskQuote.getDescription());
        return v;
    }
}
