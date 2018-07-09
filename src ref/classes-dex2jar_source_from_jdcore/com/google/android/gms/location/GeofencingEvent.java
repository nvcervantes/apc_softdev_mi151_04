package com.google.android.gms.location;

import android.content.Intent;
import android.location.Location;
import com.google.android.gms.internal.zzchp;
import java.util.ArrayList;
import java.util.List;

public class GeofencingEvent
{
  private final int zza;
  private final int zzb;
  private final List<Geofence> zzc;
  private final Location zzd;
  
  private GeofencingEvent(int paramInt1, int paramInt2, List<Geofence> paramList, Location paramLocation)
  {
    zza = paramInt1;
    zzb = paramInt2;
    zzc = paramList;
    zzd = paramLocation;
  }
  
  public static GeofencingEvent fromIntent(Intent paramIntent)
  {
    Object localObject = null;
    if (paramIntent == null) {
      return null;
    }
    int j = -1;
    int m = paramIntent.getIntExtra("gms_error_code", -1);
    int k = paramIntent.getIntExtra("com.google.android.location.intent.extra.transition", -1);
    int i = j;
    if (k != -1) {
      if ((k != 1) && (k != 2))
      {
        i = j;
        if (k != 4) {}
      }
      else
      {
        i = k;
      }
    }
    ArrayList localArrayList2 = (ArrayList)paramIntent.getSerializableExtra("com.google.android.location.intent.extra.geofence_list");
    if (localArrayList2 != null)
    {
      ArrayList localArrayList1 = new ArrayList(localArrayList2.size());
      localArrayList2 = (ArrayList)localArrayList2;
      k = localArrayList2.size();
      j = 0;
      for (;;)
      {
        localObject = localArrayList1;
        if (j >= k) {
          break;
        }
        localObject = localArrayList2.get(j);
        j += 1;
        localArrayList1.add(zzchp.zza((byte[])localObject));
      }
    }
    return new GeofencingEvent(m, i, localObject, (Location)paramIntent.getParcelableExtra("com.google.android.location.intent.extra.triggering_location"));
  }
  
  public int getErrorCode()
  {
    return zza;
  }
  
  public int getGeofenceTransition()
  {
    return zzb;
  }
  
  public List<Geofence> getTriggeringGeofences()
  {
    return zzc;
  }
  
  public Location getTriggeringLocation()
  {
    return zzd;
  }
  
  public boolean hasError()
  {
    return zza != -1;
  }
}
