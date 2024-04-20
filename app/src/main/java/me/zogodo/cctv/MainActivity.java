package me.zogodo.cctv;

import android.os.Bundle;
import android.view.KeyEvent;
import com.tencent.smtt.sdk.WebView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity
{
    public static String[] cctv_urls = {
        "https://tv.cctv.com/live/cctv1/m/",
        "https://tv.cctv.com/live/cctv2/m/",
        "https://tv.cctv.com/live/cctv13/m/", //没有cctv3
        "https://tv.cctv.com/live/cctv4/m/",
        "https://tv.cctv.com/live/cctv5/m/",
        "https://tv.cctv.com/live/cctv5plus/m/",
        "https://tv.cctv.com/live/cctv7/m/",
        "https://tv.cctv.com/live/cctv13/m/", //没有cctv8
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
    public static int c_cctv13 = 12;
    public static MainActivity me;
    public static int channel = c_cctv13;
    public static WebView webView = null;
    long exitTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        MainActivity.me = this;

        webView = new MyWebView(this);
        webView.loadUrl(cctv_urls[channel]);
        //webView.loadUrl("https://cn.bing.com/?ensearch=1&FORM=BEHPTB");
        this.setContentView(webView);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Toast.makeText(getApplicationContext(), "keyCode:" + keyCode, Toast.LENGTH_SHORT).show();

        switch (keyCode) {
            case KeyEvent.KEYCODE_0:
                channel = c_cctv13;
                break;
            case KeyEvent.KEYCODE_1:
            case KeyEvent.KEYCODE_2:
            case KeyEvent.KEYCODE_3:
            case KeyEvent.KEYCODE_4:
            case KeyEvent.KEYCODE_5:
            case KeyEvent.KEYCODE_6:
            case KeyEvent.KEYCODE_7:
            case KeyEvent.KEYCODE_8:
            case KeyEvent.KEYCODE_9:
                channel = keyCode - KeyEvent.KEYCODE_1;
                break;
            case KeyEvent.KEYCODE_DPAD_UP:
            case KeyEvent.KEYCODE_VOLUME_UP:
            case KeyEvent.KEYCODE_MENU:
                channel = (channel+1) % cctv_urls.length;
                break;
            case KeyEvent.KEYCODE_DPAD_DOWN:
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                channel = (channel-1) % cctv_urls.length;
                break;
            default:
                return super.onKeyDown(keyCode, event);
        }
        webView.loadUrl(cctv_urls[channel]); //换台
        return true;
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
