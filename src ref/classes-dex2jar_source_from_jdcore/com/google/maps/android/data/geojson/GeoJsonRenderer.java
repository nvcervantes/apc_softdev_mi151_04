package com.google.maps.android.data.geojson;

import com.google.android.gms.maps.GoogleMap;
import com.google.maps.android.data.Feature;
import com.google.maps.android.data.Renderer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

public class GeoJsonRenderer
  extends Renderer
  implements Observer
{
  private static final Object FEATURE_NOT_ON_MAP;
  
  public GeoJsonRenderer(GoogleMap paramGoogleMap, HashMap<GeoJsonFeature, Object> paramHashMap)
  {
    super(paramGoogleMap, paramHashMap);
  }
  
  private void redrawFeatureToMap(GeoJsonFeature paramGeoJsonFeature)
  {
    redrawFeatureToMap(paramGeoJsonFeature, getMap());
  }
  
  private void redrawFeatureToMap(GeoJsonFeature paramGeoJsonFeature, GoogleMap paramGoogleMap)
  {
    removeFromMap(getAllFeatures().get(paramGeoJsonFeature));
    putFeatures(paramGeoJsonFeature, FEATURE_NOT_ON_MAP);
    if ((paramGoogleMap != null) && (paramGeoJsonFeature.hasGeometry())) {
      putFeatures(paramGeoJsonFeature, addGeoJsonFeatureToMap(paramGeoJsonFeature, paramGeoJsonFeature.getGeometry()));
    }
  }
  
  public void addFeature(GeoJsonFeature paramGeoJsonFeature)
  {
    super.addFeature(paramGeoJsonFeature);
    if (isLayerOnMap()) {
      paramGeoJsonFeature.addObserver(this);
    }
  }
  
  public void addLayerToMap()
  {
    if (!isLayerOnMap())
    {
      setLayerVisibility(true);
      Iterator localIterator = super.getFeatures().iterator();
      while (localIterator.hasNext()) {
        addFeature((GeoJsonFeature)localIterator.next());
      }
    }
  }
  
  public void removeFeature(GeoJsonFeature paramGeoJsonFeature)
  {
    super.removeFeature(paramGeoJsonFeature);
    if (super.getFeatures().contains(paramGeoJsonFeature)) {
      paramGeoJsonFeature.deleteObserver(this);
    }
  }
  
  public void removeLayerFromMap()
  {
    if (isLayerOnMap())
    {
      Iterator localIterator = super.getFeatures().iterator();
      while (localIterator.hasNext())
      {
        Feature localFeature = (Feature)localIterator.next();
        removeFromMap(super.getAllFeatures().get(localFeature));
        localFeature.deleteObserver(this);
      }
      setLayerVisibility(false);
    }
  }
  
  public void setMap(GoogleMap paramGoogleMap)
  {
    super.setMap(paramGoogleMap);
    Iterator localIterator = super.getFeatures().iterator();
    while (localIterator.hasNext()) {
      redrawFeatureToMap((GeoJsonFeature)localIterator.next(), paramGoogleMap);
    }
  }
  
  public void update(Observable paramObservable, Object paramObject)
  {
    if ((paramObservable instanceof GeoJsonFeature))
    {
      paramObservable = (GeoJsonFeature)paramObservable;
      int i;
      if (getAllFeatures().get(paramObservable) != FEATURE_NOT_ON_MAP) {
        i = 1;
      } else {
        i = 0;
      }
      if ((i != 0) && (paramObservable.hasGeometry()))
      {
        redrawFeatureToMap(paramObservable);
        return;
      }
      if ((i != 0) && (!paramObservable.hasGeometry()))
      {
        removeFromMap(getAllFeatures().get(paramObservable));
        putFeatures(paramObservable, FEATURE_NOT_ON_MAP);
        return;
      }
      if ((i == 0) && (paramObservable.hasGeometry())) {
        addFeature(paramObservable);
      }
    }
  }
}
