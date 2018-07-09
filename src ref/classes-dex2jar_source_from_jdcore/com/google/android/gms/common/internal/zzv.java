package com.google.android.gms.common.internal;

import android.app.Activity;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import com.google.android.gms.common.api.internal.zzcf;

public abstract class zzv
  implements DialogInterface.OnClickListener
{
  public zzv() {}
  
  public static zzv zza(Activity paramActivity, Intent paramIntent, int paramInt)
  {
    return new zzw(paramIntent, paramActivity, paramInt);
  }
  
  public static zzv zza(@NonNull Fragment paramFragment, Intent paramIntent, int paramInt)
  {
    return new zzx(paramIntent, paramFragment, paramInt);
  }
  
  public static zzv zza(@NonNull zzcf paramZzcf, Intent paramIntent, int paramInt)
  {
    return new zzy(paramIntent, paramZzcf, 2);
  }
  
  /* Error */
  public void onClick(android.content.DialogInterface paramDialogInterface, int paramInt)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 38	com/google/android/gms/common/internal/zzv:zza	()V
    //   4: aload_1
    //   5: invokeinterface 43 1 0
    //   10: return
    //   11: astore_3
    //   12: goto +20 -> 32
    //   15: astore_3
    //   16: ldc 45
    //   18: ldc 47
    //   20: aload_3
    //   21: invokestatic 53	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   24: pop
    //   25: aload_1
    //   26: invokeinterface 43 1 0
    //   31: return
    //   32: aload_1
    //   33: invokeinterface 43 1 0
    //   38: aload_3
    //   39: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	40	0	this	zzv
    //   0	40	1	paramDialogInterface	android.content.DialogInterface
    //   0	40	2	paramInt	int
    //   11	1	3	localObject	Object
    //   15	24	3	localActivityNotFoundException	android.content.ActivityNotFoundException
    // Exception table:
    //   from	to	target	type
    //   0	4	11	finally
    //   16	25	11	finally
    //   0	4	15	android/content/ActivityNotFoundException
  }
  
  protected abstract void zza();
}
