package android.support.transition;

import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.view.View;

@RequiresApi(18)
class ViewUtilsApi18
  extends ViewUtilsApi14
{
  ViewUtilsApi18() {}
  
  public ViewOverlayImpl getOverlay(@NonNull View paramView)
  {
    return new ViewOverlayApi18(paramView);
  }
  
  public WindowIdImpl getWindowId(@NonNull View paramView)
  {
    return new WindowIdApi18(paramView);
  }
}
