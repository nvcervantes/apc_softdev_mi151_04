package kotlin;

import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000&\n\002\030\002\n\002\020\017\n\000\n\002\020\b\n\002\b\f\n\002\020\013\n\002\020\000\n\002\b\003\n\002\020\016\n\002\b\003\b\007\030\000 \0272\b\022\004\022\0020\0000\001:\001\027B\027\b\026\022\006\020\002\032\0020\003\022\006\020\004\032\0020\003¢\006\002\020\005B\035\022\006\020\002\032\0020\003\022\006\020\004\032\0020\003\022\006\020\006\032\0020\003¢\006\002\020\007J\021\020\r\032\0020\0032\006\020\016\032\0020\000H\002J\023\020\017\032\0020\0202\b\020\016\032\004\030\0010\021H\002J\b\020\022\032\0020\003H\026J\026\020\023\032\0020\0202\006\020\002\032\0020\0032\006\020\004\032\0020\003J\036\020\023\032\0020\0202\006\020\002\032\0020\0032\006\020\004\032\0020\0032\006\020\006\032\0020\003J\b\020\024\032\0020\025H\026J \020\026\032\0020\0032\006\020\002\032\0020\0032\006\020\004\032\0020\0032\006\020\006\032\0020\003H\002R\021\020\002\032\0020\003¢\006\b\n\000\032\004\b\b\020\tR\021\020\004\032\0020\003¢\006\b\n\000\032\004\b\n\020\tR\021\020\006\032\0020\003¢\006\b\n\000\032\004\b\013\020\tR\016\020\f\032\0020\003X\004¢\006\002\n\000¨\006\030"}, d2={"Lkotlin/KotlinVersion;", "", "major", "", "minor", "(II)V", "patch", "(III)V", "getMajor", "()I", "getMinor", "getPatch", "version", "compareTo", "other", "equals", "", "", "hashCode", "isAtLeast", "toString", "", "versionOf", "Companion", "kotlin-stdlib"}, k=1, mv={1, 1, 9})
@SinceKotlin(version="1.1")
public final class KotlinVersion
  implements Comparable<KotlinVersion>
{
  @JvmField
  @NotNull
  public static final KotlinVersion CURRENT = new KotlinVersion(1, 2, 31);
  public static final Companion Companion = new Companion(null);
  public static final int MAX_COMPONENT_VALUE = 255;
  private final int major;
  private final int minor;
  private final int patch;
  private final int version;
  
  public KotlinVersion(int paramInt1, int paramInt2)
  {
    this(paramInt1, paramInt2, 0);
  }
  
  public KotlinVersion(int paramInt1, int paramInt2, int paramInt3)
  {
    major = paramInt1;
    minor = paramInt2;
    patch = paramInt3;
    version = versionOf(major, minor, patch);
  }
  
  private final int versionOf(int paramInt1, int paramInt2, int paramInt3)
  {
    int i;
    if ((paramInt1 >= 0) && (255 >= paramInt1) && (paramInt2 >= 0) && (255 >= paramInt2) && (paramInt3 >= 0) && (255 >= paramInt3)) {
      i = 1;
    } else {
      i = 0;
    }
    if (i == 0)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Version components are out of range: ");
      localStringBuilder.append(paramInt1);
      localStringBuilder.append('.');
      localStringBuilder.append(paramInt2);
      localStringBuilder.append('.');
      localStringBuilder.append(paramInt3);
      throw ((Throwable)new IllegalArgumentException(localStringBuilder.toString().toString()));
    }
    return (paramInt1 << 16) + (paramInt2 << 8) + paramInt3;
  }
  
  public int compareTo(@NotNull KotlinVersion paramKotlinVersion)
  {
    Intrinsics.checkParameterIsNotNull(paramKotlinVersion, "other");
    return version - version;
  }
  
  public boolean equals(@Nullable Object paramObject)
  {
    if ((KotlinVersion)this == paramObject) {
      return true;
    }
    Object localObject = paramObject;
    if (!(paramObject instanceof KotlinVersion)) {
      localObject = null;
    }
    paramObject = (KotlinVersion)localObject;
    boolean bool = false;
    if (paramObject != null)
    {
      if (version == version) {
        bool = true;
      }
      return bool;
    }
    return false;
  }
  
  public final int getMajor()
  {
    return major;
  }
  
  public final int getMinor()
  {
    return minor;
  }
  
  public final int getPatch()
  {
    return patch;
  }
  
  public int hashCode()
  {
    return version;
  }
  
  public final boolean isAtLeast(int paramInt1, int paramInt2)
  {
    return (major > paramInt1) || ((major == paramInt1) && (minor >= paramInt2));
  }
  
  public final boolean isAtLeast(int paramInt1, int paramInt2, int paramInt3)
  {
    return (major > paramInt1) || ((major == paramInt1) && ((minor > paramInt2) || ((minor == paramInt2) && (patch >= paramInt3))));
  }
  
  @NotNull
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("");
    localStringBuilder.append(major);
    localStringBuilder.append('.');
    localStringBuilder.append(minor);
    localStringBuilder.append('.');
    localStringBuilder.append(patch);
    return localStringBuilder.toString();
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\000\030\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\000\n\002\020\b\n\000\b\003\030\0002\0020\001B\007\b\002¢\006\002\020\002R\020\020\003\032\0020\0048\006X\004¢\006\002\n\000R\016\020\005\032\0020\006XT¢\006\002\n\000¨\006\007"}, d2={"Lkotlin/KotlinVersion$Companion;", "", "()V", "CURRENT", "Lkotlin/KotlinVersion;", "MAX_COMPONENT_VALUE", "", "kotlin-stdlib"}, k=1, mv={1, 1, 9})
  public static final class Companion
  {
    private Companion() {}
  }
}
