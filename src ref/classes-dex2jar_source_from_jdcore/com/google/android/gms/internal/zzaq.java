package com.google.android.gms.internal;

import java.io.InputStream;
import java.util.Collections;
import java.util.List;

public final class zzaq
{
  private final int zza;
  private final List<zzl> zzb;
  private final int zzc;
  private final InputStream zzd;
  
  public zzaq(int paramInt, List<zzl> paramList)
  {
    this(paramInt, paramList, -1, null);
  }
  
  public zzaq(int paramInt1, List<zzl> paramList, int paramInt2, InputStream paramInputStream)
  {
    zza = paramInt1;
    zzb = paramList;
    zzc = paramInt2;
    zzd = paramInputStream;
  }
  
  public final int zza()
  {
    return zza;
  }
  
  public final List<zzl> zzb()
  {
    return Collections.unmodifiableList(zzb);
  }
  
  public final int zzc()
  {
    return zzc;
  }
  
  public final InputStream zzd()
  {
    return zzd;
  }
}
