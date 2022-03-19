package com.example.catsystem.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

@SuppressLint("AppCompatCustomView")
public class RoundImage extends ImageView
{
    Path path;
    public PaintFlagsDrawFilter mPaintFlagsDrawFilter;// 毛边过滤
    Paint paint;
    public RoundImage(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
        init();
    }

    public RoundImage(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RoundImage(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
        init();
    }

    public void init() {
        paint = new Paint();
    }

    @Override
    protected void onDraw(Canvas cns) {
        // TODO Auto-generated method stub
        Drawable drawable = getDrawable();
        if (null != drawable) {
            //Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
            Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),
                    drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
                    drawable.getIntrinsicHeight());
            drawable.draw(canvas);
            bitmap=bitmap;
            Bitmap b = circleDraw(bitmap);
            final Rect rect1 = new Rect(0, 0, b.getWidth(), b.getHeight());
            final Rect rect2 = new Rect(0, 0, getMeasuredWidth(), getMeasuredHeight());
            paint.reset();
            cns.drawBitmap(b, rect1, rect2, paint);
            b.recycle();
        } else {
            super.onDraw(cns);
        }
    }
    public static Bitmap drawableToBitmap(Drawable drawable) {

        int w = drawable.getIntrinsicWidth();
        int h = drawable.getIntrinsicHeight();
        System.out.println("Drawable转Bitmap");
        Bitmap.Config config =
                drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                        : Bitmap.Config.RGB_565;
        Bitmap bitmap = Bitmap.createBitmap(w, h, config);
        //注意，下面三行代码要用到，否则在View或者SurfaceView里的canvas.drawBitmap会看不到图
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, w, h);
        drawable.draw(canvas);

        return bitmap;
    }
    private Bitmap circleDraw(Bitmap bitmap) {
        int r=0;
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Rect rectSource = null;
        if(width>height)
            r=height;
        else
        {
            r=width;
        }
        //创建一个图片对象
        Bitmap output = Bitmap.createBitmap(r, r, Bitmap.Config.ARGB_8888);
        //创建一个图片游标
        Canvas canvas = new Canvas(output);
        final Rect rect = new Rect(0, 0, r, r);
        /* 设置取消锯齿效果 */
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(Color.WHITE);
        /* 绘画一个圆图形 */
        canvas.drawCircle(r/ 2, r / 2, r / 2, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;
    }

}
