package com.google.android.gms.auth.api.signin.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.internal.zzbq;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.json.JSONException;

public final class zzaa
{
  private static final Lock zza = new ReentrantLock();
  private static zzaa zzb;
  private final Lock zzc = new ReentrantLock();
  private final SharedPreferences zzd;
  
  private zzaa(Context paramContext)
  {
    zzd = paramContext.getSharedPreferences("com.google.android.gms.signin", 0);
  }
  
  public static zzaa zza(Context paramContext)
  {
    zzbq.zza(paramContext);
    zza.lock();
    try
    {
      if (zzb == null) {
        zzb = new zzaa(paramContext.getApplicationContext());
      }
      paramContext = zzb;
      return paramContext;
    }
    finally
    {
      zza.unlock();
    }
  }
  
  private final GoogleSignInAccount zzb(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return null;
    }
    paramString = zza(zzb("googleSignInAccount", paramString));
    if (paramString != null) {}
    try
    {
      paramString = GoogleSignInAccount.zza(paramString);
      return paramString;
    }
    catch (JSONException paramString) {}
    return null;
    return null;
  }
  
  private static String zzb(String paramString1, String paramString2)
  {
    StringBuilder localStringBuilder = new StringBuilder(1 + String.valueOf(paramString1).length() + String.valueOf(paramString2).length());
    localStringBuilder.append(paramString1);
    localStringBuilder.append(":");
    localStringBuilder.append(paramString2);
    return localStringBuilder.toString();
  }
  
  private final GoogleSignInOptions zzc(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return null;
    }
    paramString = zza(zzb("googleSignInOptions", paramString));
    if (paramString != null) {}
    try
    {
      paramString = GoogleSignInOptions.zza(paramString);
      return paramString;
    }
    catch (JSONException paramString) {}
    return null;
    return null;
  }
  
  private final void zzd(String paramString)
  {
    zzc.lock();
    try
    {
      zzd.edit().remove(paramString).apply();
      return;
    }
    finally
    {
      zzc.unlock();
    }
  }
  
  public final GoogleSignInAccount zza()
  {
    return zzb(zza("defaultGoogleSignInAccount"));
  }
  
  protected final String zza(String paramString)
  {
    zzc.lock();
    try
    {
      paramString = zzd.getString(paramString, null);
      return paramString;
    }
    finally
    {
      zzc.unlock();
    }
  }
  
  final void zza(GoogleSignInAccount paramGoogleSignInAccount, GoogleSignInOptions paramGoogleSignInOptions)
  {
    zzbq.zza(paramGoogleSignInAccount);
    zzbq.zza(paramGoogleSignInOptions);
    String str = paramGoogleSignInAccount.zzc();
    zza(zzb("googleSignInAccount", str), paramGoogleSignInAccount.zze());
    zza(zzb("googleSignInOptions", str), paramGoogleSignInOptions.zzf());
  }
  
  protected final void zza(String paramString1, String paramString2)
  {
    zzc.lock();
    try
    {
      zzd.edit().putString(paramString1, paramString2).apply();
      return;
    }
    finally
    {
      zzc.unlock();
    }
  }
  
  public final GoogleSignInOptions zzb()
  {
    return zzc(zza("defaultGoogleSignInAccount"));
  }
  
  public final void zzc()
  {
    String str = zza("defaultGoogleSignInAccount");
    zzd("defaultGoogleSignInAccount");
    if (!TextUtils.isEmpty(str))
    {
      zzd(zzb("googleSignInAccount", str));
      zzd(zzb("googleSignInOptions", str));
    }
  }
  
  public final void zzd()
  {
    zzc.lock();
    try
    {
      zzd.edit().clear().apply();
      return;
    }
    finally
    {
      zzc.unlock();
    }
  }
}
