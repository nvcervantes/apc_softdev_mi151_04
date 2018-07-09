package com.android.volley.toolbox;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import com.android.volley.VolleyError;

public class NetworkImageView
  extends ImageView
{
  private int mDefaultImageId;
  private int mErrorImageId;
  private ImageLoader.ImageContainer mImageContainer;
  private ImageLoader mImageLoader;
  private String mUrl;
  
  public NetworkImageView(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public NetworkImageView(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }
  
  public NetworkImageView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }
  
  private void setDefaultImageOrNull()
  {
    if (mDefaultImageId != 0)
    {
      setImageResource(mDefaultImageId);
      return;
    }
    setImageBitmap(null);
  }
  
  protected void drawableStateChanged()
  {
    super.drawableStateChanged();
    invalidate();
  }
  
  void loadImageIfNecessary(final boolean paramBoolean)
  {
    int n = getWidth();
    int m = getHeight();
    ImageView.ScaleType localScaleType = getScaleType();
    ViewGroup.LayoutParams localLayoutParams = getLayoutParams();
    int k = 1;
    int i;
    int j;
    if (localLayoutParams != null)
    {
      if (getLayoutParamswidth == -2) {
        i = 1;
      } else {
        i = 0;
      }
      int i1;
      if (getLayoutParamsheight == -2)
      {
        i1 = 1;
        j = i;
        i = i1;
      }
      else
      {
        i1 = 0;
        j = i;
        i = i1;
      }
    }
    else
    {
      j = 0;
      i = j;
    }
    if ((j == 0) || (i == 0)) {
      k = 0;
    }
    if ((n == 0) && (m == 0) && (k == 0)) {
      return;
    }
    if (TextUtils.isEmpty(mUrl))
    {
      if (mImageContainer != null)
      {
        mImageContainer.cancelRequest();
        mImageContainer = null;
      }
      setDefaultImageOrNull();
      return;
    }
    if ((mImageContainer != null) && (mImageContainer.getRequestUrl() != null))
    {
      if (mImageContainer.getRequestUrl().equals(mUrl)) {
        return;
      }
      mImageContainer.cancelRequest();
      setDefaultImageOrNull();
    }
    k = n;
    if (j != 0) {
      k = 0;
    }
    if (i != 0) {
      i = 0;
    } else {
      i = m;
    }
    mImageContainer = mImageLoader.get(mUrl, new ImageLoader.ImageListener()
    {
      public void onErrorResponse(VolleyError paramAnonymousVolleyError)
      {
        if (mErrorImageId != 0) {
          setImageResource(mErrorImageId);
        }
      }
      
      public void onResponse(final ImageLoader.ImageContainer paramAnonymousImageContainer, boolean paramAnonymousBoolean)
      {
        if ((paramAnonymousBoolean) && (paramBoolean))
        {
          post(new Runnable()
          {
            public void run()
            {
              onResponse(paramAnonymousImageContainer, false);
            }
          });
          return;
        }
        if (paramAnonymousImageContainer.getBitmap() != null)
        {
          setImageBitmap(paramAnonymousImageContainer.getBitmap());
          return;
        }
        if (mDefaultImageId != 0) {
          setImageResource(mDefaultImageId);
        }
      }
    }, k, i, localScaleType);
  }
  
  protected void onDetachedFromWindow()
  {
    if (mImageContainer != null)
    {
      mImageContainer.cancelRequest();
      setImageBitmap(null);
      mImageContainer = null;
    }
    super.onDetachedFromWindow();
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
    loadImageIfNecessary(true);
  }
  
  public void setDefaultImageResId(int paramInt)
  {
    mDefaultImageId = paramInt;
  }
  
  public void setErrorImageResId(int paramInt)
  {
    mErrorImageId = paramInt;
  }
  
  public void setImageUrl(String paramString, ImageLoader paramImageLoader)
  {
    mUrl = paramString;
    mImageLoader = paramImageLoader;
    loadImageIfNecessary(false);
  }
}
