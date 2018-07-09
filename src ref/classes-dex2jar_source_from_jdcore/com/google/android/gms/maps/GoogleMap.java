package com.google.android.gms.maps;

import android.graphics.Bitmap;
import android.location.Location;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresPermission;
import android.view.View;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.internal.IGoogleMapDelegate;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.GroundOverlay;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.IndoorBuilding;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PointOfInterest;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import com.google.android.gms.maps.model.TileOverlay;
import com.google.android.gms.maps.model.TileOverlayOptions;

public final class GoogleMap
{
  public static final int MAP_TYPE_HYBRID = 4;
  public static final int MAP_TYPE_NONE = 0;
  public static final int MAP_TYPE_NORMAL = 1;
  public static final int MAP_TYPE_SATELLITE = 2;
  public static final int MAP_TYPE_TERRAIN = 3;
  private final IGoogleMapDelegate zza;
  private UiSettings zzb;
  
  @Hide
  public GoogleMap(IGoogleMapDelegate paramIGoogleMapDelegate)
  {
    zza = ((IGoogleMapDelegate)zzbq.zza(paramIGoogleMapDelegate));
  }
  
  public final Circle addCircle(CircleOptions paramCircleOptions)
  {
    try
    {
      paramCircleOptions = new Circle(zza.addCircle(paramCircleOptions));
      return paramCircleOptions;
    }
    catch (RemoteException paramCircleOptions)
    {
      throw new RuntimeRemoteException(paramCircleOptions);
    }
  }
  
  public final GroundOverlay addGroundOverlay(GroundOverlayOptions paramGroundOverlayOptions)
  {
    try
    {
      paramGroundOverlayOptions = zza.addGroundOverlay(paramGroundOverlayOptions);
      if (paramGroundOverlayOptions != null)
      {
        paramGroundOverlayOptions = new GroundOverlay(paramGroundOverlayOptions);
        return paramGroundOverlayOptions;
      }
      return null;
    }
    catch (RemoteException paramGroundOverlayOptions)
    {
      throw new RuntimeRemoteException(paramGroundOverlayOptions);
    }
  }
  
  public final Marker addMarker(MarkerOptions paramMarkerOptions)
  {
    try
    {
      paramMarkerOptions = zza.addMarker(paramMarkerOptions);
      if (paramMarkerOptions != null)
      {
        paramMarkerOptions = new Marker(paramMarkerOptions);
        return paramMarkerOptions;
      }
      return null;
    }
    catch (RemoteException paramMarkerOptions)
    {
      throw new RuntimeRemoteException(paramMarkerOptions);
    }
  }
  
  public final Polygon addPolygon(PolygonOptions paramPolygonOptions)
  {
    try
    {
      paramPolygonOptions = new Polygon(zza.addPolygon(paramPolygonOptions));
      return paramPolygonOptions;
    }
    catch (RemoteException paramPolygonOptions)
    {
      throw new RuntimeRemoteException(paramPolygonOptions);
    }
  }
  
  public final Polyline addPolyline(PolylineOptions paramPolylineOptions)
  {
    try
    {
      paramPolylineOptions = new Polyline(zza.addPolyline(paramPolylineOptions));
      return paramPolylineOptions;
    }
    catch (RemoteException paramPolylineOptions)
    {
      throw new RuntimeRemoteException(paramPolylineOptions);
    }
  }
  
  public final TileOverlay addTileOverlay(TileOverlayOptions paramTileOverlayOptions)
  {
    try
    {
      paramTileOverlayOptions = zza.addTileOverlay(paramTileOverlayOptions);
      if (paramTileOverlayOptions != null)
      {
        paramTileOverlayOptions = new TileOverlay(paramTileOverlayOptions);
        return paramTileOverlayOptions;
      }
      return null;
    }
    catch (RemoteException paramTileOverlayOptions)
    {
      throw new RuntimeRemoteException(paramTileOverlayOptions);
    }
  }
  
  public final void animateCamera(CameraUpdate paramCameraUpdate)
  {
    try
    {
      zza.animateCamera(paramCameraUpdate.zza());
      return;
    }
    catch (RemoteException paramCameraUpdate)
    {
      throw new RuntimeRemoteException(paramCameraUpdate);
    }
  }
  
