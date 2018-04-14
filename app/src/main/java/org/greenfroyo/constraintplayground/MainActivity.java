package org.greenfroyo.constraintplayground;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sleep();
        Log.d("layoutTime", "simple_linear = " + getLayoutTime(R.layout.simple_linear));
        sleep();
        Log.d("layoutTime", "simple_relative = " + getLayoutTime(R.layout.simple_relative));
        sleep();
        Log.d("layoutTime", "simple_constraint = " + getLayoutTime(R.layout.simple_constraint));
        sleep();
        Log.d("layoutTime", "complex_linear = " + getLayoutTime(R.layout.complex_linear));
        sleep();
        Log.d("layoutTime", "complex_relative = " + getLayoutTime(R.layout.complex_relative));
        sleep();
        Log.d("layoutTime", "complex_constraint = " + getLayoutTime(R.layout.complex_constraint));
    }

    private void sleep(){
        try{
            Thread.sleep(3000);
        }catch (InterruptedException e){
        }
    }

    private long getLayoutTime(int layoutRes) {
        final Context targetContext = this;
        final LayoutInflater layoutInflater = LayoutInflater.from(targetContext);

        final long startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            final View view = layoutInflater.inflate(layoutRes, null);
            view.setLayoutParams(new ViewGroup.LayoutParams(0, 0));

            view.measure(View.MeasureSpec.makeMeasureSpec(1000, View.MeasureSpec.EXACTLY)
                    , View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
            final int measuredHeight = view.getMeasuredHeight();
            final int measuredWidth = view.getMeasuredWidth();

            view.layout(0, 0, measuredWidth, measuredHeight);
        }
        return System.currentTimeMillis() - startTime;
    }
}
