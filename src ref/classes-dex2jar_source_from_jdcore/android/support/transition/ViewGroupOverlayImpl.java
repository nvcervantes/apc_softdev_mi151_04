package android.support.transition;

import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.view.View;

@RequiresApi(14)
abstract interface ViewGroupOverlayImpl
  extends ViewOverlayImpl
{
  public abstract void add(@NonNull View paramView);
  
  public abstract void remove(@NonNull View paramView);
}
