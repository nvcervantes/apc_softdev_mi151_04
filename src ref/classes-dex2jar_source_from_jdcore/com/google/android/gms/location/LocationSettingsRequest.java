package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public final class LocationSettingsRequest
  extends zzbgl
{
  public static final Parcelable.Creator<LocationSettingsRequest> CREATOR = new zzag();
  private final List<LocationRequest> zza;
  private final boolean zzb;
  private final boolean zzc;
  @Hide
  private zzae zzd;
  
  @Hide
  LocationSettingsRequest(List<LocationRequest> paramList, boolean paramBoolean1, boolean paramBoolean2, zzae paramZzae)
  {
    zza = paramList;
    zzb = paramBoolean1;
    zzc = paramBoolean2;
    zzd = paramZzae;
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt)
  {
    int i = zzbgo.zza(paramParcel);
    zzbgo.zzc(paramParcel, 1, Collections.unmodifiableList(zza), false);
    zzbgo.zza(paramParcel, 2, zzb);
    zzbgo.zza(paramParcel, 3, zzc);
    zzbgo.zza(paramParcel, 5, zzd, paramInt, false);
    zzbgo.zza(paramParcel, i);
  }
  
  public static final class Builder
  {
    private final ArrayList<LocationRequest> zza = new ArrayList();
    private boolean zzb = false;
    private boolean zzc = false;
    private zzae zzd = null;
    
    public Builder() {}
    
    public final Builder addAllLocationRequests(Collection<LocationRequest> paramCollection)
    {
      paramCollection = paramCollection.iterator();
      while (paramCollection.hasNext())
      {
        LocationRequest localLocationRequest = (LocationRequest)paramCollection.next();
        if (localLocationRequest != null) {
          zza.add(localLocationRequest);
        }
      }
      return this;
    }
    
    public final Builder addLocationRequest(@NonNull LocationRequest paramLocationRequest)
    {
      if (paramLocationRequest != null) {
        zza.add(paramLocationRequest);
      }
      return this;
    }
    
    public final LocationSettingsRequest build()
    {
      return new LocationSettingsRequest(zza, zzb, zzc, null);
    }
    
    public final Builder setAlwaysShow(boolean paramBoolean)
    {
      zzb = paramBoolean;
      return this;
    }
    
    public final Builder setNeedBle(boolean paramBoolean)
    {
      zzc = paramBoolean;
      return this;
    }
  }
}
