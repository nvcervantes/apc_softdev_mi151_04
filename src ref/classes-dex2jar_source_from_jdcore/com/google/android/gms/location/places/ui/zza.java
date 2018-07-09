package com.google.android.gms.location.places.ui;

import android.content.Context;
import android.content.Intent;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbgq;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.internal.PlaceEntity;

@Hide
class zza
{
  public static final int RESULT_ERROR = 2;
  
  zza() {}
  
  @Hide
  public static Place getPlace(Context paramContext, Intent paramIntent)
  {
    zzbq.zza(paramIntent, "intent must not be null");
    zzbq.zza(paramContext, "context must not be null");
    return (Place)zzbgq.zza(paramIntent, "selected_place", PlaceEntity.CREATOR);
  }
  
  @Hide
  public static Status getStatus(Context paramContext, Intent paramIntent)
  {
    zzbq.zza(paramIntent, "intent must not be null");
    zzbq.zza(paramContext, "context must not be null");
    return (Status)zzbgq.zza(paramIntent, "status", Status.CREATOR);
  }
}
