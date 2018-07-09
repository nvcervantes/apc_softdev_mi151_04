package kotlin.text;

import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv={1, 0, 2}, d1={"\000\016\n\000\n\002\030\002\n\000\n\002\020\016\n\000\032\021\020\000\032\0020\0012\006\020\002\032\0020\003H\b¨\006\004"}, d2={"charset", "Ljava/nio/charset/Charset;", "charsetName", "", "kotlin-stdlib"}, k=2, mv={1, 1, 9})
@JvmName(name="CharsetsKt")
public final class CharsetsKt
{
  @InlineOnly
  private static final Charset charset(String paramString)
  {
    paramString = Charset.forName(paramString);
    Intrinsics.checkExpressionValueIsNotNull(paramString, "Charset.forName(charsetName)");
    return paramString;
  }
}
