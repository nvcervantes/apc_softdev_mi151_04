package com.google.android.gms.internal;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.ConstantState;

final class zzbgi
  extends Drawable.ConstantState
{
  int zza;
  int zzb;
  
  zzbgi(zzbgi paramZzbgi)
  {
    if (paramZzbgi != null)
    {
      zza = zza;
      zzb = zzb;
    }
  }
  
  public final int getChangingConfigurations()
  {
    return zza;
  }
  
  public final Drawable newDrawable()
  {
    return new zzbge(this);
  }
}
