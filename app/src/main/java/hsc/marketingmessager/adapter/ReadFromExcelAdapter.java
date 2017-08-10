package hsc.marketingmessager.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

import hsc.marketingmessager.R;
import hsc.marketingmessager.data.model.Number;
import hsc.marketingmessager.data.model.NumberOnExcel;

/**
 * Created by Hoang Ha on 8/9/2017.
 */

public class ReadFromExcelAdapter extends RecyclerView.Adapter<ReadFromExcelAdapter.ExcelViewHolder> {
    private Context mContext;
    private ArrayList<NumberOnExcel> numberOnExcels;
    protected SparseBooleanArray sparseBooleanArray;

    public ReadFromExcelAdapter(Context context, ArrayList<NumberOnExcel> numbers) {
        this.mContext = context;
        this.numberOnExcels = numbers;
        sparseBooleanArray = new SparseBooleanArray();
    }

    @Override
    public ExcelViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_read_from_excel, null, false);
        return new ExcelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ExcelViewHolder holder, int position) {
        sparseBooleanArray.put(position, numberOnExcels.get(position).isCheck());
        holder.onBind(numberOnExcels.get(position), position);
    }

    @Override
    public int getItemCount() {
        return numberOnExcels.size();
    }

    public class ExcelViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvEmail, tvPhoneNumber, tvAddress, tvBirthday, tvDescription;
        CheckBox checkBox;
        int pos = -1;

        public ExcelViewHolder(View itemView) {
            super(itemView);
            checkBox = (CheckBox) itemView.findViewById(R.id.cb);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            tvEmail = (TextView) itemView.findViewById(R.id.tv_email);
            tvPhoneNumber = (TextView) itemView.findViewById(R.id.tv_phone_number);
            tvAddress = (TextView) itemView.findViewById(R.id.tv_address);
            tvBirthday = (TextView) itemView.findViewById(R.id.tv_birthday);
            tvDescription = (TextView) itemView.findViewById(R.id.tv_description);
            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    numberOnExcels.get(pos).setCheck(checkBox.isChecked());
                }
            });
        }

        public void onBind(Number number, int pos) {
            tvName.setText(number.getName());
            tvEmail.setText(number.getEmail());
            tvPhoneNumber.setText(number.getPhoneNumber());
            tvAddress.setText(number.getAddress());
            tvBirthday.setText(number.getBirthday());
            tvDescription.setText(number.getDescription());
            checkBox.setChecked(sparseBooleanArray.get(pos));
            this.pos = pos;
        }
    }

    public ArrayList<NumberOnExcel> getNumberOnExcels() {
        return numberOnExcels;
    }

    public void setNumberOnExcels(ArrayList<NumberOnExcel> numberOnExcels) {
        this.numberOnExcels = numberOnExcels;
        notifyDataSetChanged();
    }
}
