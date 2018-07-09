package com.google.android.gms.common.images;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.common.internal.zzc;
import com.google.android.gms.internal.zzbgk;

@Hide
public abstract class zza
{
  final zzb zza;
  protected int zzb = 0;
  private int zzc = 0;
  private boolean zzd = false;
  private boolean zze = true;
  private boolean zzf = false;
  private boolean zzg = true;
  
  public zza(Uri paramUri, int paramInt)
  {
    zza = new zzb(paramUri);
    zzb = paramInt;
  }
  
  final void zza(Context paramContext, Bitmap paramBitmap, boolean paramBoolean)
  {
    zzc.zza(paramBitmap);
    zza(new BitmapDrawable(paramContext.getResources(), paramBitmap), paramBoolean, false, true);
  }
  
  final void zza(Context paramContext, zzbgk paramZzbgk)
  {
    if (zzg) {
      zza(null, false, true, false);
    }
  }
  
  final void zza(Context paramContext, zzbgk paramZzbgk, boolean paramBoolean)
  {
    if (zzb != 0)
    {
      int i = zzb;
      paramContext = paramContext.getResources().getDrawable(i);
    }
    else
    {
      paramContext = null;
    }
    zza(paramContext, paramBoolean, false, false);
  }
  
  protected abstract void zza(Drawable paramDrawable, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3);
  
  protected final boolean zza(boolean paramBoolean1, boolean paramBoolean2)
  {
    return (zze) && (!paramBoolean2) && (!paramBoolean1);
  }
}
