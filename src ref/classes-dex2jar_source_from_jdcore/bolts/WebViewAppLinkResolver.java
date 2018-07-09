package bolts;

import android.content.Context;
import android.net.Uri;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WebViewAppLinkResolver
  implements AppLinkResolver
{
  private static final String KEY_AL_VALUE = "value";
  private static final String KEY_ANDROID = "android";
  private static final String KEY_APP_NAME = "app_name";
  private static final String KEY_CLASS = "class";
  private static final String KEY_PACKAGE = "package";
  private static final String KEY_SHOULD_FALLBACK = "should_fallback";
  private static final String KEY_URL = "url";
  private static final String KEY_WEB = "web";
  private static final String KEY_WEB_URL = "url";
  private static final String META_TAG_PREFIX = "al";
  private static final String PREFER_HEADER = "Prefer-Html-Meta-Tags";
  private static final String TAG_EXTRACTION_JAVASCRIPT = "javascript:boltsWebViewAppLinkResolverResult.setValue((function() {  var metaTags = document.getElementsByTagName('meta');  var results = [];  for (var i = 0; i < metaTags.length; i++) {    var property = metaTags[i].getAttribute('property');    if (property && property.substring(0, 'al:'.length) === 'al:') {      var tag = { \"property\": metaTags[i].getAttribute('property') };      if (metaTags[i].hasAttribute('content')) {        tag['content'] = metaTags[i].getAttribute('content');      }      results.push(tag);    }  }  return JSON.stringify(results);})())";
  private final Context context;
  
  public WebViewAppLinkResolver(Context paramContext)
  {
    context = paramContext;
  }
  
  private static List<Map<String, Object>> getAlList(Map<String, Object> paramMap, String paramString)
  {
    paramMap = (List)paramMap.get(paramString);
    if (paramMap == null) {
      return Collections.emptyList();
    }
    return paramMap;
  }
  
  private static AppLink makeAppLinkFromAlData(Map<String, Object> paramMap, Uri paramUri)
  {
    ArrayList localArrayList = new ArrayList();
    Object localObject2 = (List)paramMap.get("android");
    Object localObject1 = localObject2;
    if (localObject2 == null) {
      localObject1 = Collections.emptyList();
    }
    localObject2 = ((List)localObject1).iterator();
    for (;;)
    {
      boolean bool = ((Iterator)localObject2).hasNext();
      int i = 0;
      localObject1 = null;
      if (!bool) {
        break;
      }
      localObject1 = (Map)((Iterator)localObject2).next();
      List localList1 = getAlList((Map)localObject1, "url");
      List localList2 = getAlList((Map)localObject1, "package");
      List localList3 = getAlList((Map)localObject1, "class");
      List localList4 = getAlList((Map)localObject1, "app_name");
      int j = Math.max(localList1.size(), Math.max(localList2.size(), Math.max(localList3.size(), localList4.size())));
      while (i < j)
      {
        if (localList1.size() > i) {
          localObject1 = ((Map)localList1.get(i)).get("value");
        } else {
          localObject1 = null;
        }
        Uri localUri = tryCreateUrl((String)localObject1);
        if (localList2.size() > i) {
          localObject1 = ((Map)localList2.get(i)).get("value");
        } else {
          localObject1 = null;
        }
        String str1 = (String)localObject1;
        if (localList3.size() > i) {
          localObject1 = ((Map)localList3.get(i)).get("value");
        } else {
          localObject1 = null;
        }
        String str2 = (String)localObject1;
        if (localList4.size() > i) {
          localObject1 = ((Map)localList4.get(i)).get("value");
        } else {
          localObject1 = null;
        }
        localArrayList.add(new AppLink.Target(str1, str2, localUri, (String)localObject1));
        i += 1;
      }
    }
    paramMap = (List)paramMap.get("web");
    if ((paramMap != null) && (paramMap.size() > 0))
    {
      paramMap = (Map)paramMap.get(0);
      localObject2 = (List)paramMap.get("url");
      paramMap = (List)paramMap.get("should_fallback");
      if ((paramMap != null) && (paramMap.size() > 0))
      {
        paramMap = (String)((Map)paramMap.get(0)).get("value");
        if (Arrays.asList(new String[] { "no", "false", "0" }).contains(paramMap.toLowerCase()))
        {
          paramMap = (Map<String, Object>)localObject1;
          break label497;
        }
      }
      paramMap = paramUri;
      label497:
      if ((paramMap != null) && (localObject2 != null) && (((List)localObject2).size() > 0)) {
        paramMap = tryCreateUrl((String)((Map)((List)localObject2).get(0)).get("value"));
      }
    }
    else
    {
      paramMap = paramUri;
    }
    return new AppLink(paramUri, localArrayList, paramMap);
  }
  
  private static Map<String, Object> parseAlData(JSONArray paramJSONArray)
    throws JSONException
  {
    HashMap localHashMap = new HashMap();
    int i = 0;
    while (i < paramJSONArray.length())
    {
      JSONObject localJSONObject = paramJSONArray.getJSONObject(i);
      String[] arrayOfString = localJSONObject.getString("property").split(":");
      if (arrayOfString[0].equals("al"))
      {
        Object localObject1 = localHashMap;
        int j = 1;
        for (;;)
        {
          Object localObject3 = null;
          if (j >= arrayOfString.length) {
            break;
          }
          List localList = (List)((Map)localObject1).get(arrayOfString[j]);
          Object localObject2 = localList;
          if (localList == null)
          {
            localObject2 = new ArrayList();
            ((Map)localObject1).put(arrayOfString[j], localObject2);
          }
          localObject1 = localObject3;
          if (((List)localObject2).size() > 0) {
            localObject1 = (Map)((List)localObject2).get(((List)localObject2).size() - 1);
          }
          if ((localObject1 != null) && (j != arrayOfString.length - 1)) {
            break label185;
          }
          localObject1 = new HashMap();
          ((List)localObject2).add(localObject1);
          label185:
          j += 1;
        }
        if (localJSONObject.has("content")) {
          if (localJSONObject.isNull("content")) {
            ((Map)localObject1).put("value", null);
          } else {
            ((Map)localObject1).put("value", localJSONObject.getString("content"));
          }
        }
      }
      i += 1;
    }
    return localHashMap;
  }
  
  private static String readFromConnection(URLConnection paramURLConnection)
    throws IOException
  {
    if ((paramURLConnection instanceof HttpURLConnection)) {
      localObject = (HttpURLConnection)paramURLConnection;
    }
    try
    {
      localInputStream = paramURLConnection.getInputStream();
    }
    catch (Exception localException)
    {
      for (;;)
      {
        InputStream localInputStream;
        int i;
        continue;
        i += 1;
        continue;
        localObject = paramURLConnection;
        if (paramURLConnection == null) {
          localObject = "UTF-8";
        }
      }
    }
    localInputStream = ((HttpURLConnection)localObject).getErrorStream();
    break label35;
    localInputStream = paramURLConnection.getInputStream();
    try
    {
      label35:
      ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
      localObject = new byte['Ѐ'];
      int j;
      for (;;)
      {
        j = localInputStream.read((byte[])localObject);
        i = 0;
        if (j == -1) {
          break;
        }
        localByteArrayOutputStream.write((byte[])localObject, 0, j);
      }
      String str = paramURLConnection.getContentEncoding();
      localObject = str;
      if (str == null)
      {
        localObject = paramURLConnection.getContentType().split(";");
        j = localObject.length;
        paramURLConnection = str;
        if (i >= j) {
          break label184;
        }
        paramURLConnection = localObject[i].trim();
        if (paramURLConnection.startsWith("charset="))
        {
          paramURLConnection = paramURLConnection.substring("charset=".length());
          break label184;
        }
      }
      else
      {
        paramURLConnection = new String(localByteArrayOutputStream.toByteArray(), (String)localObject);
        return paramURLConnection;
      }
    }
    finally
    {
      localInputStream.close();
    }
  }
  
  private static Uri tryCreateUrl(String paramString)
  {
    if (paramString == null) {
      return null;
    }
    return Uri.parse(paramString);
  }
  
  public Task<AppLink> getAppLinkFromUrlInBackground(final Uri paramUri)
  {
    final Capture localCapture1 = new Capture();
    final Capture localCapture2 = new Capture();
    Task.callInBackground(new Callable()
    {
      public Void call()
        throws Exception
      {
        URL localURL = new URL(paramUri.toString());
        URLConnection localURLConnection = null;
        while (localURL != null)
        {
          localURLConnection = localURL.openConnection();
          boolean bool = localURLConnection instanceof HttpURLConnection;
          if (bool) {
            ((HttpURLConnection)localURLConnection).setInstanceFollowRedirects(true);
          }
          localURLConnection.setRequestProperty("Prefer-Html-Meta-Tags", "al");
          localURLConnection.connect();
          if (bool)
          {
            HttpURLConnection localHttpURLConnection = (HttpURLConnection)localURLConnection;
            if ((localHttpURLConnection.getResponseCode() >= 300) && (localHttpURLConnection.getResponseCode() < 400))
            {
              localURL = new URL(localHttpURLConnection.getHeaderField("Location"));
              localHttpURLConnection.disconnect();
              continue;
            }
          }
          localURL = null;
        }
        try
        {
          localCapture1.set(WebViewAppLinkResolver.readFromConnection(localURLConnection));
          localCapture2.set(localURLConnection.getContentType());
          return null;
        }
        finally
        {
          if ((localURLConnection instanceof HttpURLConnection)) {
            ((HttpURLConnection)localURLConnection).disconnect();
          }
        }
      }
    }).onSuccessTask(new Continuation()
    {
      public Task<JSONArray> then(Task<Void> paramAnonymousTask)
        throws Exception
      {
        final TaskCompletionSource localTaskCompletionSource = new TaskCompletionSource();
        WebView localWebView = new WebView(context);
        localWebView.getSettings().setJavaScriptEnabled(true);
        localWebView.setNetworkAvailable(false);
        localWebView.setWebViewClient(new WebViewClient()
        {
          private boolean loaded = false;
          
          private void runJavaScript(WebView paramAnonymous2WebView)
          {
            if (!loaded)
            {
              loaded = true;
              paramAnonymous2WebView.loadUrl("javascript:boltsWebViewAppLinkResolverResult.setValue((function() {  var metaTags = document.getElementsByTagName('meta');  var results = [];  for (var i = 0; i < metaTags.length; i++) {    var property = metaTags[i].getAttribute('property');    if (property && property.substring(0, 'al:'.length) === 'al:') {      var tag = { \"property\": metaTags[i].getAttribute('property') };      if (metaTags[i].hasAttribute('content')) {        tag['content'] = metaTags[i].getAttribute('content');      }      results.push(tag);    }  }  return JSON.stringify(results);})())");
            }
          }
          
          public void onLoadResource(WebView paramAnonymous2WebView, String paramAnonymous2String)
          {
            super.onLoadResource(paramAnonymous2WebView, paramAnonymous2String);
            runJavaScript(paramAnonymous2WebView);
          }
          
          public void onPageFinished(WebView paramAnonymous2WebView, String paramAnonymous2String)
          {
            super.onPageFinished(paramAnonymous2WebView, paramAnonymous2String);
            runJavaScript(paramAnonymous2WebView);
          }
        });
        localWebView.addJavascriptInterface(new Object()
        {
          @JavascriptInterface
          public void setValue(String paramAnonymous2String)
          {
            try
            {
              localTaskCompletionSource.trySetResult(new JSONArray(paramAnonymous2String));
              return;
            }
            catch (JSONException paramAnonymous2String)
            {
              localTaskCompletionSource.trySetError(paramAnonymous2String);
            }
          }
        }, "boltsWebViewAppLinkResolverResult");
        if (localCapture2.get() != null) {
          paramAnonymousTask = ((String)localCapture2.get()).split(";")[0];
        } else {
          paramAnonymousTask = null;
        }
        localWebView.loadDataWithBaseURL(paramUri.toString(), (String)localCapture1.get(), paramAnonymousTask, null, null);
        return localTaskCompletionSource.getTask();
      }
    }, Task.UI_THREAD_EXECUTOR).onSuccess(new Continuation()
    {
      public AppLink then(Task<JSONArray> paramAnonymousTask)
        throws Exception
      {
        return WebViewAppLinkResolver.makeAppLinkFromAlData(WebViewAppLinkResolver.access$000((JSONArray)paramAnonymousTask.getResult()), paramUri);
      }
    });
  }
}
