package com.google.android.gms.common.internal;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuff.Mode;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.widget.Button;
import com.google.android.gms.R.color;
import com.google.android.gms.R.drawable;
import com.google.android.gms.R.string;
import com.google.android.gms.common.util.zzj;

public final class zzby
  extends Button
{
  public zzby(Context paramContext)
  {
    this(paramContext, null);
  }
  
  private zzby(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, null, 16842824);
  }
  
  private static int zza(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    switch (paramInt1)
    {
    default: 
      StringBuilder localStringBuilder = new StringBuilder(33);
      localStringBuilder.append("Unknown color scheme: ");
      localStringBuilder.append(paramInt1);
      throw new IllegalStateException(localStringBuilder.toString());
    case 2: 
      return paramInt4;
    case 1: 
      return paramInt3;
    }
    return paramInt2;
  }
  
  public final void zza(Resources paramResources, int paramInt1, int paramInt2)
  {
    setTypeface(Typeface.DEFAULT_BOLD);
    setTextSize(14.0F);
    int i = (int)(getDisplayMetricsdensity * 48.0F + 0.5F);
    setMinHeight(i);
    setMinWidth(i);
    i = zza(paramInt2, R.drawable.common_google_signin_btn_icon_dark, R.drawable.common_google_signin_btn_icon_light, R.drawable.common_google_signin_btn_icon_light);
    int j = zza(paramInt2, R.drawable.common_google_signin_btn_text_dark, R.drawable.common_google_signin_btn_text_light, R.drawable.common_google_signin_btn_text_light);
    switch (paramInt1)
    {
    default: 
      paramResources = new StringBuilder(32);
      paramResources.append("Unknown button size: ");
      paramResources.append(paramInt1);
      throw new IllegalStateException(paramResources.toString());
    case 0: 
    case 1: 
      i = j;
    }
    Drawable localDrawable = DrawableCompat.wrap(paramResources.getDrawable(i));
    DrawableCompat.setTintList(localDrawable, paramResources.getColorStateList(R.color.common_google_signin_btn_tint));
    DrawableCompat.setTintMode(localDrawable, PorterDuff.Mode.SRC_ATOP);
    setBackgroundDrawable(localDrawable);
    setTextColor((ColorStateList)zzbq.zza(paramResources.getColorStateList(zza(paramInt2, R.color.common_google_signin_btn_text_dark, R.color.common_google_signin_btn_text_light, R.color.common_google_signin_btn_text_light))));
    switch (paramInt1)
    {
    default: 
      paramResources = new StringBuilder(32);
      paramResources.append("Unknown button size: ");
      paramResources.append(paramInt1);
      throw new IllegalStateException(paramResources.toString());
    case 2: 
      setText(null);
      break;
    case 1: 
      paramInt1 = R.string.common_signin_button_text_long;
      break;
    case 0: 
      paramInt1 = R.string.common_signin_button_text;
    }
    setText(paramResources.getString(paramInt1));
    setTransformationMethod(null);
    if (zzj.zza(getContext())) {
      setGravity(19);
    }
  }
}
