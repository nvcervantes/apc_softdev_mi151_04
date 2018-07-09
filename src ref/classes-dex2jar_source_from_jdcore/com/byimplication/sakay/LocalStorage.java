package com.byimplication.sakay;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.JsonReader;
import android.util.JsonWriter;
import android.util.Log;
import com.byimplication.sakay.model.Alternative;
import com.byimplication.sakay.model.Announcement;
import com.byimplication.sakay.model.Conveyance;
import com.byimplication.sakay.model.Incident;
import com.byimplication.sakay.model.Itinerary;
import com.byimplication.sakay.model.LegGeometry;
import com.byimplication.sakay.model.Mapping;
import com.byimplication.sakay.model.PreviousSearch;
import com.byimplication.sakay.model.ScheduleType;
import com.byimplication.sakay.model.Terminal;
import com.byimplication.sakay.model.TripLeg;
import com.byimplication.sakay.model.TripPoint;
import com.byimplication.sakay.model.TripStep;
import com.google.android.gms.maps.model.LatLng;
import com.google.common.geometry.S2LatLng;
import com.google.common.geometry.S2Point;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.coroutines.experimental.Continuation;
import kotlin.coroutines.experimental.CoroutineContext;
import kotlin.coroutines.experimental.intrinsics.IntrinsicsKt;
import kotlin.coroutines.experimental.jvm.internal.CoroutineImpl;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.FunctionReference;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref.BooleanRef;
import kotlin.jvm.internal.Ref.IntRef;
import kotlin.jvm.internal.Ref.LongRef;
import kotlin.jvm.internal.Ref.ObjectRef;
import kotlin.reflect.KDeclarationContainer;
import kotlin.text.StringsKt;
import kotlinx.coroutines.experimental.CommonPool;
import kotlinx.coroutines.experimental.CoroutineScope;
import kotlinx.coroutines.experimental.DeferredKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONObject;

@Metadata(bv={1, 0, 2}, d1={"\000ä\001\n\002\030\002\n\002\020\000\n\002\b\002\n\002\020\016\n\002\b\020\n\002\030\002\n\002\030\002\n\002\030\002\n\002\b\005\n\002\020 \n\002\030\002\n\002\b\005\n\002\020\002\n\000\n\002\030\002\n\000\n\002\020\t\n\002\b\004\n\002\030\002\n\002\b\003\n\002\020\013\n\002\b\006\n\002\030\002\n\000\n\002\030\002\n\002\b\002\n\002\020\b\n\002\b\b\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\006\n\002\030\002\n\000\n\002\020$\n\000\n\002\030\002\n\002\b\007\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\020\n\002\030\002\n\000\n\002\030\002\n\002\b\n\n\002\030\002\n\002\b\020\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\007\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\003\n\002\030\002\n\002\b\002\bÆ\002\030\0002\0020\001B\007\b\002¢\006\002\020\002J(\020#\032\0020$2\006\020%\032\0020&2\006\020'\032\0020(2\006\020)\032\0020(2\b\020*\032\004\030\0010\004J,\020+\032\0020$2\006\020,\032\0020-2\b\020.\032\004\030\0010\0042\b\020/\032\004\030\0010\0042\b\b\002\0200\032\00201J\020\0202\032\0020$2\006\020%\032\0020&H\002J\016\0203\032\002012\006\020%\032\0020&J.\0204\032\b\022\004\022\0020\0360\0352\006\0205\032\0020-2\006\0206\032\0020-2\b\0207\032\004\030\001082\006\0209\032\0020:J\030\020;\032\0020$2\006\020<\032\0020=2\b\b\002\020>\032\0020(J\026\020?\032\002012\006\020%\032\0020&2\006\020<\032\0020=J\026\020?\032\002012\006\020%\032\0020&2\006\020@\032\0020\004J\016\020A\032\0020$2\006\020%\032\0020&J\016\020B\032\0020$2\006\020%\032\0020&J\016\020C\032\0020$2\006\020<\032\0020=J\030\020D\032\004\030\0010\0042\f\020E\032\b\022\004\022\0020F0\035H\002J\016\020G\032\0020\0042\006\020%\032\0020&J\020\020H\032\004\030\0010I2\006\020%\032\0020&J\030\020J\032\004\030\001082\006\020%\032\0020&2\006\020@\032\0020\004J\016\020\030\032\002012\006\020%\032\0020&J\020\020K\032\004\030\0010\0362\006\020<\032\0020=J\020\020L\032\004\030\0010I2\006\020%\032\0020&J\016\020M\032\002012\006\020,\032\0020-J\016\020N\032\0020$2\006\020%\032\0020&J\024\020O\032\b\022\004\022\0020P0\0352\006\020%\032\0020&J\032\020Q\032\016\022\004\022\0020=\022\004\022\002010R2\006\020%\032\0020&J$\020S\032\004\030\0010T2\006\020%\032\0020&2\006\020U\032\0020\0362\n\b\002\020V\032\004\030\0010\004J\024\020W\032\b\022\004\022\0020\0360\0352\006\020%\032\0020&J\020\020X\032\002082\006\020Y\032\0020\004H\002J\026\020Z\032\b\022\004\022\0020P0\0352\006\020[\032\0020\\H\002J\034\020]\032\016\022\004\022\0020=\022\004\022\002010R2\006\020[\032\0020\\H\002J\"\020^\032\016\022\004\022\0020\004\022\004\022\002010_2\006\020%\032\0020&2\006\020@\032\0020\004J\026\020`\032\b\022\004\022\0020\0360\0352\006\020a\032\0020bH\002J\020\020c\032\004\030\0010\0042\006\020[\032\0020\\J \020d\032\0020$2\006\020%\032\0020&2\006\020e\032\0020=2\006\020U\032\0020\036H\002J!\020f\032\0020$2\006\020%\032\0020&2\006\020g\032\0020=H@ø\001\000¢\006\002\020hJ$\020i\032\0020$2\006\020%\032\0020&2\f\020j\032\b\022\004\022\0020P0\0352\006\020k\032\0020(J\"\020l\032\0020$2\006\020%\032\0020&2\022\020m\032\016\022\004\022\0020=\022\004\022\002010RJ\016\020n\032\0020$2\006\020%\032\0020&J{\020o\032\002012\006\020%\032\0020&2\006\020p\032\0020I2\006\020q\032\0020I2\b\020'\032\004\030\0010(2\f\020r\032\b\022\004\022\0020s0\0352\f\020t\032\b\022\004\022\0020u0\0352\b\020v\032\004\030\001082\006\0209\032\0020:2\006\020w\032\0020\0042\f\020x\032\b\022\004\022\0020=0\0352\b\b\002\020y\032\0020(¢\006\002\020zJ\006\020{\032\0020$J\016\020|\032\0020$2\006\020<\032\0020=J\016\020}\032\0020$2\006\020%\032\0020&J'\020~\032\0020$2\007\020\032\0030\0012\f\020j\032\b\022\004\022\0020P0\0352\006\020k\032\0020(H\002J&\020\001\032\0020$2\007\020\032\0030\0012\022\020m\032\016\022\004\022\0020=\022\004\022\002010RH\002J\"\020\001\032\0020$2\007\020\032\0030\0012\006\020,\032\0020-2\006\020.\032\0020\004H\002J\033\020\001\032\0020$2\007\020\032\0030\0012\007\020\001\032\0020=H\002J3\020\001\032\0020$2\006\020%\032\0020&2\006\020,\032\0020-2\b\020.\032\004\030\0010\0042\b\020/\032\004\030\0010\0042\006\0200\032\00201J\033\020\001\032\0020$2\007\020\032\0030\0012\007\020\001\032\0020uH\002J\033\020\001\032\0020$2\007\020\032\0030\0012\007\020\001\032\0020(H\002J \020\001\032\0020$2\007\020\032\0030\0012\f\020t\032\b\022\004\022\0020u0\035H\002J \020\001\032\0020$2\007\020\032\0030\0012\f\020r\032\b\022\004\022\0020s0\035H\002J\031\020\001\032\0020$2\007\020\032\0030\0012\007\020\001\032\0020sJ\032\020\001\032\0020$2\007\020\032\0030\0012\006\020,\032\0020-H\002J\034\020\001\032\0020$2\007\020\032\0030\0012\b\020\001\032\0030\001H\002J\034\020\001\032\0020$2\007\020\032\0030\0012\b\020\001\032\0030\001H\002J\"\020\001\032\0020$2\007\020\032\0030\0012\006\020,\032\0020-2\006\020.\032\0020\004H\002J\032\020\001\032\0020$2\007\020\032\0030\0012\006\020U\032\0020\036H\002J\"\020\001\032\0020$2\007\020\032\0030\0012\006\020,\032\0020-2\006\020.\032\0020\004H\002J\030\020\001\032\0020$2\006\020%\032\0020&2\007\020\001\032\0020\004J\034\020\001\032\0020$2\007\020\032\0030\0012\b\020\001\032\0030\001H\002J%\020\001\032\0020$2\007\020\032\0030\0012\b\020\001\032\0030\0012\007\020 \001\032\0020\004H\002J\034\020¡\001\032\0020$2\007\020\032\0030\0012\b\020¢\001\032\0030£\001H\002J3\020¤\001\032\0020$2\006\020%\032\0020&2\006\020,\032\0020-2\b\020.\032\004\030\0010\0042\b\020/\032\004\030\0010\0042\006\0200\032\00201R\024\020\003\032\0020\004XD¢\006\b\n\000\032\004\b\005\020\006R\024\020\007\032\0020\004XD¢\006\b\n\000\032\004\b\b\020\006R\024\020\t\032\0020\004XD¢\006\b\n\000\032\004\b\n\020\006R\024\020\013\032\0020\004XD¢\006\b\n\000\032\004\b\f\020\006R\024\020\r\032\0020\004XD¢\006\b\n\000\032\004\b\016\020\006R\024\020\017\032\0020\004XD¢\006\b\n\000\032\004\b\020\020\006R\024\020\021\032\0020\004XD¢\006\b\n\000\032\004\b\022\020\006R\016\020\023\032\0020\004XT¢\006\002\n\000R!\020\024\032\022\022\004\022\0020\0260\025j\b\022\004\022\0020\026`\027¢\006\b\n\000\032\004\b\030\020\031R\024\020\032\032\0020\004XD¢\006\b\n\000\032\004\b\033\020\006R\024\020\034\032\b\022\004\022\0020\0360\035X\016¢\006\002\n\000R\024\020\037\032\0020\004XD¢\006\b\n\000\032\004\b \020\006R\024\020!\032\0020\004XD¢\006\b\n\000\032\004\b\"\020\006\002\004\n\002\b\t¨\006¥\001"}, d2={"Lcom/byimplication/sakay/LocalStorage;", "", "()V", "announcementFilename", "", "getAnnouncementFilename", "()Ljava/lang/String;", "announcementsReadFilename", "getAnnouncementsReadFilename", "confirmedIncidentsFilename", "getConfirmedIncidentsFilename", "dateFormat", "getDateFormat", "homeFilename", "getHomeFilename", "incidentsFilename", "getIncidentsFilename", "mainFilename", "getMainFilename", "offlineSearchesLogFilename", "previousSearches", "Ljava/util/ArrayList;", "Lcom/byimplication/sakay/model/PreviousSearch;", "Lkotlin/collections/ArrayList;", "getPreviousSearches", "()Ljava/util/ArrayList;", "previousSearchesFilename", "getPreviousSearchesFilename", "savedRoutes", "", "Lcom/byimplication/sakay/RouteFile;", "tokenFilename", "getTokenFilename", "workFilename", "getWorkFilename", "addOfflineSearchLog", "", "context", "Landroid/content/Context;", "searchId", "", "useTimestamp", "currentPlace", "addPreviousSearch", "latLng", "Lcom/google/android/gms/maps/model/LatLng;", "description", "provider", "isGeocoded", "", "changeRouteAliases", "changeSearchAliases", "checkForSimilarRoutes", "originPoint", "destinationPoint", "departureTime", "Ljava/util/Date;", "scheduleType", "Lcom/byimplication/sakay/model/ScheduleType;", "clickPreviousSearch", "id", "", "timeChosen", "deleteFile", "filename", "deleteHome", "deleteWork", "favePreviousSearch", "getAddress", "results", "Landroid/location/Address;", "getDeviceToken", "getHome", "Lcom/byimplication/sakay/model/Terminal;", "getLastModified", "getRouteById", "getWork", "hasSimilarSearches", "initialize", "loadAnnouncements", "Lcom/byimplication/sakay/model/Announcement;", "loadAnnouncementsRead", "", "loadSavedRoute", "Lcom/byimplication/sakay/RouteInfo;", "routeFile", "currentPlaceId", "loadSavedRoutesList", "parseDate", "dateString", "readAnnouncements", "reader", "Landroid/util/JsonReader;", "readAnnouncementsRead", "readFile", "Lkotlin/Pair;", "readSavedRoutes", "savedRoutesJson", "Lorg/json/JSONArray;", "readToken", "requestRouteListAlias", "pos", "requestSearchAlias", "searchPos", "(Landroid/content/Context;ILkotlin/coroutines/experimental/Continuation;)Ljava/lang/Object;", "saveAnnouncements", "announcements", "fromTime", "saveAnnouncementsRead", "announcementsRead", "savePreviousSearches", "saveRoute", "origin", "destination", "itineraries", "Lcom/byimplication/sakay/model/Itinerary;", "incidents", "Lcom/byimplication/sakay/model/Incident;", "date", "routeName", "routeIdsOverwritten", "savedOn", "(Landroid/content/Context;Lcom/byimplication/sakay/model/Terminal;Lcom/byimplication/sakay/model/Terminal;Ljava/lang/Long;Ljava/util/List;Ljava/util/List;Ljava/util/Date;Lcom/byimplication/sakay/model/ScheduleType;Ljava/lang/String;Ljava/util/List;J)Z", "sortPreviousSearches", "unfavePreviousSearch", "updateRouteAliases", "writeAnnouncements", "writer", "Landroid/util/JsonWriter;", "writeAnnouncementsRead", "writeDestination", "writeElevation", "eHeight", "writeHome", "writeIncident", "incident", "writeIncidentId", "iId", "writeIncidents", "writeItineraries", "writeItinerary", "itinerary", "writeLatLng", "writeLegGeometry", "legGeometry", "Lcom/byimplication/sakay/model/LegGeometry;", "writeMapping", "mapping", "Lcom/byimplication/sakay/model/Mapping;", "writeOrigin", "writeRouteFile", "writeTerminal", "writeToken", "token", "writeTripLeg", "tripLeg", "Lcom/byimplication/sakay/model/TripLeg;", "writeTripPoint", "tripPoint", "Lcom/byimplication/sakay/model/TripPoint;", "pointType", "writeTripStep", "tripStep", "Lcom/byimplication/sakay/model/TripStep;", "writeWork", "app_release"}, k=1, mv={1, 1, 9})
public final class LocalStorage
{
  public static final LocalStorage INSTANCE = new LocalStorage();
  @NotNull
  private static final String announcementFilename = "announcements.json";
  @NotNull
  private static final String announcementsReadFilename = "announcementsRead.json";
  @NotNull
  private static final String confirmedIncidentsFilename = "incidents-confirmed.json";
  @NotNull
  private static final String dateFormat = "yyyy.MM.dd G 'at' HH:mm:ss z";
  @NotNull
  private static final String homeFilename = "home_chosen.json";
  @NotNull
  private static final String incidentsFilename = "incidents.json";
  @NotNull
  private static final String mainFilename = "routesList.json";
  private static final String offlineSearchesLogFilename = "offline-log.json";
  @NotNull
  private static final ArrayList<PreviousSearch> previousSearches = new ArrayList();
  @NotNull
  private static final String previousSearchesFilename = "previous-searches.json";
  private static List<RouteFile> savedRoutes = CollectionsKt.emptyList();
  @NotNull
  private static final String tokenFilename = "token.json";
  @NotNull
  private static final String workFilename = "work_chosen.json";
  
