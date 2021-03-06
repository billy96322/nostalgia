package com.salmonzhg.nostalgia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.salmonzhg.nostalgia.core.Nostalgia;
import com.salmonzhg.nostalgia.core.Unbinder;
import com.salmonzhg.nostalgia.core.annotation.LifecycleFilter;
import com.salmonzhg.nostalgia.core.annotation.Receive;
import com.salmonzhg.nostalgia.core.annotation.Take;
import com.salmonzhg.nostalgia.core.lifecycleadapter.ActivityLifecycle;

public class Main2Activity extends AppCompatActivity {
    Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        unbinder = Nostalgia.bind(this);
    }

    @Take(times = 2)
    @Receive(tag = "tag")
    void onMain2Received() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    public void send(View view) {
        Nostalgia.post("wer", 12);
    }
}
