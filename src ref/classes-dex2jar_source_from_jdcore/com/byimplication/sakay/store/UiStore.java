package com.byimplication.sakay.store;

import android.os.Bundle;
import com.byimplication.sakay.RouteType;
import com.byimplication.sakay.action.Action;
import com.byimplication.sakay.action.CenterMapOn;
import com.byimplication.sakay.action.DisableSakayPager;
import com.byimplication.sakay.action.DragMap;
import com.byimplication.sakay.action.EnableSakayPager;
import com.byimplication.sakay.action.MoveMap;
import com.byimplication.sakay.action.ResetItinerary;
import com.byimplication.sakay.action.ResetLeg;
import com.byimplication.sakay.action.ResetUi;
import com.byimplication.sakay.action.SetMapPinningMode;
import com.byimplication.sakay.action.SetSearchImmediately;
import com.byimplication.sakay.action.UpdateConnectionStatus;
import com.byimplication.sakay.action.UpdateGoogleApiStatus;
import com.byimplication.sakay.action.UpdateItinerary;
import com.byimplication.sakay.action.UpdateLeg;
import com.byimplication.sakay.action.UpdateUi;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000:\n\002\030\002\n\002\030\002\n\002\b\002\n\002\020\016\n\002\b\007\n\002\020\b\n\002\b\b\n\002\030\002\n\002\b\005\n\002\020\002\n\000\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\004\030\0002\0020\001B\005¢\006\002\020\002J\020\020\032\032\0020\0332\006\020\034\032\0020\035H\026J\020\020\036\032\0020\0332\006\020\037\032\0020 H\026J\020\020!\032\0020\0332\006\020\"\032\0020\035H\026J\006\020#\032\0020\033R\024\020\003\032\0020\004XD¢\006\b\n\000\032\004\b\005\020\006R\024\020\007\032\0020\004XD¢\006\b\n\000\032\004\b\b\020\006R\024\020\t\032\0020\004XD¢\006\b\n\000\032\004\b\n\020\006R\032\020\013\032\0020\fX\016¢\006\016\n\000\032\004\b\r\020\016\"\004\b\017\020\020R\032\020\021\032\0020\fX\016¢\006\016\n\000\032\004\b\022\020\016\"\004\b\023\020\020R\032\020\024\032\0020\025X\016¢\006\016\n\000\032\004\b\026\020\027\"\004\b\030\020\031¨\006$"}, d2={"Lcom/byimplication/sakay/store/UiStore;", "Lcom/byimplication/sakay/store/Store;", "()V", "CURRENT_ITINERARY", "", "getCURRENT_ITINERARY", "()Ljava/lang/String;", "CURRENT_LEG", "getCURRENT_LEG", "ROUTE_TYPE", "getROUTE_TYPE", "currentItinerary", "", "getCurrentItinerary", "()I", "setCurrentItinerary", "(I)V", "currentLeg", "getCurrentLeg", "setCurrentLeg", "routeType", "Lcom/byimplication/sakay/RouteType;", "getRouteType", "()Lcom/byimplication/sakay/RouteType;", "setRouteType", "(Lcom/byimplication/sakay/RouteType;)V", "onSaveInstanceState", "", "outState", "Landroid/os/Bundle;", "receive", "stuff", "Lcom/byimplication/sakay/action/Action;", "restoreInstanceState", "savedInstanceState", "updateUi", "app_release"}, k=1, mv={1, 1, 9})
public final class UiStore
  implements Store
{
  @NotNull
  private final String CURRENT_ITINERARY = "ui_store_current_itinerary";
  @NotNull
  private final String CURRENT_LEG = "ui_store_current_leg";
  @NotNull
  private final String ROUTE_TYPE = "ui_store_route_type";
  private int currentItinerary = -1;
  private int currentLeg = -1;
  @NotNull
  private RouteType routeType = RouteType.SAKAY;
  
  public UiStore() {}
  
  @NotNull
  public final String getCURRENT_ITINERARY()
  {
    return CURRENT_ITINERARY;
  }
  
  @NotNull
  public final String getCURRENT_LEG()
  {
    return CURRENT_LEG;
  }
  
  public final int getCurrentItinerary()
  {
    return currentItinerary;
  }
  
  public final int getCurrentLeg()
  {
    return currentLeg;
  }
  
  @NotNull
  public final String getROUTE_TYPE()
  {
    return ROUTE_TYPE;
  }
  
  @NotNull
  public final RouteType getRouteType()
  {
    return routeType;
  }
  
  public void init(@Nullable Bundle paramBundle)
  {
    Store.DefaultImpls.init(this, paramBundle);
  }
  
  public void onSaveInstanceState(@NotNull Bundle paramBundle)
  {
    Intrinsics.checkParameterIsNotNull(paramBundle, "outState");
    paramBundle.putInt(CURRENT_ITINERARY, currentItinerary);
    paramBundle.putInt(CURRENT_LEG, currentLeg);
    paramBundle.putString(ROUTE_TYPE, routeType.name());
  }
  
  public void receive(@NotNull Action paramAction)
  {
    Intrinsics.checkParameterIsNotNull(paramAction, "stuff");
    if ((paramAction instanceof UpdateItinerary))
    {
      currentItinerary = ((UpdateItinerary)paramAction).getNewItinerary();
      updateUi();
      return;
    }
    if ((paramAction instanceof ResetItinerary))
    {
      currentItinerary = -1;
      updateUi();
      return;
    }
    if ((paramAction instanceof UpdateLeg))
    {
      currentLeg = ((UpdateLeg)paramAction).getNewLeg();
      updateUi();
      return;
    }
    if ((paramAction instanceof ResetLeg))
    {
      currentLeg = -1;
      updateUi();
      return;
    }
    if ((paramAction instanceof UpdateUi))
    {
      paramAction = (UpdateUi)paramAction;
      currentItinerary = paramAction.getNewItinerary();
      currentLeg = paramAction.getNewLeg();
      updateUi();
      return;
    }
    if ((paramAction instanceof ResetUi))
    {
      currentItinerary = -1;
      currentLeg = -1;
      updateUi();
      return;
    }
    if ((paramAction instanceof MoveMap))
    {
      Dispatcher.INSTANCE.publish((StoreData)new MapMotion(((MoveMap)paramAction).getEv()));
      return;
    }
    if ((paramAction instanceof DragMap))
    {
      Dispatcher.INSTANCE.publish((StoreData)new MapDrag(((DragMap)paramAction).getEv()));
      return;
    }
    if ((paramAction instanceof SetSearchImmediately))
    {
      Dispatcher.INSTANCE.publish((StoreData)new SearchImmediatelyUpdate(((SetSearchImmediately)paramAction).getShouldSearch()));
      return;
    }
    if ((paramAction instanceof EnableSakayPager))
    {
      Dispatcher.INSTANCE.publish((StoreData)new SakayPagerEnableCommand(null, 1, null));
      return;
    }
    if ((paramAction instanceof DisableSakayPager))
    {
      Dispatcher.INSTANCE.publish((StoreData)new SakayPagerDisableCommand());
      return;
    }
    if ((paramAction instanceof SetMapPinningMode))
    {
      Dispatcher.INSTANCE.publish((StoreData)new MapPinningMode(((SetMapPinningMode)paramAction).getMode()));
      return;
    }
    if ((paramAction instanceof UpdateConnectionStatus))
    {
      Dispatcher.INSTANCE.publish((StoreData)new ConnectionStatusUpdate(((UpdateConnectionStatus)paramAction).isConnected()));
      return;
    }
    Dispatcher localDispatcher;
    if ((paramAction instanceof UpdateGoogleApiStatus))
    {
      localDispatcher = Dispatcher.INSTANCE;
      paramAction = (UpdateGoogleApiStatus)paramAction;
      localDispatcher.publish((StoreData)new GoogleApiStatusUpdate(paramAction.isConnected(), paramAction.getErrorCode()));
      return;
    }
    if ((paramAction instanceof CenterMapOn))
    {
      localDispatcher = Dispatcher.INSTANCE;
      paramAction = (CenterMapOn)paramAction;
      localDispatcher.publish((StoreData)new MapCenterUpdate(paramAction.getLatLng(), paramAction.getTrueSize()));
    }
  }
  
  public void restoreInstanceState(@NotNull Bundle paramBundle)
  {
    Intrinsics.checkParameterIsNotNull(paramBundle, "savedInstanceState");
    currentItinerary = paramBundle.getInt(CURRENT_ITINERARY);
    currentLeg = paramBundle.getInt(CURRENT_LEG);
    paramBundle = paramBundle.getString(ROUTE_TYPE);
    Intrinsics.checkExpressionValueIsNotNull(paramBundle, "savedInstanceState.getString(ROUTE_TYPE)");
    routeType = RouteType.valueOf(paramBundle);
  }
  
  public final void setCurrentItinerary(int paramInt)
  {
    currentItinerary = paramInt;
  }
  
  public final void setCurrentLeg(int paramInt)
  {
    currentLeg = paramInt;
  }
  
  public final void setRouteType(@NotNull RouteType paramRouteType)
  {
    Intrinsics.checkParameterIsNotNull(paramRouteType, "<set-?>");
    routeType = paramRouteType;
  }
  
  public final void updateUi()
  {
    Dispatcher.INSTANCE.publish((StoreData)new UiUpdate(currentItinerary, currentLeg));
  }
}
