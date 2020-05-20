package com.example.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * @ClassName MyFlowView
 * @Description TODO
 * @Author guoxw
 * @Date 2020/3/13 15:24
 */
public class MyFlowView extends ViewGroup {

    public MyFlowView(Context context) {
        super(context);
    }

    public MyFlowView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyFlowView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int totalWidth = 0, totalHeight = 0; //总宽度和总高度
        int currentLineTotalWidth = 0;//当前行的总宽度

        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            //此处必须要用measureChildWithMargins,不能用measureChild,否则如果子view的宽度超过了父控件的宽度，layout_margin的右间距或者layout_marginRight会失效
            measureChildWithMargins(childAt, widthMeasureSpec, 0, heightMeasureSpec, 0);//测量每个子view的宽高(测量之后才可以拿到子view的宽高)

            MarginLayoutParams layoutParams = (MarginLayoutParams) childAt.getLayoutParams();
            int childWidth = childAt.getMeasuredWidth() + layoutParams.leftMargin + layoutParams.rightMargin;
            int childHeight = childAt.getMeasuredHeight() + layoutParams.topMargin + layoutParams.bottomMargin;

            if (childWidth + currentLineTotalWidth > widthSize) { //超过了总宽度,开始折行
                totalWidth = Math.max(currentLineTotalWidth, childWidth);//获取这一行的总宽度
                totalHeight = totalHeight + childHeight; //加上这一行的高度
                currentLineTotalWidth = childWidth;//重置当前行的宽
            } else {  //不用折行
                totalHeight = Math.max(totalHeight, childHeight);//对比取最大高度，防止子view少时没有设置总高度
                currentLineTotalWidth = currentLineTotalWidth + childWidth;//累加宽度
                totalWidth = Math.max(currentLineTotalWidth, childWidth);//获取这一行的总宽度
            }
        }
        //固定宽高则就使用默认的测量值即可，否则使用计算出来的宽和高
        setMeasuredDimension(widthMode == MeasureSpec.EXACTLY ? widthSize : totalWidth,
                heightMode == MeasureSpec.EXACTLY ? heightSize : totalHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        int top = 0, left = 0;
        int currentLineHeight = 0, currentLineWidth = 0;//当前行的宽和高

        for (int i = 0; i < getChildCount(); i++) {

            View childAt = getChildAt(i);
            MarginLayoutParams layoutParams = (MarginLayoutParams) childAt.getLayoutParams();
            int childWidth = childAt.getMeasuredWidth() + layoutParams.leftMargin + layoutParams.rightMargin;
            int childHeight = childAt.getMeasuredHeight() + layoutParams.topMargin + layoutParams.bottomMargin;

            if (currentLineWidth + childWidth > getMeasuredWidth()) { //超过了总宽度,开始折行
                top = top + currentLineHeight;//累加
                left = 0;
                //换行重新赋值
                currentLineWidth = childWidth;
                currentLineHeight = childHeight;
            } else {  //不用折行
                currentLineWidth = currentLineWidth + childWidth;
                currentLineHeight = Math.max(childHeight, currentLineHeight);
            }

            int realLeft = left + layoutParams.leftMargin;
            int realTop = top + layoutParams.topMargin;

            int realRight = realLeft + childAt.getMeasuredWidth();
            int realBottom = realTop + childAt.getMeasuredHeight();
            //设置位置
            childAt.layout(realLeft, realTop, realRight, realBottom);

            left = left + childWidth;
        }
    }

    //必须要重写该下列方法(选择性 or 最好都写上)

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(this.getContext(), attrs);
    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new MarginLayoutParams(p);
    }
}
