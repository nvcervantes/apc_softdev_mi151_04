package com.byimplication.sakay.components;

import android.os.Bundle;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000\026\n\002\030\002\n\002\020\000\n\000\n\002\020\002\n\000\n\002\030\002\n\000\bf\030\0002\0020\001J\020\020\002\032\0020\0032\006\020\004\032\0020\005H\026¨\006\006"}, d2={"Lcom/byimplication/sakay/components/ActivityGApi;", "", "onCreate", "", "savedInstanceBundle", "Landroid/os/Bundle;", "app_release"}, k=1, mv={1, 1, 9})
public abstract interface ActivityGApi
{
  public abstract void onCreate(@NotNull Bundle paramBundle);
  
  @Metadata(bv={1, 0, 2}, k=3, mv={1, 1, 9})
  public static final class DefaultImpls
  {
    public static void onCreate(ActivityGApi paramActivityGApi, @NotNull Bundle paramBundle)
    {
      Intrinsics.checkParameterIsNotNull(paramBundle, "savedInstanceBundle");
      throw ((Throwable)new NotImplementedError(null, 1, null));
    }
  }
}
