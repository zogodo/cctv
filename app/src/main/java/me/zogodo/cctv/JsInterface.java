package me.zogodo.cctv;

import android.webkit.JavascriptInterface;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class JsInterface
{
    @JavascriptInterface
    public static String GetString() {
 
        Map<String, Integer> map = new HashMap<>();
        map.put("aa", 10);
        map.put("bb", 23);

        JSONObject json = new JSONObject(map);
        return  json.toString();
    }

}
