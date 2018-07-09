package androidx.database;

import android.database.Cursor;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000:\n\000\n\002\020\022\n\002\030\002\n\000\n\002\020\016\n\002\b\002\n\002\020\b\n\000\n\002\020\006\n\002\b\004\n\002\020\007\n\002\b\b\n\002\020\t\n\002\b\004\n\002\020\n\n\002\b\006\032\025\020\000\032\0020\001*\0020\0022\006\020\003\032\0020\004H\b\032\027\020\005\032\004\030\0010\001*\0020\0022\006\020\006\032\0020\007H\b\032\027\020\005\032\004\030\0010\001*\0020\0022\006\020\003\032\0020\004H\b\032\025\020\b\032\0020\t*\0020\0022\006\020\003\032\0020\004H\b\032\034\020\n\032\004\030\0010\t*\0020\0022\006\020\006\032\0020\007H\b¢\006\002\020\013\032\034\020\n\032\004\030\0010\t*\0020\0022\006\020\003\032\0020\004H\b¢\006\002\020\f\032\025\020\r\032\0020\016*\0020\0022\006\020\003\032\0020\004H\b\032\034\020\017\032\004\030\0010\016*\0020\0022\006\020\006\032\0020\007H\b¢\006\002\020\020\032\034\020\017\032\004\030\0010\016*\0020\0022\006\020\003\032\0020\004H\b¢\006\002\020\021\032\025\020\022\032\0020\007*\0020\0022\006\020\003\032\0020\004H\b\032\034\020\023\032\004\030\0010\007*\0020\0022\006\020\006\032\0020\007H\b¢\006\002\020\024\032\034\020\023\032\004\030\0010\007*\0020\0022\006\020\003\032\0020\004H\b¢\006\002\020\025\032\025\020\026\032\0020\027*\0020\0022\006\020\003\032\0020\004H\b\032\034\020\030\032\004\030\0010\027*\0020\0022\006\020\006\032\0020\007H\b¢\006\002\020\031\032\034\020\030\032\004\030\0010\027*\0020\0022\006\020\003\032\0020\004H\b¢\006\002\020\032\032\025\020\033\032\0020\034*\0020\0022\006\020\003\032\0020\004H\b\032\034\020\035\032\004\030\0010\034*\0020\0022\006\020\006\032\0020\007H\b¢\006\002\020\036\032\034\020\035\032\004\030\0010\034*\0020\0022\006\020\003\032\0020\004H\b¢\006\002\020\037\032\025\020 \032\0020\004*\0020\0022\006\020\003\032\0020\004H\b\032\027\020!\032\004\030\0010\004*\0020\0022\006\020\006\032\0020\007H\b\032\027\020!\032\004\030\0010\004*\0020\0022\006\020\003\032\0020\004H\b¨\006\""}, d2={"getBlob", "", "Landroid/database/Cursor;", "columnName", "", "getBlobOrNull", "index", "", "getDouble", "", "getDoubleOrNull", "(Landroid/database/Cursor;I)Ljava/lang/Double;", "(Landroid/database/Cursor;Ljava/lang/String;)Ljava/lang/Double;", "getFloat", "", "getFloatOrNull", "(Landroid/database/Cursor;I)Ljava/lang/Float;", "(Landroid/database/Cursor;Ljava/lang/String;)Ljava/lang/Float;", "getInt", "getIntOrNull", "(Landroid/database/Cursor;I)Ljava/lang/Integer;", "(Landroid/database/Cursor;Ljava/lang/String;)Ljava/lang/Integer;", "getLong", "", "getLongOrNull", "(Landroid/database/Cursor;I)Ljava/lang/Long;", "(Landroid/database/Cursor;Ljava/lang/String;)Ljava/lang/Long;", "getShort", "", "getShortOrNull", "(Landroid/database/Cursor;I)Ljava/lang/Short;", "(Landroid/database/Cursor;Ljava/lang/String;)Ljava/lang/Short;", "getString", "getStringOrNull", "core-ktx_release"}, k=2, mv={1, 1, 9})
public final class CursorKt
{
  @NotNull
  public static final byte[] getBlob(@NotNull Cursor paramCursor, @NotNull String paramString)
  {
    paramCursor = paramCursor.getBlob(paramCursor.getColumnIndexOrThrow(paramString));
    Intrinsics.checkExpressionValueIsNotNull(paramCursor, "getBlob(getColumnIndexOrThrow(columnName))");
    return paramCursor;
  }
  
  @Nullable
  public static final byte[] getBlobOrNull(@NotNull Cursor paramCursor, int paramInt)
  {
    if (paramCursor.isNull(paramInt)) {
      return null;
    }
    return paramCursor.getBlob(paramInt);
  }
  
  @Nullable
  public static final byte[] getBlobOrNull(@NotNull Cursor paramCursor, @NotNull String paramString)
  {
    int i = paramCursor.getColumnIndexOrThrow(paramString);
    if (paramCursor.isNull(i)) {
      return null;
    }
    return paramCursor.getBlob(i);
  }
  
