package com.google.android.gms.auth.api.signin;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.Hide;
import java.util.List;

public abstract interface GoogleSignInOptionsExtension
{
  @Hide
  public abstract int getExtensionType();
  
  @Nullable
  @Hide
  public abstract List<Scope> getImpliedScopes();
  
  @Hide
  public abstract Bundle toBundle();
}
