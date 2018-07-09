package com.google.maps.android.data.kml;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.maps.android.data.Geometry;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

class KmlFeatureParser
{
  private static final String BOUNDARY_REGEX = "outerBoundaryIs|innerBoundaryIs";
  private static final String COMPASS_REGEX = "north|south|east|west";
  private static final String EXTENDED_DATA = "ExtendedData";
  private static final String GEOMETRY_REGEX = "Point|LineString|Polygon|MultiGeometry";
  private static final int LATITUDE_INDEX = 1;
  private static final int LONGITUDE_INDEX = 0;
  private static final String PROPERTY_REGEX = "name|description|drawOrder|visibility|open|address|phoneNumber";
  private static final String STYLE_TAG = "Style";
  private static final String STYLE_URL_TAG = "styleUrl";
  
  KmlFeatureParser() {}
  
  private static LatLng convertToLatLng(String paramString)
  {
    paramString = paramString.split(",");
    double d1 = Double.parseDouble(paramString[1]);
    double d2 = Double.parseDouble(paramString[0]);
    return new LatLng(Double.valueOf(d1).doubleValue(), Double.valueOf(d2).doubleValue());
  }
  
  private static ArrayList<LatLng> convertToLatLngArray(String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    paramString = paramString.trim().split("(\\s+)");
    int i = 0;
    int j = paramString.length;
    while (i < j)
    {
      localArrayList.add(convertToLatLng(paramString[i]));
      i += 1;
    }
    return localArrayList;
  }
  
  private static Geometry createGeometry(XmlPullParser paramXmlPullParser, String paramString)
    throws IOException, XmlPullParserException
  {
    for (int i = paramXmlPullParser.getEventType();; i = paramXmlPullParser.next())
    {
      if ((i == 3) && (paramXmlPullParser.getName().equals(paramString))) {
        return null;
      }
      if (i == 2)
      {
        if (paramXmlPullParser.getName().equals("Point")) {
          return createPoint(paramXmlPullParser);
        }
        if (paramXmlPullParser.getName().equals("LineString")) {
          return createLineString(paramXmlPullParser);
        }
        if (paramXmlPullParser.getName().equals("Polygon")) {
          return createPolygon(paramXmlPullParser);
        }
        if (paramXmlPullParser.getName().equals("MultiGeometry")) {
          return createMultiGeometry(paramXmlPullParser);
        }
      }
    }
  }
  
  static KmlGroundOverlay createGroundOverlay(XmlPullParser paramXmlPullParser)
    throws IOException, XmlPullParserException
  {
    HashMap localHashMap1 = new HashMap();
    HashMap localHashMap2 = new HashMap();
    int k = paramXmlPullParser.getEventType();
    Object localObject1 = null;
    float f1 = 0.0F;
    int i = 1;
    float f2 = f1;
    for (;;)
    {
      if ((k == 3) && (paramXmlPullParser.getName().equals("GroundOverlay"))) {
        return new KmlGroundOverlay((String)localObject1, createLatLngBounds((Double)localHashMap2.get("north"), (Double)localHashMap2.get("south"), (Double)localHashMap2.get("east"), (Double)localHashMap2.get("west")), f2, i, localHashMap1, f1);
      }
      float f3 = f2;
      Object localObject2 = localObject1;
      int j = i;
      float f4 = f1;
      if (k == 2) {
        if (paramXmlPullParser.getName().equals("Icon"))
        {
          localObject2 = getImageUrl(paramXmlPullParser);
          f3 = f2;
          j = i;
          f4 = f1;
        }
        else if (paramXmlPullParser.getName().equals("drawOrder"))
        {
          f3 = Float.parseFloat(paramXmlPullParser.nextText());
          localObject2 = localObject1;
          j = i;
          f4 = f1;
        }
        else if (paramXmlPullParser.getName().equals("visibility"))
        {
          j = Integer.parseInt(paramXmlPullParser.nextText());
          f3 = f2;
          localObject2 = localObject1;
          f4 = f1;
        }
        else if (paramXmlPullParser.getName().equals("ExtendedData"))
        {
          localHashMap1.putAll(setExtendedDataProperties(paramXmlPullParser));
          f3 = f2;
          localObject2 = localObject1;
          j = i;
          f4 = f1;
        }
        else if (paramXmlPullParser.getName().equals("rotation"))
        {
          f4 = getRotation(paramXmlPullParser);
          f3 = f2;
          localObject2 = localObject1;
          j = i;
        }
        else if ((!paramXmlPullParser.getName().matches("name|description|drawOrder|visibility|open|address|phoneNumber")) && (!paramXmlPullParser.getName().equals("color")))
        {
          f3 = f2;
          localObject2 = localObject1;
          j = i;
          f4 = f1;
          if (paramXmlPullParser.getName().matches("north|south|east|west"))
          {
            localHashMap2.put(paramXmlPullParser.getName(), Double.valueOf(Double.parseDouble(paramXmlPullParser.nextText())));
            f3 = f2;
            localObject2 = localObject1;
            j = i;
            f4 = f1;
          }
        }
        else
        {
          localHashMap1.put(paramXmlPullParser.getName(), paramXmlPullParser.nextText());
          f4 = f1;
          j = i;
          localObject2 = localObject1;
          f3 = f2;
        }
      }
      k = paramXmlPullParser.next();
      f2 = f3;
      localObject1 = localObject2;
      i = j;
      f1 = f4;
    }
  }
  
