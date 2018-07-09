package kotlin.internal;

import kotlin.KotlinVersion;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.SinceKotlin;
import kotlin.TypeCastException;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000\026\n\000\n\002\030\002\n\000\n\002\020\013\n\000\n\002\020\b\n\002\b\004\032 \020\002\032\0020\0032\006\020\004\032\0020\0052\006\020\006\032\0020\0052\006\020\007\032\0020\005H\001\032\b\020\b\032\0020\005H\002\"\020\020\000\032\0020\0018\000X\004¢\006\002\n\000¨\006\t"}, d2={"IMPLEMENTATIONS", "Lkotlin/internal/PlatformImplementations;", "apiVersionIsAtLeast", "", "major", "", "minor", "patch", "getJavaVersion", "kotlin-stdlib"}, k=2, mv={1, 1, 9})
public final class PlatformImplementationsKt
{
  @JvmField
  @NotNull
  public static final PlatformImplementations IMPLEMENTATIONS;
  
  static
  {
    int i = getJavaVersion();
    if (i >= 65544) {}
    for (;;)
    {
      try
      {
        localObject = Class.forName("kotlin.internal.jdk8.JDK8PlatformImplementations").newInstance();
        if (localObject == null) {
          throw new TypeCastException("null cannot be cast to non-null type kotlin.internal.PlatformImplementations");
        }
        localObject = (PlatformImplementations)localObject;
      }
      catch (ClassNotFoundException localClassNotFoundException1)
      {
        Object localObject;
        continue;
      }
      try
      {
        localObject = Class.forName("kotlin.internal.JRE8PlatformImplementations").newInstance();
        if (localObject == null) {
          throw new TypeCastException("null cannot be cast to non-null type kotlin.internal.PlatformImplementations");
        }
        localObject = (PlatformImplementations)localObject;
      }
      catch (ClassNotFoundException localClassNotFoundException2) {}
    }
    if (i >= 65543) {}
    for (;;)
    {
      try
      {
        localObject = Class.forName("kotlin.internal.jdk7.JDK7PlatformImplementations").newInstance();
        if (localObject == null) {
          throw new TypeCastException("null cannot be cast to non-null type kotlin.internal.PlatformImplementations");
        }
        localObject = (PlatformImplementations)localObject;
      }
      catch (ClassNotFoundException localClassNotFoundException3)
      {
        continue;
      }
      try
      {
        localObject = Class.forName("kotlin.internal.JRE7PlatformImplementations").newInstance();
        if (localObject == null) {
          throw new TypeCastException("null cannot be cast to non-null type kotlin.internal.PlatformImplementations");
        }
        localObject = (PlatformImplementations)localObject;
      }
      catch (ClassNotFoundException localClassNotFoundException4) {}
    }
    localObject = new PlatformImplementations();
    IMPLEMENTATIONS = (PlatformImplementations)localObject;
  }
  
  @PublishedApi
  @SinceKotlin(version="1.2")
  public static final boolean apiVersionIsAtLeast(int paramInt1, int paramInt2, int paramInt3)
  {
    return KotlinVersion.CURRENT.isAtLeast(paramInt1, paramInt2, paramInt3);
  }
  
  private static final int getJavaVersion()
  {
    String str = System.getProperty("java.specification.version");
    int k;
    if (str != null)
    {
      localObject = (CharSequence)str;
      k = StringsKt.indexOf$default((CharSequence)localObject, '.', 0, false, 6, null);
      if (k >= 0) {}
    }
    try
    {
      i = Integer.parseInt(str);
      return i * 65536;
    }
    catch (NumberFormatException localNumberFormatException1)
    {
      int i;
      int m;
      int j;
      return 65542;
    }
    m = k + 1;
    j = StringsKt.indexOf$default((CharSequence)localObject, '.', m, false, 4, null);
    i = j;
    if (j < 0) {
      i = str.length();
    }
    if (str == null) {
      throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }
    Object localObject = str.substring(0, k);
    Intrinsics.checkExpressionValueIsNotNull(localObject, "(this as java.lang.Strin…ing(startIndex, endIndex)");
    if (str == null) {
      throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }
    str = str.substring(m, i);
    Intrinsics.checkExpressionValueIsNotNull(str, "(this as java.lang.Strin…ing(startIndex, endIndex)");
    try
    {
      i = Integer.parseInt((String)localObject);
      j = Integer.parseInt(str);
      return i * 65536 + j;
    }
    catch (NumberFormatException localNumberFormatException2) {}
    return 65542;
    return 65542;
  }
}
