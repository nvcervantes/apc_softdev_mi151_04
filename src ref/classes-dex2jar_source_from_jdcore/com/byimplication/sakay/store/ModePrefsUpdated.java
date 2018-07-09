package com.byimplication.sakay.store;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000*\n\002\030\002\n\002\030\002\n\000\n\002\020\021\n\002\020\016\n\000\n\002\020\013\n\002\b\f\n\002\020\000\n\000\n\002\020\b\n\002\b\002\b\b\030\0002\0020\001B\035\022\f\020\002\032\b\022\004\022\0020\0040\003\022\b\b\002\020\005\032\0020\006¢\006\002\020\007J\n\020\f\032\004\030\0010\001H\026J\024\020\r\032\b\022\004\022\0020\0040\003HÆ\003¢\006\002\020\nJ\t\020\016\032\0020\006HÆ\003J(\020\017\032\0020\0002\016\b\002\020\002\032\b\022\004\022\0020\0040\0032\b\b\002\020\005\032\0020\006HÆ\001¢\006\002\020\020J\023\020\021\032\0020\0062\b\020\022\032\004\030\0010\023HÖ\003J\t\020\024\032\0020\025HÖ\001J\t\020\026\032\0020\004HÖ\001R\021\020\005\032\0020\006¢\006\b\n\000\032\004\b\005\020\bR\031\020\002\032\b\022\004\022\0020\0040\003¢\006\n\n\002\020\013\032\004\b\t\020\n¨\006\027"}, d2={"Lcom/byimplication/sakay/store/ModePrefsUpdated;", "Lcom/byimplication/sakay/store/StoreData;", "modePrefs", "", "", "isRecall", "", "([Ljava/lang/String;Z)V", "()Z", "getModePrefs", "()[Ljava/lang/String;", "[Ljava/lang/String;", "clone", "component1", "component2", "copy", "([Ljava/lang/String;Z)Lcom/byimplication/sakay/store/ModePrefsUpdated;", "equals", "other", "", "hashCode", "", "toString", "app_release"}, k=1, mv={1, 1, 9})
public final class ModePrefsUpdated
  implements StoreData
{
  private final boolean isRecall;
  @NotNull
  private final String[] modePrefs;
  
  public ModePrefsUpdated(@NotNull String[] paramArrayOfString, boolean paramBoolean)
  {
    modePrefs = paramArrayOfString;
    isRecall = paramBoolean;
  }
  
  @Nullable
  public StoreData clone()
  {
    return (StoreData)copy$default(this, (String[])((Object[])modePrefs).clone(), false, 2, null);
  }
  
  @NotNull
  public final String[] component1()
  {
    return modePrefs;
  }
  
  public final boolean component2()
  {
    return isRecall;
  }
  
  @NotNull
  public final ModePrefsUpdated copy(@NotNull String[] paramArrayOfString, boolean paramBoolean)
  {
    Intrinsics.checkParameterIsNotNull(paramArrayOfString, "modePrefs");
    return new ModePrefsUpdated(paramArrayOfString, paramBoolean);
  }
  
  public boolean equals(Object paramObject)
  {
    if (this != paramObject)
    {
      if ((paramObject instanceof ModePrefsUpdated))
      {
        paramObject = (ModePrefsUpdated)paramObject;
        if (Intrinsics.areEqual(modePrefs, modePrefs))
        {
          int i;
          if (isRecall == isRecall) {
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
  
  public int hashCode()
  {
    throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\r\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.copyTypes(TypeTransformer.java:311)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.fixTypes(TypeTransformer.java:226)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:207)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\r\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\r\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\r\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\r\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\r\n");
  }
  
  public final boolean isRecall()
  {
    return isRecall;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("ModePrefsUpdated(modePrefs=");
    localStringBuilder.append(Arrays.toString(modePrefs));
    localStringBuilder.append(", isRecall=");
    localStringBuilder.append(isRecall);
    localStringBuilder.append(")");
    return localStringBuilder.toString();
  }
}
