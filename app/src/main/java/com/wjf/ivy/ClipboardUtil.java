package com.wjf.ivy;

import android.content.ClipboardManager;
import android.content.Context;

public class ClipboardUtil {


    /**
     * 实现文本复制功能
     * add by wangqianzhou
     * @param content
     */
    public static void copy(String content, Context context)
    {
// 得到剪贴板管理器
        ClipboardManager cmb = (ClipboardManager)context.getSystemService(Context.CLIPBOARD_SERVICE);
        cmb.setText(content.trim());
    }
    /**
     * 实现粘贴功能
     * add by wangqianzhou
     * @param context
     * @return
     */
    public static String paste(Context context)
    {
// 得到剪贴板管理器
        ClipboardManager cmb = (ClipboardManager)context.getSystemService(Context.CLIPBOARD_SERVICE);
        if (cmb == null){
            return "";
        }
        return cmb.getText().toString().trim();
    }

}
