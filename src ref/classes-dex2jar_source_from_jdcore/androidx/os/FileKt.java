package androidx.os;

import android.net.Uri;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000\f\n\000\n\002\030\002\n\002\030\002\n\000\032\r\020\000\032\0020\001*\0020\002H\b¨\006\003"}, d2={"toUri", "Landroid/net/Uri;", "Ljava/io/File;", "core-ktx_release"}, k=2, mv={1, 1, 9})
public final class FileKt
{
  @NotNull
  public static final Uri toUri(@NotNull File paramFile)
  {
    paramFile = Uri.fromFile(paramFile);
    Intrinsics.checkExpressionValueIsNotNull(paramFile, "Uri.fromFile(this)");
    return paramFile;
  }
}