  public static final double getDouble(@NotNull Cursor paramCursor, @NotNull String paramString)
  {
    return paramCursor.getDouble(paramCursor.getColumnIndexOrThrow(paramString));
  }
  
  @Nullable
  public static final Double getDoubleOrNull(@NotNull Cursor paramCursor, int paramInt)
  {
    if (paramCursor.isNull(paramInt)) {
      return null;
    }
    return Double.valueOf(paramCursor.getDouble(paramInt));
  }
  
  @Nullable
  public static final Double getDoubleOrNull(@NotNull Cursor paramCursor, @NotNull String paramString)
  {
    int i = paramCursor.getColumnIndexOrThrow(paramString);
    if (paramCursor.isNull(i)) {
      return null;
    }
    return Double.valueOf(paramCursor.getDouble(i));
  }
  
  public static final float getFloat(@NotNull Cursor paramCursor, @NotNull String paramString)
  {
    return paramCursor.getFloat(paramCursor.getColumnIndexOrThrow(paramString));
  }
  
  @Nullable
  public static final Float getFloatOrNull(@NotNull Cursor paramCursor, int paramInt)
  {
    if (paramCursor.isNull(paramInt)) {
      return null;
    }
    return Float.valueOf(paramCursor.getFloat(paramInt));
  }
  
  @Nullable
  public static final Float getFloatOrNull(@NotNull Cursor paramCursor, @NotNull String paramString)
  {
    int i = paramCursor.getColumnIndexOrThrow(paramString);
    if (paramCursor.isNull(i)) {
      return null;
    }
    return Float.valueOf(paramCursor.getFloat(i));
  }
  
  public static final int getInt(@NotNull Cursor paramCursor, @NotNull String paramString)
  {
    return paramCursor.getInt(paramCursor.getColumnIndexOrThrow(paramString));
  }
  
  @Nullable
  public static final Integer getIntOrNull(@NotNull Cursor paramCursor, int paramInt)
  {
    if (paramCursor.isNull(paramInt)) {
      return null;
    }
    return Integer.valueOf(paramCursor.getInt(paramInt));
  }
  
  @Nullable
  public static final Integer getIntOrNull(@NotNull Cursor paramCursor, @NotNull String paramString)
  {
    int i = paramCursor.getColumnIndexOrThrow(paramString);
    if (paramCursor.isNull(i)) {
      return null;
    }
    return Integer.valueOf(paramCursor.getInt(i));
  }
  
  public static final long getLong(@NotNull Cursor paramCursor, @NotNull String paramString)
  {
    return paramCursor.getLong(paramCursor.getColumnIndexOrThrow(paramString));
  }
  
  @Nullable
  public static final Long getLongOrNull(@NotNull Cursor paramCursor, int paramInt)
  {
    if (paramCursor.isNull(paramInt)) {
      return null;
    }
    return Long.valueOf(paramCursor.getLong(paramInt));
  }
  
  @Nullable
  public static final Long getLongOrNull(@NotNull Cursor paramCursor, @NotNull String paramString)
  {
    int i = paramCursor.getColumnIndexOrThrow(paramString);
    if (paramCursor.isNull(i)) {
      return null;
    }
    return Long.valueOf(paramCursor.getLong(i));
  }
  
  public static final short getShort(@NotNull Cursor paramCursor, @NotNull String paramString)
  {
    return paramCursor.getShort(paramCursor.getColumnIndexOrThrow(paramString));
  }
  
  @Nullable
  public static final Short getShortOrNull(@NotNull Cursor paramCursor, int paramInt)
  {
    if (paramCursor.isNull(paramInt)) {
      return null;
    }
    return Short.valueOf(paramCursor.getShort(paramInt));
  }
  
  @Nullable
  public static final Short getShortOrNull(@NotNull Cursor paramCursor, @NotNull String paramString)
  {
    int i = paramCursor.getColumnIndexOrThrow(paramString);
    if (paramCursor.isNull(i)) {
      return null;
    }
    return Short.valueOf(paramCursor.getShort(i));
  }
  
  @NotNull
  public static final String getString(@NotNull Cursor paramCursor, @NotNull String paramString)
  {
    paramCursor = paramCursor.getString(paramCursor.getColumnIndexOrThrow(paramString));
    Intrinsics.checkExpressionValueIsNotNull(paramCursor, "getString(getColumnIndexOrThrow(columnName))");
    return paramCursor;
  }
  
  @Nullable
  public static final String getStringOrNull(@NotNull Cursor paramCursor, int paramInt)
  {
    if (paramCursor.isNull(paramInt)) {
      return null;
    }
    return paramCursor.getString(paramInt);
  }
  
  @Nullable
  public static final String getStringOrNull(@NotNull Cursor paramCursor, @NotNull String paramString)
  {
    int i = paramCursor.getColumnIndexOrThrow(paramString);
    if (paramCursor.isNull(i)) {
      return null;
    }
    return paramCursor.getString(i);
  }
}
