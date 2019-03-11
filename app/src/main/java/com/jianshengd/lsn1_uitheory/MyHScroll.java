package com.jianshengd.lsn1_uitheory;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;


/**
 * Created by jianshengd on 2019/3/11
 */
@RequiresApi(api = Build.VERSION_CODES.M)
public class MyHScroll extends HorizontalScrollView implements View.OnScrollChangeListener {

    private LinearLayout container;
    private int icon_width;
    private int centerX;

    public MyHScroll(Context context) {
        super(context);
        init();
    }

    public MyHScroll(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        //在ScrollView里面放置一个水平线性布局，再往里面放置很多ImageView
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        container = new LinearLayout(getContext());
        container.setLayoutParams(params);
        setOnScrollChangeListener(this);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        //得到某一张图片的宽度
        View v = container.getChildAt(0);
        icon_width = v.getWidth();
        Log.d("djs", "icon_width = " + icon_width);
        //得到hzv的中间x坐标
        centerX = getWidth()/2;
        Log.d("djs", "centerX = " + centerX);
        //处理下，中心坐标改为中心图片的左边界
//        centerX = centerX - icon_width/2;

        int width = getChildAt(0).getWidth();
        smoothScrollTo(width/2-icon_width/2,0);
    }


    @Override
    public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
        int startX = scrollX +centerX - icon_width/2;
        reveal(startX);
    }
    private void reveal(int startX) {
        //找到两张渐变的图片的下标--左，右
        int index_left = startX/icon_width;
        int index_right = index_left + 1;
        //设置图片的level
        for (int i = 0; i < container.getChildCount(); i++) {
            if(i==index_left||i==index_right){
                //变化
                //比例：

                float ratio = 5000f/icon_width;
                ImageView iv_left = (ImageView) container.getChildAt(index_left);
                //scrollX%icon_width:代表滑出去的距离
                //滑出去了icon_width/2  icon_width/2%icon_width
                iv_left.setImageLevel(
                        //代表的是，我滑动之后的距离在5000份当中的份额
                        (int)(5000-startX%icon_width*ratio)
                );
                //右边
                if(index_right<container.getChildCount()){
                    ImageView iv_right = (ImageView) container.getChildAt(index_right);
                    //scrollX%icon_width:代表滑出去的距离
                    //滑出去了icon_width/2  icon_width/2%icon_width
                    iv_right.setImageLevel(
                            (int)(10000-startX%icon_width*ratio)
                    );
                }
            }else{
                //灰色
                ImageView iv = (ImageView) container.getChildAt(i);
                iv.setImageLevel(0);
            }
        }
    }

    //添加图片的方法
    public void addImageViews(Drawable[] revealDrawables){
        for (int i = 0; i < revealDrawables.length; i++) {
            ImageView img = new ImageView(getContext());
            img.setImageDrawable(revealDrawables[i]);
            container.addView(img);
        }
        addView(container);
    }
}
