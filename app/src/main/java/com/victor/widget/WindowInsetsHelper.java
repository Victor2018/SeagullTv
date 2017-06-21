
package com.victor.widget;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;

public class WindowInsetsHelper {

    public static boolean onApplyWindowInsets(View view, Rect insets) {
        return view instanceof WindowInsetsHandler &&
                ((WindowInsetsHandler) view).onApplyWindowInsets(insets);
    }

    public static boolean dispatchApplyWindowInsets(ViewGroup parent, Rect insets) {
        final int count = parent.getChildCount();

        for (int i = 0; i < count; i++) {
            if (onApplyWindowInsets(parent.getChildAt(i), insets)) {
                return true;
            }
        }

        return false;
    }

}
