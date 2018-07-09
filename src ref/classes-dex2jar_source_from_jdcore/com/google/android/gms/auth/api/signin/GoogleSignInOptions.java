package com.google.android.gms.auth.api.signin;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.auth.api.signin.internal.zzo;
import com.google.android.gms.auth.api.signin.internal.zzq;
import com.google.android.gms.common.api.Api.ApiOptions.Optional;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GoogleSignInOptions
  extends zzbgl
  implements Api.ApiOptions.Optional, ReflectedParcelable
{
  public static final Parcelable.Creator<GoogleSignInOptions> CREATOR = new zze();
  public static final GoogleSignInOptions DEFAULT_GAMES_SIGN_IN;
  public static final GoogleSignInOptions DEFAULT_SIGN_IN;
  @Hide
  public static final Scope zza = new Scope("profile");
  @Hide
  public static final Scope zzb = new Scope("email");
  @Hide
  public static final Scope zzc = new Scope("openid");
  @Hide
  public static final Scope zzd = new Scope("https://www.googleapis.com/auth/games_lite");
  @Hide
  public static final Scope zze = new Scope("https://www.googleapis.com/auth/games");
  private static Comparator<Scope> zzp = new zzd();
  private int zzf;
  private final ArrayList<Scope> zzg;
  private Account zzh;
  private boolean zzi;
  private final boolean zzj;
  private final boolean zzk;
  private String zzl;
  private String zzm;
  private ArrayList<zzo> zzn;
  private Map<Integer, zzo> zzo;
  
  static
  {
    DEFAULT_SIGN_IN = new Builder().requestId().requestProfile().build();
    DEFAULT_GAMES_SIGN_IN = new Builder().requestScopes(zzd, new Scope[0]).build();
  }
  
  GoogleSignInOptions(int paramInt, ArrayList<Scope> paramArrayList, Account paramAccount, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, String paramString1, String paramString2, ArrayList<zzo> paramArrayList1)
  {
    this(paramInt, paramArrayList, paramAccount, paramBoolean1, paramBoolean2, paramBoolean3, paramString1, paramString2, zzb(paramArrayList1));
  }
  
  private GoogleSignInOptions(int paramInt, ArrayList<Scope> paramArrayList, Account paramAccount, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, String paramString1, String paramString2, Map<Integer, zzo> paramMap)
  {
    zzf = paramInt;
    zzg = paramArrayList;
    zzh = paramAccount;
    zzi = paramBoolean1;
    zzj = paramBoolean2;
    zzk = paramBoolean3;
    zzl = paramString1;
    zzm = paramString2;
    zzn = new ArrayList(paramMap.values());
    zzo = paramMap;
  }
  
  @Nullable
  @Hide
  public static GoogleSignInOptions zza(@Nullable String paramString)
    throws JSONException
  {
    if (TextUtils.isEmpty(paramString)) {
      return null;
    }
    JSONObject localJSONObject = new JSONObject(paramString);
    HashSet localHashSet = new HashSet();
    paramString = localJSONObject.getJSONArray("scopes");
    int j = paramString.length();
    int i = 0;
    while (i < j)
    {
      localHashSet.add(new Scope(paramString.getString(i)));
      i += 1;
    }
    paramString = localJSONObject.optString("accountName", null);
    if (!TextUtils.isEmpty(paramString)) {
      paramString = new Account(paramString, "com.google");
    } else {
      paramString = null;
    }
    return new GoogleSignInOptions(3, new ArrayList(localHashSet), paramString, localJSONObject.getBoolean("idTokenRequested"), localJSONObject.getBoolean("serverAuthRequested"), localJSONObject.getBoolean("forceCodeForRefreshToken"), localJSONObject.optString("serverClientId", null), localJSONObject.optString("hostedDomain", null), new HashMap());
  }
  
  private static Map<Integer, zzo> zzb(@Nullable List<zzo> paramList)
  {
    HashMap localHashMap = new HashMap();
    if (paramList == null) {
      return localHashMap;
    }
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      zzo localZzo = (zzo)paramList.next();
      localHashMap.put(Integer.valueOf(localZzo.zza()), localZzo);
    }
    return localHashMap;
  }
  
  private final JSONObject zzg()
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      JSONArray localJSONArray = new JSONArray();
      Collections.sort(zzg, zzp);
      ArrayList localArrayList = (ArrayList)zzg;
      int j = localArrayList.size();
      int i = 0;
      while (i < j)
      {
        Object localObject = localArrayList.get(i);
        i += 1;
        localJSONArray.put(((Scope)localObject).zza());
      }
      localJSONObject.put("scopes", localJSONArray);
      if (zzh != null) {
        localJSONObject.put("accountName", zzh.name);
      }
      localJSONObject.put("idTokenRequested", zzi);
      localJSONObject.put("forceCodeForRefreshToken", zzk);
      localJSONObject.put("serverAuthRequested", zzj);
      if (!TextUtils.isEmpty(zzl)) {
        localJSONObject.put("serverClientId", zzl);
      }
      if (!TextUtils.isEmpty(zzm)) {
        localJSONObject.put("hostedDomain", zzm);
      }
      return localJSONObject;
    }
    catch (JSONException localJSONException)
    {
      throw new RuntimeException(localJSONException);
    }
  }
  
  public boolean equals(Object paramObject)
  {
    if (paramObject == null) {
      return false;
    }
    try
    {
      paramObject = (GoogleSignInOptions)paramObject;
      if (zzn.size() <= 0)
      {
        if (zzn.size() > 0) {
          return false;
        }
        if (zzg.size() == paramObject.zza().size())
        {
          if (!zzg.containsAll(paramObject.zza())) {
            return false;
          }
          if ((zzh == null ? zzh == null : zzh.equals(zzh)) && (TextUtils.isEmpty(zzl) ? TextUtils.isEmpty(zzl) : zzl.equals(zzl)) && (zzk == zzk) && (zzi == zzi))
          {
            boolean bool1 = zzj;
            boolean bool2 = zzj;
            if (bool1 == bool2) {
              return true;
            }
          }
        }
      }
      return false;
    }
    catch (ClassCastException paramObject) {}
    return false;
  }
  
  public Scope[] getScopeArray()
  {
    return (Scope[])zzg.toArray(new Scope[zzg.size()]);
  }
  
  public int hashCode()
  {
    ArrayList localArrayList1 = new ArrayList();
    ArrayList localArrayList2 = (ArrayList)zzg;
    int j = localArrayList2.size();
    int i = 0;
    while (i < j)
    {
      Object localObject = localArrayList2.get(i);
      i += 1;
      localArrayList1.add(((Scope)localObject).zza());
    }
    Collections.sort(localArrayList1);
    return new zzq().zza(localArrayList1).zza(zzh).zza(zzl).zza(zzk).zza(zzi).zza(zzj).zza();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    int i = zzbgo.zza(paramParcel);
    zzbgo.zza(paramParcel, 1, zzf);
    zzbgo.zzc(paramParcel, 2, zza(), false);
    zzbgo.zza(paramParcel, 3, zzh, paramInt, false);
    zzbgo.zza(paramParcel, 4, zzi);
    zzbgo.zza(paramParcel, 5, zzj);
    zzbgo.zza(paramParcel, 6, zzk);
    zzbgo.zza(paramParcel, 7, zzl, false);
    zzbgo.zza(paramParcel, 8, zzm, false);
    zzbgo.zzc(paramParcel, 9, zzn, false);
    zzbgo.zza(paramParcel, i);
  }
  
  @Hide
  public final ArrayList<Scope> zza()
  {
    return new ArrayList(zzg);
  }
  
  @Hide
  public final Account zzb()
  {
    return zzh;
  }
  
  @Hide
  public final boolean zzc()
  {
    return zzi;
  }
  
  @Hide
  public final boolean zzd()
  {
    return zzj;
  }
  
  @Hide
  public final String zze()
  {
    return zzl;
  }
  
  @Hide
  public final String zzf()
  {
    return zzg().toString();
  }
  
  public static final class Builder
  {
    private Set<Scope> zza = new HashSet();
    private boolean zzb;
    private boolean zzc;
    private boolean zzd;
    private String zze;
    private Account zzf;
    private String zzg;
    private Map<Integer, zzo> zzh = new HashMap();
    
    public Builder() {}
    
    public Builder(@NonNull GoogleSignInOptions paramGoogleSignInOptions)
    {
      zzbq.zza(paramGoogleSignInOptions);
      zza = new HashSet(GoogleSignInOptions.zza(paramGoogleSignInOptions));
      zzb = GoogleSignInOptions.zzb(paramGoogleSignInOptions);
      zzc = GoogleSignInOptions.zzc(paramGoogleSignInOptions);
      zzd = GoogleSignInOptions.zzd(paramGoogleSignInOptions);
      zze = GoogleSignInOptions.zze(paramGoogleSignInOptions);
      zzf = GoogleSignInOptions.zzf(paramGoogleSignInOptions);
      zzg = GoogleSignInOptions.zzg(paramGoogleSignInOptions);
      zzh = GoogleSignInOptions.zza(GoogleSignInOptions.zzh(paramGoogleSignInOptions));
    }
    
    private final String zza(String paramString)
    {
      zzbq.zza(paramString);
      boolean bool;
      if ((zze != null) && (!zze.equals(paramString))) {
        bool = false;
      } else {
        bool = true;
      }
      zzbq.zzb(bool, "two different server client ids provided");
      return paramString;
    }
    
    public final Builder addExtension(GoogleSignInOptionsExtension paramGoogleSignInOptionsExtension)
    {
      if (zzh.containsKey(Integer.valueOf(paramGoogleSignInOptionsExtension.getExtensionType()))) {
        throw new IllegalStateException("Only one extension per type may be added");
      }
      if (paramGoogleSignInOptionsExtension.getImpliedScopes() != null) {
        zza.addAll(paramGoogleSignInOptionsExtension.getImpliedScopes());
      }
      zzh.put(Integer.valueOf(paramGoogleSignInOptionsExtension.getExtensionType()), new zzo(paramGoogleSignInOptionsExtension));
      return this;
    }
    
    public final GoogleSignInOptions build()
    {
      if ((zza.contains(GoogleSignInOptions.zze)) && (zza.contains(GoogleSignInOptions.zzd))) {
        zza.remove(GoogleSignInOptions.zzd);
      }
      if ((zzd) && ((zzf == null) || (!zza.isEmpty()))) {
        requestId();
      }
      return new GoogleSignInOptions(3, new ArrayList(zza), zzf, zzd, zzb, zzc, zze, zzg, zzh, null);
    }
    
    public final Builder requestEmail()
    {
      zza.add(GoogleSignInOptions.zzb);
      return this;
    }
    
    public final Builder requestId()
    {
      zza.add(GoogleSignInOptions.zzc);
      return this;
    }
    
    public final Builder requestIdToken(String paramString)
    {
      zzd = true;
      zze = zza(paramString);
      return this;
    }
    
    public final Builder requestProfile()
    {
      zza.add(GoogleSignInOptions.zza);
      return this;
    }
    
    public final Builder requestScopes(Scope paramScope, Scope... paramVarArgs)
    {
      zza.add(paramScope);
      zza.addAll(Arrays.asList(paramVarArgs));
      return this;
    }
    
    public final Builder requestServerAuthCode(String paramString)
    {
      return requestServerAuthCode(paramString, false);
    }
    
    public final Builder requestServerAuthCode(String paramString, boolean paramBoolean)
    {
      zzb = true;
      zze = zza(paramString);
      zzc = paramBoolean;
      return this;
    }
    
    public final Builder setAccountName(String paramString)
    {
      zzf = new Account(zzbq.zza(paramString), "com.google");
      return this;
    }
    
    public final Builder setHostedDomain(String paramString)
    {
      zzg = zzbq.zza(paramString);
      return this;
    }
  }
}
