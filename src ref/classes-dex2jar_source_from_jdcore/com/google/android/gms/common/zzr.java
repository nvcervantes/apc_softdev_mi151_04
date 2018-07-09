package com.google.android.gms.common;

import com.google.android.gms.common.util.zza;
import com.google.android.gms.common.util.zzm;
import java.security.MessageDigest;

final class zzr
  extends zzp
{
  private final String zzc;
  private final zzh zzd;
  private final boolean zze;
  private final boolean zzf;
  
  private zzr(String paramString, zzh paramZzh, boolean paramBoolean1, boolean paramBoolean2)
  {
    super(false, null, null);
    zzc = paramString;
    zzd = paramZzh;
    zze = paramBoolean1;
    zzf = paramBoolean2;
  }
  
  final String zzb()
  {
    String str1;
    if (zzf) {
      str1 = "debug cert rejected";
    } else {
      str1 = "not whitelisted";
    }
    String str2 = zzc;
    Object localObject = zzd;
    localObject = zzm.zzb(zza.zza("SHA-1").digest(((zzh)localObject).zza()));
    boolean bool = zze;
    StringBuilder localStringBuilder = new StringBuilder(44 + String.valueOf(str1).length() + String.valueOf(str2).length() + String.valueOf(localObject).length());
    localStringBuilder.append(str1);
    localStringBuilder.append(": pkg=");
    localStringBuilder.append(str2);
    localStringBuilder.append(", sha1=");
    localStringBuilder.append((String)localObject);
    localStringBuilder.append(", atk=");
    localStringBuilder.append(bool);
    localStringBuilder.append(", ver=12210278.false");
    return localStringBuilder.toString();
  }
}
