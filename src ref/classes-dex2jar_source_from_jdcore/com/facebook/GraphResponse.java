package com.facebook;

import android.util.Log;
import com.facebook.internal.Logger;
import com.facebook.internal.Utility;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class GraphResponse
{
  private static final String BODY_KEY = "body";
  private static final String CODE_KEY = "code";
  public static final String NON_JSON_RESPONSE_PROPERTY = "FACEBOOK_NON_JSON_RESULT";
  private static final String RESPONSE_LOG_TAG = "Response";
  public static final String SUCCESS_KEY = "success";
  private static final String TAG = "GraphResponse";
  private final HttpURLConnection connection;
  private final FacebookRequestError error;
  private final JSONObject graphObject;
  private final JSONArray graphObjectArray;
  private final String rawResponse;
  private final GraphRequest request;
  
  GraphResponse(GraphRequest paramGraphRequest, HttpURLConnection paramHttpURLConnection, FacebookRequestError paramFacebookRequestError)
  {
    this(paramGraphRequest, paramHttpURLConnection, null, null, null, paramFacebookRequestError);
  }
  
  GraphResponse(GraphRequest paramGraphRequest, HttpURLConnection paramHttpURLConnection, String paramString, JSONArray paramJSONArray)
  {
    this(paramGraphRequest, paramHttpURLConnection, paramString, null, paramJSONArray, null);
  }
  
  GraphResponse(GraphRequest paramGraphRequest, HttpURLConnection paramHttpURLConnection, String paramString, JSONObject paramJSONObject)
  {
    this(paramGraphRequest, paramHttpURLConnection, paramString, paramJSONObject, null, null);
  }
  
  GraphResponse(GraphRequest paramGraphRequest, HttpURLConnection paramHttpURLConnection, String paramString, JSONObject paramJSONObject, JSONArray paramJSONArray, FacebookRequestError paramFacebookRequestError)
  {
    request = paramGraphRequest;
    connection = paramHttpURLConnection;
    rawResponse = paramString;
    graphObject = paramJSONObject;
    graphObjectArray = paramJSONArray;
    error = paramFacebookRequestError;
  }
  
  static List<GraphResponse> constructErrorResponses(List<GraphRequest> paramList, HttpURLConnection paramHttpURLConnection, FacebookException paramFacebookException)
  {
    int j = paramList.size();
    ArrayList localArrayList = new ArrayList(j);
    int i = 0;
    while (i < j)
    {
      localArrayList.add(new GraphResponse((GraphRequest)paramList.get(i), paramHttpURLConnection, new FacebookRequestError(paramHttpURLConnection, paramFacebookException)));
      i += 1;
    }
    return localArrayList;
  }
  
  private static GraphResponse createResponseFromObject(GraphRequest paramGraphRequest, HttpURLConnection paramHttpURLConnection, Object paramObject1, Object paramObject2)
    throws JSONException
  {
    Object localObject = paramObject1;
    if ((paramObject1 instanceof JSONObject))
    {
      paramObject1 = (JSONObject)paramObject1;
      paramObject2 = FacebookRequestError.checkResponseAndCreateError(paramObject1, paramObject2, paramHttpURLConnection);
      if (paramObject2 != null)
      {
        Log.e(TAG, paramObject2.toString());
        if ((paramObject2.getErrorCode() == 190) && (Utility.isCurrentAccessToken(paramGraphRequest.getAccessToken()))) {
          if (paramObject2.getSubErrorCode() != 493) {
            AccessToken.setCurrentAccessToken(null);
          } else if (!AccessToken.getCurrentAccessToken().isExpired()) {
            AccessToken.expireCurrentAccessToken();
          }
        }
        return new GraphResponse(paramGraphRequest, paramHttpURLConnection, paramObject2);
      }
      paramObject1 = Utility.getStringPropertyAsJSON(paramObject1, "body", "FACEBOOK_NON_JSON_RESULT");
      if ((paramObject1 instanceof JSONObject)) {
        return new GraphResponse(paramGraphRequest, paramHttpURLConnection, paramObject1.toString(), (JSONObject)paramObject1);
      }
      if ((paramObject1 instanceof JSONArray)) {
        return new GraphResponse(paramGraphRequest, paramHttpURLConnection, paramObject1.toString(), (JSONArray)paramObject1);
      }
      localObject = JSONObject.NULL;
    }
    if (localObject == JSONObject.NULL) {
      return new GraphResponse(paramGraphRequest, paramHttpURLConnection, localObject.toString(), (JSONObject)null);
    }
    paramGraphRequest = new StringBuilder();
    paramGraphRequest.append("Got unexpected object type in response, class: ");
    paramGraphRequest.append(localObject.getClass().getSimpleName());
    throw new FacebookException(paramGraphRequest.toString());
  }
  
  private static List<GraphResponse> createResponsesFromObject(HttpURLConnection paramHttpURLConnection, List<GraphRequest> paramList, Object paramObject)
    throws FacebookException, JSONException
  {
    int k = paramList.size();
    ArrayList localArrayList = new ArrayList(k);
    int j = 0;
    GraphRequest localGraphRequest;
    if (k == 1) {
      localGraphRequest = (GraphRequest)paramList.get(0);
    }
    for (;;)
    {
      try
      {
        JSONObject localJSONObject = new JSONObject();
        localJSONObject.put("body", paramObject);
        if (paramHttpURLConnection == null) {
          break label324;
        }
        i = paramHttpURLConnection.getResponseCode();
        localJSONObject.put("code", i);
        JSONArray localJSONArray = new JSONArray();
        localJSONArray.put(localJSONObject);
      }
      catch (IOException localIOException)
      {
        localArrayList.add(new GraphResponse(localGraphRequest, paramHttpURLConnection, new FacebookRequestError(paramHttpURLConnection, localIOException)));
      }
      catch (JSONException localJSONException1)
      {
        localArrayList.add(new GraphResponse(localGraphRequest, paramHttpURLConnection, new FacebookRequestError(paramHttpURLConnection, localJSONException1)));
      }
      Object localObject = paramObject;
      if ((localObject instanceof JSONArray))
      {
        localObject = (JSONArray)localObject;
        i = j;
        if (((JSONArray)localObject).length() == k)
        {
          while (i < ((JSONArray)localObject).length())
          {
            localGraphRequest = (GraphRequest)paramList.get(i);
            try
            {
              localArrayList.add(createResponseFromObject(localGraphRequest, paramHttpURLConnection, ((JSONArray)localObject).get(i), paramObject));
            }
            catch (FacebookException localFacebookException)
            {
              localArrayList.add(new GraphResponse(localGraphRequest, paramHttpURLConnection, new FacebookRequestError(paramHttpURLConnection, localFacebookException)));
            }
            catch (JSONException localJSONException2)
            {
              localArrayList.add(new GraphResponse(localGraphRequest, paramHttpURLConnection, new FacebookRequestError(paramHttpURLConnection, localJSONException2)));
            }
            i += 1;
          }
          return localArrayList;
        }
      }
      throw new FacebookException("Unexpected number of results");
      label324:
      int i = 200;
    }
  }
  
  static List<GraphResponse> createResponsesFromStream(InputStream paramInputStream, HttpURLConnection paramHttpURLConnection, GraphRequestBatch paramGraphRequestBatch)
    throws FacebookException, JSONException, IOException
  {
    paramInputStream = Utility.readStreamToString(paramInputStream);
    Logger.log(LoggingBehavior.INCLUDE_RAW_RESPONSES, "Response", "Response (raw)\n  Size: %d\n  Response:\n%s\n", new Object[] { Integer.valueOf(paramInputStream.length()), paramInputStream });
    return createResponsesFromString(paramInputStream, paramHttpURLConnection, paramGraphRequestBatch);
  }
  
  static List<GraphResponse> createResponsesFromString(String paramString, HttpURLConnection paramHttpURLConnection, GraphRequestBatch paramGraphRequestBatch)
    throws FacebookException, JSONException, IOException
  {
    paramHttpURLConnection = createResponsesFromObject(paramHttpURLConnection, paramGraphRequestBatch, new JSONTokener(paramString).nextValue());
    Logger.log(LoggingBehavior.REQUESTS, "Response", "Response\n  Id: %s\n  Size: %d\n  Responses:\n%s\n", new Object[] { paramGraphRequestBatch.getId(), Integer.valueOf(paramString.length()), paramHttpURLConnection });
    return paramHttpURLConnection;
  }
  
  /* Error */
  static List<GraphResponse> fromHttpConnection(HttpURLConnection paramHttpURLConnection, GraphRequestBatch paramGraphRequestBatch)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 6
    //   3: aconst_null
    //   4: astore 7
    //   6: aconst_null
    //   7: astore_3
    //   8: aload_3
    //   9: astore_2
    //   10: aload 6
    //   12: astore 4
    //   14: aload 7
    //   16: astore 5
    //   18: aload_0
    //   19: invokevirtual 201	java/net/HttpURLConnection:getResponseCode	()I
    //   22: sipush 400
    //   25: if_icmplt +21 -> 46
    //   28: aload_3
    //   29: astore_2
    //   30: aload 6
    //   32: astore 4
    //   34: aload 7
    //   36: astore 5
    //   38: aload_0
    //   39: invokevirtual 279	java/net/HttpURLConnection:getErrorStream	()Ljava/io/InputStream;
    //   42: astore_3
    //   43: goto +141 -> 184
    //   46: aload_3
    //   47: astore_2
    //   48: aload 6
    //   50: astore 4
    //   52: aload 7
    //   54: astore 5
    //   56: aload_0
    //   57: invokevirtual 282	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
    //   60: astore_3
    //   61: goto +123 -> 184
    //   64: aload_3
    //   65: astore_2
    //   66: aload_3
    //   67: astore 4
    //   69: aload_3
    //   70: astore 5
    //   72: aload_3
    //   73: aload_0
    //   74: aload_1
    //   75: invokestatic 284	com/facebook/GraphResponse:createResponsesFromStream	(Ljava/io/InputStream;Ljava/net/HttpURLConnection;Lcom/facebook/GraphRequestBatch;)Ljava/util/List;
    //   78: astore 6
    //   80: aload_3
    //   81: invokestatic 288	com/facebook/internal/Utility:closeQuietly	(Ljava/io/Closeable;)V
    //   84: aload 6
    //   86: areturn
    //   87: astore_0
    //   88: goto +90 -> 178
    //   91: astore_3
    //   92: aload 4
    //   94: astore_2
    //   95: getstatic 263	com/facebook/LoggingBehavior:REQUESTS	Lcom/facebook/LoggingBehavior;
    //   98: ldc 20
    //   100: ldc_w 290
    //   103: iconst_1
    //   104: anewarray 4	java/lang/Object
    //   107: dup
    //   108: iconst_0
    //   109: aload_3
    //   110: aastore
    //   111: invokestatic 246	com/facebook/internal/Logger:log	(Lcom/facebook/LoggingBehavior;Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Object;)V
    //   114: aload 4
    //   116: astore_2
    //   117: aload_1
    //   118: aload_0
    //   119: new 182	com/facebook/FacebookException
    //   122: dup
    //   123: aload_3
    //   124: invokespecial 293	com/facebook/FacebookException:<init>	(Ljava/lang/Throwable;)V
    //   127: invokestatic 295	com/facebook/GraphResponse:constructErrorResponses	(Ljava/util/List;Ljava/net/HttpURLConnection;Lcom/facebook/FacebookException;)Ljava/util/List;
    //   130: astore_0
    //   131: aload 4
    //   133: invokestatic 288	com/facebook/internal/Utility:closeQuietly	(Ljava/io/Closeable;)V
    //   136: aload_0
    //   137: areturn
    //   138: astore_3
    //   139: aload 5
    //   141: astore_2
    //   142: getstatic 263	com/facebook/LoggingBehavior:REQUESTS	Lcom/facebook/LoggingBehavior;
    //   145: ldc 20
    //   147: ldc_w 290
    //   150: iconst_1
    //   151: anewarray 4	java/lang/Object
    //   154: dup
    //   155: iconst_0
    //   156: aload_3
    //   157: aastore
    //   158: invokestatic 246	com/facebook/internal/Logger:log	(Lcom/facebook/LoggingBehavior;Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Object;)V
    //   161: aload 5
    //   163: astore_2
    //   164: aload_1
    //   165: aload_0
    //   166: aload_3
    //   167: invokestatic 295	com/facebook/GraphResponse:constructErrorResponses	(Ljava/util/List;Ljava/net/HttpURLConnection;Lcom/facebook/FacebookException;)Ljava/util/List;
    //   170: astore_0
    //   171: aload 5
    //   173: invokestatic 288	com/facebook/internal/Utility:closeQuietly	(Ljava/io/Closeable;)V
    //   176: aload_0
    //   177: areturn
    //   178: aload_2
    //   179: invokestatic 288	com/facebook/internal/Utility:closeQuietly	(Ljava/io/Closeable;)V
    //   182: aload_0
    //   183: athrow
    //   184: goto -120 -> 64
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	187	0	paramHttpURLConnection	HttpURLConnection
    //   0	187	1	paramGraphRequestBatch	GraphRequestBatch
    //   9	170	2	localObject1	Object
    //   7	74	3	localInputStream	InputStream
    //   91	33	3	localException	Exception
    //   138	29	3	localFacebookException	FacebookException
    //   12	120	4	localObject2	Object
    //   16	156	5	localObject3	Object
    //   1	84	6	localList	List
    //   4	49	7	localObject4	Object
    // Exception table:
    //   from	to	target	type
    //   18	28	87	finally
    //   38	43	87	finally
    //   56	61	87	finally
    //   72	80	87	finally
    //   95	114	87	finally
    //   117	131	87	finally
    //   142	161	87	finally
    //   164	171	87	finally
    //   18	28	91	java/lang/Exception
    //   38	43	91	java/lang/Exception
    //   56	61	91	java/lang/Exception
    //   72	80	91	java/lang/Exception
    //   18	28	138	com/facebook/FacebookException
    //   38	43	138	com/facebook/FacebookException
    //   56	61	138	com/facebook/FacebookException
    //   72	80	138	com/facebook/FacebookException
  }
  
  public final HttpURLConnection getConnection()
  {
    return connection;
  }
  
  public final FacebookRequestError getError()
  {
    return error;
  }
  
  public final JSONArray getJSONArray()
  {
    return graphObjectArray;
  }
  
  public final JSONObject getJSONObject()
  {
    return graphObject;
  }
  
  public String getRawResponse()
  {
    return rawResponse;
  }
  
  public GraphRequest getRequest()
  {
    return request;
  }
  
  public GraphRequest getRequestForPagedResults(PagingDirection paramPagingDirection)
  {
    if (graphObject != null)
    {
      JSONObject localJSONObject = graphObject.optJSONObject("paging");
      if (localJSONObject != null)
      {
        if (paramPagingDirection == PagingDirection.NEXT)
        {
          paramPagingDirection = localJSONObject.optString("next");
          break label53;
        }
        paramPagingDirection = localJSONObject.optString("previous");
        break label53;
      }
    }
    paramPagingDirection = null;
    label53:
    if (Utility.isNullOrEmpty(paramPagingDirection)) {
      return null;
    }
    if ((paramPagingDirection != null) && (paramPagingDirection.equals(request.getUrlForSingleRequest()))) {
      return null;
    }
    try
    {
      paramPagingDirection = new GraphRequest(request.getAccessToken(), new URL(paramPagingDirection));
      return paramPagingDirection;
    }
    catch (MalformedURLException paramPagingDirection) {}
    return null;
  }
  
  public String toString()
  {
    try
    {
      localObject = Locale.US;
      if (connection == null) {
        break label129;
      }
      i = connection.getResponseCode();
    }
    catch (IOException localIOException)
    {
      for (;;)
      {
        Object localObject;
        label48:
        StringBuilder localStringBuilder;
        continue;
        label129:
        int i = 200;
      }
    }
    localObject = String.format((Locale)localObject, "%d", new Object[] { Integer.valueOf(i) });
    break label48;
    localObject = "unknown";
    localStringBuilder = new StringBuilder();
    localStringBuilder.append("{Response: ");
    localStringBuilder.append(" responseCode: ");
    localStringBuilder.append((String)localObject);
    localStringBuilder.append(", graphObject: ");
    localStringBuilder.append(graphObject);
    localStringBuilder.append(", error: ");
    localStringBuilder.append(error);
    localStringBuilder.append("}");
    return localStringBuilder.toString();
  }
  
  public static enum PagingDirection
  {
    NEXT,  PREVIOUS;
    
    private PagingDirection() {}
  }
}
