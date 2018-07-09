package com.google.maps.android.data.kml;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v4.util.LruCache;
import android.util.Log;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.GroundOverlay;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.maps.android.data.Feature;
import com.google.maps.android.data.Geometry;
import com.google.maps.android.data.Renderer;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class KmlRenderer
  extends Renderer
{
  private static final String LOG_TAG = "KmlRenderer";
  private ArrayList<KmlContainer> mContainers;
  private boolean mGroundOverlayImagesDownloaded = false;
  private final ArrayList<String> mGroundOverlayUrls = new ArrayList();
  private HashMap<KmlGroundOverlay, GroundOverlay> mGroundOverlays;
  private boolean mMarkerIconsDownloaded = false;
  
  KmlRenderer(GoogleMap paramGoogleMap, Context paramContext)
  {
    super(paramGoogleMap, paramContext);
  }
  
  private void addContainerGroupIconsToMarkers(String paramString, Iterable<KmlContainer> paramIterable)
  {
    paramIterable = paramIterable.iterator();
    while (paramIterable.hasNext())
    {
      KmlContainer localKmlContainer = (KmlContainer)paramIterable.next();
      addIconToMarkers(paramString, localKmlContainer.getPlacemarksHashMap());
      if (localKmlContainer.hasContainers()) {
        addContainerGroupIconsToMarkers(paramString, localKmlContainer.getContainers());
      }
    }
  }
  
  private void addContainerGroupToMap(Iterable<KmlContainer> paramIterable, boolean paramBoolean)
  {
    paramIterable = paramIterable.iterator();
    while (paramIterable.hasNext())
    {
      KmlContainer localKmlContainer = (KmlContainer)paramIterable.next();
      boolean bool = getContainerVisibility(localKmlContainer, paramBoolean);
      if (localKmlContainer.getStyles() != null) {
        putStyles(localKmlContainer.getStyles());
      }
      if (localKmlContainer.getStyleMap() != null) {
        super.assignStyleMap(localKmlContainer.getStyleMap(), getStylesRenderer());
      }
      addContainerObjectToMap(localKmlContainer, bool);
      if (localKmlContainer.hasContainers()) {
        addContainerGroupToMap(localKmlContainer.getContainers(), bool);
      }
    }
  }
  
  private void addContainerObjectToMap(KmlContainer paramKmlContainer, boolean paramBoolean)
  {
    Iterator localIterator = paramKmlContainer.getPlacemarks().iterator();
    while (localIterator.hasNext())
    {
      Feature localFeature = (Feature)localIterator.next();
      boolean bool = getPlacemarkVisibility(localFeature);
      if ((paramBoolean) && (bool)) {
        bool = true;
      } else {
        bool = false;
      }
      if (localFeature.getGeometry() != null)
      {
        Object localObject2 = localFeature.getId();
        Object localObject1 = localFeature.getGeometry();
        KmlStyle localKmlStyle = getPlacemarkStyle((String)localObject2);
        localObject2 = (KmlPlacemark)localFeature;
        localObject1 = addKmlPlacemarkToMap((KmlPlacemark)localObject2, (Geometry)localObject1, localKmlStyle, ((KmlPlacemark)localObject2).getInlineStyle(), bool);
        paramKmlContainer.setPlacemark((KmlPlacemark)localObject2, localObject1);
        putContainerFeature(localObject1, localFeature);
      }
    }
  }
  
  private void addGroundOverlayInContainerGroups(String paramString, Iterable<KmlContainer> paramIterable, boolean paramBoolean)
  {
    paramIterable = paramIterable.iterator();
    while (paramIterable.hasNext())
    {
      KmlContainer localKmlContainer = (KmlContainer)paramIterable.next();
      boolean bool = getContainerVisibility(localKmlContainer, paramBoolean);
      addGroundOverlayToMap(paramString, localKmlContainer.getGroundOverlayHashMap(), bool);
      if (localKmlContainer.hasContainers()) {
        addGroundOverlayInContainerGroups(paramString, localKmlContainer.getContainers(), bool);
      }
    }
  }
  
  private void addGroundOverlayToMap(String paramString, HashMap<KmlGroundOverlay, GroundOverlay> paramHashMap, boolean paramBoolean)
  {
    BitmapDescriptor localBitmapDescriptor = BitmapDescriptorFactory.fromBitmap((Bitmap)getImagesCache().get(paramString));
    Iterator localIterator = paramHashMap.keySet().iterator();
    while (localIterator.hasNext())
    {
      KmlGroundOverlay localKmlGroundOverlay = (KmlGroundOverlay)localIterator.next();
      if (localKmlGroundOverlay.getImageUrl().equals(paramString))
      {
        GroundOverlay localGroundOverlay = attachGroundOverlay(localKmlGroundOverlay.getGroundOverlayOptions().image(localBitmapDescriptor));
        if (!paramBoolean) {
          localGroundOverlay.setVisible(false);
        }
        paramHashMap.put(localKmlGroundOverlay, localGroundOverlay);
      }
    }
  }
  
  private void addGroundOverlays(HashMap<KmlGroundOverlay, GroundOverlay> paramHashMap)
  {
    paramHashMap = paramHashMap.keySet().iterator();
    while (paramHashMap.hasNext())
    {
      KmlGroundOverlay localKmlGroundOverlay = (KmlGroundOverlay)paramHashMap.next();
      String str = localKmlGroundOverlay.getImageUrl();
      if ((str != null) && (localKmlGroundOverlay.getLatLngBox() != null)) {
        if (getImagesCache().get(str) != null) {
          addGroundOverlayToMap(str, mGroundOverlays, true);
        } else if (!mGroundOverlayUrls.contains(str)) {
          mGroundOverlayUrls.add(str);
        }
      }
    }
  }
  
  private void addGroundOverlays(HashMap<KmlGroundOverlay, GroundOverlay> paramHashMap, Iterable<KmlContainer> paramIterable)
  {
    addGroundOverlays(paramHashMap);
    paramHashMap = paramIterable.iterator();
    while (paramHashMap.hasNext())
    {
      paramIterable = (KmlContainer)paramHashMap.next();
      addGroundOverlays(paramIterable.getGroundOverlayHashMap(), paramIterable.getContainers());
    }
  }
  
  private void addIconToMarkers(String paramString, HashMap<KmlPlacemark, Object> paramHashMap)
  {
    Iterator localIterator = paramHashMap.keySet().iterator();
    while (localIterator.hasNext())
    {
      Feature localFeature = (Feature)localIterator.next();
      KmlStyle localKmlStyle1 = (KmlStyle)getStylesRenderer().get(localFeature.getId());
      KmlPlacemark localKmlPlacemark = (KmlPlacemark)localFeature;
      KmlStyle localKmlStyle2 = localKmlPlacemark.getInlineStyle();
      if ("Point".equals(localFeature.getGeometry().getGeometryType()))
      {
        int k = 0;
        int i;
        if ((localKmlStyle2 != null) && (paramString.equals(localKmlStyle2.getIconUrl()))) {
          i = 1;
        } else {
          i = 0;
        }
        int j = k;
        if (localKmlStyle1 != null)
        {
          j = k;
          if (paramString.equals(localKmlStyle1.getIconUrl())) {
            j = 1;
          }
        }
        if (i != 0) {
          scaleBitmap(localKmlStyle2, paramHashMap, localKmlPlacemark);
        } else if (j != 0) {
          scaleBitmap(localKmlStyle1, paramHashMap, localKmlPlacemark);
        }
      }
    }
  }
  
  private void addPlacemarksToMap(HashMap<? extends Feature, Object> paramHashMap)
  {
    paramHashMap = paramHashMap.keySet().iterator();
    while (paramHashMap.hasNext()) {
      addFeature((Feature)paramHashMap.next());
    }
  }
  
  private void downloadGroundOverlays()
  {
    mGroundOverlayImagesDownloaded = true;
    Iterator localIterator = mGroundOverlayUrls.iterator();
    while (localIterator.hasNext())
    {
      new GroundOverlayImageDownload((String)localIterator.next()).execute(new String[0]);
      localIterator.remove();
    }
  }
  
  private void downloadMarkerIcons()
  {
    mMarkerIconsDownloaded = true;
    Iterator localIterator = getMarkerIconUrls().iterator();
    while (localIterator.hasNext())
    {
      new MarkerIconImageDownload((String)localIterator.next()).execute(new String[0]);
      localIterator.remove();
    }
  }
  
  static boolean getContainerVisibility(KmlContainer paramKmlContainer, boolean paramBoolean)
  {
    boolean bool1 = paramKmlContainer.hasProperty("visibility");
    boolean bool2 = false;
    int i;
    if ((bool1) && (Integer.parseInt(paramKmlContainer.getProperty("visibility")) == 0)) {
      i = 0;
    } else {
      i = 1;
    }
    bool1 = bool2;
    if (paramBoolean)
    {
      bool1 = bool2;
      if (i != 0) {
        bool1 = true;
      }
    }
    return bool1;
  }
  
  private void removeContainers(Iterable<KmlContainer> paramIterable)
  {
    paramIterable = paramIterable.iterator();
    while (paramIterable.hasNext())
    {
      KmlContainer localKmlContainer = (KmlContainer)paramIterable.next();
      removePlacemarks(localKmlContainer.getPlacemarksHashMap());
      removeGroundOverlays(localKmlContainer.getGroundOverlayHashMap());
      removeContainers(localKmlContainer.getContainers());
    }
  }
  
  private void removeGroundOverlays(HashMap<KmlGroundOverlay, GroundOverlay> paramHashMap)
  {
    paramHashMap = paramHashMap.values().iterator();
    while (paramHashMap.hasNext()) {
      ((GroundOverlay)paramHashMap.next()).remove();
    }
  }
  
  private void removePlacemarks(HashMap<? extends Feature, Object> paramHashMap)
  {
    removeFeatures(paramHashMap);
  }
  
  private void scaleBitmap(KmlStyle paramKmlStyle, HashMap<KmlPlacemark, Object> paramHashMap, KmlPlacemark paramKmlPlacemark)
  {
    double d = paramKmlStyle.getIconScale();
    paramKmlStyle = paramKmlStyle.getIconUrl();
    paramKmlStyle = scaleIcon((Bitmap)getImagesCache().get(paramKmlStyle), Double.valueOf(d));
    ((Marker)paramHashMap.get(paramKmlPlacemark)).setIcon(paramKmlStyle);
  }
  
  private static BitmapDescriptor scaleIcon(Bitmap paramBitmap, Double paramDouble)
  {
    return BitmapDescriptorFactory.fromBitmap(Bitmap.createScaledBitmap(paramBitmap, (int)(paramBitmap.getWidth() * paramDouble.doubleValue()), (int)(paramBitmap.getHeight() * paramDouble.doubleValue()), false));
  }
  
  public void addLayerToMap()
  {
    setLayerVisibility(true);
    mGroundOverlays = getGroundOverlayMap();
    mContainers = getContainerList();
    putStyles();
    assignStyleMap(getStyleMaps(), getStylesRenderer());
    addGroundOverlays(mGroundOverlays, mContainers);
    addContainerGroupToMap(mContainers, true);
    addPlacemarksToMap(getAllFeatures());
    if (!mGroundOverlayImagesDownloaded) {
      downloadGroundOverlays();
    }
    if (!mMarkerIconsDownloaded) {
      downloadMarkerIcons();
    }
  }
  
  public Iterable<KmlGroundOverlay> getGroundOverlays()
  {
    return mGroundOverlays.keySet();
  }
  
  Iterable<? extends Feature> getKmlPlacemarks()
  {
    return getFeatures();
  }
  
  public Iterable<KmlContainer> getNestedContainers()
  {
    return mContainers;
  }
  
  boolean hasKmlPlacemarks()
  {
    return hasFeatures();
  }
  
  public boolean hasNestedContainers()
  {
    return mContainers.size() > 0;
  }
  
  public void removeLayerFromMap()
  {
    removePlacemarks(getAllFeatures());
    removeGroundOverlays(mGroundOverlays);
    if (hasNestedContainers()) {
      removeContainers(getNestedContainers());
    }
    setLayerVisibility(false);
    clearStylesRenderer();
  }
  
  public void setMap(GoogleMap paramGoogleMap)
  {
    removeLayerFromMap();
    super.setMap(paramGoogleMap);
    addLayerToMap();
  }
  
  void storeKmlData(HashMap<String, KmlStyle> paramHashMap, HashMap<String, String> paramHashMap1, HashMap<KmlPlacemark, Object> paramHashMap2, ArrayList<KmlContainer> paramArrayList, HashMap<KmlGroundOverlay, GroundOverlay> paramHashMap3)
  {
    storeData(paramHashMap, paramHashMap1, paramHashMap2, paramArrayList, paramHashMap3);
  }
  
  private class GroundOverlayImageDownload
    extends AsyncTask<String, Void, Bitmap>
  {
    private final String mGroundOverlayUrl;
    
    public GroundOverlayImageDownload(String paramString)
    {
      mGroundOverlayUrl = paramString;
    }
    
    protected Bitmap doInBackground(String... paramVarArgs)
    {
      try
      {
        paramVarArgs = BitmapFactory.decodeStream((InputStream)new URL(mGroundOverlayUrl).getContent());
        return paramVarArgs;
      }
      catch (IOException paramVarArgs)
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("Image [");
        localStringBuilder.append(mGroundOverlayUrl);
        localStringBuilder.append("] download issue");
        Log.e("KmlRenderer", localStringBuilder.toString(), paramVarArgs);
        return null;
        return BitmapFactory.decodeFile(mGroundOverlayUrl);
      }
      catch (MalformedURLException paramVarArgs)
      {
        for (;;) {}
      }
    }
    
    protected void onPostExecute(Bitmap paramBitmap)
    {
      if (paramBitmap == null)
      {
        paramBitmap = new StringBuilder();
        paramBitmap.append("Image at this URL could not be found ");
        paramBitmap.append(mGroundOverlayUrl);
        Log.e("KmlRenderer", paramBitmap.toString());
        return;
      }
      putImagesCache(mGroundOverlayUrl, paramBitmap);
      if (isLayerOnMap())
      {
        KmlRenderer.this.addGroundOverlayToMap(mGroundOverlayUrl, mGroundOverlays, true);
        KmlRenderer.this.addGroundOverlayInContainerGroups(mGroundOverlayUrl, mContainers, true);
      }
    }
  }
  
  private class MarkerIconImageDownload
    extends AsyncTask<String, Void, Bitmap>
  {
    private final String mIconUrl;
    
    public MarkerIconImageDownload(String paramString)
    {
      mIconUrl = paramString;
    }
    
    protected Bitmap doInBackground(String... paramVarArgs)
    {
      try
      {
        paramVarArgs = BitmapFactory.decodeStream((InputStream)new URL(mIconUrl).getContent());
        return paramVarArgs;
      }
      catch (IOException paramVarArgs)
      {
        paramVarArgs.printStackTrace();
        return null;
        return BitmapFactory.decodeFile(mIconUrl);
      }
      catch (MalformedURLException paramVarArgs)
      {
        for (;;) {}
      }
    }
    
    protected void onPostExecute(Bitmap paramBitmap)
    {
      if (paramBitmap == null)
      {
        paramBitmap = new StringBuilder();
        paramBitmap.append("Image at this URL could not be found ");
        paramBitmap.append(mIconUrl);
        Log.e("KmlRenderer", paramBitmap.toString());
        return;
      }
      putImagesCache(mIconUrl, paramBitmap);
      if (isLayerOnMap())
      {
        KmlRenderer.this.addIconToMarkers(mIconUrl, KmlRenderer.access$000(KmlRenderer.this));
        KmlRenderer.this.addContainerGroupIconsToMarkers(mIconUrl, mContainers);
      }
    }
  }
}
