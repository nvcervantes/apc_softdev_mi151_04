package com.google.maps.android.data.geojson;

import android.content.Context;
import android.content.res.Resources;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.maps.android.data.Layer;
import com.google.maps.android.data.Layer.OnFeatureClickListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

public class GeoJsonLayer
  extends Layer
{
  private LatLngBounds mBoundingBox;
  
  public GeoJsonLayer(GoogleMap paramGoogleMap, int paramInt, Context paramContext)
    throws IOException, JSONException
  {
    this(paramGoogleMap, createJsonFileObject(paramContext.getResources().openRawResource(paramInt)));
  }
  
  public GeoJsonLayer(GoogleMap paramGoogleMap, JSONObject paramJSONObject)
  {
    if (paramJSONObject == null) {
      throw new IllegalArgumentException("GeoJSON file cannot be null");
    }
    mBoundingBox = null;
    Object localObject = new GeoJsonParser(paramJSONObject);
    mBoundingBox = ((GeoJsonParser)localObject).getBoundingBox();
    paramJSONObject = new HashMap();
    localObject = ((GeoJsonParser)localObject).getFeatures().iterator();
    while (((Iterator)localObject).hasNext()) {
      paramJSONObject.put((GeoJsonFeature)((Iterator)localObject).next(), null);
    }
    storeRenderer(new GeoJsonRenderer(paramGoogleMap, paramJSONObject));
  }
  
  private static JSONObject createJsonFileObject(InputStream paramInputStream)
    throws IOException, JSONException
  {
    StringBuilder localStringBuilder = new StringBuilder();
    paramInputStream = new BufferedReader(new InputStreamReader(paramInputStream));
    for (;;)
    {
      String str = paramInputStream.readLine();
      if (str == null) {
        break;
      }
      localStringBuilder.append(str);
    }
    paramInputStream.close();
    return new JSONObject(localStringBuilder.toString());
  }
  
  public void addFeature(GeoJsonFeature paramGeoJsonFeature)
  {
    if (paramGeoJsonFeature == null) {
      throw new IllegalArgumentException("Feature cannot be null");
    }
    super.addFeature(paramGeoJsonFeature);
  }
  
  public void addLayerToMap()
  {
    super.addGeoJsonToMap();
  }
  
  public LatLngBounds getBoundingBox()
  {
    return mBoundingBox;
  }
  
  public Iterable<GeoJsonFeature> getFeatures()
  {
    return super.getFeatures();
  }
  
  public void removeFeature(GeoJsonFeature paramGeoJsonFeature)
  {
    if (paramGeoJsonFeature == null) {
      throw new IllegalArgumentException("Feature cannot be null");
    }
    super.removeFeature(paramGeoJsonFeature);
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder("Collection{");
    localStringBuilder.append("\n Bounding box=");
    localStringBuilder.append(mBoundingBox);
    localStringBuilder.append("\n}\n");
    return localStringBuilder.toString();
  }
  
  public static abstract interface GeoJsonOnFeatureClickListener
    extends Layer.OnFeatureClickListener
  {}
}
