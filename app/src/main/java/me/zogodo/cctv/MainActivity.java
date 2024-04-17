package me.zogodo.cctv;

import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity
{
    public static String[] cctv_urls = {
        "https://tv.cctv.com/live/cctv1/m/",
        "https://tv.cctv.com/live/cctv2/m/",
        "https://tv.cctv.com/live/cctv4/m/",
        "https://tv.cctv.com/live/cctv5/m/",
        "https://tv.cctv.com/live/cctv5plus/m/",
        "https://tv.cctv.com/live/cctv7/m/",
        "https://tv.cctv.com/live/cctvjilu/m/",
        "https://tv.cctv.com/live/cctv10/m/",
        "https://tv.cctv.com/live/cctv11/m/",
        "https://tv.cctv.com/live/cctv12/m/",
        "https://tv.cctv.com/live/cctv13/m/",
        "https://tv.cctv.com/live/cctvchild/m/",
        "https://tv.cctv.com/live/cctv15/m/",
        "https://tv.cctv.com/live/cctv16/m/",
        "https://tv.cctv.com/live/cctv17/m/",
    };
    public static MainActivity me;
    public static int channel = 10; //cctv13
    public static WebView webView = null;
    long exitTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        MainActivity.me = this;

        webView = new MyWebView(this);
        webView.loadUrl(cctv_urls[channel]);
        this.setContentView(webView);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_DPAD_UP)
            channel = (channel+1) % cctv_urls.length;
        if (keyCode == KeyEvent.KEYCODE_DPAD_DOWN)
            channel = (channel-1) % cctv_urls.length;
        if (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_DOWN)
            webView.loadUrl(cctv_urls[channel]); //换台
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        if (webView != null && webView.canGoBack()) {
            webView.goBack();
        } else {
            // 判断是否可后退，是则后退，否则退出程序
            if (((System.currentTimeMillis() - exitTime) > 3000)) {
                Toast.makeText(getApplicationContext(), "再按一次返回 退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
        }
    }
}
