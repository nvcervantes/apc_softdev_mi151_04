package com.byimplication.sakay;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;

@Metadata(bv={1, 0, 2}, d1={"\000\030\n\002\030\002\n\002\020\000\n\000\n\002\020\002\n\000\n\002\020\013\n\002\b\002\bf\030\0002\0020\001J\b\020\002\032\0020\003H\026J\b\020\004\032\0020\005H\026J\b\020\006\032\0020\005H\026¨\006\007"}, d2={"Lcom/byimplication/sakay/SakayContext;", "", "goBackToDefault", "", "hasInternetPermission", "", "hasLocationPermission", "app_release"}, k=1, mv={1, 1, 9})
public abstract interface SakayContext
{
  public abstract void goBackToDefault();
  
  public abstract boolean hasInternetPermission();
  
  public abstract boolean hasLocationPermission();
  
  @Metadata(bv={1, 0, 2}, k=3, mv={1, 1, 9})
  public static final class DefaultImpls
  {
    public static void goBackToDefault(SakayContext paramSakayContext) {}
    
    public static boolean hasInternetPermission(SakayContext paramSakayContext)
    {
      if (paramSakayContext == null) {
        throw new TypeCastException("null cannot be cast to non-null type android.content.Context");
      }
      paramSakayContext = (Context)paramSakayContext;
      List localList = SakayConstants.INSTANCE.getINTERNET_PERMISSIONS();
      boolean bool2 = false;
      boolean bool1 = bool2;
      if (ContextCompat.checkSelfPermission(paramSakayContext, (String)localList.get(0)) == 0)
      {
        bool1 = bool2;
        if (ContextCompat.checkSelfPermission(paramSakayContext, (String)SakayConstants.INSTANCE.getINTERNET_PERMISSIONS().get(1)) == 0) {
          bool1 = true;
        }
      }
      return bool1;
    }
    
    public static boolean hasLocationPermission(SakayContext paramSakayContext)
    {
      if (paramSakayContext == null) {
        throw new TypeCastException("null cannot be cast to non-null type android.content.Context");
      }
      paramSakayContext = (Context)paramSakayContext;
      List localList = SakayConstants.INSTANCE.getLOCATION_PERMISSIONS();
      boolean bool = false;
      if ((ContextCompat.checkSelfPermission(paramSakayContext, (String)localList.get(0)) == 0) || (ContextCompat.checkSelfPermission(paramSakayContext, (String)SakayConstants.INSTANCE.getLOCATION_PERMISSIONS().get(1)) == 0)) {
        bool = true;
      }
      return bool;
    }
  }
}
