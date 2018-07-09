package kotlin.text;

import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000\024\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\002\b\020\bÆ\002\030\0002\0020\001B\007\b\002¢\006\002\020\002R\020\020\003\032\0020\0048\006X\004¢\006\002\n\000R\020\020\005\032\0020\0048\006X\004¢\006\002\n\000R\020\020\006\032\0020\0048\006X\004¢\006\002\n\000R\020\020\007\032\0020\0048\006X\004¢\006\002\n\000R\020\020\b\032\0020\0048\006X\004¢\006\002\n\000R\021\020\t\032\0020\0048G¢\006\006\032\004\b\n\020\013R\021\020\f\032\0020\0048G¢\006\006\032\004\b\r\020\013R\021\020\016\032\0020\0048G¢\006\006\032\004\b\017\020\013R\020\020\020\032\0020\0048\006X\004¢\006\002\n\000R\020\020\021\032\004\030\0010\004X\016¢\006\002\n\000R\020\020\022\032\004\030\0010\004X\016¢\006\002\n\000R\020\020\023\032\004\030\0010\004X\016¢\006\002\n\000¨\006\024"}, d2={"Lkotlin/text/Charsets;", "", "()V", "ISO_8859_1", "Ljava/nio/charset/Charset;", "US_ASCII", "UTF_16", "UTF_16BE", "UTF_16LE", "UTF_32", "UTF32", "()Ljava/nio/charset/Charset;", "UTF_32BE", "UTF32_BE", "UTF_32LE", "UTF32_LE", "UTF_8", "utf_32", "utf_32be", "utf_32le", "kotlin-stdlib"}, k=1, mv={1, 1, 9})
public final class Charsets
{
  public static final Charsets INSTANCE = new Charsets();
  @JvmField
  @NotNull
  public static final Charset ISO_8859_1;
  @JvmField
  @NotNull
  public static final Charset US_ASCII;
  @JvmField
  @NotNull
  public static final Charset UTF_16;
  @JvmField
  @NotNull
  public static final Charset UTF_16BE;
  @JvmField
  @NotNull
  public static final Charset UTF_16LE;
  @JvmField
  @NotNull
  public static final Charset UTF_8;
  private static Charset utf_32;
  private static Charset utf_32be;
  private static Charset utf_32le;
  
  static
  {
    Charset localCharset = Charset.forName("UTF-8");
    Intrinsics.checkExpressionValueIsNotNull(localCharset, "Charset.forName(\"UTF-8\")");
    UTF_8 = localCharset;
    localCharset = Charset.forName("UTF-16");
    Intrinsics.checkExpressionValueIsNotNull(localCharset, "Charset.forName(\"UTF-16\")");
    UTF_16 = localCharset;
    localCharset = Charset.forName("UTF-16BE");
    Intrinsics.checkExpressionValueIsNotNull(localCharset, "Charset.forName(\"UTF-16BE\")");
    UTF_16BE = localCharset;
    localCharset = Charset.forName("UTF-16LE");
    Intrinsics.checkExpressionValueIsNotNull(localCharset, "Charset.forName(\"UTF-16LE\")");
    UTF_16LE = localCharset;
    localCharset = Charset.forName("US-ASCII");
    Intrinsics.checkExpressionValueIsNotNull(localCharset, "Charset.forName(\"US-ASCII\")");
    US_ASCII = localCharset;
    localCharset = Charset.forName("ISO-8859-1");
    Intrinsics.checkExpressionValueIsNotNull(localCharset, "Charset.forName(\"ISO-8859-1\")");
    ISO_8859_1 = localCharset;
  }
  
  private Charsets() {}
  
  @JvmName(name="UTF32")
  @NotNull
  public final Charset UTF32()
  {
    Object localObject = utf_32;
    if (localObject != null) {
      return localObject;
    }
    localObject = (Charsets)this;
    localObject = Charset.forName("UTF-32");
    Intrinsics.checkExpressionValueIsNotNull(localObject, "Charset.forName(\"UTF-32\")");
    utf_32 = (Charset)localObject;
    return localObject;
  }
  
  @JvmName(name="UTF32_BE")
  @NotNull
  public final Charset UTF32_BE()
  {
    Object localObject = utf_32be;
    if (localObject != null) {
      return localObject;
    }
    localObject = (Charsets)this;
    localObject = Charset.forName("UTF-32BE");
    Intrinsics.checkExpressionValueIsNotNull(localObject, "Charset.forName(\"UTF-32BE\")");
    utf_32be = (Charset)localObject;
    return localObject;
  }
  
  @JvmName(name="UTF32_LE")
  @NotNull
  public final Charset UTF32_LE()
  {
    Object localObject = utf_32le;
    if (localObject != null) {
      return localObject;
    }
    localObject = (Charsets)this;
    localObject = Charset.forName("UTF-32LE");
    Intrinsics.checkExpressionValueIsNotNull(localObject, "Charset.forName(\"UTF-32LE\")");
    utf_32le = (Charset)localObject;
    return localObject;
  }
}
