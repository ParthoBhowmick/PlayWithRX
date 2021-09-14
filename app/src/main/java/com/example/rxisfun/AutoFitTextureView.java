package com.example.rxisfun;

/**
 * @author partho
 * @since 14/9/21
 */

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.TextureView;
import android.view.WindowManager;

/**
 * A {@link TextureView} that can be adjusted to a specified aspect ratio.
 */
public class AutoFitTextureView extends TextureView {

    public static int viewHeight = 0;
    public static int viewWidth = 0;
    private int mRatioWidth = 0;
    private int mRatioHeight = 0;
    public DisplayMetrics mMetrics = new DisplayMetrics();

    public AutoFitTextureView(Context context) {
        this(context, null);
    }

    public AutoFitTextureView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AutoFitTextureView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     * Sets the aspect ratio for this view. The size of the view will be measured based on the ratio
     * calculated from the parameters. Note that the actual sizes of parameters don't matter, that
     * is, calling setAspectRatio(2, 3) and setAspectRatio(4, 6) make the same result.
     *
     * @param width  Relative horizontal size
     * @param height Relative vertical size
     */
    public void setAspectRatio(int width, int height) {
        if (width < 0 || height < 0) {
            throw new IllegalArgumentException("Size cannot be negative.");
        }
        mRatioWidth = width;
        mRatioHeight = height;
        requestLayout();
    }

//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        Log.e("change layout 1", MeasureSpec.getSize(widthMeasureSpec) + "  " + MeasureSpec.getSize(heightMeasureSpec) );
//        Log.e("change layout 2", mRatioWidth + "  " + mRatioHeight );
//        int width = MeasureSpec.getSize(widthMeasureSpec);
//        int height = MeasureSpec.getSize(heightMeasureSpec);
//        setMeasuredDimension(width, height);
//        if (0 == mRatioWidth || 0 == mRatioHeight) {
//            setMeasuredDimension(width, height);
//        } else {
//            if (width < height * mRatioWidth / mRatioHeight) {
//                setMeasuredDimension(width, width * mRatioHeight / mRatioWidth);
//                Log.e("change layout 3", width + "  " + width * mRatioHeight / mRatioWidth );
//            } else {
//                setMeasuredDimension(height * mRatioWidth / mRatioHeight, height);
//                Log.e("change layout 4", height * mRatioWidth / mRatioHeight + "  " + height );
//            }
////
////            if((width/height)<(mRatioWidth/mRatioHeight)) {
////                int verticalOffset = (int) (width / ((mRatioWidth/mRatioHeight) - height)) / 2;
////                setMeasuredDimension(width, height+verticalOffset);
////            }
////
////            else {
////                int horizontalOffset = (int) ((mRatioWidth/mRatioHeight) * height - width) / 2;
////                setMeasuredDimension(width+horizontalOffset,height);
////            }
//
//        }
//    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        viewHeight = height;
        viewWidth = width;
        Log.e("baaaaaal", viewWidth + "   " + viewHeight );
        if (0 == mRatioWidth || 0 == mRatioHeight) {
            setMeasuredDimension(width, height);
        } else {
            WindowManager windowManager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
            windowManager.getDefaultDisplay().getMetrics(mMetrics);
            double ratio = (double)mRatioWidth / (double)mRatioHeight;
            double invertedRatio = (double)mRatioHeight / (double)mRatioWidth;
            double portraitHeight = width * invertedRatio;
            double portraitWidth = width * (mMetrics.heightPixels / portraitHeight);
            double landscapeWidth = height * ratio;
            double landscapeHeight = (mMetrics.widthPixels / landscapeWidth) * height;

            if (width < height * mRatioWidth / mRatioHeight) {
                setMeasuredDimension((int)portraitWidth, mMetrics.heightPixels);
            } else {
                setMeasuredDimension(mMetrics.widthPixels, (int)landscapeHeight);
            }
        }
    }

}
