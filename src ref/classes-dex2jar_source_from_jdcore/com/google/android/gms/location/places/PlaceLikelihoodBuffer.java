package com.google.android.gms.location.places;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.AbstractDataBuffer;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.common.internal.zzbi;
import com.google.android.gms.location.places.internal.zzai;
import com.google.android.gms.location.places.internal.zzak;
import java.util.Comparator;

public class PlaceLikelihoodBuffer
  extends AbstractDataBuffer<PlaceLikelihood>
  implements Result
{
  private static final Comparator<zzai> zzb = new zzi();
  private final String zzc;
  private final int zzd;
  private final Status zze;
  private final boolean zzf;
  
  @Hide
  public PlaceLikelihoodBuffer(DataHolder paramDataHolder, int paramInt)
  {
    this(paramDataHolder, false, paramInt);
  }
  
  @Hide
  private PlaceLikelihoodBuffer(DataHolder paramDataHolder, boolean paramBoolean, int paramInt)
  {
    super(paramDataHolder);
    zze = PlacesStatusCodes.zza(paramDataHolder.zzb());
    switch (paramInt)
    {
    default: 
      paramDataHolder = new StringBuilder(27);
      paramDataHolder.append("invalid source: ");
      paramDataHolder.append(paramInt);
      throw new IllegalArgumentException(paramDataHolder.toString());
    }
    zzd = paramInt;
    zzf = false;
    if ((paramDataHolder != null) && (paramDataHolder.zzc() != null)) {}
    for (paramDataHolder = paramDataHolder.zzc().getString("com.google.android.gms.location.places.PlaceLikelihoodBuffer.ATTRIBUTIONS_EXTRA_KEY");; paramDataHolder = null)
    {
      zzc = paramDataHolder;
      return;
    }
  }
  
  @Hide
  public static int zza(Bundle paramBundle)
  {
    return paramBundle.getInt("com.google.android.gms.location.places.PlaceLikelihoodBuffer.SOURCE_EXTRA_KEY");
  }
  
  public PlaceLikelihood get(int paramInt)
  {
    return new zzak(zza, paramInt);
  }
  
  @Nullable
  public CharSequence getAttributions()
  {
    return zzc;
  }
  
  public Status getStatus()
  {
    return zze;
  }
  
  @Hide
  public String toString()
  {
    return zzbg.zza(this).zza("status", getStatus()).zza("attributions", zzc).toString();
  }
}
