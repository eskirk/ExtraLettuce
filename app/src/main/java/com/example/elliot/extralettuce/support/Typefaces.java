package com.example.elliot.extralettuce.support;


import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;

import java.util.Hashtable;

public class Typefaces {
    private static final Hashtable<String, Typeface> cache = new Hashtable<String, Typeface>();
    private static final String TAG = Typeface.class.getSimpleName();
    private static final String YEAH_PAPA = "YeahPapa.ttf";

    public static Typeface get(Context c, String assetPath) {
        synchronized (cache) {
            if (!cache.containsKey(assetPath)) {
                try {
                    Typeface t = Typeface.createFromAsset(c.getAssets(),
                            "fonts/" + assetPath);
                    cache.put(assetPath, t);
                } catch (Exception e) {
                    Log.e(TAG, "(" + c.getPackageName() + ") Could not get typeface '" + assetPath
                            + "' because " + e.getMessage());
                    return null;
                }
            }
            return cache.get(assetPath);
        }
    }

    public static Typeface yeahPapa(Context context) {return get(context, YEAH_PAPA);}
}
