package kotlin.io;

import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000\024\n\000\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\002\b\003\032\024\020\000\032\0020\001*\0020\0022\b\b\002\020\003\032\0020\004\032\n\020\005\032\0020\001*\0020\002\032\n\020\006\032\0020\001*\0020\002¨\006\007"}, d2={"walk", "Lkotlin/io/FileTreeWalk;", "Ljava/io/File;", "direction", "Lkotlin/io/FileWalkDirection;", "walkBottomUp", "walkTopDown", "kotlin-stdlib"}, k=5, mv={1, 1, 9}, xi=1, xs="kotlin/io/FilesKt")
class FilesKt__FileTreeWalkKt
  extends FilesKt__FileReadWriteKt
{
  public FilesKt__FileTreeWalkKt() {}
  
  @NotNull
  public static final FileTreeWalk walk(@NotNull File paramFile, @NotNull FileWalkDirection paramFileWalkDirection)
  {
    Intrinsics.checkParameterIsNotNull(paramFile, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFileWalkDirection, "direction");
    return new FileTreeWalk(paramFile, paramFileWalkDirection);
  }
  
  @NotNull
  public static final FileTreeWalk walkBottomUp(@NotNull File paramFile)
  {
    Intrinsics.checkParameterIsNotNull(paramFile, "$receiver");
    return FilesKt.walk(paramFile, FileWalkDirection.BOTTOM_UP);
  }
  
  @NotNull
  public static final FileTreeWalk walkTopDown(@NotNull File paramFile)
  {
    Intrinsics.checkParameterIsNotNull(paramFile, "$receiver");
    return FilesKt.walk(paramFile, FileWalkDirection.TOP_DOWN);
  }
}
