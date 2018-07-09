package com.google.maps.android;

import android.view.View;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerDragListener;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class MarkerManager
  implements GoogleMap.OnInfoWindowClickListener, GoogleMap.OnMarkerClickListener, GoogleMap.OnMarkerDragListener, GoogleMap.InfoWindowAdapter
{
  private final Map<Marker, Collection> mAllMarkers = new HashMap();
  private final GoogleMap mMap;
  private final Map<String, Collection> mNamedCollections = new HashMap();
  
  public MarkerManager(GoogleMap paramGoogleMap)
  {
    mMap = paramGoogleMap;
  }
  
  public Collection getCollection(String paramString)
  {
    return (Collection)mNamedCollections.get(paramString);
  }
  
  public View getInfoContents(Marker paramMarker)
  {
    Collection localCollection = (Collection)mAllMarkers.get(paramMarker);
    if ((localCollection != null) && (mInfoWindowAdapter != null)) {
      return mInfoWindowAdapter.getInfoContents(paramMarker);
    }
    return null;
  }
  
  public View getInfoWindow(Marker paramMarker)
  {
    Collection localCollection = (Collection)mAllMarkers.get(paramMarker);
    if ((localCollection != null) && (mInfoWindowAdapter != null)) {
      return mInfoWindowAdapter.getInfoWindow(paramMarker);
    }
    return null;
  }
  
  public Collection newCollection()
  {
    return new Collection();
  }
  
  public Collection newCollection(String paramString)
  {
    if (mNamedCollections.get(paramString) != null)
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("collection id is not unique: ");
      ((StringBuilder)localObject).append(paramString);
      throw new IllegalArgumentException(((StringBuilder)localObject).toString());
    }
    Object localObject = new Collection();
    mNamedCollections.put(paramString, localObject);
    return localObject;
  }
  
  public void onInfoWindowClick(Marker paramMarker)
  {
    Collection localCollection = (Collection)mAllMarkers.get(paramMarker);
    if ((localCollection != null) && (mInfoWindowClickListener != null)) {
      mInfoWindowClickListener.onInfoWindowClick(paramMarker);
    }
  }
  
  public boolean onMarkerClick(Marker paramMarker)
  {
    Collection localCollection = (Collection)mAllMarkers.get(paramMarker);
    if ((localCollection != null) && (mMarkerClickListener != null)) {
      return mMarkerClickListener.onMarkerClick(paramMarker);
    }
    return false;
  }
  
  public void onMarkerDrag(Marker paramMarker)
  {
    Collection localCollection = (Collection)mAllMarkers.get(paramMarker);
    if ((localCollection != null) && (mMarkerDragListener != null)) {
      mMarkerDragListener.onMarkerDrag(paramMarker);
    }
  }
  
  public void onMarkerDragEnd(Marker paramMarker)
  {
    Collection localCollection = (Collection)mAllMarkers.get(paramMarker);
    if ((localCollection != null) && (mMarkerDragListener != null)) {
      mMarkerDragListener.onMarkerDragEnd(paramMarker);
    }
  }
  
  public void onMarkerDragStart(Marker paramMarker)
  {
    Collection localCollection = (Collection)mAllMarkers.get(paramMarker);
    if ((localCollection != null) && (mMarkerDragListener != null)) {
      mMarkerDragListener.onMarkerDragStart(paramMarker);
    }
  }
  
  public boolean remove(Marker paramMarker)
  {
    Collection localCollection = (Collection)mAllMarkers.get(paramMarker);
    return (localCollection != null) && (localCollection.remove(paramMarker));
  }
  
  public class Collection
  {
    private GoogleMap.InfoWindowAdapter mInfoWindowAdapter;
    private GoogleMap.OnInfoWindowClickListener mInfoWindowClickListener;
    private GoogleMap.OnMarkerClickListener mMarkerClickListener;
    private GoogleMap.OnMarkerDragListener mMarkerDragListener;
    private final Set<Marker> mMarkers = new HashSet();
    
    public Collection() {}
    
    public Marker addMarker(MarkerOptions paramMarkerOptions)
    {
      paramMarkerOptions = mMap.addMarker(paramMarkerOptions);
      mMarkers.add(paramMarkerOptions);
      mAllMarkers.put(paramMarkerOptions, this);
      return paramMarkerOptions;
    }
    
    public void clear()
    {
      Iterator localIterator = mMarkers.iterator();
      while (localIterator.hasNext())
      {
        Marker localMarker = (Marker)localIterator.next();
        localMarker.remove();
        mAllMarkers.remove(localMarker);
      }
      mMarkers.clear();
    }
    
    public Collection<Marker> getMarkers()
    {
      return Collections.unmodifiableCollection(mMarkers);
    }
    
    public boolean remove(Marker paramMarker)
    {
      if (mMarkers.remove(paramMarker))
      {
        mAllMarkers.remove(paramMarker);
        paramMarker.remove();
        return true;
      }
      return false;
    }
    
    public void setOnInfoWindowAdapter(GoogleMap.InfoWindowAdapter paramInfoWindowAdapter)
    {
      mInfoWindowAdapter = paramInfoWindowAdapter;
    }
    
    public void setOnInfoWindowClickListener(GoogleMap.OnInfoWindowClickListener paramOnInfoWindowClickListener)
    {
      mInfoWindowClickListener = paramOnInfoWindowClickListener;
    }
    
    public void setOnMarkerClickListener(GoogleMap.OnMarkerClickListener paramOnMarkerClickListener)
    {
      mMarkerClickListener = paramOnMarkerClickListener;
    }
    
    public void setOnMarkerDragListener(GoogleMap.OnMarkerDragListener paramOnMarkerDragListener)
    {
      mMarkerDragListener = paramOnMarkerDragListener;
    }
  }
}
