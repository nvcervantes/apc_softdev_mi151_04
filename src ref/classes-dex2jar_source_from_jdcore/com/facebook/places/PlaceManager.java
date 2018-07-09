package com.facebook.places;

import android.location.Location;
import android.os.Bundle;
import android.text.TextUtils;
import com.facebook.AccessToken;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.HttpMethod;
import com.facebook.internal.Utility;
import com.facebook.places.internal.BluetoothScanResult;
import com.facebook.places.internal.LocationPackage;
import com.facebook.places.internal.LocationPackageManager;
import com.facebook.places.internal.LocationPackageManager.Listener;
import com.facebook.places.internal.LocationPackageRequestParams.Builder;
import com.facebook.places.internal.ScannerException.Type;
import com.facebook.places.internal.WifiScanResult;
import com.facebook.places.model.CurrentPlaceFeedbackRequestParams;
import com.facebook.places.model.CurrentPlaceRequestParams;
import com.facebook.places.model.CurrentPlaceRequestParams.ConfidenceLevel;
import com.facebook.places.model.CurrentPlaceRequestParams.ScanMode;
import com.facebook.places.model.PlaceInfoRequestParams;
import com.facebook.places.model.PlaceSearchRequestParams;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PlaceManager
{
  private static final String CURRENT_PLACE_FEEDBACK = "current_place/feedback";
  private static final String CURRENT_PLACE_RESULTS = "current_place/results";
  private static final String PARAM_ACCESS_POINTS = "access_points";
  private static final String PARAM_ACCURACY = "accuracy";
  private static final String PARAM_ALTITUDE = "altitude";
  private static final String PARAM_BLUETOOTH = "bluetooth";
  private static final String PARAM_CATEGORIES = "categories";
  private static final String PARAM_CENTER = "center";
  private static final String PARAM_COORDINATES = "coordinates";
  private static final String PARAM_CURRENT_CONNECTION = "current_connection";
  private static final String PARAM_DISTANCE = "distance";
  private static final String PARAM_ENABLED = "enabled";
  private static final String PARAM_FIELDS = "fields";
  private static final String PARAM_FREQUENCY = "frequency";
  private static final String PARAM_HEADING = "heading";
  private static final String PARAM_LATITUDE = "latitude";
  private static final String PARAM_LIMIT = "limit";
  private static final String PARAM_LONGITUDE = "longitude";
  private static final String PARAM_MAC_ADDRESS = "mac_address";
  private static final String PARAM_MIN_CONFIDENCE_LEVEL = "min_confidence_level";
  private static final String PARAM_PAYLOAD = "payload";
  private static final String PARAM_PLACE_ID = "place_id";
  private static final String PARAM_Q = "q";
  private static final String PARAM_RSSI = "rssi";
  private static final String PARAM_SCANS = "scans";
  private static final String PARAM_SIGNAL_STRENGTH = "signal_strength";
  private static final String PARAM_SPEED = "speed";
  private static final String PARAM_SSID = "ssid";
  private static final String PARAM_SUMMARY = "summary";
  private static final String PARAM_TRACKING = "tracking";
  private static final String PARAM_TYPE = "type";
  private static final String PARAM_WAS_HERE = "was_here";
  private static final String PARAM_WIFI = "wifi";
  private static final String SEARCH = "search";
  
  private PlaceManager() {}
  
  private static Bundle getCurrentPlaceParameters(CurrentPlaceRequestParams paramCurrentPlaceRequestParams, LocationPackage paramLocationPackage)
    throws FacebookException
  {
    if (paramCurrentPlaceRequestParams == null) {
      throw new FacebookException("Request and location must be specified.");
    }
    Object localObject1 = paramLocationPackage;
    if (paramLocationPackage == null) {
      localObject1 = new LocationPackage();
    }
    if (location == null) {
      location = paramCurrentPlaceRequestParams.getLocation();
    }
    if (location == null) {
      throw new FacebookException("A location must be specified");
    }
    try
    {
      paramLocationPackage = new Bundle(6);
      paramLocationPackage.putString("summary", "tracking");
      int i = paramCurrentPlaceRequestParams.getLimit();
      if (i > 0) {
        paramLocationPackage.putInt("limit", i);
      }
      Object localObject2 = paramCurrentPlaceRequestParams.getFields();
      if ((localObject2 != null) && (!((Set)localObject2).isEmpty())) {
        paramLocationPackage.putString("fields", TextUtils.join(",", (Iterable)localObject2));
      }
      localObject2 = location;
      Object localObject3 = new JSONObject();
      ((JSONObject)localObject3).put("latitude", ((Location)localObject2).getLatitude());
      ((JSONObject)localObject3).put("longitude", ((Location)localObject2).getLongitude());
      if (((Location)localObject2).hasAccuracy()) {
        ((JSONObject)localObject3).put("accuracy", ((Location)localObject2).getAccuracy());
      }
      if (((Location)localObject2).hasAltitude()) {
        ((JSONObject)localObject3).put("altitude", ((Location)localObject2).getAltitude());
      }
      if (((Location)localObject2).hasBearing()) {
        ((JSONObject)localObject3).put("heading", ((Location)localObject2).getBearing());
      }
      if (((Location)localObject2).hasSpeed()) {
        ((JSONObject)localObject3).put("speed", ((Location)localObject2).getSpeed());
      }
      paramLocationPackage.putString("coordinates", ((JSONObject)localObject3).toString());
      paramCurrentPlaceRequestParams = paramCurrentPlaceRequestParams.getMinConfidenceLevel();
      if ((paramCurrentPlaceRequestParams == CurrentPlaceRequestParams.ConfidenceLevel.LOW) || (paramCurrentPlaceRequestParams == CurrentPlaceRequestParams.ConfidenceLevel.MEDIUM) || (paramCurrentPlaceRequestParams == CurrentPlaceRequestParams.ConfidenceLevel.HIGH)) {
        paramLocationPackage.putString("min_confidence_level", paramCurrentPlaceRequestParams.toString().toLowerCase(Locale.US));
      }
      if (localObject1 != null)
      {
        paramCurrentPlaceRequestParams = new JSONObject();
        paramCurrentPlaceRequestParams.put("enabled", isWifiScanningEnabled);
        localObject2 = connectedWifi;
        if (localObject2 != null) {
          paramCurrentPlaceRequestParams.put("current_connection", getWifiScanJson((WifiScanResult)localObject2));
        }
        localObject3 = ambientWifi;
        if (localObject3 != null)
        {
          localObject2 = new JSONArray();
          localObject3 = ((List)localObject3).iterator();
          while (((Iterator)localObject3).hasNext()) {
            ((JSONArray)localObject2).put(getWifiScanJson((WifiScanResult)((Iterator)localObject3).next()));
          }
          paramCurrentPlaceRequestParams.put("access_points", localObject2);
        }
        paramLocationPackage.putString("wifi", paramCurrentPlaceRequestParams.toString());
        paramCurrentPlaceRequestParams = new JSONObject();
        paramCurrentPlaceRequestParams.put("enabled", isBluetoothScanningEnabled);
        localObject2 = ambientBluetoothLe;
        if (localObject2 != null)
        {
          localObject1 = new JSONArray();
          localObject2 = ((List)localObject2).iterator();
          while (((Iterator)localObject2).hasNext())
          {
            localObject3 = (BluetoothScanResult)((Iterator)localObject2).next();
            JSONObject localJSONObject = new JSONObject();
            localJSONObject.put("payload", payload);
            localJSONObject.put("rssi", rssi);
            ((JSONArray)localObject1).put(localJSONObject);
          }
          paramCurrentPlaceRequestParams.put("scans", localObject1);
        }
        paramLocationPackage.putString("bluetooth", paramCurrentPlaceRequestParams.toString());
      }
      return paramLocationPackage;
    }
    catch (JSONException paramCurrentPlaceRequestParams)
    {
      throw new FacebookException(paramCurrentPlaceRequestParams);
    }
  }
  
  private static LocationError getLocationError(ScannerException.Type paramType)
  {
    if (paramType == ScannerException.Type.PERMISSION_DENIED) {
      return LocationError.LOCATION_PERMISSION_DENIED;
    }
    if (paramType == ScannerException.Type.DISABLED) {
      return LocationError.LOCATION_SERVICES_DISABLED;
    }
    if (paramType == ScannerException.Type.TIMEOUT) {
      return LocationError.LOCATION_TIMEOUT;
    }
    return LocationError.UNKNOWN_ERROR;
  }
  
  private static JSONObject getWifiScanJson(WifiScanResult paramWifiScanResult)
    throws JSONException
  {
    JSONObject localJSONObject = new JSONObject();
    localJSONObject.put("mac_address", bssid);
    localJSONObject.put("ssid", ssid);
    localJSONObject.put("signal_strength", rssi);
    localJSONObject.put("frequency", frequency);
    return localJSONObject;
  }
  
  public static GraphRequest newCurrentPlaceFeedbackRequest(CurrentPlaceFeedbackRequestParams paramCurrentPlaceFeedbackRequestParams)
  {
    String str1 = paramCurrentPlaceFeedbackRequestParams.getPlaceId();
    String str2 = paramCurrentPlaceFeedbackRequestParams.getTracking();
    paramCurrentPlaceFeedbackRequestParams = paramCurrentPlaceFeedbackRequestParams.wasHere();
    if ((str2 != null) && (str1 != null) && (paramCurrentPlaceFeedbackRequestParams != null))
    {
      Bundle localBundle = new Bundle(3);
      localBundle.putString("tracking", str2);
      localBundle.putString("place_id", str1);
      localBundle.putBoolean("was_here", paramCurrentPlaceFeedbackRequestParams.booleanValue());
      return new GraphRequest(AccessToken.getCurrentAccessToken(), "current_place/feedback", localBundle, HttpMethod.POST);
    }
    throw new FacebookException("tracking, placeId and wasHere must be specified.");
  }
  
  public static void newCurrentPlaceRequest(final CurrentPlaceRequestParams paramCurrentPlaceRequestParams, OnRequestReadyCallback paramOnRequestReadyCallback)
  {
    Location localLocation = paramCurrentPlaceRequestParams.getLocation();
    CurrentPlaceRequestParams.ScanMode localScanMode = paramCurrentPlaceRequestParams.getScanMode();
    LocationPackageRequestParams.Builder localBuilder = new LocationPackageRequestParams.Builder();
    boolean bool;
    if (localLocation == null) {
      bool = true;
    } else {
      bool = false;
    }
    localBuilder.setLocationScanEnabled(bool);
    if ((localScanMode != null) && (localScanMode == CurrentPlaceRequestParams.ScanMode.LOW_LATENCY)) {
      localBuilder.setWifiActiveScanAllowed(false);
    }
    LocationPackageManager.requestLocationPackage(localBuilder.build(), new LocationPackageManager.Listener()
    {
      public void onLocationPackage(LocationPackage paramAnonymousLocationPackage)
      {
        if (locationError != null)
        {
          val$callback.onLocationError(PlaceManager.getLocationError(locationError));
          return;
        }
        paramAnonymousLocationPackage = PlaceManager.getCurrentPlaceParameters(paramCurrentPlaceRequestParams, paramAnonymousLocationPackage);
        paramAnonymousLocationPackage = new GraphRequest(AccessToken.getCurrentAccessToken(), "current_place/results", paramAnonymousLocationPackage, HttpMethod.GET);
        val$callback.onRequestReady(paramAnonymousLocationPackage);
      }
    });
  }
  
  public static GraphRequest newPlaceInfoRequest(PlaceInfoRequestParams paramPlaceInfoRequestParams)
  {
    String str = paramPlaceInfoRequestParams.getPlaceId();
    if (str == null) {
      throw new FacebookException("placeId must be specified.");
    }
    Bundle localBundle = new Bundle(1);
    paramPlaceInfoRequestParams = paramPlaceInfoRequestParams.getFields();
    if ((paramPlaceInfoRequestParams != null) && (!paramPlaceInfoRequestParams.isEmpty())) {
      localBundle.putString("fields", TextUtils.join(",", paramPlaceInfoRequestParams));
    }
    return new GraphRequest(AccessToken.getCurrentAccessToken(), str, localBundle, HttpMethod.GET);
  }
  
  public static void newPlaceSearchRequest(PlaceSearchRequestParams paramPlaceSearchRequestParams, final OnRequestReadyCallback paramOnRequestReadyCallback)
  {
    LocationPackageRequestParams.Builder localBuilder = new LocationPackageRequestParams.Builder();
    localBuilder.setWifiScanEnabled(false);
    localBuilder.setBluetoothScanEnabled(false);
    LocationPackageManager.requestLocationPackage(localBuilder.build(), new LocationPackageManager.Listener()
    {
      public void onLocationPackage(LocationPackage paramAnonymousLocationPackage)
      {
        if (locationError == null)
        {
          paramAnonymousLocationPackage = PlaceManager.newPlaceSearchRequestForLocation(val$requestParams, location);
          paramOnRequestReadyCallback.onRequestReady(paramAnonymousLocationPackage);
          return;
        }
        paramOnRequestReadyCallback.onLocationError(PlaceManager.getLocationError(locationError));
      }
    });
  }
  
  public static GraphRequest newPlaceSearchRequestForLocation(PlaceSearchRequestParams paramPlaceSearchRequestParams, Location paramLocation)
  {
    String str = paramPlaceSearchRequestParams.getSearchText();
    if ((paramLocation == null) && (str == null)) {
      throw new FacebookException("Either location or searchText must be specified.");
    }
    int i = paramPlaceSearchRequestParams.getLimit();
    Set localSet1 = paramPlaceSearchRequestParams.getFields();
    Set localSet2 = paramPlaceSearchRequestParams.getCategories();
    Bundle localBundle = new Bundle(7);
    localBundle.putString("type", "place");
    if (paramLocation != null)
    {
      localBundle.putString("center", String.format(Locale.US, "%f,%f", new Object[] { Double.valueOf(paramLocation.getLatitude()), Double.valueOf(paramLocation.getLongitude()) }));
      int j = paramPlaceSearchRequestParams.getDistance();
      if (j > 0) {
        localBundle.putInt("distance", j);
      }
    }
    if (i > 0) {
      localBundle.putInt("limit", i);
    }
    if (!Utility.isNullOrEmpty(str)) {
      localBundle.putString("q", str);
    }
    if ((localSet2 != null) && (!localSet2.isEmpty()))
    {
      paramPlaceSearchRequestParams = new JSONArray();
      paramLocation = localSet2.iterator();
      while (paramLocation.hasNext()) {
        paramPlaceSearchRequestParams.put((String)paramLocation.next());
      }
      localBundle.putString("categories", paramPlaceSearchRequestParams.toString());
    }
    if ((localSet1 != null) && (!localSet1.isEmpty())) {
      localBundle.putString("fields", TextUtils.join(",", localSet1));
    }
    return new GraphRequest(AccessToken.getCurrentAccessToken(), "search", localBundle, HttpMethod.GET);
  }
  
  public static enum LocationError
  {
    LOCATION_PERMISSION_DENIED,  LOCATION_SERVICES_DISABLED,  LOCATION_TIMEOUT,  UNKNOWN_ERROR;
    
    private LocationError() {}
  }
  
  public static abstract interface OnRequestReadyCallback
  {
    public abstract void onLocationError(PlaceManager.LocationError paramLocationError);
    
    public abstract void onRequestReady(GraphRequest paramGraphRequest);
  }
}