  public final void animateCamera(CameraUpdate paramCameraUpdate, int paramInt, CancelableCallback paramCancelableCallback)
  {
    try
    {
      IGoogleMapDelegate localIGoogleMapDelegate = zza;
      IObjectWrapper localIObjectWrapper = paramCameraUpdate.zza();
      if (paramCancelableCallback == null) {
        paramCameraUpdate = null;
      } else {
        paramCameraUpdate = new zza(paramCancelableCallback);
      }
      localIGoogleMapDelegate.animateCameraWithDurationAndCallback(localIObjectWrapper, paramInt, paramCameraUpdate);
      return;
    }
    catch (RemoteException paramCameraUpdate)
    {
      throw new RuntimeRemoteException(paramCameraUpdate);
    }
  }
  
  public final void animateCamera(CameraUpdate paramCameraUpdate, CancelableCallback paramCancelableCallback)
  {
    try
    {
      IGoogleMapDelegate localIGoogleMapDelegate = zza;
      IObjectWrapper localIObjectWrapper = paramCameraUpdate.zza();
      if (paramCancelableCallback == null) {
        paramCameraUpdate = null;
      } else {
        paramCameraUpdate = new zza(paramCancelableCallback);
      }
      localIGoogleMapDelegate.animateCameraWithCallback(localIObjectWrapper, paramCameraUpdate);
      return;
    }
    catch (RemoteException paramCameraUpdate)
    {
      throw new RuntimeRemoteException(paramCameraUpdate);
    }
  }
  
