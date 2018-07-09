package com.facebook;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import android.util.Log;
import com.facebook.internal.ImageRequest;
import com.facebook.internal.Utility;
import com.facebook.internal.Utility.GraphMeRequestWithCacheCallback;
import com.facebook.internal.Validate;
import org.json.JSONException;
import org.json.JSONObject;

public final class Profile
  implements Parcelable
{
  public static final Parcelable.Creator<Profile> CREATOR = new Parcelable.Creator()
  {
    public Profile createFromParcel(Parcel paramAnonymousParcel)
    {
      return new Profile(paramAnonymousParcel, null);
    }
    
    public Profile[] newArray(int paramAnonymousInt)
    {
      return new Profile[paramAnonymousInt];
    }
  };
  private static final String FIRST_NAME_KEY = "first_name";
  private static final String ID_KEY = "id";
  private static final String LAST_NAME_KEY = "last_name";
  private static final String LINK_URI_KEY = "link_uri";
  private static final String MIDDLE_NAME_KEY = "middle_name";
  private static final String NAME_KEY = "name";
  private static final String TAG = "Profile";
  @Nullable
  private final String firstName;
  @Nullable
  private final String id;
  @Nullable
  private final String lastName;
  @Nullable
  private final Uri linkUri;
  @Nullable
  private final String middleName;
  @Nullable
  private final String name;
  
  private Profile(Parcel paramParcel)
  {
    id = paramParcel.readString();
    firstName = paramParcel.readString();
    middleName = paramParcel.readString();
    lastName = paramParcel.readString();
    name = paramParcel.readString();
    paramParcel = paramParcel.readString();
    if (paramParcel == null) {
      paramParcel = null;
    } else {
      paramParcel = Uri.parse(paramParcel);
    }
    linkUri = paramParcel;
  }
  
  public Profile(String paramString1, @Nullable String paramString2, @Nullable String paramString3, @Nullable String paramString4, @Nullable String paramString5, @Nullable Uri paramUri)
  {
    Validate.notNullOrEmpty(paramString1, "id");
    id = paramString1;
    firstName = paramString2;
    middleName = paramString3;
    lastName = paramString4;
    name = paramString5;
    linkUri = paramUri;
  }
  
  Profile(JSONObject paramJSONObject)
  {
    Object localObject = null;
    id = paramJSONObject.optString("id", null);
    firstName = paramJSONObject.optString("first_name", null);
    middleName = paramJSONObject.optString("middle_name", null);
    lastName = paramJSONObject.optString("last_name", null);
    name = paramJSONObject.optString("name", null);
    paramJSONObject = paramJSONObject.optString("link_uri", null);
    if (paramJSONObject == null) {
      paramJSONObject = localObject;
    } else {
      paramJSONObject = Uri.parse(paramJSONObject);
    }
    linkUri = paramJSONObject;
  }
  
  public static void fetchProfileForCurrentAccessToken()
  {
    AccessToken localAccessToken = AccessToken.getCurrentAccessToken();
    if (!AccessToken.isCurrentAccessTokenActive())
    {
      setCurrentProfile(null);
      return;
    }
    Utility.getGraphMeRequestWithCacheAsync(localAccessToken.getToken(), new Utility.GraphMeRequestWithCacheCallback()
    {
      public void onFailure(FacebookException paramAnonymousFacebookException)
      {
        String str = Profile.TAG;
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("Got unexpected exception: ");
        localStringBuilder.append(paramAnonymousFacebookException);
        Log.e(str, localStringBuilder.toString());
      }
      
      public void onSuccess(JSONObject paramAnonymousJSONObject)
      {
        String str1 = paramAnonymousJSONObject.optString("id");
        if (str1 == null) {
          return;
        }
        String str6 = paramAnonymousJSONObject.optString("link");
        String str2 = paramAnonymousJSONObject.optString("first_name");
        String str3 = paramAnonymousJSONObject.optString("middle_name");
        String str4 = paramAnonymousJSONObject.optString("last_name");
        String str5 = paramAnonymousJSONObject.optString("name");
        if (str6 != null) {}
        for (paramAnonymousJSONObject = Uri.parse(str6);; paramAnonymousJSONObject = null) {
          break;
        }
        Profile.setCurrentProfile(new Profile(str1, str2, str3, str4, str5, paramAnonymousJSONObject));
      }
    });
  }
  
  public static Profile getCurrentProfile()
  {
    return ProfileManager.getInstance().getCurrentProfile();
  }
  
  public static void setCurrentProfile(@Nullable Profile paramProfile)
  {
    ProfileManager.getInstance().setCurrentProfile(paramProfile);
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
    if (!(paramObject instanceof Profile)) {
      return false;
    }
    paramObject = (Profile)paramObject;
    if ((id.equals(id)) && (firstName == null)) {
      if (firstName == null) {
        return true;
      }
    }
    do
    {
      do
      {
        do
        {
          do
          {
            return false;
            if ((!firstName.equals(firstName)) || (middleName != null)) {
              break;
            }
          } while (middleName != null);
          return true;
          if ((!middleName.equals(middleName)) || (lastName != null)) {
            break;
          }
        } while (lastName != null);
        return true;
        if ((!lastName.equals(lastName)) || (name != null)) {
          break;
        }
      } while (name != null);
      return true;
      if ((!name.equals(name)) || (linkUri != null)) {
        break;
      }
    } while (linkUri != null);
    return true;
    return linkUri.equals(linkUri);
  }
  
  public String getFirstName()
  {
    return firstName;
  }
  
  public String getId()
  {
    return id;
  }
  
  public String getLastName()
  {
    return lastName;
  }
  
  public Uri getLinkUri()
  {
    return linkUri;
  }
  
  public String getMiddleName()
  {
    return middleName;
  }
  
  public String getName()
  {
    return name;
  }
  
  public Uri getProfilePictureUri(int paramInt1, int paramInt2)
  {
    return ImageRequest.getProfilePictureUri(id, paramInt1, paramInt2);
  }
  
  public int hashCode()
  {
    int j = 527 + id.hashCode();
    int i = j;
    if (firstName != null) {
      i = j * 31 + firstName.hashCode();
    }
    j = i;
    if (middleName != null) {
      j = i * 31 + middleName.hashCode();
    }
    i = j;
    if (lastName != null) {
      i = j * 31 + lastName.hashCode();
    }
    j = i;
    if (name != null) {
      j = i * 31 + name.hashCode();
    }
    i = j;
    if (linkUri != null) {
      i = j * 31 + linkUri.hashCode();
    }
    return i;
  }
  
  JSONObject toJSONObject()
  {
    JSONObject localJSONObject2 = new JSONObject();
    try
    {
      localJSONObject2.put("id", id);
      localJSONObject2.put("first_name", firstName);
      localJSONObject2.put("middle_name", middleName);
      localJSONObject2.put("last_name", lastName);
      localJSONObject2.put("name", name);
      localJSONObject1 = localJSONObject2;
      if (linkUri == null) {
        break label90;
      }
      localJSONObject2.put("link_uri", linkUri.toString());
      return localJSONObject2;
    }
    catch (JSONException localJSONException)
    {
      JSONObject localJSONObject1;
      label90:
      for (;;) {}
    }
    localJSONObject1 = null;
    return localJSONObject1;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeString(id);
    paramParcel.writeString(firstName);
    paramParcel.writeString(middleName);
    paramParcel.writeString(lastName);
    paramParcel.writeString(name);
    String str;
    if (linkUri == null) {
      str = null;
    } else {
      str = linkUri.toString();
    }
    paramParcel.writeString(str);
  }
}
