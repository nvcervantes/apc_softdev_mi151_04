package kotlin.comparisons;

import java.util.Comparator;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.internal.InlineOnly;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\0004\n\002\b\002\n\002\020\017\n\002\b\006\n\002\030\002\n\002\030\002\n\002\b\002\n\002\020\005\n\002\020\006\n\002\020\007\n\002\020\b\n\002\020\t\n\002\020\n\n\002\b\002\032-\020\000\032\002H\001\"\016\b\000\020\001*\b\022\004\022\002H\0010\0022\006\020\003\032\002H\0012\006\020\004\032\002H\001H\007¢\006\002\020\005\0325\020\000\032\002H\001\"\016\b\000\020\001*\b\022\004\022\002H\0010\0022\006\020\003\032\002H\0012\006\020\004\032\002H\0012\006\020\006\032\002H\001H\007¢\006\002\020\007\032G\020\000\032\002H\001\"\004\b\000\020\0012\006\020\003\032\002H\0012\006\020\004\032\002H\0012\006\020\006\032\002H\0012\032\020\b\032\026\022\006\b\000\022\002H\0010\tj\n\022\006\b\000\022\002H\001`\nH\007¢\006\002\020\013\032?\020\000\032\002H\001\"\004\b\000\020\0012\006\020\003\032\002H\0012\006\020\004\032\002H\0012\032\020\b\032\026\022\006\b\000\022\002H\0010\tj\n\022\006\b\000\022\002H\001`\nH\007¢\006\002\020\f\032\031\020\000\032\0020\r2\006\020\003\032\0020\r2\006\020\004\032\0020\rH\b\032!\020\000\032\0020\r2\006\020\003\032\0020\r2\006\020\004\032\0020\r2\006\020\006\032\0020\rH\b\032\031\020\000\032\0020\0162\006\020\003\032\0020\0162\006\020\004\032\0020\016H\b\032!\020\000\032\0020\0162\006\020\003\032\0020\0162\006\020\004\032\0020\0162\006\020\006\032\0020\016H\b\032\031\020\000\032\0020\0172\006\020\003\032\0020\0172\006\020\004\032\0020\017H\b\032!\020\000\032\0020\0172\006\020\003\032\0020\0172\006\020\004\032\0020\0172\006\020\006\032\0020\017H\b\032\031\020\000\032\0020\0202\006\020\003\032\0020\0202\006\020\004\032\0020\020H\b\032!\020\000\032\0020\0202\006\020\003\032\0020\0202\006\020\004\032\0020\0202\006\020\006\032\0020\020H\b\032\031\020\000\032\0020\0212\006\020\003\032\0020\0212\006\020\004\032\0020\021H\b\032!\020\000\032\0020\0212\006\020\003\032\0020\0212\006\020\004\032\0020\0212\006\020\006\032\0020\021H\b\032\031\020\000\032\0020\0222\006\020\003\032\0020\0222\006\020\004\032\0020\022H\b\032!\020\000\032\0020\0222\006\020\003\032\0020\0222\006\020\004\032\0020\0222\006\020\006\032\0020\022H\b\032-\020\023\032\002H\001\"\016\b\000\020\001*\b\022\004\022\002H\0010\0022\006\020\003\032\002H\0012\006\020\004\032\002H\001H\007¢\006\002\020\005\0325\020\023\032\002H\001\"\016\b\000\020\001*\b\022\004\022\002H\0010\0022\006\020\003\032\002H\0012\006\020\004\032\002H\0012\006\020\006\032\002H\001H\007¢\006\002\020\007\032G\020\023\032\002H\001\"\004\b\000\020\0012\006\020\003\032\002H\0012\006\020\004\032\002H\0012\006\020\006\032\002H\0012\032\020\b\032\026\022\006\b\000\022\002H\0010\tj\n\022\006\b\000\022\002H\001`\nH\007¢\006\002\020\013\032?\020\023\032\002H\001\"\004\b\000\020\0012\006\020\003\032\002H\0012\006\020\004\032\002H\0012\032\020\b\032\026\022\006\b\000\022\002H\0010\tj\n\022\006\b\000\022\002H\001`\nH\007¢\006\002\020\f\032\031\020\023\032\0020\r2\006\020\003\032\0020\r2\006\020\004\032\0020\rH\b\032!\020\023\032\0020\r2\006\020\003\032\0020\r2\006\020\004\032\0020\r2\006\020\006\032\0020\rH\b\032\031\020\023\032\0020\0162\006\020\003\032\0020\0162\006\020\004\032\0020\016H\b\032!\020\023\032\0020\0162\006\020\003\032\0020\0162\006\020\004\032\0020\0162\006\020\006\032\0020\016H\b\032\031\020\023\032\0020\0172\006\020\003\032\0020\0172\006\020\004\032\0020\017H\b\032!\020\023\032\0020\0172\006\020\003\032\0020\0172\006\020\004\032\0020\0172\006\020\006\032\0020\017H\b\032\031\020\023\032\0020\0202\006\020\003\032\0020\0202\006\020\004\032\0020\020H\b\032!\020\023\032\0020\0202\006\020\003\032\0020\0202\006\020\004\032\0020\0202\006\020\006\032\0020\020H\b\032\031\020\023\032\0020\0212\006\020\003\032\0020\0212\006\020\004\032\0020\021H\b\032!\020\023\032\0020\0212\006\020\003\032\0020\0212\006\020\004\032\0020\0212\006\020\006\032\0020\021H\b\032\031\020\023\032\0020\0222\006\020\003\032\0020\0222\006\020\004\032\0020\022H\b\032!\020\023\032\0020\0222\006\020\003\032\0020\0222\006\020\004\032\0020\0222\006\020\006\032\0020\022H\b¨\006\024"}, d2={"maxOf", "T", "", "a", "b", "(Ljava/lang/Comparable;Ljava/lang/Comparable;)Ljava/lang/Comparable;", "c", "(Ljava/lang/Comparable;Ljava/lang/Comparable;Ljava/lang/Comparable;)Ljava/lang/Comparable;", "comparator", "Ljava/util/Comparator;", "Lkotlin/Comparator;", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/util/Comparator;)Ljava/lang/Object;", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/util/Comparator;)Ljava/lang/Object;", "", "", "", "", "", "", "minOf", "kotlin-stdlib"}, k=5, mv={1, 1, 9}, xi=1, xs="kotlin/comparisons/ComparisonsKt")
class ComparisonsKt___ComparisonsKt
  extends ComparisonsKt__ComparisonsKt
{
  public ComparisonsKt___ComparisonsKt() {}
  
  @SinceKotlin(version="1.1")
  @InlineOnly
  private static final byte maxOf(byte paramByte1, byte paramByte2)
  {
    return (byte)Math.max(paramByte1, paramByte2);
  }
  
  @SinceKotlin(version="1.1")
  @InlineOnly
  private static final byte maxOf(byte paramByte1, byte paramByte2, byte paramByte3)
  {
    return (byte)Math.max(paramByte1, Math.max(paramByte2, paramByte3));
  }
  
  @SinceKotlin(version="1.1")
  @InlineOnly
  private static final double maxOf(double paramDouble1, double paramDouble2)
  {
    return Math.max(paramDouble1, paramDouble2);
  }
  
  @SinceKotlin(version="1.1")
  @InlineOnly
  private static final double maxOf(double paramDouble1, double paramDouble2, double paramDouble3)
  {
    return Math.max(paramDouble1, Math.max(paramDouble2, paramDouble3));
  }
  
  @SinceKotlin(version="1.1")
  @InlineOnly
  private static final float maxOf(float paramFloat1, float paramFloat2)
  {
    return Math.max(paramFloat1, paramFloat2);
  }
  
  @SinceKotlin(version="1.1")
  @InlineOnly
  private static final float maxOf(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    return Math.max(paramFloat1, Math.max(paramFloat2, paramFloat3));
  }
  
  @SinceKotlin(version="1.1")
  @InlineOnly
  private static final int maxOf(int paramInt1, int paramInt2)
  {
    return Math.max(paramInt1, paramInt2);
  }
  
  @SinceKotlin(version="1.1")
  @InlineOnly
  private static final int maxOf(int paramInt1, int paramInt2, int paramInt3)
  {
    return Math.max(paramInt1, Math.max(paramInt2, paramInt3));
  }
  
  @SinceKotlin(version="1.1")
  @InlineOnly
  private static final long maxOf(long paramLong1, long paramLong2)
  {
    return Math.max(paramLong1, paramLong2);
  }
  
  @SinceKotlin(version="1.1")
  @InlineOnly
  private static final long maxOf(long paramLong1, long paramLong2, long paramLong3)
  {
    return Math.max(paramLong1, Math.max(paramLong2, paramLong3));
  }
  
  @SinceKotlin(version="1.1")
  @NotNull
  public static final <T extends Comparable<? super T>> T maxOf(@NotNull T paramT1, @NotNull T paramT2)
  {
    Intrinsics.checkParameterIsNotNull(paramT1, "a");
    Intrinsics.checkParameterIsNotNull(paramT2, "b");
    if (paramT1.compareTo(paramT2) >= 0) {
      return paramT1;
    }
    return paramT2;
  }
  
  @SinceKotlin(version="1.1")
  @NotNull
  public static final <T extends Comparable<? super T>> T maxOf(@NotNull T paramT1, @NotNull T paramT2, @NotNull T paramT3)
  {
    Intrinsics.checkParameterIsNotNull(paramT1, "a");
    Intrinsics.checkParameterIsNotNull(paramT2, "b");
    Intrinsics.checkParameterIsNotNull(paramT3, "c");
    return ComparisonsKt.maxOf(paramT1, ComparisonsKt.maxOf(paramT2, paramT3));
  }
  
  @SinceKotlin(version="1.1")
  public static final <T> T maxOf(T paramT1, T paramT2, T paramT3, @NotNull Comparator<? super T> paramComparator)
  {
    Intrinsics.checkParameterIsNotNull(paramComparator, "comparator");
    return ComparisonsKt.maxOf(paramT1, ComparisonsKt.maxOf(paramT2, paramT3, paramComparator), paramComparator);
  }
  
  @SinceKotlin(version="1.1")
  public static final <T> T maxOf(T paramT1, T paramT2, @NotNull Comparator<? super T> paramComparator)
  {
    Intrinsics.checkParameterIsNotNull(paramComparator, "comparator");
    if (paramComparator.compare(paramT1, paramT2) >= 0) {
      return paramT1;
    }
    return paramT2;
  }
  
  @SinceKotlin(version="1.1")
  @InlineOnly
  private static final short maxOf(short paramShort1, short paramShort2)
  {
    return (short)Math.max(paramShort1, paramShort2);
  }
  
  @SinceKotlin(version="1.1")
  @InlineOnly
  private static final short maxOf(short paramShort1, short paramShort2, short paramShort3)
  {
    return (short)Math.max(paramShort1, Math.max(paramShort2, paramShort3));
  }
  
  @SinceKotlin(version="1.1")
  @InlineOnly
  private static final byte minOf(byte paramByte1, byte paramByte2)
  {
    return (byte)Math.min(paramByte1, paramByte2);
  }
  
  @SinceKotlin(version="1.1")
  @InlineOnly
  private static final byte minOf(byte paramByte1, byte paramByte2, byte paramByte3)
  {
    return (byte)Math.min(paramByte1, Math.min(paramByte2, paramByte3));
  }
  
  @SinceKotlin(version="1.1")
  @InlineOnly
  private static final double minOf(double paramDouble1, double paramDouble2)
  {
    return Math.min(paramDouble1, paramDouble2);
  }
  
  @SinceKotlin(version="1.1")
  @InlineOnly
  private static final double minOf(double paramDouble1, double paramDouble2, double paramDouble3)
  {
    return Math.min(paramDouble1, Math.min(paramDouble2, paramDouble3));
  }
  
  @SinceKotlin(version="1.1")
  @InlineOnly
  private static final float minOf(float paramFloat1, float paramFloat2)
  {
    return Math.min(paramFloat1, paramFloat2);
  }
  
  @SinceKotlin(version="1.1")
  @InlineOnly
  private static final float minOf(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    return Math.min(paramFloat1, Math.min(paramFloat2, paramFloat3));
  }
  
  @SinceKotlin(version="1.1")
  @InlineOnly
  private static final int minOf(int paramInt1, int paramInt2)
  {
    return Math.min(paramInt1, paramInt2);
  }
  
  @SinceKotlin(version="1.1")
  @InlineOnly
  private static final int minOf(int paramInt1, int paramInt2, int paramInt3)
  {
    return Math.min(paramInt1, Math.min(paramInt2, paramInt3));
  }
  
  @SinceKotlin(version="1.1")
  @InlineOnly
  private static final long minOf(long paramLong1, long paramLong2)
  {
    return Math.min(paramLong1, paramLong2);
  }
  
  @SinceKotlin(version="1.1")
  @InlineOnly
  private static final long minOf(long paramLong1, long paramLong2, long paramLong3)
  {
    return Math.min(paramLong1, Math.min(paramLong2, paramLong3));
  }
  
  @SinceKotlin(version="1.1")
  @NotNull
  public static final <T extends Comparable<? super T>> T minOf(@NotNull T paramT1, @NotNull T paramT2)
  {
    Intrinsics.checkParameterIsNotNull(paramT1, "a");
    Intrinsics.checkParameterIsNotNull(paramT2, "b");
    if (paramT1.compareTo(paramT2) <= 0) {
      return paramT1;
    }
    return paramT2;
  }
  
  @SinceKotlin(version="1.1")
  @NotNull
  public static final <T extends Comparable<? super T>> T minOf(@NotNull T paramT1, @NotNull T paramT2, @NotNull T paramT3)
  {
    Intrinsics.checkParameterIsNotNull(paramT1, "a");
    Intrinsics.checkParameterIsNotNull(paramT2, "b");
    Intrinsics.checkParameterIsNotNull(paramT3, "c");
    return ComparisonsKt.minOf(paramT1, ComparisonsKt.minOf(paramT2, paramT3));
  }
  
  @SinceKotlin(version="1.1")
  public static final <T> T minOf(T paramT1, T paramT2, T paramT3, @NotNull Comparator<? super T> paramComparator)
  {
    Intrinsics.checkParameterIsNotNull(paramComparator, "comparator");
    return ComparisonsKt.minOf(paramT1, ComparisonsKt.minOf(paramT2, paramT3, paramComparator), paramComparator);
  }
  
  @SinceKotlin(version="1.1")
  public static final <T> T minOf(T paramT1, T paramT2, @NotNull Comparator<? super T> paramComparator)
  {
    Intrinsics.checkParameterIsNotNull(paramComparator, "comparator");
    if (paramComparator.compare(paramT1, paramT2) <= 0) {
      return paramT1;
    }
    return paramT2;
  }
  
  @SinceKotlin(version="1.1")
  @InlineOnly
  private static final short minOf(short paramShort1, short paramShort2)
  {
    return (short)Math.min(paramShort1, paramShort2);
  }
  
  @SinceKotlin(version="1.1")
  @InlineOnly
  private static final short minOf(short paramShort1, short paramShort2, short paramShort3)
  {
    return (short)Math.min(paramShort1, Math.min(paramShort2, paramShort3));
  }
}
