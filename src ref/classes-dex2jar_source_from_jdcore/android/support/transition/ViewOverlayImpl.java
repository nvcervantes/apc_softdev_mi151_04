package android.support.transition;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;

@RequiresApi(14)
abstract interface ViewOverlayImpl
{
  public abstract void add(@NonNull Drawable paramDrawable);
  
  public abstract void clear();
  
  public abstract void remove(@NonNull Drawable paramDrawable);
}
