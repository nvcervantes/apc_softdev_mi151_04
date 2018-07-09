package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;

public final class zzbr
  extends zzbgl
{
  public static final Parcelable.Creator<zzbr> CREATOR = new zzbs();
  private int zza;
  private final Account zzb;
  private final int zzc;
  private final GoogleSignInAccount zzd;
  
  zzbr(int paramInt1, Account paramAccount, int paramInt2, GoogleSignInAccount paramGoogleSignInAccount)
  {
    zza = paramInt1;
    zzb = paramAccount;
    zzc = paramInt2;
    zzd = paramGoogleSignInAccount;
  }
  
  public zzbr(Account paramAccount, int paramInt, GoogleSignInAccount paramGoogleSignInAccount)
  {
    this(2, paramAccount, paramInt, paramGoogleSignInAccount);
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt)
  {
    int i = zzbgo.zza(paramParcel);
    zzbgo.zza(paramParcel, 1, zza);
    zzbgo.zza(paramParcel, 2, zzb, paramInt, false);
    zzbgo.zza(paramParcel, 3, zzc);
    zzbgo.zza(paramParcel, 4, zzd, paramInt, false);
    zzbgo.zza(paramParcel, i);
  }
}
