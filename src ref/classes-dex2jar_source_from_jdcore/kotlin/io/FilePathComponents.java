package kotlin.io;

import java.io.File;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\0000\n\002\030\002\n\002\020\000\n\000\n\002\030\002\n\000\n\002\020 \n\002\b\002\n\002\020\013\n\002\b\004\n\002\020\016\n\002\b\005\n\002\020\b\n\002\b\r\b\b\030\0002\0020\001B\035\b\000\022\006\020\002\032\0020\003\022\f\020\004\032\b\022\004\022\0020\0030\005¢\006\002\020\006J\t\020\026\032\0020\003HÆ\003J\017\020\027\032\b\022\004\022\0020\0030\005HÆ\003J#\020\030\032\0020\0002\b\b\002\020\002\032\0020\0032\016\b\002\020\004\032\b\022\004\022\0020\0030\005HÆ\001J\023\020\031\032\0020\b2\b\020\032\032\004\030\0010\001HÖ\003J\t\020\033\032\0020\023HÖ\001J\026\020\034\032\0020\0032\006\020\035\032\0020\0232\006\020\036\032\0020\023J\t\020\037\032\0020\rHÖ\001R\021\020\007\032\0020\b8F¢\006\006\032\004\b\007\020\tR\021\020\002\032\0020\003¢\006\b\n\000\032\004\b\n\020\013R\021\020\f\032\0020\r8F¢\006\006\032\004\b\016\020\017R\027\020\004\032\b\022\004\022\0020\0030\005¢\006\b\n\000\032\004\b\020\020\021R\021\020\022\032\0020\0238F¢\006\006\032\004\b\024\020\025¨\006 "}, d2={"Lkotlin/io/FilePathComponents;", "", "root", "Ljava/io/File;", "segments", "", "(Ljava/io/File;Ljava/util/List;)V", "isRooted", "", "()Z", "getRoot", "()Ljava/io/File;", "rootName", "", "getRootName", "()Ljava/lang/String;", "getSegments", "()Ljava/util/List;", "size", "", "getSize", "()I", "component1", "component2", "copy", "equals", "other", "hashCode", "subPath", "beginIndex", "endIndex", "toString", "kotlin-stdlib"}, k=1, mv={1, 1, 9})
public final class FilePathComponents
{
  @NotNull
  private final File root;
  @NotNull
  private final List<File> segments;
  
  public FilePathComponents(@NotNull File paramFile, @NotNull List<? extends File> paramList)
  {
    root = paramFile;
    segments = paramList;
  }
  
  @NotNull
  public final File component1()
  {
    return root;
  }
  
  @NotNull
  public final List<File> component2()
  {
    return segments;
  }
  
  @NotNull
  public final FilePathComponents copy(@NotNull File paramFile, @NotNull List<? extends File> paramList)
  {
    Intrinsics.checkParameterIsNotNull(paramFile, "root");
    Intrinsics.checkParameterIsNotNull(paramList, "segments");
    return new FilePathComponents(paramFile, paramList);
  }
  
  public boolean equals(Object paramObject)
  {
    if (this != paramObject) {
      if ((paramObject instanceof FilePathComponents))
      {
        paramObject = (FilePathComponents)paramObject;
        if ((Intrinsics.areEqual(root, root)) && (Intrinsics.areEqual(segments, segments))) {}
      }
      else
      {
        return false;
      }
    }
    return true;
  }
  
  @NotNull
  public final File getRoot()
  {
    return root;
  }
  
  @NotNull
  public final String getRootName()
  {
    String str = root.getPath();
    Intrinsics.checkExpressionValueIsNotNull(str, "root.path");
    return str;
  }
  
  @NotNull
  public final List<File> getSegments()
  {
    return segments;
  }
  
  public final int getSize()
  {
    return segments.size();
  }
  
  public int hashCode()
  {
    Object localObject = root;
    int j = 0;
    int i;
    if (localObject != null) {
      i = localObject.hashCode();
    } else {
      i = 0;
    }
    localObject = segments;
    if (localObject != null) {
      j = localObject.hashCode();
    }
    return i * 31 + j;
  }
  
  public final boolean isRooted()
  {
    String str = root.getPath();
    Intrinsics.checkExpressionValueIsNotNull(str, "root.path");
    return ((CharSequence)str).length() > 0;
  }
  
  @NotNull
  public final File subPath(int paramInt1, int paramInt2)
  {
    if ((paramInt1 >= 0) && (paramInt1 <= paramInt2) && (paramInt2 <= getSize()))
    {
      Iterable localIterable = (Iterable)segments.subList(paramInt1, paramInt2);
      String str = File.separator;
      Intrinsics.checkExpressionValueIsNotNull(str, "File.separator");
      return new File(CollectionsKt.joinToString$default(localIterable, (CharSequence)str, null, null, 0, null, null, 62, null));
    }
    throw ((Throwable)new IllegalArgumentException());
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("FilePathComponents(root=");
    localStringBuilder.append(root);
    localStringBuilder.append(", segments=");
    localStringBuilder.append(segments);
    localStringBuilder.append(")");
    return localStringBuilder.toString();
  }
}
