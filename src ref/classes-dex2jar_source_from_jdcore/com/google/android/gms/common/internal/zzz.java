package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.zzc;
import com.google.android.gms.common.zzf;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;

public final class zzz
  extends zzbgl
{
  public static final Parcelable.Creator<zzz> CREATOR = new zzaa();
  String zza;
  IBinder zzb;
  Scope[] zzc;
  Bundle zzd;
  Account zze;
  zzc[] zzf;
  private int zzg;
  private int zzh;
  private int zzi;
  
  public zzz(int paramInt)
  {
    zzg = 3;
    zzi = zzf.GOOGLE_PLAY_SERVICES_VERSION_CODE;
    zzh = paramInt;
  }
  
  zzz(int paramInt1, int paramInt2, int paramInt3, String paramString, IBinder paramIBinder, Scope[] paramArrayOfScope, Bundle paramBundle, Account paramAccount, zzc[] paramArrayOfZzc)
  {
    zzg = paramInt1;
    zzh = paramInt2;
    zzi = paramInt3;
    if ("com.google.android.gms".equals(paramString)) {
      zza = "com.google.android.gms";
    } else {
      zza = paramString;
    }
    if (paramInt1 < 2)
    {
      paramString = null;
      paramAccount = null;
      if (paramIBinder != null)
      {
        if (paramIBinder == null)
        {
          paramString = paramAccount;
        }
        else
        {
          paramString = paramIBinder.queryLocalInterface("com.google.android.gms.common.internal.IAccountAccessor");
          if ((paramString instanceof zzan)) {
            paramString = (zzan)paramString;
          } else {
            paramString = new zzap(paramIBinder);
          }
        }
        paramString = zza.zza(paramString);
      }
      zze = paramString;
    }
    else
    {
      zzb = paramIBinder;
      zze = paramAccount;
    }
    zzc = paramArrayOfScope;
    zzd = paramBundle;
    zzf = paramArrayOfZzc;
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt)
  {
    int i = zzbgo.zza(paramParcel);
    zzbgo.zza(paramParcel, 1, zzg);
    zzbgo.zza(paramParcel, 2, zzh);
    zzbgo.zza(paramParcel, 3, zzi);
    zzbgo.zza(paramParcel, 4, zza, false);
    zzbgo.zza(paramParcel, 5, zzb, false);
    zzbgo.zza(paramParcel, 6, zzc, paramInt, false);
    zzbgo.zza(paramParcel, 7, zzd, false);
    zzbgo.zza(paramParcel, 8, zze, paramInt, false);
    zzbgo.zza(paramParcel, 10, zzf, paramInt, false);
    zzbgo.zza(paramParcel, i);
  }
}
