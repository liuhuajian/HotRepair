package com.lhj.demo;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.support.multidex.MultiDex;

import com.tencent.tinker.anno.DefaultLifeCycle;
import com.tencent.tinker.lib.tinker.Tinker;
import com.tencent.tinker.lib.tinker.TinkerInstaller;
import com.tencent.tinker.loader.app.DefaultApplicationLike;
import com.tencent.tinker.loader.shareutil.ShareConstants;

/**
 * Created by messi on 17/3/1.
 */
@DefaultLifeCycle(
        application = "com.lhj.demo.MyTankApplication",
        flags = ShareConstants.TINKER_ENABLE_ALL)

public class SampleApplicationLike extends DefaultApplicationLike {
    private static Context mContext;//application Context

    public SampleApplicationLike(Application application, int tinkerFlags, boolean tinkerLoadVerifyFlag, long applicationStartElapsedTime, long applicationStartMillisTime, Intent tinkerResultIntent) {
        super(application, tinkerFlags, tinkerLoadVerifyFlag, applicationStartElapsedTime, applicationStartMillisTime, tinkerResultIntent);
    }

    @Override
    public void onBaseContextAttached(Context base) {
        super.onBaseContextAttached(base);
        //you must install multiDex whatever tinker is installed!
        MultiDex.install(base);
        mContext = base;

        TinkerInstaller.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static Context getContext(){
        return mContext;
    }
}
