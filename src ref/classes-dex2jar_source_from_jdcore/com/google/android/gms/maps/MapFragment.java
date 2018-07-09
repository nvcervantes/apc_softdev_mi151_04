package com.google.android.gms.maps;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.StrictMode;
import android.os.StrictMode.ThreadPolicy;
import android.os.StrictMode.ThreadPolicy.Builder;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.dynamic.zza;
import com.google.android.gms.dynamic.zzn;
import com.google.android.gms.dynamic.zzo;
import com.google.android.gms.maps.internal.IMapFragmentDelegate;
import com.google.android.gms.maps.internal.MapLifecycleDelegate;
import com.google.android.gms.maps.internal.zzby;
import com.google.android.gms.maps.internal.zzbz;
import com.google.android.gms.maps.internal.zze;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@TargetApi(11)
public class MapFragment
  extends Fragment
{
  @Hide
  private final zzb zza = new zzb(this);
  
  public MapFragment() {}
  
  public static MapFragment newInstance()
  {
    return new MapFragment();
  }
  
  public static MapFragment newInstance(GoogleMapOptions paramGoogleMapOptions)
  {
    MapFragment localMapFragment = new MapFragment();
    Bundle localBundle = new Bundle();
    localBundle.putParcelable("MapOptions", paramGoogleMapOptions);
    localMapFragment.setArguments(localBundle);
    return localMapFragment;
  }
  
  public void getMapAsync(OnMapReadyCallback paramOnMapReadyCallback)
  {
    zzbq.zzb("getMapAsync must be called on the main thread.");
    zza.zza(paramOnMapReadyCallback);
  }
  
  public void onActivityCreated(Bundle paramBundle)
  {
    if (paramBundle != null) {
      paramBundle.setClassLoader(MapFragment.class.getClassLoader());
    }
    super.onActivityCreated(paramBundle);
  }
  
  public void onAttach(Activity paramActivity)
  {
    super.onAttach(paramActivity);
    zzb.zza(zza, paramActivity);
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    zza.zza(paramBundle);
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    paramLayoutInflater = zza.zza(paramLayoutInflater, paramViewGroup, paramBundle);
    paramLayoutInflater.setClickable(true);
    return paramLayoutInflater;
  }
  
  public void onDestroy()
  {
    zza.zzg();
    super.onDestroy();
  }
  
  public void onDestroyView()
  {
    zza.zzf();
    super.onDestroyView();
  }
  
  public final void onEnterAmbient(Bundle paramBundle)
  {
    zzbq.zzb("onEnterAmbient must be called on the main thread.");
    zzb localZzb = zza;
    if (localZzb.zza() != null) {
      ((zza)localZzb.zza()).zza(paramBundle);
    }
  }
  
  public final void onExitAmbient()
  {
    zzbq.zzb("onExitAmbient must be called on the main thread.");
    zzb localZzb = zza;
    if (localZzb.zza() != null) {
      ((zza)localZzb.zza()).zza();
    }
  }
  
  @SuppressLint({"NewApi"})
  public void onInflate(Activity paramActivity, AttributeSet paramAttributeSet, Bundle paramBundle)
  {
    StrictMode.ThreadPolicy localThreadPolicy = StrictMode.getThreadPolicy();
    StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder(localThreadPolicy).permitAll().build());
    try
    {
      super.onInflate(paramActivity, paramAttributeSet, paramBundle);
      zzb.zza(zza, paramActivity);
      paramAttributeSet = GoogleMapOptions.createFromAttributes(paramActivity, paramAttributeSet);
      Bundle localBundle = new Bundle();
      localBundle.putParcelable("MapOptions", paramAttributeSet);
      zza.zza(paramActivity, localBundle, paramBundle);
      return;
    }
    finally
    {
      StrictMode.setThreadPolicy(localThreadPolicy);
    }
  }
  
  public void onLowMemory()
  {
    zza.zzh();
    super.onLowMemory();
  }
  
  public void onPause()
  {
    zza.zzd();
    super.onPause();
  }
  
  public void onResume()
  {
    super.onResume();
    zza.zzc();
  }
  
  public void onSaveInstanceState(Bundle paramBundle)
  {
    if (paramBundle != null) {
      paramBundle.setClassLoader(MapFragment.class.getClassLoader());
    }
    super.onSaveInstanceState(paramBundle);
    zza.zzb(paramBundle);
  }
  
  public void onStart()
  {
    super.onStart();
    zza.zzb();
  }
  
  public void onStop()
  {
    zza.zze();
    super.onStop();
  }
  
  public void setArguments(Bundle paramBundle)
  {
    super.setArguments(paramBundle);
  }
  
  @Hide
  static final class zza
    implements MapLifecycleDelegate
  {
    private final Fragment zza;
    private final IMapFragmentDelegate zzb;
    
    public zza(Fragment paramFragment, IMapFragmentDelegate paramIMapFragmentDelegate)
    {
      zzb = ((IMapFragmentDelegate)zzbq.zza(paramIMapFragmentDelegate));
      zza = ((Fragment)zzbq.zza(paramFragment));
    }
    
    public final void getMapAsync(OnMapReadyCallback paramOnMapReadyCallback)
    {
      try
      {
        zzb.getMapAsync(new zzab(this, paramOnMapReadyCallback));
        return;
      }
      catch (RemoteException paramOnMapReadyCallback)
      {
        throw new RuntimeRemoteException(paramOnMapReadyCallback);
      }
    }
    
    public final void onCreate(Bundle paramBundle)
    {
      try
      {
        Bundle localBundle1 = new Bundle();
        zzby.zza(paramBundle, localBundle1);
        Bundle localBundle2 = zza.getArguments();
        if ((localBundle2 != null) && (localBundle2.containsKey("MapOptions"))) {
          zzby.zza(localBundle1, "MapOptions", localBundle2.getParcelable("MapOptions"));
        }
        zzb.onCreate(localBundle1);
        zzby.zza(localBundle1, paramBundle);
        return;
      }
      catch (RemoteException paramBundle)
      {
        throw new RuntimeRemoteException(paramBundle);
      }
    }
    
    public final View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
    {
      try
      {
        Bundle localBundle = new Bundle();
        zzby.zza(paramBundle, localBundle);
        paramLayoutInflater = zzb.onCreateView(zzn.zza(paramLayoutInflater), zzn.zza(paramViewGroup), localBundle);
        zzby.zza(localBundle, paramBundle);
        return (View)zzn.zza(paramLayoutInflater);
      }
      catch (RemoteException paramLayoutInflater)
      {
        throw new RuntimeRemoteException(paramLayoutInflater);
      }
    }
    
    public final void onDestroy()
    {
      try
      {
        zzb.onDestroy();
        return;
      }
      catch (RemoteException localRemoteException)
      {
        throw new RuntimeRemoteException(localRemoteException);
      }
    }
    
    public final void onDestroyView()
    {
      try
      {
        zzb.onDestroyView();
        return;
      }
      catch (RemoteException localRemoteException)
      {
        throw new RuntimeRemoteException(localRemoteException);
      }
    }
    
    public final void onInflate(Activity paramActivity, Bundle paramBundle1, Bundle paramBundle2)
    {
      paramBundle1 = (GoogleMapOptions)paramBundle1.getParcelable("MapOptions");
      try
      {
        Bundle localBundle = new Bundle();
        zzby.zza(paramBundle2, localBundle);
        zzb.onInflate(zzn.zza(paramActivity), paramBundle1, localBundle);
        zzby.zza(localBundle, paramBundle2);
        return;
      }
      catch (RemoteException paramActivity)
      {
        throw new RuntimeRemoteException(paramActivity);
      }
    }
    
    public final void onLowMemory()
    {
      try
      {
        zzb.onLowMemory();
        return;
      }
      catch (RemoteException localRemoteException)
      {
        throw new RuntimeRemoteException(localRemoteException);
      }
    }
    
    public final void onPause()
    {
      try
      {
        zzb.onPause();
        return;
      }
      catch (RemoteException localRemoteException)
      {
        throw new RuntimeRemoteException(localRemoteException);
      }
    }
    
    public final void onResume()
    {
      try
      {
        zzb.onResume();
        return;
      }
      catch (RemoteException localRemoteException)
      {
        throw new RuntimeRemoteException(localRemoteException);
      }
    }
    
    public final void onSaveInstanceState(Bundle paramBundle)
    {
      try
      {
        Bundle localBundle = new Bundle();
        zzby.zza(paramBundle, localBundle);
        zzb.onSaveInstanceState(localBundle);
        zzby.zza(localBundle, paramBundle);
        return;
      }
      catch (RemoteException paramBundle)
      {
        throw new RuntimeRemoteException(paramBundle);
      }
    }
    
    public final void onStart()
    {
      try
      {
        zzb.onStart();
        return;
      }
      catch (RemoteException localRemoteException)
      {
        throw new RuntimeRemoteException(localRemoteException);
      }
    }
    
    public final void onStop()
    {
      try
      {
        zzb.onStop();
        return;
      }
      catch (RemoteException localRemoteException)
      {
        throw new RuntimeRemoteException(localRemoteException);
      }
    }
    
    public final void zza()
    {
      try
      {
        zzb.onExitAmbient();
        return;
      }
      catch (RemoteException localRemoteException)
      {
        throw new RuntimeRemoteException(localRemoteException);
      }
    }
    
    public final void zza(Bundle paramBundle)
    {
      try
      {
        Bundle localBundle = new Bundle();
        zzby.zza(paramBundle, localBundle);
        zzb.onEnterAmbient(localBundle);
        zzby.zza(localBundle, paramBundle);
        return;
      }
      catch (RemoteException paramBundle)
      {
        throw new RuntimeRemoteException(paramBundle);
      }
    }
  }
  
  static final class zzb
    extends zza<MapFragment.zza>
  {
    private final Fragment zza;
    private zzo<MapFragment.zza> zzb;
    private Activity zzc;
    private final List<OnMapReadyCallback> zzd = new ArrayList();
    
    zzb(Fragment paramFragment)
    {
      zza = paramFragment;
    }
    
    private final void zza(Activity paramActivity)
    {
      zzc = paramActivity;
      zzi();
    }
    
    private final void zzi()
    {
      if ((zzc != null) && (zzb != null) && (zza() == null)) {}
      try
      {
        MapsInitializer.initialize(zzc);
        Object localObject = zzbz.zza(zzc).zza(zzn.zza(zzc));
        if (localObject == null) {
          return;
        }
        zzb.zza(new MapFragment.zza(zza, (IMapFragmentDelegate)localObject));
        localObject = zzd.iterator();
        while (((Iterator)localObject).hasNext())
        {
          OnMapReadyCallback localOnMapReadyCallback = (OnMapReadyCallback)((Iterator)localObject).next();
          ((MapFragment.zza)zza()).getMapAsync(localOnMapReadyCallback);
        }
        zzd.clear();
        return;
      }
      catch (RemoteException localRemoteException)
      {
        throw new RuntimeRemoteException(localRemoteException);
        return;
      }
      catch (GooglePlayServicesNotAvailableException localGooglePlayServicesNotAvailableException) {}
    }
    
    protected final void zza(zzo<MapFragment.zza> paramZzo)
    {
      zzb = paramZzo;
      zzi();
    }
    
    public final void zza(OnMapReadyCallback paramOnMapReadyCallback)
    {
      if (zza() != null)
      {
        ((MapFragment.zza)zza()).getMapAsync(paramOnMapReadyCallback);
        return;
      }
      zzd.add(paramOnMapReadyCallback);
    }
  }
}
