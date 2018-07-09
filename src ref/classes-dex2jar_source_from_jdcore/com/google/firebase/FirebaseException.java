package com.google.firebase;

import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.zzbq;

public class FirebaseException
  extends Exception
{
  @Deprecated
  protected FirebaseException() {}
  
  public FirebaseException(@NonNull String paramString)
  {
    super(zzbq.zza(paramString, "Detail message must not be empty"));
  }
  
  public FirebaseException(@NonNull String paramString, Throwable paramThrowable)
  {
    super(zzbq.zza(paramString, "Detail message must not be empty"), paramThrowable);
  }
}
