package android.support.transition;

import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.WindowId;

@RequiresApi(18)
class WindowIdApi18
  implements WindowIdImpl
{
  private final WindowId mWindowId;
  
  WindowIdApi18(@NonNull View paramView)
  {
    mWindowId = paramView.getWindowId();
  }
  
  public boolean equals(Object paramObject)
  {
    return ((paramObject instanceof WindowIdApi18)) && (mWindowId.equals(mWindowId));
  }
  
  public int hashCode()
  {
    return mWindowId.hashCode();
  }
}
