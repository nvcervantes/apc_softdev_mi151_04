package com.google.maps.android.data.kml;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

class KmlContainerParser
{
  private static final String CONTAINER_REGEX = "Folder|Document";
  private static final String EXTENDED_DATA = "ExtendedData";
  private static final String GROUND_OVERLAY = "GroundOverlay";
  private static final String PLACEMARK = "Placemark";
  private static final String PROPERTY_REGEX = "name|description|visibility|open|address|phoneNumber";
  private static final String STYLE = "Style";
  private static final String STYLE_MAP = "StyleMap";
  private static final String UNSUPPORTED_REGEX = "altitude|altitudeModeGroup|altitudeMode|begin|bottomFov|cookie|displayName|displayMode|displayMode|end|expires|extrude|flyToView|gridOrigin|httpQuery|leftFov|linkDescription|linkName|linkSnippet|listItemType|maxSnippetLines|maxSessionLength|message|minAltitude|minFadeExtent|minLodPixels|minRefreshPeriod|maxAltitude|maxFadeExtent|maxLodPixels|maxHeight|maxWidth|near|overlayXY|range|refreshMode|refreshInterval|refreshVisibility|rightFov|roll|rotationXY|screenXY|shape|sourceHref|state|targetHref|tessellate|tileSize|topFov|viewBoundScale|viewFormat|viewRefreshMode|viewRefreshTime|when";
  
  KmlContainerParser() {}
  
  private static KmlContainer assignPropertiesToContainer(XmlPullParser paramXmlPullParser)
    throws XmlPullParserException, IOException
  {
    String str2 = paramXmlPullParser.getName();
    HashMap localHashMap1 = new HashMap();
    HashMap localHashMap2 = new HashMap();
    HashMap localHashMap3 = new HashMap();
    ArrayList localArrayList = new ArrayList();
    HashMap localHashMap4 = new HashMap();
    HashMap localHashMap5 = new HashMap();
    String str1;
    if (paramXmlPullParser.getAttributeValue(null, "id") != null) {
      str1 = paramXmlPullParser.getAttributeValue(null, "id");
    } else {
      str1 = null;
    }
    paramXmlPullParser.next();
    for (int i = paramXmlPullParser.getEventType();; i = paramXmlPullParser.next())
    {
      if ((i == 3) && (paramXmlPullParser.getName().equals(str2))) {
        return new KmlContainer(localHashMap1, localHashMap2, localHashMap3, localHashMap4, localArrayList, localHashMap5, str1);
      }
      if (i == 2) {
        if (paramXmlPullParser.getName().matches("altitude|altitudeModeGroup|altitudeMode|begin|bottomFov|cookie|displayName|displayMode|displayMode|end|expires|extrude|flyToView|gridOrigin|httpQuery|leftFov|linkDescription|linkName|linkSnippet|listItemType|maxSnippetLines|maxSessionLength|message|minAltitude|minFadeExtent|minLodPixels|minRefreshPeriod|maxAltitude|maxFadeExtent|maxLodPixels|maxHeight|maxWidth|near|overlayXY|range|refreshMode|refreshInterval|refreshVisibility|rightFov|roll|rotationXY|screenXY|shape|sourceHref|state|targetHref|tessellate|tileSize|topFov|viewBoundScale|viewFormat|viewRefreshMode|viewRefreshTime|when")) {
          KmlParser.skip(paramXmlPullParser);
        } else if (paramXmlPullParser.getName().matches("Folder|Document")) {
          localArrayList.add(assignPropertiesToContainer(paramXmlPullParser));
        } else if (paramXmlPullParser.getName().matches("name|description|visibility|open|address|phoneNumber")) {
          localHashMap1.put(paramXmlPullParser.getName(), paramXmlPullParser.nextText());
        } else if (paramXmlPullParser.getName().equals("StyleMap")) {
          setContainerStyleMap(paramXmlPullParser, localHashMap4);
        } else if (paramXmlPullParser.getName().equals("Style")) {
          setContainerStyle(paramXmlPullParser, localHashMap2);
        } else if (paramXmlPullParser.getName().equals("Placemark")) {
          setContainerPlacemark(paramXmlPullParser, localHashMap3);
        } else if (paramXmlPullParser.getName().equals("ExtendedData")) {
          setExtendedDataProperties(paramXmlPullParser, localHashMap1);
        } else if (paramXmlPullParser.getName().equals("GroundOverlay")) {
          localHashMap5.put(KmlFeatureParser.createGroundOverlay(paramXmlPullParser), null);
        }
      }
    }
  }
  
  static KmlContainer createContainer(XmlPullParser paramXmlPullParser)
    throws XmlPullParserException, IOException
  {
    return assignPropertiesToContainer(paramXmlPullParser);
  }
  
  private static void setContainerPlacemark(XmlPullParser paramXmlPullParser, HashMap<KmlPlacemark, Object> paramHashMap)
    throws XmlPullParserException, IOException
  {
    paramHashMap.put(KmlFeatureParser.createPlacemark(paramXmlPullParser), null);
  }
  
  private static void setContainerStyle(XmlPullParser paramXmlPullParser, HashMap<String, KmlStyle> paramHashMap)
    throws XmlPullParserException, IOException
  {
    if (paramXmlPullParser.getAttributeValue(null, "id") != null)
    {
      paramXmlPullParser = KmlStyleParser.createStyle(paramXmlPullParser);
      paramHashMap.put(paramXmlPullParser.getStyleId(), paramXmlPullParser);
    }
  }
  
  private static void setContainerStyleMap(XmlPullParser paramXmlPullParser, HashMap<String, String> paramHashMap)
    throws XmlPullParserException, IOException
  {
    paramHashMap.putAll(KmlStyleParser.createStyleMap(paramXmlPullParser));
  }
  
  private static void setExtendedDataProperties(XmlPullParser paramXmlPullParser, HashMap<String, String> paramHashMap)
    throws XmlPullParserException, IOException
  {
    int i = paramXmlPullParser.getEventType();
    Object localObject1;
    for (Object localObject2 = null;; localObject2 = localObject1)
    {
      if ((i == 3) && (paramXmlPullParser.getName().equals("ExtendedData"))) {
        return;
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
              paramHashMap.put(localObject2, paramXmlPullParser.nextText());
              localObject1 = null;
            }
          }
        }
      }
      i = paramXmlPullParser.next();
    }
  }
}
