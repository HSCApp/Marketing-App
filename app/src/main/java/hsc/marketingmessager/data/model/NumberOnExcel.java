package hsc.marketingmessager.data.model;

import android.os.Parcel;

/**
 * Created by Hoang Ha on 8/9/2017.
 */

public class NumberOnExcel extends Number {
    boolean check=false;

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeByte(this.check ? (byte) 1 : (byte) 0);
    }

    public NumberOnExcel() {
    }

    protected NumberOnExcel(Parcel in) {
        super(in);
        this.check = in.readByte() != 0;
    }

    public static final Creator<NumberOnExcel> CREATOR = new Creator<NumberOnExcel>() {
        @Override
        public NumberOnExcel createFromParcel(Parcel source) {
            return new NumberOnExcel(source);
        }

        @Override
        public NumberOnExcel[] newArray(int size) {
            return new NumberOnExcel[size];
        }
    };
}
