package com.ruthloeser.android.tctassignment.customviews;
/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 Alex Fu
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Checkable;
import androidx.appcompat.widget.AppCompatImageButton;

public class CheckableImageButton extends AppCompatImageButton implements Checkable {

  private boolean mChecked;
  private boolean mBroadcasting;

  private OnCheckedChangeListener mOnCheckedChangeListener;

  private static final int[] CHECKED_STATE_SET = {
          android.R.attr.state_checked
  };

  /**
   * Interface definition for a callback to be invoked when the checked state changed.
   */
  public static interface OnCheckedChangeListener {
    /**
     * Called when the checked state has changed.
     *
     * @param button The button view whose state has changed.
     * @param isChecked  The new checked state of button.
     */
    void onCheckedChanged(CheckableImageButton button, boolean isChecked);
  }

  public CheckableImageButton(Context context) {
    super(context);
  }

  public CheckableImageButton(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public CheckableImageButton(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @Override
  public boolean performClick() {
    toggle();
    return super.performClick();
  }

  /**
   * Register a callback to be invoked when the checked state of this button
   * changes.
   *
   * @param listener the callback to call on checked state change
   */
  public void setOnCheckedChangeListener(OnCheckedChangeListener listener) {
    mOnCheckedChangeListener = listener;
  }

  /**
   * <p>Changes the checked state of this button.</p>
   *
   * @param checked true to check the button, false to uncheck it
   */
  public void setChecked(boolean checked) {
    if (mChecked != checked) {
      mChecked = checked;
      refreshDrawableState();

      // Avoid infinite recursions if setChecked() is called from a listener
      if (mBroadcasting) {
        return;
      }

      mBroadcasting = true;
      if (mOnCheckedChangeListener != null) {
        mOnCheckedChangeListener.onCheckedChanged(this, mChecked);
      }

      mBroadcasting = false;
    }
  }

  @Override
  public boolean isChecked() {
    return mChecked;
  }

  @Override
  public void toggle() {
    setChecked(!mChecked);
  }

  @Override
  public int[] onCreateDrawableState(int extraSpace) {
    final int[] drawableState = super.onCreateDrawableState(extraSpace + 1);
    if (isChecked()) {
      mergeDrawableStates(drawableState, CHECKED_STATE_SET);
    }
    return drawableState;
  }
}