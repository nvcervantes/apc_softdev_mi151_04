package com.google.android.gms.common.internal;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;

@Hide
public final class zzcb
{
  public static String zza(String paramString1, String paramString2, Context paramContext, AttributeSet paramAttributeSet, boolean paramBoolean1, boolean paramBoolean2, String paramString3)
  {
    if (paramAttributeSet == null) {
      paramString1 = null;
    } else {
      paramString1 = paramAttributeSet.getAttributeValue(paramString1, paramString2);
    }
    String str1;
    String str2;
    if ((paramString1 != null) && (paramString1.startsWith("@string/")))
    {
      str1 = paramString1.substring(8);
      str2 = paramContext.getPackageName();
      paramAttributeSet = new TypedValue();
    }
    try
    {
      paramContext = paramContext.getResources();
      StringBuilder localStringBuilder = new StringBuilder(8 + String.valueOf(str2).length() + String.valueOf(str1).length());
      localStringBuilder.append(str2);
      localStringBuilder.append(":string/");
      localStringBuilder.append(str1);
      paramContext.getValue(localStringBuilder.toString(), paramAttributeSet, true);
    }
    catch (Resources.NotFoundException paramContext)
    {
      for (;;) {}
    }
    paramContext = new StringBuilder(30 + String.valueOf(paramString2).length() + String.valueOf(paramString1).length());
    paramContext.append("Could not find resource for ");
    paramContext.append(paramString2);
    paramContext.append(": ");
    paramContext.append(paramString1);
    Log.w(paramString3, paramContext.toString());
    if (string != null) {
      return string.toString();
    }
    paramContext = String.valueOf(paramAttributeSet);
    paramAttributeSet = new StringBuilder(28 + String.valueOf(paramString2).length() + String.valueOf(paramContext).length());
    paramAttributeSet.append("Resource ");
    paramAttributeSet.append(paramString2);
    paramAttributeSet.append(" was not a string: ");
    paramAttributeSet.append(paramContext);
    Log.w(paramString3, paramAttributeSet.toString());
    return paramString1;
  }
}
