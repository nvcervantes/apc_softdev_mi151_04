package com.byimplication.sakay.store;

import android.os.Bundle;
import com.byimplication.sakay.action.Action;
import com.byimplication.sakay.action.ApplyCurrentLocation;
import com.byimplication.sakay.action.NoLocation;
import com.byimplication.sakay.action.RequestLocation;
import com.google.android.gms.maps.model.LatLng;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000*\n\002\030\002\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\005\n\002\020\002\n\000\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\003\030\0002\0020\001B\005¢\006\002\020\002J\020\020\t\032\0020\n2\006\020\013\032\0020\fH\026J\020\020\r\032\0020\n2\006\020\016\032\0020\017H\026J\020\020\020\032\0020\n2\006\020\021\032\0020\fH\026R\034\020\003\032\004\030\0010\004X\016¢\006\016\n\000\032\004\b\005\020\006\"\004\b\007\020\b¨\006\022"}, d2={"Lcom/byimplication/sakay/store/LocationStore;", "Lcom/byimplication/sakay/store/Store;", "()V", "currentLatLng", "Lcom/google/android/gms/maps/model/LatLng;", "getCurrentLatLng", "()Lcom/google/android/gms/maps/model/LatLng;", "setCurrentLatLng", "(Lcom/google/android/gms/maps/model/LatLng;)V", "onSaveInstanceState", "", "outState", "Landroid/os/Bundle;", "receive", "stuff", "Lcom/byimplication/sakay/action/Action;", "restoreInstanceState", "savedInstanceState", "app_release"}, k=1, mv={1, 1, 9})
public final class LocationStore
  implements Store
{
  @Nullable
  private LatLng currentLatLng;
  
  public LocationStore() {}
  
  @Nullable
  public final LatLng getCurrentLatLng()
  {
    return currentLatLng;
  }
  
  public void init(@Nullable Bundle paramBundle)
  {
    Store.DefaultImpls.init(this, paramBundle);
  }
  
  public void onSaveInstanceState(@NotNull Bundle paramBundle)
  {
    Intrinsics.checkParameterIsNotNull(paramBundle, "outState");
    if (currentLatLng != null)
    {
      LatLng localLatLng = currentLatLng;
      if (localLatLng == null) {
        Intrinsics.throwNpe();
      }
      double d = latitude;
      localLatLng = currentLatLng;
      if (localLatLng == null) {
        Intrinsics.throwNpe();
      }
      paramBundle.putDoubleArray("location_store_current_location", new double[] { d, longitude });
    }
  }
  
  public void receive(@NotNull Action paramAction)
  {
    Intrinsics.checkParameterIsNotNull(paramAction, "stuff");
    if ((paramAction instanceof ApplyCurrentLocation))
    {
      paramAction = (ApplyCurrentLocation)paramAction;
      currentLatLng = paramAction.getLatLng();
      Dispatcher.INSTANCE.publish((StoreData)new CurrentLocation(paramAction.getLatLng(), paramAction.getShouldMoveMap(), paramAction.isAnimated()));
      return;
    }
    Dispatcher localDispatcher;
    if ((paramAction instanceof RequestLocation))
    {
      if (currentLatLng != null)
      {
        localDispatcher = Dispatcher.INSTANCE;
        LatLng localLatLng = currentLatLng;
        paramAction = (RequestLocation)paramAction;
        localDispatcher.publish((StoreData)new CurrentLocation(localLatLng, paramAction.getShouldMoveMap(), paramAction.isAnimated()));
      }
      Dispatcher.INSTANCE.publish((StoreData)new LocationRequest());
      return;
    }
    if ((paramAction instanceof NoLocation))
    {
      localDispatcher = Dispatcher.INSTANCE;
      paramAction = (NoLocation)paramAction;
      localDispatcher.publish((StoreData)new NoCurrentLocation(paramAction.getShouldMoveMap(), paramAction.isAnimated()));
    }
  }
  
  public void restoreInstanceState(@NotNull Bundle paramBundle)
  {
    Intrinsics.checkParameterIsNotNull(paramBundle, "savedInstanceState");
    if (paramBundle.containsKey("location_store_current_location"))
    {
      paramBundle = paramBundle.getDoubleArray("location_store_current_location");
      currentLatLng = new LatLng(paramBundle[0], paramBundle[1]);
    }
  }
  
  public final void setCurrentLatLng(@Nullable LatLng paramLatLng)
  {
    currentLatLng = paramLatLng;
  }
}
