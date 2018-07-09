package com.facebook;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.facebook.internal.Utility;
import com.facebook.internal.Utility.GraphMeRequestWithCacheCallback;
import com.facebook.internal.Validate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class AccessToken
  implements Parcelable
{
  public static final String ACCESS_TOKEN_KEY = "access_token";
  private static final String APPLICATION_ID_KEY = "application_id";
  public static final Parcelable.Creator<AccessToken> CREATOR = new Parcelable.Creator()
  {
    public AccessToken createFromParcel(Parcel paramAnonymousParcel)
    {
      return new AccessToken(paramAnonymousParcel);
    }
    
    public AccessToken[] newArray(int paramAnonymousInt)
    {
      return new AccessToken[paramAnonymousInt];
    }
  };
  private static final int CURRENT_JSON_FORMAT = 1;
  private static final String DECLINED_PERMISSIONS_KEY = "declined_permissions";
  private static final AccessTokenSource DEFAULT_ACCESS_TOKEN_SOURCE;
  private static final Date DEFAULT_EXPIRATION_TIME;
  private static final Date DEFAULT_LAST_REFRESH_TIME;
  private static final String EXPIRES_AT_KEY = "expires_at";
  public static final String EXPIRES_IN_KEY = "expires_in";
  private static final String LAST_REFRESH_KEY = "last_refresh";
  private static final Date MAX_DATE = new Date(Long.MAX_VALUE);
  private static final String PERMISSIONS_KEY = "permissions";
  private static final String SOURCE_KEY = "source";
  private static final String TOKEN_KEY = "token";
  public static final String USER_ID_KEY = "user_id";
  private static final String VERSION_KEY = "version";
  private final String applicationId;
  private final Set<String> declinedPermissions;
  private final Date expires;
  private final Date lastRefresh;
  private final Set<String> permissions;
  private final AccessTokenSource source;
  private final String token;
  private final String userId;
  
  static
  {
    DEFAULT_EXPIRATION_TIME = MAX_DATE;
    DEFAULT_LAST_REFRESH_TIME = new Date();
    DEFAULT_ACCESS_TOKEN_SOURCE = AccessTokenSource.FACEBOOK_APPLICATION_WEB;
  }
  
  AccessToken(Parcel paramParcel)
  {
    expires = new Date(paramParcel.readLong());
    ArrayList localArrayList = new ArrayList();
    paramParcel.readStringList(localArrayList);
    permissions = Collections.unmodifiableSet(new HashSet(localArrayList));
    localArrayList.clear();
    paramParcel.readStringList(localArrayList);
    declinedPermissions = Collections.unmodifiableSet(new HashSet(localArrayList));
    token = paramParcel.readString();
    source = AccessTokenSource.valueOf(paramParcel.readString());
    lastRefresh = new Date(paramParcel.readLong());
    applicationId = paramParcel.readString();
    userId = paramParcel.readString();
  }
  
  public AccessToken(String paramString1, String paramString2, String paramString3, @Nullable Collection<String> paramCollection1, @Nullable Collection<String> paramCollection2, @Nullable AccessTokenSource paramAccessTokenSource, @Nullable Date paramDate1, @Nullable Date paramDate2)
  {
    Validate.notNullOrEmpty(paramString1, "accessToken");
    Validate.notNullOrEmpty(paramString2, "applicationId");
    Validate.notNullOrEmpty(paramString3, "userId");
    if (paramDate1 == null) {
      paramDate1 = DEFAULT_EXPIRATION_TIME;
    }
    expires = paramDate1;
    if (paramCollection1 != null) {
      paramCollection1 = new HashSet(paramCollection1);
    } else {
      paramCollection1 = new HashSet();
    }
    permissions = Collections.unmodifiableSet(paramCollection1);
    if (paramCollection2 != null) {
      paramCollection1 = new HashSet(paramCollection2);
    } else {
      paramCollection1 = new HashSet();
    }
    declinedPermissions = Collections.unmodifiableSet(paramCollection1);
    token = paramString1;
    if (paramAccessTokenSource == null) {
      paramAccessTokenSource = DEFAULT_ACCESS_TOKEN_SOURCE;
    }
    source = paramAccessTokenSource;
    if (paramDate2 == null) {
      paramDate2 = DEFAULT_LAST_REFRESH_TIME;
    }
    lastRefresh = paramDate2;
    applicationId = paramString2;
    userId = paramString3;
  }
  
  private void appendPermissions(StringBuilder paramStringBuilder)
  {
    paramStringBuilder.append(" permissions:");
    if (permissions == null)
    {
      paramStringBuilder.append("null");
      return;
    }
    paramStringBuilder.append("[");
    paramStringBuilder.append(TextUtils.join(", ", permissions));
    paramStringBuilder.append("]");
  }
  
  static AccessToken createExpired(AccessToken paramAccessToken)
  {
    return new AccessToken(token, applicationId, paramAccessToken.getUserId(), paramAccessToken.getPermissions(), paramAccessToken.getDeclinedPermissions(), source, new Date(), new Date());
  }
  
  private static AccessToken createFromBundle(List<String> paramList, Bundle paramBundle, AccessTokenSource paramAccessTokenSource, Date paramDate, String paramString)
  {
    String str = paramBundle.getString("access_token");
    paramDate = Utility.getBundleLongAsDate(paramBundle, "expires_in", paramDate);
    paramBundle = paramBundle.getString("user_id");
    if ((!Utility.isNullOrEmpty(str)) && (paramDate != null)) {
      return new AccessToken(str, paramString, paramBundle, paramList, null, paramAccessTokenSource, paramDate, new Date());
    }
    return null;
  }
  
  static AccessToken createFromJSONObject(JSONObject paramJSONObject)
    throws JSONException
  {
    if (paramJSONObject.getInt("version") > 1) {
      throw new FacebookException("Unknown AccessToken serialization format.");
    }
    String str = paramJSONObject.getString("token");
    Date localDate1 = new Date(paramJSONObject.getLong("expires_at"));
    JSONArray localJSONArray1 = paramJSONObject.getJSONArray("permissions");
    JSONArray localJSONArray2 = paramJSONObject.getJSONArray("declined_permissions");
    Date localDate2 = new Date(paramJSONObject.getLong("last_refresh"));
    AccessTokenSource localAccessTokenSource = AccessTokenSource.valueOf(paramJSONObject.getString("source"));
    return new AccessToken(str, paramJSONObject.getString("application_id"), paramJSONObject.getString("user_id"), Utility.jsonArrayToStringList(localJSONArray1), Utility.jsonArrayToStringList(localJSONArray2), localAccessTokenSource, localDate1, localDate2);
  }
  
  static AccessToken createFromLegacyCache(Bundle paramBundle)
  {
    List localList1 = getPermissionsFromBundle(paramBundle, "com.facebook.TokenCachingStrategy.Permissions");
    List localList2 = getPermissionsFromBundle(paramBundle, "com.facebook.TokenCachingStrategy.DeclinedPermissions");
    String str2 = LegacyTokenHelper.getApplicationId(paramBundle);
    String str1 = str2;
    if (Utility.isNullOrEmpty(str2)) {
      str1 = FacebookSdk.getApplicationId();
    }
    str2 = LegacyTokenHelper.getToken(paramBundle);
    Object localObject = Utility.awaitGetGraphMeRequestWithCache(str2);
    try
    {
      localObject = ((JSONObject)localObject).getString("id");
      return new AccessToken(str2, str1, (String)localObject, localList1, localList2, LegacyTokenHelper.getSource(paramBundle), LegacyTokenHelper.getDate(paramBundle, "com.facebook.TokenCachingStrategy.ExpirationDate"), LegacyTokenHelper.getDate(paramBundle, "com.facebook.TokenCachingStrategy.LastRefreshDate"));
    }
    catch (JSONException paramBundle)
    {
      for (;;) {}
    }
    return null;
  }
  
  public static void createFromNativeLinkingIntent(Intent paramIntent, final String paramString, final AccessTokenCreationCallback paramAccessTokenCreationCallback)
  {
    Validate.notNull(paramIntent, "intent");
    if (paramIntent.getExtras() == null)
    {
      paramAccessTokenCreationCallback.onError(new FacebookException("No extras found on intent"));
      return;
    }
    paramIntent = new Bundle(paramIntent.getExtras());
    String str1 = paramIntent.getString("access_token");
    if ((str1 != null) && (!str1.isEmpty()))
    {
      String str2 = paramIntent.getString("user_id");
      if ((str2 != null) && (!str2.isEmpty()))
      {
        paramAccessTokenCreationCallback.onSuccess(createFromBundle(null, paramIntent, AccessTokenSource.FACEBOOK_APPLICATION_WEB, new Date(), paramString));
        return;
      }
      Utility.getGraphMeRequestWithCacheAsync(str1, new Utility.GraphMeRequestWithCacheCallback()
      {
        public void onFailure(FacebookException paramAnonymousFacebookException)
        {
          paramAccessTokenCreationCallback.onError(paramAnonymousFacebookException);
        }
        
        public void onSuccess(JSONObject paramAnonymousJSONObject)
        {
          try
          {
            paramAnonymousJSONObject = paramAnonymousJSONObject.getString("id");
            val$extras.putString("user_id", paramAnonymousJSONObject);
            paramAccessTokenCreationCallback.onSuccess(AccessToken.createFromBundle(null, val$extras, AccessTokenSource.FACEBOOK_APPLICATION_WEB, new Date(), paramString));
            return;
          }
          catch (JSONException paramAnonymousJSONObject)
          {
            for (;;) {}
          }
          paramAccessTokenCreationCallback.onError(new FacebookException("Unable to generate access token due to missing user id"));
        }
      });
      return;
    }
    paramAccessTokenCreationCallback.onError(new FacebookException("No access token found on intent"));
  }
  
  @SuppressLint({"FieldGetter"})
  static AccessToken createFromRefresh(AccessToken paramAccessToken, Bundle paramBundle)
  {
    if ((source != AccessTokenSource.FACEBOOK_APPLICATION_WEB) && (source != AccessTokenSource.FACEBOOK_APPLICATION_NATIVE) && (source != AccessTokenSource.FACEBOOK_APPLICATION_SERVICE))
    {
      paramBundle = new StringBuilder();
      paramBundle.append("Invalid token source: ");
      paramBundle.append(source);
      throw new FacebookException(paramBundle.toString());
    }
    Date localDate = Utility.getBundleLongAsDate(paramBundle, "expires_in", new Date(0L));
    paramBundle = paramBundle.getString("access_token");
    if (Utility.isNullOrEmpty(paramBundle)) {
      return null;
    }
    return new AccessToken(paramBundle, applicationId, paramAccessToken.getUserId(), paramAccessToken.getPermissions(), paramAccessToken.getDeclinedPermissions(), source, localDate, new Date());
  }
  
  static void expireCurrentAccessToken()
  {
    AccessToken localAccessToken = AccessTokenManager.getInstance().getCurrentAccessToken();
    if (localAccessToken != null) {
      setCurrentAccessToken(createExpired(localAccessToken));
    }
  }
  
  public static AccessToken getCurrentAccessToken()
  {
    return AccessTokenManager.getInstance().getCurrentAccessToken();
  }
  
  static List<String> getPermissionsFromBundle(Bundle paramBundle, String paramString)
  {
    paramBundle = paramBundle.getStringArrayList(paramString);
    if (paramBundle == null) {
      return Collections.emptyList();
    }
    return Collections.unmodifiableList(new ArrayList(paramBundle));
  }
  
  public static boolean isCurrentAccessTokenActive()
  {
    AccessToken localAccessToken = AccessTokenManager.getInstance().getCurrentAccessToken();
    return (localAccessToken != null) && (!localAccessToken.isExpired());
  }
  
  public static void refreshCurrentAccessTokenAsync()
  {
    AccessTokenManager.getInstance().refreshCurrentAccessToken(null);
  }
  
  public static void refreshCurrentAccessTokenAsync(AccessTokenRefreshCallback paramAccessTokenRefreshCallback)
  {
    AccessTokenManager.getInstance().refreshCurrentAccessToken(paramAccessTokenRefreshCallback);
  }
  
  public static void setCurrentAccessToken(AccessToken paramAccessToken)
  {
    AccessTokenManager.getInstance().setCurrentAccessToken(paramAccessToken);
  }
  
  private String tokenToString()
  {
    if (token == null) {
      return "null";
    }
    if (FacebookSdk.isLoggingBehaviorEnabled(LoggingBehavior.INCLUDE_ACCESS_TOKENS)) {
      return token;
    }
    return "ACCESS_TOKEN_REMOVED";
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {
      return true;
    }
    if (!(paramObject instanceof AccessToken)) {
      return false;
    }
    paramObject = (AccessToken)paramObject;
    return (expires.equals(expires)) && (permissions.equals(permissions)) && (declinedPermissions.equals(declinedPermissions)) && (token.equals(token)) && (source == source) && (lastRefresh.equals(lastRefresh)) && (applicationId == null ? applicationId == null : applicationId.equals(applicationId)) && (userId.equals(userId));
  }
  
  public String getApplicationId()
  {
    return applicationId;
  }
  
  public Set<String> getDeclinedPermissions()
  {
    return declinedPermissions;
  }
  
  public Date getExpires()
  {
    return expires;
  }
  
  public Date getLastRefresh()
  {
    return lastRefresh;
  }
  
  public Set<String> getPermissions()
  {
    return permissions;
  }
  
  public AccessTokenSource getSource()
  {
    return source;
  }
  
  public String getToken()
  {
    return token;
  }
  
  public String getUserId()
  {
    return userId;
  }
  
  public int hashCode()
  {
    int j = expires.hashCode();
    int k = permissions.hashCode();
    int m = declinedPermissions.hashCode();
    int n = token.hashCode();
    int i1 = source.hashCode();
    int i2 = lastRefresh.hashCode();
    int i;
    if (applicationId == null) {
      i = 0;
    } else {
      i = applicationId.hashCode();
    }
    return (((((((527 + j) * 31 + k) * 31 + m) * 31 + n) * 31 + i1) * 31 + i2) * 31 + i) * 31 + userId.hashCode();
  }
  
  public boolean isExpired()
  {
    return new Date().after(expires);
  }
  
  JSONObject toJSONObject()
    throws JSONException
  {
    JSONObject localJSONObject = new JSONObject();
    localJSONObject.put("version", 1);
    localJSONObject.put("token", token);
    localJSONObject.put("expires_at", expires.getTime());
    localJSONObject.put("permissions", new JSONArray(permissions));
    localJSONObject.put("declined_permissions", new JSONArray(declinedPermissions));
    localJSONObject.put("last_refresh", lastRefresh.getTime());
    localJSONObject.put("source", source.name());
    localJSONObject.put("application_id", applicationId);
    localJSONObject.put("user_id", userId);
    return localJSONObject;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("{AccessToken");
    localStringBuilder.append(" token:");
    localStringBuilder.append(tokenToString());
    appendPermissions(localStringBuilder);
    localStringBuilder.append("}");
    return localStringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeLong(expires.getTime());
    paramParcel.writeStringList(new ArrayList(permissions));
    paramParcel.writeStringList(new ArrayList(declinedPermissions));
    paramParcel.writeString(token);
    paramParcel.writeString(source.name());
    paramParcel.writeLong(lastRefresh.getTime());
    paramParcel.writeString(applicationId);
    paramParcel.writeString(userId);
  }
  
  public static abstract interface AccessTokenCreationCallback
  {
    public abstract void onError(FacebookException paramFacebookException);
    
    public abstract void onSuccess(AccessToken paramAccessToken);
  }
  
  public static abstract interface AccessTokenRefreshCallback
  {
    public abstract void OnTokenRefreshFailed(FacebookException paramFacebookException);
    
    public abstract void OnTokenRefreshed(AccessToken paramAccessToken);
  }
}
