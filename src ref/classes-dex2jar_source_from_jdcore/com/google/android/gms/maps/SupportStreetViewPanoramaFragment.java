package com.google.android.gms.maps;

import android.app.Activity;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.StrictMode;
import android.os.StrictMode.ThreadPolicy;
import android.os.StrictMode.ThreadPolicy.Builder;
import android.support.v4.app.Fragment;
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
import com.google.android.gms.maps.internal.IStreetViewPanoramaFragmentDelegate;
import com.google.android.gms.maps.internal.StreetViewLifecycleDelegate;
import com.google.android.gms.maps.internal.zzby;
import com.google.android.gms.maps.internal.zzbz;
import com.google.android.gms.maps.internal.zze;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SupportStreetViewPanoramaFragment
  extends Fragment
{
  @Hide
  private final zzb zza = new zzb(this);
  
  public SupportStreetViewPanoramaFragment() {}
  
  public static SupportStreetViewPanoramaFragment newInstance()
  {
    return new SupportStreetViewPanoramaFragment();
  }
  
  public static SupportStreetViewPanoramaFragment newInstance(StreetViewPanoramaOptions paramStreetViewPanoramaOptions)
  {
    SupportStreetViewPanoramaFragment localSupportStreetViewPanoramaFragment = new SupportStreetViewPanoramaFragment();
    Bundle localBundle = new Bundle();
    localBundle.putParcelable("StreetViewPanoramaOptions", paramStreetViewPanoramaOptions);
    localSupportStreetViewPanoramaFragment.setArguments(localBundle);
    return localSupportStreetViewPanoramaFragment;
  }
  
  public void getStreetViewPanoramaAsync(OnStreetViewPanoramaReadyCallback paramOnStreetViewPanoramaReadyCallback)
  {
    zzbq.zzb("getStreetViewPanoramaAsync() must be called on the main thread");
    zza.zza(paramOnStreetViewPanoramaReadyCallback);
  }
  
  public void onActivityCreated(Bundle paramBundle)
  {
    if (paramBundle != null) {
      paramBundle.setClassLoader(SupportStreetViewPanoramaFragment.class.getClassLoader());
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
    return zza.zza(paramLayoutInflater, paramViewGroup, paramBundle);
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
  
  public void onInflate(Activity paramActivity, AttributeSet paramAttributeSet, Bundle paramBundle)
  {
    StrictMode.ThreadPolicy localThreadPolicy = StrictMode.getThreadPolicy();
    StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder(localThreadPolicy).permitAll().build());
    try
    {
      super.onInflate(paramActivity, paramAttributeSet, paramBundle);
      zzb.zza(zza, paramActivity);
      paramAttributeSet = new Bundle();
      zza.zza(paramActivity, paramAttributeSet, paramBundle);
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
      paramBundle.setClassLoader(SupportStreetViewPanoramaFragment.class.getClassLoader());
    }
    super.onSaveInstanceState(paramBundle);
    zza.zzb(paramBundle);
  }
  
  public void setArguments(Bundle paramBundle)
  {
    super.setArguments(paramBundle);
  }
  
  @Hide
  static final class zza
    implements StreetViewLifecycleDelegate
  {
    private final Fragment zza;
    private final IStreetViewPanoramaFragmentDelegate zzb;
    
    public zza(Fragment paramFragment, IStreetViewPanoramaFragmentDelegate paramIStreetViewPanoramaFragmentDelegate)
    {
      zzb = ((IStreetViewPanoramaFragmentDelegate)zzbq.zza(paramIStreetViewPanoramaFragmentDelegate));
      zza = ((Fragment)zzbq.zza(paramFragment));
    }
    
    public final void getStreetViewPanoramaAsync(OnStreetViewPanoramaReadyCallback paramOnStreetViewPanoramaReadyCallback)
    {
      try
      {
        zzb.getStreetViewPanoramaAsync(new zzal(this, paramOnStreetViewPanoramaReadyCallback));
        return;
      }
      catch (RemoteException paramOnStreetViewPanoramaReadyCallback)
      {
        throw new RuntimeRemoteException(paramOnStreetViewPanoramaReadyCallback);
      }
    }
    
    public final void onCreate(Bundle paramBundle)
    {
      try
      {
        Bundle localBundle1 = new Bundle();
        zzby.zza(paramBundle, localBundle1);
        Bundle localBundle2 = zza.getArguments();
        if ((localBundle2 != null) && (localBundle2.containsKey("StreetViewPanoramaOptions"))) {
          zzby.zza(localBundle1, "StreetViewPanoramaOptions", localBundle2.getParcelable("StreetViewPanoramaOptions"));
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
      try
      {
        paramBundle1 = new Bundle();
        zzby.zza(paramBundle2, paramBundle1);
        zzb.onInflate(zzn.zza(paramActivity), null, paramBundle1);
        zzby.zza(paramBundle1, paramBundle2);
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
    
    public final void onStart() {}
    
    public final void onStop() {}
  }
  
  static final class zzb
    extends zza<SupportStreetViewPanoramaFragment.zza>
  {
    private final Fragment zza;
    private zzo<SupportStreetViewPanoramaFragment.zza> zzb;
    private Activity zzc;
    private final List<OnStreetViewPanoramaReadyCallback> zzd = new ArrayList();
    
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
        Object localObject = zzbz.zza(zzc).zzb(zzn.zza(zzc));
        zzb.zza(new SupportStreetViewPanoramaFragment.zza(zza, (IStreetViewPanoramaFragmentDelegate)localObject));
        localObject = zzd.iterator();
        while (((Iterator)localObject).hasNext())
        {
          OnStreetViewPanoramaReadyCallback localOnStreetViewPanoramaReadyCallback = (OnStreetViewPanoramaReadyCallback)((Iterator)localObject).next();
          ((SupportStreetViewPanoramaFragment.zza)zza()).getStreetViewPanoramaAsync(localOnStreetViewPanoramaReadyCallback);
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
    
    protected final void zza(zzo<SupportStreetViewPanoramaFragment.zza> paramZzo)
    {
      zzb = paramZzo;
      zzi();
    }
    
    public final void zza(OnStreetViewPanoramaReadyCallback paramOnStreetViewPanoramaReadyCallback)
    {
      if (zza() != null)
      {
        ((SupportStreetViewPanoramaFragment.zza)zza()).getStreetViewPanoramaAsync(paramOnStreetViewPanoramaReadyCallback);
        return;
      }
      zzd.add(paramOnStreetViewPanoramaReadyCallback);
    }
  }
}
