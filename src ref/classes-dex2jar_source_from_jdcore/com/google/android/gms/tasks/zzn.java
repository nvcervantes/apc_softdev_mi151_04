package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import java.util.ArrayDeque;
import java.util.Queue;

final class zzn<TResult>
{
  private final Object zza = new Object();
  private Queue<zzm<TResult>> zzb;
  private boolean zzc;
  
  zzn() {}
  
  public final void zza(@NonNull Task<TResult> paramTask)
  {
    synchronized (zza)
    {
      if ((zzb != null) && (!zzc))
      {
        zzc = true;
        synchronized (zza)
        {
          zzm localZzm = (zzm)zzb.poll();
          if (localZzm == null)
          {
            zzc = false;
            return;
          }
          localZzm.zza(paramTask);
        }
      }
      return;
    }
  }
  
  public final void zza(@NonNull zzm<TResult> paramZzm)
  {
    synchronized (zza)
    {
      if (zzb == null) {
        zzb = new ArrayDeque();
      }
      zzb.add(paramZzm);
      return;
    }
  }
}
