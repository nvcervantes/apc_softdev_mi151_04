package kotlin.io;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.Reader;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000B\n\000\n\002\030\002\n\002\b\005\n\002\020\002\n\000\n\002\020\000\n\002\020\013\n\002\020\005\n\002\020\f\n\002\020\031\n\002\020\006\n\002\020\007\n\002\020\b\n\002\020\t\n\002\020\n\n\002\b\002\n\002\020\016\n\000\032\023\020\006\032\0020\0072\b\020\b\032\004\030\0010\tH\b\032\021\020\006\032\0020\0072\006\020\b\032\0020\nH\b\032\021\020\006\032\0020\0072\006\020\b\032\0020\013H\b\032\021\020\006\032\0020\0072\006\020\b\032\0020\fH\b\032\021\020\006\032\0020\0072\006\020\b\032\0020\rH\b\032\021\020\006\032\0020\0072\006\020\b\032\0020\016H\b\032\021\020\006\032\0020\0072\006\020\b\032\0020\017H\b\032\021\020\006\032\0020\0072\006\020\b\032\0020\020H\b\032\021\020\006\032\0020\0072\006\020\b\032\0020\021H\b\032\021\020\006\032\0020\0072\006\020\b\032\0020\022H\b\032\t\020\023\032\0020\007H\b\032\023\020\023\032\0020\0072\b\020\b\032\004\030\0010\tH\b\032\021\020\023\032\0020\0072\006\020\b\032\0020\nH\b\032\021\020\023\032\0020\0072\006\020\b\032\0020\013H\b\032\021\020\023\032\0020\0072\006\020\b\032\0020\fH\b\032\021\020\023\032\0020\0072\006\020\b\032\0020\rH\b\032\021\020\023\032\0020\0072\006\020\b\032\0020\016H\b\032\021\020\023\032\0020\0072\006\020\b\032\0020\017H\b\032\021\020\023\032\0020\0072\006\020\b\032\0020\020H\b\032\021\020\023\032\0020\0072\006\020\b\032\0020\021H\b\032\021\020\023\032\0020\0072\006\020\b\032\0020\022H\b\032\b\020\024\032\004\030\0010\025\"\033\020\000\032\0020\0018BX\002¢\006\f\n\004\b\004\020\005\032\004\b\002\020\003¨\006\026"}, d2={"stdin", "Ljava/io/BufferedReader;", "getStdin", "()Ljava/io/BufferedReader;", "stdin$delegate", "Lkotlin/Lazy;", "print", "", "message", "", "", "", "", "", "", "", "", "", "", "println", "readLine", "", "kotlin-stdlib"}, k=2, mv={1, 1, 9})
@JvmName(name="ConsoleKt")
public final class ConsoleKt
{
  private static final Lazy stdin$delegate = LazyKt.lazy((Function0)stdin.2.INSTANCE);
  
  private static final BufferedReader getStdin()
  {
    Lazy localLazy = stdin$delegate;
    KProperty localKProperty = $$delegatedProperties[0];
    return (BufferedReader)localLazy.getValue();
  }
  
  @InlineOnly
  private static final void print(byte paramByte)
  {
    System.out.print(Byte.valueOf(paramByte));
  }
  
  @InlineOnly
  private static final void print(char paramChar)
  {
    System.out.print(paramChar);
  }
  
  @InlineOnly
  private static final void print(double paramDouble)
  {
    System.out.print(paramDouble);
  }
  
  @InlineOnly
  private static final void print(float paramFloat)
  {
    System.out.print(paramFloat);
  }
  
  @InlineOnly
  private static final void print(int paramInt)
  {
    System.out.print(paramInt);
  }
  
  @InlineOnly
  private static final void print(long paramLong)
  {
    System.out.print(paramLong);
  }
  
  @InlineOnly
  private static final void print(Object paramObject)
  {
    System.out.print(paramObject);
  }
  
  @InlineOnly
  private static final void print(short paramShort)
  {
    System.out.print(Short.valueOf(paramShort));
  }
  
  @InlineOnly
  private static final void print(boolean paramBoolean)
  {
    System.out.print(paramBoolean);
  }
  
  @InlineOnly
  private static final void print(char[] paramArrayOfChar)
  {
    System.out.print(paramArrayOfChar);
  }
  
  @InlineOnly
  private static final void println()
  {
    System.out.println();
  }
  
  @InlineOnly
  private static final void println(byte paramByte)
  {
    System.out.println(Byte.valueOf(paramByte));
  }
  
  @InlineOnly
  private static final void println(char paramChar)
  {
    System.out.println(paramChar);
  }
  
  @InlineOnly
  private static final void println(double paramDouble)
  {
    System.out.println(paramDouble);
  }
  
  @InlineOnly
  private static final void println(float paramFloat)
  {
    System.out.println(paramFloat);
  }
  
  @InlineOnly
  private static final void println(int paramInt)
  {
    System.out.println(paramInt);
  }
  
  @InlineOnly
  private static final void println(long paramLong)
  {
    System.out.println(paramLong);
  }
  
  @InlineOnly
  private static final void println(Object paramObject)
  {
    System.out.println(paramObject);
  }
  
  @InlineOnly
  private static final void println(short paramShort)
  {
    System.out.println(Short.valueOf(paramShort));
  }
  
  @InlineOnly
  private static final void println(boolean paramBoolean)
  {
    System.out.println(paramBoolean);
  }
  
  @InlineOnly
  private static final void println(char[] paramArrayOfChar)
  {
    System.out.println(paramArrayOfChar);
  }
  
  @Nullable
  public static final String readLine()
  {
    return getStdin().readLine();
  }
}
