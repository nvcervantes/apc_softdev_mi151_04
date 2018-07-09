package com.google.android.gms.clearcut;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Log;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.ApiOptions.NoOptions;
import com.google.android.gms.common.api.Api.zza;
import com.google.android.gms.common.api.Api.zzf;
import com.google.android.gms.common.api.PendingResults;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.util.zzi;
import com.google.android.gms.internal.zzbfi;
import com.google.android.gms.internal.zzbfn;
import com.google.android.gms.internal.zzbft;
import com.google.android.gms.internal.zzbfv;
import com.google.android.gms.internal.zzfmr;
import com.google.android.gms.phenotype.ExperimentTokens;
import java.util.ArrayList;
import java.util.TimeZone;

@KeepForSdk
public final class ClearcutLogger
{
  @Deprecated
  public static final Api<Api.ApiOptions.NoOptions> zza = new Api("ClearcutLogger.API", zzc, zzb);
  @Hide
  private static Api.zzf<zzbfn> zzb = new Api.zzf();
  @Hide
  private static Api.zza<zzbfn, Api.ApiOptions.NoOptions> zzc = new zza();
  private static final ExperimentTokens[] zzd = new ExperimentTokens[0];
  private static final String[] zze = new String[0];
  private static final byte[][] zzf = new byte[0][];
  private final String zzg;
  private final int zzh;
  private String zzi;
  private int zzj = -1;
  private String zzk;
  private String zzl;
  private final boolean zzm;
  private int zzn = 0;
  private final zzb zzo;
  private final com.google.android.gms.common.util.zze zzp;
  private zzc zzq;
  private final zza zzr;
  
  private ClearcutLogger(Context paramContext, int paramInt, String paramString1, String paramString2, String paramString3, boolean paramBoolean, zzb paramZzb, com.google.android.gms.common.util.zze paramZze, zzc paramZzc, zza paramZza)
  {
    zzg = paramContext.getPackageName();
    zzh = zza(paramContext);
    zzj = -1;
    zzi = paramString1;
    zzk = null;
    zzl = null;
    zzm = true;
    zzo = paramZzb;
    zzp = paramZze;
    zzq = new zzc();
    zzn = 0;
    zzr = paramZza;
    zzbq.zzb(true, "can't be anonymous with an upload account");
  }
  
  @KeepForSdk
  public static ClearcutLogger anonymousLogger(Context paramContext, String paramString)
  {
    return new ClearcutLogger(paramContext, -1, paramString, null, null, true, zzbfi.zza(paramContext), zzi.zzd(), null, new zzbft(paramContext));
  }
  
  private static int zza(Context paramContext)
  {
    try
    {
      int i = getPackageManagergetPackageInfogetPackageName0versionCode;
      return i;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      Log.wtf("ClearcutLogger", "This can't happen.", paramContext);
    }
    return 0;
  }
  
  private static int[] zzb(ArrayList<Integer> paramArrayList)
  {
    if (paramArrayList == null) {
      return null;
    }
    int[] arrayOfInt = new int[paramArrayList.size()];
    paramArrayList = (ArrayList)paramArrayList;
    int k = paramArrayList.size();
    int j = 0;
    int i = 0;
    while (j < k)
    {
      Object localObject = paramArrayList.get(j);
      j += 1;
      arrayOfInt[i] = ((Integer)localObject).intValue();
      i += 1;
    }
    return arrayOfInt;
  }
  
  @KeepForSdk
  public final LogEventBuilder newEvent(byte[] paramArrayOfByte)
  {
    return new LogEventBuilder(paramArrayOfByte, null);
  }
  
  public class LogEventBuilder
  {
    private int zza = ClearcutLogger.zza(ClearcutLogger.this);
    private String zzb = ClearcutLogger.zzb(ClearcutLogger.this);
    private String zzc;
    private String zzd;
    private int zze;
    private final ClearcutLogger.zzb zzf;
    private ArrayList<Integer> zzg;
    private ArrayList<String> zzh;
    private ArrayList<Integer> zzi;
    private ArrayList<ExperimentTokens> zzj;
    private ArrayList<byte[]> zzk;
    private boolean zzl;
    private final zzfmr zzm;
    private boolean zzn;
    
    private LogEventBuilder(byte[] paramArrayOfByte)
    {
      this(paramArrayOfByte, null);
    }
    
    private LogEventBuilder(byte[] paramArrayOfByte, ClearcutLogger.zzb paramZzb)
    {
      paramZzb = ClearcutLogger.this;
      zzc = null;
      paramZzb = ClearcutLogger.this;
      zzd = null;
      zze = 0;
      zzg = null;
      zzh = null;
      zzi = null;
      zzj = null;
      zzk = null;
      zzl = true;
      zzm = new zzfmr();
      zzn = false;
      zzc = null;
      zzd = null;
      zzm.zza = ClearcutLogger.zzc(ClearcutLogger.this).zza();
      zzm.zzb = ClearcutLogger.zzc(ClearcutLogger.this).zzb();
      paramZzb = zzm;
      ClearcutLogger.zzd(ClearcutLogger.this);
      long l = zzm.zza;
      zzd = (TimeZone.getDefault().getOffset(l) / 1000);
      if (paramArrayOfByte != null) {
        zzm.zzc = paramArrayOfByte;
      }
      zzf = null;
    }
    
    @KeepForSdk
    public void log()
    {
      if (zzn) {
        throw new IllegalStateException("do not reuse LogEventBuilder");
      }
      zzn = true;
      zze localZze = new zze(new zzbfv(ClearcutLogger.zzf(ClearcutLogger.this), ClearcutLogger.zzg(ClearcutLogger.this), zza, zzb, zzc, zzd, ClearcutLogger.zze(ClearcutLogger.this), 0), zzm, null, null, ClearcutLogger.zza(null), null, ClearcutLogger.zza(null), null, null, zzl);
      zzbfv localZzbfv = zza;
      if (ClearcutLogger.zzh(ClearcutLogger.this).zza(zzb, zza))
      {
        ClearcutLogger.zzi(ClearcutLogger.this).zza(localZze);
        return;
      }
      PendingResults.zza(Status.zza, null);
    }
  }
  
  public static abstract interface zza
  {
    public abstract boolean zza(String paramString, int paramInt);
  }
  
  public static abstract interface zzb
  {
    public abstract byte[] zza();
  }
  
  public static final class zzc
  {
    public zzc() {}
  }
}
