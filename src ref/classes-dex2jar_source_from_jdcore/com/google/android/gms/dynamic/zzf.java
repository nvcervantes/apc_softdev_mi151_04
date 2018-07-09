package com.google.android.gms.dynamic;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

final class zzf
  implements View.OnClickListener
{
  zzf(Context paramContext, Intent paramIntent) {}
  
  public final void onClick(View paramView)
  {
    try
    {
      zza.startActivity(zzb);
      return;
    }
    catch (ActivityNotFoundException paramView)
    {
      Log.e("DeferredLifecycleHelper", "Failed to start resolution intent", paramView);
    }
  }
}
