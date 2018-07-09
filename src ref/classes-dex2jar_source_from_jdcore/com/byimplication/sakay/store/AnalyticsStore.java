package com.byimplication.sakay.store;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.amplitude.api.Amplitude;
import com.amplitude.api.AmplitudeClient;
import com.byimplication.sakay.action.Action;
import com.byimplication.sakay.action.OnChooseTerminal;
import com.byimplication.sakay.action.OnEvent;
import com.byimplication.sakay.action.OnSimpleEvent;
import com.byimplication.sakay.model.Terminal;
import com.byimplication.sakay.model.Terminal.Companion;
import com.google.android.gms.maps.model.LatLng;
import java.util.HashMap;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;

@Metadata(bv={1, 0, 2}, d1={"\0000\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\002\b\004\n\002\020\016\n\002\b\005\n\002\020\002\n\000\n\002\030\002\n\002\b\004\n\002\030\002\n\002\b\002\030\0002\0020\001B\r\022\006\020\002\032\0020\003¢\006\002\020\004J\022\020\r\032\0020\0162\b\020\017\032\004\030\0010\020H\026J\020\020\021\032\0020\0162\006\020\022\032\0020\020H\026J\020\020\023\032\0020\0162\006\020\024\032\0020\025H\026J\020\020\026\032\0020\0162\006\020\017\032\0020\020H\026R\021\020\002\032\0020\003¢\006\b\n\000\032\004\b\005\020\006R\033\020\007\032\0020\b8FX\002¢\006\f\n\004\b\013\020\f\032\004\b\t\020\n¨\006\027"}, d2={"Lcom/byimplication/sakay/store/AnalyticsStore;", "Lcom/byimplication/sakay/store/Store;", "activity", "Landroid/app/Activity;", "(Landroid/app/Activity;)V", "getActivity", "()Landroid/app/Activity;", "projectToken", "", "getProjectToken", "()Ljava/lang/String;", "projectToken$delegate", "Lkotlin/Lazy;", "init", "", "savedInstanceState", "Landroid/os/Bundle;", "onSaveInstanceState", "outState", "receive", "stuff", "Lcom/byimplication/sakay/action/Action;", "restoreInstanceState", "app_release"}, k=1, mv={1, 1, 9})
public final class AnalyticsStore
  implements Store
{
  @NotNull
  private final Activity activity;
  @NotNull
  private final Lazy projectToken$delegate;
  
  public AnalyticsStore(@NotNull Activity paramActivity)
  {
    activity = paramActivity;
    projectToken$delegate = LazyKt.lazy((Function0)projectToken.2.INSTANCE);
  }
  
  @NotNull
  public final Activity getActivity()
  {
    return activity;
  }
  
  @NotNull
  public final String getProjectToken()
  {
    Lazy localLazy = projectToken$delegate;
    KProperty localKProperty = $$delegatedProperties[0];
    return (String)localLazy.getValue();
  }
  
  public void init(@Nullable Bundle paramBundle)
  {
    Store.DefaultImpls.init(this, paramBundle);
    paramBundle = Amplitude.getInstance();
    Activity localActivity = activity;
    if (localActivity == null) {
      Intrinsics.throwNpe();
    }
    paramBundle = paramBundle.initialize((Context)localActivity, getProjectToken());
    localActivity = activity;
    if (localActivity == null) {
      Intrinsics.throwNpe();
    }
    paramBundle.enableForegroundTracking(localActivity.getApplication());
  }
  
  public void onSaveInstanceState(@NotNull Bundle paramBundle)
  {
    Intrinsics.checkParameterIsNotNull(paramBundle, "outState");
  }
  
  public void receive(@NotNull Action paramAction)
  {
    Intrinsics.checkParameterIsNotNull(paramAction, "stuff");
    Object localObject1;
    if ((paramAction instanceof OnEvent))
    {
      localObject1 = (OnEvent)paramAction;
      paramAction = new JSONObject(((OnEvent)localObject1).getParams());
      Amplitude.getInstance().logEvent(((OnEvent)localObject1).getEventName(), paramAction);
      return;
    }
    if ((paramAction instanceof OnSimpleEvent))
    {
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append("AnalyticsStore::receive OnSimpleEvent");
      paramAction = (OnSimpleEvent)paramAction;
      ((StringBuilder)localObject1).append(paramAction.getEventName());
      Log.d("SAKAY", ((StringBuilder)localObject1).toString());
      Amplitude.getInstance().logEvent(paramAction.getEventName());
      return;
    }
    if ((paramAction instanceof OnChooseTerminal))
    {
      localObject1 = new StringBuilder();
      Object localObject2 = Terminal.Companion;
      paramAction = (OnChooseTerminal)paramAction;
      ((StringBuilder)localObject1).append(((Terminal.Companion)localObject2).turnToReadableString(paramAction.getWhichTerminal()));
      ((StringBuilder)localObject1).append(" - Set");
      localObject1 = ((StringBuilder)localObject1).toString();
      localObject2 = new HashMap();
      if (paramAction.getLocation() != null)
      {
        ((HashMap)localObject2).put("latitude", Double.valueOf(getLocationlatitude));
        ((HashMap)localObject2).put("longitude", Double.valueOf(getLocationlongitude));
      }
      if (paramAction.getDescription() != null) {
        ((HashMap)localObject2).put("description", paramAction.getDescription());
      }
      if (paramAction.getPlaceId() != null) {
        ((HashMap)localObject2).put("placeId", paramAction.getPlaceId());
      }
      ((HashMap)localObject2).put("byMethod", paramAction.getByMethod());
      paramAction = new JSONObject((Map)localObject2);
      Amplitude.getInstance().logEvent((String)localObject1, paramAction);
    }
  }
  
  public void restoreInstanceState(@NotNull Bundle paramBundle)
  {
    Intrinsics.checkParameterIsNotNull(paramBundle, "savedInstanceState");
  }
}
