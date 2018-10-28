package com.example.randyperrone.starwarscharacterbio.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Comparator;

public class CharacterData implements Parcelable{
    private String name;
    private String gender;
    private String height;
    private String mass;
    private String birthYear;
    private String eyeColor;
    private String hairColor;
    private String skinColor;

    public CharacterData(String name, String gender, String height, String mass, String birthYear, String eyeColor, String hairColor, String skinColor) {
        this.name = name;
        this.gender = gender;
        this.height = height;
        this.mass = mass;
        this.birthYear = birthYear;
        this.eyeColor = eyeColor;
        this.hairColor = hairColor;
        this.skinColor = skinColor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getMass() {
        return mass;
    }

    public void setMass(String mass) {
        this.mass = mass;
    }

    public String getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(String birthYear) {
        this.birthYear = birthYear;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(String eyeColor) {
        this.eyeColor = eyeColor;
    }

    public String getHairColor() {
        return hairColor;
    }

    public void setHairColor(String hairColor) {
        this.hairColor = hairColor;
    }

    public String getSkinColor() {
        return skinColor;
    }

    public void setSkinColor(String skinColor) {
        this.skinColor = skinColor;
    }

    public static Comparator<CharacterData> CharacterNameComparatorAscending = new Comparator<CharacterData>(){
        public int compare(CharacterData char1, CharacterData char2) {
            String charName1 = char1.getName().toUpperCase();
            String charName2 = char2.getName().toUpperCase();

            //ascending order
            return charName1.compareTo(charName2);
        }
    };

    public static Comparator<CharacterData> CharacterNameComparatorDescending = new Comparator<CharacterData>(){
        public int compare(CharacterData char1, CharacterData char2) {
            String charName1 = char1.getName().toUpperCase();
            String charName2 = char2.getName().toUpperCase();

            //descending order
            return charName2.compareTo(charName1);
        }
    };

    private CharacterData(Parcel in){
        this.name = in.readString();
        this.gender = in.readString();
        this.height = in.readString();
        this.mass = in.readString();
        this.birthYear = in.readString();
        this.eyeColor = in.readString();
        this.hairColor = in.readString();
        this.skinColor = in.readString();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(gender);
        parcel.writeString(height);
        parcel.writeString(mass);
        parcel.writeString(birthYear);
        parcel.writeString(eyeColor);
        parcel.writeString(hairColor);
        parcel.writeString(skinColor);
    }

    public static final Parcelable.Creator<CharacterData> CREATOR = new Parcelable.Creator<CharacterData>(){

        @Override
        public CharacterData createFromParcel(Parcel parcel) {
            return new CharacterData(parcel);
        }

        @Override
        public CharacterData[] newArray(int i) {
            return new CharacterData[i];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }
}
