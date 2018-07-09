package com.google.maps.android.data;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.GoogleMap.OnPolygonClickListener;
import com.google.android.gms.maps.GoogleMap.OnPolylineClickListener;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.Polyline;
import com.google.maps.android.data.geojson.GeoJsonLineStringStyle;
import com.google.maps.android.data.geojson.GeoJsonPointStyle;
import com.google.maps.android.data.geojson.GeoJsonPolygonStyle;
import com.google.maps.android.data.geojson.GeoJsonRenderer;
import com.google.maps.android.data.kml.KmlContainer;
import com.google.maps.android.data.kml.KmlGroundOverlay;
import com.google.maps.android.data.kml.KmlRenderer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import org.xmlpull.v1.XmlPullParserException;

public abstract class Layer
{
  private Renderer mRenderer;
  
  public Layer() {}
  
  private ArrayList<?> multiObjectHandler(Object paramObject)
  {
    Iterator localIterator = mRenderer.getValues().iterator();
    while (localIterator.hasNext())
    {
      Object localObject = localIterator.next();
      if (localObject.getClass().getSimpleName().equals("ArrayList"))
      {
        localObject = (ArrayList)localObject;
        if (((ArrayList)localObject).contains(paramObject)) {
          return localObject;
        }
      }
    }
    return null;
  }
  
  protected void addFeature(Feature paramFeature)
  {
    mRenderer.addFeature(paramFeature);
  }
  
  protected void addGeoJsonToMap()
  {
    if ((mRenderer instanceof GeoJsonRenderer))
    {
      ((GeoJsonRenderer)mRenderer).addLayerToMap();
      return;
    }
    throw new UnsupportedOperationException("Stored renderer is not a GeoJsonRenderer");
  }
  
  protected void addKMLToMap()
    throws IOException, XmlPullParserException
  {
    if ((mRenderer instanceof KmlRenderer))
    {
      ((KmlRenderer)mRenderer).addLayerToMap();
      return;
    }
    throw new UnsupportedOperationException("Stored renderer is not a KmlRenderer");
  }
  
  public Feature getContainerFeature(Object paramObject)
  {
    return mRenderer.getContainerFeature(paramObject);
  }
  
  protected Iterable<KmlContainer> getContainers()
  {
    if ((mRenderer instanceof KmlRenderer)) {
      return ((KmlRenderer)mRenderer).getNestedContainers();
    }
    return null;
  }
  
  public GeoJsonLineStringStyle getDefaultLineStringStyle()
  {
    return mRenderer.getDefaultLineStringStyle();
  }
  
  public GeoJsonPointStyle getDefaultPointStyle()
  {
    return mRenderer.getDefaultPointStyle();
  }
  
  public GeoJsonPolygonStyle getDefaultPolygonStyle()
  {
    return mRenderer.getDefaultPolygonStyle();
  }
  
  public Feature getFeature(Object paramObject)
  {
    return mRenderer.getFeature(paramObject);
  }
  
  public Iterable<? extends Feature> getFeatures()
  {
    return mRenderer.getFeatures();
  }
  
  protected Iterable<KmlGroundOverlay> getGroundOverlays()
  {
    if ((mRenderer instanceof KmlRenderer)) {
      return ((KmlRenderer)mRenderer).getGroundOverlays();
    }
    return null;
  }
  
  public GoogleMap getMap()
  {
    return mRenderer.getMap();
  }
  
  protected boolean hasContainers()
  {
    if ((mRenderer instanceof KmlRenderer)) {
      return ((KmlRenderer)mRenderer).hasNestedContainers();
    }
    return false;
  }
  
  protected boolean hasFeatures()
  {
    return mRenderer.hasFeatures();
  }
  
  public boolean isLayerOnMap()
  {
    return mRenderer.isLayerOnMap();
  }
  
  protected void removeFeature(Feature paramFeature)
  {
    mRenderer.removeFeature(paramFeature);
  }
  
  public void removeLayerFromMap()
  {
    if ((mRenderer instanceof GeoJsonRenderer))
    {
      ((GeoJsonRenderer)mRenderer).removeLayerFromMap();
      return;
    }
    if ((mRenderer instanceof KmlRenderer)) {
      ((KmlRenderer)mRenderer).removeLayerFromMap();
    }
  }
  
  public void setMap(GoogleMap paramGoogleMap)
  {
    mRenderer.setMap(paramGoogleMap);
  }
  
  public void setOnFeatureClickListener(final OnFeatureClickListener paramOnFeatureClickListener)
  {
    GoogleMap localGoogleMap = getMap();
    localGoogleMap.setOnPolygonClickListener(new GoogleMap.OnPolygonClickListener()
    {
      public void onPolygonClick(Polygon paramAnonymousPolygon)
      {
        if (getFeature(paramAnonymousPolygon) != null)
        {
          paramOnFeatureClickListener.onFeatureClick(getFeature(paramAnonymousPolygon));
          return;
        }
        if (getContainerFeature(paramAnonymousPolygon) != null)
        {
          paramOnFeatureClickListener.onFeatureClick(getContainerFeature(paramAnonymousPolygon));
          return;
        }
        paramOnFeatureClickListener.onFeatureClick(getFeature(Layer.this.multiObjectHandler(paramAnonymousPolygon)));
      }
    });
    localGoogleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener()
    {
      public boolean onMarkerClick(Marker paramAnonymousMarker)
      {
        if (getFeature(paramAnonymousMarker) != null) {
          paramOnFeatureClickListener.onFeatureClick(getFeature(paramAnonymousMarker));
        } else if (getContainerFeature(paramAnonymousMarker) != null) {
          paramOnFeatureClickListener.onFeatureClick(getContainerFeature(paramAnonymousMarker));
        } else {
          paramOnFeatureClickListener.onFeatureClick(getFeature(Layer.this.multiObjectHandler(paramAnonymousMarker)));
        }
        return false;
      }
    });
    localGoogleMap.setOnPolylineClickListener(new GoogleMap.OnPolylineClickListener()
    {
      public void onPolylineClick(Polyline paramAnonymousPolyline)
      {
        if (getFeature(paramAnonymousPolyline) != null)
        {
          paramOnFeatureClickListener.onFeatureClick(getFeature(paramAnonymousPolyline));
          return;
        }
        if (getContainerFeature(paramAnonymousPolyline) != null)
        {
          paramOnFeatureClickListener.onFeatureClick(getContainerFeature(paramAnonymousPolyline));
          return;
        }
        paramOnFeatureClickListener.onFeatureClick(getFeature(Layer.this.multiObjectHandler(paramAnonymousPolyline)));
      }
    });
  }
  
  protected void storeRenderer(Renderer paramRenderer)
  {
    mRenderer = paramRenderer;
  }
  
  public static abstract interface OnFeatureClickListener
  {
    public abstract void onFeatureClick(Feature paramFeature);
  }
}
