package com.wjf.ivy;

import android.util.Log;

public class BugClass {
    public String bug(){
        String str = "这里的bug没修复好";
        Log.e("BugClass", str);
        return str;
    }
}
