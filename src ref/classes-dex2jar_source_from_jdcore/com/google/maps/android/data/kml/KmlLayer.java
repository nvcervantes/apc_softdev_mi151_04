package com.google.maps.android.data.kml;

import android.content.Context;
import android.content.res.Resources;
import com.google.android.gms.maps.GoogleMap;
import com.google.maps.android.data.Layer;
import java.io.IOException;
import java.io.InputStream;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

public class KmlLayer
  extends Layer
{
  public KmlLayer(GoogleMap paramGoogleMap, int paramInt, Context paramContext)
    throws XmlPullParserException, IOException
  {
    this(paramGoogleMap, paramContext.getResources().openRawResource(paramInt), paramContext);
  }
  
  public KmlLayer(GoogleMap paramGoogleMap, InputStream paramInputStream, Context paramContext)
    throws XmlPullParserException, IOException
  {
    if (paramInputStream == null) {
      throw new IllegalArgumentException("KML InputStream cannot be null");
    }
    paramGoogleMap = new KmlRenderer(paramGoogleMap, paramContext);
    paramContext = new KmlParser(createXmlParser(paramInputStream));
    paramContext.parseKml();
    paramInputStream.close();
    paramGoogleMap.storeKmlData(paramContext.getStyles(), paramContext.getStyleMaps(), paramContext.getPlacemarks(), paramContext.getContainers(), paramContext.getGroundOverlays());
    storeRenderer(paramGoogleMap);
  }
  
  private static XmlPullParser createXmlParser(InputStream paramInputStream)
    throws XmlPullParserException
  {
    Object localObject = XmlPullParserFactory.newInstance();
    ((XmlPullParserFactory)localObject).setNamespaceAware(true);
    localObject = ((XmlPullParserFactory)localObject).newPullParser();
    ((XmlPullParser)localObject).setInput(paramInputStream, null);
    return localObject;
  }
  
  public void addLayerToMap()
    throws IOException, XmlPullParserException
  {
    super.addKMLToMap();
  }
  
  public Iterable<KmlContainer> getContainers()
  {
    return super.getContainers();
  }
  
  public Iterable<KmlGroundOverlay> getGroundOverlays()
  {
    return super.getGroundOverlays();
  }
  
  public Iterable<KmlPlacemark> getPlacemarks()
  {
    return getFeatures();
  }
  
  public boolean hasContainers()
  {
    return super.hasContainers();
  }
  
  public boolean hasPlacemarks()
  {
    return hasFeatures();
  }
}
