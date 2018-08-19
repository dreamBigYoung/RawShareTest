package com.bigyoung.rawsharetest.utils;

import android.content.Context;
import android.widget.Toast;

import org.xutils.common.Callback;
import org.xutils.common.util.LogUtil;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;
import java.util.List;

/**
 * Created by BigYoung on 2018/8/19.
 */

public class Utils {
    public static void downloadImagesByUrl(final Context context, String url, final List<String> filesPath) {
        /*
        获取保存路径：手机SD卡1存储 storage/sdcard/Android/data/应用的包名/files
        Genymotion模拟器的路径：/storage/emulated/0/Android/data/com.atguigu.zhuatutu/files
         */
        File filesDir = context.getExternalFilesDir(null);
        //获取文件名:/february_2016-001.jpg
        String fileName = url.substring(url.lastIndexOf("/"));
        //清除历史记录
        if(filesPath!=null){
            filesPath.clear();
        }
        //存到本地的绝对路径
        final String filePath = filesDir + "/myPicture" + fileName;
        File file = new File(filesDir + "/myPicture");
        //如果不存在
        if (!file.exists()) {
            //创建
            file.mkdirs();
        }

        RequestParams entity = new RequestParams(url);
        entity.setSaveFilePath(filePath);
        x.http().get(entity, new Callback.CommonCallback<File>() {
            @Override
            public void onSuccess(File result) {
                filesPath.add(result.getAbsolutePath());
                LogUtil.e("onSuccess：" + result.getAbsolutePath());
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                LogUtil.e("onError ");
                Toast.makeText(x.app(),"网络错误，下载失败",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(CancelledException cex) {
                LogUtil.e("onCancelled ");
            }

            @Override
            public void onFinished() {
                LogUtil.e("onFinished ");
                Toast.makeText(x.app(), "下载成功,保存到：" + filesPath.get(filesPath.size() - 1), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
