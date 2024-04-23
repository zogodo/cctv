package me.zogodo.cctv;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;

import org.mozilla.geckoview.GeckoRuntime;
import org.mozilla.geckoview.GeckoRuntimeSettings;
import org.mozilla.geckoview.GeckoSession;
import org.mozilla.geckoview.GeckoView;
import org.mozilla.geckoview.WebExtension;

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
    long exitTime = 0;

    GeckoView view_gecko = null;
    static GeckoRuntime runtime = null;
    GeckoSession session = null;
    private static WebExtension.Port mPort;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        view_gecko = new GeckoView(this);

        GeckoRuntimeSettings.Builder builder = new GeckoRuntimeSettings.Builder()
                .javaScriptEnabled(true)
                .doubleTapZoomingEnabled(false)
                .inputAutoZoomEnabled(false)
                .consoleOutput(true);
        session = new GeckoSession();
        runtime = GeckoRuntime.create(this,builder.build());
        session.open(runtime);
        view_gecko.setSession(session);
        session.getSettings().setAllowJavascript(true);
        session.getPanZoomController().setIsLongpressEnabled(false);
        session.loadUri(cctv_urls[channel]);

        setContentView(view_gecko);
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
        session.loadUri(cctv_urls[channel]); //换台
        return true;
    }

    @Override
    public void onBackPressed() {
        if (((System.currentTimeMillis() - exitTime) > 3000)) {
            Toast.makeText(getApplicationContext(), "再按一次返回 退出程序", Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }
    }
}
