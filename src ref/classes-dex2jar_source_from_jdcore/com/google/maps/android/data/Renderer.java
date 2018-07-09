package com.google.maps.android.data;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.GroundOverlay;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.R.id;
import com.google.maps.android.R.layout;
import com.google.maps.android.data.geojson.BiMultiMap;
import com.google.maps.android.data.geojson.GeoJsonFeature;
import com.google.maps.android.data.geojson.GeoJsonGeometryCollection;
import com.google.maps.android.data.geojson.GeoJsonLineString;
import com.google.maps.android.data.geojson.GeoJsonLineStringStyle;
import com.google.maps.android.data.geojson.GeoJsonMultiLineString;
import com.google.maps.android.data.geojson.GeoJsonMultiPoint;
import com.google.maps.android.data.geojson.GeoJsonMultiPolygon;
import com.google.maps.android.data.geojson.GeoJsonPoint;
import com.google.maps.android.data.geojson.GeoJsonPointStyle;
import com.google.maps.android.data.geojson.GeoJsonPolygon;
import com.google.maps.android.data.geojson.GeoJsonPolygonStyle;
import com.google.maps.android.data.kml.KmlContainer;
import com.google.maps.android.data.kml.KmlGroundOverlay;
import com.google.maps.android.data.kml.KmlMultiGeometry;
import com.google.maps.android.data.kml.KmlPlacemark;
import com.google.maps.android.data.kml.KmlPoint;
import com.google.maps.android.data.kml.KmlStyle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Renderer
{
  private static final Object FEATURE_NOT_ON_MAP;
  private static final int LRU_CACHE_SIZE = 50;
  private BiMultiMap<Feature> mContainerFeatures;
  private ArrayList<KmlContainer> mContainers;
  private Context mContext;
  private final GeoJsonLineStringStyle mDefaultLineStringStyle;
  private final GeoJsonPointStyle mDefaultPointStyle;
  private final GeoJsonPolygonStyle mDefaultPolygonStyle;
  private final BiMultiMap<Feature> mFeatures = new BiMultiMap();
  private HashMap<KmlGroundOverlay, GroundOverlay> mGroundOverlays;
  private final LruCache<String, Bitmap> mImagesCache;
  private boolean mLayerOnMap;
  private GoogleMap mMap;
  private final ArrayList<String> mMarkerIconUrls;
  private HashMap<String, String> mStyleMaps;
  private HashMap<String, KmlStyle> mStyles;
  private HashMap<String, KmlStyle> mStylesRenderer;
  
  public Renderer(GoogleMap paramGoogleMap, Context paramContext)
  {
    mMap = paramGoogleMap;
    mContext = paramContext;
    mLayerOnMap = false;
    mImagesCache = new LruCache(50);
    mMarkerIconUrls = new ArrayList();
    mStylesRenderer = new HashMap();
    mDefaultPointStyle = null;
    mDefaultLineStringStyle = null;
    mDefaultPolygonStyle = null;
    mContainerFeatures = new BiMultiMap();
  }
  
  public Renderer(GoogleMap paramGoogleMap, HashMap<? extends Feature, Object> paramHashMap)
  {
    mMap = paramGoogleMap;
    mFeatures.putAll(paramHashMap);
    mLayerOnMap = false;
    mMarkerIconUrls = null;
    mDefaultPointStyle = new GeoJsonPointStyle();
    mDefaultLineStringStyle = new GeoJsonLineStringStyle();
    mDefaultPolygonStyle = new GeoJsonPolygonStyle();
    mImagesCache = null;
    mContainerFeatures = null;
  }
  
  private ArrayList<Object> addGeometryCollectionToMap(GeoJsonFeature paramGeoJsonFeature, List<Geometry> paramList)
  {
    ArrayList localArrayList = new ArrayList();
    paramList = paramList.iterator();
    while (paramList.hasNext()) {
      localArrayList.add(addGeoJsonFeatureToMap(paramGeoJsonFeature, (Geometry)paramList.next()));
    }
    return localArrayList;
  }
  
  private void addMarkerIcons(String paramString, MarkerOptions paramMarkerOptions)
  {
    if (mImagesCache.get(paramString) != null)
    {
      paramMarkerOptions.icon(BitmapDescriptorFactory.fromBitmap((Bitmap)mImagesCache.get(paramString)));
      return;
    }
    if (!mMarkerIconUrls.contains(paramString)) {
      mMarkerIconUrls.add(paramString);
    }
  }
  
  private ArrayList<Object> addMultiGeometryToMap(KmlPlacemark paramKmlPlacemark, KmlMultiGeometry paramKmlMultiGeometry, KmlStyle paramKmlStyle1, KmlStyle paramKmlStyle2, boolean paramBoolean)
  {
    ArrayList localArrayList = new ArrayList();
    paramKmlMultiGeometry = paramKmlMultiGeometry.getGeometryObject().iterator();
    while (paramKmlMultiGeometry.hasNext()) {
      localArrayList.add(addKmlPlacemarkToMap(paramKmlPlacemark, (Geometry)paramKmlMultiGeometry.next(), paramKmlStyle1, paramKmlStyle2, paramBoolean));
    }
    return localArrayList;
  }
  
  private ArrayList<Polyline> addMultiLineStringToMap(GeoJsonLineStringStyle paramGeoJsonLineStringStyle, GeoJsonMultiLineString paramGeoJsonMultiLineString)
  {
    ArrayList localArrayList = new ArrayList();
    paramGeoJsonMultiLineString = paramGeoJsonMultiLineString.getLineStrings().iterator();
    while (paramGeoJsonMultiLineString.hasNext())
    {
      GeoJsonLineString localGeoJsonLineString = (GeoJsonLineString)paramGeoJsonMultiLineString.next();
      localArrayList.add(addLineStringToMap(paramGeoJsonLineStringStyle.toPolylineOptions(), localGeoJsonLineString));
    }
    return localArrayList;
  }
  
  private ArrayList<Marker> addMultiPointToMap(GeoJsonPointStyle paramGeoJsonPointStyle, GeoJsonMultiPoint paramGeoJsonMultiPoint)
  {
    ArrayList localArrayList = new ArrayList();
    paramGeoJsonMultiPoint = paramGeoJsonMultiPoint.getPoints().iterator();
    while (paramGeoJsonMultiPoint.hasNext())
    {
      GeoJsonPoint localGeoJsonPoint = (GeoJsonPoint)paramGeoJsonMultiPoint.next();
      localArrayList.add(addPointToMap(paramGeoJsonPointStyle.toMarkerOptions(), localGeoJsonPoint));
    }
    return localArrayList;
  }
  
  private ArrayList<Polygon> addMultiPolygonToMap(GeoJsonPolygonStyle paramGeoJsonPolygonStyle, GeoJsonMultiPolygon paramGeoJsonMultiPolygon)
  {
    ArrayList localArrayList = new ArrayList();
    paramGeoJsonMultiPolygon = paramGeoJsonMultiPolygon.getPolygons().iterator();
    while (paramGeoJsonMultiPolygon.hasNext())
    {
      GeoJsonPolygon localGeoJsonPolygon = (GeoJsonPolygon)paramGeoJsonMultiPolygon.next();
      localArrayList.add(addPolygonToMap(paramGeoJsonPolygonStyle.toPolygonOptions(), localGeoJsonPolygon));
    }
    return localArrayList;
  }
  
  private void createInfoWindow()
  {
    mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter()
    {
      public View getInfoContents(Marker paramAnonymousMarker)
      {
        View localView = LayoutInflater.from(mContext).inflate(R.layout.amu_info_window, null);
        TextView localTextView = (TextView)localView.findViewById(R.id.window);
        if (paramAnonymousMarker.getSnippet() != null)
        {
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append(paramAnonymousMarker.getTitle());
          localStringBuilder.append("<br>");
          localStringBuilder.append(paramAnonymousMarker.getSnippet());
          localTextView.setText(Html.fromHtml(localStringBuilder.toString()));
          return localView;
        }
        localTextView.setText(Html.fromHtml(paramAnonymousMarker.getTitle()));
        return localView;
      }
      
      public View getInfoWindow(Marker paramAnonymousMarker)
      {
        return null;
      }
    });
  }
  
  protected static boolean getPlacemarkVisibility(Feature paramFeature)
  {
    return (!paramFeature.hasProperty("visibility")) || (Integer.parseInt(paramFeature.getProperty("visibility")) != 0);
  }
  
  protected static void removeFeatures(HashMap<Feature, Object> paramHashMap)
  {
    paramHashMap = paramHashMap.values().iterator();
    while (paramHashMap.hasNext())
    {
      Object localObject = paramHashMap.next();
      if ((localObject instanceof Marker)) {
        ((Marker)localObject).remove();
      } else if ((localObject instanceof Polyline)) {
        ((Polyline)localObject).remove();
      } else if ((localObject instanceof Polygon)) {
        ((Polygon)localObject).remove();
      }
    }
  }
  
  public static void removeFromMap(Object paramObject)
  {
    if ((paramObject instanceof Marker))
    {
      ((Marker)paramObject).remove();
      return;
    }
    if ((paramObject instanceof Polyline))
    {
      ((Polyline)paramObject).remove();
      return;
    }
    if ((paramObject instanceof Polygon))
    {
      ((Polygon)paramObject).remove();
      return;
    }
    if ((paramObject instanceof ArrayList))
    {
      paramObject = ((ArrayList)paramObject).iterator();
      while (paramObject.hasNext()) {
        removeFromMap(paramObject.next());
      }
    }
  }
  
  private void setFeatureDefaultStyles(GeoJsonFeature paramGeoJsonFeature)
  {
    if (paramGeoJsonFeature.getPointStyle() == null) {
      paramGeoJsonFeature.setPointStyle(mDefaultPointStyle);
    }
    if (paramGeoJsonFeature.getLineStringStyle() == null) {
      paramGeoJsonFeature.setLineStringStyle(mDefaultLineStringStyle);
    }
    if (paramGeoJsonFeature.getPolygonStyle() == null) {
      paramGeoJsonFeature.setPolygonStyle(mDefaultPolygonStyle);
    }
  }
  
  private void setInlineLineStringStyle(PolylineOptions paramPolylineOptions, KmlStyle paramKmlStyle)
  {
    PolylineOptions localPolylineOptions = paramKmlStyle.getPolylineOptions();
    if (paramKmlStyle.isStyleSet("outlineColor")) {
      paramPolylineOptions.color(localPolylineOptions.getColor());
    }
    if (paramKmlStyle.isStyleSet("width")) {
      paramPolylineOptions.width(localPolylineOptions.getWidth());
    }
    if (paramKmlStyle.isLineRandomColorMode()) {
      paramPolylineOptions.color(KmlStyle.computeRandomColor(localPolylineOptions.getColor()));
    }
  }
  
  private void setInlinePointStyle(MarkerOptions paramMarkerOptions, KmlStyle paramKmlStyle, String paramString)
  {
    MarkerOptions localMarkerOptions = paramKmlStyle.getMarkerOptions();
    if (paramKmlStyle.isStyleSet("heading")) {
      paramMarkerOptions.rotation(localMarkerOptions.getRotation());
    }
    if (paramKmlStyle.isStyleSet("hotSpot")) {
      paramMarkerOptions.anchor(localMarkerOptions.getAnchorU(), localMarkerOptions.getAnchorV());
    }
    if (paramKmlStyle.isStyleSet("markerColor")) {
      paramMarkerOptions.icon(localMarkerOptions.getIcon());
    }
    if (paramKmlStyle.isStyleSet("iconUrl"))
    {
      addMarkerIcons(paramKmlStyle.getIconUrl(), paramMarkerOptions);
      return;
    }
    if (paramString != null) {
      addMarkerIcons(paramString, paramMarkerOptions);
    }
  }
  
  private void setInlinePolygonStyle(PolygonOptions paramPolygonOptions, KmlStyle paramKmlStyle)
  {
    PolygonOptions localPolygonOptions = paramKmlStyle.getPolygonOptions();
    if ((paramKmlStyle.hasFill()) && (paramKmlStyle.isStyleSet("fillColor"))) {
      paramPolygonOptions.fillColor(localPolygonOptions.getFillColor());
    }
    if (paramKmlStyle.hasOutline())
    {
      if (paramKmlStyle.isStyleSet("outlineColor")) {
        paramPolygonOptions.strokeColor(localPolygonOptions.getStrokeColor());
      }
      if (paramKmlStyle.isStyleSet("width")) {
        paramPolygonOptions.strokeWidth(localPolygonOptions.getStrokeWidth());
      }
    }
    if (paramKmlStyle.isPolyRandomColorMode()) {
      paramPolygonOptions.fillColor(KmlStyle.computeRandomColor(localPolygonOptions.getFillColor()));
    }
  }
  
  private void setMarkerInfoWindow(KmlStyle paramKmlStyle, Marker paramMarker, KmlPlacemark paramKmlPlacemark)
  {
    boolean bool1 = paramKmlPlacemark.hasProperty("name");
    boolean bool2 = paramKmlPlacemark.hasProperty("description");
    boolean bool3 = paramKmlStyle.hasBalloonStyle();
    boolean bool4 = paramKmlStyle.getBalloonOptions().containsKey("text");
    if ((bool3) && (bool4))
    {
      paramMarker.setTitle((String)paramKmlStyle.getBalloonOptions().get("text"));
      createInfoWindow();
      return;
    }
    if ((bool3) && (bool1))
    {
      paramMarker.setTitle(paramKmlPlacemark.getProperty("name"));
      createInfoWindow();
      return;
    }
    if ((bool1) && (bool2))
    {
      paramMarker.setTitle(paramKmlPlacemark.getProperty("name"));
      paramMarker.setSnippet(paramKmlPlacemark.getProperty("description"));
      createInfoWindow();
      return;
    }
    if (bool2)
    {
      paramMarker.setTitle(paramKmlPlacemark.getProperty("description"));
      createInfoWindow();
      return;
    }
    if (bool1)
    {
      paramMarker.setTitle(paramKmlPlacemark.getProperty("name"));
      createInfoWindow();
    }
  }
  
  public void addFeature(Feature paramFeature)
  {
    Object localObject2 = FEATURE_NOT_ON_MAP;
    if ((paramFeature instanceof GeoJsonFeature)) {
      setFeatureDefaultStyles((GeoJsonFeature)paramFeature);
    }
    Object localObject1 = localObject2;
    if (mLayerOnMap)
    {
      if (mFeatures.containsKey(paramFeature)) {
        removeFromMap(mFeatures.get(paramFeature));
      }
      localObject1 = localObject2;
      if (paramFeature.hasGeometry()) {
        if ((paramFeature instanceof KmlPlacemark))
        {
          boolean bool = getPlacemarkVisibility(paramFeature);
          localObject2 = paramFeature.getId();
          localObject1 = paramFeature.getGeometry();
          localObject2 = getPlacemarkStyle((String)localObject2);
          KmlPlacemark localKmlPlacemark = (KmlPlacemark)paramFeature;
          localObject1 = addKmlPlacemarkToMap(localKmlPlacemark, (Geometry)localObject1, (KmlStyle)localObject2, localKmlPlacemark.getInlineStyle(), bool);
        }
        else
        {
          localObject1 = addGeoJsonFeatureToMap(paramFeature, paramFeature.getGeometry());
        }
      }
    }
    mFeatures.put(paramFeature, localObject1);
  }
  
  protected Object addGeoJsonFeatureToMap(Feature paramFeature, Geometry paramGeometry)
  {
    Object localObject1 = paramGeometry.getGeometryType();
    switch (((String)localObject1).hashCode())
    {
    default: 
      break;
    case 1950410960: 
      if (((String)localObject1).equals("GeometryCollection")) {
        i = 6;
      }
      break;
    case 1806700869: 
      if (((String)localObject1).equals("LineString")) {
        i = 1;
      }
      break;
    case 1267133722: 
      if (((String)localObject1).equals("Polygon")) {
        i = 2;
      }
      break;
    case 77292912: 
      if (((String)localObject1).equals("Point")) {
        i = 0;
      }
      break;
    case -627102946: 
      if (((String)localObject1).equals("MultiLineString")) {
        i = 4;
      }
      break;
    case -1065891849: 
      if (((String)localObject1).equals("MultiPoint")) {
        i = 3;
      }
      break;
    case -2116761119: 
      if (((String)localObject1).equals("MultiPolygon")) {
        i = 5;
      }
      break;
    }
    int i = -1;
    Object localObject2 = null;
    Object localObject3 = null;
    localObject1 = null;
    switch (i)
    {
    default: 
      return null;
    case 6: 
      return addGeometryCollectionToMap((GeoJsonFeature)paramFeature, ((GeoJsonGeometryCollection)paramGeometry).getGeometries());
    case 5: 
      return addMultiPolygonToMap(((GeoJsonFeature)paramFeature).getPolygonStyle(), (GeoJsonMultiPolygon)paramGeometry);
    case 4: 
      return addMultiLineStringToMap(((GeoJsonFeature)paramFeature).getLineStringStyle(), (GeoJsonMultiLineString)paramGeometry);
    case 3: 
      return addMultiPointToMap(((GeoJsonFeature)paramFeature).getPointStyle(), (GeoJsonMultiPoint)paramGeometry);
    case 2: 
      if ((paramFeature instanceof GeoJsonFeature)) {
        localObject1 = ((GeoJsonFeature)paramFeature).getPolygonOptions();
      } else if ((paramFeature instanceof KmlPlacemark)) {
        localObject1 = ((KmlPlacemark)paramFeature).getPolygonOptions();
      }
      return addPolygonToMap((PolygonOptions)localObject1, (DataPolygon)paramGeometry);
    case 1: 
      if ((paramFeature instanceof GeoJsonFeature))
      {
        localObject1 = ((GeoJsonFeature)paramFeature).getPolylineOptions();
      }
      else
      {
        localObject1 = localObject2;
        if ((paramFeature instanceof KmlPlacemark)) {
          localObject1 = ((KmlPlacemark)paramFeature).getPolylineOptions();
        }
      }
      return addLineStringToMap((PolylineOptions)localObject1, (GeoJsonLineString)paramGeometry);
    }
    if ((paramFeature instanceof GeoJsonFeature))
    {
      localObject1 = ((GeoJsonFeature)paramFeature).getMarkerOptions();
    }
    else
    {
      localObject1 = localObject3;
      if ((paramFeature instanceof KmlPlacemark)) {
        localObject1 = ((KmlPlacemark)paramFeature).getMarkerOptions();
      }
    }
    return addPointToMap((MarkerOptions)localObject1, (GeoJsonPoint)paramGeometry);
  }
  
  protected Object addKmlPlacemarkToMap(KmlPlacemark paramKmlPlacemark, Geometry paramGeometry, KmlStyle paramKmlStyle1, KmlStyle paramKmlStyle2, boolean paramBoolean)
  {
    Object localObject = paramGeometry.getGeometryType();
    boolean bool2 = paramKmlPlacemark.hasProperty("drawOrder");
    int i = 0;
    float f2 = 0.0F;
    boolean bool1 = bool2;
    float f1 = f2;
    if (bool2) {}
    try
    {
      f1 = Float.parseFloat(paramKmlPlacemark.getProperty("drawOrder"));
      bool1 = bool2;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      int j;
      for (;;) {}
    }
    bool1 = false;
    f1 = f2;
    j = ((String)localObject).hashCode();
    if (j != 77292912)
    {
      if (j != 89139371)
      {
        if (j != 1267133722)
        {
          if ((j == 1806700869) && (((String)localObject).equals("LineString")))
          {
            i = 1;
            break label172;
          }
        }
        else if (((String)localObject).equals("Polygon"))
        {
          i = 2;
          break label172;
        }
      }
      else if (((String)localObject).equals("MultiGeometry"))
      {
        i = 3;
        break label172;
      }
    }
    else {
      if (((String)localObject).equals("Point")) {
        break label172;
      }
    }
    i = -1;
    switch (i)
    {
    default: 
      return null;
    case 3: 
      return addMultiGeometryToMap(paramKmlPlacemark, (KmlMultiGeometry)paramGeometry, paramKmlStyle1, paramKmlStyle2, paramBoolean);
    case 2: 
      paramKmlPlacemark = paramKmlStyle1.getPolygonOptions();
      if (paramKmlStyle2 != null) {
        setInlinePolygonStyle(paramKmlPlacemark, paramKmlStyle2);
      } else if (paramKmlStyle1.isPolyRandomColorMode()) {
        paramKmlPlacemark.fillColor(KmlStyle.computeRandomColor(paramKmlPlacemark.getFillColor()));
      }
      paramKmlPlacemark = addPolygonToMap(paramKmlPlacemark, (DataPolygon)paramGeometry);
      paramKmlPlacemark.setVisible(paramBoolean);
      if (bool1) {
        paramKmlPlacemark.setZIndex(f1);
      }
      return paramKmlPlacemark;
    case 1: 
      label172:
      paramKmlPlacemark = paramKmlStyle1.getPolylineOptions();
      if (paramKmlStyle2 != null) {
        setInlineLineStringStyle(paramKmlPlacemark, paramKmlStyle2);
      } else if (paramKmlStyle1.isLineRandomColorMode()) {
        paramKmlPlacemark.color(KmlStyle.computeRandomColor(paramKmlPlacemark.getColor()));
      }
      paramKmlPlacemark = addLineStringToMap(paramKmlPlacemark, (LineString)paramGeometry);
      paramKmlPlacemark.setVisible(paramBoolean);
      if (bool1) {
        paramKmlPlacemark.setZIndex(f1);
      }
      return paramKmlPlacemark;
    }
    localObject = paramKmlStyle1.getMarkerOptions();
    if (paramKmlStyle2 != null) {
      setInlinePointStyle((MarkerOptions)localObject, paramKmlStyle2, paramKmlStyle1.getIconUrl());
    } else if (paramKmlStyle1.getIconUrl() != null) {
      addMarkerIcons(paramKmlStyle1.getIconUrl(), (MarkerOptions)localObject);
    }
    paramGeometry = addPointToMap((MarkerOptions)localObject, (KmlPoint)paramGeometry);
    paramGeometry.setVisible(paramBoolean);
    setMarkerInfoWindow(paramKmlStyle1, paramGeometry, paramKmlPlacemark);
    if (bool1) {
      paramGeometry.setZIndex(f1);
    }
    return paramGeometry;
  }
  
  protected Polyline addLineStringToMap(PolylineOptions paramPolylineOptions, LineString paramLineString)
  {
    paramPolylineOptions.addAll(paramLineString.getGeometryObject());
    paramPolylineOptions = mMap.addPolyline(paramPolylineOptions);
    paramPolylineOptions.setClickable(true);
    return paramPolylineOptions;
  }
  
  protected Marker addPointToMap(MarkerOptions paramMarkerOptions, Point paramPoint)
  {
    paramMarkerOptions.position(paramPoint.getGeometryObject());
    return mMap.addMarker(paramMarkerOptions);
  }
  
  protected Polygon addPolygonToMap(PolygonOptions paramPolygonOptions, DataPolygon paramDataPolygon)
  {
    paramPolygonOptions.addAll(paramDataPolygon.getOuterBoundaryCoordinates());
    paramDataPolygon = paramDataPolygon.getInnerBoundaryCoordinates().iterator();
    while (paramDataPolygon.hasNext()) {
      paramPolygonOptions.addHole((List)paramDataPolygon.next());
    }
    paramPolygonOptions = mMap.addPolygon(paramPolygonOptions);
    paramPolygonOptions.setClickable(true);
    return paramPolygonOptions;
  }
  
  public void assignStyleMap(HashMap<String, String> paramHashMap, HashMap<String, KmlStyle> paramHashMap1)
  {
    Iterator localIterator = paramHashMap.keySet().iterator();
    while (localIterator.hasNext())
    {
      String str1 = (String)localIterator.next();
      String str2 = (String)paramHashMap.get(str1);
      if (paramHashMap1.containsKey(str2)) {
        paramHashMap1.put(str1, paramHashMap1.get(str2));
      }
    }
  }
  
  public GroundOverlay attachGroundOverlay(GroundOverlayOptions paramGroundOverlayOptions)
  {
    return mMap.addGroundOverlay(paramGroundOverlayOptions);
  }
  
  public void clearStylesRenderer()
  {
    mStylesRenderer.clear();
  }
  
  protected HashMap<? extends Feature, Object> getAllFeatures()
  {
    return mFeatures;
  }
  
  public Feature getContainerFeature(Object paramObject)
  {
    if (mContainerFeatures != null) {
      return (Feature)mContainerFeatures.getKey(paramObject);
    }
    return null;
  }
  
  public ArrayList<KmlContainer> getContainerList()
  {
    return mContainers;
  }
  
  public GeoJsonLineStringStyle getDefaultLineStringStyle()
  {
    return mDefaultLineStringStyle;
  }
  
  public GeoJsonPointStyle getDefaultPointStyle()
  {
    return mDefaultPointStyle;
  }
  
  public GeoJsonPolygonStyle getDefaultPolygonStyle()
  {
    return mDefaultPolygonStyle;
  }
  
  public Feature getFeature(Object paramObject)
  {
    return (Feature)mFeatures.getKey(paramObject);
  }
  
  public Set<Feature> getFeatures()
  {
    return mFeatures.keySet();
  }
  
  public HashMap<KmlGroundOverlay, GroundOverlay> getGroundOverlayMap()
  {
    return mGroundOverlays;
  }
  
  public LruCache<String, Bitmap> getImagesCache()
  {
    return mImagesCache;
  }
  
  public GoogleMap getMap()
  {
    return mMap;
  }
  
  public ArrayList<String> getMarkerIconUrls()
  {
    return mMarkerIconUrls;
  }
  
  protected KmlStyle getPlacemarkStyle(String paramString)
  {
    KmlStyle localKmlStyle = (KmlStyle)mStylesRenderer.get(null);
    if (mStylesRenderer.get(paramString) != null) {
      localKmlStyle = (KmlStyle)mStylesRenderer.get(paramString);
    }
    return localKmlStyle;
  }
  
  public HashMap<String, String> getStyleMaps()
  {
    return mStyleMaps;
  }
  
  public HashMap<String, KmlStyle> getStylesRenderer()
  {
    return mStylesRenderer;
  }
  
  public Collection<Object> getValues()
  {
    return mFeatures.values();
  }
  
  public boolean hasFeatures()
  {
    return mFeatures.size() > 0;
  }
  
  public boolean isLayerOnMap()
  {
    return mLayerOnMap;
  }
  
  protected void putContainerFeature(Object paramObject, Feature paramFeature)
  {
    mContainerFeatures.put(paramFeature, paramObject);
  }
  
  public void putFeatures(Feature paramFeature, Object paramObject)
  {
    mFeatures.put(paramFeature, paramObject);
  }
  
  public void putImagesCache(String paramString, Bitmap paramBitmap)
  {
    mImagesCache.put(paramString, paramBitmap);
  }
  
  public void putStyles()
  {
    mStylesRenderer.putAll(mStyles);
  }
  
  public void putStyles(HashMap<String, KmlStyle> paramHashMap)
  {
    mStylesRenderer.putAll(paramHashMap);
  }
  
  protected void removeFeature(Feature paramFeature)
  {
    if (mFeatures.containsKey(paramFeature)) {
      removeFromMap(mFeatures.remove(paramFeature));
    }
  }
  
  protected void setLayerVisibility(boolean paramBoolean)
  {
    mLayerOnMap = paramBoolean;
  }
  
  public void setMap(GoogleMap paramGoogleMap)
  {
    mMap = paramGoogleMap;
  }
  
  protected void storeData(HashMap<String, KmlStyle> paramHashMap, HashMap<String, String> paramHashMap1, HashMap<KmlPlacemark, Object> paramHashMap2, ArrayList<KmlContainer> paramArrayList, HashMap<KmlGroundOverlay, GroundOverlay> paramHashMap3)
  {
    mStyles = paramHashMap;
    mStyleMaps = paramHashMap1;
    mFeatures.putAll(paramHashMap2);
    mContainers = paramArrayList;
    mGroundOverlays = paramHashMap3;
  }
}