  private LocalStorage() {}
  
  private final void changeRouteAliases(Context paramContext)
  {
    DeferredKt.async$default((CoroutineContext)CommonPool.INSTANCE, null, (Function2)new CoroutineImpl(paramContext, null)
    {
      private CoroutineScope p$;
      
      @NotNull
      public final Continuation<Unit> create(@NotNull CoroutineScope paramAnonymousCoroutineScope, @NotNull Continuation<? super Unit> paramAnonymousContinuation)
      {
        Intrinsics.checkParameterIsNotNull(paramAnonymousCoroutineScope, "$receiver");
        Intrinsics.checkParameterIsNotNull(paramAnonymousContinuation, "continuation");
        paramAnonymousContinuation = new 1($context, paramAnonymousContinuation);
        p$ = paramAnonymousCoroutineScope;
        return paramAnonymousContinuation;
      }
      
      @Nullable
      public final Object doResume(@Nullable Object paramAnonymousObject, @Nullable Throwable paramAnonymousThrowable)
      {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (label != 0) {
          throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        if (paramAnonymousThrowable != null) {
          throw paramAnonymousThrowable;
        }
        paramAnonymousObject = p$;
        int k = LocalStorage.access$getSavedRoutes$p(LocalStorage.INSTANCE).size();
        int i = 0;
        int j = i;
        while (i < k)
        {
          if (!((RouteFile)LocalStorage.access$getSavedRoutes$p(LocalStorage.INSTANCE).get(i)).isGeocoded())
          {
            Log.d("SAKAY", "LocalStorage::changeRouteAliases this route is not geocoded");
            LocalStorage.access$requestRouteListAlias(LocalStorage.INSTANCE, $context, i, (RouteFile)LocalStorage.access$getSavedRoutes$p(LocalStorage.INSTANCE).get(i));
            j = 1;
          }
          i += 1;
        }
        if (j != 0)
        {
          paramAnonymousObject = $context.openFileOutput(LocalStorage.INSTANCE.getMainFilename(), 0);
          Intrinsics.checkExpressionValueIsNotNull(paramAnonymousObject, "context.openFileOutput(m…me, Context.MODE_PRIVATE)");
          paramAnonymousObject = new JsonWriter((Writer)new OutputStreamWriter((OutputStream)paramAnonymousObject, "UTF-8"));
          paramAnonymousObject.setIndent("  ");
          paramAnonymousObject.beginArray();
          Object localObject = (Iterable)LocalStorage.access$getSavedRoutes$p(LocalStorage.INSTANCE);
          paramAnonymousThrowable = (Collection)new ArrayList(CollectionsKt.collectionSizeOrDefault((Iterable)localObject, 10));
          localObject = ((Iterable)localObject).iterator();
          while (((Iterator)localObject).hasNext())
          {
            RouteFile localRouteFile = (RouteFile)((Iterator)localObject).next();
            LocalStorage.access$writeRouteFile(LocalStorage.INSTANCE, paramAnonymousObject, localRouteFile);
            paramAnonymousThrowable.add(Unit.INSTANCE);
          }
          paramAnonymousThrowable = (List)paramAnonymousThrowable;
          paramAnonymousObject.endArray();
          paramAnonymousObject.close();
        }
        return Unit.INSTANCE;
      }
      
      @Nullable
      public final Object invoke(@NotNull CoroutineScope paramAnonymousCoroutineScope, @NotNull Continuation<? super Unit> paramAnonymousContinuation)
      {
        Intrinsics.checkParameterIsNotNull(paramAnonymousCoroutineScope, "$receiver");
        Intrinsics.checkParameterIsNotNull(paramAnonymousContinuation, "continuation");
        return ((1)create(paramAnonymousCoroutineScope, paramAnonymousContinuation)).doResume(Unit.INSTANCE, null);
      }
    }, 2, null);
  }
  
  private final String getAddress(List<? extends Address> paramList)
  {
    boolean bool = ((Collection)paramList).isEmpty();
    int i = 1;
    if ((bool ^ true))
    {
      Address localAddress = (Address)paramList.get(0);
      paramList = localAddress.getAddressLine(0);
      int j = localAddress.getMaxAddressLineIndex();
      while (i < j)
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append(paramList);
        localStringBuilder.append(", ");
        localStringBuilder.append(localAddress.getAddressLine(i));
        paramList = localStringBuilder.toString();
        i += 1;
      }
      return paramList;
    }
    return null;
  }
  
  private final Date parseDate(String paramString)
  {
    paramString = new SimpleDateFormat(dateFormat, Locale.getDefault()).parse(paramString);
    Intrinsics.checkExpressionValueIsNotNull(paramString, "sdf.parse(dateString)");
    return paramString;
  }
  
  private final List<Announcement> readAnnouncements(final JsonReader paramJsonReader)
  {
    ArrayList localArrayList = new ArrayList();
    paramJsonReader.beginArray();
    while (paramJsonReader.hasNext())
    {
      Ref.IntRef localIntRef = new Ref.IntRef();
      element = 0;
      final Ref.LongRef localLongRef = new Ref.LongRef();
      element = 0L;
      final Ref.ObjectRef localObjectRef1 = new Ref.ObjectRef();
      element = "";
      final Ref.ObjectRef localObjectRef2 = new Ref.ObjectRef();
      element = "";
      Lambda local1 = new Lambda(localIntRef)
      {
        public final void invoke(@NotNull String paramAnonymousString)
        {
          Intrinsics.checkParameterIsNotNull(paramAnonymousString, "n");
          int i = paramAnonymousString.hashCode();
          if (i != 3355)
          {
            if (i != 55126294)
            {
              String str;
              if (i != 110371416)
              {
                if (i != 954925063) {
                  return;
                }
                if (paramAnonymousString.equals("message"))
                {
                  paramAnonymousString = localObjectRef2;
                  str = paramJsonReader.nextString();
                  Intrinsics.checkExpressionValueIsNotNull(str, "reader.nextString()");
                  element = str;
                }
              }
              else if (paramAnonymousString.equals("title"))
              {
                paramAnonymousString = localObjectRef1;
                str = paramJsonReader.nextString();
                Intrinsics.checkExpressionValueIsNotNull(str, "reader.nextString()");
                element = str;
              }
            }
            else if (paramAnonymousString.equals("timestamp"))
            {
              localLongRefelement = paramJsonReader.nextLong();
            }
          }
          else if (paramAnonymousString.equals("id")) {
            $id.element = paramJsonReader.nextInt();
          }
        }
      };
      paramJsonReader.beginObject();
      while (paramJsonReader.hasNext())
      {
        String str = paramJsonReader.nextName();
        Intrinsics.checkExpressionValueIsNotNull(str, "reader.nextName()");
        local1.invoke(str);
      }
      paramJsonReader.endObject();
      ((Collection)localArrayList).add(new Announcement(element, element, (String)element, (String)element));
    }
    paramJsonReader.endArray();
    return (List)localArrayList;
  }
  
  private final Map<Integer, Boolean> readAnnouncementsRead(final JsonReader paramJsonReader)
  {
    Map localMap = MapsKt.emptyMap();
    paramJsonReader.beginArray();
    while (paramJsonReader.hasNext())
    {
      Ref.IntRef localIntRef = new Ref.IntRef();
      element = 0;
      final Ref.BooleanRef localBooleanRef = new Ref.BooleanRef();
      element = false;
      Lambda local1 = new Lambda(localIntRef)
      {
        public final void invoke(@NotNull String paramAnonymousString)
        {
          Intrinsics.checkParameterIsNotNull(paramAnonymousString, "n");
          int i = paramAnonymousString.hashCode();
          if (i != -1180158496)
          {
            if (i != 3355) {
              return;
            }
            if (paramAnonymousString.equals("id")) {
              $id.element = paramJsonReader.nextInt();
            }
          }
          else if (paramAnonymousString.equals("isRead"))
          {
            localBooleanRefelement = paramJsonReader.nextBoolean();
          }
        }
      };
      paramJsonReader.beginObject();
      while (paramJsonReader.hasNext())
      {
        String str = paramJsonReader.nextName();
        Intrinsics.checkExpressionValueIsNotNull(str, "reader.nextName()");
        local1.invoke(str);
      }
      paramJsonReader.endObject();
      localMap = MapsKt.plus(localMap, MapsKt.plus(localMap, new Pair(Integer.valueOf(element), Boolean.valueOf(element))));
    }
    paramJsonReader.endArray();
    return localMap;
  }
  
