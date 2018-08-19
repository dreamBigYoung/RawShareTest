package com.bigyoung.rawsharetest;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.bigyoung.rawsharetest.utils.Utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public View mShareButton;
    public View mDownLoadBtn;
    public List<String> mFilesPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final String imgUrl="https://gss3.bdstatic.com/-Po3dSag_xI4khGkpoWK1HF6hhy/baike/w%3D268%3Bg%3D0/sign=088406db434a20a4311e3bc1a869ff1f/71cf3bc79f3df8dc9a92d158c111728b4610284f.jpg";
        mFilesPath = new ArrayList<String>();
        mDownLoadBtn = findViewById(R.id.down_button);
        mDownLoadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.downloadImagesByUrl(MainActivity.this,imgUrl, mFilesPath);
            }
        });

        mShareButton = findViewById(R.id.share_button);
        mShareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent=new Intent(Intent.ACTION_SEND);
              /*  //检测图片
                if(mFilesPath.size()>0&&!mFilesPath.get(0).isEmpty()){
                    File f = new File(mFilesPath.get(0));
                    if (f != null && f.exists() && f.isFile()) {
                        shareIntent.setType("image/jpg");
                        Uri u = Uri.fromFile(f);
                        shareIntent.putExtra(Intent.EXTRA_STREAM, u);
                    }else{
                        shareIntent.setType("text/plain");
                    }
                }else{
                    shareIntent.setType("text/plain");
                }*/
                shareIntent.setType("text/plain");
                //shareIntent.putExtra(Intent.EXTRA_SUBJECT, "http://www.bbc.co.uk/news/world-latin-america-45237368");
                shareIntent.putExtra(Intent.EXTRA_TEXT,"http://www.bbc.co.uk/news/world-latin-america-45237368");
                shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                //设置分享列表的标题，并且每次都显示分享列表
                startActivity(Intent.createChooser(shareIntent,"分享到"));
            }
        });
    }
}
