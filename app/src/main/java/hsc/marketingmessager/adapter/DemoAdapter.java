package hsc.marketingmessager.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import hsc.marketingmessager.R;

/**
 * Created by Hoang Ha on 8/9/2017.
 */

public class DemoAdapter extends RecyclerView.Adapter<DemoAdapter.MyView> {
    String[] strings;
    public DemoAdapter(String[] s)
    {
        this.strings=s;
    }
    @Override
    public MyView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user,null,false);
        return new MyView(view);
    }

    @Override
    public void onBindViewHolder(MyView holder, int position) {
            holder.bind(strings[position]);
    }

    @Override
    public int getItemCount() {
        return strings.length;
    }

    public static  class MyView extends RecyclerView.ViewHolder
    {
        TextView tv1,tv2,tv3,tv4,tv5;
        public MyView(View itemView) {
            super(itemView);
            tv1=(TextView) itemView.findViewById(R.id.tv1);
            tv2=(TextView) itemView.findViewById(R.id.tv2);
            tv3=(TextView) itemView.findViewById(R.id.tv3);
            tv4=(TextView) itemView.findViewById(R.id.tv4);
        }
        public void bind(String s)
        {
            s="ScrollView and HorizontalScrollView has same attributes, the only difference is scrollView scroll the child items in vertical direction while horizontal scroll view scroll the child items in horizontal direction";
            tv1.setText(s);
            tv2.setText(s);
            tv3.setText(s);
            tv4.setText(s);
        }
    }
}
