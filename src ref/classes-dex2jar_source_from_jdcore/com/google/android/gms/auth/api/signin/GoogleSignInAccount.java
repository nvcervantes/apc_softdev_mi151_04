package com.google.android.gms.auth.api.signin;

import android.accounts.Account;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.util.zze;
import com.google.android.gms.common.util.zzi;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GoogleSignInAccount
  extends zzbgl
  implements ReflectedParcelable
{
  public static final Parcelable.Creator<GoogleSignInAccount> CREATOR = new zzb();
  @Hide
  private static zze zza = zzi.zzd();
  @Hide
  private int zzb;
  private String zzc;
  private String zzd;
  private String zze;
  private String zzf;
  private Uri zzg;
  private String zzh;
  private long zzi;
  private String zzj;
  private List<Scope> zzk;
  private String zzl;
  private String zzm;
  private Set<Scope> zzn = new HashSet();
  
  GoogleSignInAccount(int paramInt, String paramString1, String paramString2, String paramString3, String paramString4, Uri paramUri, String paramString5, long paramLong, String paramString6, List<Scope> paramList, String paramString7, String paramString8)
  {
    zzb = paramInt;
    zzc = paramString1;
    zzd = paramString2;
    zze = paramString3;
    zzf = paramString4;
    zzg = paramUri;
    zzh = paramString5;
    zzi = paramLong;
    zzj = paramString6;
    zzk = paramList;
    zzl = paramString7;
    zzm = paramString8;
  }
  
  @Hide
  public static GoogleSignInAccount zza()
  {
    Account localAccount = new Account("<<default account>>", "com.google");
    HashSet localHashSet = new HashSet();
    return zza(null, null, name, null, null, null, null, Long.valueOf(0L), name, localHashSet);
  }
  
  @Nullable
  @Hide
  public static GoogleSignInAccount zza(@Nullable String paramString)
    throws JSONException
  {
    if (TextUtils.isEmpty(paramString)) {
      return null;
    }
    JSONObject localJSONObject = new JSONObject(paramString);
    paramString = localJSONObject.optString("photoUrl", null);
    if (!TextUtils.isEmpty(paramString)) {
      paramString = Uri.parse(paramString);
    } else {
      paramString = null;
    }
    long l = Long.parseLong(localJSONObject.getString("expirationTime"));
    HashSet localHashSet = new HashSet();
    JSONArray localJSONArray = localJSONObject.getJSONArray("grantedScopes");
    int j = localJSONArray.length();
    int i = 0;
    while (i < j)
    {
      localHashSet.add(new Scope(localJSONArray.getString(i)));
      i += 1;
    }
    paramString = zza(localJSONObject.optString("id"), localJSONObject.optString("tokenId", null), localJSONObject.optString("email", null), localJSONObject.optString("displayName", null), localJSONObject.optString("givenName", null), localJSONObject.optString("familyName", null), paramString, Long.valueOf(l), localJSONObject.getString("obfuscatedIdentifier"), localHashSet);
    zzh = localJSONObject.optString("serverAuthCode", null);
    return paramString;
  }
  
  @Hide
  private static GoogleSignInAccount zza(@Nullable String paramString1, @Nullable String paramString2, @Nullable String paramString3, @Nullable String paramString4, @Nullable String paramString5, @Nullable String paramString6, @Nullable Uri paramUri, @Nullable Long paramLong, @NonNull String paramString7, @NonNull Set<Scope> paramSet)
  {
    if (paramLong == null) {
      paramLong = Long.valueOf(zza.zza() / 1000L);
    }
    return new GoogleSignInAccount(3, paramString1, paramString2, paramString3, paramString4, paramUri, null, paramLong.longValue(), zzbq.zza(paramString7), new ArrayList((Collection)zzbq.zza(paramSet)), paramString5, paramString6);
  }
  
  private final JSONObject zzf()
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      if (getId() != null) {
        localJSONObject.put("id", getId());
      }
      if (getIdToken() != null) {
        localJSONObject.put("tokenId", getIdToken());
      }
      if (getEmail() != null) {
        localJSONObject.put("email", getEmail());
      }
      if (getDisplayName() != null) {
        localJSONObject.put("displayName", getDisplayName());
      }
      if (getGivenName() != null) {
        localJSONObject.put("givenName", getGivenName());
      }
      if (getFamilyName() != null) {
        localJSONObject.put("familyName", getFamilyName());
      }
      if (getPhotoUrl() != null) {
        localJSONObject.put("photoUrl", getPhotoUrl().toString());
      }
      if (getServerAuthCode() != null) {
        localJSONObject.put("serverAuthCode", getServerAuthCode());
      }
      localJSONObject.put("expirationTime", zzi);
      localJSONObject.put("obfuscatedIdentifier", zzj);
      JSONArray localJSONArray = new JSONArray();
      Scope[] arrayOfScope = (Scope[])zzk.toArray(new Scope[zzk.size()]);
      Arrays.sort(arrayOfScope, zza.zza);
      int j = arrayOfScope.length;
      int i = 0;
      while (i < j)
      {
        localJSONArray.put(arrayOfScope[i].zza());
        i += 1;
      }
      localJSONObject.put("grantedScopes", localJSONArray);
      return localJSONObject;
    }
    catch (JSONException localJSONException)
    {
      throw new RuntimeException(localJSONException);
    }
  }
  
  public boolean equals(Object paramObject)
  {
    if (paramObject == this) {
      return true;
    }
    if (!(paramObject instanceof GoogleSignInAccount)) {
      return false;
    }
    paramObject = (GoogleSignInAccount)paramObject;
    return (zzj.equals(zzj)) && (paramObject.zzd().equals(zzd()));
  }
  
  @Nullable
  public Account getAccount()
  {
    if (zze == null) {
      return null;
    }
    return new Account(zze, "com.google");
  }
  
  @Nullable
  public String getDisplayName()
  {
    return zzf;
  }
  
  @Nullable
  public String getEmail()
  {
    return zze;
  }
  
  @Nullable
  public String getFamilyName()
  {
    return zzm;
  }
  
  @Nullable
  public String getGivenName()
  {
    return zzl;
  }
  
  @NonNull
  public Set<Scope> getGrantedScopes()
  {
    return new HashSet(zzk);
  }
  
  @Nullable
  public String getId()
  {
    return zzc;
  }
  
  @Nullable
  public String getIdToken()
  {
    return zzd;
  }
  
  @Nullable
  public Uri getPhotoUrl()
  {
    return zzg;
  }
  
  @Nullable
  public String getServerAuthCode()
  {
    return zzh;
  }
  
  public int hashCode()
  {
    return (527 + zzj.hashCode()) * 31 + zzd().hashCode();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    int i = zzbgo.zza(paramParcel);
    zzbgo.zza(paramParcel, 1, zzb);
    zzbgo.zza(paramParcel, 2, getId(), false);
    zzbgo.zza(paramParcel, 3, getIdToken(), false);
    zzbgo.zza(paramParcel, 4, getEmail(), false);
    zzbgo.zza(paramParcel, 5, getDisplayName(), false);
    zzbgo.zza(paramParcel, 6, getPhotoUrl(), paramInt, false);
    zzbgo.zza(paramParcel, 7, getServerAuthCode(), false);
    zzbgo.zza(paramParcel, 8, zzi);
    zzbgo.zza(paramParcel, 9, zzj, false);
    zzbgo.zzc(paramParcel, 10, zzk, false);
    zzbgo.zza(paramParcel, 11, getGivenName(), false);
    zzbgo.zza(paramParcel, 12, getFamilyName(), false);
    zzbgo.zza(paramParcel, i);
  }
  
  @Hide
  public final GoogleSignInAccount zza(Scope... paramVarArgs)
  {
    if (paramVarArgs != null) {
      Collections.addAll(zzn, paramVarArgs);
    }
    return this;
  }
  
  @Hide
  public final boolean zzb()
  {
    return zza.zza() / 1000L >= zzi - 300L;
  }
  
  @NonNull
  @Hide
  public final String zzc()
  {
    return zzj;
  }
  
  @NonNull
  @Hide
  public final Set<Scope> zzd()
  {
    HashSet localHashSet = new HashSet(zzk);
    localHashSet.addAll(zzn);
    return localHashSet;
  }
  
  @Hide
  public final String zze()
  {
    JSONObject localJSONObject = zzf();
    localJSONObject.remove("serverAuthCode");
    return localJSONObject.toString();
  }
}
