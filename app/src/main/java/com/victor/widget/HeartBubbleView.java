package com.victor.widget;import android.animation.Animator;import android.animation.AnimatorListenerAdapter;import android.animation.TypeEvaluator;import android.animation.ValueAnimator;import android.content.Context;import android.content.res.Resources;import android.content.res.TypedArray;import android.graphics.Canvas;import android.graphics.Color;import android.graphics.Paint;import android.graphics.Path;import android.graphics.Point;import android.util.AttributeSet;import android.util.DisplayMetrics;import android.view.View;import android.view.ViewGroup;import android.view.animation.AccelerateDecelerateInterpolator;import com.victor.seagull.R;/** * Created by victor on 16/6/7. */public class HeartBubbleView extends View implements ValueAnimator.AnimatorUpdateListener {    public static final int VIEW_SIZE = 20;    protected Context mContext;    private int mMeasureWidth;    private int mWidthMode;    private int mMeasureHeight;    private int mHeightMode;    private Paint paint;    private int heartColor;    private int[] colors = new int[]{        R.color.colorBluePrimary,        R.color.colorRedPrimary,        R.color.colorBrownPrimary,        R.color.colorGreenPrimary,        R.color.colorPurplePrimary,        R.color.colorTealPrimary,        R.color.colorPinkPrimary,        R.color.colorDeepPurplePrimary,        R.color.colorOrangePrimary,        R.color.colorIndigoPrimary,        R.color.colorCyanPrimary,        R.color.colorLightGreenPrimary,        R.color.colorLimePrimary,        R.color.colorDeepOrangePrimary,        R.color.colorBlueGreyPrimary    };;    protected Point startPosition;    protected Point endPosition;    public HeartBubbleView(Context context) {        this(context, null);    }    public HeartBubbleView(Context context, AttributeSet attrs) {        this(context, attrs, 0);    }    public HeartBubbleView(Context context, AttributeSet attrs, int defStyleAttr) {        super(context, attrs, defStyleAttr);        mContext = context;        TypedArray ta = context.obtainStyledAttributes(attrs,R.styleable.HeartBubbleView);        heartColor = ta.getColor(R.styleable.HeartBubbleView_heartColor, Color.YELLOW);        //生成心形随机颜色        int minColor = 0;        int maxColor = colors.length - 1;        int colorPosition = (int) (Math.random() * (maxColor - minColor + 1)) + minColor;        heartColor = mContext.getResources().getColor(colors[colorPosition]);        ta.recycle();    }    public void setStartPosition(Point startPosition) {        startPosition.y -= 10;        this.startPosition = startPosition;    }    public void setEndPosition(Point endPosition) {        this.endPosition = endPosition;    }    @Override    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {        mWidthMode = MeasureSpec.getMode(widthMeasureSpec);        mHeightMode = MeasureSpec.getMode(heightMeasureSpec);        mMeasureWidth = MeasureSpec.getSize(widthMeasureSpec);        mMeasureHeight = MeasureSpec.getSize(heightMeasureSpec);        if(mWidthMode == MeasureSpec.AT_MOST && mHeightMode == MeasureSpec.AT_MOST){            setMeasuredDimension(200,200);        }else if(mWidthMode == MeasureSpec.AT_MOST){            setMeasuredDimension(200,mMeasureHeight);        }else if(mHeightMode == MeasureSpec.AT_MOST){            setMeasuredDimension(mMeasureWidth,200);        }else{            setMeasuredDimension(mMeasureWidth,mMeasureHeight);        }    }    @Override    protected void onDraw(Canvas canvas) {//        canvas.drawCircle(getMeasuredWidth() / 2, getMeasuredHeight() / 2, radius, mPaint4Circle);        super.onDraw(canvas);        paint = new Paint();        paint.setStrokeWidth(6);        paint.setAntiAlias(true);        paint.setColor(heartColor);//        int width = getWidth();//        int height = getHeight();        //心形的宽高        int width = (int) convertDpToPixel(40,mContext);        int height = (int) convertDpToPixel(50,mContext);        // 绘制心形        Path path = new Path();        path.moveTo(width/2,height/4);        path.cubicTo((width*6)/7,height/9,(width*12)/13,(height*2)/5,width/2,(height*7)/12);        canvas.drawPath(path,paint);        Path path2 = new Path();        path2.moveTo(width/2,height/4);        path2.cubicTo(width / 7, height / 9, width / 13, (height * 2) / 5, width / 2, (height * 7) / 12);        canvas.drawPath(path2,paint);    }    public void startBeizerAnimation() {        //随机生成x水平结束位置        int minEndX = 720 / 2 -300;        int maxEndX = 720 / 2 +300;        endPosition.x = (int) (Math.random() * (maxEndX - minEndX + 1)) + minEndX;        //随机生成X控制点        int minCtrlX = 2;        int maxCtrlX = 50;        int ctrlX = (int) (Math.random() * (maxCtrlX - minCtrlX + 1)) + minCtrlX;        if (startPosition == null || endPosition == null) return;//        int pointX = (startPosition.x + endPosition.x) / 2;//随机生成X控制点//        int pointX = (startPosition.x + endPosition.x) / ctrlX;//随机生成X控制点        int pointX = (int) (Math.random() * (720 / 2 - 50 + 1)) + 50;//随机生成X控制点        int pointY = (int) (startPosition.y - convertDpToPixel(100, mContext));        Point controllPoint = new Point(pointX, pointY);        BezierEvaluator bezierEvaluator = new BezierEvaluator(controllPoint);        ValueAnimator anim = ValueAnimator.ofObject(bezierEvaluator, startPosition, endPosition);        anim.addUpdateListener(this);        anim.setDuration(1000);        anim.addListener(new AnimatorListenerAdapter() {            @Override            public void onAnimationEnd(Animator animation) {                super.onAnimationEnd(animation);                ViewGroup viewGroup = (ViewGroup) getParent();                viewGroup.removeView(HeartBubbleView.this);            }        });        anim.setInterpolator(new AccelerateDecelerateInterpolator());        anim.start();    }    @Override    public void onAnimationUpdate(ValueAnimator animation) {        Point point = (Point) animation.getAnimatedValue();        setX(point.x);        setY(point.y);        //getAnimatedFraction()就是mBezierEvalutor估值器对象中evaluate方法t即时间因子,        // 从0~1变化,所以爱心透明度应该是从1~0变化正好到了顶部，t变为1，透明度变为0，即爱心消失        setAlpha(1 - animation.getAnimatedFraction());        invalidate();    }    public class BezierEvaluator implements TypeEvaluator<Point> {        private Point controllPoint;        public BezierEvaluator(Point controllPoint) {            this.controllPoint = controllPoint;        }        @Override        public Point evaluate(float t, Point startValue, Point endValue) {            int x = (int) ((1 - t) * (1 - t) * startValue.x + 2 * t * (1 - t) * controllPoint.x + t * t * endValue.x);            int y = (int) ((1 - t) * (1 - t) * startValue.y + 2 * t * (1 - t) * controllPoint.y + t * t * endValue.y);            return new Point(x, y);        }    }    public static float convertDpToPixel(float dp, Context context) {        Resources resources = context.getResources();        DisplayMetrics metrics = resources.getDisplayMetrics();        float px = dp * (metrics.densityDpi / 160f);        return px;    }}