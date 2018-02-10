package com.example.japjot.matchthemembers;

import android.content.Context;

/**
 * Created by japjot on 2/9/18.
 */

public class Member {

    private String name;
    private int image;

    public Member(String name, Context context){

        this.name = name;

        String modifiedName = name.toLowerCase().replace(" ","");
        int actualImage = context.getResources().getIdentifier(modifiedName, "drawable", context.getPackageName());
        this.image = actualImage;

    }

    public String getName(){
        return this.name;
    }

    public int getImage(){
        return this.image;
    }


}
