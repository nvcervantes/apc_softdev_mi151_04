package com.google.android.gms.common.internal;

import android.content.Context;
import android.view.View;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzn;
import com.google.android.gms.dynamic.zzp;
import com.google.android.gms.dynamic.zzq;

public final class zzbx
  extends zzp<zzbd>
{
  private static final zzbx zza = new zzbx();
  
  private zzbx()
  {
    super("com.google.android.gms.common.ui.SignInButtonCreatorImpl");
  }
  
  public static View zza(Context paramContext, int paramInt1, int paramInt2)
    throws zzq
  {
    return zza.zzb(paramContext, paramInt1, paramInt2);
  }
  
  private final View zzb(Context paramContext, int paramInt1, int paramInt2)
    throws zzq
  {
    try
    {
      localObject = new zzbv(paramInt1, paramInt2, null);
      IObjectWrapper localIObjectWrapper = zzn.zza(paramContext);
      paramContext = (View)zzn.zza(((zzbd)zzb(paramContext)).zza(localIObjectWrapper, (zzbv)localObject));
      return paramContext;
    }
    catch (Exception paramContext)
    {
      Object localObject = new StringBuilder(64);
      ((StringBuilder)localObject).append("Could not get button with size ");
      ((StringBuilder)localObject).append(paramInt1);
      ((StringBuilder)localObject).append(" and color ");
      ((StringBuilder)localObject).append(paramInt2);
      throw new zzq(((StringBuilder)localObject).toString(), paramContext);
    }
  }
}
