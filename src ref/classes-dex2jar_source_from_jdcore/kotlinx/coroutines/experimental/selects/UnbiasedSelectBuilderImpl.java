package kotlinx.coroutines.experimental.selects;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.Unit;
import kotlin.coroutines.experimental.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.experimental.Deferred;
import kotlinx.coroutines.experimental.Job;
import kotlinx.coroutines.experimental.channels.ReceiveChannel;
import kotlinx.coroutines.experimental.channels.SendChannel;
import kotlinx.coroutines.experimental.sync.Mutex;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000~\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\002\b\002\n\002\030\002\n\002\030\002\n\002\020\002\n\002\030\002\n\002\b\003\n\002\030\002\n\002\b\004\n\002\020\003\n\000\n\002\020\000\n\002\b\002\n\002\020\t\n\000\n\002\030\002\n\000\n\002\030\002\n\002\b\003\n\002\030\002\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\004\n\002\030\002\n\002\b\003\n\002\030\002\n\002\b\003\b\001\030\000*\006\b\000\020\001 \0002\b\022\004\022\002H\0010\002B\023\022\f\020\003\032\b\022\004\022\0028\0000\004¢\006\002\020\005J\020\020\021\032\0020\t2\006\020\022\032\0020\023H\001J\n\020\024\032\004\030\0010\025H\001J>\020\026\032\0020\t2\006\020\027\032\0020\0302\006\020\031\032\0020\0322\034\020\033\032\030\b\001\022\n\022\b\022\004\022\0028\0000\004\022\006\022\004\030\0010\0250\034H\026ø\001\000¢\006\002\020\035JD\020\036\032\0020\t\"\004\b\001\020\037*\b\022\004\022\002H\0370 2\"\020\033\032\036\b\001\022\004\022\002H\037\022\n\022\b\022\004\022\0028\0000\004\022\006\022\004\030\0010\0250!H\026ø\001\000¢\006\002\020\"J2\020#\032\0020\t*\0020$2\034\020\033\032\030\b\001\022\n\022\b\022\004\022\0028\0000\004\022\006\022\004\030\0010\0250\034H\026ø\001\000¢\006\002\020%J<\020&\032\0020\t*\0020'2\b\020(\032\004\030\0010\0252\034\020\033\032\030\b\001\022\n\022\b\022\004\022\0028\0000\004\022\006\022\004\030\0010\0250\034H\026ø\001\000¢\006\002\020)JD\020*\032\0020\t\"\004\b\001\020+*\b\022\004\022\002H+0,2\"\020\033\032\036\b\001\022\004\022\002H+\022\n\022\b\022\004\022\0028\0000\004\022\006\022\004\030\0010\0250!H\026ø\001\000¢\006\002\020-JF\020.\032\0020\t\"\004\b\001\020+*\b\022\004\022\002H+0,2$\020\033\032 \b\001\022\006\022\004\030\001H+\022\n\022\b\022\004\022\0028\0000\004\022\006\022\004\030\0010\0250!H\026ø\001\000¢\006\002\020-JF\020/\032\0020\t\"\004\b\001\020+*\b\022\004\022\002H+002\006\0201\032\002H+2\034\020\033\032\030\b\001\022\n\022\b\022\004\022\0028\0000\004\022\006\022\004\030\0010\0250\034H\026ø\001\000¢\006\002\0202R-\020\006\032\036\022\n\022\b\022\004\022\0020\t0\b0\007j\016\022\n\022\b\022\004\022\0020\t0\b`\n¢\006\b\n\000\032\004\b\013\020\fR\027\020\r\032\b\022\004\022\0028\0000\016¢\006\b\n\000\032\004\b\017\020\020\002\004\n\002\b\t¨\0063"}, d2={"Lkotlinx/coroutines/experimental/selects/UnbiasedSelectBuilderImpl;", "R", "Lkotlinx/coroutines/experimental/selects/SelectBuilder;", "cont", "Lkotlin/coroutines/experimental/Continuation;", "(Lkotlin/coroutines/experimental/Continuation;)V", "clauses", "Ljava/util/ArrayList;", "Lkotlin/Function0;", "", "Lkotlin/collections/ArrayList;", "getClauses", "()Ljava/util/ArrayList;", "instance", "Lkotlinx/coroutines/experimental/selects/SelectBuilderImpl;", "getInstance", "()Lkotlinx/coroutines/experimental/selects/SelectBuilderImpl;", "handleBuilderException", "e", "", "initSelectResult", "", "onTimeout", "time", "", "unit", "Ljava/util/concurrent/TimeUnit;", "block", "Lkotlin/Function1;", "(JLjava/util/concurrent/TimeUnit;Lkotlin/jvm/functions/Function1;)V", "onAwait", "T", "Lkotlinx/coroutines/experimental/Deferred;", "Lkotlin/Function2;", "(Lkotlinx/coroutines/experimental/Deferred;Lkotlin/jvm/functions/Function2;)V", "onJoin", "Lkotlinx/coroutines/experimental/Job;", "(Lkotlinx/coroutines/experimental/Job;Lkotlin/jvm/functions/Function1;)V", "onLock", "Lkotlinx/coroutines/experimental/sync/Mutex;", "owner", "(Lkotlinx/coroutines/experimental/sync/Mutex;Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)V", "onReceive", "E", "Lkotlinx/coroutines/experimental/channels/ReceiveChannel;", "(Lkotlinx/coroutines/experimental/channels/ReceiveChannel;Lkotlin/jvm/functions/Function2;)V", "onReceiveOrNull", "onSend", "Lkotlinx/coroutines/experimental/channels/SendChannel;", "element", "(Lkotlinx/coroutines/experimental/channels/SendChannel;Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)V", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
@PublishedApi
public final class UnbiasedSelectBuilderImpl<R>
  implements SelectBuilder<R>
{
  @NotNull
  private final ArrayList<Function0<Unit>> clauses;
  @NotNull
  private final SelectBuilderImpl<R> instance;
  
  public UnbiasedSelectBuilderImpl(@NotNull Continuation<? super R> paramContinuation)
  {
    instance = new SelectBuilderImpl(paramContinuation);
    clauses = new ArrayList();
  }
  
  @NotNull
  public final ArrayList<Function0<Unit>> getClauses()
  {
    return clauses;
  }
  
  @NotNull
  public final SelectBuilderImpl<R> getInstance()
  {
    return instance;
  }
  
  @PublishedApi
  public final void handleBuilderException(@NotNull Throwable paramThrowable)
  {
    Intrinsics.checkParameterIsNotNull(paramThrowable, "e");
    instance.handleBuilderException(paramThrowable);
  }
  
  @PublishedApi
  @Nullable
  public final Object initSelectResult()
  {
    if (!instance.isSelected()) {
      try
      {
        Collections.shuffle((List)clauses);
        Iterator localIterator = ((Iterable)clauses).iterator();
        while (localIterator.hasNext()) {
          ((Function0)localIterator.next()).invoke();
        }
        return instance.getResult();
      }
      catch (Throwable localThrowable)
      {
        instance.handleBuilderException(localThrowable);
      }
    }
  }
  
  public <T> void onAwait(@NotNull final Deferred<? extends T> paramDeferred, @NotNull final Function2<? super T, ? super Continuation<? super R>, ? extends Object> paramFunction2)
  {
    Intrinsics.checkParameterIsNotNull(paramDeferred, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction2, "block");
    ((Collection)clauses).add(new Lambda(paramDeferred)
    {
      public final void invoke()
      {
        paramDeferred.registerSelectAwait((SelectInstance)this$0.getInstance(), paramFunction2);
      }
    });
  }
  
  public void onJoin(@NotNull final Job paramJob, @NotNull final Function1<? super Continuation<? super R>, ? extends Object> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramJob, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "block");
    ((Collection)clauses).add(new Lambda(paramJob)
    {
      public final void invoke()
      {
        paramJob.registerSelectJoin((SelectInstance)this$0.getInstance(), paramFunction1);
      }
    });
  }
  
  public void onLock(@NotNull final Mutex paramMutex, @Nullable final Object paramObject, @NotNull final Function1<? super Continuation<? super R>, ? extends Object> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramMutex, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "block");
    ((Collection)clauses).add(new Lambda(paramMutex)
    {
      public final void invoke()
      {
        paramMutex.registerSelectLock((SelectInstance)this$0.getInstance(), paramObject, paramFunction1);
      }
    });
  }
  
  public <E> void onReceive(@NotNull final ReceiveChannel<? extends E> paramReceiveChannel, @NotNull final Function2<? super E, ? super Continuation<? super R>, ? extends Object> paramFunction2)
  {
    Intrinsics.checkParameterIsNotNull(paramReceiveChannel, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction2, "block");
    ((Collection)clauses).add(new Lambda(paramReceiveChannel)
    {
      public final void invoke()
      {
        paramReceiveChannel.registerSelectReceive((SelectInstance)this$0.getInstance(), paramFunction2);
      }
    });
  }
  
  public <E> void onReceiveOrNull(@NotNull final ReceiveChannel<? extends E> paramReceiveChannel, @NotNull final Function2<? super E, ? super Continuation<? super R>, ? extends Object> paramFunction2)
  {
    Intrinsics.checkParameterIsNotNull(paramReceiveChannel, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction2, "block");
    ((Collection)clauses).add(new Lambda(paramReceiveChannel)
    {
      public final void invoke()
      {
        paramReceiveChannel.registerSelectReceiveOrNull((SelectInstance)this$0.getInstance(), paramFunction2);
      }
    });
  }
  
  public <E> void onSend(@NotNull final SendChannel<? super E> paramSendChannel, final E paramE, @NotNull final Function1<? super Continuation<? super R>, ? extends Object> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramSendChannel, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "block");
    ((Collection)clauses).add(new Lambda(paramSendChannel)
    {
      public final void invoke()
      {
        paramSendChannel.registerSelectSend((SelectInstance)this$0.getInstance(), paramE, paramFunction1);
      }
    });
  }
  
  public void onTimeout(final long paramLong, @NotNull TimeUnit paramTimeUnit, @NotNull final Function1<? super Continuation<? super R>, ? extends Object> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramTimeUnit, "unit");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "block");
    ((Collection)clauses).add(new Lambda(paramLong)
    {
      public final void invoke()
      {
        this$0.getInstance().onTimeout(paramLong, paramFunction1, $block);
      }
    });
  }
}
