package com.facebook;

import android.content.Intent;
import com.facebook.internal.CallbackManagerImpl;

public abstract interface CallbackManager
{
  public abstract boolean onActivityResult(int paramInt1, int paramInt2, Intent paramIntent);
  
  public static class Factory
  {
    public Factory() {}
    
    public static CallbackManager create()
    {
      return new CallbackManagerImpl();
    }
  }
}
