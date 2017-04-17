package com.turktech.dubizzletask.enums;

/**
 * Created by Adeel Turk on 14/04/2017.
 */

public enum ScreenSizeCategory{

    SMALL_SCREEN(0),NORMAL_SCREEN(1),LARGE_SCREEN(2),EXTRA_LARGE_SCREEN(3),OTHER_SCREEN(4);

    public int val;

    ScreenSizeCategory(int val){

        this.val=val;
    }

}
