package com.google.android.gms.internal;

import java.io.IOException;

public abstract class zzflm<M extends zzflm<M>>
  extends zzfls
{
  protected zzflo zzax;
  
  public zzflm() {}
  
  protected int zza()
  {
    zzflo localZzflo = zzax;
    int j = 0;
    if (localZzflo != null)
    {
      int i = 0;
      for (;;)
      {
        k = i;
        if (j >= zzax.zza()) {
          break;
        }
        i += zzax.zzb(j).zza();
        j += 1;
      }
    }
    int k = 0;
    return k;
  }
  
  public final <T> T zza(zzfln<M, T> paramZzfln)
  {
    if (zzax == null) {
      return null;
    }
    zzflp localZzflp = zzax.zza(zzb >>> 3);
    if (localZzflp == null) {
      return null;
    }
    return localZzflp.zza(paramZzfln);
  }
  
  public void zza(zzflk paramZzflk)
    throws IOException
  {
    if (zzax == null) {
      return;
    }
    int i = 0;
    while (i < zzax.zza())
    {
      zzax.zzb(i).zza(paramZzflk);
      i += 1;
    }
  }
  
  protected final boolean zza(zzflj paramZzflj, int paramInt)
    throws IOException
  {
    int i = paramZzflj.zzm();
    if (!paramZzflj.zzb(paramInt)) {
      return false;
    }
    int j = paramInt >>> 3;
    zzflu localZzflu = new zzflu(paramInt, paramZzflj.zza(i, paramZzflj.zzm() - i));
    paramZzflj = null;
    if (zzax == null) {
      zzax = new zzflo();
    } else {
      paramZzflj = zzax.zza(j);
    }
    Object localObject = paramZzflj;
    if (paramZzflj == null)
    {
      localObject = new zzflp();
      zzax.zza(j, (zzflp)localObject);
    }
    ((zzflp)localObject).zza(localZzflu);
    return true;
  }
  
  public M zzc()
    throws CloneNotSupportedException
  {
    zzflm localZzflm = (zzflm)super.zzd();
    zzflq.zza(this, localZzflm);
    return localZzflm;
  }
}
