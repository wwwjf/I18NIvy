package com.wjf.ivy;

import android.util.Log;

public class BugClass {
    public String bug(){
        String str = null;
        Log.e("BugClass", "this is a bug class");
        return "this is a bug class";
    }
}
