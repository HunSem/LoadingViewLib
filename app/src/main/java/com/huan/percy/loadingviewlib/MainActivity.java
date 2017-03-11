package com.huan.percy.loadingviewlib;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.huan.percy.loadingviewlib.view.SimleView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SimleView simleView = (SimleView) findViewById(R.id.smileView);

        simleView.startAnim();

    }
}
