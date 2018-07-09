package androidx.graphics;

import android.graphics.Path;
import android.graphics.Path.Op;
import android.graphics.PointF;
import android.support.annotation.RequiresApi;
import java.util.ArrayList;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000\034\n\000\n\002\030\002\n\002\b\002\n\002\020\034\n\002\030\002\n\000\n\002\020\007\n\002\b\005\032\025\020\000\032\0020\001*\0020\0012\006\020\002\032\0020\001H\f\032\034\020\003\032\b\022\004\022\0020\0050\004*\0020\0012\b\b\002\020\006\032\0020\007H\007\032\025\020\b\032\0020\001*\0020\0012\006\020\002\032\0020\001H\n\032\025\020\t\032\0020\001*\0020\0012\006\020\002\032\0020\001H\f\032\025\020\n\032\0020\001*\0020\0012\006\020\002\032\0020\001H\n\032\025\020\013\032\0020\001*\0020\0012\006\020\002\032\0020\001H\f¨\006\f"}, d2={"and", "Landroid/graphics/Path;", "p", "flatten", "", "Landroidx/graphics/PathSegment;", "error", "", "minus", "or", "plus", "xor", "core-ktx_release"}, k=2, mv={1, 1, 9})
public final class PathKt
{
  @RequiresApi(19)
  @NotNull
  public static final Path and(@NotNull Path paramPath1, @NotNull Path paramPath2)
  {
    paramPath1 = new Path(paramPath1);
    paramPath1.op(paramPath2, Path.Op.UNION);
    return paramPath1;
  }
  
  @RequiresApi(26)
  @NotNull
  public static final Iterable<PathSegment> flatten(@NotNull Path paramPath, float paramFloat)
  {
    paramPath = paramPath.approximate(paramFloat);
    int i = 1;
    int j = paramPath.length / 3;
    ArrayList localArrayList = new ArrayList(j / 2);
    while (i < j)
    {
      int k = i * 3;
      int m = (i - 1) * 3;
      paramFloat = paramPath[k];
      float f1 = paramPath[(k + 1)];
      float f2 = paramPath[(k + 2)];
      float f3 = paramPath[m];
      float f4 = paramPath[(m + 1)];
      float f5 = paramPath[(m + 2)];
      if ((paramFloat != f3) && ((f1 != f5) || (f2 != f5))) {
        localArrayList.add(new PathSegment(new PointF(f4, f5), f3, new PointF(f1, f2), paramFloat));
      }
      i += 1;
    }
    return (Iterable)localArrayList;
  }
  
  @RequiresApi(19)
  @NotNull
  public static final Path minus(@NotNull Path paramPath1, @NotNull Path paramPath2)
  {
    paramPath1 = new Path(paramPath1);
    paramPath1.op(paramPath2, Path.Op.DIFFERENCE);
    return paramPath1;
  }
  
  @RequiresApi(19)
  @NotNull
  public static final Path or(@NotNull Path paramPath1, @NotNull Path paramPath2)
  {
    Path localPath = new Path();
    localPath.op(paramPath1, paramPath2, Path.Op.INTERSECT);
    return localPath;
  }
  
  @RequiresApi(19)
  @NotNull
  public static final Path plus(@NotNull Path paramPath1, @NotNull Path paramPath2)
  {
    paramPath1 = new Path(paramPath1);
    paramPath1.op(paramPath2, Path.Op.UNION);
    return paramPath1;
  }
  
  @RequiresApi(19)
  @NotNull
  public static final Path xor(@NotNull Path paramPath1, @NotNull Path paramPath2)
  {
    paramPath1 = new Path(paramPath1);
    paramPath1.op(paramPath2, Path.Op.XOR);
    return paramPath1;
  }
}
