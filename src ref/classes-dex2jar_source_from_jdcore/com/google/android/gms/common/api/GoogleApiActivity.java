package com.google.android.gms.common.api;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.content.IntentSender.SendIntentException;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.internal.zzbm;
import com.google.android.gms.common.internal.Hide;

public class GoogleApiActivity
  extends Activity
  implements DialogInterface.OnCancelListener
{
  @Hide
  private int zza = 0;
  
  @Hide
  public GoogleApiActivity() {}
  
  @Hide
  public static PendingIntent zza(Context paramContext, PendingIntent paramPendingIntent, int paramInt)
  {
    return PendingIntent.getActivity(paramContext, 0, zza(paramContext, paramPendingIntent, paramInt, true), 134217728);
  }
  
  @Hide
  public static Intent zza(Context paramContext, PendingIntent paramPendingIntent, int paramInt, boolean paramBoolean)
  {
    paramContext = new Intent(paramContext, GoogleApiActivity.class);
    paramContext.putExtra("pending_intent", paramPendingIntent);
    paramContext.putExtra("failing_client_id", paramInt);
    paramContext.putExtra("notify_manager", paramBoolean);
    return paramContext;
  }
  
  @Hide
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (paramInt1 == 1)
    {
      boolean bool = getIntent().getBooleanExtra("notify_manager", true);
      zza = 0;
      setResult(paramInt2, paramIntent);
      if (bool)
      {
        paramIntent = zzbm.zza(this);
        switch (paramInt2)
        {
        default: 
          break;
        case 0: 
          paramIntent.zzb(new ConnectionResult(13, null), getIntent().getIntExtra("failing_client_id", -1));
          break;
        case -1: 
          paramIntent.zzd();
          break;
        }
      }
    }
    else if (paramInt1 == 2)
    {
      zza = 0;
      setResult(paramInt2, paramIntent);
    }
    finish();
  }
  
  @Hide
  public void onCancel(DialogInterface paramDialogInterface)
  {
    zza = 0;
    setResult(0);
    finish();
  }
  
  @Hide
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    if (paramBundle != null) {
      zza = paramBundle.getInt("resolution");
    }
    if (zza != 1)
    {
      Object localObject = getIntent().getExtras();
      if (localObject == null)
      {
        paramBundle = "Activity started without extras";
        Log.e("GoogleApiActivity", paramBundle);
      }
      for (;;)
      {
        finish();
        return;
        paramBundle = (PendingIntent)((Bundle)localObject).get("pending_intent");
        localObject = (Integer)((Bundle)localObject).get("error_code");
        if ((paramBundle == null) && (localObject == null))
        {
          paramBundle = "Activity started without resolution";
          break;
        }
        if (paramBundle == null) {
          break label124;
        }
        try
        {
          startIntentSenderForResult(paramBundle.getIntentSender(), 1, null, 0, 0, 0);
          zza = 1;
          return;
        }
        catch (IntentSender.SendIntentException paramBundle)
        {
          Log.e("GoogleApiActivity", "Failed to launch pendingIntent", paramBundle);
        }
      }
      label124:
      GoogleApiAvailability.getInstance().showErrorDialogFragment(this, ((Integer)localObject).intValue(), 2, this);
      zza = 1;
    }
  }
  
  @Hide
  protected void onSaveInstanceState(Bundle paramBundle)
  {
    paramBundle.putInt("resolution", zza);
    super.onSaveInstanceState(paramBundle);
  }
}
