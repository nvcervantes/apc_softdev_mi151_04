package kotlin.concurrent;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;
import kotlin.Metadata;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.InlineMarker;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000\032\n\002\b\002\n\002\030\002\n\000\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\003\032&\020\000\032\002H\001\"\004\b\000\020\001*\0020\0022\f\020\003\032\b\022\004\022\002H\0010\004H\b¢\006\002\020\005\032&\020\006\032\002H\001\"\004\b\000\020\001*\0020\0072\f\020\003\032\b\022\004\022\002H\0010\004H\b¢\006\002\020\b\032&\020\t\032\002H\001\"\004\b\000\020\001*\0020\0022\f\020\003\032\b\022\004\022\002H\0010\004H\b¢\006\002\020\005¨\006\n"}, d2={"read", "T", "Ljava/util/concurrent/locks/ReentrantReadWriteLock;", "action", "Lkotlin/Function0;", "(Ljava/util/concurrent/locks/ReentrantReadWriteLock;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "withLock", "Ljava/util/concurrent/locks/Lock;", "(Ljava/util/concurrent/locks/Lock;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "write", "kotlin-stdlib"}, k=2, mv={1, 1, 9})
@JvmName(name="LocksKt")
public final class LocksKt
{
  @InlineOnly
  private static final <T> T read(@NotNull ReentrantReadWriteLock paramReentrantReadWriteLock, Function0<? extends T> paramFunction0)
  {
    paramReentrantReadWriteLock = paramReentrantReadWriteLock.readLock();
    paramReentrantReadWriteLock.lock();
    try
    {
      paramFunction0 = paramFunction0.invoke();
      return paramFunction0;
    }
    finally
    {
      InlineMarker.finallyStart(1);
      paramReentrantReadWriteLock.unlock();
      InlineMarker.finallyEnd(1);
    }
  }
  
  @InlineOnly
  private static final <T> T withLock(@NotNull Lock paramLock, Function0<? extends T> paramFunction0)
  {
    paramLock.lock();
    try
    {
      paramFunction0 = paramFunction0.invoke();
      return paramFunction0;
    }
    finally
    {
      InlineMarker.finallyStart(1);
      paramLock.unlock();
      InlineMarker.finallyEnd(1);
    }
  }
  
  @InlineOnly
  private static final <T> T write(@NotNull ReentrantReadWriteLock paramReentrantReadWriteLock, Function0<? extends T> paramFunction0)
  {
    ReentrantReadWriteLock.ReadLock localReadLock = paramReentrantReadWriteLock.readLock();
    int i = paramReentrantReadWriteLock.getWriteHoldCount();
    int m = 0;
    int k = 0;
    if (i == 0) {
      i = paramReentrantReadWriteLock.getReadHoldCount();
    } else {
      i = 0;
    }
    int j = 0;
    while (j < i)
    {
      localReadLock.unlock();
      j += 1;
    }
    paramReentrantReadWriteLock = paramReentrantReadWriteLock.writeLock();
    paramReentrantReadWriteLock.lock();
    try
    {
      paramFunction0 = paramFunction0.invoke();
      return paramFunction0;
    }
    finally
    {
      InlineMarker.finallyStart(1);
      j = m;
      while (j < i)
      {
        localReadLock.lock();
        j += 1;
      }
      paramReentrantReadWriteLock.unlock();
      InlineMarker.finallyEnd(1);
    }
  }
}
