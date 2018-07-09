package com.byimplication.sakay.store;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000\022\n\002\030\002\n\002\020\000\n\000\n\002\020\002\n\002\b\005\bf\030\0002\0020\001J\b\020\002\032\0020\003H\026J\b\020\004\032\0020\003H\026J\032\020\005\032\0020\0032\006\020\006\032\0020\0012\b\020\007\032\004\030\0010\001H&¨\006\b"}, d2={"Lcom/byimplication/sakay/store/Observer;", "", "onDestroy", "", "onStart", "onStoreChange", "newValue", "oldValue", "app_release"}, k=1, mv={1, 1, 9})
public abstract interface Observer
{
  public abstract void onDestroy();
  
  public abstract void onStart();
  
  public abstract void onStoreChange(@NotNull Object paramObject1, @Nullable Object paramObject2);
  
  @Metadata(bv={1, 0, 2}, k=3, mv={1, 1, 9})
  public static final class DefaultImpls
  {
    public static void onDestroy(Observer paramObserver)
    {
      Dispatcher.INSTANCE.unregister(paramObserver);
    }
    
    public static void onStart(Observer paramObserver)
    {
      Dispatcher.INSTANCE.register(paramObserver);
    }
  }
}
