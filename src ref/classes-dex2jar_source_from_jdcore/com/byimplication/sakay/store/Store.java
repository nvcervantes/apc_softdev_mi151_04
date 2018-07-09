package com.byimplication.sakay.store;

import android.os.Bundle;
import com.byimplication.sakay.action.Action;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000 \n\002\030\002\n\002\020\000\n\000\n\002\020\002\n\000\n\002\030\002\n\002\b\004\n\002\030\002\n\002\b\002\bf\030\0002\0020\001J\022\020\002\032\0020\0032\b\020\004\032\004\030\0010\005H\026J\020\020\006\032\0020\0032\006\020\007\032\0020\005H&J\020\020\b\032\0020\0032\006\020\t\032\0020\nH&J\020\020\013\032\0020\0032\006\020\004\032\0020\005H&¨\006\f"}, d2={"Lcom/byimplication/sakay/store/Store;", "", "init", "", "savedInstanceState", "Landroid/os/Bundle;", "onSaveInstanceState", "outState", "receive", "stuff", "Lcom/byimplication/sakay/action/Action;", "restoreInstanceState", "app_release"}, k=1, mv={1, 1, 9})
public abstract interface Store
{
  public abstract void init(@Nullable Bundle paramBundle);
  
  public abstract void onSaveInstanceState(@NotNull Bundle paramBundle);
  
  public abstract void receive(@NotNull Action paramAction);
  
  public abstract void restoreInstanceState(@NotNull Bundle paramBundle);
  
  @Metadata(bv={1, 0, 2}, k=3, mv={1, 1, 9})
  public static final class DefaultImpls
  {
    public static void init(Store paramStore, @Nullable Bundle paramBundle)
    {
      Dispatcher.INSTANCE.register(paramStore);
      if (paramBundle != null) {
        paramStore.restoreInstanceState(paramBundle);
      }
    }
  }
}
