package com.google.android.gms.common.api.internal;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;

public abstract interface zzcf
{
  public abstract void startActivityForResult(Intent paramIntent, int paramInt);
  
  public abstract Activity zza();
  
  public abstract <T extends LifecycleCallback> T zza(String paramString, Class<T> paramClass);
  
  public abstract void zza(String paramString, @NonNull LifecycleCallback paramLifecycleCallback);
}
