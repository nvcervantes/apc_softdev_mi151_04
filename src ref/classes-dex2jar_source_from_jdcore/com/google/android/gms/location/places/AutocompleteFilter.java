package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.common.internal.zzbi;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class AutocompleteFilter
  extends zzbgl
  implements ReflectedParcelable
{
  @Hide
  public static final Parcelable.Creator<AutocompleteFilter> CREATOR = new zzc();
  public static final int TYPE_FILTER_ADDRESS = 2;
  public static final int TYPE_FILTER_CITIES = 5;
  public static final int TYPE_FILTER_ESTABLISHMENT = 34;
  public static final int TYPE_FILTER_GEOCODE = 1007;
  public static final int TYPE_FILTER_NONE = 0;
  public static final int TYPE_FILTER_REGIONS = 4;
  private int zza;
  private boolean zzb;
  private List<Integer> zzc;
  private String zzd;
  private int zze;
  
  @Hide
  AutocompleteFilter(int paramInt, boolean paramBoolean, List<Integer> paramList, String paramString)
  {
    zza = paramInt;
    zzc = paramList;
    if ((paramList != null) && (!paramList.isEmpty())) {
      paramInt = ((Integer)paramList.iterator().next()).intValue();
    } else {
      paramInt = 0;
    }
    zze = paramInt;
    zzd = paramString;
    if (zza <= 0)
    {
      zzb = (paramBoolean ^ true);
      return;
    }
    zzb = paramBoolean;
  }
  
  @Hide
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {
      return true;
    }
    if (!(paramObject instanceof AutocompleteFilter)) {
      return false;
    }
    paramObject = (AutocompleteFilter)paramObject;
    return (zze == zze) && (zzb == zzb) && (zzd == zzd);
  }
  
  public int getTypeFilter()
  {
    return zze;
  }
  
  @Hide
  public int hashCode()
  {
    return Arrays.hashCode(new Object[] { Boolean.valueOf(zzb), Integer.valueOf(zze), zzd });
  }
  
  @Hide
  public String toString()
  {
    return zzbg.zza(this).zza("includeQueryPredictions", Boolean.valueOf(zzb)).zza("typeFilter", Integer.valueOf(zze)).zza("country", zzd).toString();
  }
  
  @Hide
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramInt = zzbgo.zza(paramParcel);
    zzbgo.zza(paramParcel, 1, zzb);
    zzbgo.zza(paramParcel, 2, zzc, false);
    zzbgo.zza(paramParcel, 3, zzd, false);
    zzbgo.zza(paramParcel, 1000, zza);
    zzbgo.zza(paramParcel, paramInt);
  }
  
  public static final class Builder
  {
    private boolean zza = false;
    private int zzb = 0;
    private String zzc = "";
    
    public Builder() {}
    
    public final AutocompleteFilter build()
    {
      return new AutocompleteFilter(1, false, Arrays.asList(new Integer[] { Integer.valueOf(zzb) }), zzc);
    }
    
    public final Builder setCountry(String paramString)
    {
      zzc = paramString;
      return this;
    }
    
    public final Builder setTypeFilter(int paramInt)
    {
      zzb = paramInt;
      return this;
    }
  }
}
