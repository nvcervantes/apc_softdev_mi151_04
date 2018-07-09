package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import com.google.android.gms.internal.zzchp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GeofencingRequest
  extends zzbgl
{
  public static final Parcelable.Creator<GeofencingRequest> CREATOR = new zzq();
  public static final int INITIAL_TRIGGER_DWELL = 4;
  public static final int INITIAL_TRIGGER_ENTER = 1;
  public static final int INITIAL_TRIGGER_EXIT = 2;
  private final List<zzchp> zza;
  private final int zzb;
  @Hide
  private final String zzc;
  
  GeofencingRequest(List<zzchp> paramList, int paramInt, String paramString)
  {
    zza = paramList;
    zzb = paramInt;
    zzc = paramString;
  }
  
  public List<Geofence> getGeofences()
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.addAll(zza);
    return localArrayList;
  }
  
  public int getInitialTrigger()
  {
    return zzb;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("GeofencingRequest[");
    localStringBuilder.append("geofences=");
    localStringBuilder.append(zza);
    int i = zzb;
    Object localObject = new StringBuilder(30);
    ((StringBuilder)localObject).append(", initialTrigger=");
    ((StringBuilder)localObject).append(i);
    ((StringBuilder)localObject).append(", ");
    localStringBuilder.append(((StringBuilder)localObject).toString());
    localObject = String.valueOf(zzc);
    if (((String)localObject).length() != 0) {
      localObject = "tag=".concat((String)localObject);
    } else {
      localObject = new String("tag=");
    }
    localStringBuilder.append((String)localObject);
    localStringBuilder.append("]");
    return localStringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramInt = zzbgo.zza(paramParcel);
    zzbgo.zzc(paramParcel, 1, zza, false);
    zzbgo.zza(paramParcel, 2, getInitialTrigger());
    zzbgo.zza(paramParcel, 3, zzc, false);
    zzbgo.zza(paramParcel, paramInt);
  }
  
  public static final class Builder
  {
    private final List<zzchp> zza = new ArrayList();
    private int zzb = 5;
    private String zzc = "";
    
    public Builder() {}
    
    public final Builder addGeofence(Geofence paramGeofence)
    {
      zzbq.zza(paramGeofence, "geofence can't be null.");
      zzbq.zzb(paramGeofence instanceof zzchp, "Geofence must be created using Geofence.Builder.");
      zza.add((zzchp)paramGeofence);
      return this;
    }
    
    public final Builder addGeofences(List<Geofence> paramList)
    {
      if (paramList != null)
      {
        if (paramList.isEmpty()) {
          return this;
        }
        paramList = paramList.iterator();
        while (paramList.hasNext())
        {
          Geofence localGeofence = (Geofence)paramList.next();
          if (localGeofence != null) {
            addGeofence(localGeofence);
          }
        }
      }
      return this;
    }
    
    public final GeofencingRequest build()
    {
      zzbq.zzb(zza.isEmpty() ^ true, "No geofence has been added to this request.");
      return new GeofencingRequest(zza, zzb, zzc);
    }
    
    public final Builder setInitialTrigger(int paramInt)
    {
      zzb = (paramInt & 0x7);
      return this;
    }
  }
}
