package androidx.graphics.drawable;

import android.graphics.Bitmap;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.support.annotation.RequiresApi;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000\026\n\000\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\002\020\022\n\000\032\r\020\000\032\0020\001*\0020\002H\b\032\r\020\003\032\0020\001*\0020\002H\b\032\r\020\003\032\0020\001*\0020\004H\b\032\r\020\003\032\0020\001*\0020\005H\b¨\006\006"}, d2={"toAdaptiveIcon", "Landroid/graphics/drawable/Icon;", "Landroid/graphics/Bitmap;", "toIcon", "Landroid/net/Uri;", "", "core-ktx_release"}, k=2, mv={1, 1, 9})
public final class IconKt
{
  @RequiresApi(26)
  @NotNull
  public static final Icon toAdaptiveIcon(@NotNull Bitmap paramBitmap)
  {
    paramBitmap = Icon.createWithAdaptiveBitmap(paramBitmap);
    Intrinsics.checkExpressionValueIsNotNull(paramBitmap, "Icon.createWithAdaptiveBitmap(this)");
    return paramBitmap;
  }
  
  @RequiresApi(26)
  @NotNull
  public static final Icon toIcon(@NotNull Bitmap paramBitmap)
  {
    paramBitmap = Icon.createWithBitmap(paramBitmap);
    Intrinsics.checkExpressionValueIsNotNull(paramBitmap, "Icon.createWithBitmap(this)");
    return paramBitmap;
  }
  
  @RequiresApi(26)
  @NotNull
  public static final Icon toIcon(@NotNull Uri paramUri)
  {
    paramUri = Icon.createWithContentUri(paramUri);
    Intrinsics.checkExpressionValueIsNotNull(paramUri, "Icon.createWithContentUri(this)");
    return paramUri;
  }
  
  @RequiresApi(26)
  @NotNull
  public static final Icon toIcon(@NotNull byte[] paramArrayOfByte)
  {
    paramArrayOfByte = Icon.createWithData(paramArrayOfByte, 0, paramArrayOfByte.length);
    Intrinsics.checkExpressionValueIsNotNull(paramArrayOfByte, "Icon.createWithData(this, 0, size)");
    return paramArrayOfByte;
  }
}
