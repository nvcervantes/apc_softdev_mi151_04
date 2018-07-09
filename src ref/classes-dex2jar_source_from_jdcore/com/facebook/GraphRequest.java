package com.facebook;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.location.Location;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.ParcelFileDescriptor.AutoCloseInputStream;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import com.facebook.internal.AttributionIdentifiers;
import com.facebook.internal.InternalSettings;
import com.facebook.internal.Logger;
import com.facebook.internal.ServerProtocol;
import com.facebook.internal.Utility;
import com.facebook.internal.Validate;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GraphRequest
{
  private static final String ACCEPT_LANGUAGE_HEADER = "Accept-Language";
  public static final String ACCESS_TOKEN_PARAM = "access_token";
  private static final String ATTACHED_FILES_PARAM = "attached_files";
  private static final String ATTACHMENT_FILENAME_PREFIX = "file";
  private static final String BATCH_APP_ID_PARAM = "batch_app_id";
  private static final String BATCH_BODY_PARAM = "body";
  private static final String BATCH_ENTRY_DEPENDS_ON_PARAM = "depends_on";
  private static final String BATCH_ENTRY_NAME_PARAM = "name";
  private static final String BATCH_ENTRY_OMIT_RESPONSE_ON_SUCCESS_PARAM = "omit_response_on_success";
  private static final String BATCH_METHOD_PARAM = "method";
  private static final String BATCH_PARAM = "batch";
  private static final String BATCH_RELATIVE_URL_PARAM = "relative_url";
  private static final String CAPTION_PARAM = "caption";
  private static final String CONTENT_ENCODING_HEADER = "Content-Encoding";
  private static final String CONTENT_TYPE_HEADER = "Content-Type";
  private static final String DEBUG_KEY = "__debug__";
  private static final String DEBUG_MESSAGES_KEY = "messages";
  private static final String DEBUG_MESSAGE_KEY = "message";
  private static final String DEBUG_MESSAGE_LINK_KEY = "link";
  private static final String DEBUG_MESSAGE_TYPE_KEY = "type";
  private static final String DEBUG_PARAM = "debug";
  private static final String DEBUG_SEVERITY_INFO = "info";
  private static final String DEBUG_SEVERITY_WARNING = "warning";
  public static final String FIELDS_PARAM = "fields";
  private static final String FORMAT_JSON = "json";
  private static final String FORMAT_PARAM = "format";
  private static final String GRAPH_PATH_FORMAT = "%s/%s";
  private static final String ISO_8601_FORMAT_STRING = "yyyy-MM-dd'T'HH:mm:ssZ";
  public static final int MAXIMUM_BATCH_SIZE = 50;
  private static final String ME = "me";
  private static final String MIME_BOUNDARY = "3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f";
  private static final String MY_FRIENDS = "me/friends";
  private static final String MY_PHOTOS = "me/photos";
  private static final String PICTURE_PARAM = "picture";
  private static final String SDK_ANDROID = "android";
  private static final String SDK_PARAM = "sdk";
  private static final String SEARCH = "search";
  public static final String TAG = "GraphRequest";
  private static final String USER_AGENT_BASE = "FBAndroidSDK";
  private static final String USER_AGENT_HEADER = "User-Agent";
  private static final String VIDEOS_SUFFIX = "/videos";
  private static String defaultBatchApplicationId;
  private static volatile String userAgent;
  private static Pattern versionPattern = Pattern.compile("^/?v\\d+\\.\\d+/(.*)");
  private AccessToken accessToken;
  private String batchEntryDependsOn;
  private String batchEntryName;
  private boolean batchEntryOmitResultOnSuccess = true;
  private Callback callback;
  private JSONObject graphObject;
  private String graphPath;
  private HttpMethod httpMethod;
  private String overriddenURL;
  private Bundle parameters;
  private boolean skipClientToken = false;
  private Object tag;
  private String version;
  
  public GraphRequest()
  {
    this(null, null, null, null, null);
  }
  
  public GraphRequest(AccessToken paramAccessToken, String paramString)
  {
    this(paramAccessToken, paramString, null, null, null);
  }
  
  public GraphRequest(AccessToken paramAccessToken, String paramString, Bundle paramBundle, HttpMethod paramHttpMethod)
  {
    this(paramAccessToken, paramString, paramBundle, paramHttpMethod, null);
  }
  
  public GraphRequest(AccessToken paramAccessToken, String paramString, Bundle paramBundle, HttpMethod paramHttpMethod, Callback paramCallback)
  {
    this(paramAccessToken, paramString, paramBundle, paramHttpMethod, paramCallback, null);
  }
  
  public GraphRequest(AccessToken paramAccessToken, String paramString1, Bundle paramBundle, HttpMethod paramHttpMethod, Callback paramCallback, String paramString2)
  {
    accessToken = paramAccessToken;
    graphPath = paramString1;
    version = paramString2;
    setCallback(paramCallback);
    setHttpMethod(paramHttpMethod);
    if (paramBundle != null) {
      parameters = new Bundle(paramBundle);
    } else {
      parameters = new Bundle();
    }
    if (version == null) {
      version = FacebookSdk.getGraphApiVersion();
    }
  }
  
  GraphRequest(AccessToken paramAccessToken, URL paramURL)
  {
    accessToken = paramAccessToken;
    overriddenURL = paramURL.toString();
    setHttpMethod(HttpMethod.GET);
    parameters = new Bundle();
  }
  
  private void addCommonParameters()
  {
    String str1;
    if (accessToken != null)
    {
      if (!parameters.containsKey("access_token"))
      {
        str1 = accessToken.getToken();
        Logger.registerAccessToken(str1);
        parameters.putString("access_token", str1);
      }
    }
    else if ((!skipClientToken) && (!parameters.containsKey("access_token")))
    {
      str1 = FacebookSdk.getApplicationId();
      String str2 = FacebookSdk.getClientToken();
      if ((!Utility.isNullOrEmpty(str1)) && (!Utility.isNullOrEmpty(str2)))
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append(str1);
        localStringBuilder.append("|");
        localStringBuilder.append(str2);
        str1 = localStringBuilder.toString();
        parameters.putString("access_token", str1);
      }
      else
      {
        Log.d(TAG, "Warning: Request without access token missing application ID or client token.");
      }
    }
    parameters.putString("sdk", "android");
    parameters.putString("format", "json");
    if (FacebookSdk.isLoggingBehaviorEnabled(LoggingBehavior.GRAPH_API_DEBUG_INFO))
    {
      parameters.putString("debug", "info");
      return;
    }
    if (FacebookSdk.isLoggingBehaviorEnabled(LoggingBehavior.GRAPH_API_DEBUG_WARNING)) {
      parameters.putString("debug", "warning");
    }
  }
  
  private String appendParametersToBaseUrl(String paramString, Boolean paramBoolean)
  {
    if ((!paramBoolean.booleanValue()) && (httpMethod == HttpMethod.POST)) {
      return paramString;
    }
    Uri.Builder localBuilder = Uri.parse(paramString).buildUpon();
    Iterator localIterator = parameters.keySet().iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      paramBoolean = parameters.get(str);
      paramString = paramBoolean;
      if (paramBoolean == null) {
        paramString = "";
      }
      if (isSupportedParameterType(paramString)) {
        localBuilder.appendQueryParameter(str, parameterToString(paramString).toString());
      } else if (httpMethod == HttpMethod.GET) {
        throw new IllegalArgumentException(String.format(Locale.US, "Unsupported parameter type for GET request: %s", new Object[] { paramString.getClass().getSimpleName() }));
      }
    }
    return localBuilder.toString();
  }
  
  private static HttpURLConnection createConnection(URL paramURL)
    throws IOException
  {
    paramURL = (HttpURLConnection)paramURL.openConnection();
    paramURL.setRequestProperty("User-Agent", getUserAgent());
    paramURL.setRequestProperty("Accept-Language", Locale.getDefault().toString());
    paramURL.setChunkedStreamingMode(0);
    return paramURL;
  }
  
  public static GraphResponse executeAndWait(GraphRequest paramGraphRequest)
  {
    paramGraphRequest = executeBatchAndWait(new GraphRequest[] { paramGraphRequest });
    if ((paramGraphRequest != null) && (paramGraphRequest.size() == 1)) {
      return (GraphResponse)paramGraphRequest.get(0);
    }
    throw new FacebookException("invalid state: expected a single response");
  }
  
  /* Error */
  public static List<GraphResponse> executeBatchAndWait(GraphRequestBatch paramGraphRequestBatch)
  {
    // Byte code:
    //   0: aload_0
    //   1: ldc_w 468
    //   4: invokestatic 474	com/facebook/internal/Validate:notEmptyAndContainsNoNulls	(Ljava/util/Collection;Ljava/lang/String;)V
    //   7: aconst_null
    //   8: astore_1
    //   9: aload_0
    //   10: invokestatic 478	com/facebook/GraphRequest:toHttpConnection	(Lcom/facebook/GraphRequestBatch;)Ljava/net/HttpURLConnection;
    //   13: astore_2
    //   14: aload_2
    //   15: aload_0
    //   16: invokestatic 482	com/facebook/GraphRequest:executeConnectionAndWait	(Ljava/net/HttpURLConnection;Lcom/facebook/GraphRequestBatch;)Ljava/util/List;
    //   19: astore_0
    //   20: aload_2
    //   21: invokestatic 486	com/facebook/internal/Utility:disconnectQuietly	(Ljava/net/URLConnection;)V
    //   24: aload_0
    //   25: areturn
    //   26: astore_1
    //   27: aload_2
    //   28: astore_0
    //   29: goto +40 -> 69
    //   32: astore_2
    //   33: aload_1
    //   34: astore_0
    //   35: aload_2
    //   36: astore_1
    //   37: goto +32 -> 69
    //   40: astore_2
    //   41: aload_0
    //   42: invokevirtual 492	com/facebook/GraphRequestBatch:getRequests	()Ljava/util/List;
    //   45: aconst_null
    //   46: new 460	com/facebook/FacebookException
    //   49: dup
    //   50: aload_2
    //   51: invokespecial 495	com/facebook/FacebookException:<init>	(Ljava/lang/Throwable;)V
    //   54: invokestatic 499	com/facebook/GraphResponse:constructErrorResponses	(Ljava/util/List;Ljava/net/HttpURLConnection;Lcom/facebook/FacebookException;)Ljava/util/List;
    //   57: astore_2
    //   58: aload_0
    //   59: aload_2
    //   60: invokestatic 503	com/facebook/GraphRequest:runCallbacks	(Lcom/facebook/GraphRequestBatch;Ljava/util/List;)V
    //   63: aconst_null
    //   64: invokestatic 486	com/facebook/internal/Utility:disconnectQuietly	(Ljava/net/URLConnection;)V
    //   67: aload_2
    //   68: areturn
    //   69: aload_0
    //   70: invokestatic 486	com/facebook/internal/Utility:disconnectQuietly	(Ljava/net/URLConnection;)V
    //   73: aload_1
    //   74: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	75	0	paramGraphRequestBatch	GraphRequestBatch
    //   8	1	1	localObject1	Object
    //   26	8	1	localObject2	Object
    //   36	38	1	localObject3	Object
    //   13	15	2	localHttpURLConnection	HttpURLConnection
    //   32	4	2	localObject4	Object
    //   40	11	2	localException	Exception
    //   57	11	2	localList	List
    // Exception table:
    //   from	to	target	type
    //   14	20	26	finally
    //   9	14	32	finally
    //   41	63	32	finally
    //   9	14	40	java/lang/Exception
  }
  
  public static List<GraphResponse> executeBatchAndWait(Collection<GraphRequest> paramCollection)
  {
    return executeBatchAndWait(new GraphRequestBatch(paramCollection));
  }
  
  public static List<GraphResponse> executeBatchAndWait(GraphRequest... paramVarArgs)
  {
    Validate.notNull(paramVarArgs, "requests");
    return executeBatchAndWait(Arrays.asList(paramVarArgs));
  }
  
  public static GraphRequestAsyncTask executeBatchAsync(GraphRequestBatch paramGraphRequestBatch)
  {
    Validate.notEmptyAndContainsNoNulls(paramGraphRequestBatch, "requests");
    paramGraphRequestBatch = new GraphRequestAsyncTask(paramGraphRequestBatch);
    paramGraphRequestBatch.executeOnExecutor(FacebookSdk.getExecutor(), new Void[0]);
    return paramGraphRequestBatch;
  }
  
  public static GraphRequestAsyncTask executeBatchAsync(Collection<GraphRequest> paramCollection)
  {
    return executeBatchAsync(new GraphRequestBatch(paramCollection));
  }
  
  public static GraphRequestAsyncTask executeBatchAsync(GraphRequest... paramVarArgs)
  {
    Validate.notNull(paramVarArgs, "requests");
    return executeBatchAsync(Arrays.asList(paramVarArgs));
  }
  
  public static List<GraphResponse> executeConnectionAndWait(HttpURLConnection paramHttpURLConnection, GraphRequestBatch paramGraphRequestBatch)
  {
    List localList = GraphResponse.fromHttpConnection(paramHttpURLConnection, paramGraphRequestBatch);
    Utility.disconnectQuietly(paramHttpURLConnection);
    int i = paramGraphRequestBatch.size();
    if (i != localList.size()) {
      throw new FacebookException(String.format(Locale.US, "Received %d responses while expecting %d", new Object[] { Integer.valueOf(localList.size()), Integer.valueOf(i) }));
    }
    runCallbacks(paramGraphRequestBatch, localList);
    AccessTokenManager.getInstance().extendAccessTokenIfNeeded();
    return localList;
  }
  
  public static List<GraphResponse> executeConnectionAndWait(HttpURLConnection paramHttpURLConnection, Collection<GraphRequest> paramCollection)
  {
    return executeConnectionAndWait(paramHttpURLConnection, new GraphRequestBatch(paramCollection));
  }
  
  public static GraphRequestAsyncTask executeConnectionAsync(Handler paramHandler, HttpURLConnection paramHttpURLConnection, GraphRequestBatch paramGraphRequestBatch)
  {
    Validate.notNull(paramHttpURLConnection, "connection");
    paramHttpURLConnection = new GraphRequestAsyncTask(paramHttpURLConnection, paramGraphRequestBatch);
    paramGraphRequestBatch.setCallbackHandler(paramHandler);
    paramHttpURLConnection.executeOnExecutor(FacebookSdk.getExecutor(), new Void[0]);
    return paramHttpURLConnection;
  }
  
  public static GraphRequestAsyncTask executeConnectionAsync(HttpURLConnection paramHttpURLConnection, GraphRequestBatch paramGraphRequestBatch)
  {
    return executeConnectionAsync(null, paramHttpURLConnection, paramGraphRequestBatch);
  }
  
  private static String getBatchAppId(GraphRequestBatch paramGraphRequestBatch)
  {
    if (!Utility.isNullOrEmpty(paramGraphRequestBatch.getBatchApplicationId())) {
      return paramGraphRequestBatch.getBatchApplicationId();
    }
    paramGraphRequestBatch = paramGraphRequestBatch.iterator();
    while (paramGraphRequestBatch.hasNext())
    {
      Object localObject = nextaccessToken;
      if (localObject != null)
      {
        localObject = ((AccessToken)localObject).getApplicationId();
        if (localObject != null) {
          return localObject;
        }
      }
    }
    if (!Utility.isNullOrEmpty(defaultBatchApplicationId)) {
      return defaultBatchApplicationId;
    }
    return FacebookSdk.getApplicationId();
  }
  
  public static final String getDefaultBatchApplicationId()
  {
    return defaultBatchApplicationId;
  }
  
  private static String getDefaultPhotoPathIfNull(String paramString)
  {
    String str = paramString;
    if (paramString == null) {
      str = "me/photos";
    }
    return str;
  }
  
  private String getGraphPathWithVersion()
  {
    if (versionPattern.matcher(graphPath).matches()) {
      return graphPath;
    }
    return String.format("%s/%s", new Object[] { version, graphPath });
  }
  
  private static String getMimeContentType()
  {
    return String.format("multipart/form-data; boundary=%s", new Object[] { "3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f" });
  }
  
  private static String getUserAgent()
  {
    if (userAgent == null)
    {
      userAgent = String.format("%s.%s", new Object[] { "FBAndroidSDK", "4.33.0" });
      String str = InternalSettings.getCustomUserAgent();
      if (!Utility.isNullOrEmpty(str)) {
        userAgent = String.format(Locale.ROOT, "%s/%s", new Object[] { userAgent, str });
      }
    }
    return userAgent;
  }
  
  private static boolean hasOnProgressCallbacks(GraphRequestBatch paramGraphRequestBatch)
  {
    Iterator localIterator = paramGraphRequestBatch.getCallbacks().iterator();
    while (localIterator.hasNext()) {
      if (((GraphRequestBatch.Callback)localIterator.next() instanceof GraphRequestBatch.OnProgressCallback)) {
        return true;
      }
    }
    paramGraphRequestBatch = paramGraphRequestBatch.iterator();
    while (paramGraphRequestBatch.hasNext()) {
      if ((((GraphRequest)paramGraphRequestBatch.next()).getCallback() instanceof OnProgressCallback)) {
        return true;
      }
    }
    return false;
  }
  
  private static boolean isGzipCompressible(GraphRequestBatch paramGraphRequestBatch)
  {
    GraphRequest localGraphRequest;
    String str;
    do
    {
      paramGraphRequestBatch = paramGraphRequestBatch.iterator();
      Iterator localIterator;
      while (!localIterator.hasNext())
      {
        if (!paramGraphRequestBatch.hasNext()) {
          break;
        }
        localGraphRequest = (GraphRequest)paramGraphRequestBatch.next();
        localIterator = parameters.keySet().iterator();
      }
      str = (String)localIterator.next();
    } while (!isSupportedAttachmentType(parameters.get(str)));
    return false;
    return true;
  }
  
  private static boolean isMeRequest(String paramString)
  {
    Matcher localMatcher = versionPattern.matcher(paramString);
    if (localMatcher.matches()) {
      paramString = localMatcher.group(1);
    }
    if (!paramString.startsWith("me/")) {
      return paramString.startsWith("/me/");
    }
    return true;
  }
  
  private static boolean isSupportedAttachmentType(Object paramObject)
  {
    return ((paramObject instanceof Bitmap)) || ((paramObject instanceof byte[])) || ((paramObject instanceof Uri)) || ((paramObject instanceof ParcelFileDescriptor)) || ((paramObject instanceof ParcelableResourceWithMimeType));
  }
  
  private static boolean isSupportedParameterType(Object paramObject)
  {
    return ((paramObject instanceof String)) || ((paramObject instanceof Boolean)) || ((paramObject instanceof Number)) || ((paramObject instanceof Date));
  }
  
  public static GraphRequest newCustomAudienceThirdPartyIdRequest(AccessToken paramAccessToken, Context paramContext, Callback paramCallback)
  {
    return newCustomAudienceThirdPartyIdRequest(paramAccessToken, paramContext, null, paramCallback);
  }
  
  public static GraphRequest newCustomAudienceThirdPartyIdRequest(AccessToken paramAccessToken, Context paramContext, String paramString, Callback paramCallback)
  {
    Object localObject = paramString;
    if (paramString == null)
    {
      localObject = paramString;
      if (paramAccessToken != null) {
        localObject = paramAccessToken.getApplicationId();
      }
    }
    paramString = (String)localObject;
    if (localObject == null) {
      paramString = Utility.getMetadataApplicationId(paramContext);
    }
    if (paramString == null) {
      throw new FacebookException("Facebook App ID cannot be determined");
    }
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append(paramString);
    ((StringBuilder)localObject).append("/custom_audience_third_party_id");
    localObject = ((StringBuilder)localObject).toString();
    AttributionIdentifiers localAttributionIdentifiers = AttributionIdentifiers.getAttributionIdentifiers(paramContext);
    Bundle localBundle = new Bundle();
    if (paramAccessToken == null)
    {
      if (localAttributionIdentifiers == null) {
        throw new FacebookException("There is no access token and attribution identifiers could not be retrieved");
      }
      if (localAttributionIdentifiers.getAttributionId() != null) {
        paramString = localAttributionIdentifiers.getAttributionId();
      } else {
        paramString = localAttributionIdentifiers.getAndroidAdvertiserId();
      }
      if (localAttributionIdentifiers.getAttributionId() != null) {
        localBundle.putString("udid", paramString);
      }
    }
    if ((FacebookSdk.getLimitEventAndDataUsage(paramContext)) || ((localAttributionIdentifiers != null) && (localAttributionIdentifiers.isTrackingLimited()))) {
      localBundle.putString("limit_event_usage", "1");
    }
    return new GraphRequest(paramAccessToken, (String)localObject, localBundle, HttpMethod.GET, paramCallback);
  }
  
  public static GraphRequest newDeleteObjectRequest(AccessToken paramAccessToken, String paramString, Callback paramCallback)
  {
    return new GraphRequest(paramAccessToken, paramString, null, HttpMethod.DELETE, paramCallback);
  }
  
  public static GraphRequest newGraphPathRequest(AccessToken paramAccessToken, String paramString, Callback paramCallback)
  {
    return new GraphRequest(paramAccessToken, paramString, null, null, paramCallback);
  }
  
  public static GraphRequest newMeRequest(AccessToken paramAccessToken, GraphJSONObjectCallback paramGraphJSONObjectCallback)
  {
    new GraphRequest(paramAccessToken, "me", null, null, new Callback()
    {
      public void onCompleted(GraphResponse paramAnonymousGraphResponse)
      {
        if (val$callback != null) {
          val$callback.onCompleted(paramAnonymousGraphResponse.getJSONObject(), paramAnonymousGraphResponse);
        }
      }
    });
  }
  
  public static GraphRequest newMyFriendsRequest(AccessToken paramAccessToken, GraphJSONArrayCallback paramGraphJSONArrayCallback)
  {
    new GraphRequest(paramAccessToken, "me/friends", null, null, new Callback()
    {
      public void onCompleted(GraphResponse paramAnonymousGraphResponse)
      {
        if (val$callback != null)
        {
          Object localObject = paramAnonymousGraphResponse.getJSONObject();
          if (localObject != null) {
            localObject = ((JSONObject)localObject).optJSONArray("data");
          } else {
            localObject = null;
          }
          val$callback.onCompleted((JSONArray)localObject, paramAnonymousGraphResponse);
        }
      }
    });
  }
  
  public static GraphRequest newPlacesSearchRequest(AccessToken paramAccessToken, Location paramLocation, int paramInt1, int paramInt2, String paramString, GraphJSONArrayCallback paramGraphJSONArrayCallback)
  {
    if ((paramLocation == null) && (Utility.isNullOrEmpty(paramString))) {
      throw new FacebookException("Either location or searchText must be specified.");
    }
    Bundle localBundle = new Bundle(5);
    localBundle.putString("type", "place");
    localBundle.putInt("limit", paramInt2);
    if (paramLocation != null)
    {
      localBundle.putString("center", String.format(Locale.US, "%f,%f", new Object[] { Double.valueOf(paramLocation.getLatitude()), Double.valueOf(paramLocation.getLongitude()) }));
      localBundle.putInt("distance", paramInt1);
    }
    if (!Utility.isNullOrEmpty(paramString)) {
      localBundle.putString("q", paramString);
    }
    paramLocation = new Callback()
    {
      public void onCompleted(GraphResponse paramAnonymousGraphResponse)
      {
        if (val$callback != null)
        {
          Object localObject = paramAnonymousGraphResponse.getJSONObject();
          if (localObject != null) {
            localObject = ((JSONObject)localObject).optJSONArray("data");
          } else {
            localObject = null;
          }
          val$callback.onCompleted((JSONArray)localObject, paramAnonymousGraphResponse);
        }
      }
    };
    return new GraphRequest(paramAccessToken, "search", localBundle, HttpMethod.GET, paramLocation);
  }
  
  public static GraphRequest newPostRequest(AccessToken paramAccessToken, String paramString, JSONObject paramJSONObject, Callback paramCallback)
  {
    paramAccessToken = new GraphRequest(paramAccessToken, paramString, null, HttpMethod.POST, paramCallback);
    paramAccessToken.setGraphObject(paramJSONObject);
    return paramAccessToken;
  }
  
  public static GraphRequest newUploadPhotoRequest(AccessToken paramAccessToken, String paramString1, Bitmap paramBitmap, String paramString2, Bundle paramBundle, Callback paramCallback)
  {
    paramString1 = getDefaultPhotoPathIfNull(paramString1);
    Bundle localBundle = new Bundle();
    if (paramBundle != null) {
      localBundle.putAll(paramBundle);
    }
    localBundle.putParcelable("picture", paramBitmap);
    if ((paramString2 != null) && (!paramString2.isEmpty())) {
      localBundle.putString("caption", paramString2);
    }
    return new GraphRequest(paramAccessToken, paramString1, localBundle, HttpMethod.POST, paramCallback);
  }
  
  public static GraphRequest newUploadPhotoRequest(AccessToken paramAccessToken, String paramString1, Uri paramUri, String paramString2, Bundle paramBundle, Callback paramCallback)
    throws FileNotFoundException
  {
    paramString1 = getDefaultPhotoPathIfNull(paramString1);
    if (Utility.isFileUri(paramUri)) {
      return newUploadPhotoRequest(paramAccessToken, paramString1, new File(paramUri.getPath()), paramString2, paramBundle, paramCallback);
    }
    if (!Utility.isContentUri(paramUri)) {
      throw new FacebookException("The photo Uri must be either a file:// or content:// Uri");
    }
    Bundle localBundle = new Bundle();
    if (paramBundle != null) {
      localBundle.putAll(paramBundle);
    }
    localBundle.putParcelable("picture", paramUri);
    if ((paramString2 != null) && (!paramString2.isEmpty())) {
      localBundle.putString("caption", paramString2);
    }
    return new GraphRequest(paramAccessToken, paramString1, localBundle, HttpMethod.POST, paramCallback);
  }
  
  public static GraphRequest newUploadPhotoRequest(AccessToken paramAccessToken, String paramString1, File paramFile, String paramString2, Bundle paramBundle, Callback paramCallback)
    throws FileNotFoundException
  {
    paramString1 = getDefaultPhotoPathIfNull(paramString1);
    paramFile = ParcelFileDescriptor.open(paramFile, 268435456);
    Bundle localBundle = new Bundle();
    if (paramBundle != null) {
      localBundle.putAll(paramBundle);
    }
    localBundle.putParcelable("picture", paramFile);
    if ((paramString2 != null) && (!paramString2.isEmpty())) {
      localBundle.putString("caption", paramString2);
    }
    return new GraphRequest(paramAccessToken, paramString1, localBundle, HttpMethod.POST, paramCallback);
  }
  
  private static String parameterToString(Object paramObject)
  {
    if ((paramObject instanceof String)) {
      return (String)paramObject;
    }
    if ((!(paramObject instanceof Boolean)) && (!(paramObject instanceof Number)))
    {
      if ((paramObject instanceof Date)) {
        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.US).format(paramObject);
      }
      throw new IllegalArgumentException("Unsupported parameter type.");
    }
    return paramObject.toString();
  }
  
  private static void processGraphObject(JSONObject paramJSONObject, String paramString, KeyValueSerializer paramKeyValueSerializer)
    throws IOException
  {
    if (isMeRequest(paramString))
    {
      i = paramString.indexOf(":");
      int j = paramString.indexOf("?");
      if ((i > 3) && ((j == -1) || (i < j)))
      {
        i = 1;
        break label48;
      }
    }
    int i = 0;
    label48:
    paramString = paramJSONObject.keys();
    while (paramString.hasNext())
    {
      String str = (String)paramString.next();
      Object localObject = paramJSONObject.opt(str);
      boolean bool;
      if ((i != 0) && (str.equalsIgnoreCase("image"))) {
        bool = true;
      } else {
        bool = false;
      }
      processGraphObjectProperty(str, localObject, paramKeyValueSerializer, bool);
    }
  }
  
  private static void processGraphObjectProperty(String paramString, Object paramObject, KeyValueSerializer paramKeyValueSerializer, boolean paramBoolean)
    throws IOException
  {
    Object localObject = paramObject.getClass();
    if (JSONObject.class.isAssignableFrom((Class)localObject))
    {
      paramObject = (JSONObject)paramObject;
      if (paramBoolean)
      {
        localObject = paramObject.keys();
        while (((Iterator)localObject).hasNext())
        {
          String str = (String)((Iterator)localObject).next();
          processGraphObjectProperty(String.format("%s[%s]", new Object[] { paramString, str }), paramObject.opt(str), paramKeyValueSerializer, paramBoolean);
        }
      }
      if (paramObject.has("id"))
      {
        processGraphObjectProperty(paramString, paramObject.optString("id"), paramKeyValueSerializer, paramBoolean);
        return;
      }
      if (paramObject.has("url"))
      {
        processGraphObjectProperty(paramString, paramObject.optString("url"), paramKeyValueSerializer, paramBoolean);
        return;
      }
      if (paramObject.has("fbsdk:create_object")) {
        processGraphObjectProperty(paramString, paramObject.toString(), paramKeyValueSerializer, paramBoolean);
      }
    }
    else
    {
      if (JSONArray.class.isAssignableFrom((Class)localObject))
      {
        paramObject = (JSONArray)paramObject;
        int j = paramObject.length();
        int i = 0;
        while (i < j)
        {
          processGraphObjectProperty(String.format(Locale.ROOT, "%s[%d]", new Object[] { paramString, Integer.valueOf(i) }), paramObject.opt(i), paramKeyValueSerializer, paramBoolean);
          i += 1;
        }
      }
      if ((!String.class.isAssignableFrom((Class)localObject)) && (!Number.class.isAssignableFrom((Class)localObject)) && (!Boolean.class.isAssignableFrom((Class)localObject)))
      {
        if (Date.class.isAssignableFrom((Class)localObject))
        {
          paramObject = (Date)paramObject;
          paramKeyValueSerializer.writeString(paramString, new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.US).format(paramObject));
        }
      }
      else {
        paramKeyValueSerializer.writeString(paramString, paramObject.toString());
      }
    }
  }
  
  private static void processRequest(GraphRequestBatch paramGraphRequestBatch, Logger paramLogger, int paramInt, URL paramURL, OutputStream paramOutputStream, boolean paramBoolean)
    throws IOException, JSONException
  {
    paramOutputStream = new Serializer(paramOutputStream, paramLogger, paramBoolean);
    if (paramInt == 1)
    {
      paramGraphRequestBatch = paramGraphRequestBatch.get(0);
      HashMap localHashMap = new HashMap();
      Iterator localIterator = parameters.keySet().iterator();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        Object localObject = parameters.get(str);
        if (isSupportedAttachmentType(localObject)) {
          localHashMap.put(str, new Attachment(paramGraphRequestBatch, localObject));
        }
      }
      if (paramLogger != null) {
        paramLogger.append("  Parameters:\n");
      }
      serializeParameters(parameters, paramOutputStream, paramGraphRequestBatch);
      if (paramLogger != null) {
        paramLogger.append("  Attachments:\n");
      }
      serializeAttachments(localHashMap, paramOutputStream);
      if (graphObject != null) {
        processGraphObject(graphObject, paramURL.getPath(), paramOutputStream);
      }
    }
    else
    {
      paramURL = getBatchAppId(paramGraphRequestBatch);
      if (Utility.isNullOrEmpty(paramURL)) {
        throw new FacebookException("App ID was not specified at the request or Settings.");
      }
      paramOutputStream.writeString("batch_app_id", paramURL);
      paramURL = new HashMap();
      serializeRequestsAsJSON(paramOutputStream, paramGraphRequestBatch, paramURL);
      if (paramLogger != null) {
        paramLogger.append("  Attachments:\n");
      }
      serializeAttachments(paramURL, paramOutputStream);
    }
  }
  
  static void runCallbacks(final GraphRequestBatch paramGraphRequestBatch, List<GraphResponse> paramList)
  {
    int j = paramGraphRequestBatch.size();
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    while (i < j)
    {
      GraphRequest localGraphRequest = paramGraphRequestBatch.get(i);
      if (callback != null) {
        localArrayList.add(new Pair(callback, paramList.get(i)));
      }
      i += 1;
    }
    if (localArrayList.size() > 0)
    {
      paramList = new Runnable()
      {
        public void run()
        {
          Iterator localIterator = val$callbacks.iterator();
          while (localIterator.hasNext())
          {
            Pair localPair = (Pair)localIterator.next();
            ((GraphRequest.Callback)first).onCompleted((GraphResponse)second);
          }
          localIterator = paramGraphRequestBatch.getCallbacks().iterator();
          while (localIterator.hasNext()) {
            ((GraphRequestBatch.Callback)localIterator.next()).onBatchCompleted(paramGraphRequestBatch);
          }
        }
      };
      paramGraphRequestBatch = paramGraphRequestBatch.getCallbackHandler();
      if (paramGraphRequestBatch == null)
      {
        paramList.run();
        return;
      }
      paramGraphRequestBatch.post(paramList);
    }
  }
  
  private static void serializeAttachments(Map<String, Attachment> paramMap, Serializer paramSerializer)
    throws IOException
  {
    Iterator localIterator = paramMap.keySet().iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      Attachment localAttachment = (Attachment)paramMap.get(str);
      if (isSupportedAttachmentType(localAttachment.getValue())) {
        paramSerializer.writeObject(str, localAttachment.getValue(), localAttachment.getRequest());
      }
    }
  }
  
  private static void serializeParameters(Bundle paramBundle, Serializer paramSerializer, GraphRequest paramGraphRequest)
    throws IOException
  {
    Iterator localIterator = paramBundle.keySet().iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      Object localObject = paramBundle.get(str);
      if (isSupportedParameterType(localObject)) {
        paramSerializer.writeObject(str, localObject, paramGraphRequest);
      }
    }
  }
  
  private static void serializeRequestsAsJSON(Serializer paramSerializer, Collection<GraphRequest> paramCollection, Map<String, Attachment> paramMap)
    throws JSONException, IOException
  {
    JSONArray localJSONArray = new JSONArray();
    Iterator localIterator = paramCollection.iterator();
    while (localIterator.hasNext()) {
      ((GraphRequest)localIterator.next()).serializeToBatch(localJSONArray, paramMap);
    }
    paramSerializer.writeRequestsAsJson("batch", localJSONArray, paramCollection);
  }
  
  private void serializeToBatch(JSONArray paramJSONArray, final Map<String, Attachment> paramMap)
    throws JSONException, IOException
  {
    JSONObject localJSONObject = new JSONObject();
    if (batchEntryName != null)
    {
      localJSONObject.put("name", batchEntryName);
      localJSONObject.put("omit_response_on_success", batchEntryOmitResultOnSuccess);
    }
    if (batchEntryDependsOn != null) {
      localJSONObject.put("depends_on", batchEntryDependsOn);
    }
    String str1 = getRelativeUrlForBatchedRequest();
    localJSONObject.put("relative_url", str1);
    localJSONObject.put("method", httpMethod);
    if (accessToken != null) {
      Logger.registerAccessToken(accessToken.getToken());
    }
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = parameters.keySet().iterator();
    while (localIterator.hasNext())
    {
      Object localObject = (String)localIterator.next();
      localObject = parameters.get((String)localObject);
      if (isSupportedAttachmentType(localObject))
      {
        String str2 = String.format(Locale.ROOT, "%s%d", new Object[] { "file", Integer.valueOf(paramMap.size()) });
        localArrayList.add(str2);
        paramMap.put(str2, new Attachment(this, localObject));
      }
    }
    if (!localArrayList.isEmpty()) {
      localJSONObject.put("attached_files", TextUtils.join(",", localArrayList));
    }
    if (graphObject != null)
    {
      paramMap = new ArrayList();
      processGraphObject(graphObject, str1, new KeyValueSerializer()
      {
        public void writeString(String paramAnonymousString1, String paramAnonymousString2)
          throws IOException
        {
          paramMap.add(String.format(Locale.US, "%s=%s", new Object[] { paramAnonymousString1, URLEncoder.encode(paramAnonymousString2, "UTF-8") }));
        }
      });
      localJSONObject.put("body", TextUtils.join("&", paramMap));
    }
    paramJSONArray.put(localJSONObject);
  }
  
  /* Error */
  static final void serializeToUrlConnection(GraphRequestBatch paramGraphRequestBatch, HttpURLConnection paramHttpURLConnection)
    throws IOException, JSONException
  {
    // Byte code:
    //   0: new 281	com/facebook/internal/Logger
    //   3: dup
    //   4: getstatic 1032	com/facebook/LoggingBehavior:REQUESTS	Lcom/facebook/LoggingBehavior;
    //   7: ldc_w 1034
    //   10: invokespecial 1037	com/facebook/internal/Logger:<init>	(Lcom/facebook/LoggingBehavior;Ljava/lang/String;)V
    //   13: astore 7
    //   15: aload_0
    //   16: invokevirtual 553	com/facebook/GraphRequestBatch:size	()I
    //   19: istore_3
    //   20: aload_0
    //   21: invokestatic 1039	com/facebook/GraphRequest:isGzipCompressible	(Lcom/facebook/GraphRequestBatch;)Z
    //   24: istore 4
    //   26: iconst_0
    //   27: istore_2
    //   28: iload_3
    //   29: iconst_1
    //   30: if_icmpne +16 -> 46
    //   33: aload_0
    //   34: iconst_0
    //   35: invokevirtual 890	com/facebook/GraphRequestBatch:get	(I)Lcom/facebook/GraphRequest;
    //   38: getfield 343	com/facebook/GraphRequest:httpMethod	Lcom/facebook/HttpMethod;
    //   41: astore 5
    //   43: goto +8 -> 51
    //   46: getstatic 346	com/facebook/HttpMethod:POST	Lcom/facebook/HttpMethod;
    //   49: astore 5
    //   51: aload_1
    //   52: aload 5
    //   54: invokevirtual 1041	com/facebook/HttpMethod:name	()Ljava/lang/String;
    //   57: invokevirtual 1044	java/net/HttpURLConnection:setRequestMethod	(Ljava/lang/String;)V
    //   60: aload_1
    //   61: iload 4
    //   63: invokestatic 1048	com/facebook/GraphRequest:setConnectionContentType	(Ljava/net/HttpURLConnection;Z)V
    //   66: aload_1
    //   67: invokevirtual 1052	java/net/HttpURLConnection:getURL	()Ljava/net/URL;
    //   70: astore 8
    //   72: aload 7
    //   74: ldc_w 1054
    //   77: invokevirtual 906	com/facebook/internal/Logger:append	(Ljava/lang/String;)V
    //   80: aload 7
    //   82: ldc_w 1056
    //   85: aload_0
    //   86: invokevirtual 1059	com/facebook/GraphRequestBatch:getId	()Ljava/lang/String;
    //   89: invokevirtual 1063	com/facebook/internal/Logger:appendKeyValue	(Ljava/lang/String;Ljava/lang/Object;)V
    //   92: aload 7
    //   94: ldc_w 1065
    //   97: aload 8
    //   99: invokevirtual 1063	com/facebook/internal/Logger:appendKeyValue	(Ljava/lang/String;Ljava/lang/Object;)V
    //   102: aload 7
    //   104: ldc_w 1067
    //   107: aload_1
    //   108: invokevirtual 1070	java/net/HttpURLConnection:getRequestMethod	()Ljava/lang/String;
    //   111: invokevirtual 1063	com/facebook/internal/Logger:appendKeyValue	(Ljava/lang/String;Ljava/lang/Object;)V
    //   114: aload 7
    //   116: ldc -93
    //   118: aload_1
    //   119: ldc -93
    //   121: invokevirtual 1073	java/net/HttpURLConnection:getRequestProperty	(Ljava/lang/String;)Ljava/lang/String;
    //   124: invokevirtual 1063	com/facebook/internal/Logger:appendKeyValue	(Ljava/lang/String;Ljava/lang/Object;)V
    //   127: aload 7
    //   129: ldc 88
    //   131: aload_1
    //   132: ldc 88
    //   134: invokevirtual 1073	java/net/HttpURLConnection:getRequestProperty	(Ljava/lang/String;)Ljava/lang/String;
    //   137: invokevirtual 1063	com/facebook/internal/Logger:appendKeyValue	(Ljava/lang/String;Ljava/lang/Object;)V
    //   140: aload_1
    //   141: aload_0
    //   142: invokevirtual 1076	com/facebook/GraphRequestBatch:getTimeout	()I
    //   145: invokevirtual 1079	java/net/HttpURLConnection:setConnectTimeout	(I)V
    //   148: aload_1
    //   149: aload_0
    //   150: invokevirtual 1076	com/facebook/GraphRequestBatch:getTimeout	()I
    //   153: invokevirtual 1082	java/net/HttpURLConnection:setReadTimeout	(I)V
    //   156: aload 5
    //   158: getstatic 346	com/facebook/HttpMethod:POST	Lcom/facebook/HttpMethod;
    //   161: if_acmpne +5 -> 166
    //   164: iconst_1
    //   165: istore_2
    //   166: iload_2
    //   167: ifne +9 -> 176
    //   170: aload 7
    //   172: invokevirtual 1085	com/facebook/internal/Logger:log	()V
    //   175: return
    //   176: aload_1
    //   177: iconst_1
    //   178: invokevirtual 1089	java/net/HttpURLConnection:setDoOutput	(Z)V
    //   181: new 1091	java/io/BufferedOutputStream
    //   184: dup
    //   185: aload_1
    //   186: invokevirtual 1095	java/net/HttpURLConnection:getOutputStream	()Ljava/io/OutputStream;
    //   189: invokespecial 1098	java/io/BufferedOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   192: astore_1
    //   193: iload 4
    //   195: ifeq +26 -> 221
    //   198: new 1100	java/util/zip/GZIPOutputStream
    //   201: dup
    //   202: aload_1
    //   203: invokespecial 1101	java/util/zip/GZIPOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   206: astore 5
    //   208: aload 5
    //   210: astore_1
    //   211: goto +10 -> 221
    //   214: astore_0
    //   215: aload_1
    //   216: astore 5
    //   218: goto +118 -> 336
    //   221: aload_1
    //   222: astore 6
    //   224: aload_1
    //   225: astore 5
    //   227: aload_0
    //   228: invokestatic 1103	com/facebook/GraphRequest:hasOnProgressCallbacks	(Lcom/facebook/GraphRequestBatch;)Z
    //   231: ifeq +64 -> 295
    //   234: aload_1
    //   235: astore 5
    //   237: new 1105	com/facebook/ProgressNoopOutputStream
    //   240: dup
    //   241: aload_0
    //   242: invokevirtual 951	com/facebook/GraphRequestBatch:getCallbackHandler	()Landroid/os/Handler;
    //   245: invokespecial 1107	com/facebook/ProgressNoopOutputStream:<init>	(Landroid/os/Handler;)V
    //   248: astore 6
    //   250: aload_1
    //   251: astore 5
    //   253: aload_0
    //   254: aconst_null
    //   255: iload_3
    //   256: aload 8
    //   258: aload 6
    //   260: iload 4
    //   262: invokestatic 1109	com/facebook/GraphRequest:processRequest	(Lcom/facebook/GraphRequestBatch;Lcom/facebook/internal/Logger;ILjava/net/URL;Ljava/io/OutputStream;Z)V
    //   265: aload_1
    //   266: astore 5
    //   268: aload 6
    //   270: invokevirtual 1112	com/facebook/ProgressNoopOutputStream:getMaxProgress	()I
    //   273: istore_2
    //   274: aload_1
    //   275: astore 5
    //   277: new 1114	com/facebook/ProgressOutputStream
    //   280: dup
    //   281: aload_1
    //   282: aload_0
    //   283: aload 6
    //   285: invokevirtual 1118	com/facebook/ProgressNoopOutputStream:getProgressMap	()Ljava/util/Map;
    //   288: iload_2
    //   289: i2l
    //   290: invokespecial 1121	com/facebook/ProgressOutputStream:<init>	(Ljava/io/OutputStream;Lcom/facebook/GraphRequestBatch;Ljava/util/Map;J)V
    //   293: astore 6
    //   295: aload 6
    //   297: astore 5
    //   299: aload_0
    //   300: aload 7
    //   302: iload_3
    //   303: aload 8
    //   305: aload 6
    //   307: iload 4
    //   309: invokestatic 1109	com/facebook/GraphRequest:processRequest	(Lcom/facebook/GraphRequestBatch;Lcom/facebook/internal/Logger;ILjava/net/URL;Ljava/io/OutputStream;Z)V
    //   312: aload 6
    //   314: ifnull +8 -> 322
    //   317: aload 6
    //   319: invokevirtual 1126	java/io/OutputStream:close	()V
    //   322: aload 7
    //   324: invokevirtual 1085	com/facebook/internal/Logger:log	()V
    //   327: return
    //   328: astore_0
    //   329: goto +7 -> 336
    //   332: astore_0
    //   333: aconst_null
    //   334: astore 5
    //   336: aload 5
    //   338: ifnull +8 -> 346
    //   341: aload 5
    //   343: invokevirtual 1126	java/io/OutputStream:close	()V
    //   346: aload_0
    //   347: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	348	0	paramGraphRequestBatch	GraphRequestBatch
    //   0	348	1	paramHttpURLConnection	HttpURLConnection
    //   27	262	2	i	int
    //   19	284	3	j	int
    //   24	284	4	bool	boolean
    //   41	301	5	localObject1	Object
    //   222	96	6	localObject2	Object
    //   13	310	7	localLogger	Logger
    //   70	234	8	localURL	URL
    // Exception table:
    //   from	to	target	type
    //   198	208	214	finally
    //   227	234	328	finally
    //   237	250	328	finally
    //   253	265	328	finally
    //   268	274	328	finally
    //   277	295	328	finally
    //   299	312	328	finally
    //   181	193	332	finally
  }
  
  private static void setConnectionContentType(HttpURLConnection paramHttpURLConnection, boolean paramBoolean)
  {
    if (paramBoolean)
    {
      paramHttpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
      paramHttpURLConnection.setRequestProperty("Content-Encoding", "gzip");
      return;
    }
    paramHttpURLConnection.setRequestProperty("Content-Type", getMimeContentType());
  }
  
  public static final void setDefaultBatchApplicationId(String paramString)
  {
    defaultBatchApplicationId = paramString;
  }
  
  static final boolean shouldWarnOnMissingFieldsParam(GraphRequest paramGraphRequest)
  {
    String str = paramGraphRequest.getVersion();
    if (Utility.isNullOrEmpty(str)) {
      return true;
    }
    paramGraphRequest = str;
    if (str.startsWith("v")) {
      paramGraphRequest = str.substring(1);
    }
    paramGraphRequest = paramGraphRequest.split("\\.");
    boolean bool2 = false;
    boolean bool1;
    if ((paramGraphRequest.length < 2) || (Integer.parseInt(paramGraphRequest[0]) <= 2))
    {
      bool1 = bool2;
      if (Integer.parseInt(paramGraphRequest[0]) >= 2)
      {
        bool1 = bool2;
        if (Integer.parseInt(paramGraphRequest[1]) < 4) {}
      }
    }
    else
    {
      bool1 = true;
    }
    return bool1;
  }
  
  public static HttpURLConnection toHttpConnection(GraphRequestBatch paramGraphRequestBatch)
  {
    validateFieldsParamForGetRequests(paramGraphRequestBatch);
    try
    {
      Object localObject1;
      if (paramGraphRequestBatch.size() == 1) {
        localObject1 = new URL(paramGraphRequestBatch.get(0).getUrlForSingleRequest());
      } else {
        localObject1 = new URL(ServerProtocol.getGraphUrlBase());
      }
      Object localObject2 = null;
      try
      {
        localObject1 = createConnection((URL)localObject1);
        try
        {
          serializeToUrlConnection(paramGraphRequestBatch, (HttpURLConnection)localObject1);
          return localObject1;
        }
        catch (IOException|JSONException localIOException2)
        {
          paramGraphRequestBatch = (GraphRequestBatch)localObject1;
          localObject1 = localIOException2;
        }
        Utility.disconnectQuietly(paramGraphRequestBatch);
      }
      catch (IOException|JSONException localIOException1)
      {
        paramGraphRequestBatch = localIOException2;
      }
      throw new FacebookException("could not construct request body", localIOException1);
    }
    catch (MalformedURLException paramGraphRequestBatch)
    {
      throw new FacebookException("could not construct URL for request", paramGraphRequestBatch);
    }
  }
  
  public static HttpURLConnection toHttpConnection(Collection<GraphRequest> paramCollection)
  {
    Validate.notEmptyAndContainsNoNulls(paramCollection, "requests");
    return toHttpConnection(new GraphRequestBatch(paramCollection));
  }
  
  public static HttpURLConnection toHttpConnection(GraphRequest... paramVarArgs)
  {
    return toHttpConnection(Arrays.asList(paramVarArgs));
  }
  
  static final void validateFieldsParamForGetRequests(GraphRequestBatch paramGraphRequestBatch)
  {
    paramGraphRequestBatch = paramGraphRequestBatch.iterator();
    while (paramGraphRequestBatch.hasNext())
    {
      GraphRequest localGraphRequest = (GraphRequest)paramGraphRequestBatch.next();
      if ((HttpMethod.GET.equals(localGraphRequest.getHttpMethod())) && (shouldWarnOnMissingFieldsParam(localGraphRequest)))
      {
        Bundle localBundle = localGraphRequest.getParameters();
        if ((!localBundle.containsKey("fields")) || (Utility.isNullOrEmpty(localBundle.getString("fields")))) {
          Logger.log(LoggingBehavior.DEVELOPER_ERRORS, 5, "Request", "starting with Graph API v2.4, GET requests for /%s should contain an explicit \"fields\" parameter.", new Object[] { localGraphRequest.getGraphPath() });
        }
      }
    }
  }
  
  public final GraphResponse executeAndWait()
  {
    return executeAndWait(this);
  }
  
  public final GraphRequestAsyncTask executeAsync()
  {
    return executeBatchAsync(new GraphRequest[] { this });
  }
  
  public final AccessToken getAccessToken()
  {
    return accessToken;
  }
  
  public final String getBatchEntryDependsOn()
  {
    return batchEntryDependsOn;
  }
  
  public final String getBatchEntryName()
  {
    return batchEntryName;
  }
  
  public final boolean getBatchEntryOmitResultOnSuccess()
  {
    return batchEntryOmitResultOnSuccess;
  }
  
  public final Callback getCallback()
  {
    return callback;
  }
  
  public final JSONObject getGraphObject()
  {
    return graphObject;
  }
  
  public final String getGraphPath()
  {
    return graphPath;
  }
  
  public final HttpMethod getHttpMethod()
  {
    return httpMethod;
  }
  
  public final Bundle getParameters()
  {
    return parameters;
  }
  
  final String getRelativeUrlForBatchedRequest()
  {
    if (overriddenURL != null) {
      throw new FacebookException("Can't override URL for a batch request");
    }
    Object localObject = String.format("%s/%s", new Object[] { ServerProtocol.getGraphUrlBase(), getGraphPathWithVersion() });
    addCommonParameters();
    localObject = Uri.parse(appendParametersToBaseUrl((String)localObject, Boolean.valueOf(true)));
    return String.format("%s?%s", new Object[] { ((Uri)localObject).getPath(), ((Uri)localObject).getQuery() });
  }
  
  public final Object getTag()
  {
    return tag;
  }
  
  final String getUrlForSingleRequest()
  {
    if (overriddenURL != null) {
      return overriddenURL.toString();
    }
    if ((getHttpMethod() == HttpMethod.POST) && (graphPath != null) && (graphPath.endsWith("/videos"))) {
      str = ServerProtocol.getGraphVideoUrlBase();
    } else {
      str = ServerProtocol.getGraphUrlBase();
    }
    String str = String.format("%s/%s", new Object[] { str, getGraphPathWithVersion() });
    addCommonParameters();
    return appendParametersToBaseUrl(str, Boolean.valueOf(false));
  }
  
  public final String getVersion()
  {
    return version;
  }
  
  public final void setAccessToken(AccessToken paramAccessToken)
  {
    accessToken = paramAccessToken;
  }
  
  public final void setBatchEntryDependsOn(String paramString)
  {
    batchEntryDependsOn = paramString;
  }
  
  public final void setBatchEntryName(String paramString)
  {
    batchEntryName = paramString;
  }
  
  public final void setBatchEntryOmitResultOnSuccess(boolean paramBoolean)
  {
    batchEntryOmitResultOnSuccess = paramBoolean;
  }
  
  public final void setCallback(final Callback paramCallback)
  {
    if ((!FacebookSdk.isLoggingBehaviorEnabled(LoggingBehavior.GRAPH_API_DEBUG_INFO)) && (!FacebookSdk.isLoggingBehaviorEnabled(LoggingBehavior.GRAPH_API_DEBUG_WARNING)))
    {
      callback = paramCallback;
      return;
    }
    callback = new Callback()
    {
      public void onCompleted(GraphResponse paramAnonymousGraphResponse)
      {
        Object localObject1 = paramAnonymousGraphResponse.getJSONObject();
        if (localObject1 != null) {
          localObject1 = ((JSONObject)localObject1).optJSONObject("__debug__");
        } else {
          localObject1 = null;
        }
        JSONArray localJSONArray;
        if (localObject1 != null) {
          localJSONArray = ((JSONObject)localObject1).optJSONArray("messages");
        } else {
          localJSONArray = null;
        }
        if (localJSONArray != null)
        {
          int i = 0;
          while (i < localJSONArray.length())
          {
            Object localObject3 = localJSONArray.optJSONObject(i);
            if (localObject3 != null) {
              localObject1 = ((JSONObject)localObject3).optString("message");
            } else {
              localObject1 = null;
            }
            Object localObject2;
            if (localObject3 != null) {
              localObject2 = ((JSONObject)localObject3).optString("type");
            } else {
              localObject2 = null;
            }
            if (localObject3 != null) {
              localObject3 = ((JSONObject)localObject3).optString("link");
            } else {
              localObject3 = null;
            }
            if ((localObject1 != null) && (localObject2 != null))
            {
              LoggingBehavior localLoggingBehavior = LoggingBehavior.GRAPH_API_DEBUG_INFO;
              if (((String)localObject2).equals("warning")) {
                localLoggingBehavior = LoggingBehavior.GRAPH_API_DEBUG_WARNING;
              }
              localObject2 = localObject1;
              if (!Utility.isNullOrEmpty((String)localObject3))
              {
                localObject2 = new StringBuilder();
                ((StringBuilder)localObject2).append((String)localObject1);
                ((StringBuilder)localObject2).append(" Link: ");
                ((StringBuilder)localObject2).append((String)localObject3);
                localObject2 = ((StringBuilder)localObject2).toString();
              }
              Logger.log(localLoggingBehavior, GraphRequest.TAG, (String)localObject2);
            }
            i += 1;
          }
        }
        if (paramCallback != null) {
          paramCallback.onCompleted(paramAnonymousGraphResponse);
        }
      }
    };
  }
  
  public final void setGraphObject(JSONObject paramJSONObject)
  {
    graphObject = paramJSONObject;
  }
  
  public final void setGraphPath(String paramString)
  {
    graphPath = paramString;
  }
  
  public final void setHttpMethod(HttpMethod paramHttpMethod)
  {
    if ((overriddenURL != null) && (paramHttpMethod != HttpMethod.GET)) {
      throw new FacebookException("Can't change HTTP method on request with overridden URL.");
    }
    if (paramHttpMethod == null) {
      paramHttpMethod = HttpMethod.GET;
    }
    httpMethod = paramHttpMethod;
  }
  
  public final void setParameters(Bundle paramBundle)
  {
    parameters = paramBundle;
  }
  
  public final void setSkipClientToken(boolean paramBoolean)
  {
    skipClientToken = paramBoolean;
  }
  
  public final void setTag(Object paramObject)
  {
    tag = paramObject;
  }
  
  public final void setVersion(String paramString)
  {
    version = paramString;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("{Request: ");
    localStringBuilder.append(" accessToken: ");
    Object localObject;
    if (accessToken == null) {
      localObject = "null";
    } else {
      localObject = accessToken;
    }
    localStringBuilder.append(localObject);
    localStringBuilder.append(", graphPath: ");
    localStringBuilder.append(graphPath);
    localStringBuilder.append(", graphObject: ");
    localStringBuilder.append(graphObject);
    localStringBuilder.append(", httpMethod: ");
    localStringBuilder.append(httpMethod);
    localStringBuilder.append(", parameters: ");
    localStringBuilder.append(parameters);
    localStringBuilder.append("}");
    return localStringBuilder.toString();
  }
  
  private static class Attachment
  {
    private final GraphRequest request;
    private final Object value;
    
    public Attachment(GraphRequest paramGraphRequest, Object paramObject)
    {
      request = paramGraphRequest;
      value = paramObject;
    }
    
    public GraphRequest getRequest()
    {
      return request;
    }
    
    public Object getValue()
    {
      return value;
    }
  }
  
  public static abstract interface Callback
  {
    public abstract void onCompleted(GraphResponse paramGraphResponse);
  }
  
  public static abstract interface GraphJSONArrayCallback
  {
    public abstract void onCompleted(JSONArray paramJSONArray, GraphResponse paramGraphResponse);
  }
  
  public static abstract interface GraphJSONObjectCallback
  {
    public abstract void onCompleted(JSONObject paramJSONObject, GraphResponse paramGraphResponse);
  }
  
  private static abstract interface KeyValueSerializer
  {
    public abstract void writeString(String paramString1, String paramString2)
      throws IOException;
  }
  
  public static abstract interface OnProgressCallback
    extends GraphRequest.Callback
  {
    public abstract void onProgress(long paramLong1, long paramLong2);
  }
  
  public static class ParcelableResourceWithMimeType<RESOURCE extends Parcelable>
    implements Parcelable
  {
    public static final Parcelable.Creator<ParcelableResourceWithMimeType> CREATOR = new Parcelable.Creator()
    {
      public GraphRequest.ParcelableResourceWithMimeType createFromParcel(Parcel paramAnonymousParcel)
      {
        return new GraphRequest.ParcelableResourceWithMimeType(paramAnonymousParcel, null);
      }
      
      public GraphRequest.ParcelableResourceWithMimeType[] newArray(int paramAnonymousInt)
      {
        return new GraphRequest.ParcelableResourceWithMimeType[paramAnonymousInt];
      }
    };
    private final String mimeType;
    private final RESOURCE resource;
    
    private ParcelableResourceWithMimeType(Parcel paramParcel)
    {
      mimeType = paramParcel.readString();
      resource = paramParcel.readParcelable(FacebookSdk.getApplicationContext().getClassLoader());
    }
    
    public ParcelableResourceWithMimeType(RESOURCE paramRESOURCE, String paramString)
    {
      mimeType = paramString;
      resource = paramRESOURCE;
    }
    
    public int describeContents()
    {
      return 1;
    }
    
    public String getMimeType()
    {
      return mimeType;
    }
    
    public RESOURCE getResource()
    {
      return resource;
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      paramParcel.writeString(mimeType);
      paramParcel.writeParcelable(resource, paramInt);
    }
  }
  
  private static class Serializer
    implements GraphRequest.KeyValueSerializer
  {
    private boolean firstWrite = true;
    private final Logger logger;
    private final OutputStream outputStream;
    private boolean useUrlEncode = false;
    
    public Serializer(OutputStream paramOutputStream, Logger paramLogger, boolean paramBoolean)
    {
      outputStream = paramOutputStream;
      logger = paramLogger;
      useUrlEncode = paramBoolean;
    }
    
    private RuntimeException getInvalidTypeError()
    {
      return new IllegalArgumentException("value is not a supported type.");
    }
    
    public void write(String paramString, Object... paramVarArgs)
      throws IOException
    {
      if (!useUrlEncode)
      {
        if (firstWrite)
        {
          outputStream.write("--".getBytes());
          outputStream.write("3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f".getBytes());
          outputStream.write("\r\n".getBytes());
          firstWrite = false;
        }
        outputStream.write(String.format(paramString, paramVarArgs).getBytes());
        return;
      }
      outputStream.write(URLEncoder.encode(String.format(Locale.US, paramString, paramVarArgs), "UTF-8").getBytes());
    }
    
    public void writeBitmap(String paramString, Bitmap paramBitmap)
      throws IOException
    {
      writeContentDisposition(paramString, paramString, "image/png");
      paramBitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
      writeLine("", new Object[0]);
      writeRecordBoundary();
      if (logger != null)
      {
        paramBitmap = logger;
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("    ");
        localStringBuilder.append(paramString);
        paramBitmap.appendKeyValue(localStringBuilder.toString(), "<Image>");
      }
    }
    
    public void writeBytes(String paramString, byte[] paramArrayOfByte)
      throws IOException
    {
      writeContentDisposition(paramString, paramString, "content/unknown");
      outputStream.write(paramArrayOfByte);
      writeLine("", new Object[0]);
      writeRecordBoundary();
      if (logger != null)
      {
        Logger localLogger = logger;
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("    ");
        localStringBuilder.append(paramString);
        localLogger.appendKeyValue(localStringBuilder.toString(), String.format(Locale.ROOT, "<Data: %d>", new Object[] { Integer.valueOf(paramArrayOfByte.length) }));
      }
    }
    
    public void writeContentDisposition(String paramString1, String paramString2, String paramString3)
      throws IOException
    {
      if (!useUrlEncode)
      {
        write("Content-Disposition: form-data; name=\"%s\"", new Object[] { paramString1 });
        if (paramString2 != null) {
          write("; filename=\"%s\"", new Object[] { paramString2 });
        }
        writeLine("", new Object[0]);
        if (paramString3 != null) {
          writeLine("%s: %s", new Object[] { "Content-Type", paramString3 });
        }
        writeLine("", new Object[0]);
        return;
      }
      outputStream.write(String.format("%s=", new Object[] { paramString1 }).getBytes());
    }
    
    public void writeContentUri(String paramString1, Uri paramUri, String paramString2)
      throws IOException
    {
      String str = paramString2;
      if (paramString2 == null) {
        str = "content/unknown";
      }
      writeContentDisposition(paramString1, paramString1, str);
      int i;
      if ((outputStream instanceof ProgressNoopOutputStream))
      {
        long l = Utility.getContentSize(paramUri);
        ((ProgressNoopOutputStream)outputStream).addProgress(l);
        i = 0;
      }
      else
      {
        i = Utility.copyAndCloseInputStream(FacebookSdk.getApplicationContext().getContentResolver().openInputStream(paramUri), outputStream) + 0;
      }
      writeLine("", new Object[0]);
      writeRecordBoundary();
      if (logger != null)
      {
        paramUri = logger;
        paramString2 = new StringBuilder();
        paramString2.append("    ");
        paramString2.append(paramString1);
        paramUri.appendKeyValue(paramString2.toString(), String.format(Locale.ROOT, "<Data: %d>", new Object[] { Integer.valueOf(i) }));
      }
    }
    
    public void writeFile(String paramString1, ParcelFileDescriptor paramParcelFileDescriptor, String paramString2)
      throws IOException
    {
      String str = paramString2;
      if (paramString2 == null) {
        str = "content/unknown";
      }
      writeContentDisposition(paramString1, paramString1, str);
      int i;
      if ((outputStream instanceof ProgressNoopOutputStream))
      {
        ((ProgressNoopOutputStream)outputStream).addProgress(paramParcelFileDescriptor.getStatSize());
        i = 0;
      }
      else
      {
        i = Utility.copyAndCloseInputStream(new ParcelFileDescriptor.AutoCloseInputStream(paramParcelFileDescriptor), outputStream) + 0;
      }
      writeLine("", new Object[0]);
      writeRecordBoundary();
      if (logger != null)
      {
        paramParcelFileDescriptor = logger;
        paramString2 = new StringBuilder();
        paramString2.append("    ");
        paramString2.append(paramString1);
        paramParcelFileDescriptor.appendKeyValue(paramString2.toString(), String.format(Locale.ROOT, "<Data: %d>", new Object[] { Integer.valueOf(i) }));
      }
    }
    
    public void writeLine(String paramString, Object... paramVarArgs)
      throws IOException
    {
      write(paramString, paramVarArgs);
      if (!useUrlEncode) {
        write("\r\n", new Object[0]);
      }
    }
    
    public void writeObject(String paramString, Object paramObject, GraphRequest paramGraphRequest)
      throws IOException
    {
      if ((outputStream instanceof RequestOutputStream)) {
        ((RequestOutputStream)outputStream).setCurrentRequest(paramGraphRequest);
      }
      if (GraphRequest.isSupportedParameterType(paramObject))
      {
        writeString(paramString, GraphRequest.parameterToString(paramObject));
        return;
      }
      if ((paramObject instanceof Bitmap))
      {
        writeBitmap(paramString, (Bitmap)paramObject);
        return;
      }
      if ((paramObject instanceof byte[]))
      {
        writeBytes(paramString, (byte[])paramObject);
        return;
      }
      if ((paramObject instanceof Uri))
      {
        writeContentUri(paramString, (Uri)paramObject, null);
        return;
      }
      if ((paramObject instanceof ParcelFileDescriptor))
      {
        writeFile(paramString, (ParcelFileDescriptor)paramObject, null);
        return;
      }
      if ((paramObject instanceof GraphRequest.ParcelableResourceWithMimeType))
      {
        paramGraphRequest = (GraphRequest.ParcelableResourceWithMimeType)paramObject;
        paramObject = paramGraphRequest.getResource();
        paramGraphRequest = paramGraphRequest.getMimeType();
        if ((paramObject instanceof ParcelFileDescriptor))
        {
          writeFile(paramString, (ParcelFileDescriptor)paramObject, paramGraphRequest);
          return;
        }
        if ((paramObject instanceof Uri))
        {
          writeContentUri(paramString, (Uri)paramObject, paramGraphRequest);
          return;
        }
        throw getInvalidTypeError();
      }
      throw getInvalidTypeError();
    }
    
    public void writeRecordBoundary()
      throws IOException
    {
      if (!useUrlEncode)
      {
        writeLine("--%s", new Object[] { "3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f" });
        return;
      }
      outputStream.write("&".getBytes());
    }
    
    public void writeRequestsAsJson(String paramString, JSONArray paramJSONArray, Collection<GraphRequest> paramCollection)
      throws IOException, JSONException
    {
      if (!(outputStream instanceof RequestOutputStream))
      {
        writeString(paramString, paramJSONArray.toString());
        return;
      }
      Object localObject = (RequestOutputStream)outputStream;
      writeContentDisposition(paramString, null, null);
      write("[", new Object[0]);
      paramCollection = paramCollection.iterator();
      int i = 0;
      while (paramCollection.hasNext())
      {
        GraphRequest localGraphRequest = (GraphRequest)paramCollection.next();
        JSONObject localJSONObject = paramJSONArray.getJSONObject(i);
        ((RequestOutputStream)localObject).setCurrentRequest(localGraphRequest);
        if (i > 0) {
          write(",%s", new Object[] { localJSONObject.toString() });
        } else {
          write("%s", new Object[] { localJSONObject.toString() });
        }
        i += 1;
      }
      write("]", new Object[0]);
      if (logger != null)
      {
        paramCollection = logger;
        localObject = new StringBuilder();
        ((StringBuilder)localObject).append("    ");
        ((StringBuilder)localObject).append(paramString);
        paramCollection.appendKeyValue(((StringBuilder)localObject).toString(), paramJSONArray.toString());
      }
    }
    
    public void writeString(String paramString1, String paramString2)
      throws IOException
    {
      writeContentDisposition(paramString1, null, null);
      writeLine("%s", new Object[] { paramString2 });
      writeRecordBoundary();
      if (logger != null)
      {
        Logger localLogger = logger;
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("    ");
        localStringBuilder.append(paramString1);
        localLogger.appendKeyValue(localStringBuilder.toString(), paramString2);
      }
    }
  }
}
