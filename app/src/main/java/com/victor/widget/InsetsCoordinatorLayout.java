package com.victor.widget;

import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.WindowInsetsCompat;
import android.util.AttributeSet;
import android.view.View;

import com.victor.util.WindowInsetsCompatUtil;

public class InsetsCoordinatorLayout extends CoordinatorLayout {

    public InsetsCoordinatorLayout(Context context) {
        this(context, null);
    }

    public InsetsCoordinatorLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public InsetsCoordinatorLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        ViewCompat.setOnApplyWindowInsetsListener(this, new android.support.v4.view.OnApplyWindowInsetsListener() {
            @Override
            public WindowInsetsCompat onApplyWindowInsets(View v, WindowInsetsCompat insets) {
                if (insets.isConsumed()) {
                    return insets;
                }

                for (int i = 0, count = getChildCount(); i < count; i++) {
                    final View child = getChildAt(i);
                    final Behavior b = getBehavior(child);

                    if (b == null) {
                        continue;
                    }

                    //noinspection unchecked
                    b.onApplyWindowInsets(InsetsCoordinatorLayout.this, child, WindowInsetsCompatUtil.copy(insets));
                }

                return insets;
            }
        });
    }

    private static Behavior getBehavior(View child) {
        final LayoutParams lp = (LayoutParams) child.getLayoutParams();
        return lp.getBehavior();
    }

    @Override
    @SuppressWarnings("deprecation")
    protected boolean fitSystemWindows(Rect insets) {
        if (Build.VERSION.SDK_INT >= 21) {
            return super.fitSystemWindows(insets);
        }

        boolean consumed = false;

        for (int i = 0, count = getChildCount(); i < count; i++) {
            final View child = getChildAt(i);
            final Behavior b = getBehavior(child);

            if (b == null) {
                continue;
            }

            if (!(b instanceof WindowInsetsHandlingBehavior)) {
                continue;
            }

            if (((WindowInsetsHandlingBehavior) b).onApplyWindowInsets(this, child, insets)) {
                consumed = true;
            }
        }

        return consumed;
    }

    public interface WindowInsetsHandlingBehavior {

        boolean onApplyWindowInsets(CoordinatorLayout layout, View child, Rect insets);

    }

}
