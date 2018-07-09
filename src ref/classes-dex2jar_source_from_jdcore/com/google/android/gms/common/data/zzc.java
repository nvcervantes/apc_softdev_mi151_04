package com.google.android.gms.common.data;

import android.database.CharArrayBuffer;
import android.net.Uri;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.common.internal.zzbq;
import java.util.Arrays;

@Hide
public class zzc
{
  protected final DataHolder zza;
  protected int zzb;
  private int zzc;
  
  public zzc(DataHolder paramDataHolder, int paramInt)
  {
    zza = ((DataHolder)zzbq.zza(paramDataHolder));
    zza(paramInt);
  }
  
  public boolean equals(Object paramObject)
  {
    if ((paramObject instanceof zzc))
    {
      paramObject = (zzc)paramObject;
      if ((zzbg.zza(Integer.valueOf(zzb), Integer.valueOf(zzb))) && (zzbg.zza(Integer.valueOf(zzc), Integer.valueOf(zzc))) && (zza == zza)) {
        return true;
      }
    }
    return false;
  }
  
  public int hashCode()
  {
    return Arrays.hashCode(new Object[] { Integer.valueOf(zzb), Integer.valueOf(zzc), zza });
  }
  
  public boolean isDataValid()
  {
    return !zza.zze();
  }
  
  protected final void zza(int paramInt)
  {
    boolean bool;
    if ((paramInt >= 0) && (paramInt < zza.zza)) {
      bool = true;
    } else {
      bool = false;
    }
    zzbq.zza(bool);
    zzb = paramInt;
    zzc = zza.zza(zzb);
  }
  
  protected final void zza(String paramString, CharArrayBuffer paramCharArrayBuffer)
  {
    zza.zza(paramString, zzb, zzc, paramCharArrayBuffer);
  }
  
  public final boolean zza(String paramString)
  {
    return zza.zza(paramString);
  }
  
  protected final long zzb(String paramString)
  {
    return zza.zza(paramString, zzb, zzc);
  }
  
  protected final int zzc(String paramString)
  {
    return zza.zzb(paramString, zzb, zzc);
  }
  
  protected final boolean zzd(String paramString)
  {
    return zza.zzd(paramString, zzb, zzc);
  }
  
  protected final String zze(String paramString)
  {
    return zza.zzc(paramString, zzb, zzc);
  }
  
  protected final float zzf(String paramString)
  {
    return zza.zze(paramString, zzb, zzc);
  }
  
  protected final byte[] zzg(String paramString)
  {
    return zza.zzf(paramString, zzb, zzc);
  }
  
  protected final Uri zzh(String paramString)
  {
    paramString = zza.zzc(paramString, zzb, zzc);
    if (paramString == null) {
      return null;
    }
    return Uri.parse(paramString);
  }
  
  protected final boolean zzi(String paramString)
  {
    return zza.zzg(paramString, zzb, zzc);
  }
}
