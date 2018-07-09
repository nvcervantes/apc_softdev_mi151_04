package android.support.transition;

import android.graphics.Matrix;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@RequiresApi(21)
class GhostViewApi21
  implements GhostViewImpl
{
  private static final String TAG = "GhostViewApi21";
  private static Method sAddGhostMethod;
  private static boolean sAddGhostMethodFetched;
  private static Class<?> sGhostViewClass;
  private static boolean sGhostViewClassFetched;
  private static Method sRemoveGhostMethod;
  private static boolean sRemoveGhostMethodFetched;
  private final View mGhostView;
  
  private GhostViewApi21(@NonNull View paramView)
  {
    mGhostView = paramView;
  }
  
  private static void fetchAddGhostMethod()
  {
    if (!sAddGhostMethodFetched)
    {
      try
      {
        fetchGhostViewClass();
        sAddGhostMethod = sGhostViewClass.getDeclaredMethod("addGhost", new Class[] { View.class, ViewGroup.class, Matrix.class });
        sAddGhostMethod.setAccessible(true);
      }
      catch (NoSuchMethodException localNoSuchMethodException)
      {
        Log.i("GhostViewApi21", "Failed to retrieve addGhost method", localNoSuchMethodException);
      }
      sAddGhostMethodFetched = true;
    }
  }
  
  private static void fetchGhostViewClass()
  {
    if (!sGhostViewClassFetched)
    {
      try
      {
        sGhostViewClass = Class.forName("android.view.GhostView");
      }
      catch (ClassNotFoundException localClassNotFoundException)
      {
        Log.i("GhostViewApi21", "Failed to retrieve GhostView class", localClassNotFoundException);
      }
      sGhostViewClassFetched = true;
    }
  }
  
  private static void fetchRemoveGhostMethod()
  {
    if (!sRemoveGhostMethodFetched)
    {
      try
      {
        fetchGhostViewClass();
        sRemoveGhostMethod = sGhostViewClass.getDeclaredMethod("removeGhost", new Class[] { View.class });
        sRemoveGhostMethod.setAccessible(true);
      }
      catch (NoSuchMethodException localNoSuchMethodException)
      {
        Log.i("GhostViewApi21", "Failed to retrieve removeGhost method", localNoSuchMethodException);
      }
      sRemoveGhostMethodFetched = true;
    }
  }
  
  public void reserveEndViewTransition(ViewGroup paramViewGroup, View paramView) {}
  
  public void setVisibility(int paramInt)
  {
    mGhostView.setVisibility(paramInt);
  }
  
  static class Creator
    implements GhostViewImpl.Creator
  {
    Creator() {}
    
    public GhostViewImpl addGhost(View paramView, ViewGroup paramViewGroup, Matrix paramMatrix)
    {
      
      if (GhostViewApi21.sAddGhostMethod != null) {}
      try
      {
        paramView = new GhostViewApi21((View)GhostViewApi21.sAddGhostMethod.invoke(null, new Object[] { paramView, paramViewGroup, paramMatrix }), null);
        return paramView;
      }
      catch (InvocationTargetException paramView)
      {
        throw new RuntimeException(paramView.getCause());
        return null;
      }
      catch (IllegalAccessException paramView) {}
      return null;
    }
    
    public void removeGhost(View paramView)
    {
      
      if (GhostViewApi21.sRemoveGhostMethod != null) {}
      try
      {
        GhostViewApi21.sRemoveGhostMethod.invoke(null, new Object[] { paramView });
        return;
      }
      catch (InvocationTargetException paramView)
      {
        throw new RuntimeException(paramView.getCause());
        return;
      }
      catch (IllegalAccessException paramView) {}
    }
  }
}
