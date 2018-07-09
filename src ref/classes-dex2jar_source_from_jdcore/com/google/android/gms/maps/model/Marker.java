package com.google.android.gms.maps.model;

import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.dynamic.zzn;
import com.google.android.gms.maps.model.internal.zzp;

public final class Marker
{
  private final zzp zza;
  
  @Hide
  public Marker(zzp paramZzp)
  {
    zza = ((zzp)zzbq.zza(paramZzp));
  }
  
  public final boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof Marker)) {
      return false;
    }
    try
    {
      boolean bool = zza.zza(zza);
      return bool;
    }
    catch (RemoteException paramObject)
    {
      throw new RuntimeRemoteException(paramObject);
    }
  }
  
  public final float getAlpha()
  {
    try
    {
      float f = zza.zzn();
      return f;
    }
    catch (RemoteException localRemoteException)
    {
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public final String getId()
  {
    try
    {
      String str = zza.zzb();
      return str;
    }
    catch (RemoteException localRemoteException)
    {
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public final LatLng getPosition()
  {
    try
    {
      LatLng localLatLng = zza.zzc();
      return localLatLng;
    }
    catch (RemoteException localRemoteException)
    {
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public final float getRotation()
  {
    try
    {
      float f = zza.zzm();
      return f;
    }
    catch (RemoteException localRemoteException)
    {
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public final String getSnippet()
  {
    try
    {
      String str = zza.zze();
      return str;
    }
    catch (RemoteException localRemoteException)
    {
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  @Nullable
  public final Object getTag()
  {
    try
    {
      Object localObject = zzn.zza(zza.zzp());
      return localObject;
    }
    catch (RemoteException localRemoteException)
    {
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public final String getTitle()
  {
    try
    {
      String str = zza.zzd();
      return str;
    }
    catch (RemoteException localRemoteException)
    {
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public final float getZIndex()
  {
    try
    {
      float f = zza.zzo();
      return f;
    }
    catch (RemoteException localRemoteException)
    {
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public final int hashCode()
  {
    try
    {
      int i = zza.zzk();
      return i;
    }
    catch (RemoteException localRemoteException)
    {
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public final void hideInfoWindow()
  {
    try
    {
      zza.zzh();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public final boolean isDraggable()
  {
    try
    {
      boolean bool = zza.zzf();
      return bool;
    }
    catch (RemoteException localRemoteException)
    {
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public final boolean isFlat()
  {
    try
    {
      boolean bool = zza.zzl();
      return bool;
    }
    catch (RemoteException localRemoteException)
    {
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public final boolean isInfoWindowShown()
  {
    try
    {
      boolean bool = zza.zzi();
      return bool;
    }
    catch (RemoteException localRemoteException)
    {
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public final boolean isVisible()
  {
    try
    {
      boolean bool = zza.zzj();
      return bool;
    }
    catch (RemoteException localRemoteException)
    {
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public final void remove()
  {
    try
    {
      zza.zza();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public final void setAlpha(float paramFloat)
  {
    try
    {
      zza.zzb(paramFloat);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public final void setAnchor(float paramFloat1, float paramFloat2)
  {
    try
    {
      zza.zza(paramFloat1, paramFloat2);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public final void setDraggable(boolean paramBoolean)
  {
    try
    {
      zza.zza(paramBoolean);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public final void setFlat(boolean paramBoolean)
  {
    try
    {
      zza.zzc(paramBoolean);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public final void setIcon(@Nullable BitmapDescriptor paramBitmapDescriptor)
  {
    if (paramBitmapDescriptor == null) {}
    try
    {
      zza.zza(null);
      return;
    }
    catch (RemoteException paramBitmapDescriptor)
    {
      for (;;) {}
    }
    paramBitmapDescriptor = paramBitmapDescriptor.zza();
    zza.zza(paramBitmapDescriptor);
    return;
    throw new RuntimeRemoteException(paramBitmapDescriptor);
  }
  
  public final void setInfoWindowAnchor(float paramFloat1, float paramFloat2)
  {
    try
    {
      zza.zzb(paramFloat1, paramFloat2);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public final void setPosition(@NonNull LatLng paramLatLng)
  {
    if (paramLatLng == null) {
      throw new IllegalArgumentException("latlng cannot be null - a position is required.");
    }
    try
    {
      zza.zza(paramLatLng);
      return;
    }
    catch (RemoteException paramLatLng)
    {
      throw new RuntimeRemoteException(paramLatLng);
    }
  }
  
  public final void setRotation(float paramFloat)
  {
    try
    {
      zza.zza(paramFloat);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public final void setSnippet(@Nullable String paramString)
  {
    try
    {
      zza.zzb(paramString);
      return;
    }
    catch (RemoteException paramString)
    {
      throw new RuntimeRemoteException(paramString);
    }
  }
  
  public final void setTag(@Nullable Object paramObject)
  {
    try
    {
      zza.zzb(zzn.zza(paramObject));
      return;
    }
    catch (RemoteException paramObject)
    {
      throw new RuntimeRemoteException(paramObject);
    }
  }
  
  public final void setTitle(@Nullable String paramString)
  {
    try
    {
      zza.zza(paramString);
      return;
    }
    catch (RemoteException paramString)
    {
      throw new RuntimeRemoteException(paramString);
    }
  }
  
  public final void setVisible(boolean paramBoolean)
  {
    try
    {
      zza.zzb(paramBoolean);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public final void setZIndex(float paramFloat)
  {
    try
    {
      zza.zzc(paramFloat);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public final void showInfoWindow()
  {
    try
    {
      zza.zzg();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
}
