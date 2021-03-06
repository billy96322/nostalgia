package com.salmonzhg.nostalgia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.salmonzhg.lifecycleadapter_zhihu_rxlifecycle.RxLifecycleAdapter;
import com.salmonzhg.nostalgia.core.Nostalgia;
import com.salmonzhg.nostalgia.core.Unbinder;
import com.salmonzhg.nostalgia.core.annotation.LifecycleFilter;
import com.salmonzhg.nostalgia.core.annotation.Receive;
import com.salmonzhg.nostalgia.core.annotation.Scheduler;
import com.salmonzhg.nostalgia.core.annotation.Take;
import com.salmonzhg.nostalgia.core.lifecycleadapter.ActivityLifecycle;

public class MainActivity extends AppCompatActivity {

    Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        unbinder = Nostalgia.bind(this, new RxLifecycleAdapter(this));

    }

    @Receive(tag = "tag" , scheduler = Scheduler.MAINTHREAD)
    void onReceived(String contentStr) {
        Log.d("asd", "onReceived: " + contentStr);
    }

    @Receive(tag = "wer")
    void onEmptyParam() {
        Log.d("asd", "onEmptyParam: ");
    }



    @Receive(tag = "wer")
    @LifecycleFilter(from = ActivityLifecycle.RESUME, to = ActivityLifecycle.PAUSE)
    void onBaseTypeParamWhenVisible(int i) {
        Log.d("asd", "onBaseTypeParamWhenVisible: " + i);
    }

    @Take(times = 3)
    @Receive(tag = "wer")
    void onBaseTypeParamTake3Times(int i) {
        Log.d("asd", "onBaseTypeParamTake3Times: " + i);
    }


    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    public void second(View view) {
        startActivity(new Intent(this, Main2Activity.class));
    }

    public void send(View view) {
        Nostalgia.post("wer", 10);
    }
}
