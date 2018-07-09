package com.google.android.gms.common.api;

import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.internal.zzh;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.common.internal.zzbq;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class AvailabilityException
  extends Exception
{
  private final ArrayMap<zzh<?>, ConnectionResult> zza;
  
  @Hide
  public AvailabilityException(ArrayMap<zzh<?>, ConnectionResult> paramArrayMap)
  {
    zza = paramArrayMap;
  }
  
  public ConnectionResult getConnectionResult(GoogleApi<? extends Api.ApiOptions> paramGoogleApi)
  {
    paramGoogleApi = paramGoogleApi.zzc();
    boolean bool;
    if (zza.get(paramGoogleApi) != null) {
      bool = true;
    } else {
      bool = false;
    }
    zzbq.zzb(bool, "The given API was not part of the availability request.");
    return (ConnectionResult)zza.get(paramGoogleApi);
  }
  
  public String getMessage()
  {
    ArrayList localArrayList = new ArrayList();
    Object localObject1 = zza.keySet().iterator();
    int i = 1;
    while (((Iterator)localObject1).hasNext())
    {
      localObject2 = (zzh)((Iterator)localObject1).next();
      Object localObject3 = (ConnectionResult)zza.get(localObject2);
      if (((ConnectionResult)localObject3).isSuccess()) {
        i = 0;
      }
      localObject2 = ((zzh)localObject2).zza();
      localObject3 = String.valueOf(localObject3);
      StringBuilder localStringBuilder = new StringBuilder(2 + String.valueOf(localObject2).length() + String.valueOf(localObject3).length());
      localStringBuilder.append((String)localObject2);
      localStringBuilder.append(": ");
      localStringBuilder.append((String)localObject3);
      localArrayList.add(localStringBuilder.toString());
    }
    Object localObject2 = new StringBuilder();
    if (i != 0) {}
    for (localObject1 = "None of the queried APIs are available. ";; localObject1 = "Some of the queried APIs are unavailable. ")
    {
      ((StringBuilder)localObject2).append((String)localObject1);
      break;
    }
    ((StringBuilder)localObject2).append(TextUtils.join("; ", localArrayList));
    return ((StringBuilder)localObject2).toString();
  }
  
  @Hide
  public final ArrayMap<zzh<?>, ConnectionResult> zza()
  {
    return zza;
  }
}
