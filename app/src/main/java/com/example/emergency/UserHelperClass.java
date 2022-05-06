package com.example.emergency;

public class UserHelperClass {
    public String getUid() {
        return Uid;
    }

    public void setUid(String uid) {
        Uid = uid;
    }

    String Uid;
    String FullName;
    String Phone1;
    String Phone2;
    String Message;
    String Number;

    public void setMessage(String message) {
        Message = message;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }


    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getPhone1() {
        return Phone1;
    }

    public void setPhone1(String phone1) {
        Phone1 = phone1;
    }

    public String getPhone2() {
        return Phone2;
    }

    public void setPhone2(String phone2) {
        Phone2 = phone2;
    }

    public String getMessage() {
        return Message;
    }

    public void setPassword(String message) {
        Message = message;
    }

    public UserHelperClass( String fullName, String phone1, String phone2, String message, String number)  {
        FullName = fullName;
        Phone1 = phone1;
        Phone2 = phone2;
        Message = message;
        Number = number;
    }
}