  private static LatLngBounds createLatLngBounds(Double paramDouble1, Double paramDouble2, Double paramDouble3, Double paramDouble4)
  {
    return new LatLngBounds(new LatLng(paramDouble2.doubleValue(), paramDouble4.doubleValue()), new LatLng(paramDouble1.doubleValue(), paramDouble3.doubleValue()));
  }
  
  private static KmlLineString createLineString(XmlPullParser paramXmlPullParser)
    throws XmlPullParserException, IOException
  {
    Object localObject1 = new ArrayList();
    int i = paramXmlPullParser.getEventType();
    for (;;)
    {
      if ((i == 3) && (paramXmlPullParser.getName().equals("LineString"))) {
        return new KmlLineString((ArrayList)localObject1);
      }
      Object localObject2 = localObject1;
      if (i == 2)
      {
        localObject2 = localObject1;
        if (paramXmlPullParser.getName().equals("coordinates")) {
          localObject2 = convertToLatLngArray(paramXmlPullParser.nextText());
        }
      }
      i = paramXmlPullParser.next();
      localObject1 = localObject2;
    }
  }
  
  private static KmlMultiGeometry createMultiGeometry(XmlPullParser paramXmlPullParser)
    throws XmlPullParserException, IOException
  {
    ArrayList localArrayList = new ArrayList();
    for (int i = paramXmlPullParser.next();; i = paramXmlPullParser.next())
    {
      if ((i == 3) && (paramXmlPullParser.getName().equals("MultiGeometry"))) {
        return new KmlMultiGeometry(localArrayList);
      }
      if ((i == 2) && (paramXmlPullParser.getName().matches("Point|LineString|Polygon|MultiGeometry"))) {
        localArrayList.add(createGeometry(paramXmlPullParser, paramXmlPullParser.getName()));
      }
    }
  }
  
  static KmlPlacemark createPlacemark(XmlPullParser paramXmlPullParser)
    throws IOException, XmlPullParserException
  {
    HashMap localHashMap = new HashMap();
    int i = paramXmlPullParser.getEventType();
    Object localObject3 = null;
    Object localObject2 = null;
    Object localObject6;
    for (Object localObject1 = localObject2;; localObject1 = localObject6)
    {
      if ((i == 3) && (paramXmlPullParser.getName().equals("Placemark"))) {
        return new KmlPlacemark((Geometry)localObject3, (String)localObject2, localObject1, localHashMap);
      }
      Object localObject4 = localObject3;
      Object localObject5 = localObject2;
      localObject6 = localObject1;
      if (i == 2) {
        if (paramXmlPullParser.getName().equals("styleUrl"))
        {
          localObject5 = paramXmlPullParser.nextText();
          localObject4 = localObject3;
          localObject6 = localObject1;
        }
        else if (paramXmlPullParser.getName().matches("Point|LineString|Polygon|MultiGeometry"))
        {
          localObject4 = createGeometry(paramXmlPullParser, paramXmlPullParser.getName());
          localObject5 = localObject2;
          localObject6 = localObject1;
        }
        else if (paramXmlPullParser.getName().matches("name|description|drawOrder|visibility|open|address|phoneNumber"))
        {
          localHashMap.put(paramXmlPullParser.getName(), paramXmlPullParser.nextText());
          localObject4 = localObject3;
          localObject5 = localObject2;
          localObject6 = localObject1;
        }
        else if (paramXmlPullParser.getName().equals("ExtendedData"))
        {
          localHashMap.putAll(setExtendedDataProperties(paramXmlPullParser));
          localObject4 = localObject3;
          localObject5 = localObject2;
          localObject6 = localObject1;
        }
        else
        {
          localObject4 = localObject3;
          localObject5 = localObject2;
          localObject6 = localObject1;
          if (paramXmlPullParser.getName().equals("Style"))
          {
            localObject6 = KmlStyleParser.createStyle(paramXmlPullParser);
            localObject5 = localObject2;
            localObject4 = localObject3;
          }
        }
      }
      i = paramXmlPullParser.next();
      localObject3 = localObject4;
      localObject2 = localObject5;
    }
  }
  
