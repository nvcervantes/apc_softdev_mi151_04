package com.google.android.gms.maps;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.StrictMode;
import android.os.StrictMode.ThreadPolicy;
import android.os.StrictMode.ThreadPolicy.Builder;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.dynamic.zza;
import com.google.android.gms.dynamic.zzn;
import com.google.android.gms.dynamic.zzo;
import com.google.android.gms.maps.internal.IStreetViewPanoramaViewDelegate;
import com.google.android.gms.maps.internal.StreetViewLifecycleDelegate;
import com.google.android.gms.maps.internal.zzby;
import com.google.android.gms.maps.internal.zzbz;
import com.google.android.gms.maps.internal.zze;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class StreetViewPanoramaView
  extends FrameLayout
{
  private final zzb zza;
  
  public StreetViewPanoramaView(Context paramContext)
  {
    super(paramContext);
    zza = new zzb(this, paramContext, null);
  }
  
  public StreetViewPanoramaView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    zza = new zzb(this, paramContext, null);
  }
  
  public StreetViewPanoramaView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    zza = new zzb(this, paramContext, null);
  }
  
  public StreetViewPanoramaView(Context paramContext, StreetViewPanoramaOptions paramStreetViewPanoramaOptions)
  {
    super(paramContext);
    zza = new zzb(this, paramContext, paramStreetViewPanoramaOptions);
  }
  
  public void getStreetViewPanoramaAsync(OnStreetViewPanoramaReadyCallback paramOnStreetViewPanoramaReadyCallback)
  {
    zzbq.zzb("getStreetViewPanoramaAsync() must be called on the main thread");
    zza.zza(paramOnStreetViewPanoramaReadyCallback);
  }
  
  public final void onCreate(Bundle paramBundle)
  {
    StrictMode.ThreadPolicy localThreadPolicy = StrictMode.getThreadPolicy();
    StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder(localThreadPolicy).permitAll().build());
    try
    {
      zza.zza(paramBundle);
      if (zza.zza() == null) {
        zza.zzb(this);
      }
      return;
    }
    finally
    {
      StrictMode.setThreadPolicy(localThreadPolicy);
    }
  }
  
  public final void onDestroy()
  {
    zza.zzg();
  }
  
  public final void onLowMemory()
  {
    zza.zzh();
  }
  
  public final void onPause()
  {
    zza.zzd();
  }
  
  public final void onResume()
  {
    zza.zzc();
  }
  
  public final void onSaveInstanceState(Bundle paramBundle)
  {
    zza.zzb(paramBundle);
  }
  
  @Hide
  static final class zza
    implements StreetViewLifecycleDelegate
  {
    private final ViewGroup zza;
    private final IStreetViewPanoramaViewDelegate zzb;
    private View zzc;
    
    public zza(ViewGroup paramViewGroup, IStreetViewPanoramaViewDelegate paramIStreetViewPanoramaViewDelegate)
    {
      zzb = ((IStreetViewPanoramaViewDelegate)zzbq.zza(paramIStreetViewPanoramaViewDelegate));
      zza = ((ViewGroup)zzbq.zza(paramViewGroup));
    }
    
    public final void getStreetViewPanoramaAsync(OnStreetViewPanoramaReadyCallback paramOnStreetViewPanoramaReadyCallback)
    {
      try
      {
        zzb.getStreetViewPanoramaAsync(new zzaj(this, paramOnStreetViewPanoramaReadyCallback));
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
        Bundle localBundle = new Bundle();
        zzby.zza(paramBundle, localBundle);
        zzb.onCreate(localBundle);
        zzby.zza(localBundle, paramBundle);
        zzc = ((View)zzn.zza(zzb.getView()));
        zza.removeAllViews();
        zza.addView(zzc);
        return;
      }
      catch (RemoteException paramBundle)
      {
        throw new RuntimeRemoteException(paramBundle);
      }
    }
    
    public final View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
    {
      throw new UnsupportedOperationException("onCreateView not allowed on StreetViewPanoramaViewDelegate");
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
      throw new UnsupportedOperationException("onDestroyView not allowed on StreetViewPanoramaViewDelegate");
    }
    
    public final void onInflate(Activity paramActivity, Bundle paramBundle1, Bundle paramBundle2)
    {
      throw new UnsupportedOperationException("onInflate not allowed on StreetViewPanoramaViewDelegate");
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
    extends zza<StreetViewPanoramaView.zza>
  {
    private final ViewGroup zza;
    private final Context zzb;
    private zzo<StreetViewPanoramaView.zza> zzc;
    private final StreetViewPanoramaOptions zzd;
    private final List<OnStreetViewPanoramaReadyCallback> zze = new ArrayList();
    
    zzb(ViewGroup paramViewGroup, Context paramContext, StreetViewPanoramaOptions paramStreetViewPanoramaOptions)
    {
      zza = paramViewGroup;
      zzb = paramContext;
      zzd = paramStreetViewPanoramaOptions;
    }
    
    protected final void zza(zzo<StreetViewPanoramaView.zza> paramZzo)
    {
      zzc = paramZzo;
      if ((zzc != null) && (zza() == null)) {}
      try
      {
        MapsInitializer.initialize(zzb);
        paramZzo = zzbz.zza(zzb).zza(zzn.zza(zzb), zzd);
        zzc.zza(new StreetViewPanoramaView.zza(zza, paramZzo));
        paramZzo = zze.iterator();
        while (paramZzo.hasNext())
        {
          OnStreetViewPanoramaReadyCallback localOnStreetViewPanoramaReadyCallback = (OnStreetViewPanoramaReadyCallback)paramZzo.next();
          ((StreetViewPanoramaView.zza)zza()).getStreetViewPanoramaAsync(localOnStreetViewPanoramaReadyCallback);
        }
        zze.clear();
        return;
      }
      catch (RemoteException paramZzo)
      {
        throw new RuntimeRemoteException(paramZzo);
        return;
      }
      catch (GooglePlayServicesNotAvailableException paramZzo) {}
    }
    
    public final void zza(OnStreetViewPanoramaReadyCallback paramOnStreetViewPanoramaReadyCallback)
    {
      if (zza() != null)
      {
        ((StreetViewPanoramaView.zza)zza()).getStreetViewPanoramaAsync(paramOnStreetViewPanoramaReadyCallback);
        return;
      }
      zze.add(paramOnStreetViewPanoramaReadyCallback);
    }
  }
}
