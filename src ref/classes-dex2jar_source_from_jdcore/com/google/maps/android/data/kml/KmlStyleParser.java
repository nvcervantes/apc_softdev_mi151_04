package com.google.maps.android.data.kml;

import java.io.IOException;
import java.util.HashMap;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

class KmlStyleParser
{
  private static final String COLOR_STYLE_COLOR = "color";
  private static final String COLOR_STYLE_MODE = "colorMode";
  private static final String ICON_STYLE_HEADING = "heading";
  private static final String ICON_STYLE_HOTSPOT = "hotSpot";
  private static final String ICON_STYLE_SCALE = "scale";
  private static final String ICON_STYLE_URL = "Icon";
  private static final String LINE_STYLE_WIDTH = "width";
  private static final String POLY_STYLE_FILL = "fill";
  private static final String POLY_STYLE_OUTLINE = "outline";
  private static final String STYLE_MAP_KEY = "key";
  private static final String STYLE_MAP_NORMAL_STYLE = "normal";
  private static final String STYLE_TAG = "styleUrl";
  
  KmlStyleParser() {}
  
  private static void createBalloonStyle(XmlPullParser paramXmlPullParser, KmlStyle paramKmlStyle)
    throws XmlPullParserException, IOException
  {
    for (int i = paramXmlPullParser.getEventType();; i = paramXmlPullParser.next())
    {
      if ((i == 3) && (paramXmlPullParser.getName().equals("BalloonStyle"))) {
        return;
      }
      if ((i == 2) && (paramXmlPullParser.getName().equals("text"))) {
        paramKmlStyle.setInfoWindowText(paramXmlPullParser.nextText());
      }
    }
  }
  
  private static void createIconStyle(XmlPullParser paramXmlPullParser, KmlStyle paramKmlStyle)
    throws XmlPullParserException, IOException
  {
    for (int i = paramXmlPullParser.getEventType();; i = paramXmlPullParser.next())
    {
      if ((i == 3) && (paramXmlPullParser.getName().equals("IconStyle"))) {
        return;
      }
      if (i == 2) {
        if (paramXmlPullParser.getName().equals("heading")) {
          paramKmlStyle.setHeading(Float.parseFloat(paramXmlPullParser.nextText()));
        } else if (paramXmlPullParser.getName().equals("Icon")) {
          setIconUrl(paramXmlPullParser, paramKmlStyle);
        } else if (paramXmlPullParser.getName().equals("hotSpot")) {
          setIconHotSpot(paramXmlPullParser, paramKmlStyle);
        } else if (paramXmlPullParser.getName().equals("scale")) {
          paramKmlStyle.setIconScale(Double.parseDouble(paramXmlPullParser.nextText()));
        } else if (paramXmlPullParser.getName().equals("color")) {
          paramKmlStyle.setMarkerColor(paramXmlPullParser.nextText());
        } else if (paramXmlPullParser.getName().equals("colorMode")) {
          paramKmlStyle.setIconColorMode(paramXmlPullParser.nextText());
        }
      }
    }
  }
  
  private static void createLineStyle(XmlPullParser paramXmlPullParser, KmlStyle paramKmlStyle)
    throws XmlPullParserException, IOException
  {
    for (int i = paramXmlPullParser.getEventType();; i = paramXmlPullParser.next())
    {
      if ((i == 3) && (paramXmlPullParser.getName().equals("LineStyle"))) {
        return;
      }
      if (i == 2) {
        if (paramXmlPullParser.getName().equals("color")) {
          paramKmlStyle.setOutlineColor(paramXmlPullParser.nextText());
        } else if (paramXmlPullParser.getName().equals("width")) {
          paramKmlStyle.setWidth(Float.valueOf(paramXmlPullParser.nextText()));
        } else if (paramXmlPullParser.getName().equals("colorMode")) {
          paramKmlStyle.setLineColorMode(paramXmlPullParser.nextText());
        }
      }
    }
  }
  
  private static void createPolyStyle(XmlPullParser paramXmlPullParser, KmlStyle paramKmlStyle)
    throws XmlPullParserException, IOException
  {
    for (int i = paramXmlPullParser.getEventType();; i = paramXmlPullParser.next())
    {
      if ((i == 3) && (paramXmlPullParser.getName().equals("PolyStyle"))) {
        return;
      }
      if (i == 2) {
        if (paramXmlPullParser.getName().equals("color")) {
          paramKmlStyle.setFillColor(paramXmlPullParser.nextText());
        } else if (paramXmlPullParser.getName().equals("outline")) {
          paramKmlStyle.setOutline(KmlBoolean.parseBoolean(paramXmlPullParser.nextText()));
        } else if (paramXmlPullParser.getName().equals("fill")) {
          paramKmlStyle.setFill(KmlBoolean.parseBoolean(paramXmlPullParser.nextText()));
        } else if (paramXmlPullParser.getName().equals("colorMode")) {
          paramKmlStyle.setPolyColorMode(paramXmlPullParser.nextText());
        }
      }
    }
  }
  
