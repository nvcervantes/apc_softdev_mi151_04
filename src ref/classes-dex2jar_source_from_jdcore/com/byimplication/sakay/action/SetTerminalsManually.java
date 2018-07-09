package com.byimplication.sakay.action;

import com.byimplication.sakay.model.Terminal;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000,\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\002\b\002\n\002\020\013\n\002\b\r\n\002\020\000\n\000\n\002\020\b\n\000\n\002\020\016\n\000\b\b\030\0002\0020\001B)\022\006\020\002\032\0020\003\022\006\020\004\032\0020\003\022\b\b\002\020\005\032\0020\006\022\b\b\002\020\007\032\0020\006¢\006\002\020\bJ\t\020\r\032\0020\003HÆ\003J\t\020\016\032\0020\003HÆ\003J\t\020\017\032\0020\006HÆ\003J\t\020\020\032\0020\006HÆ\003J1\020\021\032\0020\0002\b\b\002\020\002\032\0020\0032\b\b\002\020\004\032\0020\0032\b\b\002\020\005\032\0020\0062\b\b\002\020\007\032\0020\006HÆ\001J\023\020\022\032\0020\0062\b\020\023\032\004\030\0010\024HÖ\003J\t\020\025\032\0020\026HÖ\001J\t\020\027\032\0020\030HÖ\001R\021\020\004\032\0020\003¢\006\b\n\000\032\004\b\t\020\nR\021\020\007\032\0020\006¢\006\b\n\000\032\004\b\007\020\013R\021\020\005\032\0020\006¢\006\b\n\000\032\004\b\005\020\013R\021\020\002\032\0020\003¢\006\b\n\000\032\004\b\f\020\n¨\006\031"}, d2={"Lcom/byimplication/sakay/action/SetTerminalsManually;", "Lcom/byimplication/sakay/action/Action;", "origin", "Lcom/byimplication/sakay/model/Terminal;", "destination", "isSilent", "", "isRecall", "(Lcom/byimplication/sakay/model/Terminal;Lcom/byimplication/sakay/model/Terminal;ZZ)V", "getDestination", "()Lcom/byimplication/sakay/model/Terminal;", "()Z", "getOrigin", "component1", "component2", "component3", "component4", "copy", "equals", "other", "", "hashCode", "", "toString", "", "app_release"}, k=1, mv={1, 1, 9})
public final class SetTerminalsManually
  implements Action
{
  @NotNull
  private final Terminal destination;
  private final boolean isRecall;
  private final boolean isSilent;
  @NotNull
  private final Terminal origin;
  
  public SetTerminalsManually(@NotNull Terminal paramTerminal1, @NotNull Terminal paramTerminal2, boolean paramBoolean1, boolean paramBoolean2)
  {
    origin = paramTerminal1;
    destination = paramTerminal2;
    isSilent = paramBoolean1;
    isRecall = paramBoolean2;
  }
  
  @NotNull
  public final Terminal component1()
  {
    return origin;
  }
  
  @NotNull
  public final Terminal component2()
  {
    return destination;
  }
  
  public final boolean component3()
  {
    return isSilent;
  }
  
  public final boolean component4()
  {
    return isRecall;
  }
  
  @NotNull
  public final SetTerminalsManually copy(@NotNull Terminal paramTerminal1, @NotNull Terminal paramTerminal2, boolean paramBoolean1, boolean paramBoolean2)
  {
    Intrinsics.checkParameterIsNotNull(paramTerminal1, "origin");
    Intrinsics.checkParameterIsNotNull(paramTerminal2, "destination");
    return new SetTerminalsManually(paramTerminal1, paramTerminal2, paramBoolean1, paramBoolean2);
  }
  
  public boolean equals(Object paramObject)
  {
    if (this != paramObject)
    {
      if ((paramObject instanceof SetTerminalsManually))
      {
        paramObject = (SetTerminalsManually)paramObject;
        if ((Intrinsics.areEqual(origin, origin)) && (Intrinsics.areEqual(destination, destination)))
        {
          int i;
          if (isSilent == isSilent) {
            i = 1;
          } else {
            i = 0;
          }
          if (i != 0)
          {
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
      }
      return false;
    }
    return true;
  }
  
  @NotNull
  public final Terminal getDestination()
  {
    return destination;
  }
  
  @NotNull
  public final Terminal getOrigin()
  {
    return origin;
  }
  
  public int hashCode()
  {
    throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\r\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.copyTypes(TypeTransformer.java:311)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.fixTypes(TypeTransformer.java:226)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:207)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\r\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\r\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\r\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\r\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\r\n");
  }
  
  public final boolean isRecall()
  {
    return isRecall;
  }
  
  public final boolean isSilent()
  {
    return isSilent;
  }
  
  public void post()
  {
    Action.DefaultImpls.post(this);
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("SetTerminalsManually(origin=");
    localStringBuilder.append(origin);
    localStringBuilder.append(", destination=");
    localStringBuilder.append(destination);
    localStringBuilder.append(", isSilent=");
    localStringBuilder.append(isSilent);
    localStringBuilder.append(", isRecall=");
    localStringBuilder.append(isRecall);
    localStringBuilder.append(")");
    return localStringBuilder.toString();
  }
}
