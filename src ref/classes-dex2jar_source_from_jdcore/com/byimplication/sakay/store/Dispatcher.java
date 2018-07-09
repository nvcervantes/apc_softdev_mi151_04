package com.byimplication.sakay.store;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.byimplication.sakay.action.Action;
import com.google.android.gms.common.api.GoogleApiClient;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000V\n\002\030\002\n\002\020\000\n\002\b\002\n\002\020\013\n\000\n\002\020!\n\002\030\002\n\000\n\002\020%\n\002\020\016\n\000\n\002\030\002\n\000\n\002\020\002\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\002\b\004\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\006\bÆ\002\030\0002\0020\001B\007\b\002¢\006\002\020\002J \020\r\032\0020\0162\006\020\017\032\0020\0202\006\020\021\032\0020\0222\b\020\023\032\004\030\0010\024J\016\020\025\032\0020\0162\006\020\026\032\0020\024J\016\020\027\032\0020\0162\006\020\030\032\0020\031J\016\020\032\032\0020\0162\006\020\033\032\0020\034J\016\020\035\032\0020\0162\006\020\036\032\0020\007J\016\020\035\032\0020\0162\006\020\037\032\0020\fJ\016\020 \032\0020\0162\006\020\036\032\0020\007J\016\020 \032\0020\0162\006\020\037\032\0020\fJ\006\020!\032\0020\016R\016\020\003\032\0020\004X\016¢\006\002\n\000R\024\020\005\032\b\022\004\022\0020\0070\006X\004¢\006\002\n\000R\034\020\b\032\020\022\004\022\0020\n\022\006\022\004\030\0010\0010\tX\004¢\006\002\n\000R\024\020\013\032\b\022\004\022\0020\f0\006X\004¢\006\002\n\000¨\006\""}, d2={"Lcom/byimplication/sakay/store/Dispatcher;", "", "()V", "isInited", "", "observers", "", "Lcom/byimplication/sakay/store/Observer;", "oldValues", "", "", "stores", "Lcom/byimplication/sakay/store/Store;", "init", "", "activity", "Landroid/app/Activity;", "gApiClient", "Lcom/google/android/gms/common/api/GoogleApiClient;", "savedInstanceState", "Landroid/os/Bundle;", "onSaveInstanceState", "outState", "post", "action", "Lcom/byimplication/sakay/action/Action;", "publish", "data", "Lcom/byimplication/sakay/store/StoreData;", "register", "observer", "store", "unregister", "unregisterAll", "app_release"}, k=1, mv={1, 1, 9})
public final class Dispatcher
{
  public static final Dispatcher INSTANCE = new Dispatcher();
  private static boolean isInited;
  private static final List<Observer> observers = (List)new ArrayList();
  private static final Map<String, Object> oldValues = (Map)new LinkedHashMap();
  private static final List<Store> stores = (List)new ArrayList();
  
  private Dispatcher() {}
  
  public final void init(@NotNull Activity paramActivity, @NotNull GoogleApiClient paramGoogleApiClient, @Nullable Bundle paramBundle)
  {
    Intrinsics.checkParameterIsNotNull(paramActivity, "activity");
    Intrinsics.checkParameterIsNotNull(paramGoogleApiClient, "gApiClient");
    if (!isInited)
    {
      new LocationStore().init(paramBundle);
      new TerminalStore(paramActivity, paramGoogleApiClient).init(paramBundle);
      new RouteStore((Context)paramActivity).init(paramBundle);
      new UiStore().init(paramBundle);
      new CommuteUpdateStore().init(paramBundle);
      new AnalyticsStore(paramActivity).init(paramBundle);
      isInited = true;
    }
  }
  
  public final void onSaveInstanceState(@NotNull Bundle paramBundle)
  {
    Intrinsics.checkParameterIsNotNull(paramBundle, "outState");
    Iterator localIterator = ((Iterable)stores).iterator();
    while (localIterator.hasNext()) {
      ((Store)localIterator.next()).onSaveInstanceState(paramBundle);
    }
  }
  
  public final void post(@NotNull Action paramAction)
  {
    Intrinsics.checkParameterIsNotNull(paramAction, "action");
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append("Dispatcher::post action: ");
    ((StringBuilder)localObject).append(paramAction.toString());
    Log.d("SAKAY", ((StringBuilder)localObject).toString());
    localObject = stores.iterator();
    while (((Iterator)localObject).hasNext()) {
      ((Store)((Iterator)localObject).next()).receive(paramAction);
    }
  }
  
  public final void publish(@NotNull StoreData paramStoreData)
  {
    Intrinsics.checkParameterIsNotNull(paramStoreData, "data");
    String str = paramStoreData.getClass().getName();
    Intrinsics.checkExpressionValueIsNotNull(str, "data.javaClass.name");
    Object localObject = oldValues.get(str);
    ListIterator localListIterator = observers.listIterator();
    while (localListIterator.hasNext())
    {
      Observer localObserver = (Observer)localListIterator.next();
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Dispatcher::publish Observers, ");
      localStringBuilder.append(localObserver.getClass().toString());
      localStringBuilder.append(", accepting ");
      localStringBuilder.append(paramStoreData.toString());
      Log.d("SAKAY", localStringBuilder.toString());
      localObserver.onStoreChange(paramStoreData, localObject);
    }
    oldValues.put(str, paramStoreData.clone());
  }
  
  public final void register(@NotNull Observer paramObserver)
  {
    Intrinsics.checkParameterIsNotNull(paramObserver, "observer");
    if (!observers.contains(paramObserver)) {
      observers.add(paramObserver);
    }
  }
  
  public final void register(@NotNull Store paramStore)
  {
    Intrinsics.checkParameterIsNotNull(paramStore, "store");
    if (!stores.contains(paramStore)) {
      stores.add(paramStore);
    }
  }
  
  public final void unregister(@NotNull Observer paramObserver)
  {
    Intrinsics.checkParameterIsNotNull(paramObserver, "observer");
    observers.remove(paramObserver);
  }
  
  public final void unregister(@NotNull Store paramStore)
  {
    Intrinsics.checkParameterIsNotNull(paramStore, "store");
    stores.remove(paramStore);
  }
  
  public final void unregisterAll()
  {
    stores.clear();
    observers.clear();
  }
}