  static KmlStyle createStyle(XmlPullParser paramXmlPullParser)
    throws IOException, XmlPullParserException
  {
    KmlStyle localKmlStyle = new KmlStyle();
    setStyleId(paramXmlPullParser.getAttributeValue(null, "id"), localKmlStyle);
    for (int i = paramXmlPullParser.getEventType();; i = paramXmlPullParser.next())
    {
      if ((i == 3) && (paramXmlPullParser.getName().equals("Style"))) {
        return localKmlStyle;
      }
      if (i == 2) {
        if (paramXmlPullParser.getName().equals("IconStyle")) {
          createIconStyle(paramXmlPullParser, localKmlStyle);
        } else if (paramXmlPullParser.getName().equals("LineStyle")) {
          createLineStyle(paramXmlPullParser, localKmlStyle);
        } else if (paramXmlPullParser.getName().equals("PolyStyle")) {
          createPolyStyle(paramXmlPullParser, localKmlStyle);
        } else if (paramXmlPullParser.getName().equals("BalloonStyle")) {
          createBalloonStyle(paramXmlPullParser, localKmlStyle);
        }
      }
    }
  }
  
  static HashMap<String, String> createStyleMap(XmlPullParser paramXmlPullParser)
    throws XmlPullParserException, IOException
  {
    HashMap localHashMap = new HashMap();
    Object localObject2 = Boolean.valueOf(false);
    Object localObject1 = new StringBuilder();
    ((StringBuilder)localObject1).append("#");
    ((StringBuilder)localObject1).append(paramXmlPullParser.getAttributeValue(null, "id"));
    String str = ((StringBuilder)localObject1).toString();
    int i = paramXmlPullParser.getEventType();
    for (;;)
    {
      if ((i == 3) && (paramXmlPullParser.getName().equals("StyleMap"))) {
        return localHashMap;
      }
      localObject1 = localObject2;
      if (i == 2) {
        if ((paramXmlPullParser.getName().equals("key")) && (paramXmlPullParser.nextText().equals("normal")))
        {
          localObject1 = Boolean.valueOf(true);
        }
        else
        {
          localObject1 = localObject2;
          if (paramXmlPullParser.getName().equals("styleUrl"))
          {
            localObject1 = localObject2;
            if (((Boolean)localObject2).booleanValue())
            {
              localHashMap.put(str, paramXmlPullParser.nextText());
              localObject1 = Boolean.valueOf(false);
            }
          }
        }
      }
      i = paramXmlPullParser.next();
      localObject2 = localObject1;
    }
  }
  
  private static void setIconHotSpot(XmlPullParser paramXmlPullParser, KmlStyle paramKmlStyle)
  {
    float f1 = Float.parseFloat(paramXmlPullParser.getAttributeValue(null, "x"));
    float f2 = Float.parseFloat(paramXmlPullParser.getAttributeValue(null, "y"));
    String str = paramXmlPullParser.getAttributeValue(null, "xunits");
    paramXmlPullParser = paramXmlPullParser.getAttributeValue(null, "yunits");
    paramKmlStyle.setHotSpot(Float.valueOf(f1).floatValue(), Float.valueOf(f2).floatValue(), str, paramXmlPullParser);
  }
  
  private static void setIconUrl(XmlPullParser paramXmlPullParser, KmlStyle paramKmlStyle)
    throws XmlPullParserException, IOException
  {
    for (int i = paramXmlPullParser.getEventType();; i = paramXmlPullParser.next())
    {
      if ((i == 3) && (paramXmlPullParser.getName().equals("Icon"))) {
        return;
      }
      if ((i == 2) && (paramXmlPullParser.getName().equals("href"))) {
        paramKmlStyle.setIconUrl(paramXmlPullParser.nextText());
      }
    }
  }
  
  private static void setStyleId(String paramString, KmlStyle paramKmlStyle)
  {
    if (paramString != null)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("#");
      localStringBuilder.append(paramString);
      paramKmlStyle.setStyleId(localStringBuilder.toString());
    }
  }
}
