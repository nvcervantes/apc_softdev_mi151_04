package android.arch.lifecycle;

import android.support.annotation.Nullable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

class ClassesInfoCache
{
  private static final int CALL_TYPE_NO_ARG = 0;
  private static final int CALL_TYPE_PROVIDER = 1;
  private static final int CALL_TYPE_PROVIDER_WITH_EVENT = 2;
  static ClassesInfoCache sInstance = new ClassesInfoCache();
  private final Map<Class, CallbackInfo> mCallbackMap = new HashMap();
  private final Map<Class, Boolean> mHasLifecycleMethods = new HashMap();
  
  ClassesInfoCache() {}
  
  private CallbackInfo createInfo(Class paramClass, @Nullable Method[] paramArrayOfMethod)
  {
    throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge Z and I\r\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.copyTypes(TypeTransformer.java:311)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.fixTypes(TypeTransformer.java:226)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:207)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\r\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\r\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\r\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\r\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\r\n");
  }
  
  private Method[] getDeclaredMethods(Class paramClass)
  {
    try
    {
      paramClass = paramClass.getDeclaredMethods();
      return paramClass;
    }
    catch (NoClassDefFoundError paramClass)
    {
      throw new IllegalArgumentException("The observer class has some methods that use newer APIs which are not available in the current OS version. Lifecycles cannot access even other methods so you should make sure that your observer classes only access framework classes that are available in your min API level OR use lifecycle:compiler annotation processor.", paramClass);
    }
  }
  
  private void verifyAndPutHandler(Map<MethodReference, Lifecycle.Event> paramMap, MethodReference paramMethodReference, Lifecycle.Event paramEvent, Class paramClass)
  {
    Lifecycle.Event localEvent = (Lifecycle.Event)paramMap.get(paramMethodReference);
    if ((localEvent != null) && (paramEvent != localEvent))
    {
      paramMap = mMethod;
      paramMethodReference = new StringBuilder();
      paramMethodReference.append("Method ");
      paramMethodReference.append(paramMap.getName());
      paramMethodReference.append(" in ");
      paramMethodReference.append(paramClass.getName());
      paramMethodReference.append(" already declared with different @OnLifecycleEvent value: previous");
      paramMethodReference.append(" value ");
      paramMethodReference.append(localEvent);
      paramMethodReference.append(", new value ");
      paramMethodReference.append(paramEvent);
      throw new IllegalArgumentException(paramMethodReference.toString());
    }
    if (localEvent == null) {
      paramMap.put(paramMethodReference, paramEvent);
    }
  }
  
  CallbackInfo getInfo(Class paramClass)
  {
    CallbackInfo localCallbackInfo = (CallbackInfo)mCallbackMap.get(paramClass);
    if (localCallbackInfo != null) {
      return localCallbackInfo;
    }
    return createInfo(paramClass, null);
  }
  
  boolean hasLifecycleMethods(Class paramClass)
  {
    if (mHasLifecycleMethods.containsKey(paramClass)) {
      return ((Boolean)mHasLifecycleMethods.get(paramClass)).booleanValue();
    }
    Method[] arrayOfMethod = getDeclaredMethods(paramClass);
    int j = arrayOfMethod.length;
    int i = 0;
    while (i < j)
    {
      if ((OnLifecycleEvent)arrayOfMethod[i].getAnnotation(OnLifecycleEvent.class) != null)
      {
        createInfo(paramClass, arrayOfMethod);
        return true;
      }
      i += 1;
    }
    mHasLifecycleMethods.put(paramClass, Boolean.valueOf(false));
    return false;
  }
  
  static class CallbackInfo
  {
    final Map<Lifecycle.Event, List<ClassesInfoCache.MethodReference>> mEventToHandlers;
    final Map<ClassesInfoCache.MethodReference, Lifecycle.Event> mHandlerToEvent;
    
    CallbackInfo(Map<ClassesInfoCache.MethodReference, Lifecycle.Event> paramMap)
    {
      mHandlerToEvent = paramMap;
      mEventToHandlers = new HashMap();
      Iterator localIterator = paramMap.entrySet().iterator();
      while (localIterator.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        Lifecycle.Event localEvent = (Lifecycle.Event)localEntry.getValue();
        List localList = (List)mEventToHandlers.get(localEvent);
        paramMap = localList;
        if (localList == null)
        {
          paramMap = new ArrayList();
          mEventToHandlers.put(localEvent, paramMap);
        }
        paramMap.add(localEntry.getKey());
      }
    }
    
    private static void invokeMethodsForEvent(List<ClassesInfoCache.MethodReference> paramList, LifecycleOwner paramLifecycleOwner, Lifecycle.Event paramEvent, Object paramObject)
    {
      if (paramList != null)
      {
        int i = paramList.size() - 1;
        while (i >= 0)
        {
          ((ClassesInfoCache.MethodReference)paramList.get(i)).invokeCallback(paramLifecycleOwner, paramEvent, paramObject);
          i -= 1;
        }
      }
    }
    
    void invokeCallbacks(LifecycleOwner paramLifecycleOwner, Lifecycle.Event paramEvent, Object paramObject)
    {
      invokeMethodsForEvent((List)mEventToHandlers.get(paramEvent), paramLifecycleOwner, paramEvent, paramObject);
      invokeMethodsForEvent((List)mEventToHandlers.get(Lifecycle.Event.ON_ANY), paramLifecycleOwner, paramEvent, paramObject);
    }
  }
  
  static class MethodReference
  {
    final int mCallType;
    final Method mMethod;
    
    MethodReference(int paramInt, Method paramMethod)
    {
      mCallType = paramInt;
      mMethod = paramMethod;
      mMethod.setAccessible(true);
    }
    
    public boolean equals(Object paramObject)
    {
      if (this == paramObject) {
        return true;
      }
      if (paramObject != null)
      {
        if (getClass() != paramObject.getClass()) {
          return false;
        }
        paramObject = (MethodReference)paramObject;
        return (mCallType == mCallType) && (mMethod.getName().equals(mMethod.getName()));
      }
      return false;
    }
    
    public int hashCode()
    {
      return 31 * mCallType + mMethod.getName().hashCode();
    }
    
    void invokeCallback(LifecycleOwner paramLifecycleOwner, Lifecycle.Event paramEvent, Object paramObject)
    {
      try
      {
        switch (mCallType)
        {
        case 2: 
          mMethod.invoke(paramObject, new Object[] { paramLifecycleOwner, paramEvent });
          return;
        }
      }
      catch (IllegalAccessException paramLifecycleOwner)
      {
        throw new RuntimeException(paramLifecycleOwner);
      }
      catch (InvocationTargetException paramLifecycleOwner)
      {
        throw new RuntimeException("Failed to call observer method", paramLifecycleOwner.getCause());
      }
      mMethod.invoke(paramObject, new Object[] { paramLifecycleOwner });
      return;
      mMethod.invoke(paramObject, new Object[0]);
      return;
    }
  }
}
