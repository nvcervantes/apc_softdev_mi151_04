package kotlin.collections;

import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.util.Collection;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.internal.InlineOnly;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000*\n\000\n\002\020\021\n\002\b\003\n\002\020\b\n\002\b\004\n\002\020\016\n\002\020\022\n\000\n\002\030\002\n\000\n\002\020\036\n\002\b\002\032/\020\000\032\b\022\004\022\002H\0020\001\"\004\b\000\020\0022\f\020\003\032\b\022\004\022\002H\0020\0012\006\020\004\032\0020\005H\000¢\006\002\020\006\032,\020\007\032\n\022\006\b\001\022\002H\0020\001\"\006\b\000\020\002\030\001*\f\022\006\b\001\022\002H\002\030\0010\001H\b¢\006\002\020\b\032\025\020\t\032\0020\n*\0020\0132\006\020\f\032\0020\rH\b\032&\020\016\032\b\022\004\022\002H\0020\001\"\006\b\000\020\002\030\001*\b\022\004\022\002H\0020\017H\b¢\006\002\020\020¨\006\021"}, d2={"arrayOfNulls", "", "T", "reference", "size", "", "([Ljava/lang/Object;I)[Ljava/lang/Object;", "orEmpty", "([Ljava/lang/Object;)[Ljava/lang/Object;", "toString", "", "", "charset", "Ljava/nio/charset/Charset;", "toTypedArray", "", "(Ljava/util/Collection;)[Ljava/lang/Object;", "kotlin-stdlib"}, k=5, mv={1, 1, 9}, xi=1, xs="kotlin/collections/ArraysKt")
class ArraysKt__ArraysJVMKt
{
  public ArraysKt__ArraysJVMKt() {}
  
  @NotNull
  public static final <T> T[] arrayOfNulls(@NotNull T[] paramArrayOfT, int paramInt)
  {
    Intrinsics.checkParameterIsNotNull(paramArrayOfT, "reference");
    paramArrayOfT = Array.newInstance(paramArrayOfT.getClass().getComponentType(), paramInt);
    if (paramArrayOfT == null) {
      throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
    }
    return (Object[])paramArrayOfT;
  }
  
  private static final <T> T[] orEmpty(@Nullable T[] paramArrayOfT)
  {
    if (paramArrayOfT != null) {
      return paramArrayOfT;
    }
    Intrinsics.reifiedOperationMarker(0, "T?");
    return new Object[0];
  }
  
  @InlineOnly
  private static final String toString(@NotNull byte[] paramArrayOfByte, Charset paramCharset)
  {
    return new String(paramArrayOfByte, paramCharset);
  }
  
  private static final <T> T[] toTypedArray(@NotNull Collection<? extends T> paramCollection)
  {
    if (paramCollection == null) {
      throw new TypeCastException("null cannot be cast to non-null type java.util.Collection<T>");
    }
    Intrinsics.reifiedOperationMarker(0, "T?");
    paramCollection = paramCollection.toArray(new Object[0]);
    if (paramCollection == null) {
      throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
    }
    return paramCollection;
  }
}
