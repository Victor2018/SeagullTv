package com.victor.widget;

import android.content.Context;
import android.graphics.Rect;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;

public class InsetsSwipeRefreshLayout extends SwipeRefreshLayout implements WindowInsetsHandler {

    public InsetsSwipeRefreshLayout(Context context) {
        super(context);
    }

    public InsetsSwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onApplyWindowInsets(Rect insets) {
        return WindowInsetsHelper.dispatchApplyWindowInsets(this, insets);
    }

}
