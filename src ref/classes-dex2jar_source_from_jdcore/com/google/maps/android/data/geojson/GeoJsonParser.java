package com.google.maps.android.data.geojson;

import android.util.Log;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.maps.android.data.Geometry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class GeoJsonParser
{
  private static final String BOUNDING_BOX = "bbox";
  private static final String FEATURE = "Feature";
  private static final String FEATURE_COLLECTION = "FeatureCollection";
  private static final String FEATURE_COLLECTION_ARRAY = "features";
  private static final String FEATURE_GEOMETRY = "geometry";
  private static final String FEATURE_ID = "id";
  private static final String GEOMETRY_COLLECTION = "GeometryCollection";
  private static final String GEOMETRY_COLLECTION_ARRAY = "geometries";
  private static final String GEOMETRY_COORDINATES_ARRAY = "coordinates";
  private static final String LINESTRING = "LineString";
  private static final String LOG_TAG = "GeoJsonParser";
  private static final String MULTILINESTRING = "MultiLineString";
  private static final String MULTIPOINT = "MultiPoint";
  private static final String MULTIPOLYGON = "MultiPolygon";
  private static final String POINT = "Point";
  private static final String POLYGON = "Polygon";
  private static final String PROPERTIES = "properties";
  private LatLngBounds mBoundingBox;
  private final ArrayList<GeoJsonFeature> mGeoJsonFeatures;
  private final JSONObject mGeoJsonFile;
  
  GeoJsonParser(JSONObject paramJSONObject)
  {
    mGeoJsonFile = paramJSONObject;
    mGeoJsonFeatures = new ArrayList();
    mBoundingBox = null;
    parseGeoJson();
  }
  
  private static Geometry createGeometry(String paramString, JSONArray paramJSONArray)
    throws JSONException
  {
    if (paramString.equals("Point")) {
      return createPoint(paramJSONArray);
    }
    if (paramString.equals("MultiPoint")) {
      return createMultiPoint(paramJSONArray);
    }
    if (paramString.equals("LineString")) {
      return createLineString(paramJSONArray);
    }
    if (paramString.equals("MultiLineString")) {
      return createMultiLineString(paramJSONArray);
    }
    if (paramString.equals("Polygon")) {
      return createPolygon(paramJSONArray);
    }
    if (paramString.equals("MultiPolygon")) {
      return createMultiPolygon(paramJSONArray);
    }
    if (paramString.equals("GeometryCollection")) {
      return createGeometryCollection(paramJSONArray);
    }
    return null;
  }
  
  private static GeoJsonGeometryCollection createGeometryCollection(JSONArray paramJSONArray)
    throws JSONException
  {
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    while (i < paramJSONArray.length())
    {
      Geometry localGeometry = parseGeometry(paramJSONArray.getJSONObject(i));
      if (localGeometry != null) {
        localArrayList.add(localGeometry);
      }
      i += 1;
    }
    return new GeoJsonGeometryCollection(localArrayList);
  }
  
  private static GeoJsonLineString createLineString(JSONArray paramJSONArray)
    throws JSONException
  {
    return new GeoJsonLineString(parseCoordinatesArray(paramJSONArray));
  }
  
  private static GeoJsonMultiLineString createMultiLineString(JSONArray paramJSONArray)
    throws JSONException
  {
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    while (i < paramJSONArray.length())
    {
      localArrayList.add(createLineString(paramJSONArray.getJSONArray(i)));
      i += 1;
    }
    return new GeoJsonMultiLineString(localArrayList);
  }
  
  private static GeoJsonMultiPoint createMultiPoint(JSONArray paramJSONArray)
    throws JSONException
  {
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    while (i < paramJSONArray.length())
    {
      localArrayList.add(createPoint(paramJSONArray.getJSONArray(i)));
      i += 1;
    }
    return new GeoJsonMultiPoint(localArrayList);
  }
  
  private static GeoJsonMultiPolygon createMultiPolygon(JSONArray paramJSONArray)
    throws JSONException
  {
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    while (i < paramJSONArray.length())
    {
      localArrayList.add(createPolygon(paramJSONArray.getJSONArray(i)));
      i += 1;
    }
    return new GeoJsonMultiPolygon(localArrayList);
  }
  
  private static GeoJsonPoint createPoint(JSONArray paramJSONArray)
    throws JSONException
  {
    return new GeoJsonPoint(parseCoordinate(paramJSONArray));
  }
  
  private static GeoJsonPolygon createPolygon(JSONArray paramJSONArray)
    throws JSONException
  {
    return new GeoJsonPolygon(parseCoordinatesArrays(paramJSONArray));
  }
  
  private static boolean isGeometry(String paramString)
  {
    return paramString.matches("Point|MultiPoint|LineString|MultiLineString|Polygon|MultiPolygon|GeometryCollection");
  }
  
  private static LatLngBounds parseBoundingBox(JSONArray paramJSONArray)
    throws JSONException
  {
    return new LatLngBounds(new LatLng(paramJSONArray.getDouble(1), paramJSONArray.getDouble(0)), new LatLng(paramJSONArray.getDouble(3), paramJSONArray.getDouble(2)));
  }
  
  private static LatLng parseCoordinate(JSONArray paramJSONArray)
    throws JSONException
  {
    return new LatLng(paramJSONArray.getDouble(1), paramJSONArray.getDouble(0));
  }
  
  private static ArrayList<LatLng> parseCoordinatesArray(JSONArray paramJSONArray)
    throws JSONException
  {
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    while (i < paramJSONArray.length())
    {
      localArrayList.add(parseCoordinate(paramJSONArray.getJSONArray(i)));
      i += 1;
    }
    return localArrayList;
  }
  
  private static ArrayList<ArrayList<LatLng>> parseCoordinatesArrays(JSONArray paramJSONArray)
    throws JSONException
  {
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    while (i < paramJSONArray.length())
    {
      localArrayList.add(parseCoordinatesArray(paramJSONArray.getJSONArray(i)));
      i += 1;
    }
    return localArrayList;
  }
  
  private static GeoJsonFeature parseFeature(JSONObject paramJSONObject)
  {
    HashMap localHashMap2 = new HashMap();
    try
    {
      if (!paramJSONObject.has("id")) {
        break label171;
      }
      localObject1 = paramJSONObject.getString("id");
    }
    catch (JSONException localJSONException)
    {
      for (;;)
      {
        Object localObject1;
        HashMap localHashMap1;
        continue;
        Object localObject2 = null;
        continue;
        LatLngBounds localLatLngBounds = null;
        continue;
        Geometry localGeometry = null;
      }
    }
    if (paramJSONObject.has("bbox"))
    {
      localLatLngBounds = parseBoundingBox(paramJSONObject.getJSONArray("bbox"));
      if ((!paramJSONObject.has("geometry")) || (paramJSONObject.isNull("geometry"))) {
        break label181;
      }
      localGeometry = parseGeometry(paramJSONObject.getJSONObject("geometry"));
      localHashMap1 = localHashMap2;
      if (paramJSONObject.has("properties"))
      {
        localHashMap1 = localHashMap2;
        if (!paramJSONObject.isNull("properties")) {
          localHashMap1 = parseProperties(paramJSONObject.getJSONObject("properties"));
        }
      }
      return new GeoJsonFeature(localGeometry, (String)localObject1, localHashMap1, localLatLngBounds);
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append("Feature could not be successfully parsed ");
      ((StringBuilder)localObject1).append(paramJSONObject.toString());
      Log.w("GeoJsonParser", ((StringBuilder)localObject1).toString());
      return null;
    }
  }
  
  private ArrayList<GeoJsonFeature> parseFeatureCollection(JSONObject paramJSONObject)
  {
    ArrayList localArrayList = new ArrayList();
    for (;;)
    {
      try
      {
        localJSONArray = paramJSONObject.getJSONArray("features");
        if (paramJSONObject.has("bbox")) {
          mBoundingBox = parseBoundingBox(paramJSONObject.getJSONArray("bbox"));
        }
        i = 0;
        if (i >= localJSONArray.length()) {
          break;
        }
      }
      catch (JSONException paramJSONObject)
      {
        JSONArray localJSONArray;
        int i;
        continue;
      }
      try
      {
        paramJSONObject = localJSONArray.getJSONObject(i);
        if (!paramJSONObject.getString("type").equals("Feature")) {
          continue;
        }
        paramJSONObject = parseFeature(paramJSONObject);
        if (paramJSONObject != null)
        {
          localArrayList.add(paramJSONObject);
        }
        else
        {
          paramJSONObject = new StringBuilder();
          paramJSONObject.append("Index of Feature in Feature Collection that could not be created: ");
          paramJSONObject.append(i);
          Log.w("GeoJsonParser", paramJSONObject.toString());
        }
      }
      catch (JSONException paramJSONObject)
      {
        continue;
      }
      paramJSONObject = new StringBuilder();
      paramJSONObject.append("Index of Feature in Feature Collection that could not be created: ");
      paramJSONObject.append(i);
      Log.w("GeoJsonParser", paramJSONObject.toString());
      i += 1;
    }
    return localArrayList;
    Log.w("GeoJsonParser", "Feature Collection could not be created.");
    return localArrayList;
  }
  
  private void parseGeoJson()
  {
    try
    {
      Object localObject = mGeoJsonFile.getString("type");
      if (((String)localObject).equals("Feature"))
      {
        localObject = parseFeature(mGeoJsonFile);
        if (localObject == null) {
          break label116;
        }
        mGeoJsonFeatures.add(localObject);
        return;
      }
      if (((String)localObject).equals("FeatureCollection"))
      {
        mGeoJsonFeatures.addAll(parseFeatureCollection(mGeoJsonFile));
        return;
      }
      if (isGeometry((String)localObject))
      {
        localObject = parseGeometryToFeature(mGeoJsonFile);
        if (localObject == null) {
          break label116;
        }
        mGeoJsonFeatures.add(localObject);
        return;
      }
      Log.w("GeoJsonParser", "GeoJSON file could not be parsed.");
      return;
    }
    catch (JSONException localJSONException)
    {
      label116:
      for (;;) {}
    }
    Log.w("GeoJsonParser", "GeoJSON file could not be parsed.");
  }
  
  private static Geometry parseGeometry(JSONObject paramJSONObject)
  {
    try
    {
      String str = paramJSONObject.getString("type");
      if (str.equals("GeometryCollection"))
      {
        paramJSONObject = paramJSONObject.getJSONArray("geometries");
      }
      else
      {
        if (!isGeometry(str)) {
          break label49;
        }
        paramJSONObject = paramJSONObject.getJSONArray("coordinates");
      }
      paramJSONObject = createGeometry(str, paramJSONObject);
      return paramJSONObject;
      label49:
      return null;
    }
    catch (JSONException paramJSONObject) {}
    return null;
  }
  
  private static GeoJsonFeature parseGeometryToFeature(JSONObject paramJSONObject)
  {
    paramJSONObject = parseGeometry(paramJSONObject);
    if (paramJSONObject != null) {
      return new GeoJsonFeature(paramJSONObject, null, new HashMap(), null);
    }
    Log.w("GeoJsonParser", "Geometry could not be parsed");
    return null;
  }
  
  private static HashMap<String, String> parseProperties(JSONObject paramJSONObject)
    throws JSONException
  {
    HashMap localHashMap = new HashMap();
    Iterator localIterator = paramJSONObject.keys();
    while (localIterator.hasNext())
    {
      String str2 = (String)localIterator.next();
      String str1;
      if (paramJSONObject.isNull(str2)) {
        str1 = null;
      } else {
        str1 = paramJSONObject.getString(str2);
      }
      localHashMap.put(str2, str1);
    }
    return localHashMap;
  }
  
  LatLngBounds getBoundingBox()
  {
    return mBoundingBox;
  }
  
  ArrayList<GeoJsonFeature> getFeatures()
  {
    return mGeoJsonFeatures;
  }
}