  private final List<RouteFile> readSavedRoutes(JSONArray paramJSONArray)
  {
    ArrayList localArrayList = new ArrayList();
    int i = paramJSONArray.length();
    int j = 0;
    while (j < i)
    {
      Object localObject3 = paramJSONArray.getJSONObject(j);
      LatLng localLatLng1 = new LatLng(((JSONObject)localObject3).getDouble("originLatitude"), ((JSONObject)localObject3).getDouble("originLongitude"));
      String str2 = ((JSONObject)localObject3).getString("originName");
      String str3 = ((JSONObject)localObject3).optString("originAlias");
      LatLng localLatLng2 = new LatLng(((JSONObject)localObject3).getDouble("destinationLatitude"), ((JSONObject)localObject3).getDouble("destinationLongitude"));
      String str4 = ((JSONObject)localObject3).getString("destinationName");
      String str5 = ((JSONObject)localObject3).optString("destinationAlias");
      Object localObject1 = (Date)null;
      Object localObject2 = ((JSONObject)localObject3).optString("departureTime");
      if (localObject2 != null) {}
      try
      {
        localObject2 = parseDate((String)localObject2);
        localObject1 = localObject2;
      }
      catch (Exception localException)
      {
        String str6;
        long l1;
        long l2;
        String str7;
        String str1;
        boolean bool;
        for (;;) {}
      }
      Log.e("SAKAY", "LocalStorage::readSavedRoutes Saved route date parsing error.");
      localObject2 = ((JSONObject)localObject3).optString("scheduleType");
      if ((localObject2 != null) && (((String)localObject2).hashCode() == 930446297) && (((String)localObject2).equals("Arrival"))) {
        localObject2 = ScheduleType.ARRIVAL;
      } else {
        localObject2 = ScheduleType.DEPARTURE;
      }
      str6 = ((JSONObject)localObject3).optString("routeName", "");
      Intrinsics.checkExpressionValueIsNotNull(str6, "savedRouteJson.optString(\"routeName\", \"\")");
      l1 = ((JSONObject)localObject3).optLong("savedOn", 0L);
      l2 = ((JSONObject)localObject3).optLong("searchId");
      str7 = ((JSONObject)localObject3).getString("filename");
      Intrinsics.checkExpressionValueIsNotNull(str7, "savedRouteJson.getString(\"filename\")");
      Intrinsics.checkExpressionValueIsNotNull(str2, "originName");
      if (str3.length() > 0) {
        localObject3 = str3;
      } else {
        localObject3 = str2;
      }
      Intrinsics.checkExpressionValueIsNotNull(localObject3, "if (originAlias.length >…ginAlias; else originName");
      Intrinsics.checkExpressionValueIsNotNull(str4, "destinationName");
      if (str5.length() > 0) {
        str1 = str5;
      } else {
        str1 = str4;
      }
      Intrinsics.checkExpressionValueIsNotNull(str1, "if (destinationAlias.len…ias; else destinationName");
      if ((str3.length() > 0) && (str5.length() > 0)) {
        bool = true;
      } else {
        bool = false;
      }
      localObject1 = new RouteFile(Long.valueOf(l2), str7, localLatLng1, str2, (String)localObject3, localLatLng2, str4, str1, (Date)localObject1, (ScheduleType)localObject2, j, bool, str6, l1);
      localArrayList.add(localObject1);
      j += 1;
    }
    return (List)localArrayList;
  }
  
  private final void requestRouteListAlias(Context paramContext, int paramInt, RouteFile paramRouteFile)
  {
    Object localObject = new Geocoder(paramContext);
    paramContext = paramRouteFile.getOrigin();
    paramContext = ((Geocoder)localObject).getFromLocation(latitude, longitude, 1);
    Intrinsics.checkExpressionValueIsNotNull(paramContext, "originResults");
    paramContext = getAddress(paramContext);
    paramRouteFile = paramRouteFile.getDestination();
    paramRouteFile = ((Geocoder)localObject).getFromLocation(latitude, longitude, 1);
    Intrinsics.checkExpressionValueIsNotNull(paramRouteFile, "destinationResults");
    paramRouteFile = getAddress(paramRouteFile);
    localObject = (RouteFile)savedRoutes.get(paramInt);
    if (paramContext == null) {
      paramContext = "";
    }
    ((RouteFile)localObject).setOriginAlias(paramContext);
    localObject = (RouteFile)savedRoutes.get(paramInt);
    if (paramRouteFile != null) {
      paramContext = paramRouteFile;
    } else {
      paramContext = "";
    }
    ((RouteFile)localObject).setDestinationAlias(paramContext);
    ((RouteFile)savedRoutes.get(paramInt)).setGeocoded(true);
  }
  
