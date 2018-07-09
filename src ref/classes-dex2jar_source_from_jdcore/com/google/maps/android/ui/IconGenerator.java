package com.google.maps.android.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.widget.TextView;
import com.google.maps.android.R.id;
import com.google.maps.android.R.layout;
import com.google.maps.android.R.style;

public class IconGenerator
{
  public static final int STYLE_BLUE = 4;
  public static final int STYLE_DEFAULT = 1;
  public static final int STYLE_GREEN = 5;
  public static final int STYLE_ORANGE = 7;
  public static final int STYLE_PURPLE = 6;
  public static final int STYLE_RED = 3;
  public static final int STYLE_WHITE = 2;
  private float mAnchorU = 0.5F;
  private float mAnchorV = 1.0F;
  private BubbleDrawable mBackground;
  private ViewGroup mContainer;
  private View mContentView;
  private final Context mContext;
  private int mRotation;
  private RotationLayout mRotationLayout;
  private TextView mTextView;
  
  public IconGenerator(Context paramContext)
  {
    mContext = paramContext;
    mBackground = new BubbleDrawable(mContext.getResources());
    mContainer = ((ViewGroup)LayoutInflater.from(mContext).inflate(R.layout.amu_text_bubble, null));
    mRotationLayout = ((RotationLayout)mContainer.getChildAt(0));
    paramContext = (TextView)mRotationLayout.findViewById(R.id.amu_text);
    mTextView = paramContext;
    mContentView = paramContext;
    setStyle(1);
  }
  
  private static int getStyleColor(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return -1;
    case 7: 
      return 34816;
    case 6: 
      return -6736948;
    case 5: 
      return -10053376;
    case 4: 
      return -16737844;
    }
    return -3407872;
  }
  
  private static int getTextStyle(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return R.style.amu_Bubble_TextAppearance_Dark;
    }
    return R.style.amu_Bubble_TextAppearance_Light;
  }
  
  private float rotateAnchor(float paramFloat1, float paramFloat2)
  {
    switch (mRotation)
    {
    default: 
      throw new IllegalStateException();
    case 3: 
      return paramFloat2;
    case 2: 
      return 1.0F - paramFloat1;
    case 1: 
      return 1.0F - paramFloat2;
    }
    return paramFloat1;
  }
  
  public float getAnchorU()
  {
    return rotateAnchor(mAnchorU, mAnchorV);
  }
  
  public float getAnchorV()
  {
    return rotateAnchor(mAnchorV, mAnchorU);
  }
  
  public Bitmap makeIcon()
  {
    int i = View.MeasureSpec.makeMeasureSpec(0, 0);
    mContainer.measure(i, i);
    i = mContainer.getMeasuredWidth();
    int j = mContainer.getMeasuredHeight();
    mContainer.layout(0, 0, i, j);
    if ((mRotation == 1) || (mRotation == 3))
    {
      j = mContainer.getMeasuredWidth();
      i = mContainer.getMeasuredHeight();
    }
    Bitmap localBitmap = Bitmap.createBitmap(i, j, Bitmap.Config.ARGB_8888);
    localBitmap.eraseColor(0);
    Canvas localCanvas = new Canvas(localBitmap);
    if (mRotation != 0) {
      if (mRotation == 1)
      {
        localCanvas.translate(i, 0.0F);
        localCanvas.rotate(90.0F);
      }
      else if (mRotation == 2)
      {
        localCanvas.rotate(180.0F, i / 2, j / 2);
      }
      else
      {
        localCanvas.translate(0.0F, j);
        localCanvas.rotate(270.0F);
      }
    }
    mContainer.draw(localCanvas);
    return localBitmap;
  }
  
  public Bitmap makeIcon(CharSequence paramCharSequence)
  {
    if (mTextView != null) {
      mTextView.setText(paramCharSequence);
    }
    return makeIcon();
  }
  
  public void setBackground(Drawable paramDrawable)
  {
    mContainer.setBackgroundDrawable(paramDrawable);
    if (paramDrawable != null)
    {
      Rect localRect = new Rect();
      paramDrawable.getPadding(localRect);
      mContainer.setPadding(left, top, right, bottom);
      return;
    }
    mContainer.setPadding(0, 0, 0, 0);
  }
  
  public void setColor(int paramInt)
  {
    mBackground.setColor(paramInt);
    setBackground(mBackground);
  }
  
  public void setContentPadding(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    mContentView.setPadding(paramInt1, paramInt2, paramInt3, paramInt4);
  }
  
  public void setContentRotation(int paramInt)
  {
    mRotationLayout.setViewRotation(paramInt);
  }
  
  public void setContentView(View paramView)
  {
    mRotationLayout.removeAllViews();
    mRotationLayout.addView(paramView);
    mContentView = paramView;
    paramView = mRotationLayout.findViewById(R.id.amu_text);
    if ((paramView instanceof TextView)) {
      paramView = (TextView)paramView;
    } else {
      paramView = null;
    }
    mTextView = paramView;
  }
  
  public void setRotation(int paramInt)
  {
    mRotation = ((paramInt + 360) % 360 / 90);
  }
  
  public void setStyle(int paramInt)
  {
    setColor(getStyleColor(paramInt));
    setTextAppearance(mContext, getTextStyle(paramInt));
  }
  
  public void setTextAppearance(int paramInt)
  {
    setTextAppearance(mContext, paramInt);
  }
  
  public void setTextAppearance(Context paramContext, int paramInt)
  {
    if (mTextView != null) {
      mTextView.setTextAppearance(paramContext, paramInt);
    }
  }
}
