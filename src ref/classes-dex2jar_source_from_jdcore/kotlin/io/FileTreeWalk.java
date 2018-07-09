package kotlin.io;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Stack;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin._Assertions;
import kotlin.collections.AbstractIterator;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000L\n\002\030\002\n\002\030\002\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\002\n\002\030\002\n\002\020\013\n\000\n\002\020\002\n\000\n\002\030\002\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\002\n\002\020\b\n\002\b\002\n\002\020(\n\002\b\006\030\0002\b\022\004\022\0020\0020\001:\003\032\033\034B\031\b\020\022\006\020\003\032\0020\002\022\b\b\002\020\004\032\0020\005¢\006\002\020\006B\001\b\002\022\006\020\003\032\0020\002\022\b\b\002\020\004\032\0020\005\022\024\020\007\032\020\022\004\022\0020\002\022\004\022\0020\t\030\0010\b\022\024\020\n\032\020\022\004\022\0020\002\022\004\022\0020\013\030\0010\b\0228\020\f\0324\022\023\022\0210\002¢\006\f\b\016\022\b\b\017\022\004\b\b(\020\022\023\022\0210\021¢\006\f\b\016\022\b\b\017\022\004\b\b(\022\022\004\022\0020\013\030\0010\r\022\b\b\002\020\023\032\0020\024¢\006\002\020\025J\017\020\026\032\b\022\004\022\0020\0020\027H\002J\016\020\023\032\0020\0002\006\020\030\032\0020\024J\032\020\007\032\0020\0002\022\020\031\032\016\022\004\022\0020\002\022\004\022\0020\t0\bJ \020\f\032\0020\0002\030\020\031\032\024\022\004\022\0020\002\022\004\022\0020\021\022\004\022\0020\0130\rJ\032\020\n\032\0020\0002\022\020\031\032\016\022\004\022\0020\002\022\004\022\0020\0130\bR\016\020\004\032\0020\005X\004¢\006\002\n\000R\016\020\023\032\0020\024X\004¢\006\002\n\000R\034\020\007\032\020\022\004\022\0020\002\022\004\022\0020\t\030\0010\bX\004¢\006\002\n\000R@\020\f\0324\022\023\022\0210\002¢\006\f\b\016\022\b\b\017\022\004\b\b(\020\022\023\022\0210\021¢\006\f\b\016\022\b\b\017\022\004\b\b(\022\022\004\022\0020\013\030\0010\rX\004¢\006\002\n\000R\034\020\n\032\020\022\004\022\0020\002\022\004\022\0020\013\030\0010\bX\004¢\006\002\n\000R\016\020\003\032\0020\002X\004¢\006\002\n\000¨\006\035"}, d2={"Lkotlin/io/FileTreeWalk;", "Lkotlin/sequences/Sequence;", "Ljava/io/File;", "start", "direction", "Lkotlin/io/FileWalkDirection;", "(Ljava/io/File;Lkotlin/io/FileWalkDirection;)V", "onEnter", "Lkotlin/Function1;", "", "onLeave", "", "onFail", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "f", "Ljava/io/IOException;", "e", "maxDepth", "", "(Ljava/io/File;Lkotlin/io/FileWalkDirection;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function2;I)V", "iterator", "", "depth", "function", "DirectoryState", "FileTreeWalkIterator", "WalkState", "kotlin-stdlib"}, k=1, mv={1, 1, 9})
public final class FileTreeWalk
  implements Sequence<File>
{
  private final FileWalkDirection direction;
  private final int maxDepth;
  private final Function1<File, Boolean> onEnter;
  private final Function2<File, IOException, Unit> onFail;
  private final Function1<File, Unit> onLeave;
  private final File start;
  
  public FileTreeWalk(@NotNull File paramFile, @NotNull FileWalkDirection paramFileWalkDirection)
  {
    this(paramFile, paramFileWalkDirection, null, null, null, 0, 32, null);
  }
  
  private FileTreeWalk(File paramFile, FileWalkDirection paramFileWalkDirection, Function1<? super File, Boolean> paramFunction1, Function1<? super File, Unit> paramFunction11, Function2<? super File, ? super IOException, Unit> paramFunction2, int paramInt)
  {
    start = paramFile;
    direction = paramFileWalkDirection;
    onEnter = paramFunction1;
    onLeave = paramFunction11;
    onFail = paramFunction2;
    maxDepth = paramInt;
  }
  
  @NotNull
  public Iterator<File> iterator()
  {
    return (Iterator)new FileTreeWalkIterator();
  }
  
  @NotNull
  public final FileTreeWalk maxDepth(int paramInt)
  {
    if (paramInt <= 0)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("depth must be positive, but was ");
      localStringBuilder.append(paramInt);
      localStringBuilder.append('.');
      throw ((Throwable)new IllegalArgumentException(localStringBuilder.toString()));
    }
    return new FileTreeWalk(start, direction, onEnter, onLeave, onFail, paramInt);
  }
  
  @NotNull
  public final FileTreeWalk onEnter(@NotNull Function1<? super File, Boolean> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramFunction1, "function");
    return new FileTreeWalk(start, direction, paramFunction1, onLeave, onFail, maxDepth);
  }
  
  @NotNull
  public final FileTreeWalk onFail(@NotNull Function2<? super File, ? super IOException, Unit> paramFunction2)
  {
    Intrinsics.checkParameterIsNotNull(paramFunction2, "function");
    return new FileTreeWalk(start, direction, onEnter, onLeave, paramFunction2, maxDepth);
  }
  
  @NotNull
  public final FileTreeWalk onLeave(@NotNull Function1<? super File, Unit> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramFunction1, "function");
    return new FileTreeWalk(start, direction, onEnter, paramFunction1, onFail, maxDepth);
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\000\022\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\002\b\002\b\"\030\0002\0020\001B\r\022\006\020\002\032\0020\003¢\006\002\020\004¨\006\005"}, d2={"Lkotlin/io/FileTreeWalk$DirectoryState;", "Lkotlin/io/FileTreeWalk$WalkState;", "rootDir", "Ljava/io/File;", "(Ljava/io/File;)V", "kotlin-stdlib"}, k=1, mv={1, 1, 9})
  private static abstract class DirectoryState
    extends FileTreeWalk.WalkState
  {
    public DirectoryState(@NotNull File paramFile)
    {
      super();
      if (_Assertions.ENABLED)
      {
        boolean bool = paramFile.isDirectory();
        if ((_Assertions.ENABLED) && (!bool)) {
          throw ((Throwable)new AssertionError("rootDir must be verified to be directory beforehand."));
        }
      }
    }
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\000(\n\002\030\002\n\002\030\002\n\002\030\002\n\002\b\002\n\002\030\002\n\002\030\002\n\000\n\002\020\002\n\000\n\002\030\002\n\002\b\006\b\004\030\0002\b\022\004\022\0020\0020\001:\003\r\016\017B\005¢\006\002\020\003J\b\020\007\032\0020\bH\024J\020\020\t\032\0020\n2\006\020\013\032\0020\002H\002J\013\020\f\032\004\030\0010\002H\020R\024\020\004\032\b\022\004\022\0020\0060\005X\004¢\006\002\n\000¨\006\020"}, d2={"Lkotlin/io/FileTreeWalk$FileTreeWalkIterator;", "Lkotlin/collections/AbstractIterator;", "Ljava/io/File;", "(Lkotlin/io/FileTreeWalk;)V", "state", "Ljava/util/Stack;", "Lkotlin/io/FileTreeWalk$WalkState;", "computeNext", "", "directoryState", "Lkotlin/io/FileTreeWalk$DirectoryState;", "root", "gotoNext", "BottomUpDirectoryState", "SingleFileState", "TopDownDirectoryState", "kotlin-stdlib"}, k=1, mv={1, 1, 9})
  private final class FileTreeWalkIterator
    extends AbstractIterator<File>
  {
    private final Stack<FileTreeWalk.WalkState> state = new Stack();
    
    public FileTreeWalkIterator()
    {
      if (FileTreeWalk.access$getStart$p(this$1).isDirectory())
      {
        state.push(directoryState(FileTreeWalk.access$getStart$p(this$1)));
        return;
      }
      if (FileTreeWalk.access$getStart$p(this$1).isFile())
      {
        state.push(new SingleFileState(FileTreeWalk.access$getStart$p(this$1)));
        return;
      }
      done();
    }
    
    private final FileTreeWalk.DirectoryState directoryState(File paramFile)
    {
      FileWalkDirection localFileWalkDirection = FileTreeWalk.access$getDirection$p(this$0);
      switch (FileTreeWalk.FileTreeWalkIterator.WhenMappings.$EnumSwitchMapping$0[localFileWalkDirection.ordinal()])
      {
      default: 
        throw new NoWhenBranchMatchedException();
      case 2: 
        return (FileTreeWalk.DirectoryState)new BottomUpDirectoryState(paramFile);
      }
      return (FileTreeWalk.DirectoryState)new TopDownDirectoryState(paramFile);
    }
    
    private final File gotoNext()
    {
      File localFile;
      for (;;)
      {
        if (state.empty()) {
          return null;
        }
        Object localObject = state.peek();
        if (localObject == null) {
          Intrinsics.throwNpe();
        }
        localObject = (FileTreeWalk.WalkState)localObject;
        localFile = ((FileTreeWalk.WalkState)localObject).step();
        if (localFile == null)
        {
          state.pop();
        }
        else
        {
          if ((Intrinsics.areEqual(localFile, ((FileTreeWalk.WalkState)localObject).getRoot())) || (!localFile.isDirectory())) {
            break;
          }
          if (state.size() >= FileTreeWalk.access$getMaxDepth$p(this$0)) {
            return localFile;
          }
          state.push(directoryState(localFile));
        }
      }
      return localFile;
    }
    
    protected void computeNext()
    {
      File localFile = gotoNext();
      if (localFile != null)
      {
        setNext(localFile);
        return;
      }
      done();
    }
    
    @Metadata(bv={1, 0, 2}, d1={"\000&\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\002\b\002\n\002\020\013\n\000\n\002\020\b\n\000\n\002\020\021\n\002\b\004\b\004\030\0002\0020\001B\r\022\006\020\002\032\0020\003¢\006\002\020\004J\n\020\r\032\004\030\0010\003H\026R\016\020\005\032\0020\006X\016¢\006\002\n\000R\016\020\007\032\0020\bX\016¢\006\002\n\000R\030\020\t\032\n\022\004\022\0020\003\030\0010\nX\016¢\006\004\n\002\020\013R\016\020\f\032\0020\006X\016¢\006\002\n\000¨\006\016"}, d2={"Lkotlin/io/FileTreeWalk$FileTreeWalkIterator$BottomUpDirectoryState;", "Lkotlin/io/FileTreeWalk$DirectoryState;", "rootDir", "Ljava/io/File;", "(Lkotlin/io/FileTreeWalk$FileTreeWalkIterator;Ljava/io/File;)V", "failed", "", "fileIndex", "", "fileList", "", "[Ljava/io/File;", "rootVisited", "step", "kotlin-stdlib"}, k=1, mv={1, 1, 9})
    private final class BottomUpDirectoryState
      extends FileTreeWalk.DirectoryState
    {
      private boolean failed;
      private int fileIndex;
      private File[] fileList;
      private boolean rootVisited;
      
      public BottomUpDirectoryState()
      {
        super();
      }
      
      @Nullable
      public File step()
      {
        if ((!failed) && (fileList == null))
        {
          localObject = FileTreeWalk.access$getOnEnter$p(this$0);
          if ((localObject != null) && (!((Boolean)((Function1)localObject).invoke(getRoot())).booleanValue())) {
            return null;
          }
          fileList = getRoot().listFiles();
          if (fileList == null)
          {
            localObject = FileTreeWalk.access$getOnFail$p(this$0);
            if (localObject != null) {
              localObject = (Unit)((Function2)localObject).invoke(getRoot(), new AccessDeniedException(getRoot(), null, "Cannot list files in a directory", 2, null));
            }
            failed = true;
          }
        }
        if (fileList != null)
        {
          int i = fileIndex;
          localObject = fileList;
          if (localObject == null) {
            Intrinsics.throwNpe();
          }
          if (i < ((Object[])localObject).length)
          {
            localObject = fileList;
            if (localObject == null) {
              Intrinsics.throwNpe();
            }
            i = fileIndex;
            fileIndex = (i + 1);
            return localObject[i];
          }
        }
        if (!rootVisited)
        {
          rootVisited = true;
          return getRoot();
        }
        Object localObject = FileTreeWalk.access$getOnLeave$p(this$0);
        if (localObject != null) {
          localObject = (Unit)((Function1)localObject).invoke(getRoot());
        }
        return null;
      }
    }
    
    @Metadata(bv={1, 0, 2}, d1={"\000\032\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\002\b\002\n\002\020\013\n\002\b\002\b\004\030\0002\0020\001B\r\022\006\020\002\032\0020\003¢\006\002\020\004J\n\020\007\032\004\030\0010\003H\026R\016\020\005\032\0020\006X\016¢\006\002\n\000¨\006\b"}, d2={"Lkotlin/io/FileTreeWalk$FileTreeWalkIterator$SingleFileState;", "Lkotlin/io/FileTreeWalk$WalkState;", "rootFile", "Ljava/io/File;", "(Lkotlin/io/FileTreeWalk$FileTreeWalkIterator;Ljava/io/File;)V", "visited", "", "step", "kotlin-stdlib"}, k=1, mv={1, 1, 9})
    private final class SingleFileState
      extends FileTreeWalk.WalkState
    {
      private boolean visited;
      
      public SingleFileState()
      {
        super();
        if (_Assertions.ENABLED)
        {
          boolean bool = localObject.isFile();
          if ((_Assertions.ENABLED) && (!bool)) {
            throw ((Throwable)new AssertionError("rootFile must be verified to be file beforehand."));
          }
        }
      }
      
      @Nullable
      public File step()
      {
        if (visited) {
          return null;
        }
        visited = true;
        return getRoot();
      }
    }
    
    @Metadata(bv={1, 0, 2}, d1={"\000(\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\002\b\002\n\002\020\b\n\000\n\002\020\021\n\002\b\002\n\002\020\013\n\002\b\002\b\004\030\0002\0020\001B\r\022\006\020\002\032\0020\003¢\006\002\020\004J\n\020\f\032\004\030\0010\003H\026R\016\020\005\032\0020\006X\016¢\006\002\n\000R\030\020\007\032\n\022\004\022\0020\003\030\0010\bX\016¢\006\004\n\002\020\tR\016\020\n\032\0020\013X\016¢\006\002\n\000¨\006\r"}, d2={"Lkotlin/io/FileTreeWalk$FileTreeWalkIterator$TopDownDirectoryState;", "Lkotlin/io/FileTreeWalk$DirectoryState;", "rootDir", "Ljava/io/File;", "(Lkotlin/io/FileTreeWalk$FileTreeWalkIterator;Ljava/io/File;)V", "fileIndex", "", "fileList", "", "[Ljava/io/File;", "rootVisited", "", "step", "kotlin-stdlib"}, k=1, mv={1, 1, 9})
    private final class TopDownDirectoryState
      extends FileTreeWalk.DirectoryState
    {
      private int fileIndex;
      private File[] fileList;
      private boolean rootVisited;
      
      public TopDownDirectoryState()
      {
        super();
      }
      
      @Nullable
      public File step()
      {
        if (!rootVisited)
        {
          localObject = FileTreeWalk.access$getOnEnter$p(this$0);
          if ((localObject != null) && (!((Boolean)((Function1)localObject).invoke(getRoot())).booleanValue())) {
            return null;
          }
          rootVisited = true;
          return getRoot();
        }
        if (fileList != null)
        {
          i = fileIndex;
          localObject = fileList;
          if (localObject == null) {
            Intrinsics.throwNpe();
          }
          if (i >= ((Object[])localObject).length)
          {
            localObject = FileTreeWalk.access$getOnLeave$p(this$0);
            if (localObject != null) {
              localObject = (Unit)((Function1)localObject).invoke(getRoot());
            }
            return null;
          }
        }
        if (fileList == null)
        {
          fileList = getRoot().listFiles();
          if (fileList == null)
          {
            localObject = FileTreeWalk.access$getOnFail$p(this$0);
            if (localObject != null) {
              localObject = (Unit)((Function2)localObject).invoke(getRoot(), new AccessDeniedException(getRoot(), null, "Cannot list files in a directory", 2, null));
            }
          }
          if (fileList != null)
          {
            localObject = fileList;
            if (localObject == null) {
              Intrinsics.throwNpe();
            }
            if (((Object[])localObject).length != 0) {}
          }
          else
          {
            localObject = FileTreeWalk.access$getOnLeave$p(this$0);
            if (localObject != null) {
              localObject = (Unit)((Function1)localObject).invoke(getRoot());
            }
            return null;
          }
        }
        Object localObject = fileList;
        if (localObject == null) {
          Intrinsics.throwNpe();
        }
        int i = fileIndex;
        fileIndex = (i + 1);
        return localObject[i];
      }
    }
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\000\022\n\002\030\002\n\002\020\000\n\000\n\002\030\002\n\002\b\005\b\"\030\0002\0020\001B\r\022\006\020\002\032\0020\003¢\006\002\020\004J\n\020\007\032\004\030\0010\003H&R\021\020\002\032\0020\003¢\006\b\n\000\032\004\b\005\020\006¨\006\b"}, d2={"Lkotlin/io/FileTreeWalk$WalkState;", "", "root", "Ljava/io/File;", "(Ljava/io/File;)V", "getRoot", "()Ljava/io/File;", "step", "kotlin-stdlib"}, k=1, mv={1, 1, 9})
  private static abstract class WalkState
  {
    @NotNull
    private final File root;
    
    public WalkState(@NotNull File paramFile)
    {
      root = paramFile;
    }
    
    @NotNull
    public final File getRoot()
    {
      return root;
    }
    
    @Nullable
    public abstract File step();
  }
}
