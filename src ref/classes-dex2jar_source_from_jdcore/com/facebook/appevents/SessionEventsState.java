package com.facebook.appevents;

import android.content.Context;
import android.os.Bundle;
import com.facebook.GraphRequest;
import com.facebook.appevents.internal.AppEventsLoggerUtility;
import com.facebook.appevents.internal.AppEventsLoggerUtility.GraphAPIActivityType;
import com.facebook.internal.AttributionIdentifiers;
import com.facebook.internal.Utility;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class SessionEventsState
{
  private final int MAX_ACCUMULATED_LOG_EVENTS = 1000;
  private List<AppEvent> accumulatedEvents = new ArrayList();
  private String anonymousAppDeviceGUID;
  private AttributionIdentifiers attributionIdentifiers;
  private List<AppEvent> inFlightEvents = new ArrayList();
  private int numSkippedEventsDueToFullBuffer;
  
  public SessionEventsState(AttributionIdentifiers paramAttributionIdentifiers, String paramString)
  {
    attributionIdentifiers = paramAttributionIdentifiers;
    anonymousAppDeviceGUID = paramString;
  }
  
  private byte[] getStringAsByteArray(String paramString)
  {
    try
    {
      paramString = paramString.getBytes("UTF-8");
      return paramString;
    }
    catch (UnsupportedEncodingException paramString)
    {
      Utility.logd("Encoding exception: ", paramString);
    }
    return null;
  }
  
  private void populateRequest(GraphRequest paramGraphRequest, Context paramContext, int paramInt, JSONArray paramJSONArray, boolean paramBoolean)
  {
    try
    {
      localObject = AppEventsLoggerUtility.getJSONObjectForGraphAPICall(AppEventsLoggerUtility.GraphAPIActivityType.CUSTOM_APP_EVENTS, attributionIdentifiers, anonymousAppDeviceGUID, paramBoolean, paramContext);
      paramContext = (Context)localObject;
      if (numSkippedEventsDueToFullBuffer <= 0) {
        break label52;
      }
      ((JSONObject)localObject).put("num_skipped_events", paramInt);
      paramContext = (Context)localObject;
    }
    catch (JSONException paramContext)
    {
      Object localObject;
      label52:
      for (;;) {}
    }
    paramContext = new JSONObject();
    paramGraphRequest.setGraphObject(paramContext);
    localObject = paramGraphRequest.getParameters();
    paramContext = (Context)localObject;
    if (localObject == null) {
      paramContext = new Bundle();
    }
    paramJSONArray = paramJSONArray.toString();
    if (paramJSONArray != null)
    {
      paramContext.putByteArray("custom_events_file", getStringAsByteArray(paramJSONArray));
      paramGraphRequest.setTag(paramJSONArray);
    }
    paramGraphRequest.setParameters(paramContext);
  }
  
  public void accumulatePersistedEvents(List<AppEvent> paramList)
  {
    try
    {
      accumulatedEvents.addAll(paramList);
      return;
    }
    finally
    {
      paramList = finally;
      throw paramList;
    }
  }
  
  public void addEvent(AppEvent paramAppEvent)
  {
    try
    {
      if (accumulatedEvents.size() + inFlightEvents.size() >= 1000) {
        numSkippedEventsDueToFullBuffer += 1;
      } else {
        accumulatedEvents.add(paramAppEvent);
      }
      return;
    }
    finally {}
  }
  
  public void clearInFlightAndStats(boolean paramBoolean)
  {
    if (paramBoolean) {}
    try
    {
      accumulatedEvents.addAll(inFlightEvents);
      inFlightEvents.clear();
      numSkippedEventsDueToFullBuffer = 0;
      return;
    }
    finally
    {
      Object localObject1;
      for (;;) {}
    }
    throw localObject1;
  }
  
  public int getAccumulatedEventCount()
  {
    try
    {
      int i = accumulatedEvents.size();
      return i;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  public List<AppEvent> getEventsToPersist()
  {
    try
    {
      List localList = accumulatedEvents;
      accumulatedEvents = new ArrayList();
      return localList;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  public int populateRequest(GraphRequest paramGraphRequest, Context paramContext, boolean paramBoolean1, boolean paramBoolean2)
  {
    try
    {
      int i = numSkippedEventsDueToFullBuffer;
      inFlightEvents.addAll(accumulatedEvents);
      accumulatedEvents.clear();
      JSONArray localJSONArray = new JSONArray();
      Iterator localIterator = inFlightEvents.iterator();
      while (localIterator.hasNext())
      {
        AppEvent localAppEvent = (AppEvent)localIterator.next();
        if (localAppEvent.isChecksumValid())
        {
          if ((paramBoolean1) || (!localAppEvent.getIsImplicit())) {
            localJSONArray.put(localAppEvent.getJSONObject());
          }
        }
        else {
          Utility.logd("Event with invalid checksum: %s", localAppEvent.toString());
        }
      }
      if (localJSONArray.length() == 0) {
        return 0;
      }
      populateRequest(paramGraphRequest, paramContext, i, localJSONArray, paramBoolean2);
      return localJSONArray.length();
    }
    finally {}
  }
}
