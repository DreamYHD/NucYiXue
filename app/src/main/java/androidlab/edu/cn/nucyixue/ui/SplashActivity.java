package androidlab.edu.cn.nucyixue.ui;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.avos.avoscloud.im.v2.AVIMClient;
import com.avos.avoscloud.im.v2.AVIMException;
import com.avos.avoscloud.im.v2.callback.AVIMClientCallback;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;
import androidlab.edu.cn.nucyixue.R;
import androidlab.edu.cn.nucyixue.base.BaseActivity;
import cn.leancloud.chatkit.LCChatKit;
import io.reactivex.functions.Consumer;

public class SplashActivity extends BaseActivity {

    private static final String TAG = "SplashActivity";

    protected void logicActivity(Bundle mSavedInstanceState) {
        if(mAVUserFinal != null){
            LCChatKit.getInstance().open(mAVUserFinal.getObjectId(), new AVIMClientCallback() {
                @Override
                public void done(AVIMClient avimClient, AVIMException e) {
                    if(e != null){
                        Toast.makeText(SplashActivity.this, "初始化失败", Toast.LENGTH_LONG).show();
                    }

                    RxPermissions mRxPermissions = new RxPermissions(SplashActivity.this);

                    mRxPermissions.requestEach(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.SEND_SMS)
                            .subscribe(new Consumer<Permission>() {
                                @Override
                                public void accept(Permission mPermission) throws Exception {
                                    if (mPermission.granted){
                                        Log.i(TAG, "accept: success "+mPermission.name);
                                    }else {
                                        Log.e(TAG, "accept: fail"+mPermission.name );
                                    }
                                    Log.i(TAG, "accept: start success");
                                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                                    finish();
                                }
                            });
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    mActivity.finish();

                }
            });
        }else{
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            mActivity.finish();
        }
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }
}
