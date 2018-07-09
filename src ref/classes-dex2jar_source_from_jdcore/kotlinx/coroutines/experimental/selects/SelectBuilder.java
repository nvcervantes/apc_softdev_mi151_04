package kotlinx.coroutines.experimental.selects;

import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.coroutines.experimental.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.experimental.Deferred;
import kotlinx.coroutines.experimental.Job;
import kotlinx.coroutines.experimental.channels.ReceiveChannel;
import kotlinx.coroutines.experimental.channels.SendChannel;
import kotlinx.coroutines.experimental.sync.Mutex;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000V\n\002\030\002\n\000\n\002\020\000\n\000\n\002\020\002\n\000\n\002\020\t\n\000\n\002\030\002\n\000\n\002\030\002\n\002\030\002\n\002\b\003\n\002\030\002\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\004\n\002\030\002\n\002\b\003\n\002\030\002\n\002\b\003\bf\030\000*\006\b\000\020\001 \0002\0020\002J@\020\003\032\0020\0042\006\020\005\032\0020\0062\b\b\002\020\007\032\0020\b2\034\020\t\032\030\b\001\022\n\022\b\022\004\022\0028\0000\013\022\006\022\004\030\0010\0020\nH&ø\001\000¢\006\002\020\fJD\020\r\032\0020\004\"\004\b\001\020\016*\b\022\004\022\002H\0160\0172\"\020\t\032\036\b\001\022\004\022\002H\016\022\n\022\b\022\004\022\0028\0000\013\022\006\022\004\030\0010\0020\020H&ø\001\000¢\006\002\020\021J2\020\022\032\0020\004*\0020\0232\034\020\t\032\030\b\001\022\n\022\b\022\004\022\0028\0000\013\022\006\022\004\030\0010\0020\nH&ø\001\000¢\006\002\020\024J>\020\025\032\0020\004*\0020\0262\n\b\002\020\027\032\004\030\0010\0022\034\020\t\032\030\b\001\022\n\022\b\022\004\022\0028\0000\013\022\006\022\004\030\0010\0020\nH&ø\001\000¢\006\002\020\030JD\020\031\032\0020\004\"\004\b\001\020\032*\b\022\004\022\002H\0320\0332\"\020\t\032\036\b\001\022\004\022\002H\032\022\n\022\b\022\004\022\0028\0000\013\022\006\022\004\030\0010\0020\020H&ø\001\000¢\006\002\020\034JF\020\035\032\0020\004\"\004\b\001\020\032*\b\022\004\022\002H\0320\0332$\020\t\032 \b\001\022\006\022\004\030\001H\032\022\n\022\b\022\004\022\0028\0000\013\022\006\022\004\030\0010\0020\020H&ø\001\000¢\006\002\020\034JF\020\036\032\0020\004\"\004\b\001\020\032*\b\022\004\022\002H\0320\0372\006\020 \032\002H\0322\034\020\t\032\030\b\001\022\n\022\b\022\004\022\0028\0000\013\022\006\022\004\030\0010\0020\nH&ø\001\000¢\006\002\020!\002\004\n\002\b\t¨\006\""}, d2={"Lkotlinx/coroutines/experimental/selects/SelectBuilder;", "R", "", "onTimeout", "", "time", "", "unit", "Ljava/util/concurrent/TimeUnit;", "block", "Lkotlin/Function1;", "Lkotlin/coroutines/experimental/Continuation;", "(JLjava/util/concurrent/TimeUnit;Lkotlin/jvm/functions/Function1;)V", "onAwait", "T", "Lkotlinx/coroutines/experimental/Deferred;", "Lkotlin/Function2;", "(Lkotlinx/coroutines/experimental/Deferred;Lkotlin/jvm/functions/Function2;)V", "onJoin", "Lkotlinx/coroutines/experimental/Job;", "(Lkotlinx/coroutines/experimental/Job;Lkotlin/jvm/functions/Function1;)V", "onLock", "Lkotlinx/coroutines/experimental/sync/Mutex;", "owner", "(Lkotlinx/coroutines/experimental/sync/Mutex;Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)V", "onReceive", "E", "Lkotlinx/coroutines/experimental/channels/ReceiveChannel;", "(Lkotlinx/coroutines/experimental/channels/ReceiveChannel;Lkotlin/jvm/functions/Function2;)V", "onReceiveOrNull", "onSend", "Lkotlinx/coroutines/experimental/channels/SendChannel;", "element", "(Lkotlinx/coroutines/experimental/channels/SendChannel;Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)V", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
public abstract interface SelectBuilder<R>
{
  public abstract <T> void onAwait(@NotNull Deferred<? extends T> paramDeferred, @NotNull Function2<? super T, ? super Continuation<? super R>, ? extends Object> paramFunction2);
  
  public abstract void onJoin(@NotNull Job paramJob, @NotNull Function1<? super Continuation<? super R>, ? extends Object> paramFunction1);
  
  public abstract void onLock(@NotNull Mutex paramMutex, @Nullable Object paramObject, @NotNull Function1<? super Continuation<? super R>, ? extends Object> paramFunction1);
  
  public abstract <E> void onReceive(@NotNull ReceiveChannel<? extends E> paramReceiveChannel, @NotNull Function2<? super E, ? super Continuation<? super R>, ? extends Object> paramFunction2);
  
  public abstract <E> void onReceiveOrNull(@NotNull ReceiveChannel<? extends E> paramReceiveChannel, @NotNull Function2<? super E, ? super Continuation<? super R>, ? extends Object> paramFunction2);
  
  public abstract <E> void onSend(@NotNull SendChannel<? super E> paramSendChannel, E paramE, @NotNull Function1<? super Continuation<? super R>, ? extends Object> paramFunction1);
  
  public abstract void onTimeout(long paramLong, @NotNull TimeUnit paramTimeUnit, @NotNull Function1<? super Continuation<? super R>, ? extends Object> paramFunction1);
  
  @Metadata(bv={1, 0, 2}, k=3, mv={1, 1, 7})
  public static final class DefaultImpls {}
}