  private final void writeAnnouncements(JsonWriter paramJsonWriter, List<Announcement> paramList, long paramLong)
  {
    paramJsonWriter.beginArray();
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      Announcement localAnnouncement = (Announcement)paramList.next();
      if (localAnnouncement.getTimestamp() > paramLong)
      {
        paramJsonWriter.beginObject();
        paramJsonWriter.name("id").value((Number)Integer.valueOf(localAnnouncement.getId()));
        paramJsonWriter.name("timestamp").value(localAnnouncement.getTimestamp());
        paramJsonWriter.name("title").value(localAnnouncement.getTitle());
        paramJsonWriter.name("message").value(localAnnouncement.getMessage());
        paramJsonWriter.endObject();
      }
    }
    paramJsonWriter.endArray();
  }
  
  private final void writeAnnouncementsRead(JsonWriter paramJsonWriter, Map<Integer, Boolean> paramMap)
  {
    paramJsonWriter.beginArray();
    paramMap = paramMap.entrySet().iterator();
    while (paramMap.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)paramMap.next();
      int i = ((Number)localEntry.getKey()).intValue();
      boolean bool = ((Boolean)localEntry.getValue()).booleanValue();
      paramJsonWriter.beginObject();
      paramJsonWriter.name("id").value((Number)Integer.valueOf(i));
      paramJsonWriter.name("isRead").value(bool);
      paramJsonWriter.endObject();
    }
    paramJsonWriter.endArray();
  }
  
  private final void writeDestination(JsonWriter paramJsonWriter, LatLng paramLatLng, String paramString)
  {
    Log.d("SAKAY", "WRITING DESTINATION");
    paramJsonWriter.name("destination");
    paramJsonWriter.beginObject();
    writeTerminal(paramJsonWriter, paramLatLng, paramString);
    paramJsonWriter.endObject();
  }
  
  private final void writeElevation(JsonWriter paramJsonWriter, int paramInt)
  {
    Log.d("SAKAY", "LocalStorage::writeElevation WRITING ELEVATION");
    paramJsonWriter.beginObject();
    paramJsonWriter.name("elevation").value((Number)Integer.valueOf(paramInt));
    paramJsonWriter.endObject();
  }
  
  private final void writeIncident(JsonWriter paramJsonWriter, Incident paramIncident)
  {
    Log.d("SAKAY", "LocalStorage::writeIncident");
    paramJsonWriter.beginObject();
    paramJsonWriter.name("description").value(paramIncident.getDescription());
    Object localObject3;
    if ((((Collection)paramIncident.getMapping()).isEmpty() ^ true))
    {
      paramJsonWriter.beginArray();
      localObject2 = (Iterable)paramIncident.getMapping();
      localObject1 = (Collection)new ArrayList(CollectionsKt.collectionSizeOrDefault((Iterable)localObject2, 10));
      localObject2 = ((Iterable)localObject2).iterator();
      while (((Iterator)localObject2).hasNext())
      {
        localObject3 = (Mapping)((Iterator)localObject2).next();
        INSTANCE.writeMapping(paramJsonWriter, (Mapping)localObject3);
        ((Collection)localObject1).add(Unit.INSTANCE);
      }
      localObject1 = (List)localObject1;
      paramJsonWriter.endArray();
    }
    else
    {
      paramJsonWriter.name("mapping").nullValue();
    }
    paramJsonWriter.name("last_updated").value(paramIncident.getLast_updated());
    paramJsonWriter.name("incidentType").value(paramIncident.getIncidentType());
    paramJsonWriter.name("created").value(paramIncident.getCreated());
    paramJsonWriter.name("likes");
    paramJsonWriter.beginArray();
    Object localObject2 = (Iterable)paramIncident.getLikes();
    Object localObject1 = (Collection)new ArrayList(CollectionsKt.collectionSizeOrDefault((Iterable)localObject2, 10));
    localObject2 = ((Iterable)localObject2).iterator();
    while (((Iterator)localObject2).hasNext())
    {
      localObject3 = (String)((Iterator)localObject2).next();
      paramJsonWriter.beginObject();
      paramJsonWriter.name("liker").value((String)localObject3);
      ((Collection)localObject1).add(paramJsonWriter.endObject());
    }
    localObject1 = (List)localObject1;
    paramJsonWriter.endArray();
    paramJsonWriter.name("id").value(paramIncident.getId());
    paramJsonWriter.name("loc");
    paramJsonWriter.beginArray();
    localObject2 = (Iterable)paramIncident.getLoc();
    localObject1 = (Collection)new ArrayList(CollectionsKt.collectionSizeOrDefault((Iterable)localObject2, 10));
    localObject2 = ((Iterable)localObject2).iterator();
    while (((Iterator)localObject2).hasNext())
    {
      double d = ((Number)((Iterator)localObject2).next()).doubleValue();
      paramJsonWriter.beginObject();
      paramJsonWriter.name("value").value(d);
      ((Collection)localObject1).add(paramJsonWriter.endObject());
    }
    localObject1 = (List)localObject1;
    paramJsonWriter.endArray();
    paramJsonWriter.name("created_by").value(paramIncident.getCreated_by());
    paramJsonWriter.endObject();
  }
  
  private final void writeIncidentId(JsonWriter paramJsonWriter, long paramLong)
  {
    Log.d("SAKAY", "LocalStorage::writeIncidentId WRITING INCIDENT ID");
    paramJsonWriter.beginObject();
    paramJsonWriter.name("id").value(paramLong);
    paramJsonWriter.endObject();
  }
  
  private final void writeIncidents(JsonWriter paramJsonWriter, List<Incident> paramList)
  {
    Log.d("SAKAY", "LocalStorage::writeIncidents");
    paramJsonWriter.name("incidents");
    paramJsonWriter.beginArray();
    Object localObject = (Iterable)paramList;
    paramList = (Collection)new ArrayList(CollectionsKt.collectionSizeOrDefault((Iterable)localObject, 10));
    localObject = ((Iterable)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      Incident localIncident = (Incident)((Iterator)localObject).next();
      INSTANCE.writeIncident(paramJsonWriter, localIncident);
      paramList.add(Unit.INSTANCE);
    }
    paramList = (List)paramList;
    paramJsonWriter.endArray();
  }
  
  private final void writeItineraries(JsonWriter paramJsonWriter, List<Itinerary> paramList)
  {
    Log.d("SAKAY", "WRITING ITINERARIES");
    paramJsonWriter.name("itineraries");
    paramJsonWriter.beginArray();
    Object localObject = (Iterable)paramList;
    paramList = (Collection)new ArrayList(CollectionsKt.collectionSizeOrDefault((Iterable)localObject, 10));
    localObject = ((Iterable)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      Itinerary localItinerary = (Itinerary)((Iterator)localObject).next();
      INSTANCE.writeItinerary(paramJsonWriter, localItinerary);
      paramList.add(Unit.INSTANCE);
    }
    paramList = (List)paramList;
    paramJsonWriter.endArray();
  }
  
  private final void writeLatLng(JsonWriter paramJsonWriter, LatLng paramLatLng)
  {
    paramJsonWriter.name("latitude").value(latitude);
    paramJsonWriter.name("longitude").value(longitude);
  }
  
  private final void writeLegGeometry(JsonWriter paramJsonWriter, LegGeometry paramLegGeometry)
  {
    Log.d("SAKAY", "LocalStorage::writeLegGeometry WRITING LEGGEOMETRY");
    paramJsonWriter.name("legGeometry");
    paramJsonWriter.beginObject();
    paramJsonWriter.name("points").value(paramLegGeometry.getPoints());
    paramJsonWriter.name("length").value((Number)Integer.valueOf(paramLegGeometry.getLength()));
    paramJsonWriter.endObject();
  }
  
  private final void writeMapping(JsonWriter paramJsonWriter, Mapping paramMapping)
  {
    paramJsonWriter.beginObject();
    paramJsonWriter.name("route_index").value((Number)Integer.valueOf(paramMapping.getRoute_index()));
    paramJsonWriter.name("leg_index").value((Number)Integer.valueOf(paramMapping.getLeg_index()));
    paramJsonWriter.endObject();
  }
  
  private final void writeOrigin(JsonWriter paramJsonWriter, LatLng paramLatLng, String paramString)
  {
    Log.d("SAKAY", "LocalStorage::writeOrigin WRITING ORIGIN");
    paramJsonWriter.name("origin");
    paramJsonWriter.beginObject();
    writeTerminal(paramJsonWriter, paramLatLng, paramString);
    paramJsonWriter.endObject();
  }
  
  private final void writeRouteFile(JsonWriter paramJsonWriter, RouteFile paramRouteFile)
  {
    paramJsonWriter.beginObject();
    paramJsonWriter.name("filename").value(paramRouteFile.getFilename());
    paramJsonWriter.name("originLatitude").value(getOriginlatitude);
    paramJsonWriter.name("originLongitude").value(getOriginlongitude);
    paramJsonWriter.name("originName").value(paramRouteFile.getOriginName());
    paramJsonWriter.name("destinationLatitude").value(getDestinationlatitude);
    paramJsonWriter.name("destinationLongitude").value(getDestinationlongitude);
    paramJsonWriter.name("destinationName").value(paramRouteFile.getDestinationName());
    if (paramRouteFile.getDepartureTime() != null)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("WEE: ");
      Object localObject = paramRouteFile.getDepartureTime();
      if (localObject != null) {
        localObject = ((Date)localObject).toString();
      } else {
        localObject = null;
      }
      localStringBuilder.append((String)localObject);
      Log.d("SAKAY", localStringBuilder.toString());
      localObject = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z", Locale.getDefault());
      paramJsonWriter.name("departureTime").value(((SimpleDateFormat)localObject).format(paramRouteFile.getDepartureTime()));
      paramJsonWriter.name("scheduleType");
      localObject = paramRouteFile.getScheduleType();
      if (LocalStorage.WhenMappings.$EnumSwitchMapping$1[localObject.ordinal()] != 1) {
        paramJsonWriter.value("Departure");
      } else {
        paramJsonWriter.value("Arrival");
      }
    }
    if (paramRouteFile.isGeocoded())
    {
      paramJsonWriter.name("originAlias").value(paramRouteFile.getOriginAlias());
      paramJsonWriter.name("destinationAlias").value(paramRouteFile.getDestinationAlias());
    }
    if (paramRouteFile.getSearchId() != null) {
      paramJsonWriter.name("searchId").value(paramRouteFile.getSearchId().longValue());
    }
    paramJsonWriter.name("routeName").value(paramRouteFile.getRouteName());
    paramJsonWriter.name("savedOn").value(paramRouteFile.getSavedOn());
    paramJsonWriter.endObject();
  }
  
  private final void writeTerminal(JsonWriter paramJsonWriter, LatLng paramLatLng, String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("WRITING TERMINAL: ");
    localStringBuilder.append(latitude);
    localStringBuilder.append(" ");
    localStringBuilder.append(longitude);
    Log.d("SAKAY", localStringBuilder.toString());
    writeLatLng(paramJsonWriter, paramLatLng);
    paramJsonWriter.name("description").value(paramString);
  }
  
  private final void writeTripLeg(JsonWriter paramJsonWriter, TripLeg paramTripLeg)
  {
    Log.d("SAKAY", "LocalStorage::writeTripLeg WRITING TRIPLEG");
    paramJsonWriter.beginObject();
    paramJsonWriter.name("rentedBike").value(paramTripLeg.getRentedBike());
    paramJsonWriter.name("departureDelay").value((Number)Integer.valueOf(paramTripLeg.getDepartureDelay()));
    paramJsonWriter.name("agencyTimeZoneOffset").value(paramTripLeg.getAgencyTimeZoneOffset());
    paramJsonWriter.name("startTime").value(paramTripLeg.getStartTime());
    paramJsonWriter.name("endTime").value(paramTripLeg.getEndTime());
    paramJsonWriter.name("steps");
    paramJsonWriter.beginArray();
    Object localObject2 = (Iterable)paramTripLeg.getSteps();
    Object localObject1 = (Collection)new ArrayList(CollectionsKt.collectionSizeOrDefault((Iterable)localObject2, 10));
    localObject2 = ((Iterable)localObject2).iterator();
    while (((Iterator)localObject2).hasNext())
    {
      TripStep localTripStep = (TripStep)((Iterator)localObject2).next();
      INSTANCE.writeTripStep(paramJsonWriter, localTripStep);
      ((Collection)localObject1).add(Unit.INSTANCE);
    }
    localObject1 = (List)localObject1;
    paramJsonWriter.endArray();
    paramJsonWriter.name("conveyance");
    paramJsonWriter.beginObject();
    paramJsonWriter.name("primary").value(paramTripLeg.getConveyance().getPrimary());
    if (paramTripLeg.getConveyance().getSecondary() != null) {
      paramJsonWriter.name("secondary").value(paramTripLeg.getConveyance().getSecondary());
    } else {
      paramJsonWriter.name("secondary").nullValue();
    }
    paramJsonWriter.endObject();
    if (paramTripLeg.getFare() != null) {
      paramJsonWriter.name("fare").value(paramTripLeg.getFare().doubleValue());
    } else {
      paramJsonWriter.name("fare").nullValue();
    }
    if (paramTripLeg.getFareId() != null) {
      paramJsonWriter.name("fareId").value(paramTripLeg.getFareId());
    } else {
      paramJsonWriter.name("fareId").nullValue();
    }
    paramJsonWriter.name("duration").value((Number)Integer.valueOf(paramTripLeg.getDuration()));
    paramJsonWriter.name("route").value(paramTripLeg.getRoute());
    paramJsonWriter.name("interlineWithPreviousLeg").value(paramTripLeg.getInterlineWithPreviousLeg());
    writeLegGeometry(paramJsonWriter, paramTripLeg.getLegGeometry());
    paramJsonWriter.name("realTime").value(paramTripLeg.getRealTime());
    paramJsonWriter.name("pathway").value(paramTripLeg.getPathway());
    writeTripPoint(paramJsonWriter, paramTripLeg.getFrom(), "from");
    paramJsonWriter.name("distance").value(paramTripLeg.getDistance());
    paramJsonWriter.name("transitLeg").value(paramTripLeg.getTransitLeg());
    paramJsonWriter.name("arrivalDelay").value((Number)Integer.valueOf(paramTripLeg.getArrivalDelay()));
    paramJsonWriter.name("routeId").value(paramTripLeg.getRouteId());
    if ((paramTripLeg.getAlternatives() != null) && ((((Collection)paramTripLeg.getAlternatives()).isEmpty() ^ true)))
    {
      paramJsonWriter.name("alternatives");
      paramJsonWriter.beginArray();
      localObject1 = ((Iterable)paramTripLeg.getAlternatives()).iterator();
      while (((Iterator)localObject1).hasNext())
      {
        localObject2 = (Alternative)((Iterator)localObject1).next();
        paramJsonWriter.beginObject();
        paramJsonWriter.name("id").value(((Alternative)localObject2).getId());
        paramJsonWriter.name("shortName").value(((Alternative)localObject2).getShortName());
        paramJsonWriter.name("longName").value(((Alternative)localObject2).getLongName());
        paramJsonWriter.endObject();
      }
      paramJsonWriter.endArray();
    }
    else
    {
      paramJsonWriter.name("alternatives").nullValue();
    }
    paramJsonWriter.name("headsign").value(paramTripLeg.getHeadsign());
    paramJsonWriter.name("routeLongName").value(paramTripLeg.getRouteLongName());
    writeTripPoint(paramJsonWriter, paramTripLeg.getTo(), "to");
    paramJsonWriter.endObject();
  }
  
  private final void writeTripPoint(JsonWriter paramJsonWriter, TripPoint paramTripPoint, String paramString)
  {
    Log.d("SAKAY", "LocalStorage::writeTripPoint WRITING TRIPPOINT");
    paramJsonWriter.name(paramString);
    paramJsonWriter.beginObject();
    if (paramTripPoint.getName() != null) {
      paramJsonWriter.name("name").value(paramTripPoint.getName());
    } else {
      paramJsonWriter.name("name").nullValue();
    }
    if (paramTripPoint.getLon() != null) {
      paramJsonWriter.name("lon").value(paramTripPoint.getLon().doubleValue());
    } else {
      paramJsonWriter.name("lon").nullValue();
    }
    if (paramTripPoint.getLat() != null) {
      paramJsonWriter.name("lat").value(paramTripPoint.getLat().doubleValue());
    } else {
      paramJsonWriter.name("lat").nullValue();
    }
    if (paramTripPoint.getDeparture() != null) {
      paramJsonWriter.name("departure").value(paramTripPoint.getDeparture().longValue());
    } else {
      paramJsonWriter.name("departure").nullValue();
    }
    if (paramTripPoint.getArrival() != null) {
      paramJsonWriter.name("arrival").value(paramTripPoint.getArrival().longValue());
    } else {
      paramJsonWriter.name("arrival").nullValue();
    }
    if (paramTripPoint.getOrig() != null) {
      paramJsonWriter.name("orig").value(paramTripPoint.getOrig());
    } else {
      paramJsonWriter.name("orig").nullValue();
    }
    if (paramTripPoint.getStopId() != null) {
      paramJsonWriter.name("stopId").value(paramTripPoint.getStopId());
    } else {
      paramJsonWriter.name("stopId").nullValue();
    }
    if (paramTripPoint.getZoneId() != null) {
      paramJsonWriter.name("zoneId").value(paramTripPoint.getZoneId());
    } else {
      paramJsonWriter.name("zoneId").nullValue();
    }
    if (paramTripPoint.getStopIndex() != null) {
      paramJsonWriter.name("stopIndex").value((Number)paramTripPoint.getStopIndex());
    } else {
      paramJsonWriter.name("stopIndex").nullValue();
    }
    if (paramTripPoint.getStopSequence() != null) {
      paramJsonWriter.name("stopSequence").value((Number)paramTripPoint.getStopSequence());
    } else {
      paramJsonWriter.name("stopSequence").nullValue();
    }
    paramJsonWriter.endObject();
  }
  
  private final void writeTripStep(JsonWriter paramJsonWriter, TripStep paramTripStep)
  {
    Log.d("SAKAY", "LocalStorage::writeTripStep WRITING TRIPSTEP");
    paramJsonWriter.beginObject();
    paramJsonWriter.name("streetName").value(paramTripStep.getStreetName());
    paramJsonWriter.name("elevations");
    paramJsonWriter.beginArray();
    Object localObject2 = (Iterable)paramTripStep.getElevation();
    Object localObject1 = (Collection)new ArrayList(CollectionsKt.collectionSizeOrDefault((Iterable)localObject2, 10));
    localObject2 = ((Iterable)localObject2).iterator();
    while (((Iterator)localObject2).hasNext())
    {
      int i = ((Number)((Iterator)localObject2).next()).intValue();
      INSTANCE.writeElevation(paramJsonWriter, i);
      ((Collection)localObject1).add(Unit.INSTANCE);
    }
    localObject1 = (List)localObject1;
    paramJsonWriter.endArray();
    paramJsonWriter.name("relativeDirection").value(paramTripStep.getRelativeDirection());
    paramJsonWriter.name("absoluteDirection").value(paramTripStep.getAbsoluteDirection());
    paramJsonWriter.name("bogusName").value(paramTripStep.getBogusName());
    paramJsonWriter.name("lon").value(paramTripStep.getLon());
    paramJsonWriter.name("lat").value(paramTripStep.getLat());
    paramJsonWriter.name("stayOn").value(paramTripStep.getStayOn());
    paramJsonWriter.name("area").value(paramTripStep.getArea());
    paramJsonWriter.name("distance").value(paramTripStep.getDistance());
    paramJsonWriter.endObject();
  }
  
  public final void addOfflineSearchLog(@NotNull Context paramContext, long paramLong1, long paramLong2, @Nullable String paramString)
  {
    Intrinsics.checkParameterIsNotNull(paramContext, "context");
    Pair localPair = readFile(paramContext, "offline-log.json");
    JSONObject localJSONObject = new JSONObject();
    localJSONObject.put("id", paramLong1);
    localJSONObject.put("useTimestamp", paramLong2);
    if (paramString != null) {
      localJSONObject.put("currentPlace", paramString);
    }
    try
    {
      if (((Boolean)localPair.getSecond()).booleanValue()) {
        paramString = new JSONArray((String)localPair.getFirst());
      } else {
        paramString = new JSONArray();
      }
    }
    catch (Exception paramString)
    {
      for (;;) {}
    }
    paramString = new JSONArray();
    paramString.put(localJSONObject);
    try
    {
      paramContext = new OutputStreamWriter((OutputStream)paramContext.getApplicationContext().openFileOutput("offline-log.json", 0), "UTF-8");
      paramContext.write(paramString.toString());
      paramContext.close();
      return;
    }
    catch (Exception paramContext)
    {
      paramString = new StringBuilder();
      paramString.append("LocalStorage::addOfflineSearchLog Error occured: ");
      paramString.append(paramContext.getMessage());
      Log.e("SAKAY", paramString.toString());
      return;
    }
  }
  
  public final void addPreviousSearch(@NotNull LatLng paramLatLng, @Nullable String paramString1, @Nullable String paramString2, boolean paramBoolean)
  {
    Intrinsics.checkParameterIsNotNull(paramLatLng, "latLng");
    if (!hasSimilarSearches(paramLatLng)) {
      previousSearches.add(new PreviousSearch(previousSearches.size(), paramLatLng, paramString1, paramBoolean, false, System.currentTimeMillis(), 1, paramString2));
    }
  }
  
  public final boolean changeSearchAliases(@NotNull Context paramContext)
  {
    Intrinsics.checkParameterIsNotNull(paramContext, "context");
    final Ref.BooleanRef localBooleanRef = new Ref.BooleanRef();
    final int i = 0;
    element = false;
    int j = previousSearches.size();
    while (i < j)
    {
      if (!((PreviousSearch)previousSearches.get(i)).isGeocoded()) {
        DeferredKt.async$default((CoroutineContext)CommonPool.INSTANCE, null, (Function2)new CoroutineImpl(paramContext, i)
        {
          private CoroutineScope p$;
          
          @NotNull
          public final Continuation<Unit> create(@NotNull CoroutineScope paramAnonymousCoroutineScope, @NotNull Continuation<? super Unit> paramAnonymousContinuation)
          {
            Intrinsics.checkParameterIsNotNull(paramAnonymousCoroutineScope, "$receiver");
            Intrinsics.checkParameterIsNotNull(paramAnonymousContinuation, "continuation");
            paramAnonymousContinuation = new 1($context, i, localBooleanRef, paramAnonymousContinuation);
            p$ = paramAnonymousCoroutineScope;
            return paramAnonymousContinuation;
          }
          
          @Nullable
          public final Object doResume(@Nullable Object paramAnonymousObject, @Nullable Throwable paramAnonymousThrowable)
          {
            paramAnonymousObject = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (label)
            {
            default: 
              throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            case 1: 
              if (paramAnonymousThrowable != null) {
                throw paramAnonymousThrowable;
              }
              break;
            case 0: 
              if (paramAnonymousThrowable != null) {
                throw paramAnonymousThrowable;
              }
              paramAnonymousThrowable = p$;
              paramAnonymousThrowable = LocalStorage.INSTANCE;
              Context localContext = $context;
              int i = i;
              label = 1;
              paramAnonymousThrowable = paramAnonymousThrowable.requestSearchAlias(localContext, i, this);
              InlineMarker.mark(2);
              if (paramAnonymousThrowable == paramAnonymousObject) {
                return paramAnonymousObject;
              }
              break;
            }
            localBooleanRefelement = true;
            return Unit.INSTANCE;
          }
          
          @Nullable
          public final Object invoke(@NotNull CoroutineScope paramAnonymousCoroutineScope, @NotNull Continuation<? super Unit> paramAnonymousContinuation)
          {
            Intrinsics.checkParameterIsNotNull(paramAnonymousCoroutineScope, "$receiver");
            Intrinsics.checkParameterIsNotNull(paramAnonymousContinuation, "continuation");
            return ((1)create(paramAnonymousCoroutineScope, paramAnonymousContinuation)).doResume(Unit.INSTANCE, null);
          }
        }, 2, null);
      }
      i += 1;
    }
    return element;
  }
  
  @NotNull
  public final List<RouteFile> checkForSimilarRoutes(@NotNull LatLng paramLatLng1, @NotNull LatLng paramLatLng2, @Nullable Date paramDate, @NotNull ScheduleType paramScheduleType)
  {
    Intrinsics.checkParameterIsNotNull(paramLatLng1, "originPoint");
    Intrinsics.checkParameterIsNotNull(paramLatLng2, "destinationPoint");
    Intrinsics.checkParameterIsNotNull(paramScheduleType, "scheduleType");
    Object localObject1 = (Iterable)savedRoutes;
    Collection localCollection = (Collection)new ArrayList();
    Iterator localIterator = ((Iterable)localObject1).iterator();
    while (localIterator.hasNext())
    {
      Object localObject2 = localIterator.next();
      localObject1 = (RouteFile)localObject2;
      if ((SakayConstants.INSTANCE.tooNear(new LatLng(getOriginlatitude, getOriginlongitude), paramLatLng1)) && (SakayConstants.INSTANCE.tooNear(new LatLng(getDestinationlatitude, getDestinationlongitude), paramLatLng2)) && (Intrinsics.areEqual(((RouteFile)localObject1).getScheduleType(), paramScheduleType)))
      {
        localObject1 = ((RouteFile)localObject1).getDepartureTime();
        Long localLong = null;
        if (localObject1 != null) {
          localObject1 = Long.valueOf(((Date)localObject1).getTime());
        } else {
          localObject1 = null;
        }
        if (paramDate != null) {
          localLong = Long.valueOf(paramDate.getTime());
        }
        if (Intrinsics.areEqual(localObject1, localLong))
        {
          i = 1;
          break label219;
        }
      }
      int i = 0;
      label219:
      if (i != 0) {
        localCollection.add(localObject2);
      }
    }
    return (List)localCollection;
  }
  
  public final void clickPreviousSearch(int paramInt, long paramLong)
  {
    PreviousSearch localPreviousSearch = (PreviousSearch)previousSearches.get(paramInt);
    localPreviousSearch.setTimesChosen(localPreviousSearch.getTimesChosen() + 1);
    ((PreviousSearch)previousSearches.get(paramInt)).setLastChosen(paramLong);
  }
  
  public final boolean deleteFile(@NotNull Context paramContext, int paramInt)
  {
    Intrinsics.checkParameterIsNotNull(paramContext, "context");
    for (;;)
    {
      int i;
      try
      {
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append("Deleting id: ");
        ((StringBuilder)localObject1).append(paramInt);
        Log.d("SAKAY", ((StringBuilder)localObject1).toString());
        localObject1 = savedRoutes.iterator();
        i = 0;
        if (!((Iterator)localObject1).hasNext()) {
          break label432;
        }
        if (paramInt != ((RouteFile)((Iterator)localObject1).next()).getId()) {
          break label414;
        }
        j = 1;
      }
      catch (Exception paramContext)
      {
        Object localObject2;
        RouteFile localRouteFile;
        Object localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append("Exception occured at deleteFile ");
        ((StringBuilder)localObject1).append(paramContext.getMessage());
        Log.d("SAKAY", ((StringBuilder)localObject1).toString());
        return false;
      }
      if (i >= 0)
      {
        localObject1 = ((RouteFile)savedRoutes.get(i)).getFilename();
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append("Filename:");
        ((StringBuilder)localObject2).append((String)localObject1);
        Log.d("SAKAY", ((StringBuilder)localObject2).toString());
        paramContext.getApplicationContext().deleteFile(((RouteFile)savedRoutes.get(i)).getFilename());
        localObject1 = new ArrayList((Collection)savedRoutes);
        ((ArrayList)localObject1).remove(i);
        savedRoutes = CollectionsKt.toList((Iterable)localObject1);
        paramContext = paramContext.getApplicationContext().openFileOutput(mainFilename, 0);
        Intrinsics.checkExpressionValueIsNotNull(paramContext, "context.applicationConte…me, Context.MODE_PRIVATE)");
        paramContext = new JsonWriter((Writer)new OutputStreamWriter((OutputStream)paramContext, "UTF-8"));
        paramContext.setIndent("  ");
        paramContext.beginArray();
        localObject2 = (Iterable)savedRoutes;
        localObject1 = (Collection)new ArrayList(CollectionsKt.collectionSizeOrDefault((Iterable)localObject2, 10));
        localObject2 = ((Iterable)localObject2).iterator();
        if (((Iterator)localObject2).hasNext())
        {
          localRouteFile = (RouteFile)((Iterator)localObject2).next();
          INSTANCE.writeRouteFile(paramContext, localRouteFile);
          ((Collection)localObject1).add(Unit.INSTANCE);
          continue;
        }
        localObject1 = (List)localObject1;
        paramContext.endArray();
        paramContext.close();
        return true;
      }
      Log.d("SAKAY", "Yaw eh");
      return false;
      label414:
      int j = 0;
      if (j == 0)
      {
        i += 1;
        continue;
        label432:
        i = -1;
      }
    }
  }
  
  public final boolean deleteFile(@NotNull Context paramContext, @NotNull String paramString)
  {
    Intrinsics.checkParameterIsNotNull(paramContext, "context");
    Intrinsics.checkParameterIsNotNull(paramString, "filename");
    try
    {
      paramContext.getApplicationContext().deleteFile(paramString);
      return true;
    }
    catch (Exception paramContext)
    {
      paramString = new StringBuilder();
      paramString.append("Exception occured at deleteFile ");
      paramString.append(paramContext.getMessage());
      Log.d("SAKAY", paramString.toString());
    }
    return false;
  }
  
  public final void deleteHome(@NotNull Context paramContext)
  {
    Intrinsics.checkParameterIsNotNull(paramContext, "context");
    deleteFile(paramContext, homeFilename);
  }
  
  public final void deleteWork(@NotNull Context paramContext)
  {
    Intrinsics.checkParameterIsNotNull(paramContext, "context");
    deleteFile(paramContext, workFilename);
  }
  
  public final void favePreviousSearch(int paramInt)
  {
    ((PreviousSearch)previousSearches.get(paramInt)).setFavorite(true);
  }
  
  @NotNull
  public final String getAnnouncementFilename()
  {
    return announcementFilename;
  }
  
  @NotNull
  public final String getAnnouncementsReadFilename()
  {
    return announcementsReadFilename;
  }
  
  @NotNull
  public final String getConfirmedIncidentsFilename()
  {
    return confirmedIncidentsFilename;
  }
  
  @NotNull
  public final String getDateFormat()
  {
    return dateFormat;
  }
  
  @NotNull
  public final String getDeviceToken(@NotNull Context paramContext)
  {
    Intrinsics.checkParameterIsNotNull(paramContext, "context");
    try
    {
      String str1 = readToken(new JsonReader((Reader)new InputStreamReader((InputStream)paramContext.getApplicationContext().openFileInput(tokenFilename))));
      if (str1 != null) {
        return str1;
      }
    }
    catch (Exception localException)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("LocalStorage::getDeviceToken exception getting token: ");
      localStringBuilder.append(localException.getMessage());
      Log.e("SAKAY", localStringBuilder.toString());
      String str2 = UUID.randomUUID().toString();
      Intrinsics.checkExpressionValueIsNotNull(str2, "newToken");
      writeToken(paramContext, str2);
      return str2;
    }
  }
  
  @Nullable
  public final Terminal getHome(@NotNull Context paramContext)
  {
    Intrinsics.checkParameterIsNotNull(paramContext, "context");
    paramContext = readFile(paramContext, homeFilename);
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append("LocalStorage::getHome text: ");
    ((StringBuilder)localObject).append((String)paramContext.getFirst());
    Log.d("SAKAY", ((StringBuilder)localObject).toString());
    if (((Boolean)paramContext.getSecond()).booleanValue()) {
      try
      {
        localObject = new JSONObject((String)paramContext.getFirst());
        double d1 = ((JSONObject)localObject).getDouble("latitude");
        double d2 = ((JSONObject)localObject).getDouble("longitude");
        paramContext = ((JSONObject)localObject).optString("description");
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("s2:");
        localStringBuilder.append(S2LatLng.fromDegrees(d1, d2).toPoint().hashCode());
        localObject = ((JSONObject)localObject).optString("provider", localStringBuilder.toString());
        paramContext = new Terminal(new LatLng(d1, d2), paramContext, (String)localObject);
        return paramContext;
      }
      catch (Exception paramContext)
      {
        localObject = new StringBuilder();
        ((StringBuilder)localObject).append("LocalStorage::getHome encountered an error: ");
        ((StringBuilder)localObject).append(paramContext.getMessage());
        Log.e("SAKAY", ((StringBuilder)localObject).toString());
      }
    }
    return null;
  }
  
  @NotNull
  public final String getHomeFilename()
  {
    return homeFilename;
  }
  
  @NotNull
  public final String getIncidentsFilename()
  {
    return incidentsFilename;
  }
  
  @Nullable
  public final Date getLastModified(@NotNull Context paramContext, @NotNull String paramString)
  {
    Intrinsics.checkParameterIsNotNull(paramContext, "context");
    Intrinsics.checkParameterIsNotNull(paramString, "filename");
    try
    {
      paramContext = new Date(new File(paramString).lastModified());
      return paramContext;
    }
    catch (Exception paramContext)
    {
      for (;;) {}
    }
    return null;
  }
  
  @NotNull
  public final String getMainFilename()
  {
    return mainFilename;
  }
  
  @NotNull
  public final ArrayList<PreviousSearch> getPreviousSearches()
  {
    return previousSearches;
  }
  
  public final boolean getPreviousSearches(@NotNull Context paramContext)
  {
    Intrinsics.checkParameterIsNotNull(paramContext, "context");
    paramContext = readFile(paramContext, previousSearchesFilename);
    boolean bool1 = ((Boolean)paramContext.getSecond()).booleanValue();
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append("LocalStorage::getSeparatedPreviousSearches text: ");
    ((StringBuilder)localObject).append((String)paramContext.getFirst());
    Log.d("SAKAY", ((StringBuilder)localObject).toString());
    if (bool1) {}
    for (;;)
    {
      try
      {
        paramContext = new JSONArray((String)paramContext.getFirst());
        previousSearches.clear();
        int i = paramContext.length();
        int j = 0;
        if (j < i)
        {
          localObject = paramContext.getJSONObject(j);
          double d1 = ((JSONObject)localObject).getDouble("latitude");
          double d2 = ((JSONObject)localObject).getDouble("longitude");
          boolean bool2 = ((JSONObject)localObject).optBoolean("isGeocoded", false);
          String str = ((JSONObject)localObject).optString("description");
          boolean bool3 = ((JSONObject)localObject).optBoolean("isFavorite", false);
          long l = ((JSONObject)localObject).optLong("lastChosen", 0L);
          int m = ((JSONObject)localObject).optInt("timesChosen", 0);
          localObject = ((JSONObject)localObject).optString("provider");
          CharSequence localCharSequence = (CharSequence)localObject;
          if (localCharSequence == null) {
            break label381;
          }
          if (!StringsKt.isBlank(localCharSequence)) {
            break label375;
          }
          break label381;
          if (k != 0)
          {
            localObject = new StringBuilder();
            ((StringBuilder)localObject).append("s2:");
            ((StringBuilder)localObject).append(S2LatLng.fromDegrees(d1, d2).toPoint().hashCode());
            localObject = ((StringBuilder)localObject).toString();
          }
          previousSearches.add(new PreviousSearch(j, new LatLng(d1, d2), str, bool2, bool3, l, m, (String)localObject));
          j += 1;
          continue;
        }
        sortPreviousSearches();
      }
      catch (Exception paramContext)
      {
        localObject = new StringBuilder();
        ((StringBuilder)localObject).append("LocalStorage::getSeparatedPreviousSearches encountered an error: ");
        ((StringBuilder)localObject).append(paramContext.getMessage());
        Log.e("SAKAY", ((StringBuilder)localObject).toString());
        return false;
      }
      return bool1;
      label375:
      int k = 0;
      continue;
      label381:
      k = 1;
    }
  }
  
  @NotNull
  public final String getPreviousSearchesFilename()
  {
    return previousSearchesFilename;
  }
  
  @Nullable
  public final RouteFile getRouteById(int paramInt)
  {
    Iterator localIterator = ((Iterable)savedRoutes).iterator();
    while (localIterator.hasNext())
    {
      localObject = localIterator.next();
      int i;
      if (((RouteFile)localObject).getId() == paramInt) {
        i = 1;
      } else {
        i = 0;
      }
      if (i != 0) {
        break label58;
      }
    }
    Object localObject = null;
    label58:
    return (RouteFile)localObject;
  }
  
  @NotNull
  public final String getTokenFilename()
  {
    return tokenFilename;
  }
  
  @Nullable
  public final Terminal getWork(@NotNull Context paramContext)
  {
    Intrinsics.checkParameterIsNotNull(paramContext, "context");
    paramContext = readFile(paramContext, workFilename);
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append("LocalStorage::getWork text: ");
    ((StringBuilder)localObject).append((String)paramContext.getFirst());
    Log.d("SAKAY", ((StringBuilder)localObject).toString());
    if (((Boolean)paramContext.getSecond()).booleanValue()) {
      try
      {
        localObject = new JSONObject((String)paramContext.getFirst());
        double d1 = ((JSONObject)localObject).getDouble("latitude");
        double d2 = ((JSONObject)localObject).getDouble("longitude");
        paramContext = ((JSONObject)localObject).optString("description");
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("s2:");
        localStringBuilder.append(S2LatLng.fromDegrees(d1, d2).toPoint().hashCode());
        localObject = ((JSONObject)localObject).optString("provider", localStringBuilder.toString());
        paramContext = new Terminal(new LatLng(d1, d2), paramContext, (String)localObject);
        return paramContext;
      }
      catch (Exception paramContext)
      {
        localObject = new StringBuilder();
        ((StringBuilder)localObject).append("LocalStorage::getWork encountered an error: ");
        ((StringBuilder)localObject).append(paramContext.getMessage());
        Log.e("SAKAY", ((StringBuilder)localObject).toString());
      }
    }
    return null;
  }
  
  @NotNull
  public final String getWorkFilename()
  {
    return workFilename;
  }
  
  public final boolean hasSimilarSearches(@NotNull LatLng paramLatLng)
  {
    Intrinsics.checkParameterIsNotNull(paramLatLng, "latLng");
    Iterator localIterator = ((Iterable)previousSearches).iterator();
    while (localIterator.hasNext())
    {
      PreviousSearch localPreviousSearch = (PreviousSearch)localIterator.next();
      if (SakayConstants.INSTANCE.tooNear(paramLatLng, localPreviousSearch.getLatLng())) {
        return true;
      }
    }
    return false;
  }
  
  public final void initialize(@NotNull Context paramContext)
  {
    Intrinsics.checkParameterIsNotNull(paramContext, "context");
    getPreviousSearches(paramContext);
    Object localObject = paramContext.getApplicationContext().getSystemService("connectivity");
    if (localObject == null) {
      throw new TypeCastException("null cannot be cast to non-null type android.net.ConnectivityManager");
    }
    localObject = (ConnectivityManager)localObject;
    if (((ConnectivityManager)localObject).getActiveNetworkInfo() != null)
    {
      localObject = ((ConnectivityManager)localObject).getActiveNetworkInfo();
      Intrinsics.checkExpressionValueIsNotNull(localObject, "connectivityManager.activeNetworkInfo");
      if (((NetworkInfo)localObject).isConnectedOrConnecting())
      {
        paramContext = readFile(paramContext, "offline-log.json");
        if ((((Boolean)paramContext.getSecond()).booleanValue()) && ((StringsKt.isBlank((CharSequence)paramContext.getFirst()) ^ true)))
        {
          localObject = initialize.1.INSTANCE;
          localObject = initialize.2.INSTANCE;
          try
          {
            Sakay.INSTANCE.reportOfflineSearches(new JSONArray((String)paramContext.getFirst()), (Function1)initialize.3.INSTANCE, (Function2)initialize.4.INSTANCE);
            return;
          }
          catch (Exception paramContext)
          {
            localObject = new StringBuilder();
            ((StringBuilder)localObject).append("LocalStorage::initialize error: ");
            ((StringBuilder)localObject).append(paramContext.getMessage());
            Log.d("SAKAY", ((StringBuilder)localObject).toString());
          }
        }
      }
    }
  }
  
  @NotNull
  public final List<Announcement> loadAnnouncements(@NotNull Context paramContext)
  {
    Intrinsics.checkParameterIsNotNull(paramContext, "context");
    Object localObject = CollectionsKt.emptyList();
    try
    {
      JsonReader localJsonReader = new JsonReader((Reader)new InputStreamReader((InputStream)paramContext.openFileInput(announcementFilename), "UTF-8"));
      paramContext = readAnnouncements(localJsonReader);
      try
      {
        localJsonReader.close();
        return paramContext;
      }
      catch (Exception localException1) {}
      localObject = new StringBuilder();
    }
    catch (Exception localException2)
    {
      paramContext = (Context)localObject;
    }
    ((StringBuilder)localObject).append("Exception occured while loading announcements ");
    ((StringBuilder)localObject).append(localException2.getMessage());
    Log.d("SAKAY", ((StringBuilder)localObject).toString());
    return paramContext;
  }
  
  @NotNull
  public final Map<Integer, Boolean> loadAnnouncementsRead(@NotNull Context paramContext)
  {
    Intrinsics.checkParameterIsNotNull(paramContext, "context");
    Object localObject = MapsKt.emptyMap();
    try
    {
      JsonReader localJsonReader = new JsonReader((Reader)new InputStreamReader((InputStream)paramContext.openFileInput(announcementsReadFilename), "UTF-8"));
      paramContext = readAnnouncementsRead(localJsonReader);
      try
      {
        localJsonReader.close();
        return paramContext;
      }
      catch (Exception localException1) {}
      localObject = new StringBuilder();
    }
    catch (Exception localException2)
    {
      paramContext = (Context)localObject;
    }
    ((StringBuilder)localObject).append("Exception occured while loading announcements ");
    ((StringBuilder)localObject).append(localException2.getMessage());
    Log.d("SAKAY", ((StringBuilder)localObject).toString());
    return paramContext;
  }
  
  @Nullable
  public final RouteInfo loadSavedRoute(@NotNull Context paramContext, @NotNull RouteFile paramRouteFile, @Nullable String paramString)
  {
    Intrinsics.checkParameterIsNotNull(paramContext, "context");
    Intrinsics.checkParameterIsNotNull(paramRouteFile, "routeFile");
    Object localObject1 = readFile(paramContext, paramRouteFile.getFilename());
    if (((Boolean)((Pair)localObject1).getSecond()).booleanValue())
    {
      Object localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append("LocalStorage::loadSavedRoute ");
      ((StringBuilder)localObject2).append((String)((Pair)localObject1).getFirst());
      Log.d("SAKAY", ((StringBuilder)localObject2).toString());
      Object localObject4 = new JSONObject((String)((Pair)localObject1).getFirst());
      localObject1 = ((JSONObject)localObject4).getJSONObject("origin");
      Intrinsics.checkExpressionValueIsNotNull(localObject1, "dataJson.getJSONObject(\"origin\")");
      localObject2 = new LatLng(((JSONObject)localObject1).getDouble("latitude"), ((JSONObject)localObject1).getDouble("longitude"));
      Object localObject3 = paramRouteFile.getOriginAlias();
      Object localObject5 = new StringBuilder();
      ((StringBuilder)localObject5).append("s2:");
      ((StringBuilder)localObject5).append(S2LatLng.fromDegrees(latitude, longitude).toPoint().hashCode());
      localObject5 = new Terminal((LatLng)localObject2, (String)localObject3, ((JSONObject)localObject1).optString("provider", ((StringBuilder)localObject5).toString()));
      localObject2 = ((JSONObject)localObject4).getJSONObject("destination");
      Intrinsics.checkExpressionValueIsNotNull(localObject2, "dataJson.getJSONObject(\"destination\")");
      localObject2 = new LatLng(((JSONObject)localObject2).getDouble("latitude"), ((JSONObject)localObject2).getDouble("longitude"));
      localObject3 = paramRouteFile.getDestinationAlias();
      Object localObject6 = new StringBuilder();
      ((StringBuilder)localObject6).append("s2:");
      ((StringBuilder)localObject6).append(S2LatLng.fromDegrees(latitude, longitude).toPoint().hashCode());
      localObject6 = new Terminal((LatLng)localObject2, (String)localObject3, ((JSONObject)localObject1).optString("provider", ((StringBuilder)localObject6).toString()));
      localObject1 = (Date)null;
      localObject2 = ScheduleType.DEPARTURE;
      if (((JSONObject)localObject4).has("departureTime"))
      {
        localObject1 = ((JSONObject)localObject4).getString("departureTime");
        Intrinsics.checkExpressionValueIsNotNull(localObject1, "scheduleString");
        localObject2 = parseDate((String)localObject1);
        localObject1 = ((JSONObject)localObject4).getString("scheduleType");
        if ((localObject1 != null) && (((String)localObject1).hashCode() == 930446297) && (((String)localObject1).equals("Arrival"))) {
          localObject1 = ScheduleType.ARRIVAL;
        } else {
          localObject1 = ScheduleType.DEPARTURE;
        }
        localObject3 = localObject1;
        localObject1 = localObject2;
        localObject2 = localObject3;
      }
      localObject3 = SakayJsonParser.INSTANCE.parseItineraryList((JSONObject)localObject4);
      localObject4 = CollectionsKt.emptyList();
      if (paramRouteFile.getSearchId() != null) {
        addOfflineSearchLog(paramContext, paramRouteFile.getSearchId().longValue(), System.currentTimeMillis() / 'Ϩ', paramString);
      }
      return new RouteInfo((Terminal)localObject5, (Terminal)localObject6, paramRouteFile.getSearchId(), (Date)localObject1, (ScheduleType)localObject2, (List)localObject3, (List)localObject4);
    }
    return null;
  }
  
  @NotNull
  public final List<RouteFile> loadSavedRoutesList(@NotNull Context paramContext)
  {
    Intrinsics.checkParameterIsNotNull(paramContext, "context");
    try
    {
      paramContext = readFile(paramContext, mainFilename);
      if (((Boolean)paramContext.getSecond()).booleanValue()) {
        savedRoutes = readSavedRoutes(new JSONArray((String)paramContext.getFirst()));
      }
    }
    catch (Exception paramContext)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("LocalStorage::loadSavedRoutesList occured while loading announcements ");
      localStringBuilder.append(paramContext.getMessage());
      Log.d("SAKAY", localStringBuilder.toString());
      savedRoutes = CollectionsKt.emptyList();
    }
    return savedRoutes;
  }
  
  @NotNull
  public final Pair<String, Boolean> readFile(@NotNull Context paramContext, @NotNull String paramString)
  {
    Intrinsics.checkParameterIsNotNull(paramContext, "context");
    Intrinsics.checkParameterIsNotNull(paramString, "filename");
    StringBuilder localStringBuilder = new StringBuilder();
    boolean bool;
    try
    {
      Log.d("SAKAY", "LocalStorage::readFile");
      paramString = new BufferedReader((Reader)new InputStreamReader((InputStream)new BufferedInputStream((InputStream)paramContext.openFileInput(paramString))));
      for (paramContext = paramString.readLine(); paramContext != null; paramContext = paramString.readLine()) {
        localStringBuilder.append(paramContext);
      }
      bool = true;
    }
    catch (Exception paramContext)
    {
      paramString = new StringBuilder();
      paramString.append("LocalStorage::readFile Exception occured ");
      paramString.append(paramContext.getMessage());
      Log.e("SAKAY", paramString.toString());
      bool = false;
    }
    return new Pair(localStringBuilder.toString(), Boolean.valueOf(bool));
  }
  
  @Nullable
  public final String readToken(@NotNull JsonReader paramJsonReader)
  {
    Intrinsics.checkParameterIsNotNull(paramJsonReader, "reader");
    String str = (String)null;
    paramJsonReader.beginObject();
    while (paramJsonReader.hasNext()) {
      if (Intrinsics.areEqual(paramJsonReader.nextName(), "token")) {
        str = paramJsonReader.nextString();
      }
    }
    paramJsonReader.endObject();
    return str;
  }
  
  @Nullable
  final Object requestSearchAlias(@NotNull Context paramContext, int paramInt, @NotNull Continuation<? super Unit> paramContinuation)
  {
    paramContext = new Geocoder(paramContext);
    paramContinuation = ((PreviousSearch)previousSearches.get(paramInt)).getLatLng();
    paramContext = paramContext.getFromLocation(latitude, longitude, 1);
    paramContinuation = (PreviousSearch)previousSearches.get(paramInt);
    Intrinsics.checkExpressionValueIsNotNull(paramContext, "results");
    paramContinuation.setDescription(getAddress(paramContext));
    ((PreviousSearch)previousSearches.get(paramInt)).setGeocoded(true);
    return Unit.INSTANCE;
  }
  
  public final void saveAnnouncements(@NotNull Context paramContext, @NotNull List<Announcement> paramList, long paramLong)
  {
    Intrinsics.checkParameterIsNotNull(paramContext, "context");
    Intrinsics.checkParameterIsNotNull(paramList, "announcements");
    try
    {
      paramContext = new JsonWriter((Writer)new OutputStreamWriter((OutputStream)paramContext.openFileOutput(announcementFilename, 0), "UTF-8"));
      paramContext.setIndent("  ");
      writeAnnouncements(paramContext, paramList, paramLong);
      paramContext.close();
      return;
    }
    catch (Exception paramContext)
    {
      paramList = new StringBuilder();
      paramList.append("Exception occured while saving announcements ");
      paramList.append(paramContext.getMessage());
      Log.d("SAKAY", paramList.toString());
    }
  }
  
  public final void saveAnnouncementsRead(@NotNull Context paramContext, @NotNull Map<Integer, Boolean> paramMap)
  {
    Intrinsics.checkParameterIsNotNull(paramContext, "context");
    Intrinsics.checkParameterIsNotNull(paramMap, "announcementsRead");
    try
    {
      paramContext = new JsonWriter((Writer)new OutputStreamWriter((OutputStream)paramContext.openFileOutput(announcementsReadFilename, 0), "UTF-8"));
      paramContext.setIndent("  ");
      writeAnnouncementsRead(paramContext, paramMap);
      paramContext.close();
      return;
    }
    catch (Exception paramContext)
    {
      paramMap = new StringBuilder();
      paramMap.append("Exception occured while saving announcements ");
      paramMap.append(paramContext.getMessage());
      Log.d("SAKAY", paramMap.toString());
    }
  }
  
  public final void savePreviousSearches(@NotNull Context paramContext)
  {
    Intrinsics.checkParameterIsNotNull(paramContext, "context");
    if (previousSearches.size() > 0) {
      sortPreviousSearches();
    }
    for (;;)
    {
      try
      {
        localObject = new StringBuilder();
        ((StringBuilder)localObject).append("LocalStorage::savePreviousSearches separatedPreviousSearches size = ");
        ((StringBuilder)localObject).append(String.valueOf(previousSearches.size()));
        Log.d("SAKAY", ((StringBuilder)localObject).toString());
        localObject = previousSearchesFilename;
        int i = 0;
        localObject = new JsonWriter((Writer)new OutputStreamWriter((OutputStream)paramContext.openFileOutput((String)localObject, 0), "UTF-8"));
        ((JsonWriter)localObject).setIndent("  ");
        ((JsonWriter)localObject).beginArray();
        int j = Math.min(previousSearches.size(), 100);
        if (i < j)
        {
          PreviousSearch localPreviousSearch = (PreviousSearch)previousSearches.get(i);
          ((JsonWriter)localObject).beginObject();
          writeLatLng((JsonWriter)localObject, localPreviousSearch.getLatLng());
          ((JsonWriter)localObject).name("isGeocoded").value(localPreviousSearch.isGeocoded());
          JsonWriter localJsonWriter = ((JsonWriter)localObject).name("description");
          paramContext = localPreviousSearch.getDescription();
          if (paramContext == null) {
            break label352;
          }
          localJsonWriter.value(paramContext);
          ((JsonWriter)localObject).name("isFavorite").value(localPreviousSearch.isFavorite());
          ((JsonWriter)localObject).name("lastChosen").value(localPreviousSearch.getLastChosen());
          ((JsonWriter)localObject).name("timesChosen").value((Number)Integer.valueOf(localPreviousSearch.getTimesChosen()));
          ((JsonWriter)localObject).name("provider").value(localPreviousSearch.getProvider());
          ((JsonWriter)localObject).endObject();
          i += 1;
          continue;
        }
        ((JsonWriter)localObject).endArray();
        ((JsonWriter)localObject).close();
        return;
      }
      catch (Exception paramContext)
      {
        Object localObject = new StringBuilder();
        ((StringBuilder)localObject).append("LocalStorage::savePreviousSearches encountered an error: ");
        ((StringBuilder)localObject).append(paramContext.getMessage());
        Log.e("SAKAY", ((StringBuilder)localObject).toString());
      }
      return;
      label352:
      paramContext = "";
    }
  }
  
  public final boolean saveRoute(@NotNull Context paramContext, @NotNull Terminal paramTerminal1, @NotNull Terminal paramTerminal2, @Nullable Long paramLong, @NotNull List<Itinerary> paramList, @NotNull List<Incident> paramList1, @Nullable Date paramDate, @NotNull ScheduleType paramScheduleType, @NotNull String paramString, @NotNull List<Integer> paramList2, long paramLong1)
  {
    Intrinsics.checkParameterIsNotNull(paramContext, "context");
    Intrinsics.checkParameterIsNotNull(paramTerminal1, "origin");
    Intrinsics.checkParameterIsNotNull(paramTerminal2, "destination");
    Intrinsics.checkParameterIsNotNull(paramList, "itineraries");
    Intrinsics.checkParameterIsNotNull(paramList1, "incidents");
    Intrinsics.checkParameterIsNotNull(paramScheduleType, "scheduleType");
    Intrinsics.checkParameterIsNotNull(paramString, "routeName");
    Intrinsics.checkParameterIsNotNull(paramList2, "routeIdsOverwritten");
    Object localObject1 = new Object[1];
    paramList1 = paramTerminal1.getLocation();
    if (paramList1 != null) {
      paramList1 = Double.valueOf(latitude);
    } else {
      paramList1 = null;
    }
    localObject1[0] = paramList1;
    localObject1 = String.format("%.6f", Arrays.copyOf((Object[])localObject1, localObject1.length));
    Intrinsics.checkExpressionValueIsNotNull(localObject1, "java.lang.String.format(this, *args)");
    Object localObject2 = new Object[1];
    paramList1 = paramTerminal1.getLocation();
    if (paramList1 != null) {
      paramList1 = Double.valueOf(longitude);
    } else {
      paramList1 = null;
    }
    localObject2[0] = paramList1;
    localObject2 = String.format("%.6f", Arrays.copyOf((Object[])localObject2, localObject2.length));
    Intrinsics.checkExpressionValueIsNotNull(localObject2, "java.lang.String.format(this, *args)");
    Object localObject3 = new Object[1];
    paramList1 = paramTerminal2.getLocation();
    if (paramList1 != null) {
      paramList1 = Double.valueOf(longitude);
    } else {
      paramList1 = null;
    }
    localObject3[0] = paramList1;
    localObject3 = String.format("%.6f", Arrays.copyOf((Object[])localObject3, localObject3.length));
    Intrinsics.checkExpressionValueIsNotNull(localObject3, "java.lang.String.format(this, *args)");
    Object localObject4 = new Object[1];
    paramList1 = paramTerminal2.getLocation();
    if (paramList1 != null) {
      paramList1 = Double.valueOf(longitude);
    } else {
      paramList1 = null;
    }
    localObject4[0] = paramList1;
    localObject4 = String.format("%.6f", Arrays.copyOf((Object[])localObject4, localObject4.length));
    Intrinsics.checkExpressionValueIsNotNull(localObject4, "java.lang.String.format(this, *args)");
    if ((((Collection)paramList2).isEmpty() ^ true)) {}
    for (localObject1 = "eww";; localObject1 = paramList2.toString())
    {
      break;
      if (paramDate == null)
      {
        paramList1 = "";
      }
      else
      {
        paramList1 = new SimpleDateFormat("yy_MM_dd_Hm", Locale.getDefault());
        paramList2 = new StringBuilder();
        paramList2.append(paramList1.format(paramDate));
        if (Intrinsics.areEqual(paramScheduleType, ScheduleType.DEPARTURE)) {
          paramList1 = "_depart";
        } else {
          paramList1 = "_arrive";
        }
        paramList2.append(paramList1);
        paramList1 = paramList2.toString();
      }
      paramList2 = new StringBuilder();
      paramList2.append((String)localObject1);
      paramList2.append(",");
      paramList2.append((String)localObject2);
      paramList2.append("-");
      paramList2.append((String)localObject3);
      paramList2.append(",");
      paramList2.append((String)localObject4);
      paramList2.append(paramList1);
    }
    paramList2 = ((Itinerary)paramList.get(0)).getLegs();
    if (paramTerminal1.getDescription() != null)
    {
      paramList1 = paramTerminal1.getDescription();
      if (paramList1 == null) {
        Intrinsics.throwNpe();
      }
    }
    else if (((TripLeg)paramList2.get(0)).getFrom().getName() != null)
    {
      paramList1 = ((TripLeg)paramList2.get(0)).getFrom().getName();
      if (paramList1 == null) {
        paramList1 = "";
      }
    }
    else
    {
      paramList1 = "origin";
    }
    if (paramTerminal2.getDescription() != null)
    {
      localObject2 = paramTerminal2.getDescription();
      paramList2 = (List<Integer>)localObject2;
      if (localObject2 == null)
      {
        Intrinsics.throwNpe();
        paramList2 = (List<Integer>)localObject2;
      }
    }
    for (;;)
    {
      break;
      if (((TripLeg)paramList2.get(paramList2.size() - 1)).getTo().getName() != null)
      {
        paramList2 = ((TripLeg)paramList2.get(paramList2.size() - 1)).getTo().getName();
        if (paramList2 == null) {
          paramList2 = "";
        }
      }
      else
      {
        paramList2 = "destination";
      }
    }
    try
    {
      localObject2 = paramContext.openFileOutput((String)localObject1, 0);
      Intrinsics.checkExpressionValueIsNotNull(localObject2, "context.openFileOutput(f…me, Context.MODE_PRIVATE)");
      localObject3 = new JsonWriter((Writer)new OutputStreamWriter((OutputStream)localObject2, "UTF-8"));
      ((JsonWriter)localObject3).setIndent("  ");
      ((JsonWriter)localObject3).beginObject();
      localObject4 = paramTerminal1.getLocation();
      if (localObject4 == null) {
        try
        {
          Intrinsics.throwNpe();
        }
        catch (Exception paramList)
        {
          break label968;
        }
      }
      localObject2 = this;
      try
      {
        ((LocalStorage)localObject2).writeOrigin((JsonWriter)localObject3, (LatLng)localObject4, paramList1);
        localObject4 = paramTerminal2.getLocation();
        if (localObject4 == null) {
          Intrinsics.throwNpe();
        }
        ((LocalStorage)localObject2).writeDestination((JsonWriter)localObject3, (LatLng)localObject4, paramList2);
        if (paramDate != null)
        {
          localObject4 = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z", Locale.getDefault());
          ((JsonWriter)localObject3).name("departureTime").value(((SimpleDateFormat)localObject4).format(paramDate));
          ((JsonWriter)localObject3).name("scheduleType");
          if (LocalStorage.WhenMappings.$EnumSwitchMapping$0[paramScheduleType.ordinal()] != 1) {
            ((JsonWriter)localObject3).value("Departure");
          } else {
            ((JsonWriter)localObject3).value("Arrival");
          }
        }
        else
        {
          Log.d("SAKAY", "departureTime is null");
        }
        ((LocalStorage)localObject2).writeItineraries((JsonWriter)localObject3, paramList);
        ((JsonWriter)localObject3).endObject();
        ((JsonWriter)localObject3).close();
        bool = true;
      }
      catch (Exception paramList) {}
      localObject2 = new StringBuilder();
    }
    catch (Exception paramList) {}
    label968:
    ((StringBuilder)localObject2).append("LocalStorage::saveRoute saving route error");
    ((StringBuilder)localObject2).append(paramList.getMessage());
    Log.d("SAKAY", ((StringBuilder)localObject2).toString());
    boolean bool = false;
    savedRoutes = loadSavedRoutesList(paramContext);
    try
    {
      paramContext = paramContext.openFileOutput(mainFilename, 0);
      Intrinsics.checkExpressionValueIsNotNull(paramContext, "context.openFileOutput(m…me, Context.MODE_PRIVATE)");
      paramContext = new JsonWriter((Writer)new OutputStreamWriter((OutputStream)paramContext, "UTF-8"));
      paramContext.setIndent("  ");
      paramContext.beginArray();
      paramList = paramTerminal1.getLocation();
      if (paramList == null) {
        try
        {
          Intrinsics.throwNpe();
        }
        catch (Exception paramContext)
        {
          break label1374;
        }
      }
      double d = latitude;
      paramTerminal1 = paramTerminal1.getLocation();
      if (paramTerminal1 == null) {
        Intrinsics.throwNpe();
      }
      try
      {
        paramTerminal1 = new LatLng(d, longitude);
        paramList = paramTerminal2.getLocation();
        if (paramList == null) {
          try
          {
            Intrinsics.throwNpe();
          }
          catch (Exception paramContext)
          {
            break label1374;
          }
        }
        d = latitude;
        paramTerminal2 = paramTerminal2.getLocation();
        if (paramTerminal2 == null) {
          Intrinsics.throwNpe();
        }
        paramTerminal2 = new LatLng(d, longitude);
        paramTerminal1 = new RouteFile(paramLong, (String)localObject1, paramTerminal1, paramList1, paramList1, paramTerminal2, paramList2, paramList2, paramDate, paramScheduleType, 0, false, paramString, paramLong1, 3072, null);
        paramTerminal2 = new ArrayList();
        paramTerminal2.add(paramTerminal1);
        paramTerminal2.addAll((Collection)savedRoutes);
        savedRoutes = CollectionsKt.toList((Iterable)paramTerminal2);
        int j = savedRoutes.size();
        int i = 0;
        for (;;)
        {
          if (i < j)
          {
            ((RouteFile)savedRoutes.get(i)).setId(i);
            paramTerminal1 = new StringBuilder();
            paramTerminal1.append("LocalStorage::saveRoute ");
            paramTerminal1.append(i);
            Log.d("SAKAY", paramTerminal1.toString());
            paramTerminal1 = (RouteFile)savedRoutes.get(i);
          }
          try
          {
            writeRouteFile(paramContext, paramTerminal1);
            i += 1;
          }
          catch (Exception paramContext)
          {
            break label1374;
          }
        }
        paramContext.endArray();
        paramContext.close();
        return bool;
      }
      catch (Exception paramContext) {}
      paramTerminal1 = new StringBuilder();
    }
    catch (Exception paramContext) {}
    label1374:
    paramTerminal1.append("LocalStorage::saveRoute Exception occured while saving route master list: ");
    paramTerminal1.append(paramContext.getMessage());
    Log.d("SAKAY", paramTerminal1.toString());
    return bool;
  }
  
  public final void sortPreviousSearches()
  {
    List localList = (List)previousSearches;
    if (localList.size() > 1) {
      CollectionsKt.sortWith(localList, (Comparator)new Comparator()
      {
        public final int compare(T paramAnonymousT1, T paramAnonymousT2)
        {
          paramAnonymousT2 = (PreviousSearch)paramAnonymousT2;
          long l1 = paramAnonymousT2.getLastChosen();
          long l2 = 'Ϩ';
          paramAnonymousT2 = (Comparable)Long.valueOf(l1 / l2 + TimeUnit.DAYS.toSeconds(paramAnonymousT2.getTimesChosen()));
          paramAnonymousT1 = (PreviousSearch)paramAnonymousT1;
          return ComparisonsKt.compareValues(paramAnonymousT2, (Comparable)Long.valueOf(paramAnonymousT1.getLastChosen() / l2 + TimeUnit.DAYS.toSeconds(paramAnonymousT1.getTimesChosen())));
        }
      });
    }
  }
  
  public final void unfavePreviousSearch(int paramInt)
  {
    ((PreviousSearch)previousSearches.get(paramInt)).setFavorite(false);
  }
  
  public final void updateRouteAliases(@NotNull Context paramContext)
  {
    Intrinsics.checkParameterIsNotNull(paramContext, "context");
    changeRouteAliases(paramContext);
    paramContext = Unit.INSTANCE;
  }
  
  public final void writeHome(@NotNull Context paramContext, @NotNull LatLng paramLatLng, @Nullable String paramString1, @Nullable String paramString2, boolean paramBoolean)
  {
    Intrinsics.checkParameterIsNotNull(paramContext, "context");
    Intrinsics.checkParameterIsNotNull(paramLatLng, "latLng");
    try
    {
      paramContext = new JsonWriter((Writer)new OutputStreamWriter((OutputStream)paramContext.openFileOutput(homeFilename, 0), "UTF-8"));
      paramContext.setIndent("  ");
      paramContext.beginObject();
      writeLatLng(paramContext, paramLatLng);
      if ((paramBoolean) && (paramString1 != null))
      {
        paramContext.name("description").value(paramString1);
        paramContext.name("provider").value(paramString2);
      }
      paramContext.endObject();
      paramContext.close();
      return;
    }
    catch (Exception paramContext)
    {
      paramLatLng = new StringBuilder();
      paramLatLng.append("LocalStorage::writeHome encountered an error: ");
      paramLatLng.append(paramContext.getMessage());
      Log.e("SAKAY", paramLatLng.toString());
    }
  }
  
  public final void writeItinerary(@NotNull JsonWriter paramJsonWriter, @NotNull Itinerary paramItinerary)
  {
    Intrinsics.checkParameterIsNotNull(paramJsonWriter, "writer");
    Intrinsics.checkParameterIsNotNull(paramItinerary, "itinerary");
    Log.d("SAKAY", "LocalStorage::writeItinerary WRITING ITINERARY");
    paramJsonWriter.beginObject();
    Log.d("SAKAY", "LocalStorage::writeItinerary beginning object");
    paramJsonWriter.name("waitingTime").value((Number)Integer.valueOf(paramItinerary.getWaitingTime()));
    Log.d("SAKAY", "LocalStorage::writeItinerary waitingTime done");
    paramJsonWriter.name("startTime").value(paramItinerary.getStartTime());
    Log.d("SAKAY", "LocalStorage::writeItinerary startTime done");
    paramJsonWriter.name("endTime").value(paramItinerary.getEndTime());
    paramJsonWriter.name("elevationGained").value(paramItinerary.getElevationGained());
    paramJsonWriter.name("duration").value(paramItinerary.getDuration());
    paramJsonWriter.name("walkLimitExceeded").value(paramItinerary.getWalkLimitExceeded());
    paramJsonWriter.name("tooSloped").value(paramItinerary.getTooSloped());
    paramJsonWriter.name("walkTime").value((Number)Integer.valueOf(paramItinerary.getWalkTime()));
    paramJsonWriter.name("walkDistance").value(paramItinerary.getWalkDistance());
    Log.d("SAKAY", "walkDistance");
    paramJsonWriter.name("legs");
    paramJsonWriter.beginArray();
    Log.d("SAKAY", "LocalStorage::writeItinerary WRITING ITINERARY: legs began array");
    Object localObject2 = (Iterable)paramItinerary.getLegs();
    Object localObject1 = (Collection)new ArrayList(CollectionsKt.collectionSizeOrDefault((Iterable)localObject2, 10));
    localObject2 = ((Iterable)localObject2).iterator();
    while (((Iterator)localObject2).hasNext())
    {
      TripLeg localTripLeg = (TripLeg)((Iterator)localObject2).next();
      INSTANCE.writeTripLeg(paramJsonWriter, localTripLeg);
      ((Collection)localObject1).add(Unit.INSTANCE);
    }
    localObject1 = (List)localObject1;
    paramJsonWriter.endArray();
    paramJsonWriter.name("transfers").value((Number)Integer.valueOf(paramItinerary.getTransfers()));
    paramJsonWriter.name("transitTime").value(paramItinerary.getTransitTime());
    paramJsonWriter.name("elevationLost").value((Number)Integer.valueOf(paramItinerary.getElevationLost()));
    paramJsonWriter.endObject();
  }
  
  public final void writeToken(@NotNull Context paramContext, @NotNull String paramString)
  {
    Intrinsics.checkParameterIsNotNull(paramContext, "context");
    Intrinsics.checkParameterIsNotNull(paramString, "token");
    try
    {
      paramContext = new JsonWriter((Writer)new OutputStreamWriter((OutputStream)paramContext.openFileOutput(tokenFilename, 0), "UTF-8"));
      paramContext.setIndent("  ");
      paramContext.beginObject();
      paramContext.name("token").value(paramString);
      paramContext.endObject();
      paramContext.close();
      return;
    }
    catch (Exception paramContext)
    {
      paramString = new StringBuilder();
      paramString.append("LocalStorage::writeToken exception saving token: ");
      paramString.append(paramContext.getMessage());
      Log.d("SAKAY", paramString.toString());
    }
  }
  
  public final void writeWork(@NotNull Context paramContext, @NotNull LatLng paramLatLng, @Nullable String paramString1, @Nullable String paramString2, boolean paramBoolean)
  {
    Intrinsics.checkParameterIsNotNull(paramContext, "context");
    Intrinsics.checkParameterIsNotNull(paramLatLng, "latLng");
    try
    {
      paramContext = new JsonWriter((Writer)new OutputStreamWriter((OutputStream)paramContext.openFileOutput(workFilename, 0), "UTF-8"));
      paramContext.setIndent("  ");
      paramContext.beginObject();
      writeLatLng(paramContext, paramLatLng);
      if ((paramBoolean) && (paramString1 != null))
      {
        paramContext.name("description").value(paramString1);
        paramContext.name("provider").value(paramString2);
      }
      paramContext.endObject();
      paramContext.close();
      return;
    }
    catch (Exception paramContext)
    {
      paramLatLng = new StringBuilder();
      paramLatLng.append("LocalStorage::writeWork encountered an error: ");
      paramLatLng.append(paramContext.getMessage());
      Log.e("SAKAY", paramLatLng.toString());
    }
  }
}