  private static KmlPoint createPoint(XmlPullParser paramXmlPullParser)
    throws XmlPullParserException, IOException
  {
    int i = paramXmlPullParser.getEventType();
    Object localObject2;
    for (Object localObject1 = null;; localObject1 = localObject2)
    {
      if ((i == 3) && (paramXmlPullParser.getName().equals("Point"))) {
        return new KmlPoint((LatLng)localObject1);
      }
      localObject2 = localObject1;
      if (i == 2)
      {
        localObject2 = localObject1;
        if (paramXmlPullParser.getName().equals("coordinates")) {
          localObject2 = convertToLatLng(paramXmlPullParser.nextText());
        }
      }
      i = paramXmlPullParser.next();
    }
  }
  
  private static KmlPolygon createPolygon(XmlPullParser paramXmlPullParser)
    throws XmlPullParserException, IOException
  {
    Object localObject2 = Boolean.valueOf(false);
    Object localObject1 = new ArrayList();
    ArrayList localArrayList = new ArrayList();
    int i = paramXmlPullParser.getEventType();
    for (;;)
    {
      if ((i == 3) && (paramXmlPullParser.getName().equals("Polygon"))) {
        return new KmlPolygon((List)localObject1, localArrayList);
      }
      Object localObject3 = localObject2;
      Object localObject4 = localObject1;
      if (i == 2) {
        if (paramXmlPullParser.getName().matches("outerBoundaryIs|innerBoundaryIs"))
        {
          localObject3 = Boolean.valueOf(paramXmlPullParser.getName().equals("outerBoundaryIs"));
          localObject4 = localObject1;
        }
        else
        {
          localObject3 = localObject2;
          localObject4 = localObject1;
          if (paramXmlPullParser.getName().equals("coordinates")) {
            if (((Boolean)localObject2).booleanValue())
            {
              localObject4 = convertToLatLngArray(paramXmlPullParser.nextText());
              localObject3 = localObject2;
            }
            else
            {
              localArrayList.add(convertToLatLngArray(paramXmlPullParser.nextText()));
              localObject4 = localObject1;
              localObject3 = localObject2;
            }
          }
        }
      }
      i = paramXmlPullParser.next();
      localObject2 = localObject3;
      localObject1 = localObject4;
    }
  }
  
  private static String getImageUrl(XmlPullParser paramXmlPullParser)
    throws IOException, XmlPullParserException
  {
    for (int i = paramXmlPullParser.getEventType();; i = paramXmlPullParser.next())
    {
      if ((i == 3) && (paramXmlPullParser.getName().equals("Icon"))) {
        return null;
      }
      if ((i == 2) && (paramXmlPullParser.getName().equals("href"))) {
        return paramXmlPullParser.nextText();
      }
    }
  }
  
  private static float getRotation(XmlPullParser paramXmlPullParser)
    throws IOException, XmlPullParserException
  {
    return -Float.parseFloat(paramXmlPullParser.nextText());
  }
  
  private static HashMap<String, String> setExtendedDataProperties(XmlPullParser paramXmlPullParser)
    throws XmlPullParserException, IOException
  {
    HashMap localHashMap = new HashMap();
    int i = paramXmlPullParser.getEventType();
    Object localObject1;
    for (Object localObject2 = null;; localObject2 = localObject1)
    {
      if ((i == 3) && (paramXmlPullParser.getName().equals("ExtendedData"))) {
        return localHashMap;
      }
      localObject1 = localObject2;
      if (i == 2) {
        if (paramXmlPullParser.getName().equals("Data"))
        {
          localObject1 = paramXmlPullParser.getAttributeValue(null, "name");
        }
        else
        {
          localObject1 = localObject2;
          if (paramXmlPullParser.getName().equals("value"))
          {
            localObject1 = localObject2;
            if (localObject2 != null)
            {
              localHashMap.put(localObject2, paramXmlPullParser.nextText());
              localObject1 = null;
            }
          }
        }
      }
      i = paramXmlPullParser.next();
    }
  }
}
