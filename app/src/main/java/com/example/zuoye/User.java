package com.example.zuoye;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class User implements Parcelable {

    private String pwd;
    private String name;

    public User() {
    }

    public User(Parcel in) {
        pwd = in.readString();;
        name = in.readString();
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(pwd);
        dest.writeString(name);
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>(){
        //实现从Parcel容器中读取传递数据值，封装成Parcelable对象返回逻辑层
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
