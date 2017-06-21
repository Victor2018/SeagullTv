package com.victor.widget;import android.content.Context;import android.content.res.Resources;import android.util.AttributeSet;import android.view.View;import android.widget.TextView;import com.victor.interfaces.ColorUiInterface;import com.victor.util.ViewAttributeUtil;/** * 单行文本跑马灯控件 * Created by victor on 2016/1/9. * */public class ColorMovingTextView extends TextView implements ColorUiInterface {    private int attr_drawable = -1;    private int attr_textAppearance = -1;    private int attr_textColor = -1;    private int attr_textLinkColor = -1;    public ColorMovingTextView(Context context) {        super(context);        // TODO Auto-generated constructor stub    }    public ColorMovingTextView(Context context, AttributeSet attrs) {        super(context, attrs);        this.attr_drawable = ViewAttributeUtil.getBackgroundAttibute(attrs);        this.attr_textColor = ViewAttributeUtil.getTextColorAttribute(attrs);        this.attr_textLinkColor = ViewAttributeUtil.getTextLinkColorAttribute(attrs);    }    public ColorMovingTextView(Context context, AttributeSet attrs,                               int defStyle) {        super(context, attrs, defStyle);        this.attr_drawable = ViewAttributeUtil.getBackgroundAttibute(attrs);        this.attr_textColor = ViewAttributeUtil.getTextColorAttribute(attrs);        this.attr_textLinkColor = ViewAttributeUtil.getTextLinkColorAttribute(attrs);    }    @Override    public boolean isFocused() {        return true;    }    @Override    public View getView() {        return this;    }    @Override    public void setTheme(Resources.Theme themeId) {        if (attr_drawable != -1) {            ViewAttributeUtil.applyBackgroundDrawable(this, themeId, attr_drawable);        }        if (attr_textColor != -1) {            ViewAttributeUtil.applyTextColor(this, themeId, attr_textColor);        }        if (attr_textLinkColor != -1) {            ViewAttributeUtil.applyTextLinkColor(this, themeId, attr_textLinkColor);        }    }}