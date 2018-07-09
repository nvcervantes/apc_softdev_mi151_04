package com.facebook.internal;

import android.content.Context;
import android.net.Uri;
import android.net.Uri.Builder;
import com.facebook.FacebookSdk;
import java.util.Locale;

public class ImageRequest
{
  private static final String HEIGHT_PARAM = "height";
  private static final String MIGRATION_PARAM = "migration_overrides";
  private static final String MIGRATION_VALUE = "{october_2012:true}";
  private static final String PATH = "%s/%s/picture";
  public static final int UNSPECIFIED_DIMENSION = 0;
  private static final String WIDTH_PARAM = "width";
  private boolean allowCachedRedirects;
  private Callback callback;
  private Object callerTag;
  private Context context;
  private Uri imageUri;
  
  private ImageRequest(Builder paramBuilder)
  {
    context = context;
    imageUri = imageUrl;
    callback = callback;
    allowCachedRedirects = allowCachedRedirects;
    if (callerTag == null) {
      paramBuilder = new Object();
    } else {
      paramBuilder = callerTag;
    }
    callerTag = paramBuilder;
  }
  
  public static Uri getProfilePictureUri(String paramString, int paramInt1, int paramInt2)
  {
    Validate.notNullOrEmpty(paramString, "userId");
    paramInt1 = Math.max(paramInt1, 0);
    paramInt2 = Math.max(paramInt2, 0);
    if ((paramInt1 == 0) && (paramInt2 == 0)) {
      throw new IllegalArgumentException("Either width or height must be greater than 0");
    }
    paramString = Uri.parse(ServerProtocol.getGraphUrlBase()).buildUpon().path(String.format(Locale.US, "%s/%s/picture", new Object[] { FacebookSdk.getGraphApiVersion(), paramString }));
    if (paramInt2 != 0) {
      paramString.appendQueryParameter("height", String.valueOf(paramInt2));
    }
    if (paramInt1 != 0) {
      paramString.appendQueryParameter("width", String.valueOf(paramInt1));
    }
    paramString.appendQueryParameter("migration_overrides", "{october_2012:true}");
    return paramString.build();
  }
  
  public Callback getCallback()
  {
    return callback;
  }
  
  public Object getCallerTag()
  {
    return callerTag;
  }
  
  public Context getContext()
  {
    return context;
  }
  
  public Uri getImageUri()
  {
    return imageUri;
  }
  
  public boolean isCachedRedirectAllowed()
  {
    return allowCachedRedirects;
  }
  
  public static class Builder
  {
    private boolean allowCachedRedirects;
    private ImageRequest.Callback callback;
    private Object callerTag;
    private Context context;
    private Uri imageUrl;
    
    public Builder(Context paramContext, Uri paramUri)
    {
      Validate.notNull(paramUri, "imageUri");
      context = paramContext;
      imageUrl = paramUri;
    }
    
    public ImageRequest build()
    {
      return new ImageRequest(this, null);
    }
    
    public Builder setAllowCachedRedirects(boolean paramBoolean)
    {
      allowCachedRedirects = paramBoolean;
      return this;
    }
    
    public Builder setCallback(ImageRequest.Callback paramCallback)
    {
      callback = paramCallback;
      return this;
    }
    
    public Builder setCallerTag(Object paramObject)
    {
      callerTag = paramObject;
      return this;
    }
  }
  
  public static abstract interface Callback
  {
    public abstract void onCompleted(ImageResponse paramImageResponse);
  }
}
