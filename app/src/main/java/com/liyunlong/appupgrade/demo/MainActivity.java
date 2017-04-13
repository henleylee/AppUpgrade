package com.liyunlong.appupgrade.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.liyunlong.upgrade.UpdateAgent;
import com.liyunlong.upgrade.UpdaterConfiguration;
import com.liyunlong.upgrade.model.TotalUpdateInfo;
import com.liyunlong.upgrade.model.UpdateInfo;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final UpdaterConfiguration config = new UpdaterConfiguration();
        //此处模拟更新信息获取,信息获取后需要将UpdateInfo设置到配置信息中，然后要调用相应的回调方法才能使整个流程完整执行
        UpdateInfo updateInfo = new UpdateInfo();
        updateInfo.setVersionCode(2);
        updateInfo.setVersionName("v1.2");
        updateInfo.setUpdateTime("2016/10/28");
        updateInfo.setUpdateSize(1024);
        updateInfo.setUpdateContent("更新日志:\n1.新增万能更新库，实现更新功能只要几行代码。\n2.优化界面，增加用户体验。");
        updateInfo.setIgnorable(true);
        //使用全量更新信息
        updateInfo.setIncrementUpdate(false);
        TotalUpdateInfo totalUpdateInfo = new TotalUpdateInfo();
        totalUpdateInfo.setApkUrl("http://wap.apk.anzhi.com/data2/apk/201609/05/f06abcb0e2cba4c8ce2301c4b437a492_72932500.apk");
        updateInfo.setTotalUpdateInfo(totalUpdateInfo);

        //设置更新信息，这样各模块就可以通过config.getUpdateInfo()共享这个数据了,注意这个方法一定要调用且要在UpdateCheckCallback.onCheckSuccess之前调用
        config.setUpdateInfo(updateInfo);
        UpdateAgent.getInstance().init(config);

        findViewById(R.id.update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //此处的Context默认必须为Activity
                UpdateAgent.getInstance().check(MainActivity.this);
            }
        });

    }
}
