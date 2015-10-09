package cn.kokoi.android.garden.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.widget.GridView;

import cn.kokoi.android.garden.R;

/**
 * Created by zkl on 2015/5/12.
 */
public class BookShelfGridView extends GridView {

    private Bitmap mBackground;
    private Bitmap newBackground;

    public BookShelfGridView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mBackground = BitmapFactory.decodeResource(getResources(), R.drawable.bookshelf_row_bg);

    }

    @Override
    protected void dispatchDraw(Canvas canvas) {

        int count = getChildCount();
        int top = count > 0?getChildAt(0).getTop():0;
        int bgWidth = mBackground.getWidth();
        int bgHeight = mBackground.getHeight();

        int width = getWidth();
        int height = getHeight();

        float scaleWidth = ((float)width) / bgWidth;
        float scaleHeight = 1;

        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);

        newBackground = Bitmap.createBitmap(mBackground,0,0, bgWidth, bgHeight, matrix, true);

        for (int y = top; y < height; y+=bgHeight){
            canvas.drawBitmap(newBackground, 0, y, null);
        }

        super.dispatchDraw(canvas);
    }


}
