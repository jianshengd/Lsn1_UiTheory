package com.jianshengd.lsn1_uitheory;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Gravity;

/**
 * 自定义drawable
 *
 * @author jianshengd
 * @date 2019/3/9 22:02
 */
public class MyDrawable extends Drawable {
    private Drawable unDrawable, norDrawable;


    public MyDrawable(Drawable unDrawable, Drawable normalDrawable) {
        this.unDrawable = unDrawable;
        this.norDrawable = normalDrawable;
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        //从一个已有的bound矩形边界里面截取矩形
        int level = getLevel();
        if (level == 0 || level == 10000){
            unDrawable.draw(canvas);
        }else{
            Rect r = getBounds();
            Rect newR = new Rect();
            float ratio = level/5000f;
            int unWidth = (int) (r.width() * (Math.abs(1-ratio)));
            int norWidth = r.width() - unWidth;
            {
                //未选中部分
                int gravity = ratio < 1?Gravity.START:Gravity.END;

                Gravity.apply(
                        //起始方向
                        gravity,
                        unWidth, r.height(), r,newR);
                canvas.save();
                canvas.clipRect(newR);
                unDrawable.draw(canvas);
                canvas.restore();
            }
            {
                //选中部分
                int gravity = ratio < 1?Gravity.END:Gravity.START;
                Gravity.apply(
                        //起始方向
                        gravity,
                        norWidth, r.height(), r,newR);
                canvas.save();
                canvas.clipRect(newR);
                norDrawable.draw(canvas);
                canvas.restore();
            }
        }
    }

    @Override
    public void setAlpha(int alpha) {

    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        unDrawable.setBounds(bounds);
        norDrawable.setBounds(bounds);
        super.onBoundsChange(bounds);
    }
    @Override
    public int getIntrinsicWidth() {
        //得到Drawable的实际宽度
        return Math.max(norDrawable.getIntrinsicWidth(),
                unDrawable.getIntrinsicWidth());
    }

    @Override
    public int getIntrinsicHeight() {
        //得到Drawable的实际高度
        return Math.max(norDrawable.getIntrinsicHeight(),
                unDrawable.getIntrinsicHeight());
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {

    }

    @Override
    public int getOpacity() {
        return 0;
    }

    @Override
    protected boolean onLevelChange(int level) {
        invalidateSelf();
        return super.onLevelChange(level);
    }
}
