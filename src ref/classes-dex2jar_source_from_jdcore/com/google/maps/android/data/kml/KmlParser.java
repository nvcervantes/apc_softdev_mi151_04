package com.google.maps.android.data.kml;

import com.google.android.gms.maps.model.GroundOverlay;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

class KmlParser
{
  private static final String CONTAINER_REGEX = "Folder|Document";
  private static final String GROUND_OVERLAY = "GroundOverlay";
  private static final String PLACEMARK = "Placemark";
  private static final String STYLE = "Style";
  private static final String STYLE_MAP = "StyleMap";
  private static final String UNSUPPORTED_REGEX = "altitude|altitudeModeGroup|altitudeMode|begin|bottomFov|cookie|displayName|displayMode|displayMode|end|expires|extrude|flyToView|gridOrigin|httpQuery|leftFov|linkDescription|linkName|linkSnippet|listItemType|maxSnippetLines|maxSessionLength|message|minAltitude|minFadeExtent|minLodPixels|minRefreshPeriod|maxAltitude|maxFadeExtent|maxLodPixels|maxHeight|maxWidth|near|NetworkLink|NetworkLinkControl|overlayXY|range|refreshMode|refreshInterval|refreshVisibility|rightFov|roll|rotationXY|screenXY|shape|sourceHref|state|targetHref|tessellate|tileSize|topFov|viewBoundScale|viewFormat|viewRefreshMode|viewRefreshTime|when";
  private final ArrayList<KmlContainer> mContainers;
  private final HashMap<KmlGroundOverlay, GroundOverlay> mGroundOverlays;
  private final XmlPullParser mParser;
  private final HashMap<KmlPlacemark, Object> mPlacemarks;
  private final HashMap<String, String> mStyleMaps;
  private final HashMap<String, KmlStyle> mStyles;
  
  KmlParser(XmlPullParser paramXmlPullParser)
  {
    mParser = paramXmlPullParser;
    mPlacemarks = new HashMap();
    mContainers = new ArrayList();
    mStyles = new HashMap();
    mStyleMaps = new HashMap();
    mGroundOverlays = new HashMap();
  }
  
  static void skip(XmlPullParser paramXmlPullParser)
    throws XmlPullParserException, IOException
  {
    if (paramXmlPullParser.getEventType() != 2) {
      throw new IllegalStateException();
    }
    int i = 1;
    while (i != 0) {
      switch (paramXmlPullParser.next())
      {
      default: 
        break;
      case 3: 
        i -= 1;
        break;
      case 2: 
        i += 1;
      }
    }
  }
  
  ArrayList<KmlContainer> getContainers()
  {
    return mContainers;
  }
  
  HashMap<KmlGroundOverlay, GroundOverlay> getGroundOverlays()
  {
    return mGroundOverlays;
  }
  
  HashMap<KmlPlacemark, Object> getPlacemarks()
  {
    return mPlacemarks;
  }
  
  HashMap<String, String> getStyleMaps()
  {
    return mStyleMaps;
  }
  
  HashMap<String, KmlStyle> getStyles()
  {
    return mStyles;
  }
  
  void parseKml()
    throws XmlPullParserException, IOException
  {
    for (int i = mParser.getEventType(); i != 1; i = mParser.next()) {
      if (i == 2)
      {
        if (mParser.getName().matches("altitude|altitudeModeGroup|altitudeMode|begin|bottomFov|cookie|displayName|displayMode|displayMode|end|expires|extrude|flyToView|gridOrigin|httpQuery|leftFov|linkDescription|linkName|linkSnippet|listItemType|maxSnippetLines|maxSessionLength|message|minAltitude|minFadeExtent|minLodPixels|minRefreshPeriod|maxAltitude|maxFadeExtent|maxLodPixels|maxHeight|maxWidth|near|NetworkLink|NetworkLinkControl|overlayXY|range|refreshMode|refreshInterval|refreshVisibility|rightFov|roll|rotationXY|screenXY|shape|sourceHref|state|targetHref|tessellate|tileSize|topFov|viewBoundScale|viewFormat|viewRefreshMode|viewRefreshTime|when")) {
          skip(mParser);
        }
        if (mParser.getName().matches("Folder|Document")) {
          mContainers.add(KmlContainerParser.createContainer(mParser));
        }
        if (mParser.getName().equals("Style"))
        {
          KmlStyle localKmlStyle = KmlStyleParser.createStyle(mParser);
          mStyles.put(localKmlStyle.getStyleId(), localKmlStyle);
        }
        if (mParser.getName().equals("StyleMap")) {
          mStyleMaps.putAll(KmlStyleParser.createStyleMap(mParser));
        }
        if (mParser.getName().equals("Placemark")) {
          mPlacemarks.put(KmlFeatureParser.createPlacemark(mParser), null);
        }
        if (mParser.getName().equals("GroundOverlay")) {
          mGroundOverlays.put(KmlFeatureParser.createGroundOverlay(mParser), null);
        }
      }
    }
    mStyles.put(null, new KmlStyle());
  }
}
