package com.byimplication.sakay.store;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000&\n\002\030\002\n\002\030\002\n\000\n\002\020\013\n\000\n\002\020\016\n\002\b\n\n\002\020\000\n\000\n\002\020\b\n\002\b\002\b\b\030\0002\0020\001B\031\022\006\020\002\032\0020\003\022\n\b\002\020\004\032\004\030\0010\005¢\006\002\020\006J\n\020\n\032\004\030\0010\001H\026J\t\020\013\032\0020\003HÆ\003J\013\020\f\032\004\030\0010\005HÆ\003J\037\020\r\032\0020\0002\b\b\002\020\002\032\0020\0032\n\b\002\020\004\032\004\030\0010\005HÆ\001J\023\020\016\032\0020\0032\b\020\017\032\004\030\0010\020HÖ\003J\t\020\021\032\0020\022HÖ\001J\t\020\023\032\0020\005HÖ\001R\023\020\004\032\004\030\0010\005¢\006\b\n\000\032\004\b\007\020\bR\021\020\002\032\0020\003¢\006\b\n\000\032\004\b\002\020\t¨\006\024"}, d2={"Lcom/byimplication/sakay/store/GoogleApiStatusUpdate;", "Lcom/byimplication/sakay/store/StoreData;", "isConnected", "", "errorCode", "", "(ZLjava/lang/String;)V", "getErrorCode", "()Ljava/lang/String;", "()Z", "clone", "component1", "component2", "copy", "equals", "other", "", "hashCode", "", "toString", "app_release"}, k=1, mv={1, 1, 9})
public final class GoogleApiStatusUpdate
  implements StoreData
{
  @Nullable
  private final String errorCode;
  private final boolean isConnected;
  
  public GoogleApiStatusUpdate(boolean paramBoolean, @Nullable String paramString)
  {
    isConnected = paramBoolean;
    errorCode = paramString;
  }
  
  @Nullable
  public StoreData clone()
  {
    return (StoreData)copy$default(this, false, null, 3, null);
  }
  
  public final boolean component1()
  {
    return isConnected;
  }
  
  @Nullable
  public final String component2()
  {
    return errorCode;
  }
  
  @NotNull
  public final GoogleApiStatusUpdate copy(boolean paramBoolean, @Nullable String paramString)
  {
    return new GoogleApiStatusUpdate(paramBoolean, paramString);
  }
  
  public boolean equals(Object paramObject)
  {
    if (this != paramObject)
    {
      if ((paramObject instanceof GoogleApiStatusUpdate))
      {
        paramObject = (GoogleApiStatusUpdate)paramObject;
        int i;
        if (isConnected == isConnected) {
          i = 1;
        } else {
          i = 0;
        }
        if ((i != 0) && (Intrinsics.areEqual(errorCode, errorCode))) {
          return true;
        }
      }
      return false;
    }
    return true;
  }
  
  @Nullable
  public final String getErrorCode()
  {
    return errorCode;
  }
  
  public int hashCode()
  {
    throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\r\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.copyTypes(TypeTransformer.java:311)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.fixTypes(TypeTransformer.java:226)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:207)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\r\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\r\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\r\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\r\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\r\n");
  }
  
  public final boolean isConnected()
  {
    return isConnected;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("GoogleApiStatusUpdate(isConnected=");
    localStringBuilder.append(isConnected);
    localStringBuilder.append(", errorCode=");
    localStringBuilder.append(errorCode);
    localStringBuilder.append(")");
    return localStringBuilder.toString();
  }
}
