package com.example.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.viewflipper.R;

import java.util.List;

/**
 * @ClassName BgAdapter
 * @Description TODO
 * @Author guoxw
 * @Date 2020/3/13 10:04
 */
public class BgAdapter extends BaseAdapter {

    private List<Integer> data;

    private Context context;

    private int nowSelect = 0;

    public BgAdapter(List<Integer> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder ;

        if(convertView == null){
            convertView = View.inflate(context, R.layout.bg_item,null);
            holder = new Holder();
            holder.imageView = convertView.findViewById(R.id.imgBg);
            convertView.setTag(holder);
        }else{
            holder = (Holder) convertView.getTag();
        }
        Log.d("select: - - >",nowSelect+"") ;

        if(position == nowSelect){
            holder.imageView.setVisibility(View.VISIBLE);
        }else{
            holder.imageView.setVisibility(View.INVISIBLE);
        }

        holder.imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.record_item_select_bg));


        return convertView;
    }

    class  Holder{
        ImageView imageView;
    }



    public void  moveTop(){
        nowSelect --;
        notifyDataSetChanged();
    }

    public void moveBottom(){
        nowSelect ++;
        notifyDataSetChanged();
    }

    public int getNowSelect(){
        return  nowSelect;
    }
}
