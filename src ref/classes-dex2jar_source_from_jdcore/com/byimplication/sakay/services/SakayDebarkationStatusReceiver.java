package com.byimplication.sakay.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000&\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\002\b\004\n\002\020\002\n\000\n\002\030\002\n\000\n\002\030\002\n\002\b\002\030\0002\0020\001:\001\rB\r\022\006\020\002\032\0020\003¢\006\002\020\004J\034\020\007\032\0020\b2\b\020\t\032\004\030\0010\n2\b\020\013\032\004\030\0010\fH\026R\021\020\002\032\0020\003¢\006\b\n\000\032\004\b\005\020\006¨\006\016"}, d2={"Lcom/byimplication/sakay/services/SakayDebarkationStatusReceiver;", "Landroid/content/BroadcastReceiver;", "listener", "Lcom/byimplication/sakay/services/SakayDebarkationStatusReceiver$OnReceiveListener;", "(Lcom/byimplication/sakay/services/SakayDebarkationStatusReceiver$OnReceiveListener;)V", "getListener", "()Lcom/byimplication/sakay/services/SakayDebarkationStatusReceiver$OnReceiveListener;", "onReceive", "", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "OnReceiveListener", "app_release"}, k=1, mv={1, 1, 9})
public final class SakayDebarkationStatusReceiver
  extends BroadcastReceiver
{
  @NotNull
  private final OnReceiveListener listener;
  
  public SakayDebarkationStatusReceiver(@NotNull OnReceiveListener paramOnReceiveListener)
  {
    listener = paramOnReceiveListener;
  }
  
  @NotNull
  public final OnReceiveListener getListener()
  {
    return listener;
  }
  
  public void onReceive(@Nullable Context paramContext, @Nullable Intent paramIntent)
  {
    Log.d("SAKAY", "SakayDebarkationStatusReceiver::onReceive");
    if (paramIntent != null)
    {
      Log.d("SAKAY", "SakayDebarkationStatusReceiver::onReceive intent is nat null");
      boolean bool = paramIntent.getExtras().getBoolean("isOn", true);
      listener.onSakayDebarkationStatusReceive(bool);
    }
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\000\026\n\002\030\002\n\002\020\000\n\000\n\002\020\002\n\000\n\002\020\013\n\000\bf\030\0002\0020\001J\020\020\002\032\0020\0032\006\020\004\032\0020\005H&¨\006\006"}, d2={"Lcom/byimplication/sakay/services/SakayDebarkationStatusReceiver$OnReceiveListener;", "", "onSakayDebarkationStatusReceive", "", "isOn", "", "app_release"}, k=1, mv={1, 1, 9})
  public static abstract interface OnReceiveListener
  {
    public abstract void onSakayDebarkationStatusReceive(boolean paramBoolean);
  }
}
