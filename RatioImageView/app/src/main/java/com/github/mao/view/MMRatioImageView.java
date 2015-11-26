package com.github.mao.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.github.mao.R;

/**
 * Created by maoweiwei on 15/11/26.
 */
public class MMRatioImageView extends ImageView {
    protected float mWHRatio = 1.0f;
    protected boolean resizeEnable;

    public MMRatioImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MMRatioImageView(Context context) {
        this(context, null);
    }

    public MMRatioImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.MMRatioImageView);
        mWHRatio = a.getFloat(R.styleable.MMRatioImageView_whMMRatio, 1.0f);
        resizeEnable = a.getBoolean(R.styleable.MMRatioImageView_whMMRatioEnable,
                false);
        a.recycle();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (resizeEnable) {
            int width = getMeasuredWidth();
            int height = (int) (width / mWHRatio);
            widthMeasureSpec = MeasureSpec.makeMeasureSpec(width,
                    MeasureSpec.EXACTLY);
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(height,
                    MeasureSpec.EXACTLY);
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    public float getWHRatio() {
        return mWHRatio;
    }

    public void setWHRatio(float mWHRatio) {
        this.mWHRatio = mWHRatio;
    }

    public boolean isResizeEnable() {
        return resizeEnable;
    }

    public void setResizeEnable(boolean resizeEnable) {
        this.resizeEnable = resizeEnable;
    }

    @Override
    public void setImageBitmap(Bitmap bm) {
        if (null != bm && !resizeEnable && bm.getWidth() != 0
                && bm.getHeight() != 0) {
            setWHRatio(bm.getWidth() * 1.0f / bm.getHeight());
            setResizeEnable(true);
        }
        super.setImageBitmap(bm);
    }

    @Override
    public void setImageDrawable(Drawable drawable) {
        if (null != drawable && !resizeEnable
                && drawable.getIntrinsicWidth() != 0
                && drawable.getIntrinsicHeight() != 0) {
            setWHRatio(drawable.getIntrinsicWidth() * 1.0f
                    / drawable.getIntrinsicHeight());
            setResizeEnable(true);
        }
        super.setImageDrawable(drawable);
    }
}
