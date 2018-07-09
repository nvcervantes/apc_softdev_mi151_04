package com.google.android.gms.maps.internal;

import android.location.Location;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzev;
import com.google.android.gms.internal.zzex;
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
import com.google.android.gms.maps.model.internal.IPolylineDelegate.zza;
import com.google.android.gms.maps.model.internal.zzd;
import com.google.android.gms.maps.model.internal.zze;
import com.google.android.gms.maps.model.internal.zzj;
import com.google.android.gms.maps.model.internal.zzk;
import com.google.android.gms.maps.model.internal.zzq;
import com.google.android.gms.maps.model.internal.zzs;
import com.google.android.gms.maps.model.internal.zzw;

public final class zzg
  extends zzev
  implements IGoogleMapDelegate
{
  zzg(IBinder paramIBinder)
  {
    super(paramIBinder, "com.google.android.gms.maps.internal.IGoogleMapDelegate");
  }
  
  public final zzd addCircle(CircleOptions paramCircleOptions)
    throws RemoteException
  {
    Object localObject = a_();
    zzex.zza((Parcel)localObject, paramCircleOptions);
    paramCircleOptions = zza(35, (Parcel)localObject);
    localObject = zze.zza(paramCircleOptions.readStrongBinder());
    paramCircleOptions.recycle();
    return localObject;
  }
  
  public final com.google.android.gms.maps.model.internal.zzg addGroundOverlay(GroundOverlayOptions paramGroundOverlayOptions)
    throws RemoteException
  {
    Object localObject = a_();
    zzex.zza((Parcel)localObject, paramGroundOverlayOptions);
    paramGroundOverlayOptions = zza(12, (Parcel)localObject);
    localObject = com.google.android.gms.maps.model.internal.zzh.zza(paramGroundOverlayOptions.readStrongBinder());
    paramGroundOverlayOptions.recycle();
    return localObject;
  }
  
  public final com.google.android.gms.maps.model.internal.zzp addMarker(MarkerOptions paramMarkerOptions)
    throws RemoteException
  {
    Object localObject = a_();
    zzex.zza((Parcel)localObject, paramMarkerOptions);
    paramMarkerOptions = zza(11, (Parcel)localObject);
    localObject = zzq.zza(paramMarkerOptions.readStrongBinder());
    paramMarkerOptions.recycle();
    return localObject;
  }
  
  public final zzs addPolygon(PolygonOptions paramPolygonOptions)
    throws RemoteException
  {
    Object localObject = a_();
    zzex.zza((Parcel)localObject, paramPolygonOptions);
    paramPolygonOptions = zza(10, (Parcel)localObject);
    localObject = com.google.android.gms.maps.model.internal.zzt.zza(paramPolygonOptions.readStrongBinder());
    paramPolygonOptions.recycle();
    return localObject;
  }
  
  public final IPolylineDelegate addPolyline(PolylineOptions paramPolylineOptions)
    throws RemoteException
  {
    Object localObject = a_();
    zzex.zza((Parcel)localObject, paramPolylineOptions);
    paramPolylineOptions = zza(9, (Parcel)localObject);
    localObject = IPolylineDelegate.zza.zza(paramPolylineOptions.readStrongBinder());
    paramPolylineOptions.recycle();
    return localObject;
  }
  
  public final zzw addTileOverlay(TileOverlayOptions paramTileOverlayOptions)
    throws RemoteException
  {
    Object localObject = a_();
    zzex.zza((Parcel)localObject, paramTileOverlayOptions);
    paramTileOverlayOptions = zza(13, (Parcel)localObject);
    localObject = com.google.android.gms.maps.model.internal.zzx.zza(paramTileOverlayOptions.readStrongBinder());
    paramTileOverlayOptions.recycle();
    return localObject;
  }
  
  public final void animateCamera(IObjectWrapper paramIObjectWrapper)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramIObjectWrapper);
    zzb(5, localParcel);
  }
  
  public final void animateCameraWithCallback(IObjectWrapper paramIObjectWrapper, zzc paramZzc)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramIObjectWrapper);
    zzex.zza(localParcel, paramZzc);
    zzb(6, localParcel);
  }
  
  public final void animateCameraWithDurationAndCallback(IObjectWrapper paramIObjectWrapper, int paramInt, zzc paramZzc)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramIObjectWrapper);
    localParcel.writeInt(paramInt);
    zzex.zza(localParcel, paramZzc);
    zzb(7, localParcel);
  }
  
  public final void clear()
    throws RemoteException
  {
    zzb(14, a_());
  }
  
  public final CameraPosition getCameraPosition()
    throws RemoteException
  {
    Parcel localParcel = zza(1, a_());
    CameraPosition localCameraPosition = (CameraPosition)zzex.zza(localParcel, CameraPosition.CREATOR);
    localParcel.recycle();
    return localCameraPosition;
  }
  
  public final zzj getFocusedBuilding()
    throws RemoteException
  {
    Parcel localParcel = zza(44, a_());
    zzj localZzj = zzk.zza(localParcel.readStrongBinder());
    localParcel.recycle();
    return localZzj;
  }
  
  public final void getMapAsync(zzap paramZzap)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramZzap);
    zzb(53, localParcel);
  }
  
  public final int getMapType()
    throws RemoteException
  {
    Parcel localParcel = zza(15, a_());
    int i = localParcel.readInt();
    localParcel.recycle();
    return i;
  }
  
  public final float getMaxZoomLevel()
    throws RemoteException
  {
    Parcel localParcel = zza(2, a_());
    float f = localParcel.readFloat();
    localParcel.recycle();
    return f;
  }
  
  public final float getMinZoomLevel()
    throws RemoteException
  {
    Parcel localParcel = zza(3, a_());
    float f = localParcel.readFloat();
    localParcel.recycle();
    return f;
  }
  
  public final Location getMyLocation()
    throws RemoteException
  {
    Parcel localParcel = zza(23, a_());
    Location localLocation = (Location)zzex.zza(localParcel, Location.CREATOR);
    localParcel.recycle();
    return localLocation;
  }
  
  public final IProjectionDelegate getProjection()
    throws RemoteException
  {
    Parcel localParcel = zza(26, a_());
    Object localObject = localParcel.readStrongBinder();
    if (localObject == null)
    {
      localObject = null;
    }
    else
    {
      IInterface localIInterface = ((IBinder)localObject).queryLocalInterface("com.google.android.gms.maps.internal.IProjectionDelegate");
      if ((localIInterface instanceof IProjectionDelegate)) {
        localObject = (IProjectionDelegate)localIInterface;
      } else {
        localObject = new zzbr((IBinder)localObject);
      }
    }
    localParcel.recycle();
    return localObject;
  }
  
  public final IUiSettingsDelegate getUiSettings()
    throws RemoteException
  {
    Parcel localParcel = zza(25, a_());
    Object localObject = localParcel.readStrongBinder();
    if (localObject == null)
    {
      localObject = null;
    }
    else
    {
      IInterface localIInterface = ((IBinder)localObject).queryLocalInterface("com.google.android.gms.maps.internal.IUiSettingsDelegate");
      if ((localIInterface instanceof IUiSettingsDelegate)) {
        localObject = (IUiSettingsDelegate)localIInterface;
      } else {
        localObject = new zzbx((IBinder)localObject);
      }
    }
    localParcel.recycle();
    return localObject;
  }
  
  public final boolean isBuildingsEnabled()
    throws RemoteException
  {
    Parcel localParcel = zza(40, a_());
    boolean bool = zzex.zza(localParcel);
    localParcel.recycle();
    return bool;
  }
  
  public final boolean isIndoorEnabled()
    throws RemoteException
  {
    Parcel localParcel = zza(19, a_());
    boolean bool = zzex.zza(localParcel);
    localParcel.recycle();
    return bool;
  }
  
  public final boolean isMyLocationEnabled()
    throws RemoteException
  {
    Parcel localParcel = zza(21, a_());
    boolean bool = zzex.zza(localParcel);
    localParcel.recycle();
    return bool;
  }
  
  public final boolean isTrafficEnabled()
    throws RemoteException
  {
    Parcel localParcel = zza(17, a_());
    boolean bool = zzex.zza(localParcel);
    localParcel.recycle();
    return bool;
  }
  
  public final void moveCamera(IObjectWrapper paramIObjectWrapper)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramIObjectWrapper);
    zzb(4, localParcel);
  }
  
  public final void onCreate(Bundle paramBundle)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramBundle);
    zzb(54, localParcel);
  }
  
  public final void onDestroy()
    throws RemoteException
  {
    zzb(57, a_());
  }
  
  public final void onEnterAmbient(Bundle paramBundle)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramBundle);
    zzb(81, localParcel);
  }
  
  public final void onExitAmbient()
    throws RemoteException
  {
    zzb(82, a_());
  }
  
  public final void onLowMemory()
    throws RemoteException
  {
    zzb(58, a_());
  }
  
  public final void onPause()
    throws RemoteException
  {
    zzb(56, a_());
  }
  
  public final void onResume()
    throws RemoteException
  {
    zzb(55, a_());
  }
  
  public final void onSaveInstanceState(Bundle paramBundle)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramBundle);
    localParcel = zza(60, localParcel);
    if (localParcel.readInt() != 0) {
      paramBundle.readFromParcel(localParcel);
    }
    localParcel.recycle();
  }
  
  public final void onStart()
    throws RemoteException
  {
    zzb(101, a_());
  }
  
  public final void onStop()
    throws RemoteException
  {
    zzb(102, a_());
  }
  
  public final void resetMinMaxZoomPreference()
    throws RemoteException
  {
    zzb(94, a_());
  }
  
  public final void setBuildingsEnabled(boolean paramBoolean)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramBoolean);
    zzb(41, localParcel);
  }
  
  public final void setContentDescription(String paramString)
    throws RemoteException
  {
    Parcel localParcel = a_();
    localParcel.writeString(paramString);
    zzb(61, localParcel);
  }
  
  public final boolean setIndoorEnabled(boolean paramBoolean)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramBoolean);
    localParcel = zza(20, localParcel);
    paramBoolean = zzex.zza(localParcel);
    localParcel.recycle();
    return paramBoolean;
  }
  
  public final void setInfoWindowAdapter(zzh paramZzh)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramZzh);
    zzb(33, localParcel);
  }
  
  public final void setLatLngBoundsForCameraTarget(LatLngBounds paramLatLngBounds)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramLatLngBounds);
    zzb(95, localParcel);
  }
  
  public final void setLocationSource(ILocationSourceDelegate paramILocationSourceDelegate)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramILocationSourceDelegate);
    zzb(24, localParcel);
  }
  
  public final boolean setMapStyle(MapStyleOptions paramMapStyleOptions)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramMapStyleOptions);
    paramMapStyleOptions = zza(91, localParcel);
    boolean bool = zzex.zza(paramMapStyleOptions);
    paramMapStyleOptions.recycle();
    return bool;
  }
  
  public final void setMapType(int paramInt)
    throws RemoteException
  {
    Parcel localParcel = a_();
    localParcel.writeInt(paramInt);
    zzb(16, localParcel);
  }
  
  public final void setMaxZoomPreference(float paramFloat)
    throws RemoteException
  {
    Parcel localParcel = a_();
    localParcel.writeFloat(paramFloat);
    zzb(93, localParcel);
  }
  
  public final void setMinZoomPreference(float paramFloat)
    throws RemoteException
  {
    Parcel localParcel = a_();
    localParcel.writeFloat(paramFloat);
    zzb(92, localParcel);
  }
  
  public final void setMyLocationEnabled(boolean paramBoolean)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramBoolean);
    zzb(22, localParcel);
  }
  
  public final void setOnCameraChangeListener(zzl paramZzl)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramZzl);
    zzb(27, localParcel);
  }
  
  public final void setOnCameraIdleListener(zzn paramZzn)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramZzn);
    zzb(99, localParcel);
  }
  
  public final void setOnCameraMoveCanceledListener(zzp paramZzp)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramZzp);
    zzb(98, localParcel);
  }
  
  public final void setOnCameraMoveListener(zzr paramZzr)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramZzr);
    zzb(97, localParcel);
  }
  
  public final void setOnCameraMoveStartedListener(zzt paramZzt)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramZzt);
    zzb(96, localParcel);
  }
  
  public final void setOnCircleClickListener(zzv paramZzv)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramZzv);
    zzb(89, localParcel);
  }
  
  public final void setOnGroundOverlayClickListener(zzx paramZzx)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramZzx);
    zzb(83, localParcel);
  }
  
  public final void setOnIndoorStateChangeListener(zzz paramZzz)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramZzz);
    zzb(45, localParcel);
  }
  
  public final void setOnInfoWindowClickListener(zzab paramZzab)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramZzab);
    zzb(32, localParcel);
  }
  
  public final void setOnInfoWindowCloseListener(zzad paramZzad)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramZzad);
    zzb(86, localParcel);
  }
  
  public final void setOnInfoWindowLongClickListener(zzaf paramZzaf)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramZzaf);
    zzb(84, localParcel);
  }
  
  public final void setOnMapClickListener(zzaj paramZzaj)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramZzaj);
    zzb(28, localParcel);
  }
  
  public final void setOnMapLoadedCallback(zzal paramZzal)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramZzal);
    zzb(42, localParcel);
  }
  
  public final void setOnMapLongClickListener(zzan paramZzan)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramZzan);
    zzb(29, localParcel);
  }
  
  public final void setOnMarkerClickListener(zzar paramZzar)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramZzar);
    zzb(30, localParcel);
  }
  
  public final void setOnMarkerDragListener(zzat paramZzat)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramZzat);
    zzb(31, localParcel);
  }
  
  public final void setOnMyLocationButtonClickListener(zzav paramZzav)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramZzav);
    zzb(37, localParcel);
  }
  
  public final void setOnMyLocationChangeListener(zzax paramZzax)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramZzax);
    zzb(36, localParcel);
  }
  
  public final void setOnMyLocationClickListener(zzaz paramZzaz)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramZzaz);
    zzb(107, localParcel);
  }
  
  public final void setOnPoiClickListener(zzbb paramZzbb)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramZzbb);
    zzb(80, localParcel);
  }
  
  public final void setOnPolygonClickListener(zzbd paramZzbd)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramZzbd);
    zzb(85, localParcel);
  }
  
  public final void setOnPolylineClickListener(zzbf paramZzbf)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramZzbf);
    zzb(87, localParcel);
  }
  
  public final void setPadding(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    throws RemoteException
  {
    Parcel localParcel = a_();
    localParcel.writeInt(paramInt1);
    localParcel.writeInt(paramInt2);
    localParcel.writeInt(paramInt3);
    localParcel.writeInt(paramInt4);
    zzb(39, localParcel);
  }
  
  public final void setTrafficEnabled(boolean paramBoolean)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramBoolean);
    zzb(18, localParcel);
  }
  
  public final void setWatermarkEnabled(boolean paramBoolean)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramBoolean);
    zzb(51, localParcel);
  }
  
  public final void snapshot(zzbs paramZzbs, IObjectWrapper paramIObjectWrapper)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramZzbs);
    zzex.zza(localParcel, paramIObjectWrapper);
    zzb(38, localParcel);
  }
  
  public final void snapshotForTest(zzbs paramZzbs)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramZzbs);
    zzb(71, localParcel);
  }
  
  public final void stopAnimation()
    throws RemoteException
  {
    zzb(8, a_());
  }
  
  public final boolean useViewLifecycleWhenInFragment()
    throws RemoteException
  {
    Parcel localParcel = zza(59, a_());
    boolean bool = zzex.zza(localParcel);
    localParcel.recycle();
    return bool;
  }
}
