package com.facebook.appevents;

import android.content.Context;
import com.facebook.FacebookSdk;
import com.facebook.internal.AttributionIdentifiers;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

class AppEventCollection
{
  private final HashMap<AccessTokenAppIdPair, SessionEventsState> stateMap = new HashMap();
  
  public AppEventCollection() {}
  
  private SessionEventsState getSessionEventsState(AccessTokenAppIdPair paramAccessTokenAppIdPair)
  {
    try
    {
      SessionEventsState localSessionEventsState = (SessionEventsState)stateMap.get(paramAccessTokenAppIdPair);
      Object localObject = localSessionEventsState;
      if (localSessionEventsState == null)
      {
        localObject = FacebookSdk.getApplicationContext();
        localObject = new SessionEventsState(AttributionIdentifiers.getAttributionIdentifiers((Context)localObject), AppEventsLogger.getAnonymousAppDeviceGUID((Context)localObject));
      }
      stateMap.put(paramAccessTokenAppIdPair, localObject);
      return localObject;
    }
    finally {}
  }
  
  public void addEvent(AccessTokenAppIdPair paramAccessTokenAppIdPair, AppEvent paramAppEvent)
  {
    try
    {
      getSessionEventsState(paramAccessTokenAppIdPair).addEvent(paramAppEvent);
      return;
    }
    finally
    {
      paramAccessTokenAppIdPair = finally;
      throw paramAccessTokenAppIdPair;
    }
  }
  
  public void addPersistedEvents(PersistedEvents paramPersistedEvents)
  {
    if (paramPersistedEvents == null) {
      return;
    }
    try
    {
      Iterator localIterator = paramPersistedEvents.keySet().iterator();
      while (localIterator.hasNext())
      {
        Object localObject = (AccessTokenAppIdPair)localIterator.next();
        SessionEventsState localSessionEventsState = getSessionEventsState((AccessTokenAppIdPair)localObject);
        localObject = paramPersistedEvents.get((AccessTokenAppIdPair)localObject).iterator();
        while (((Iterator)localObject).hasNext()) {
          localSessionEventsState.addEvent((AppEvent)((Iterator)localObject).next());
        }
      }
      return;
    }
    finally {}
  }
  
  public SessionEventsState get(AccessTokenAppIdPair paramAccessTokenAppIdPair)
  {
    try
    {
      paramAccessTokenAppIdPair = (SessionEventsState)stateMap.get(paramAccessTokenAppIdPair);
      return paramAccessTokenAppIdPair;
    }
    finally
    {
      paramAccessTokenAppIdPair = finally;
      throw paramAccessTokenAppIdPair;
    }
  }
  
  public int getEventCount()
  {
    int i = 0;
    try
    {
      Iterator localIterator = stateMap.values().iterator();
      while (localIterator.hasNext())
      {
        int j = ((SessionEventsState)localIterator.next()).getAccumulatedEventCount();
        i += j;
      }
      return i;
    }
    finally {}
  }
  
  public Set<AccessTokenAppIdPair> keySet()
  {
    try
    {
      Set localSet = stateMap.keySet();
      return localSet;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
}
