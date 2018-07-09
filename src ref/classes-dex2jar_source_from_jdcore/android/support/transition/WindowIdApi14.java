package android.support.transition;

import android.os.IBinder;
import android.support.annotation.RequiresApi;

@RequiresApi(14)
class WindowIdApi14
  implements WindowIdImpl
{
  private final IBinder mToken;
  
  WindowIdApi14(IBinder paramIBinder)
  {
    mToken = paramIBinder;
  }
  
  public boolean equals(Object paramObject)
  {
    return ((paramObject instanceof WindowIdApi14)) && (mToken.equals(mToken));
  }
  
  public int hashCode()
  {
    return mToken.hashCode();
  }
}
