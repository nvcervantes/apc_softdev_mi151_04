package android.support.transition;

import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.view.ViewGroup;

class ViewGroupUtils
{
  private static final ViewGroupUtilsImpl IMPL = new ViewGroupUtilsApi14();
  
  static
  {
    if (Build.VERSION.SDK_INT >= 18)
    {
      IMPL = new ViewGroupUtilsApi18();
      return;
    }
  }
  
  ViewGroupUtils() {}
  
  static ViewGroupOverlayImpl getOverlay(@NonNull ViewGroup paramViewGroup)
  {
    return IMPL.getOverlay(paramViewGroup);
  }
  
  static void suppressLayout(@NonNull ViewGroup paramViewGroup, boolean paramBoolean)
  {
    IMPL.suppressLayout(paramViewGroup, paramBoolean);
  }
}
