package kotlin.reflect;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000.\n\002\030\002\n\002\020\000\n\000\n\002\030\002\n\000\n\002\030\002\n\002\b\t\n\002\020\013\n\002\b\002\n\002\020\b\n\000\n\002\020\016\n\002\b\002\b\b\030\000 \0252\0020\001:\001\025B\031\022\b\020\002\032\004\030\0010\003\022\b\020\004\032\004\030\0010\005¢\006\002\020\006J\013\020\013\032\004\030\0010\003HÆ\003J\013\020\f\032\004\030\0010\005HÆ\003J!\020\r\032\0020\0002\n\b\002\020\002\032\004\030\0010\0032\n\b\002\020\004\032\004\030\0010\005HÆ\001J\023\020\016\032\0020\0172\b\020\020\032\004\030\0010\001HÖ\003J\t\020\021\032\0020\022HÖ\001J\t\020\023\032\0020\024HÖ\001R\023\020\004\032\004\030\0010\005¢\006\b\n\000\032\004\b\007\020\bR\023\020\002\032\004\030\0010\003¢\006\b\n\000\032\004\b\t\020\n¨\006\026"}, d2={"Lkotlin/reflect/KTypeProjection;", "", "variance", "Lkotlin/reflect/KVariance;", "type", "Lkotlin/reflect/KType;", "(Lkotlin/reflect/KVariance;Lkotlin/reflect/KType;)V", "getType", "()Lkotlin/reflect/KType;", "getVariance", "()Lkotlin/reflect/KVariance;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "Companion", "kotlin-runtime"}, k=1, mv={1, 1, 9})
@SinceKotlin(version="1.1")
public final class KTypeProjection
{
  public static final Companion Companion = new Companion(null);
  @NotNull
  private static final KTypeProjection STAR = new KTypeProjection(null, null);
  @Nullable
  private final KType type;
  @Nullable
  private final KVariance variance;
  
  public KTypeProjection(@Nullable KVariance paramKVariance, @Nullable KType paramKType)
  {
    variance = paramKVariance;
    type = paramKType;
  }
  
  @Nullable
  public final KVariance component1()
  {
    return variance;
  }
  
  @Nullable
  public final KType component2()
  {
    return type;
  }
  
  @NotNull
  public final KTypeProjection copy(@Nullable KVariance paramKVariance, @Nullable KType paramKType)
  {
    return new KTypeProjection(paramKVariance, paramKType);
  }
  
  public boolean equals(Object paramObject)
  {
    if (this != paramObject) {
      if ((paramObject instanceof KTypeProjection))
      {
        paramObject = (KTypeProjection)paramObject;
        if ((Intrinsics.areEqual(variance, variance)) && (Intrinsics.areEqual(type, type))) {}
      }
      else
      {
        return false;
      }
    }
    return true;
  }
  
  @Nullable
  public final KType getType()
  {
    return type;
  }
  
  @Nullable
  public final KVariance getVariance()
  {
    return variance;
  }
  
  public int hashCode()
  {
    Object localObject = variance;
    int j = 0;
    int i;
    if (localObject != null) {
      i = localObject.hashCode();
    } else {
      i = 0;
    }
    localObject = type;
    if (localObject != null) {
      j = localObject.hashCode();
    }
    return i * 31 + j;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("KTypeProjection(variance=");
    localStringBuilder.append(variance);
    localStringBuilder.append(", type=");
    localStringBuilder.append(type);
    localStringBuilder.append(")");
    return localStringBuilder.toString();
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\000\034\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\002\b\004\n\002\030\002\n\002\b\003\b\003\030\0002\0020\001B\007\b\002¢\006\002\020\002J\016\020\007\032\0020\0042\006\020\b\032\0020\tJ\016\020\n\032\0020\0042\006\020\b\032\0020\tJ\016\020\013\032\0020\0042\006\020\b\032\0020\tR\021\020\003\032\0020\004¢\006\b\n\000\032\004\b\005\020\006¨\006\f"}, d2={"Lkotlin/reflect/KTypeProjection$Companion;", "", "()V", "STAR", "Lkotlin/reflect/KTypeProjection;", "getSTAR", "()Lkotlin/reflect/KTypeProjection;", "contravariant", "type", "Lkotlin/reflect/KType;", "covariant", "invariant", "kotlin-runtime"}, k=1, mv={1, 1, 9})
  public static final class Companion
  {
    private Companion() {}
    
    @NotNull
    public final KTypeProjection contravariant(@NotNull KType paramKType)
    {
      Intrinsics.checkParameterIsNotNull(paramKType, "type");
      return new KTypeProjection(KVariance.IN, paramKType);
    }
    
    @NotNull
    public final KTypeProjection covariant(@NotNull KType paramKType)
    {
      Intrinsics.checkParameterIsNotNull(paramKType, "type");
      return new KTypeProjection(KVariance.OUT, paramKType);
    }
    
    @NotNull
    public final KTypeProjection getSTAR()
    {
      return KTypeProjection.access$getSTAR$cp();
    }
    
    @NotNull
    public final KTypeProjection invariant(@NotNull KType paramKType)
    {
      Intrinsics.checkParameterIsNotNull(paramKType, "type");
      return new KTypeProjection(KVariance.INVARIANT, paramKType);
    }
  }
}
