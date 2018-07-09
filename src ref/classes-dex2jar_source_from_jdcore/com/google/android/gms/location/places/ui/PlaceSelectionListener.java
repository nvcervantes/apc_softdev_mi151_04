package com.google.android.gms.location.places.ui;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;

public abstract interface PlaceSelectionListener
{
  public abstract void onError(Status paramStatus);
  
  public abstract void onPlaceSelected(Place paramPlace);
}