  public final void clear()
  {
    try
    {
      zza.clear();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public final CameraPosition getCameraPosition()
  {
    try
    {
      CameraPosition localCameraPosition = zza.getCameraPosition();
      return localCameraPosition;
    }
    catch (RemoteException localRemoteException)
    {
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public final IndoorBuilding getFocusedBuilding()
  {
    try
    {
      Object localObject = zza.getFocusedBuilding();
      if (localObject != null)
      {
        localObject = new IndoorBuilding((com.google.android.gms.maps.model.internal.zzj)localObject);
        return localObject;
      }
      return null;
    }
    catch (RemoteException localRemoteException)
    {
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public final int getMapType()
  {
    try
    {
      int i = zza.getMapType();
      return i;
    }
    catch (RemoteException localRemoteException)
    {
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public final float getMaxZoomLevel()
  {
    try
    {
      float f = zza.getMaxZoomLevel();
      return f;
    }
    catch (RemoteException localRemoteException)
    {
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public final float getMinZoomLevel()
  {
    try
    {
      float f = zza.getMinZoomLevel();
      return f;
    }
    catch (RemoteException localRemoteException)
    {
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  @Deprecated
  public final Location getMyLocation()
  {
    try
    {
      Location localLocation = zza.getMyLocation();
      return localLocation;
    }
    catch (RemoteException localRemoteException)
    {
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public final Projection getProjection()
  {
    try
    {
      Projection localProjection = new Projection(zza.getProjection());
      return localProjection;
    }
    catch (RemoteException localRemoteException)
    {
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public final UiSettings getUiSettings()
  {
    try
    {
      if (zzb == null) {
        zzb = new UiSettings(zza.getUiSettings());
      }
      UiSettings localUiSettings = zzb;
      return localUiSettings;
    }
    catch (RemoteException localRemoteException)
    {
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public final boolean isBuildingsEnabled()
  {
    try
    {
      boolean bool = zza.isBuildingsEnabled();
      return bool;
    }
    catch (RemoteException localRemoteException)
    {
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public final boolean isIndoorEnabled()
  {
    try
    {
      boolean bool = zza.isIndoorEnabled();
      return bool;
    }
    catch (RemoteException localRemoteException)
    {
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public final boolean isMyLocationEnabled()
  {
    try
    {
      boolean bool = zza.isMyLocationEnabled();
      return bool;
    }
    catch (RemoteException localRemoteException)
    {
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public final boolean isTrafficEnabled()
  {
    try
    {
      boolean bool = zza.isTrafficEnabled();
      return bool;
    }
    catch (RemoteException localRemoteException)
    {
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public final void moveCamera(CameraUpdate paramCameraUpdate)
  {
    try
    {
      zza.moveCamera(paramCameraUpdate.zza());
      return;
    }
    catch (RemoteException paramCameraUpdate)
    {
      throw new RuntimeRemoteException(paramCameraUpdate);
    }
  }
  
  public final void resetMinMaxZoomPreference()
  {
    try
    {
      zza.resetMinMaxZoomPreference();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public final void setBuildingsEnabled(boolean paramBoolean)
  {
    try
    {
      zza.setBuildingsEnabled(paramBoolean);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public final void setContentDescription(String paramString)
  {
    try
    {
      zza.setContentDescription(paramString);
      return;
    }
    catch (RemoteException paramString)
    {
      throw new RuntimeRemoteException(paramString);
    }
  }
  
  public final boolean setIndoorEnabled(boolean paramBoolean)
  {
    try
    {
      paramBoolean = zza.setIndoorEnabled(paramBoolean);
      return paramBoolean;
    }
    catch (RemoteException localRemoteException)
    {
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public final void setInfoWindowAdapter(InfoWindowAdapter paramInfoWindowAdapter)
  {
    if (paramInfoWindowAdapter == null) {}
    try
    {
      zza.setInfoWindowAdapter(null);
      return;
    }
    catch (RemoteException paramInfoWindowAdapter)
    {
      for (;;) {}
    }
    zza.setInfoWindowAdapter(new zzg(this, paramInfoWindowAdapter));
    return;
    throw new RuntimeRemoteException(paramInfoWindowAdapter);
  }
  
  public final void setLatLngBoundsForCameraTarget(LatLngBounds paramLatLngBounds)
  {
    try
    {
      zza.setLatLngBoundsForCameraTarget(paramLatLngBounds);
      return;
    }
    catch (RemoteException paramLatLngBounds)
    {
      throw new RuntimeRemoteException(paramLatLngBounds);
    }
  }
  
  public final void setLocationSource(LocationSource paramLocationSource)
  {
    if (paramLocationSource == null) {}
    try
    {
      zza.setLocationSource(null);
      return;
    }
    catch (RemoteException paramLocationSource)
    {
      for (;;) {}
    }
    zza.setLocationSource(new zzl(this, paramLocationSource));
    return;
    throw new RuntimeRemoteException(paramLocationSource);
  }
  
  public final boolean setMapStyle(@Nullable MapStyleOptions paramMapStyleOptions)
  {
    try
    {
      boolean bool = zza.setMapStyle(paramMapStyleOptions);
      return bool;
    }
    catch (RemoteException paramMapStyleOptions)
    {
      throw new RuntimeRemoteException(paramMapStyleOptions);
    }
  }
  
  public final void setMapType(int paramInt)
  {
    try
    {
      zza.setMapType(paramInt);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public final void setMaxZoomPreference(float paramFloat)
  {
    try
    {
      zza.setMaxZoomPreference(paramFloat);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public final void setMinZoomPreference(float paramFloat)
  {
    try
    {
      zza.setMinZoomPreference(paramFloat);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  @RequiresPermission(anyOf={"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"})
  public final void setMyLocationEnabled(boolean paramBoolean)
  {
    try
    {
      zza.setMyLocationEnabled(paramBoolean);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  @Deprecated
  public final void setOnCameraChangeListener(@Nullable OnCameraChangeListener paramOnCameraChangeListener)
  {
    if (paramOnCameraChangeListener == null) {}
    try
    {
      zza.setOnCameraChangeListener(null);
      return;
    }
    catch (RemoteException paramOnCameraChangeListener)
    {
      for (;;) {}
    }
    zza.setOnCameraChangeListener(new zzt(this, paramOnCameraChangeListener));
    return;
    throw new RuntimeRemoteException(paramOnCameraChangeListener);
  }
  
  public final void setOnCameraIdleListener(@Nullable OnCameraIdleListener paramOnCameraIdleListener)
  {
    if (paramOnCameraIdleListener == null) {}
    try
    {
      zza.setOnCameraIdleListener(null);
      return;
    }
    catch (RemoteException paramOnCameraIdleListener)
    {
      for (;;) {}
    }
    zza.setOnCameraIdleListener(new zzx(this, paramOnCameraIdleListener));
    return;
    throw new RuntimeRemoteException(paramOnCameraIdleListener);
  }
  
  public final void setOnCameraMoveCanceledListener(@Nullable OnCameraMoveCanceledListener paramOnCameraMoveCanceledListener)
  {
    if (paramOnCameraMoveCanceledListener == null) {}
    try
    {
      zza.setOnCameraMoveCanceledListener(null);
      return;
    }
    catch (RemoteException paramOnCameraMoveCanceledListener)
    {
      for (;;) {}
    }
    zza.setOnCameraMoveCanceledListener(new zzw(this, paramOnCameraMoveCanceledListener));
    return;
    throw new RuntimeRemoteException(paramOnCameraMoveCanceledListener);
  }
  
  public final void setOnCameraMoveListener(@Nullable OnCameraMoveListener paramOnCameraMoveListener)
  {
    if (paramOnCameraMoveListener == null) {}
    try
    {
      zza.setOnCameraMoveListener(null);
      return;
    }
    catch (RemoteException paramOnCameraMoveListener)
    {
      for (;;) {}
    }
    zza.setOnCameraMoveListener(new zzv(this, paramOnCameraMoveListener));
    return;
    throw new RuntimeRemoteException(paramOnCameraMoveListener);
  }
  
  public final void setOnCameraMoveStartedListener(@Nullable OnCameraMoveStartedListener paramOnCameraMoveStartedListener)
  {
    if (paramOnCameraMoveStartedListener == null) {}
    try
    {
      zza.setOnCameraMoveStartedListener(null);
      return;
    }
    catch (RemoteException paramOnCameraMoveStartedListener)
    {
      for (;;) {}
    }
    zza.setOnCameraMoveStartedListener(new zzu(this, paramOnCameraMoveStartedListener));
    return;
    throw new RuntimeRemoteException(paramOnCameraMoveStartedListener);
  }
  
  public final void setOnCircleClickListener(OnCircleClickListener paramOnCircleClickListener)
  {
    if (paramOnCircleClickListener == null) {}
    try
    {
      zza.setOnCircleClickListener(null);
      return;
    }
    catch (RemoteException paramOnCircleClickListener)
    {
      for (;;) {}
    }
    zza.setOnCircleClickListener(new zzo(this, paramOnCircleClickListener));
    return;
    throw new RuntimeRemoteException(paramOnCircleClickListener);
  }
  
  public final void setOnGroundOverlayClickListener(OnGroundOverlayClickListener paramOnGroundOverlayClickListener)
  {
    if (paramOnGroundOverlayClickListener == null) {}
    try
    {
      zza.setOnGroundOverlayClickListener(null);
      return;
    }
    catch (RemoteException paramOnGroundOverlayClickListener)
    {
      for (;;) {}
    }
    zza.setOnGroundOverlayClickListener(new zzn(this, paramOnGroundOverlayClickListener));
    return;
    throw new RuntimeRemoteException(paramOnGroundOverlayClickListener);
  }
  
  public final void setOnIndoorStateChangeListener(OnIndoorStateChangeListener paramOnIndoorStateChangeListener)
  {
    if (paramOnIndoorStateChangeListener == null) {}
    try
    {
      zza.setOnIndoorStateChangeListener(null);
      return;
    }
    catch (RemoteException paramOnIndoorStateChangeListener)
    {
      for (;;) {}
    }
    zza.setOnIndoorStateChangeListener(new zza(this, paramOnIndoorStateChangeListener));
    return;
    throw new RuntimeRemoteException(paramOnIndoorStateChangeListener);
  }
  
  public final void setOnInfoWindowClickListener(@Nullable OnInfoWindowClickListener paramOnInfoWindowClickListener)
  {
    if (paramOnInfoWindowClickListener == null) {}
    try
    {
      zza.setOnInfoWindowClickListener(null);
      return;
    }
    catch (RemoteException paramOnInfoWindowClickListener)
    {
      for (;;) {}
    }
    zza.setOnInfoWindowClickListener(new zzd(this, paramOnInfoWindowClickListener));
    return;
    throw new RuntimeRemoteException(paramOnInfoWindowClickListener);
  }
  
  public final void setOnInfoWindowCloseListener(@Nullable OnInfoWindowCloseListener paramOnInfoWindowCloseListener)
  {
    if (paramOnInfoWindowCloseListener == null) {}
    try
    {
      zza.setOnInfoWindowCloseListener(null);
      return;
    }
    catch (RemoteException paramOnInfoWindowCloseListener)
    {
      for (;;) {}
    }
    zza.setOnInfoWindowCloseListener(new zzf(this, paramOnInfoWindowCloseListener));
    return;
    throw new RuntimeRemoteException(paramOnInfoWindowCloseListener);
  }
  
  public final void setOnInfoWindowLongClickListener(@Nullable OnInfoWindowLongClickListener paramOnInfoWindowLongClickListener)
  {
    if (paramOnInfoWindowLongClickListener == null) {}
    try
    {
      zza.setOnInfoWindowLongClickListener(null);
      return;
    }
    catch (RemoteException paramOnInfoWindowLongClickListener)
    {
      for (;;) {}
    }
    zza.setOnInfoWindowLongClickListener(new zze(this, paramOnInfoWindowLongClickListener));
    return;
    throw new RuntimeRemoteException(paramOnInfoWindowLongClickListener);
  }
  
  public final void setOnMapClickListener(@Nullable OnMapClickListener paramOnMapClickListener)
  {
    if (paramOnMapClickListener == null) {}
    try
    {
      zza.setOnMapClickListener(null);
      return;
    }
    catch (RemoteException paramOnMapClickListener)
    {
      for (;;) {}
    }
    zza.setOnMapClickListener(new zzy(this, paramOnMapClickListener));
    return;
    throw new RuntimeRemoteException(paramOnMapClickListener);
  }
  
  public final void setOnMapLoadedCallback(OnMapLoadedCallback paramOnMapLoadedCallback)
  {
    if (paramOnMapLoadedCallback == null) {}
    try
    {
      zza.setOnMapLoadedCallback(null);
      return;
    }
    catch (RemoteException paramOnMapLoadedCallback)
    {
      for (;;) {}
    }
    zza.setOnMapLoadedCallback(new zzk(this, paramOnMapLoadedCallback));
    return;
    throw new RuntimeRemoteException(paramOnMapLoadedCallback);
  }
  
  public final void setOnMapLongClickListener(@Nullable OnMapLongClickListener paramOnMapLongClickListener)
  {
    if (paramOnMapLongClickListener == null) {}
    try
    {
      zza.setOnMapLongClickListener(null);
      return;
    }
    catch (RemoteException paramOnMapLongClickListener)
    {
      for (;;) {}
    }
    zza.setOnMapLongClickListener(new zzz(this, paramOnMapLongClickListener));
    return;
    throw new RuntimeRemoteException(paramOnMapLongClickListener);
  }
  
  public final void setOnMarkerClickListener(@Nullable OnMarkerClickListener paramOnMarkerClickListener)
  {
    if (paramOnMarkerClickListener == null) {}
    try
    {
      zza.setOnMarkerClickListener(null);
      return;
    }
    catch (RemoteException paramOnMarkerClickListener)
    {
      for (;;) {}
    }
    zza.setOnMarkerClickListener(new zzb(this, paramOnMarkerClickListener));
    return;
    throw new RuntimeRemoteException(paramOnMarkerClickListener);
  }
  
  public final void setOnMarkerDragListener(@Nullable OnMarkerDragListener paramOnMarkerDragListener)
  {
    if (paramOnMarkerDragListener == null) {}
    try
    {
      zza.setOnMarkerDragListener(null);
      return;
    }
    catch (RemoteException paramOnMarkerDragListener)
    {
      for (;;) {}
    }
    zza.setOnMarkerDragListener(new zzc(this, paramOnMarkerDragListener));
    return;
    throw new RuntimeRemoteException(paramOnMarkerDragListener);
  }
  
  public final void setOnMyLocationButtonClickListener(@Nullable OnMyLocationButtonClickListener paramOnMyLocationButtonClickListener)
  {
    if (paramOnMyLocationButtonClickListener == null) {}
    try
    {
      zza.setOnMyLocationButtonClickListener(null);
      return;
    }
    catch (RemoteException paramOnMyLocationButtonClickListener)
    {
      for (;;) {}
    }
    zza.setOnMyLocationButtonClickListener(new zzi(this, paramOnMyLocationButtonClickListener));
    return;
    throw new RuntimeRemoteException(paramOnMyLocationButtonClickListener);
  }
  
  @Deprecated
  public final void setOnMyLocationChangeListener(@Nullable OnMyLocationChangeListener paramOnMyLocationChangeListener)
  {
    if (paramOnMyLocationChangeListener == null) {}
    try
    {
      zza.setOnMyLocationChangeListener(null);
      return;
    }
    catch (RemoteException paramOnMyLocationChangeListener)
    {
      for (;;) {}
    }
    zza.setOnMyLocationChangeListener(new zzh(this, paramOnMyLocationChangeListener));
    return;
    throw new RuntimeRemoteException(paramOnMyLocationChangeListener);
  }
  
  public final void setOnMyLocationClickListener(@Nullable OnMyLocationClickListener paramOnMyLocationClickListener)
  {
    if (paramOnMyLocationClickListener == null) {}
    try
    {
      zza.setOnMyLocationClickListener(null);
      return;
    }
    catch (RemoteException paramOnMyLocationClickListener)
    {
      for (;;) {}
    }
    zza.setOnMyLocationClickListener(new zzj(this, paramOnMyLocationClickListener));
    return;
    throw new RuntimeRemoteException(paramOnMyLocationClickListener);
  }
  
  public final void setOnPoiClickListener(OnPoiClickListener paramOnPoiClickListener)
  {
    if (paramOnPoiClickListener == null) {}
    try
    {
      zza.setOnPoiClickListener(null);
      return;
    }
    catch (RemoteException paramOnPoiClickListener)
    {
      for (;;) {}
    }
    zza.setOnPoiClickListener(new zzs(this, paramOnPoiClickListener));
    return;
    throw new RuntimeRemoteException(paramOnPoiClickListener);
  }
  
  public final void setOnPolygonClickListener(OnPolygonClickListener paramOnPolygonClickListener)
  {
    if (paramOnPolygonClickListener == null) {}
    try
    {
      zza.setOnPolygonClickListener(null);
      return;
    }
    catch (RemoteException paramOnPolygonClickListener)
    {
      for (;;) {}
    }
    zza.setOnPolygonClickListener(new zzp(this, paramOnPolygonClickListener));
    return;
    throw new RuntimeRemoteException(paramOnPolygonClickListener);
  }
  
  public final void setOnPolylineClickListener(OnPolylineClickListener paramOnPolylineClickListener)
  {
    if (paramOnPolylineClickListener == null) {}
    try
    {
      zza.setOnPolylineClickListener(null);
      return;
    }
    catch (RemoteException paramOnPolylineClickListener)
    {
      for (;;) {}
    }
    zza.setOnPolylineClickListener(new zzq(this, paramOnPolylineClickListener));
    return;
    throw new RuntimeRemoteException(paramOnPolylineClickListener);
  }
  
  public final void setPadding(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    try
    {
      zza.setPadding(paramInt1, paramInt2, paramInt3, paramInt4);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public final void setTrafficEnabled(boolean paramBoolean)
  {
    try
    {
      zza.setTrafficEnabled(paramBoolean);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public final void snapshot(SnapshotReadyCallback paramSnapshotReadyCallback)
  {
    snapshot(paramSnapshotReadyCallback, null);
  }
  
  public final void snapshot(SnapshotReadyCallback paramSnapshotReadyCallback, Bitmap paramBitmap)
  {
    if (paramBitmap != null) {
      paramBitmap = com.google.android.gms.dynamic.zzn.zza(paramBitmap);
    } else {
      paramBitmap = null;
    }
    paramBitmap = (com.google.android.gms.dynamic.zzn)paramBitmap;
    try
    {
      zza.snapshot(new zzr(this, paramSnapshotReadyCallback), paramBitmap);
      return;
    }
    catch (RemoteException paramSnapshotReadyCallback)
    {
      throw new RuntimeRemoteException(paramSnapshotReadyCallback);
    }
  }
  
  public final void stopAnimation()
  {
    try
    {
      zza.stopAnimation();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public static abstract interface CancelableCallback
  {
    public abstract void onCancel();
    
    public abstract void onFinish();
  }
  
  public static abstract interface InfoWindowAdapter
  {
    public abstract View getInfoContents(Marker paramMarker);
    
    public abstract View getInfoWindow(Marker paramMarker);
  }
  
  @Deprecated
  public static abstract interface OnCameraChangeListener
  {
    public abstract void onCameraChange(CameraPosition paramCameraPosition);
  }
  
  public static abstract interface OnCameraIdleListener
  {
    public abstract void onCameraIdle();
  }
  
  public static abstract interface OnCameraMoveCanceledListener
  {
    public abstract void onCameraMoveCanceled();
  }
  
  public static abstract interface OnCameraMoveListener
  {
    public abstract void onCameraMove();
  }
  
  public static abstract interface OnCameraMoveStartedListener
  {
    public static final int REASON_API_ANIMATION = 2;
    public static final int REASON_DEVELOPER_ANIMATION = 3;
    public static final int REASON_GESTURE = 1;
    
    public abstract void onCameraMoveStarted(int paramInt);
  }
  
  public static abstract interface OnCircleClickListener
  {
    public abstract void onCircleClick(Circle paramCircle);
  }
  
  public static abstract interface OnGroundOverlayClickListener
  {
    public abstract void onGroundOverlayClick(GroundOverlay paramGroundOverlay);
  }
  
  public static abstract interface OnIndoorStateChangeListener
  {
    public abstract void onIndoorBuildingFocused();
    
    public abstract void onIndoorLevelActivated(IndoorBuilding paramIndoorBuilding);
  }
  
  public static abstract interface OnInfoWindowClickListener
  {
    public abstract void onInfoWindowClick(Marker paramMarker);
  }
  
  public static abstract interface OnInfoWindowCloseListener
  {
    public abstract void onInfoWindowClose(Marker paramMarker);
  }
  
  public static abstract interface OnInfoWindowLongClickListener
  {
    public abstract void onInfoWindowLongClick(Marker paramMarker);
  }
  
  public static abstract interface OnMapClickListener
  {
    public abstract void onMapClick(LatLng paramLatLng);
  }
  
  public static abstract interface OnMapLoadedCallback
  {
    public abstract void onMapLoaded();
  }
  
  public static abstract interface OnMapLongClickListener
  {
    public abstract void onMapLongClick(LatLng paramLatLng);
  }
  
  public static abstract interface OnMarkerClickListener
  {
    public abstract boolean onMarkerClick(Marker paramMarker);
  }
  
  public static abstract interface OnMarkerDragListener
  {
    public abstract void onMarkerDrag(Marker paramMarker);
    
    public abstract void onMarkerDragEnd(Marker paramMarker);
    
    public abstract void onMarkerDragStart(Marker paramMarker);
  }
  
  public static abstract interface OnMyLocationButtonClickListener
  {
    public abstract boolean onMyLocationButtonClick();
  }
  
  @Deprecated
  public static abstract interface OnMyLocationChangeListener
  {
    public abstract void onMyLocationChange(Location paramLocation);
  }
  
  public static abstract interface OnMyLocationClickListener
  {
    public abstract void onMyLocationClick(@NonNull Location paramLocation);
  }
  
  public static abstract interface OnPoiClickListener
  {
    public abstract void onPoiClick(PointOfInterest paramPointOfInterest);
  }
  
  public static abstract interface OnPolygonClickListener
  {
    public abstract void onPolygonClick(Polygon paramPolygon);
  }
  
  public static abstract interface OnPolylineClickListener
  {
    public abstract void onPolylineClick(Polyline paramPolyline);
  }
  
  public static abstract interface SnapshotReadyCallback
  {
    public abstract void onSnapshotReady(Bitmap paramBitmap);
  }
  
  static final class zza
    extends com.google.android.gms.maps.internal.zzd
  {
    private final GoogleMap.CancelableCallback zza;
    
    zza(GoogleMap.CancelableCallback paramCancelableCallback)
    {
      zza = paramCancelableCallback;
    }
    
    public final void zza()
    {
      zza.onFinish();
    }
    
    public final void zzb()
    {
      zza.onCancel();
    }
  }
}
