package com.victor.interfaces;

import android.content.res.Resources;
import android.view.View;

/**
 * 切换主题接口
 * Created by victor on 2016/10/18.
 */
public interface ColorUiInterface {


    View getView();

    void setTheme(Resources.Theme themeId);
}
