package android.support.transition;

import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.view.ViewGroup;

@RequiresApi(14)
abstract interface ViewGroupUtilsImpl
{
  public abstract ViewGroupOverlayImpl getOverlay(@NonNull ViewGroup paramViewGroup);
  
  public abstract void suppressLayout(@NonNull ViewGroup paramViewGroup, boolean paramBoolean);
}
