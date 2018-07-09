package com.google.android.gms.common;

import android.content.Intent;
import com.google.android.gms.common.internal.Hide;

public class GooglePlayServicesRepairableException
  extends UserRecoverableException
{
  private final int zza;
  
  @Hide
  public GooglePlayServicesRepairableException(int paramInt, String paramString, Intent paramIntent)
  {
    super(paramString, paramIntent);
    zza = paramInt;
  }
  
  public int getConnectionStatusCode()
  {
    return zza;
  }
}
