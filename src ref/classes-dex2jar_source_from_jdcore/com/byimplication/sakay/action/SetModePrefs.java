package com.byimplication.sakay.action;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000*\n\002\030\002\n\002\030\002\n\000\n\002\020\021\n\002\020\016\n\000\n\002\020\013\n\002\b\f\n\002\020\000\n\000\n\002\020\b\n\002\b\002\b\b\030\0002\0020\001B\035\022\f\020\002\032\b\022\004\022\0020\0040\003\022\b\b\002\020\005\032\0020\006¢\006\002\020\007J\024\020\r\032\b\022\004\022\0020\0040\003HÆ\003¢\006\002\020\tJ\t\020\016\032\0020\006HÆ\003J(\020\017\032\0020\0002\016\b\002\020\002\032\b\022\004\022\0020\0040\0032\b\b\002\020\005\032\0020\006HÆ\001¢\006\002\020\020J\023\020\021\032\0020\0062\b\020\022\032\004\030\0010\023HÖ\003J\t\020\024\032\0020\025HÖ\001J\t\020\026\032\0020\004HÖ\001R\031\020\002\032\b\022\004\022\0020\0040\003¢\006\n\n\002\020\n\032\004\b\b\020\tR\021\020\005\032\0020\006¢\006\b\n\000\032\004\b\013\020\f¨\006\027"}, d2={"Lcom/byimplication/sakay/action/SetModePrefs;", "Lcom/byimplication/sakay/action/Action;", "modePrefs", "", "", "shouldUpdate", "", "([Ljava/lang/String;Z)V", "getModePrefs", "()[Ljava/lang/String;", "[Ljava/lang/String;", "getShouldUpdate", "()Z", "component1", "component2", "copy", "([Ljava/lang/String;Z)Lcom/byimplication/sakay/action/SetModePrefs;", "equals", "other", "", "hashCode", "", "toString", "app_release"}, k=1, mv={1, 1, 9})
public final class SetModePrefs
  implements Action
{
  @NotNull
  private final String[] modePrefs;
  private final boolean shouldUpdate;
  
  public SetModePrefs(@NotNull String[] paramArrayOfString, boolean paramBoolean)
  {
    modePrefs = paramArrayOfString;
    shouldUpdate = paramBoolean;
  }
  
  @NotNull
  public final String[] component1()
  {
    return modePrefs;
  }
  
  public final boolean component2()
  {
    return shouldUpdate;
  }
  
  @NotNull
  public final SetModePrefs copy(@NotNull String[] paramArrayOfString, boolean paramBoolean)
  {
    Intrinsics.checkParameterIsNotNull(paramArrayOfString, "modePrefs");
    return new SetModePrefs(paramArrayOfString, paramBoolean);
  }
  
  public boolean equals(Object paramObject)
  {
    if (this != paramObject)
    {
      if ((paramObject instanceof SetModePrefs))
      {
        paramObject = (SetModePrefs)paramObject;
        if (Intrinsics.areEqual(modePrefs, modePrefs))
        {
          int i;
          if (shouldUpdate == shouldUpdate) {
            i = 1;
          } else {
            i = 0;
          }
          if (i != 0) {
            return true;
          }
        }
      }
      return false;
    }
    return true;
  }
  
  @NotNull
  public final String[] getModePrefs()
  {
    return modePrefs;
  }
  
  public final boolean getShouldUpdate()
  {
    return shouldUpdate;
  }
  
  public int hashCode()
  {
    throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\r\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.copyTypes(TypeTransformer.java:311)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.fixTypes(TypeTransformer.java:226)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:207)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\r\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\r\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\r\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\r\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\r\n");
  }
  
  public void post()
  {
    Action.DefaultImpls.post(this);
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("SetModePrefs(modePrefs=");
    localStringBuilder.append(Arrays.toString(modePrefs));
    localStringBuilder.append(", shouldUpdate=");
    localStringBuilder.append(shouldUpdate);
    localStringBuilder.append(")");
    return localStringBuilder.toString();
  }
}
