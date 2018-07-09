package com.facebook.appevents;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

class PersistedEvents
  implements Serializable
{
  private static final long serialVersionUID = 20160629001L;
  private HashMap<AccessTokenAppIdPair, List<AppEvent>> events = new HashMap();
  
  public PersistedEvents() {}
  
  public PersistedEvents(HashMap<AccessTokenAppIdPair, List<AppEvent>> paramHashMap)
  {
    events.putAll(paramHashMap);
  }
  
  private Object writeReplace()
  {
    return new SerializationProxyV1(events, null);
  }
  
  public void addEvents(AccessTokenAppIdPair paramAccessTokenAppIdPair, List<AppEvent> paramList)
  {
    if (!events.containsKey(paramAccessTokenAppIdPair))
    {
      events.put(paramAccessTokenAppIdPair, paramList);
      return;
    }
    ((List)events.get(paramAccessTokenAppIdPair)).addAll(paramList);
  }
  
  public boolean containsKey(AccessTokenAppIdPair paramAccessTokenAppIdPair)
  {
    return events.containsKey(paramAccessTokenAppIdPair);
  }
  
  public List<AppEvent> get(AccessTokenAppIdPair paramAccessTokenAppIdPair)
  {
    return (List)events.get(paramAccessTokenAppIdPair);
  }
  
  public Set<AccessTokenAppIdPair> keySet()
  {
    return events.keySet();
  }
  
  static class SerializationProxyV1
    implements Serializable
  {
    private static final long serialVersionUID = 20160629001L;
    private final HashMap<AccessTokenAppIdPair, List<AppEvent>> proxyEvents;
    
    private SerializationProxyV1(HashMap<AccessTokenAppIdPair, List<AppEvent>> paramHashMap)
    {
      proxyEvents = paramHashMap;
    }
    
    private Object readResolve()
    {
      return new PersistedEvents(proxyEvents);
    }
  }
}
