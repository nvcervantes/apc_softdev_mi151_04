package com.google.android.gms.maps.internal;

import android.location.Location;
import android.os.Bundle;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.TileOverlayOptions;
import com.google.android.gms.maps.model.internal.IPolylineDelegate;
import com.google.android.gms.maps.model.internal.zzd;
import com.google.android.gms.maps.model.internal.zzg;
import com.google.android.gms.maps.model.internal.zzj;
import com.google.android.gms.maps.model.internal.zzs;
import com.google.android.gms.maps.model.internal.zzw;

@Hide
public abstract interface IGoogleMapDelegate
  extends IInterface
{
  public abstract zzd addCircle(CircleOptions paramCircleOptions)
    throws RemoteException;
  
  public abstract zzg addGroundOverlay(GroundOverlayOptions paramGroundOverlayOptions)
    throws RemoteException;
  
  public abstract com.google.android.gms.maps.model.internal.zzp addMarker(MarkerOptions paramMarkerOptions)
    throws RemoteException;
  
  public abstract zzs addPolygon(PolygonOptions paramPolygonOptions)
    throws RemoteException;
  
  public abstract IPolylineDelegate addPolyline(PolylineOptions paramPolylineOptions)
    throws RemoteException;
  
  public abstract zzw addTileOverlay(TileOverlayOptions paramTileOverlayOptions)
    throws RemoteException;
  
  public abstract void animateCamera(IObjectWrapper paramIObjectWrapper)
    throws RemoteException;
  
  public abstract void animateCameraWithCallback(IObjectWrapper paramIObjectWrapper, zzc paramZzc)
    throws RemoteException;
  
  public abstract void animateCameraWithDurationAndCallback(IObjectWrapper paramIObjectWrapper, int paramInt, zzc paramZzc)
    throws RemoteException;
  
  public abstract void clear()
    throws RemoteException;
  
  public abstract CameraPosition getCameraPosition()
    throws RemoteException;
  
  public abstract zzj getFocusedBuilding()
    throws RemoteException;
  
  public abstract void getMapAsync(zzap paramZzap)
    throws RemoteException;
  
  public abstract int getMapType()
    throws RemoteException;
  
  public abstract float getMaxZoomLevel()
    throws RemoteException;
  
  public abstract float getMinZoomLevel()
    throws RemoteException;
  
  public abstract Location getMyLocation()
    throws RemoteException;
  
  public abstract IProjectionDelegate getProjection()
    throws RemoteException;
  
  public abstract IUiSettingsDelegate getUiSettings()
    throws RemoteException;
  
  public abstract boolean isBuildingsEnabled()
    throws RemoteException;
  
  public abstract boolean isIndoorEnabled()
    throws RemoteException;
  
  public abstract boolean isMyLocationEnabled()
    throws RemoteException;
  
  public abstract boolean isTrafficEnabled()
    throws RemoteException;
  
  public abstract void moveCamera(IObjectWrapper paramIObjectWrapper)
    throws RemoteException;
  
  public abstract void onCreate(Bundle paramBundle)
    throws RemoteException;
  
  public abstract void onDestroy()
    throws RemoteException;
  
  public abstract void onEnterAmbient(Bundle paramBundle)
    throws RemoteException;
  
  public abstract void onExitAmbient()
    throws RemoteException;
  
  public abstract void onLowMemory()
    throws RemoteException;
  
  public abstract void onPause()
    throws RemoteException;
  
  public abstract void onResume()
    throws RemoteException;
  
  public abstract void onSaveInstanceState(Bundle paramBundle)
    throws RemoteException;
  
  public abstract void onStart()
    throws RemoteException;
  
  public abstract void onStop()
    throws RemoteException;
  
  public abstract void resetMinMaxZoomPreference()
    throws RemoteException;
  
  public abstract void setBuildingsEnabled(boolean paramBoolean)
    throws RemoteException;
  
  public abstract void setContentDescription(String paramString)
    throws RemoteException;
  
  public abstract boolean setIndoorEnabled(boolean paramBoolean)
    throws RemoteException;
  
  public abstract void setInfoWindowAdapter(zzh paramZzh)
    throws RemoteException;
  
  public abstract void setLatLngBoundsForCameraTarget(LatLngBounds paramLatLngBounds)
    throws RemoteException;
  
  public abstract void setLocationSource(ILocationSourceDelegate paramILocationSourceDelegate)
    throws RemoteException;
  
  public abstract boolean setMapStyle(MapStyleOptions paramMapStyleOptions)
    throws RemoteException;
  
  public abstract void setMapType(int paramInt)
    throws RemoteException;
  
  public abstract void setMaxZoomPreference(float paramFloat)
    throws RemoteException;
  
  public abstract void setMinZoomPreference(float paramFloat)
    throws RemoteException;
  
  public abstract void setMyLocationEnabled(boolean paramBoolean)
    throws RemoteException;
  
  public abstract void setOnCameraChangeListener(zzl paramZzl)
    throws RemoteException;
  
  public abstract void setOnCameraIdleListener(zzn paramZzn)
    throws RemoteException;
  
  public abstract void setOnCameraMoveCanceledListener(zzp paramZzp)
    throws RemoteException;
  
  public abstract void setOnCameraMoveListener(zzr paramZzr)
    throws RemoteException;
  
  public abstract void setOnCameraMoveStartedListener(zzt paramZzt)
    throws RemoteException;
  
  public abstract void setOnCircleClickListener(zzv paramZzv)
    throws RemoteException;
  
  public abstract void setOnGroundOverlayClickListener(zzx paramZzx)
    throws RemoteException;
  
  public abstract void setOnIndoorStateChangeListener(zzz paramZzz)
    throws RemoteException;
  
  public abstract void setOnInfoWindowClickListener(zzab paramZzab)
    throws RemoteException;
  
  public abstract void setOnInfoWindowCloseListener(zzad paramZzad)
    throws RemoteException;
  
  public abstract void setOnInfoWindowLongClickListener(zzaf paramZzaf)
    throws RemoteException;
  
  public abstract void setOnMapClickListener(zzaj paramZzaj)
    throws RemoteException;
  
  public abstract void setOnMapLoadedCallback(zzal paramZzal)
    throws RemoteException;
  
  public abstract void setOnMapLongClickListener(zzan paramZzan)
    throws RemoteException;
  
  public abstract void setOnMarkerClickListener(zzar paramZzar)
    throws RemoteException;
  
  public abstract void setOnMarkerDragListener(zzat paramZzat)
    throws RemoteException;
  
  public abstract void setOnMyLocationButtonClickListener(zzav paramZzav)
    throws RemoteException;
  
  public abstract void setOnMyLocationChangeListener(zzax paramZzax)
    throws RemoteException;
  
  public abstract void setOnMyLocationClickListener(zzaz paramZzaz)
    throws RemoteException;
  
  public abstract void setOnPoiClickListener(zzbb paramZzbb)
    throws RemoteException;
  
  public abstract void setOnPolygonClickListener(zzbd paramZzbd)
    throws RemoteException;
  
  public abstract void setOnPolylineClickListener(zzbf paramZzbf)
    throws RemoteException;
  
  public abstract void setPadding(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    throws RemoteException;
  
  public abstract void setTrafficEnabled(boolean paramBoolean)
    throws RemoteException;
  
  public abstract void setWatermarkEnabled(boolean paramBoolean)
    throws RemoteException;
  
  public abstract void snapshot(zzbs paramZzbs, IObjectWrapper paramIObjectWrapper)
    throws RemoteException;
  
  public abstract void snapshotForTest(zzbs paramZzbs)
    throws RemoteException;
  
  public abstract void stopAnimation()
    throws RemoteException;
  
  public abstract boolean useViewLifecycleWhenInFragment()
    throws RemoteException;
}
