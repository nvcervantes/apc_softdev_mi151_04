package androidx.os;

import android.support.v4.os.TraceCompat;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.InlineMarker;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000\022\n\002\b\003\n\002\020\016\n\000\n\002\030\002\n\002\b\002\032*\020\000\032\002H\001\"\004\b\000\020\0012\006\020\002\032\0020\0032\f\020\004\032\b\022\004\022\002H\0010\005H\b¢\006\002\020\006¨\006\007"}, d2={"trace", "T", "sectionName", "", "block", "Lkotlin/Function0;", "(Ljava/lang/String;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "core-ktx_release"}, k=2, mv={1, 1, 9})
public final class TraceKt
{
  public static final <T> T trace(@NotNull String paramString, @NotNull Function0<? extends T> paramFunction0)
  {
    TraceCompat.beginSection(paramString);
    try
    {
      paramString = paramFunction0.invoke();
      return paramString;
    }
    finally
    {
      InlineMarker.finallyStart(1);
      TraceCompat.endSection();
      InlineMarker.finallyEnd(1);
    }
  }
}
