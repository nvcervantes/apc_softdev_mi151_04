package com.byimplication.sakay.store;

import com.byimplication.sakay.model.Terminal;
import com.byimplication.sakay.model.TerminalType;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\0006\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\002\b\002\n\002\020\021\n\002\030\002\n\000\n\002\020\013\n\002\b\021\n\002\020\000\n\000\n\002\020\b\n\000\n\002\020\016\n\000\b\b\030\0002\0020\001B-\022\006\020\002\032\0020\003\022\006\020\004\032\0020\003\022\f\020\005\032\b\022\004\022\0020\0070\006\022\b\b\002\020\b\032\0020\t¢\006\002\020\nJ\n\020\022\032\004\030\0010\001H\026J\t\020\023\032\0020\003HÆ\003J\t\020\024\032\0020\003HÆ\003J\024\020\025\032\b\022\004\022\0020\0070\006HÆ\003¢\006\002\020\020J\t\020\026\032\0020\tHÆ\003J<\020\027\032\0020\0002\b\b\002\020\002\032\0020\0032\b\b\002\020\004\032\0020\0032\016\b\002\020\005\032\b\022\004\022\0020\0070\0062\b\b\002\020\b\032\0020\tHÆ\001¢\006\002\020\030J\023\020\031\032\0020\t2\b\020\032\032\004\030\0010\033HÖ\003J\t\020\034\032\0020\035HÖ\001J\t\020\036\032\0020\037HÖ\001R\021\020\004\032\0020\003¢\006\b\n\000\032\004\b\013\020\fR\021\020\b\032\0020\t¢\006\b\n\000\032\004\b\b\020\rR\021\020\002\032\0020\003¢\006\b\n\000\032\004\b\016\020\fR\031\020\005\032\b\022\004\022\0020\0070\006¢\006\n\n\002\020\021\032\004\b\017\020\020¨\006 "}, d2={"Lcom/byimplication/sakay/store/TerminalsUpdated;", "Lcom/byimplication/sakay/store/StoreData;", "originTerminal", "Lcom/byimplication/sakay/model/Terminal;", "destinationTerminal", "terminalTypesUpdated", "", "Lcom/byimplication/sakay/model/TerminalType;", "isRecall", "", "(Lcom/byimplication/sakay/model/Terminal;Lcom/byimplication/sakay/model/Terminal;[Lcom/byimplication/sakay/model/TerminalType;Z)V", "getDestinationTerminal", "()Lcom/byimplication/sakay/model/Terminal;", "()Z", "getOriginTerminal", "getTerminalTypesUpdated", "()[Lcom/byimplication/sakay/model/TerminalType;", "[Lcom/byimplication/sakay/model/TerminalType;", "clone", "component1", "component2", "component3", "component4", "copy", "(Lcom/byimplication/sakay/model/Terminal;Lcom/byimplication/sakay/model/Terminal;[Lcom/byimplication/sakay/model/TerminalType;Z)Lcom/byimplication/sakay/store/TerminalsUpdated;", "equals", "other", "", "hashCode", "", "toString", "", "app_release"}, k=1, mv={1, 1, 9})
public final class TerminalsUpdated
  implements StoreData
{
  @NotNull
  private final Terminal destinationTerminal;
  private final boolean isRecall;
  @NotNull
  private final Terminal originTerminal;
  @NotNull
  private final TerminalType[] terminalTypesUpdated;
  
  public TerminalsUpdated(@NotNull Terminal paramTerminal1, @NotNull Terminal paramTerminal2, @NotNull TerminalType[] paramArrayOfTerminalType, boolean paramBoolean)
  {
    originTerminal = paramTerminal1;
    destinationTerminal = paramTerminal2;
    terminalTypesUpdated = paramArrayOfTerminalType;
    isRecall = paramBoolean;
  }
  
  @Nullable
  public StoreData clone()
  {
    return (StoreData)new TerminalsUpdated(Terminal.copy$default(originTerminal, null, null, null, 7, null), Terminal.copy$default(destinationTerminal, null, null, null, 7, null), (TerminalType[])((Object[])terminalTypesUpdated).clone(), isRecall);
  }
  
  @NotNull
  public final Terminal component1()
  {
    return originTerminal;
  }
  
  @NotNull
  public final Terminal component2()
  {
    return destinationTerminal;
  }
  
  @NotNull
  public final TerminalType[] component3()
  {
    return terminalTypesUpdated;
  }
  
  public final boolean component4()
  {
    return isRecall;
  }
  
  @NotNull
  public final TerminalsUpdated copy(@NotNull Terminal paramTerminal1, @NotNull Terminal paramTerminal2, @NotNull TerminalType[] paramArrayOfTerminalType, boolean paramBoolean)
  {
    Intrinsics.checkParameterIsNotNull(paramTerminal1, "originTerminal");
    Intrinsics.checkParameterIsNotNull(paramTerminal2, "destinationTerminal");
    Intrinsics.checkParameterIsNotNull(paramArrayOfTerminalType, "terminalTypesUpdated");
    return new TerminalsUpdated(paramTerminal1, paramTerminal2, paramArrayOfTerminalType, paramBoolean);
  }
  
  public boolean equals(Object paramObject)
  {
    if (this != paramObject)
    {
      if ((paramObject instanceof TerminalsUpdated))
      {
        paramObject = (TerminalsUpdated)paramObject;
        if ((Intrinsics.areEqual(originTerminal, originTerminal)) && (Intrinsics.areEqual(destinationTerminal, destinationTerminal)) && (Intrinsics.areEqual(terminalTypesUpdated, terminalTypesUpdated)))
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
  public final Terminal getDestinationTerminal()
  {
    return destinationTerminal;
  }
  
  @NotNull
  public final Terminal getOriginTerminal()
  {
    return originTerminal;
  }
  
  @NotNull
  public final TerminalType[] getTerminalTypesUpdated()
  {
    return terminalTypesUpdated;
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
    localStringBuilder.append("TerminalsUpdated(originTerminal=");
    localStringBuilder.append(originTerminal);
    localStringBuilder.append(", destinationTerminal=");
    localStringBuilder.append(destinationTerminal);
    localStringBuilder.append(", terminalTypesUpdated=");
    localStringBuilder.append(Arrays.toString(terminalTypesUpdated));
    localStringBuilder.append(", isRecall=");
    localStringBuilder.append(isRecall);
    localStringBuilder.append(")");
    return localStringBuilder.toString();
  }
}
