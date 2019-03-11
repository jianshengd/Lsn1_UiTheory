package com.jianshengd.lsn1_uitheory;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyHScroll myHscroll = findViewById(R.id.myHscroll);

        Drawable unDrawable = ContextCompat.getDrawable(this,R.drawable.black_umb);
        Drawable normalDrawable = ContextCompat.getDrawable(this,R.drawable.red_umb);
        final MyDrawable myDrawable = new MyDrawable(unDrawable,normalDrawable);
        final MyDrawable myDrawable2 = new MyDrawable(unDrawable,normalDrawable);
        final MyDrawable myDrawable3 = new MyDrawable(unDrawable,normalDrawable);
        final MyDrawable myDrawable4 = new MyDrawable(unDrawable,normalDrawable);
        final MyDrawable myDrawable5 = new MyDrawable(unDrawable,normalDrawable);
        final MyDrawable myDrawable6 = new MyDrawable(unDrawable,normalDrawable);
        final MyDrawable myDrawable7= new MyDrawable(unDrawable,normalDrawable);
        myHscroll.addImageViews(new Drawable[]{
                myDrawable,myDrawable2,myDrawable3,myDrawable4,
                myDrawable5,myDrawable6,myDrawable7});

    }


}
