package kotlinx.coroutines.experimental.sync;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.experimental.Continuation;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.experimental.selects.SelectInstance;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000,\n\002\030\002\n\002\020\000\n\000\n\002\020\013\n\002\b\004\n\002\020\002\n\002\b\004\n\002\030\002\n\000\n\002\030\002\n\002\030\002\n\002\b\005\bf\030\000 \0242\0020\001:\001\024J\020\020\005\032\0020\0032\006\020\006\032\0020\001H&J\035\020\007\032\0020\b2\n\b\002\020\006\032\004\030\0010\001H¦@ø\001\000¢\006\002\020\tJL\020\n\032\0020\b\"\004\b\000\020\0132\f\020\f\032\b\022\004\022\002H\0130\r2\b\020\006\032\004\030\0010\0012\034\020\016\032\030\b\001\022\n\022\b\022\004\022\002H\0130\020\022\006\022\004\030\0010\0010\017H&ø\001\000¢\006\002\020\021J\024\020\022\032\0020\0032\n\b\002\020\006\032\004\030\0010\001H&J\024\020\023\032\0020\b2\n\b\002\020\006\032\004\030\0010\001H&R\022\020\002\032\0020\003X¦\004¢\006\006\032\004\b\002\020\004\002\004\n\002\b\t¨\006\025"}, d2={"Lkotlinx/coroutines/experimental/sync/Mutex;", "", "isLocked", "", "()Z", "holdsLock", "owner", "lock", "", "(Ljava/lang/Object;Lkotlin/coroutines/experimental/Continuation;)Ljava/lang/Object;", "registerSelectLock", "R", "select", "Lkotlinx/coroutines/experimental/selects/SelectInstance;", "block", "Lkotlin/Function1;", "Lkotlin/coroutines/experimental/Continuation;", "(Lkotlinx/coroutines/experimental/selects/SelectInstance;Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)V", "tryLock", "unlock", "Factory", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
public abstract interface Mutex
{
  public static final Factory Factory = new Factory(null);
  
  public abstract boolean holdsLock(@NotNull Object paramObject);
  
  public abstract boolean isLocked();
  
  @Nullable
  public abstract Object lock(@Nullable Object paramObject, @NotNull Continuation<? super Unit> paramContinuation);
  
  public abstract <R> void registerSelectLock(@NotNull SelectInstance<? super R> paramSelectInstance, @Nullable Object paramObject, @NotNull Function1<? super Continuation<? super R>, ? extends Object> paramFunction1);
  
  public abstract boolean tryLock(@Nullable Object paramObject);
  
  public abstract void unlock(@Nullable Object paramObject);
  
  @Metadata(bv={1, 0, 2}, k=3, mv={1, 1, 7})
  public static final class DefaultImpls {}
  
  @Metadata(bv={1, 0, 2}, d1={"\000\030\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\000\n\002\020\013\n\000\b\003\030\0002\0020\001B\007\b\002¢\006\002\020\002J\023\020\003\032\0020\0042\b\b\002\020\005\032\0020\006H\002¨\006\007"}, d2={"Lkotlinx/coroutines/experimental/sync/Mutex$Factory;", "", "()V", "invoke", "Lkotlinx/coroutines/experimental/sync/Mutex;", "locked", "", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
  public static final class Factory
  {
    private Factory() {}
  }
}
