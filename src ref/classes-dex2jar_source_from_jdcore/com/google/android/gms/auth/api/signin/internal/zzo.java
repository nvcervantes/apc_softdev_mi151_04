package com.google.android.gms.auth.api.signin.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.auth.api.signin.GoogleSignInOptionsExtension;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;

@Hide
public final class zzo
  extends zzbgl
{
  public static final Parcelable.Creator<zzo> CREATOR = new zzn();
  private int zza;
  private int zzb;
  private Bundle zzc;
  
  zzo(int paramInt1, int paramInt2, Bundle paramBundle)
  {
    zza = paramInt1;
    zzb = paramInt2;
    zzc = paramBundle;
  }
  
  public zzo(GoogleSignInOptionsExtension paramGoogleSignInOptionsExtension)
  {
    this(1, paramGoogleSignInOptionsExtension.getExtensionType(), paramGoogleSignInOptionsExtension.toBundle());
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramInt = zzbgo.zza(paramParcel);
    zzbgo.zza(paramParcel, 1, zza);
    zzbgo.zza(paramParcel, 2, zzb);
    zzbgo.zza(paramParcel, 3, zzc, false);
    zzbgo.zza(paramParcel, paramInt);
  }
  
  public final int zza()
  {
    return zzb;
  }
}
