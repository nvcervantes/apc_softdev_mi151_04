package android.support.transition;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.ViewOverlay;

@RequiresApi(18)
class ViewOverlayApi18
  implements ViewOverlayImpl
{
  private final ViewOverlay mViewOverlay;
  
  ViewOverlayApi18(@NonNull View paramView)
  {
    mViewOverlay = paramView.getOverlay();
  }
  
  public void add(@NonNull Drawable paramDrawable)
  {
    mViewOverlay.add(paramDrawable);
  }
  
  public void clear()
  {
    mViewOverlay.clear();
  }
  
  public void remove(@NonNull Drawable paramDrawable)
  {
    mViewOverlay.remove(paramDrawable);
  }
}
