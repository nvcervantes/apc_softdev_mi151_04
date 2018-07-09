package com.google.android.gms.internal;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.Callback;
import android.graphics.drawable.Drawable.ConstantState;
import android.os.SystemClock;

public final class zzbge
  extends Drawable
  implements Drawable.Callback
{
  private int zza = 0;
  private long zzb;
  private int zzc;
  private int zzd;
  private int zze = 255;
  private int zzf;
  private int zzg = 0;
  private boolean zzh = true;
  private boolean zzi;
  private zzbgi zzj;
  private Drawable zzk;
  private Drawable zzl;
  private boolean zzm;
  private boolean zzn;
  private boolean zzo;
  private int zzp;
  
  public zzbge(Drawable paramDrawable1, Drawable paramDrawable2)
  {
    this(null);
    Object localObject = paramDrawable1;
    if (paramDrawable1 == null) {
      localObject = zzbgg.zza();
    }
    zzk = ((Drawable)localObject);
    ((Drawable)localObject).setCallback(this);
    paramDrawable1 = zzj;
    int i = zzb;
    zzb = (((Drawable)localObject).getChangingConfigurations() | i);
    paramDrawable1 = paramDrawable2;
    if (paramDrawable2 == null) {
      paramDrawable1 = zzbgg.zza();
    }
    zzl = paramDrawable1;
    paramDrawable1.setCallback(this);
    paramDrawable2 = zzj;
    i = zzb;
    zzb = (paramDrawable1.getChangingConfigurations() | i);
  }
  
  zzbge(zzbgi paramZzbgi)
  {
    zzj = new zzbgi(paramZzbgi);
  }
  
  private final boolean zzb()
  {
    if (!zzm)
    {
      boolean bool;
      if ((zzk.getConstantState() != null) && (zzl.getConstantState() != null)) {
        bool = true;
      } else {
        bool = false;
      }
      zzn = bool;
      zzm = true;
    }
    return zzn;
  }
  
  public final void draw(Canvas paramCanvas)
  {
    int k = zza;
    int i = 1;
    int j = 1;
    switch (k)
    {
    default: 
      break;
    case 2: 
      if (zzb >= 0L)
      {
        float f = (float)(SystemClock.uptimeMillis() - zzb) / zzf;
        if (f >= 1.0F) {
          i = j;
        } else {
          i = 0;
        }
        if (i != 0) {
          zza = 0;
        }
        f = Math.min(f, 1.0F);
        zzg = ((int)(0.0F + zzd * f));
      }
      break;
    case 1: 
      zzb = SystemClock.uptimeMillis();
      zza = 2;
      i = 0;
    }
    j = zzg;
    boolean bool = zzh;
    Drawable localDrawable1 = zzk;
    Drawable localDrawable2 = zzl;
    if (i != 0)
    {
      if ((!bool) || (j == 0)) {
        localDrawable1.draw(paramCanvas);
      }
      if (j == zze)
      {
        localDrawable2.setAlpha(zze);
        localDrawable2.draw(paramCanvas);
      }
      return;
    }
    if (bool) {
      localDrawable1.setAlpha(zze - j);
    }
    localDrawable1.draw(paramCanvas);
    if (bool) {
      localDrawable1.setAlpha(zze);
    }
    if (j > 0)
    {
      localDrawable2.setAlpha(j);
      localDrawable2.draw(paramCanvas);
      localDrawable2.setAlpha(zze);
    }
    invalidateSelf();
  }
  
  public final int getChangingConfigurations()
  {
    return super.getChangingConfigurations() | zzj.zza | zzj.zzb;
  }
  
  public final Drawable.ConstantState getConstantState()
  {
    if (zzb())
    {
      zzj.zza = getChangingConfigurations();
      return zzj;
    }
    return null;
  }
  
  public final int getIntrinsicHeight()
  {
    return Math.max(zzk.getIntrinsicHeight(), zzl.getIntrinsicHeight());
  }
  
  public final int getIntrinsicWidth()
  {
    return Math.max(zzk.getIntrinsicWidth(), zzl.getIntrinsicWidth());
  }
  
  public final int getOpacity()
  {
    if (!zzo)
    {
      zzp = Drawable.resolveOpacity(zzk.getOpacity(), zzl.getOpacity());
      zzo = true;
    }
    return zzp;
  }
  
  public final void invalidateDrawable(Drawable paramDrawable)
  {
    paramDrawable = getCallback();
    if (paramDrawable != null) {
      paramDrawable.invalidateDrawable(this);
    }
  }
  
  public final Drawable mutate()
  {
    if ((!zzi) && (super.mutate() == this))
    {
      if (!zzb()) {
        throw new IllegalStateException("One or more children of this LayerDrawable does not have constant state; this drawable cannot be mutated.");
      }
      zzk.mutate();
      zzl.mutate();
      zzi = true;
    }
    return this;
  }
  
  protected final void onBoundsChange(Rect paramRect)
  {
    zzk.setBounds(paramRect);
    zzl.setBounds(paramRect);
  }
  
  public final void scheduleDrawable(Drawable paramDrawable, Runnable paramRunnable, long paramLong)
  {
    paramDrawable = getCallback();
    if (paramDrawable != null) {
      paramDrawable.scheduleDrawable(this, paramRunnable, paramLong);
    }
  }
  
  public final void setAlpha(int paramInt)
  {
    if (zzg == zze) {
      zzg = paramInt;
    }
    zze = paramInt;
    invalidateSelf();
  }
  
  public final void setColorFilter(ColorFilter paramColorFilter)
  {
    zzk.setColorFilter(paramColorFilter);
    zzl.setColorFilter(paramColorFilter);
  }
  
  public final void unscheduleDrawable(Drawable paramDrawable, Runnable paramRunnable)
  {
    paramDrawable = getCallback();
    if (paramDrawable != null) {
      paramDrawable.unscheduleDrawable(this, paramRunnable);
    }
  }
  
  public final Drawable zza()
  {
    return zzl;
  }
  
  public final void zza(int paramInt)
  {
    zzc = 0;
    zzd = zze;
    zzg = 0;
    zzf = 250;
    zza = 1;
    invalidateSelf();
  }
}
