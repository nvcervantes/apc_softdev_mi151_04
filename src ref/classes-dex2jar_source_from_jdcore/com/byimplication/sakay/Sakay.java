package com.byimplication.sakay;

import android.content.Context;
import android.net.Uri;
import android.net.Uri.Builder;
import android.util.JsonWriter;
import android.util.Log;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.RetryPolicy;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.byimplication.sakay.model.AnnouncementList;
import com.byimplication.sakay.model.IncidentReportResponse;
import com.byimplication.sakay.model.IncidentType;
import com.byimplication.sakay.model.IssueType;
import com.byimplication.sakay.model.Itinerary;
import com.byimplication.sakay.model.PeliasOsmModel;
import com.byimplication.sakay.model.PhotonOsmModel;
import com.byimplication.sakay.model.Plan;
import com.byimplication.sakay.model.PlanError;
import com.byimplication.sakay.model.PlanResponse;
import com.byimplication.sakay.model.ScheduleType;
import com.byimplication.sakay.model.StopDetails;
import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.KProperty;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONObject;

@Metadata(bv={1, 0, 2}, d1={"\000è\001\n\002\030\002\n\002\020\000\n\002\b\002\n\002\020\016\n\002\b\005\n\002\020\t\n\002\b\005\n\002\030\002\n\002\b\005\n\002\030\002\n\002\b\020\n\002\030\002\n\002\b\002\n\002\030\002\n\002\030\002\n\002\020\002\n\002\b\003\n\002\030\002\n\002\b\002\n\002\020\b\n\000\n\002\030\002\n\000\n\002\030\002\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\007\n\002\030\002\n\000\n\002\020\021\n\002\b\003\n\002\030\002\n\002\030\002\n\002\030\002\n\002\b\005\n\002\030\002\n\000\n\002\030\002\n\002\b\004\n\002\030\002\n\000\n\002\020\013\n\002\b\002\n\002\030\002\n\002\b\007\n\002\030\002\n\000\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\002\n\002\030\002\n\000\n\002\020$\n\002\b\002\n\002\030\002\n\000\n\002\030\002\n\002\b\f\bÆ\002\030\0002\0020\001:\006\001\001\001B\007\b\002¢\006\002\020\002J$\020&\032\0020'2\006\020(\032\0020\n2\024\020)\032\020\022\006\022\004\030\0010+\022\004\022\0020,0*J\030\020-\032\0020'2\006\020.\032\0020\0042\b\020/\032\004\030\00100JP\0201\032\0020'2\006\020.\032\0020\0042\b\020/\032\004\030\001002\006\0202\032\002032\022\0204\032\016\022\004\022\00205\022\004\022\0020,0*2\032\0206\032\026\022\006\022\004\030\00108\022\004\022\0020\004\022\004\022\0020,07J\016\0209\032\0020\0042\006\020:\032\0020;J\020\020<\032\002082\b\020=\032\004\030\0010>J\001\020?\032\0020'2\006\020@\032\002002\b\020A\032\004\030\0010\0042\006\020B\032\002002\b\020C\032\004\030\0010\0042\006\020D\032\0020;2\006\020E\032\0020F2\f\020G\032\b\022\004\022\0020\0040H2\b\020I\032\004\030\001002\006\020J\032\0020\0042$\020K\032 \022\006\022\004\030\0010\n\022\006\022\004\030\0010M\022\006\022\004\030\0010N\022\004\022\0020,0L2\022\0206\032\016\022\004\022\00208\022\004\022\0020,0*¢\006\002\020OJ`\020P\032\0020'2\006\020Q\032\0020\0042\006\020R\032\0020\0042>\020S\032:\022\023\022\0210\004¢\006\f\bT\022\b\bU\022\004\b\b(Q\022\023\022\0210\004¢\006\f\bT\022\b\bU\022\004\b\b(R\022\006\022\004\030\0010V\022\004\022\0020,0L2\b\b\002\020W\032\0020\nJ\016\020X\032\0020\0042\006\020:\032\0020;J\016\020Y\032\0020,2\006\020Z\032\0020[J\006\020\\\032\0020]JF\020^\032\0020'2\006\020_\032\0020`2\006\020a\032\0020\0042\006\020b\032\0020\0042\006\020c\032\002032\036\020d\032\032\022\004\022\0020]\022\004\022\0020\004\022\004\022\0020\004\022\004\022\0020,0LJ4\020e\032\0020'2\006\020f\032\002002\006\020g\032\0020h2\006\020b\032\0020\0042\024\020i\032\020\022\006\022\004\030\0010j\022\004\022\0020,0*J^\020k\032\004\030\0010'2\006\020l\032\0020m2\006\020n\032\002032\006\020b\032\0020\0042\006\020o\032\0020p2\b\020I\032\004\030\001002\026\020q\032\022\022\004\022\0020\004\022\006\022\004\030\0010\004\030\0010r2\022\020s\032\016\022\004\022\0020]\022\004\022\0020,0*J>\020t\032\0020u2\006\020v\032\0020w2\022\020x\032\016\022\004\022\0020]\022\004\022\0020,0*2\032\0206\032\026\022\006\022\004\030\00108\022\004\022\0020\004\022\004\022\0020,07J\037\020y\032\0020,2\b\020z\032\004\030\0010\n2\b\b\002\020{\032\0020\n¢\006\002\020|JM\020}\032\0020'2\006\020~\032\002002!\0204\032\035\022\023\022\02105¢\006\f\bT\022\b\bU\022\004\b\b(\022\004\022\0020,0*2\032\0206\032\026\022\006\022\004\030\00108\022\004\022\0020\004\022\004\022\0020,07R\033\020\003\032\0020\0048FX\002¢\006\f\n\004\b\007\020\b\032\004\b\005\020\006R\032\020\t\032\0020\nX\016¢\006\016\n\000\032\004\b\013\020\f\"\004\b\r\020\016R\034\020\017\032\004\030\0010\020X\016¢\006\016\n\000\032\004\b\021\020\022\"\004\b\023\020\024R\021\020\025\032\0020\026¢\006\b\n\000\032\004\b\027\020\030R\016\020\031\032\0020\004XT¢\006\002\n\000R\033\020\032\032\0020\0048FX\002¢\006\f\n\004\b\034\020\b\032\004\b\033\020\006R\024\020\035\032\0020\004XD¢\006\b\n\000\032\004\b\036\020\006R\024\020\037\032\0020\004XD¢\006\b\n\000\032\004\b \020\006R\033\020!\032\0020\0048FX\002¢\006\f\n\004\b#\020\b\032\004\b\"\020\006R\024\020$\032\0020\004XD¢\006\b\n\000\032\004\b%\020\006¨\006\001"}, d2={"Lcom/byimplication/sakay/Sakay;", "", "()V", "authority", "", "getAuthority", "()Ljava/lang/String;", "authority$delegate", "Lkotlin/Lazy;", "lastSearchTime", "", "getLastSearchTime", "()J", "setLastSearchTime", "(J)V", "queue", "Lcom/android/volley/RequestQueue;", "getQueue", "()Lcom/android/volley/RequestQueue;", "setQueue", "(Lcom/android/volley/RequestQueue;)V", "reportIssueIsContactingServer", "Ljava/util/concurrent/atomic/AtomicBoolean;", "getReportIssueIsContactingServer", "()Ljava/util/concurrent/atomic/AtomicBoolean;", "sakayPeliasUrl", "sakayPhotonUrl", "getSakayPhotonUrl", "sakayPhotonUrl$delegate", "sampleUUID", "getSampleUUID", "scheme", "getScheme", "unstableAuthority", "getUnstableAuthority", "unstableAuthority$delegate", "version", "getVersion", "getAnnouncements", "Lcom/byimplication/sakay/Sakay$SakayRequest;", "since", "announceCallback", "Lkotlin/Function1;", "Lcom/byimplication/sakay/model/AnnouncementList;", "", "getAutocompleteFromSakayPelias", "constraint", "currentLocation", "Lcom/google/android/gms/maps/model/LatLng;", "getAutocompleteFromSakayPhoton", "searchLimit", "", "callback", "Lcom/byimplication/sakay/model/PhotonOsmModel;", "errorCallback", "Lkotlin/Function2;", "Lcom/byimplication/sakay/Sakay$NetworkErrorType;", "getDate", "date", "Ljava/util/Date;", "getNetworkErrorType", "error", "Lcom/android/volley/VolleyError;", "getPlan", "from", "fromName", "to", "toName", "timePoint", "scheduleType", "Lcom/byimplication/sakay/model/ScheduleType;", "modePrefs", "", "currentLoc", "userToken", "planRequestCallback", "Lkotlin/Function3;", "Lcom/byimplication/sakay/model/Plan;", "Lcom/byimplication/sakay/model/PlanError;", "(Lcom/google/android/gms/maps/model/LatLng;Ljava/lang/String;Lcom/google/android/gms/maps/model/LatLng;Ljava/lang/String;Ljava/util/Date;Lcom/byimplication/sakay/model/ScheduleType;[Ljava/lang/String;Lcom/google/android/gms/maps/model/LatLng;Ljava/lang/String;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function1;)Lcom/byimplication/sakay/Sakay$SakayRequest;", "getStopDetails", "tripId", "stopId", "callBack", "Lkotlin/ParameterName;", "name", "Lcom/byimplication/sakay/model/StopDetails;", "timestamp", "getTime", "init", "context", "Landroid/content/Context;", "isQueueNull", "", "reportError", "errorType", "Lcom/byimplication/sakay/ErrorType;", "route", "desc", "leg", "errorReportCallBack", "reportIncident", "loc", "reportType", "Lcom/byimplication/sakay/model/IncidentType;", "incidentCallback", "Lcom/byimplication/sakay/model/IncidentReportResponse;", "reportIssue", "itinerary", "Lcom/byimplication/sakay/model/Itinerary;", "tripLegNum", "issueType", "Lcom/byimplication/sakay/model/IssueType;", "structuredData", "", "reportIssueCallback", "reportOfflineSearches", "Lcom/byimplication/sakay/Sakay$SakayJsonObjectRequest;", "offlineSearchEntries", "Lorg/json/JSONArray;", "reportCallback", "reportSearch", "searchId", "responseTime", "(Ljava/lang/Long;J)V", "reverseGeocodeFromSakayPhoton", "location", "photonModel", "NetworkErrorType", "SakayJsonObjectRequest", "SakayRequest", "app_release"}, k=1, mv={1, 1, 9})
public final class Sakay
{
  public static final Sakay INSTANCE = new Sakay();
  @NotNull
  private static final Lazy authority$delegate = LazyKt.lazy((Function0)authority.2.INSTANCE);
  private static long lastSearchTime = 0L;
  @Nullable
  private static RequestQueue queue;
  @NotNull
  private static final AtomicBoolean reportIssueIsContactingServer = new AtomicBoolean(false);
  @NotNull
  public static final String sakayPeliasUrl = "pelias.byimplication.com:4000";
  @NotNull
  private static final Lazy sakayPhotonUrl$delegate;
  @NotNull
  private static final String sampleUUID = "123e4567-e89b-12d3-a456-426655440000";
  @NotNull
  private static final String scheme = "https";
  @NotNull
  private static final Lazy unstableAuthority$delegate = LazyKt.lazy((Function0)unstableAuthority.2.INSTANCE);
  @NotNull
  private static final String version = String.valueOf(300015);
  
