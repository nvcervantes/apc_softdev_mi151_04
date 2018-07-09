package com.facebook;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public final class CurrentAccessTokenExpirationBroadcastReceiver
  extends BroadcastReceiver
{
  public CurrentAccessTokenExpirationBroadcastReceiver() {}
  
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    if ("com.facebook.sdk.ACTION_CURRENT_ACCESS_TOKEN_CHANGED".equals(paramIntent.getAction())) {
      AccessTokenManager.getInstance().currentAccessTokenChanged();
    }
  }
}
