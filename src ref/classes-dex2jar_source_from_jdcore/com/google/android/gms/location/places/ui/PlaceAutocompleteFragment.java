package com.google.android.gms.location.places.ui;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import com.google.android.gms.R.id;
import com.google.android.gms.R.layout;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.maps.model.LatLngBounds;

@TargetApi(12)
public class PlaceAutocompleteFragment
  extends Fragment
{
  private View zza;
  private View zzb;
  private EditText zzc;
  private boolean zzd;
  @Nullable
  private LatLngBounds zze;
  @Nullable
  private AutocompleteFilter zzf;
  @Nullable
  private PlaceSelectionListener zzg;
  
  public PlaceAutocompleteFragment() {}
  
  private final void zza()
  {
    boolean bool = zzc.getText().toString().isEmpty();
    View localView = zzb;
    int i;
    if ((bool ^ true)) {
      i = 0;
    } else {
      i = 8;
    }
    localView.setVisibility(i);
  }
  
  private final void zzb()
  {
    int i;
    try
    {
      Intent localIntent = new PlaceAutocomplete.IntentBuilder(2).setBoundsBias(zze).setFilter(zzf).zza(zzc.getText().toString()).zza(1).build(getActivity());
      zzd = true;
      startActivityForResult(localIntent, 30421);
      i = -1;
    }
    catch (GooglePlayServicesNotAvailableException localGooglePlayServicesNotAvailableException)
    {
      i = errorCode;
    }
    catch (GooglePlayServicesRepairableException localGooglePlayServicesRepairableException)
    {
      i = localGooglePlayServicesRepairableException.getConnectionStatusCode();
    }
    Log.e("Places", "Could not open autocomplete activity", localGooglePlayServicesRepairableException);
    if (i != -1) {
      GoogleApiAvailability.getInstance().showErrorDialogFragment(getActivity(), i, 30422);
    }
  }
  
  @Hide
  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    zzd = false;
    if (paramInt1 == 30421)
    {
      Object localObject;
      if (paramInt2 == -1)
      {
        localObject = PlaceAutocomplete.getPlace(getActivity(), paramIntent);
        if (zzg != null) {
          zzg.onPlaceSelected((Place)localObject);
        }
        setText(((Place)localObject).getName().toString());
      }
      else if (paramInt2 == 2)
      {
        localObject = PlaceAutocomplete.getStatus(getActivity(), paramIntent);
        if (zzg != null) {
          zzg.onError((Status)localObject);
        }
      }
    }
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
  }
  
  @Hide
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    paramLayoutInflater = paramLayoutInflater.inflate(R.layout.place_autocomplete_fragment, paramViewGroup, false);
    zza = paramLayoutInflater.findViewById(R.id.place_autocomplete_search_button);
    zzb = paramLayoutInflater.findViewById(R.id.place_autocomplete_clear_button);
    zzc = ((EditText)paramLayoutInflater.findViewById(R.id.place_autocomplete_search_input));
    paramViewGroup = new zzc(this);
    zza.setOnClickListener(paramViewGroup);
    zzc.setOnClickListener(paramViewGroup);
    zzb.setOnClickListener(new zzd(this));
    zza();
    return paramLayoutInflater;
  }
  
  @Hide
  public void onDestroyView()
  {
    zza = null;
    zzb = null;
    zzc = null;
    super.onDestroyView();
  }
  
  public void setBoundsBias(@Nullable LatLngBounds paramLatLngBounds)
  {
    zze = paramLatLngBounds;
  }
  
  public void setFilter(@Nullable AutocompleteFilter paramAutocompleteFilter)
  {
    zzf = paramAutocompleteFilter;
  }
  
  public void setHint(CharSequence paramCharSequence)
  {
    zzc.setHint(paramCharSequence);
    zza.setContentDescription(paramCharSequence);
  }
  
  public void setOnPlaceSelectedListener(PlaceSelectionListener paramPlaceSelectionListener)
  {
    zzg = paramPlaceSelectionListener;
  }
  
  public void setText(CharSequence paramCharSequence)
  {
    zzc.setText(paramCharSequence);
    zza();
  }
}