  static
  {
    sakayPhotonUrl$delegate = LazyKt.lazy((Function0)sakayPhotonUrl.2.INSTANCE);
  }
  
  private Sakay() {}
  
  @NotNull
  public final SakayRequest getAnnouncements(long paramLong, @NotNull Function1<? super AnnouncementList, Unit> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramFunction1, "announceCallback");
    Object localObject = new Uri.Builder();
    ((Uri.Builder)localObject).authority(getAuthority());
    ((Uri.Builder)localObject).scheme(scheme);
    ((Uri.Builder)localObject).appendPath("api");
    ((Uri.Builder)localObject).appendPath("announcements");
    ((Uri.Builder)localObject).appendQueryParameter("since", String.valueOf(paramLong));
    localObject = ((Uri.Builder)localObject).build().toString();
    Intrinsics.checkExpressionValueIsNotNull(localObject, "url");
    paramFunction1 = new SakayRequest(0, (String)localObject, (Response.Listener)new getAnnouncements.ResponseListener(paramFunction1), (Response.ErrorListener)new getAnnouncements.ErrorListener());
    localObject = queue;
    if (localObject != null) {
      ((RequestQueue)localObject).add((Request)paramFunction1);
    }
    return paramFunction1;
  }
  
  @NotNull
  public final String getAuthority()
  {
    Lazy localLazy = authority$delegate;
    KProperty localKProperty = $$delegatedProperties[0];
    return (String)localLazy.getValue();
  }
  
  @NotNull
  public final SakayRequest getAutocompleteFromSakayPelias(@NotNull String paramString, @Nullable LatLng paramLatLng)
  {
    Intrinsics.checkParameterIsNotNull(paramString, "constraint");
    Uri.Builder localBuilder = new Uri.Builder();
    localBuilder.encodedAuthority("pelias.byimplication.com:4000");
    localBuilder.scheme("http");
    localBuilder.appendPath("v1");
    localBuilder.appendPath("autocomplete");
    localBuilder.appendQueryParameter("text", paramString);
    if (paramLatLng != null)
    {
      paramString = new Object[1];
      paramString[0] = Double.valueOf(latitude);
      paramString = String.format("%.6f", Arrays.copyOf(paramString, paramString.length));
      Intrinsics.checkExpressionValueIsNotNull(paramString, "java.lang.String.format(this, *args)");
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = Double.valueOf(longitude);
      paramLatLng = String.format("%.6f", Arrays.copyOf(arrayOfObject, arrayOfObject.length));
      Intrinsics.checkExpressionValueIsNotNull(paramLatLng, "java.lang.String.format(this, *args)");
      localBuilder.appendQueryParameter("focus.point.lat", paramString);
      localBuilder.appendQueryParameter("focus.point.lon", paramLatLng);
    }
    paramString = localBuilder.build().toString();
    Intrinsics.checkExpressionValueIsNotNull(paramString, "url");
    paramString = new SakayRequest(0, paramString, (Response.Listener)new getAutocompleteFromSakayPelias.ResponseListener(), (Response.ErrorListener)new getAutocompleteFromSakayPelias.ErrorListener());
    paramLatLng = queue;
    if (paramLatLng != null) {
      paramLatLng.add((Request)paramString);
    }
    return paramString;
  }
  
  @NotNull
  public final SakayRequest getAutocompleteFromSakayPhoton(@NotNull String paramString, @Nullable LatLng paramLatLng, int paramInt, @NotNull Function1<? super PhotonOsmModel, Unit> paramFunction1, @NotNull final Function2<? super NetworkErrorType, ? super String, Unit> paramFunction2)
  {
    Intrinsics.checkParameterIsNotNull(paramString, "constraint");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "callback");
    Intrinsics.checkParameterIsNotNull(paramFunction2, "errorCallback");
    Uri.Builder localBuilder = new Uri.Builder();
    localBuilder.encodedAuthority(getSakayPhotonUrl());
    localBuilder.scheme("https");
    localBuilder.appendPath("api");
    localBuilder.appendQueryParameter("q", paramString);
    if (paramLatLng != null)
    {
      paramString = new Object[1];
      paramString[0] = Double.valueOf(latitude);
      paramString = String.format("%.6f", Arrays.copyOf(paramString, paramString.length));
      Intrinsics.checkExpressionValueIsNotNull(paramString, "java.lang.String.format(this, *args)");
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = Double.valueOf(longitude);
      paramLatLng = String.format("%.6f", Arrays.copyOf(arrayOfObject, arrayOfObject.length));
      Intrinsics.checkExpressionValueIsNotNull(paramLatLng, "java.lang.String.format(this, *args)");
      localBuilder.appendQueryParameter("lat", paramString);
      localBuilder.appendQueryParameter("lon", paramLatLng);
    }
    localBuilder.appendQueryParameter("limit", String.valueOf(paramInt));
    paramString = localBuilder.build().toString();
    Intrinsics.checkExpressionValueIsNotNull(paramString, "url");
    paramString = new SakayRequest(0, paramString, (Response.Listener)new getAutocompleteFromSakayPhoton.ResponseListener(paramFunction1, paramFunction2), (Response.ErrorListener)new getAutocompleteFromSakayPhoton.ErrorListener(paramFunction2));
    paramLatLng = queue;
    if (paramLatLng != null) {
      paramLatLng.add((Request)paramString);
    }
    return paramString;
  }
  
  @NotNull
  public final String getDate(@NotNull Date paramDate)
  {
    Intrinsics.checkParameterIsNotNull(paramDate, "date");
    paramDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(paramDate);
    Intrinsics.checkExpressionValueIsNotNull(paramDate, "sdf.format(date)");
    return paramDate;
  }
  
  public final long getLastSearchTime()
  {
    return lastSearchTime;
  }
  
  @NotNull
  public final NetworkErrorType getNetworkErrorType(@Nullable VolleyError paramVolleyError)
  {
    if ((paramVolleyError instanceof AuthFailureError)) {
      return NetworkErrorType.AUTH_FAIL;
    }
    if ((paramVolleyError instanceof NoConnectionError)) {
      return NetworkErrorType.NO_CONNECTION;
    }
    if ((paramVolleyError instanceof ServerError)) {
      return NetworkErrorType.SERVER_ERROR;
    }
    if ((paramVolleyError instanceof TimeoutError)) {
      return NetworkErrorType.TIMEOUT_ERROR;
    }
    return NetworkErrorType.UNKNOWN_ERROR;
  }
  
  @NotNull
  public final SakayRequest getPlan(@NotNull LatLng paramLatLng1, @Nullable String paramString1, @NotNull LatLng paramLatLng2, @Nullable String paramString2, @NotNull Date paramDate, @NotNull ScheduleType paramScheduleType, @NotNull String[] paramArrayOfString, @Nullable LatLng paramLatLng3, @NotNull String paramString3, @NotNull Function3<? super Long, ? super Plan, ? super PlanError, Unit> paramFunction3, @NotNull Function1<? super NetworkErrorType, Unit> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramLatLng1, "from");
    Intrinsics.checkParameterIsNotNull(paramLatLng2, "to");
    Intrinsics.checkParameterIsNotNull(paramDate, "timePoint");
    Intrinsics.checkParameterIsNotNull(paramScheduleType, "scheduleType");
    Intrinsics.checkParameterIsNotNull(paramArrayOfString, "modePrefs");
    Intrinsics.checkParameterIsNotNull(paramString3, "userToken");
    Intrinsics.checkParameterIsNotNull(paramFunction3, "planRequestCallback");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "errorCallback");
    Log.d("SAKAY", "Sakay::getPlan");
    Object localObject1 = new StringBuilder();
    Object localObject2 = new Object[1];
    localObject2[0] = Double.valueOf(latitude);
    localObject2 = String.format("%.6f", Arrays.copyOf((Object[])localObject2, localObject2.length));
    Intrinsics.checkExpressionValueIsNotNull(localObject2, "java.lang.String.format(this, *args)");
    ((StringBuilder)localObject1).append((String)localObject2);
    ((StringBuilder)localObject1).append(",");
    localObject2 = new Object[1];
    localObject2[0] = Double.valueOf(longitude);
    paramLatLng1 = String.format("%1.6f", Arrays.copyOf((Object[])localObject2, localObject2.length));
    Intrinsics.checkExpressionValueIsNotNull(paramLatLng1, "java.lang.String.format(this, *args)");
    ((StringBuilder)localObject1).append(paramLatLng1);
    paramLatLng1 = ((StringBuilder)localObject1).toString();
    localObject1 = new StringBuilder();
    localObject2 = new Object[1];
    localObject2[0] = Double.valueOf(latitude);
    localObject2 = String.format("%.6f", Arrays.copyOf((Object[])localObject2, localObject2.length));
    Intrinsics.checkExpressionValueIsNotNull(localObject2, "java.lang.String.format(this, *args)");
    ((StringBuilder)localObject1).append((String)localObject2);
    ((StringBuilder)localObject1).append(",");
    localObject2 = new Object[1];
    localObject2[0] = Double.valueOf(longitude);
    paramLatLng2 = String.format("%1.6f", Arrays.copyOf((Object[])localObject2, localObject2.length));
    Intrinsics.checkExpressionValueIsNotNull(paramLatLng2, "java.lang.String.format(this, *args)");
    ((StringBuilder)localObject1).append(paramLatLng2);
    localObject1 = ((StringBuilder)localObject1).toString();
    localObject2 = getDate(paramDate);
    paramDate = getTime(paramDate);
    boolean bool = Intrinsics.areEqual(paramScheduleType, ScheduleType.ARRIVAL);
    paramLatLng2 = new Uri.Builder();
    paramLatLng2.scheme(scheme);
    paramLatLng2.authority(getAuthority());
    paramLatLng2.appendPath("api");
    paramLatLng2.appendPath("plan");
    paramLatLng2.appendQueryParameter("fromPlace", paramLatLng1);
    paramLatLng2.appendQueryParameter("fromName", paramString1);
    paramLatLng2.appendQueryParameter("toPlace", (String)localObject1);
    paramLatLng2.appendQueryParameter("toName", paramString2);
    paramLatLng2.appendQueryParameter("date", (String)localObject2);
    paramLatLng2.appendQueryParameter("time", paramDate);
    paramLatLng2.appendQueryParameter("arriveBy", String.valueOf(bool));
    paramString1 = (Object[])paramArrayOfString;
    int i;
    if (paramString1.length == 0) {
      i = 1;
    } else {
      i = 0;
    }
    if (((i ^ 0x1) != 0) && (paramString1.length < 7))
    {
      paramLatLng1 = paramArrayOfString[0];
      int j = paramString1.length;
      i = 1;
      while (i < j)
      {
        paramString1 = new StringBuilder();
        paramString1.append(paramLatLng1);
        paramString1.append(",");
        paramString1.append(paramArrayOfString[i]);
        paramLatLng1 = paramString1.toString();
        i += 1;
      }
      paramString1 = new StringBuilder();
      paramString1.append("Sakay::getPlan ");
      paramString1.append(paramLatLng1.toString());
      Log.d("SAKAY", paramString1.toString());
      paramLatLng2.appendQueryParameter("preference", paramLatLng1);
    }
    if (paramLatLng3 != null)
    {
      paramLatLng1 = new StringBuilder();
      paramString1 = new Object[1];
      paramString1[0] = Double.valueOf(latitude);
      paramString1 = String.format("%.6f", Arrays.copyOf(paramString1, paramString1.length));
      Intrinsics.checkExpressionValueIsNotNull(paramString1, "java.lang.String.format(this, *args)");
      paramLatLng1.append(paramString1);
      paramLatLng1.append(",");
      paramString1 = new Object[1];
      paramString1[0] = Double.valueOf(longitude);
      paramString1 = String.format("%1.6f", Arrays.copyOf(paramString1, paramString1.length));
      Intrinsics.checkExpressionValueIsNotNull(paramString1, "java.lang.String.format(this, *args)");
      paramLatLng1.append(paramString1);
      paramLatLng2.appendQueryParameter("currentPlace", paramLatLng1.toString());
    }
    paramLatLng2.appendQueryParameter("userToken", paramString3);
    paramLatLng2.appendQueryParameter("version", version);
    paramLatLng1 = paramLatLng2.build().toString();
    paramString1 = new StringBuilder();
    paramString1.append("Sakay::getPlan ");
    paramString1.append(paramLatLng1);
    Log.d("SAKAY", paramString1.toString());
    Intrinsics.checkExpressionValueIsNotNull(paramLatLng1, "url");
    paramLatLng1 = new SakayRequest(0, paramLatLng1, (Response.Listener)new getPlan.ResponseListener(paramFunction3), (Response.ErrorListener)new getPlan.ErrorListener(paramFunction1));
    paramLatLng1.setRetryPolicy((RetryPolicy)new DefaultRetryPolicy(25000, 1, 1.0F));
    paramString1 = queue;
    if (paramString1 != null) {
      paramString1.add((Request)paramLatLng1);
    }
    lastSearchTime = System.currentTimeMillis();
    return paramLatLng1;
  }
  
  @Nullable
  public final RequestQueue getQueue()
  {
    return queue;
  }
  
  @NotNull
  public final AtomicBoolean getReportIssueIsContactingServer()
  {
    return reportIssueIsContactingServer;
  }
  
  @NotNull
  public final String getSakayPhotonUrl()
  {
    Lazy localLazy = sakayPhotonUrl$delegate;
    KProperty localKProperty = $$delegatedProperties[2];
    return (String)localLazy.getValue();
  }
  
  @NotNull
  public final String getSampleUUID()
  {
    return sampleUUID;
  }
  
  @NotNull
  public final String getScheme()
  {
    return scheme;
  }
  
  @NotNull
  public final SakayRequest getStopDetails(@NotNull final String paramString1, @NotNull final String paramString2, @NotNull Function3<? super String, ? super String, ? super StopDetails, Unit> paramFunction3, long paramLong)
  {
    Intrinsics.checkParameterIsNotNull(paramString1, "tripId");
    Intrinsics.checkParameterIsNotNull(paramString2, "stopId");
    Intrinsics.checkParameterIsNotNull(paramFunction3, "callBack");
    Object localObject = new Uri.Builder();
    ((Uri.Builder)localObject).authority("sakay.ph");
    ((Uri.Builder)localObject).scheme(scheme);
    ((Uri.Builder)localObject).appendPath("api");
    ((Uri.Builder)localObject).appendPath("info");
    ((Uri.Builder)localObject).appendPath("stop");
    ((Uri.Builder)localObject).appendQueryParameter("trip_id", paramString1);
    ((Uri.Builder)localObject).appendQueryParameter("stop_id", paramString2);
    ((Uri.Builder)localObject).appendQueryParameter("timestamp", String.valueOf(paramLong));
    localObject = ((Uri.Builder)localObject).build().toString();
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Sakay::getStopDetails ResponseListener::onResponse url: ");
    localStringBuilder.append((String)localObject);
    Log.d("SAKAY", localStringBuilder.toString());
    Intrinsics.checkExpressionValueIsNotNull(localObject, "url");
    paramString1 = new SakayRequest(0, (String)localObject, (Response.Listener)new getStopDetails.ResponseListener(paramFunction3, paramString1, paramString2), (Response.ErrorListener)new getStopDetails.ErrorListener(paramFunction3, paramString1, paramString2));
    paramString2 = queue;
    if (paramString2 != null) {
      paramString2.add((Request)paramString1);
    }
    return paramString1;
  }
  
  @NotNull
  public final String getTime(@NotNull Date paramDate)
  {
    Intrinsics.checkParameterIsNotNull(paramDate, "date");
    paramDate = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(paramDate);
    Intrinsics.checkExpressionValueIsNotNull(paramDate, "sdf.format(date)");
    return paramDate;
  }
  
  @NotNull
  public final String getUnstableAuthority()
  {
    Lazy localLazy = unstableAuthority$delegate;
    KProperty localKProperty = $$delegatedProperties[1];
    return (String)localLazy.getValue();
  }
  
  @NotNull
  public final String getVersion()
  {
    return version;
  }
  
  public final void init(@NotNull Context paramContext)
  {
    Intrinsics.checkParameterIsNotNull(paramContext, "context");
    queue = Volley.newRequestQueue(paramContext);
  }
  
  public final boolean isQueueNull()
  {
    return queue == null;
  }
  
  @NotNull
  public final SakayRequest reportError(@NotNull ErrorType paramErrorType, @NotNull String paramString1, @NotNull String paramString2, int paramInt, @NotNull Function3<? super Boolean, ? super String, ? super String, Unit> paramFunction3)
  {
    Intrinsics.checkParameterIsNotNull(paramErrorType, "errorType");
    Intrinsics.checkParameterIsNotNull(paramString1, "route");
    Intrinsics.checkParameterIsNotNull(paramString2, "desc");
    Intrinsics.checkParameterIsNotNull(paramFunction3, "errorReportCallBack");
    Log.d("SAKAY", "Sakay::reportError");
    Object localObject = new Uri.Builder();
    ((Uri.Builder)localObject).authority(getAuthority());
    ((Uri.Builder)localObject).scheme(scheme);
    ((Uri.Builder)localObject).appendPath("api");
    ((Uri.Builder)localObject).appendPath("errors");
    localObject = ((Uri.Builder)localObject).build().toString();
    switch (Sakay.WhenMappings.$EnumSwitchMapping$0[paramErrorType.ordinal()])
    {
    default: 
      paramErrorType = "OTHER";
      break;
    case 4: 
      paramErrorType = "INCORRECT NAME";
      break;
    case 3: 
      paramErrorType = "INCORRECT FARE";
      break;
    case 2: 
      paramErrorType = "INCORRECT STOP";
      break;
    case 1: 
      paramErrorType = "INCORRECT ROUTE";
    }
    Intrinsics.checkExpressionValueIsNotNull(localObject, "url");
    paramFunction3 = new SakayRequest(1, (String)localObject, (Response.Listener)new reportError.ResponseListener(paramFunction3), (Response.ErrorListener)new reportError.ErrorListener(paramFunction3));
    paramFunction3.getPostParams().put("category", paramErrorType);
    paramFunction3.getPostParams().put("route", paramString1);
    paramFunction3.getPostParams().put("desc", paramString2);
    paramFunction3.getPostParams().put("leg", String.valueOf(paramInt));
    paramErrorType = queue;
    if (paramErrorType != null) {
      paramErrorType.add((Request)paramFunction3);
    }
    return paramFunction3;
  }
  
  @NotNull
  public final SakayRequest reportIncident(@NotNull LatLng paramLatLng, @NotNull IncidentType paramIncidentType, @NotNull String paramString, @NotNull Function1<? super IncidentReportResponse, Unit> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramLatLng, "loc");
    Intrinsics.checkParameterIsNotNull(paramIncidentType, "reportType");
    Intrinsics.checkParameterIsNotNull(paramString, "desc");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "incidentCallback");
    Object localObject = new Uri.Builder();
    ((Uri.Builder)localObject).authority(getAuthority());
    ((Uri.Builder)localObject).scheme(scheme);
    ((Uri.Builder)localObject).appendPath("api");
    ((Uri.Builder)localObject).appendPath("incidents");
    localObject = ((Uri.Builder)localObject).build().toString();
    Intrinsics.checkExpressionValueIsNotNull(localObject, "builder.build().toString()");
    paramFunction1 = new SakayRequest(1, (String)localObject, (Response.Listener)new reportIncident.ResponseListener(paramFunction1), (Response.ErrorListener)new reportIncident.ErrorListener());
    paramFunction1.getPostParams().put("lat", String.valueOf(latitude));
    paramFunction1.getPostParams().put("lon", String.valueOf(longitude));
    paramFunction1.getPostParams().put("type", paramIncidentType.toString());
    paramFunction1.getPostParams().put("token", sampleUUID);
    paramFunction1.getPostParams().put("desc", paramString);
    paramLatLng = queue;
    if (paramLatLng != null) {
      paramLatLng.add((Request)paramFunction1);
    }
    return paramFunction1;
  }
  
  @Nullable
  public final SakayRequest reportIssue(@NotNull Itinerary paramItinerary, int paramInt, @NotNull String paramString, @NotNull IssueType paramIssueType, @Nullable LatLng paramLatLng, @Nullable Map<String, String> paramMap, @NotNull Function1<? super Boolean, Unit> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramItinerary, "itinerary");
    Intrinsics.checkParameterIsNotNull(paramString, "desc");
    Intrinsics.checkParameterIsNotNull(paramIssueType, "issueType");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "reportIssueCallback");
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append("Sakay::reportIssue Debouncing: ");
    ((StringBuilder)localObject).append(reportIssueIsContactingServer.get());
    Log.d("SAKAY", ((StringBuilder)localObject).toString());
    if (!reportIssueIsContactingServer.get())
    {
      Log.d("SAKAY", "Sakay::reportIssue is in");
      localObject = new Uri.Builder();
      ((Uri.Builder)localObject).authority(getAuthority());
      ((Uri.Builder)localObject).scheme(scheme);
      ((Uri.Builder)localObject).appendPath("api");
      ((Uri.Builder)localObject).appendPath("feedback");
      localObject = ((Uri.Builder)localObject).build().toString();
      Intrinsics.checkExpressionValueIsNotNull(localObject, "url");
      paramFunction1 = new SakayRequest(1, (String)localObject, (Response.Listener)new reportIssue.ResponseListener(paramFunction1), (Response.ErrorListener)new reportIssue.ErrorListener(paramFunction1));
      localObject = new ByteArrayOutputStream();
      JsonWriter localJsonWriter = new JsonWriter((Writer)new OutputStreamWriter((OutputStream)localObject, "UTF-8"));
      LocalStorage.INSTANCE.writeItinerary(localJsonWriter, paramItinerary);
      localJsonWriter.close();
      paramItinerary = (Map)paramFunction1.getPostParams();
      localObject = ((ByteArrayOutputStream)localObject).toString();
      Intrinsics.checkExpressionValueIsNotNull(localObject, "baoStream.toString()");
      paramItinerary.put("route", localObject);
      ((Map)paramFunction1.getPostParams()).put("desc", paramString);
      ((Map)paramFunction1.getPostParams()).put("leg", String.valueOf(paramInt));
      ((Map)paramFunction1.getPostParams()).put("category", paramIssueType.toString());
      if (paramLatLng != null)
      {
        ((Map)paramFunction1.getPostParams()).put("lat", String.valueOf(latitude));
        ((Map)paramFunction1.getPostParams()).put("lon", String.valueOf(longitude));
      }
      if (paramMap == null) {}
    }
    try
    {
      paramItinerary = (Map)paramFunction1.getPostParams();
      paramString = new JSONObject(paramMap).toString();
      Intrinsics.checkExpressionValueIsNotNull(paramString, "JSONObject(structuredData).toString()");
      paramItinerary.put("structured_data", paramString);
    }
    catch (Exception paramItinerary)
    {
      for (;;) {}
    }
    Log.e("SAKAY", "Sakay::reportIssue structuredData has error parsing");
    ((Map)paramFunction1.getPostParams()).put("client_version", String.valueOf(300015));
    ((Map)paramFunction1.getPostParams()).put("client_platform", "ANDROID");
    reportIssueIsContactingServer.set(true);
    paramItinerary = queue;
    if (paramItinerary != null) {
      paramItinerary.add((Request)paramFunction1);
    }
    return paramFunction1;
    return null;
  }
  
  @NotNull
  public final SakayJsonObjectRequest reportOfflineSearches(@NotNull JSONArray paramJSONArray, @NotNull Function1<? super Boolean, Unit> paramFunction1, @NotNull Function2<? super NetworkErrorType, ? super String, Unit> paramFunction2)
  {
    Intrinsics.checkParameterIsNotNull(paramJSONArray, "offlineSearchEntries");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "reportCallback");
    Intrinsics.checkParameterIsNotNull(paramFunction2, "errorCallback");
    Object localObject = new Uri.Builder();
    ((Uri.Builder)localObject).authority(getAuthority());
    ((Uri.Builder)localObject).scheme("https");
    ((Uri.Builder)localObject).appendPath("api");
    ((Uri.Builder)localObject).appendPath("searches-offline");
    localObject = ((Uri.Builder)localObject).build().toString();
    paramFunction1 = (Response.Listener)new Response.Listener()
    {
      public final void onResponse(@Nullable JSONObject paramAnonymousJSONObject)
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("Sakay::reportOfflineSearches::Response.Listener ");
        String str;
        if (paramAnonymousJSONObject != null) {
          str = paramAnonymousJSONObject.toString();
        } else {
          str = null;
        }
        localStringBuilder.append(str);
        Log.d("SAKAY", localStringBuilder.toString());
        if (paramAnonymousJSONObject != null) {}
        for (;;)
        {
          try
          {
            bool = paramAnonymousJSONObject.optBoolean("success");
            $reportCallback.invoke(Boolean.valueOf(bool));
            return;
          }
          catch (Exception paramAnonymousJSONObject)
          {
            continue;
          }
          $reportCallback.invoke(Boolean.valueOf(false));
          return;
          boolean bool = false;
        }
      }
    };
    paramFunction2 = (Response.ErrorListener)new Response.ErrorListener()
    {
      public final void onErrorResponse(VolleyError paramAnonymousVolleyError)
      {
        Object localObject3 = new StringBuilder();
        ((StringBuilder)localObject3).append("Sakay::reportOfflineSearches::Response.ErrorListener ");
        Object localObject2 = null;
        if (paramAnonymousVolleyError != null) {
          localObject1 = paramAnonymousVolleyError.getMessage();
        } else {
          localObject1 = null;
        }
        ((StringBuilder)localObject3).append((String)localObject1);
        Log.d("SAKAY", ((StringBuilder)localObject3).toString());
        localObject3 = $errorCallback;
        Sakay.NetworkErrorType localNetworkErrorType = Sakay.INSTANCE.getNetworkErrorType(paramAnonymousVolleyError);
        Object localObject1 = localObject2;
        if (paramAnonymousVolleyError != null) {
          localObject1 = paramAnonymousVolleyError.getMessage();
        }
        if (localObject1 == null) {
          localObject1 = "";
        }
        ((Function2)localObject3).invoke(localNetworkErrorType, localObject1);
      }
    };
    paramJSONArray = paramJSONArray.toString();
    Intrinsics.checkExpressionValueIsNotNull(paramJSONArray, "offlineSearchEntries.toString()");
    paramJSONArray = new JSONArray(StringsKt.replace$default(paramJSONArray, "\"searchId\"", "\"id\"", false, 4, null));
    Intrinsics.checkExpressionValueIsNotNull(localObject, "url");
    paramJSONArray = paramJSONArray.toString();
    Intrinsics.checkExpressionValueIsNotNull(paramJSONArray, "jsonArray.toString()");
    paramJSONArray = new SakayJsonObjectRequest(1, (String)localObject, paramJSONArray, paramFunction1, paramFunction2);
    paramFunction1 = queue;
    if (paramFunction1 != null) {
      paramFunction1.add((Request)paramJSONArray);
    }
    return paramJSONArray;
  }
  
  public final void reportSearch(@Nullable Long paramLong, long paramLong1)
  {
    if (paramLong != null)
    {
      Object localObject = new Uri.Builder();
      ((Uri.Builder)localObject).authority(getAuthority());
      ((Uri.Builder)localObject).scheme(scheme);
      ((Uri.Builder)localObject).appendPath("api");
      ((Uri.Builder)localObject).appendPath("searches");
      ((Uri.Builder)localObject).appendPath(String.valueOf(paramLong.longValue()));
      paramLong = ((Uri.Builder)localObject).build().toString();
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("Sakay::reportSearch url: ");
      ((StringBuilder)localObject).append(paramLong);
      Log.d("SAKAY", ((StringBuilder)localObject).toString());
      Intrinsics.checkExpressionValueIsNotNull(paramLong, "url");
      paramLong = new SakayRequest(2, paramLong, (Response.Listener)new reportSearch.ResponseListener(), (Response.ErrorListener)new reportSearch.ErrorListener());
      paramLong.getPostParams().put("responseTime", String.valueOf(paramLong1));
      localObject = queue;
      if (localObject != null) {
        ((RequestQueue)localObject).add((Request)paramLong);
      }
    }
  }
  
  @NotNull
  public final SakayRequest reverseGeocodeFromSakayPhoton(@NotNull LatLng paramLatLng, @NotNull Function1<? super PhotonOsmModel, Unit> paramFunction1, @NotNull final Function2<? super NetworkErrorType, ? super String, Unit> paramFunction2)
  {
    Intrinsics.checkParameterIsNotNull(paramLatLng, "location");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "callback");
    Intrinsics.checkParameterIsNotNull(paramFunction2, "errorCallback");
    Uri.Builder localBuilder = new Uri.Builder();
    localBuilder.encodedAuthority(getSakayPhotonUrl());
    localBuilder.scheme("https");
    localBuilder.appendPath("reverse");
    Object localObject = new Object[1];
    localObject[0] = Double.valueOf(latitude);
    localObject = String.format("%.6f", Arrays.copyOf((Object[])localObject, localObject.length));
    Intrinsics.checkExpressionValueIsNotNull(localObject, "java.lang.String.format(this, *args)");
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = Double.valueOf(longitude);
    paramLatLng = String.format("%.6f", Arrays.copyOf(arrayOfObject, arrayOfObject.length));
    Intrinsics.checkExpressionValueIsNotNull(paramLatLng, "java.lang.String.format(this, *args)");
    localBuilder.appendQueryParameter("lat", (String)localObject);
    localBuilder.appendQueryParameter("lon", paramLatLng);
    localBuilder.appendQueryParameter("distance_sort", "true");
    localBuilder.appendQueryParameter("must_not", "osm_value:(milestone OR stream OR river OR artwork) osm_key:(boundary OR amenity OR shop)");
    paramLatLng = localBuilder.build().toString();
    Intrinsics.checkExpressionValueIsNotNull(paramLatLng, "url");
    paramLatLng = new SakayRequest(0, paramLatLng, (Response.Listener)new reverseGeocodeFromSakayPhoton.ResponseListener(paramFunction1, paramFunction2), (Response.ErrorListener)new reverseGeocodeFromSakayPhoton.ErrorListener(paramFunction2));
    paramFunction1 = queue;
    if (paramFunction1 != null) {
      paramFunction1.add((Request)paramLatLng);
    }
    return paramLatLng;
  }
  
  public final void setLastSearchTime(long paramLong)
  {
    lastSearchTime = paramLong;
  }
  
  public final void setQueue(@Nullable RequestQueue paramRequestQueue)
  {
    queue = paramRequestQueue;
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\000\f\n\002\030\002\n\002\020\020\n\002\b\007\b\001\030\0002\b\022\004\022\0020\0000\001B\007\b\002¢\006\002\020\002j\002\b\003j\002\b\004j\002\b\005j\002\b\006j\002\b\007¨\006\b"}, d2={"Lcom/byimplication/sakay/Sakay$NetworkErrorType;", "", "(Ljava/lang/String;I)V", "AUTH_FAIL", "NO_CONNECTION", "SERVER_ERROR", "TIMEOUT_ERROR", "UNKNOWN_ERROR", "app_release"}, k=1, mv={1, 1, 9})
  public static enum NetworkErrorType
  {
    static
    {
      NetworkErrorType localNetworkErrorType1 = new NetworkErrorType("AUTH_FAIL", 0);
      AUTH_FAIL = localNetworkErrorType1;
      NetworkErrorType localNetworkErrorType2 = new NetworkErrorType("NO_CONNECTION", 1);
      NO_CONNECTION = localNetworkErrorType2;
      NetworkErrorType localNetworkErrorType3 = new NetworkErrorType("SERVER_ERROR", 2);
      SERVER_ERROR = localNetworkErrorType3;
      NetworkErrorType localNetworkErrorType4 = new NetworkErrorType("TIMEOUT_ERROR", 3);
      TIMEOUT_ERROR = localNetworkErrorType4;
      NetworkErrorType localNetworkErrorType5 = new NetworkErrorType("UNKNOWN_ERROR", 4);
      UNKNOWN_ERROR = localNetworkErrorType5;
      $VALUES = new NetworkErrorType[] { localNetworkErrorType1, localNetworkErrorType2, localNetworkErrorType3, localNetworkErrorType4, localNetworkErrorType5 };
    }
    
    protected NetworkErrorType() {}
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\000F\n\002\030\002\n\002\030\002\n\002\030\002\n\000\n\002\020\b\n\000\n\002\020\016\n\002\b\002\n\002\030\002\n\000\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\004\n\002\020%\n\002\b\002\n\002\030\002\n\000\n\002\030\002\n\000\030\0002\n\022\006\022\004\030\0010\0020\001B5\022\006\020\003\032\0020\004\022\006\020\005\032\0020\006\022\006\020\007\032\0020\006\022\016\020\b\032\n\022\006\022\004\030\0010\0020\t\022\006\020\n\032\0020\013¢\006\002\020\fJ\024\020\017\032\016\022\004\022\0020\006\022\004\022\0020\0060\023H\026J\024\020\024\032\016\022\004\022\0020\006\022\004\022\0020\0060\016H\024J\032\020\025\032\n\022\006\022\004\030\0010\0020\0262\b\020\027\032\004\030\0010\030H\024R\035\020\r\032\016\022\004\022\0020\006\022\004\022\0020\0060\016¢\006\b\n\000\032\004\b\017\020\020R\035\020\021\032\016\022\004\022\0020\006\022\004\022\0020\0060\016¢\006\b\n\000\032\004\b\022\020\020¨\006\031"}, d2={"Lcom/byimplication/sakay/Sakay$SakayJsonObjectRequest;", "Lcom/android/volley/toolbox/JsonRequest;", "Lorg/json/JSONObject;", "method", "", "url", "", "requestBody", "listener", "Lcom/android/volley/Response$Listener;", "errorListener", "Lcom/android/volley/Response$ErrorListener;", "(ILjava/lang/String;Ljava/lang/String;Lcom/android/volley/Response$Listener;Lcom/android/volley/Response$ErrorListener;)V", "headers", "Ljava/util/HashMap;", "getHeaders", "()Ljava/util/HashMap;", "postParams", "getPostParams", "", "getParams", "parseNetworkResponse", "Lcom/android/volley/Response;", "response", "Lcom/android/volley/NetworkResponse;", "app_release"}, k=1, mv={1, 1, 9})
  public static final class SakayJsonObjectRequest
    extends JsonRequest<JSONObject>
  {
    @NotNull
    private final HashMap<String, String> headers = new HashMap();
    @NotNull
    private final HashMap<String, String> postParams = new HashMap();
    
    public SakayJsonObjectRequest(int paramInt, @NotNull String paramString1, @NotNull String paramString2, @NotNull Response.Listener<JSONObject> paramListener, @NotNull Response.ErrorListener paramErrorListener)
    {
      super(paramString1, paramString2, paramListener, paramErrorListener);
      headers.put("Accept-Encoding", "identity");
      headers.put("X-API-Version", "4");
      headers.put("X-API-Source", "android");
      setRetryPolicy((RetryPolicy)new DefaultRetryPolicy(5000, 1, 1.0F));
    }
    
    @NotNull
    public final HashMap<String, String> getHeaders()
    {
      return headers;
    }
    
    @NotNull
    public Map<String, String> getHeaders()
    {
      return (Map)headers;
    }
    
    @NotNull
    protected HashMap<String, String> getParams()
    {
      return postParams;
    }
    
    @NotNull
    public final HashMap<String, String> getPostParams()
    {
      return postParams;
    }
    
    @NotNull
    protected Response<JSONObject> parseNetworkResponse(@Nullable NetworkResponse paramNetworkResponse)
    {
      if (paramNetworkResponse != null)
      {
        paramNetworkResponse = data;
        Intrinsics.checkExpressionValueIsNotNull(paramNetworkResponse, "response.data");
        paramNetworkResponse = new JSONObject(new String(paramNetworkResponse, Charsets.UTF_8));
      }
      else
      {
        paramNetworkResponse = null;
      }
      paramNetworkResponse = Response.success(paramNetworkResponse, null);
      Intrinsics.checkExpressionValueIsNotNull(paramNetworkResponse, "Response.success(responseJson, null)");
      return paramNetworkResponse;
    }
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\0004\n\002\030\002\n\002\030\002\n\000\n\002\020\b\n\000\n\002\020\016\n\000\n\002\030\002\n\000\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\004\n\002\020%\n\002\b\002\030\0002\0020\001B+\022\006\020\002\032\0020\003\022\006\020\004\032\0020\005\022\f\020\006\032\b\022\004\022\0020\0050\007\022\006\020\b\032\0020\t¢\006\002\020\nJ\024\020\r\032\016\022\004\022\0020\005\022\004\022\0020\0050\021H\026J\024\020\022\032\016\022\004\022\0020\005\022\004\022\0020\0050\021H\024R\035\020\013\032\016\022\004\022\0020\005\022\004\022\0020\0050\f¢\006\b\n\000\032\004\b\r\020\016R\035\020\017\032\016\022\004\022\0020\005\022\004\022\0020\0050\f¢\006\b\n\000\032\004\b\020\020\016¨\006\023"}, d2={"Lcom/byimplication/sakay/Sakay$SakayRequest;", "Lcom/android/volley/toolbox/StringRequest;", "method", "", "url", "", "listener", "Lcom/android/volley/Response$Listener;", "errorListener", "Lcom/android/volley/Response$ErrorListener;", "(ILjava/lang/String;Lcom/android/volley/Response$Listener;Lcom/android/volley/Response$ErrorListener;)V", "headers", "Ljava/util/HashMap;", "getHeaders", "()Ljava/util/HashMap;", "postParams", "getPostParams", "", "getParams", "app_release"}, k=1, mv={1, 1, 9})
  public static final class SakayRequest
    extends StringRequest
  {
    @NotNull
    private final HashMap<String, String> headers = new HashMap();
    @NotNull
    private final HashMap<String, String> postParams = new HashMap();
    
    public SakayRequest(int paramInt, @NotNull String paramString, @NotNull Response.Listener<String> paramListener, @NotNull Response.ErrorListener paramErrorListener)
    {
      super(paramString, paramListener, paramErrorListener);
      headers.put("Accept-Encoding", "identity");
      headers.put("X-API-Version", "4");
      headers.put("X-API-Source", "android");
      setRetryPolicy((RetryPolicy)new DefaultRetryPolicy(5000, 1, 1.0F));
    }
    
    @NotNull
    public final HashMap<String, String> getHeaders()
    {
      return headers;
    }
    
    @NotNull
    public Map<String, String> getHeaders()
    {
      return (Map)headers;
    }
    
    @NotNull
    protected Map<String, String> getParams()
    {
      return (Map)postParams;
    }
    
    @NotNull
    public final HashMap<String, String> getPostParams()
    {
      return postParams;
    }
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\000\031\n\000\n\002\030\002\n\002\b\002\n\002\020\002\n\000\n\002\030\002\n\000*\001\000\b\n\030\0002\0020\001B\005¢\006\002\020\002J\020\020\003\032\0020\0042\006\020\005\032\0020\006H\026¨\006\007"}, d2={"com/byimplication/sakay/Sakay$getAnnouncements$ErrorListener", "Lcom/android/volley/Response$ErrorListener;", "()V", "onErrorResponse", "", "error", "Lcom/android/volley/VolleyError;", "app_release"}, k=1, mv={1, 1, 9})
  public static final class getAnnouncements$ErrorListener
    implements Response.ErrorListener
  {
    public getAnnouncements$ErrorListener() {}
    
    public void onErrorResponse(@NotNull VolleyError paramVolleyError)
    {
      Intrinsics.checkParameterIsNotNull(paramVolleyError, "error");
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Sakay::getAnnouncements Error: ");
      localStringBuilder.append(paramVolleyError.toString());
      Log.d("SAKAY", localStringBuilder.toString());
    }
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\000\031\n\000\n\002\030\002\n\002\020\016\n\002\b\002\n\002\020\002\n\002\b\002*\001\000\b\n\030\0002\b\022\004\022\0020\0020\001B\005¢\006\002\020\003J\020\020\004\032\0020\0052\006\020\006\032\0020\002H\026¨\006\007"}, d2={"com/byimplication/sakay/Sakay$getAnnouncements$ResponseListener", "Lcom/android/volley/Response$Listener;", "", "(Lkotlin/jvm/functions/Function1;)V", "onResponse", "", "response", "app_release"}, k=1, mv={1, 1, 9})
  public static final class getAnnouncements$ResponseListener
    implements Response.Listener<String>
  {
    public getAnnouncements$ResponseListener(Function1 paramFunction1) {}
    
    public void onResponse(@NotNull String paramString)
    {
      Intrinsics.checkParameterIsNotNull(paramString, "response");
      try
      {
        paramString = SakayJsonParser.INSTANCE.parseAnnouncementListFromResponse(paramString);
        $announceCallback.invoke(paramString);
        return;
      }
      catch (Exception paramString)
      {
        for (;;) {}
      }
      Log.e("SAKAY", "Sakay::getAnnouncements error");
    }
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\000\031\n\000\n\002\030\002\n\002\b\002\n\002\020\002\n\000\n\002\030\002\n\000*\001\000\b\n\030\0002\0020\001B\005¢\006\002\020\002J\022\020\003\032\0020\0042\b\020\005\032\004\030\0010\006H\026¨\006\007"}, d2={"com/byimplication/sakay/Sakay$getAutocompleteFromSakayPelias$ErrorListener", "Lcom/android/volley/Response$ErrorListener;", "()V", "onErrorResponse", "", "error", "Lcom/android/volley/VolleyError;", "app_release"}, k=1, mv={1, 1, 9})
  public static final class getAutocompleteFromSakayPelias$ErrorListener
    implements Response.ErrorListener
  {
    public getAutocompleteFromSakayPelias$ErrorListener() {}
    
    public void onErrorResponse(@Nullable VolleyError paramVolleyError)
    {
      if (paramVolleyError != null)
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("Sakay::getAutocompleteFromSakayPelias iz error ");
        localStringBuilder.append(paramVolleyError.getMessage());
        Log.d("SAKAY", localStringBuilder.toString());
        return;
      }
      Log.d("SAKAY", "Sakay::getAutocompleteFromSakayPelias iz error");
    }
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\000\031\n\000\n\002\030\002\n\002\020\016\n\002\b\002\n\002\020\002\n\002\b\002*\001\000\b\n\030\0002\b\022\004\022\0020\0020\001B\005¢\006\002\020\003J\022\020\004\032\0020\0052\b\020\006\032\004\030\0010\002H\026¨\006\007"}, d2={"com/byimplication/sakay/Sakay$getAutocompleteFromSakayPelias$ResponseListener", "Lcom/android/volley/Response$Listener;", "", "()V", "onResponse", "", "response", "app_release"}, k=1, mv={1, 1, 9})
  public static final class getAutocompleteFromSakayPelias$ResponseListener
    implements Response.Listener<String>
  {
    public getAutocompleteFromSakayPelias$ResponseListener() {}
    
    public void onResponse(@Nullable String paramString)
    {
      if (paramString != null)
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("Sakay::getAutocompleteFromSakayPelias ");
        localStringBuilder.append(paramString);
        Log.d("SAKAY", localStringBuilder.toString());
        paramString = (PeliasOsmModel)new Gson().fromJson(paramString, PeliasOsmModel.class);
        localStringBuilder = new StringBuilder();
        localStringBuilder.append("Sakay::getAutocompleteFromSakayPelias Osm Model ");
        localStringBuilder.append(paramString.toString());
        Log.d("SAKAY", localStringBuilder.toString());
        return;
      }
      Log.d("SAKAY", "Sakay::getAutocompleteFromSakayPelias iz null");
    }
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\000\031\n\000\n\002\030\002\n\002\b\002\n\002\020\002\n\000\n\002\030\002\n\000*\001\000\b\n\030\0002\0020\001B\005¢\006\002\020\002J\022\020\003\032\0020\0042\b\020\005\032\004\030\0010\006H\026¨\006\007"}, d2={"com/byimplication/sakay/Sakay$getAutocompleteFromSakayPhoton$ErrorListener", "Lcom/android/volley/Response$ErrorListener;", "(Lkotlin/jvm/functions/Function2;)V", "onErrorResponse", "", "error", "Lcom/android/volley/VolleyError;", "app_release"}, k=1, mv={1, 1, 9})
  public static final class getAutocompleteFromSakayPhoton$ErrorListener
    implements Response.ErrorListener
  {
    public getAutocompleteFromSakayPhoton$ErrorListener(Function2 paramFunction2) {}
    
    public void onErrorResponse(@Nullable VolleyError paramVolleyError)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Sakay::getAutocompleteFromSakayPhoton iz error ");
      localStringBuilder.append(String.valueOf(paramVolleyError));
      Log.d("SAKAY", localStringBuilder.toString());
      $errorCallback.invoke(Sakay.INSTANCE.getNetworkErrorType(paramVolleyError), "");
    }
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\000\031\n\000\n\002\030\002\n\002\020\016\n\002\b\002\n\002\020\002\n\002\b\002*\001\000\b\n\030\0002\b\022\004\022\0020\0020\001B\005¢\006\002\020\003J\022\020\004\032\0020\0052\b\020\006\032\004\030\0010\002H\026¨\006\007"}, d2={"com/byimplication/sakay/Sakay$getAutocompleteFromSakayPhoton$ResponseListener", "Lcom/android/volley/Response$Listener;", "", "(Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function2;)V", "onResponse", "", "response", "app_release"}, k=1, mv={1, 1, 9})
  public static final class getAutocompleteFromSakayPhoton$ResponseListener
    implements Response.Listener<String>
  {
    public getAutocompleteFromSakayPhoton$ResponseListener(Function1 paramFunction1, Function2 paramFunction2) {}
    
    public void onResponse(@Nullable String paramString)
    {
      if (paramString != null)
      {
        Object localObject = new StringBuilder();
        ((StringBuilder)localObject).append("Sakay::getAutocompleteFromSakayPhoton ");
        ((StringBuilder)localObject).append(paramString);
        Log.d("SAKAY", ((StringBuilder)localObject).toString());
        paramString = (PhotonOsmModel)new Gson().fromJson(paramString, PhotonOsmModel.class);
        localObject = $callback;
        Intrinsics.checkExpressionValueIsNotNull(paramString, "osmModel");
        ((Function1)localObject).invoke(paramString);
        return;
      }
      paramFunction2.invoke(null, "JSON Parsing Error");
    }
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\000\031\n\000\n\002\030\002\n\002\b\002\n\002\020\002\n\000\n\002\030\002\n\000*\001\000\b\n\030\0002\0020\001B\005¢\006\002\020\002J\022\020\003\032\0020\0042\b\020\005\032\004\030\0010\006H\026¨\006\007"}, d2={"com/byimplication/sakay/Sakay$getPlan$ErrorListener", "Lcom/android/volley/Response$ErrorListener;", "(Lkotlin/jvm/functions/Function1;)V", "onErrorResponse", "", "error", "Lcom/android/volley/VolleyError;", "app_release"}, k=1, mv={1, 1, 9})
  public static final class getPlan$ErrorListener
    implements Response.ErrorListener
  {
    public getPlan$ErrorListener(Function1 paramFunction1) {}
    
    public void onErrorResponse(@Nullable VolleyError paramVolleyError)
    {
      Log.d("SAKAY", "Sakay::getPlan error");
      paramVolleyError = Sakay.INSTANCE.getNetworkErrorType(paramVolleyError);
      $errorCallback.invoke(paramVolleyError);
    }
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\000\031\n\000\n\002\030\002\n\002\020\016\n\002\b\002\n\002\020\002\n\002\b\002*\001\000\b\n\030\0002\b\022\004\022\0020\0020\001B\005¢\006\002\020\003J\022\020\004\032\0020\0052\b\020\006\032\004\030\0010\002H\026¨\006\007"}, d2={"com/byimplication/sakay/Sakay$getPlan$ResponseListener", "Lcom/android/volley/Response$Listener;", "", "(Lkotlin/jvm/functions/Function3;)V", "onResponse", "", "response", "app_release"}, k=1, mv={1, 1, 9})
  public static final class getPlan$ResponseListener
    implements Response.Listener<String>
  {
    public getPlan$ResponseListener(Function3 paramFunction3) {}
    
    public void onResponse(@Nullable String paramString)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Sakay::getPlan response ");
      localStringBuilder.append(paramString);
      Log.d("SAKAY", localStringBuilder.toString());
      if (paramString != null)
      {
        int i = 0;
        try
        {
          paramString = SakayJsonParser.INSTANCE.parsePlanResponseFromResponse(paramString);
          int j = 1;
          i = j;
          Sakay.INSTANCE.setLastSearchTime(System.currentTimeMillis() - Sakay.INSTANCE.getLastSearchTime());
          i = j;
          Sakay.reportSearch$default(Sakay.INSTANCE, paramString.getSearchId(), 0L, 2, null);
          i = j;
          $planRequestCallback.invoke(paramString.getSearchId(), paramString.getPlan(), paramString.getError());
          return;
        }
        catch (Exception paramString)
        {
          localStringBuilder = new StringBuilder();
          localStringBuilder.append("Sakay::getPlan ResponseListener::onResponse error ");
          localStringBuilder.append(paramString.toString());
          Log.d("SAKAY", localStringBuilder.toString());
          if (i == 0) {
            $planRequestCallback.invoke(null, null, null);
          }
        }
      }
    }
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\000\031\n\000\n\002\030\002\n\002\b\002\n\002\020\002\n\000\n\002\030\002\n\000*\001\000\b\n\030\0002\0020\001B\005¢\006\002\020\002J\022\020\003\032\0020\0042\b\020\005\032\004\030\0010\006H\026¨\006\007"}, d2={"com/byimplication/sakay/Sakay$getStopDetails$ErrorListener", "Lcom/android/volley/Response$ErrorListener;", "(Lkotlin/jvm/functions/Function3;Ljava/lang/String;Ljava/lang/String;)V", "onErrorResponse", "", "error", "Lcom/android/volley/VolleyError;", "app_release"}, k=1, mv={1, 1, 9})
  public static final class getStopDetails$ErrorListener
    implements Response.ErrorListener
  {
    public getStopDetails$ErrorListener(Function3 paramFunction3, String paramString1, String paramString2) {}
    
    public void onErrorResponse(@Nullable VolleyError paramVolleyError)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Sakay::getStopDetails ResponseListener::onResponse has error ");
      if (paramVolleyError != null) {
        paramVolleyError = paramVolleyError.toString();
      } else {
        paramVolleyError = null;
      }
      localStringBuilder.append(paramVolleyError);
      Log.e("SAKAY", localStringBuilder.toString());
      $callBack.invoke(paramString1, paramString2, null);
    }
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\000\031\n\000\n\002\030\002\n\002\020\016\n\002\b\002\n\002\020\002\n\002\b\002*\001\000\b\n\030\0002\b\022\004\022\0020\0020\001B\005¢\006\002\020\003J\022\020\004\032\0020\0052\b\020\006\032\004\030\0010\002H\026¨\006\007"}, d2={"com/byimplication/sakay/Sakay$getStopDetails$ResponseListener", "Lcom/android/volley/Response$Listener;", "", "(Lkotlin/jvm/functions/Function3;Ljava/lang/String;Ljava/lang/String;)V", "onResponse", "", "response", "app_release"}, k=1, mv={1, 1, 9})
  public static final class getStopDetails$ResponseListener
    implements Response.Listener<String>
  {
    public getStopDetails$ResponseListener(Function3 paramFunction3, String paramString1, String paramString2) {}
    
    public void onResponse(@Nullable String paramString)
    {
      if (paramString != null) {
        try
        {
          localStringBuilder = new StringBuilder();
          localStringBuilder.append("Sakay::getStopDetails ResponseListener::onResponse response: ");
          localStringBuilder.append(paramString);
          Log.d("SAKAY", localStringBuilder.toString());
          paramString = SakayJsonParser.INSTANCE.parseStopDetailsFromResponse(paramString);
          $callBack.invoke(paramString1, paramString2, paramString);
          return;
        }
        catch (Exception paramString)
        {
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append("Sakay::getStopDetails ResponseListener::onResponse has error ");
          localStringBuilder.append(paramString.toString());
          Log.e("SAKAY", localStringBuilder.toString());
          $callBack.invoke(paramString1, paramString2, null);
        }
      }
    }
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\000\031\n\000\n\002\030\002\n\002\b\002\n\002\020\002\n\000\n\002\030\002\n\000*\001\000\b\n\030\0002\0020\001B\005¢\006\002\020\002J\022\020\003\032\0020\0042\b\020\005\032\004\030\0010\006H\026¨\006\007"}, d2={"com/byimplication/sakay/Sakay$reportError$ErrorListener", "Lcom/android/volley/Response$ErrorListener;", "(Lkotlin/jvm/functions/Function3;)V", "onErrorResponse", "", "error", "Lcom/android/volley/VolleyError;", "app_release"}, k=1, mv={1, 1, 9})
  public static final class reportError$ErrorListener
    implements Response.ErrorListener
  {
    public reportError$ErrorListener(Function3 paramFunction3) {}
    
    public void onErrorResponse(@Nullable VolleyError paramVolleyError)
    {
      Log.d("SAKAY", "Sakay::getPlan error");
      if (paramVolleyError != null)
      {
        paramVolleyError = paramVolleyError.getMessage();
        if (paramVolleyError != null) {}
      }
      else
      {
        paramVolleyError = "Unknown";
      }
      $errorReportCallBack.invoke(Boolean.valueOf(false), "", paramVolleyError);
    }
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\000\031\n\000\n\002\030\002\n\002\020\016\n\002\b\002\n\002\020\002\n\002\b\002*\001\000\b\n\030\0002\b\022\004\022\0020\0020\001B\005¢\006\002\020\003J\022\020\004\032\0020\0052\b\020\006\032\004\030\0010\002H\026¨\006\007"}, d2={"com/byimplication/sakay/Sakay$reportError$ResponseListener", "Lcom/android/volley/Response$Listener;", "", "(Lkotlin/jvm/functions/Function3;)V", "onResponse", "", "response", "app_release"}, k=1, mv={1, 1, 9})
  public static final class reportError$ResponseListener
    implements Response.Listener<String>
  {
    public reportError$ResponseListener(Function3 paramFunction3) {}
    
    public void onResponse(@Nullable String paramString)
    {
      Object localObject = new StringBuilder();
      ((StringBuilder)localObject).append("Sakay::reportError response ");
      ((StringBuilder)localObject).append(paramString);
      Log.d("SAKAY", ((StringBuilder)localObject).toString());
      if (paramString != null)
      {
        localObject = new JSONObject(paramString);
        boolean bool = ((JSONObject)localObject).getBoolean("success");
        paramString = ((JSONObject)localObject).optString("code");
        Intrinsics.checkExpressionValueIsNotNull(paramString, "reJson.optString(\"code\")");
        localObject = ((JSONObject)localObject).optString("message");
        Intrinsics.checkExpressionValueIsNotNull(localObject, "reJson.optString(\"message\")");
        $errorReportCallBack.invoke(Boolean.valueOf(bool), paramString, localObject);
      }
    }
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\000\031\n\000\n\002\030\002\n\002\b\002\n\002\020\002\n\000\n\002\030\002\n\000*\001\000\b\n\030\0002\0020\001B\005¢\006\002\020\002J\022\020\003\032\0020\0042\b\020\005\032\004\030\0010\006H\026¨\006\007"}, d2={"com/byimplication/sakay/Sakay$reportIncident$ErrorListener", "Lcom/android/volley/Response$ErrorListener;", "()V", "onErrorResponse", "", "error", "Lcom/android/volley/VolleyError;", "app_release"}, k=1, mv={1, 1, 9})
  public static final class reportIncident$ErrorListener
    implements Response.ErrorListener
  {
    public reportIncident$ErrorListener() {}
    
    public void onErrorResponse(@Nullable VolleyError paramVolleyError)
    {
      Log.d("SAKAY", "Sakay::reportIncident error");
    }
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\000\031\n\000\n\002\030\002\n\002\020\016\n\002\b\002\n\002\020\002\n\002\b\002*\001\000\b\n\030\0002\b\022\004\022\0020\0020\001B\005¢\006\002\020\003J\022\020\004\032\0020\0052\b\020\006\032\004\030\0010\002H\026¨\006\007"}, d2={"com/byimplication/sakay/Sakay$reportIncident$ResponseListener", "Lcom/android/volley/Response$Listener;", "", "(Lkotlin/jvm/functions/Function1;)V", "onResponse", "", "response", "app_release"}, k=1, mv={1, 1, 9})
  public static final class reportIncident$ResponseListener
    implements Response.Listener<String>
  {
    public reportIncident$ResponseListener(Function1 paramFunction1) {}
    
    public void onResponse(@Nullable String paramString)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Sakay::reportIncident response ");
      localStringBuilder.append(paramString);
      Log.d("SAKAY", localStringBuilder.toString());
      if (paramString != null) {}
      try
      {
        paramString = new JSONObject(paramString);
        $incidentCallback.invoke(new IncidentReportResponse(paramString.getBoolean("success"), paramString.getLong("id")));
        return;
      }
      catch (Exception paramString)
      {
        for (;;) {}
      }
      Log.e("SAKAY", "incident report response error");
      $incidentCallback.invoke(null);
    }
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\000\031\n\000\n\002\030\002\n\002\b\002\n\002\020\002\n\000\n\002\030\002\n\000*\001\000\b\n\030\0002\0020\001B\005¢\006\002\020\002J\022\020\003\032\0020\0042\b\020\005\032\004\030\0010\006H\026¨\006\007"}, d2={"com/byimplication/sakay/Sakay$reportIssue$ErrorListener", "Lcom/android/volley/Response$ErrorListener;", "(Lkotlin/jvm/functions/Function1;)V", "onErrorResponse", "", "error", "Lcom/android/volley/VolleyError;", "app_release"}, k=1, mv={1, 1, 9})
  public static final class reportIssue$ErrorListener
    implements Response.ErrorListener
  {
    public reportIssue$ErrorListener(Function1 paramFunction1) {}
    
    public void onErrorResponse(@Nullable VolleyError paramVolleyError)
    {
      if (paramVolleyError != null)
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("Sakay::reportIssue error: ");
        localStringBuilder.append(paramVolleyError.getMessage());
        Log.e("SAKAY", localStringBuilder.toString());
      }
      else
      {
        Log.e("SAKAY", "Sakay::reportIssue error: Unknown");
      }
      Sakay.INSTANCE.getReportIssueIsContactingServer().set(false);
      $reportIssueCallback.invoke(Boolean.valueOf(false));
    }
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\000\031\n\000\n\002\030\002\n\002\020\016\n\002\b\002\n\002\020\002\n\002\b\002*\001\000\b\n\030\0002\b\022\004\022\0020\0020\001B\005¢\006\002\020\003J\022\020\004\032\0020\0052\b\020\006\032\004\030\0010\002H\026¨\006\007"}, d2={"com/byimplication/sakay/Sakay$reportIssue$ResponseListener", "Lcom/android/volley/Response$Listener;", "", "(Lkotlin/jvm/functions/Function1;)V", "onResponse", "", "response", "app_release"}, k=1, mv={1, 1, 9})
  public static final class reportIssue$ResponseListener
    implements Response.Listener<String>
  {
    public reportIssue$ResponseListener(Function1 paramFunction1) {}
    
    public void onResponse(@Nullable String paramString)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Sakay::reportIssue returned with response: ");
      if (paramString == null) {
        paramString = "";
      }
      localStringBuilder.append(paramString);
      Log.d("SAKAY", localStringBuilder.toString());
      Sakay.INSTANCE.getReportIssueIsContactingServer().set(false);
      $reportIssueCallback.invoke(Boolean.valueOf(true));
    }
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\000\031\n\000\n\002\030\002\n\002\b\002\n\002\020\002\n\000\n\002\030\002\n\000*\001\000\b\n\030\0002\0020\001B\005¢\006\002\020\002J\022\020\003\032\0020\0042\b\020\005\032\004\030\0010\006H\026¨\006\007"}, d2={"com/byimplication/sakay/Sakay$reportSearch$ErrorListener", "Lcom/android/volley/Response$ErrorListener;", "()V", "onErrorResponse", "", "error", "Lcom/android/volley/VolleyError;", "app_release"}, k=1, mv={1, 1, 9})
  public static final class reportSearch$ErrorListener
    implements Response.ErrorListener
  {
    public reportSearch$ErrorListener() {}
    
    public void onErrorResponse(@Nullable VolleyError paramVolleyError)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Sakay::reportSearch Error: ");
      localStringBuilder.append(String.valueOf(paramVolleyError));
      Log.d("SAKAY", localStringBuilder.toString());
    }
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\000\031\n\000\n\002\030\002\n\002\020\016\n\002\b\002\n\002\020\002\n\002\b\002*\001\000\b\n\030\0002\b\022\004\022\0020\0020\001B\005¢\006\002\020\003J\022\020\004\032\0020\0052\b\020\006\032\004\030\0010\002H\026¨\006\007"}, d2={"com/byimplication/sakay/Sakay$reportSearch$ResponseListener", "Lcom/android/volley/Response$Listener;", "", "()V", "onResponse", "", "response", "app_release"}, k=1, mv={1, 1, 9})
  public static final class reportSearch$ResponseListener
    implements Response.Listener<String>
  {
    public reportSearch$ResponseListener() {}
    
    public void onResponse(@Nullable String paramString)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Sakay::reportSearch Search responded with result: ");
      if (paramString == null) {
        paramString = "";
      }
      localStringBuilder.append(paramString);
      Log.d("SAKAY", localStringBuilder.toString());
    }
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\000\031\n\000\n\002\030\002\n\002\b\002\n\002\020\002\n\000\n\002\030\002\n\000*\001\000\b\n\030\0002\0020\001B\005¢\006\002\020\002J\022\020\003\032\0020\0042\b\020\005\032\004\030\0010\006H\026¨\006\007"}, d2={"com/byimplication/sakay/Sakay$reverseGeocodeFromSakayPhoton$ErrorListener", "Lcom/android/volley/Response$ErrorListener;", "(Lkotlin/jvm/functions/Function2;)V", "onErrorResponse", "", "error", "Lcom/android/volley/VolleyError;", "app_release"}, k=1, mv={1, 1, 9})
  public static final class reverseGeocodeFromSakayPhoton$ErrorListener
    implements Response.ErrorListener
  {
    public reverseGeocodeFromSakayPhoton$ErrorListener(Function2 paramFunction2) {}
    
    public void onErrorResponse(@Nullable VolleyError paramVolleyError)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Sakay::getAutocompleteFromSakayPhoton error ");
      localStringBuilder.append(String.valueOf(paramVolleyError));
      Log.e("SAKAY", localStringBuilder.toString());
      $errorCallback.invoke(Sakay.INSTANCE.getNetworkErrorType(paramVolleyError), "");
    }
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\000\031\n\000\n\002\030\002\n\002\020\016\n\002\b\002\n\002\020\002\n\002\b\002*\001\000\b\n\030\0002\b\022\004\022\0020\0020\001B\005¢\006\002\020\003J\022\020\004\032\0020\0052\b\020\006\032\004\030\0010\002H\026¨\006\007"}, d2={"com/byimplication/sakay/Sakay$reverseGeocodeFromSakayPhoton$ResponseListener", "Lcom/android/volley/Response$Listener;", "", "(Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function2;)V", "onResponse", "", "response", "app_release"}, k=1, mv={1, 1, 9})
  public static final class reverseGeocodeFromSakayPhoton$ResponseListener
    implements Response.Listener<String>
  {
    public reverseGeocodeFromSakayPhoton$ResponseListener(Function1 paramFunction1, Function2 paramFunction2) {}
    
    public void onResponse(@Nullable String paramString)
    {
      if (paramString != null)
      {
        Object localObject = new StringBuilder();
        ((StringBuilder)localObject).append("Sakay::getAutocompleteFromSakayPhoton response: ");
        ((StringBuilder)localObject).append(paramString);
        Log.d("SAKAY", ((StringBuilder)localObject).toString());
        paramString = (PhotonOsmModel)new Gson().fromJson(paramString, PhotonOsmModel.class);
        localObject = $callback;
        Intrinsics.checkExpressionValueIsNotNull(paramString, "photonOsmModel");
        ((Function1)localObject).invoke(paramString);
        return;
      }
      paramFunction2.invoke(null, "JSON Parsing Error");
    }
  }
}
