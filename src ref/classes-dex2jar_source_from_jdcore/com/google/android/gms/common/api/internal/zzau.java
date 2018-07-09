package com.google.android.gms.common.api.internal;

import android.support.annotation.WorkerThread;
import com.google.android.gms.common.api.Api.zze;
import java.util.ArrayList;

final class zzau
  extends zzay
{
  private final ArrayList<Api.zze> zza;
  
  public zzau(ArrayList<Api.zze> paramArrayList)
  {
    super(paramArrayList, null);
    Object localObject;
    zza = localObject;
  }
  
  @WorkerThread
  public final void zza()
  {
    zzczzb).zzd.zzc = zzao.zzf(zzb);
    ArrayList localArrayList = (ArrayList)zza;
    int j = localArrayList.size();
    int i = 0;
    while (i < j)
    {
      Object localObject = localArrayList.get(i);
      i += 1;
      ((Api.zze)localObject).zza(zzao.zzg(zzb), zzczzb).zzd.zzc);
    }
  }
}
