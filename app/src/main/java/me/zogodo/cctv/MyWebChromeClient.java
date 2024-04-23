package me.zogodo.cctv;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.util.Log;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.widget.FrameLayout;

public class MyWebChromeClient extends WebChromeClient
{
    @Override
    public boolean onConsoleMessage(ConsoleMessage cm) {
        Log.d("MyApplication", cm.message() + " -- From line "
                + cm.lineNumber() + " of "
                + cm.sourceId());
        return true;
    }

    private View fullScreenView = null;
    FrameLayout frame = (FrameLayout)MainActivity.me.getWindow().getDecorView();
    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    public void onShowCustomView(View view, CustomViewCallback callback) {
        frame.addView(view);
        MainActivity.me.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        fullScreenView = view;
    }

    @Override
    public void onHideCustomView() {
        frame.removeView(fullScreenView);
        MainActivity.me.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
    }
}
