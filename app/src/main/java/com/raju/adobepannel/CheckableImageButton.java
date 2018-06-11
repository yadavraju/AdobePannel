package com.raju.adobepannel;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v7.widget.AppCompatImageButton;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.widget.Checkable;

public class CheckableImageButton extends AppCompatImageButton implements Checkable {
    private static final int[] DRAWABLE_STATE_CHECKED = {android.R.attr.state_checked};
    private boolean mChecked;

    public CheckableImageButton(Context paramContext) {
        this(paramContext, null);
    }

    public CheckableImageButton(Context paramContext, AttributeSet paramAttributeSet) {
        this(paramContext, paramAttributeSet, android.support.v7.appcompat.R.attr.imageButtonStyle);
    }

    public CheckableImageButton(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
        super(paramContext, paramAttributeSet, paramInt);
        TypedArray a=  paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.CheckableImageButton, paramInt, 0);
        setChecked(a.getBoolean(R.styleable.CheckableImageButton_checked, false));
        a.recycle();
        ViewCompat.setAccessibilityDelegate(this, new AccessibilityDelegateCompat() {
            public void onInitializeAccessibilityEvent(View paramAnonymousView, AccessibilityEvent paramAnonymousAccessibilityEvent) {
                super.onInitializeAccessibilityEvent(paramAnonymousView, paramAnonymousAccessibilityEvent);
                paramAnonymousAccessibilityEvent.setChecked(CheckableImageButton.this.isChecked());
            }

            public void onInitializeAccessibilityNodeInfo(View paramAnonymousView, AccessibilityNodeInfoCompat paramAnonymousAccessibilityNodeInfoCompat) {
                super.onInitializeAccessibilityNodeInfo(paramAnonymousView, paramAnonymousAccessibilityNodeInfoCompat);
                paramAnonymousAccessibilityNodeInfoCompat.setCheckable(true);
                paramAnonymousAccessibilityNodeInfoCompat.setChecked(CheckableImageButton.this.isChecked());
            }
        });
    }

    public boolean isChecked() {
        return mChecked;
    }

    public int[] onCreateDrawableState(int paramInt) {
        if (mChecked) {
            return mergeDrawableStates(super.onCreateDrawableState(DRAWABLE_STATE_CHECKED.length + paramInt), DRAWABLE_STATE_CHECKED);
        }
        return super.onCreateDrawableState(paramInt);
    }

    public void setChecked(boolean paramBoolean) {
        if (mChecked != paramBoolean) {
            mChecked = paramBoolean;
            refreshDrawableState();
            sendAccessibilityEvent(AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED);
        }
    }

    public void toggle() {
        setChecked(!mChecked);
    }
}

