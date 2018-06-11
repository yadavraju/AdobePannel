package com.raju.adobepannel;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.widget.LinearLayout;
import android.widget.ViewSwitcher;

public class RightPanelLayout extends LinearLayout {
    private ViewGroup leftPanel;
    private boolean leftPanelOpened;
    private View rightPanel;
    private boolean rightPanelOpened;
    private ViewPropertyAnimator showAnimator;
    private ViewPropertyAnimator hideAnimator;

    public RightPanelLayout(Context paramContext) {
        this(paramContext, null);
    }

    public RightPanelLayout(Context paramContext, @Nullable AttributeSet paramAttributeSet) {
        this(paramContext, paramAttributeSet, 0);
    }

    public RightPanelLayout(Context paramContext, @Nullable AttributeSet paramAttributeSet, int paramInt) {
        super(paramContext, paramAttributeSet, paramInt);
        TypedArray typedArray = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.RightPanelLayout, 0, 0);
        leftPanelOpened = typedArray.getBoolean(R.styleable.RightPanelLayout_leftPanelOpened, false);
        rightPanelOpened = typedArray.getBoolean(R.styleable.RightPanelLayout_rightPanelOpened, false);
        typedArray.recycle();

    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.leftPanel = ((ViewGroup) findViewById(R.id.vsLeftPanel));
        this.rightPanel = findViewById(R.id.blackLayer);
        showLeftPanel(leftPanelOpened);
        showRightPanel(rightPanelOpened);
    }

    public void hideLeftPanel(boolean paramBoolean) {
        hideViewWithAnimation(leftPanel);
    }

    public void hideRightPanel(boolean paramBoolean) {
        hideViewWithAnimation(rightPanel);
    }

    public void showLeftPanel(boolean isShown) {
        if (isShown) {
            showViewWithAnimation(leftPanel);
        } else {
            hideLeftPanel(isShown);
        }

    }

    public void showRightPanel(boolean isShown) {
        if (isShown) {
            showViewWithAnimation(rightPanel);
        } else {
            hideRightPanel(isShown);
        }
    }

    public void leftPanelShowNext() {
        ((ViewSwitcher) leftPanel).showNext();
    }

    public void leftPanelShowPrevious() {
        ((ViewSwitcher) leftPanel).showPrevious();
    }

    public void toggleLeftPanel(boolean isShowingLeftPanel) {
        if (isShowingLeftPanel) {
            hideViewWithAnimation(leftPanel);
        } else {
            showViewWithAnimation(leftPanel);
        }
    }

    public void toggleRightPanel(boolean isShowingRightPanel) {
        if (isShowingRightPanel) {
            hideViewWithAnimation(rightPanel);
        } else {
            showViewWithAnimation(rightPanel);
        }
    }

    /*Showing Animation from direction x */
    private void showViewWithAnimation(View fadeIn) {
        if (fadeIn != null) {
            AnimatorUtils.Companion.cancel(showAnimator);
            showAnimator = fadeIn.animate()
                    .alpha(1f)
                    .translationX(0f)
                    .setDuration(200)
                    .withStartAction(() -> {
                        fadeIn.setAlpha(1f);
                        fadeIn.setVisibility(VISIBLE);
                    });
            showAnimator.start();
        }
    }

    /*Hiding Animation from direction x */
    private void hideViewWithAnimation(View fadeOut) {
        if (fadeOut != null) {
            AnimatorUtils.Companion.cancel(hideAnimator);
            hideAnimator = fadeOut.animate()
                    .alpha(1f)
                    .translationX(fadeOut.getWidth())
                    .withEndAction(() -> fadeOut.setVisibility(GONE));
            hideAnimator.start();
        }
    }

    /*Teardown All animation from causing memory leak*/
    public void tearDown() {
        AnimatorUtils.Companion.cancel(hideAnimator);
        AnimatorUtils.Companion.cancel(showAnimator);
    }

}
