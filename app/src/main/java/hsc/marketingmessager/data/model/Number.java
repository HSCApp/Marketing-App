package hsc.marketingmessager.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Hoang Ha on 8/9/2017.
 */

public class Number implements Parcelable {
    @SerializedName("name")
    public String name;
    @SerializedName("email")
    public String email;
    @SerializedName("address")
    public String address;
    @SerializedName("phoneNumber")
    public String phoneNumber;
    @SerializedName("birthday")
    public String birthday;
    @SerializedName("group")
    public String group;
    @SerializedName("description")
    public String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.email);
        dest.writeString(this.address);
        dest.writeString(this.phoneNumber);
        dest.writeString(this.birthday);
        dest.writeString(this.group);
        dest.writeString(this.description);
    }

    public Number() {
    }

    protected Number(Parcel in) {
        this.name = in.readString();
        this.email = in.readString();
        this.address = in.readString();
        this.phoneNumber = in.readString();
        this.birthday = in.readString();
        this.group = in.readString();
        this.description = in.readString();
    }

    public String toString() {
        return name + ", " + email + ", " + address + ", " + phoneNumber + ", " + birthday + ", " + group + ", " + description;
    }
}
