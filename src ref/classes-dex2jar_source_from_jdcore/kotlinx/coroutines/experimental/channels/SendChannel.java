package kotlinx.coroutines.experimental.channels;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.experimental.Continuation;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.experimental.selects.SelectInstance;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\0006\n\002\030\002\n\000\n\002\020\000\n\000\n\002\020\013\n\002\b\004\n\002\020\003\n\002\b\004\n\002\020\002\n\002\b\002\n\002\030\002\n\000\n\002\030\002\n\002\030\002\n\002\b\004\bf\030\000*\006\b\000\020\001 \0002\0020\002J\024\020\007\032\0020\0042\n\b\002\020\b\032\004\030\0010\tH&J\025\020\n\032\0020\0042\006\020\013\032\0028\000H&¢\006\002\020\fJJ\020\r\032\0020\016\"\004\b\001\020\0172\f\020\020\032\b\022\004\022\002H\0170\0212\006\020\013\032\0028\0002\034\020\022\032\030\b\001\022\n\022\b\022\004\022\002H\0170\024\022\006\022\004\030\0010\0020\023H&ø\001\000¢\006\002\020\025J\031\020\026\032\0020\0162\006\020\013\032\0028\000H¦@ø\001\000¢\006\002\020\027R\022\020\003\032\0020\004X¦\004¢\006\006\032\004\b\003\020\005R\022\020\006\032\0020\004X¦\004¢\006\006\032\004\b\006\020\005\002\004\n\002\b\t¨\006\030"}, d2={"Lkotlinx/coroutines/experimental/channels/SendChannel;", "E", "", "isClosedForSend", "", "()Z", "isFull", "close", "cause", "", "offer", "element", "(Ljava/lang/Object;)Z", "registerSelectSend", "", "R", "select", "Lkotlinx/coroutines/experimental/selects/SelectInstance;", "block", "Lkotlin/Function1;", "Lkotlin/coroutines/experimental/Continuation;", "(Lkotlinx/coroutines/experimental/selects/SelectInstance;Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)V", "send", "(Ljava/lang/Object;Lkotlin/coroutines/experimental/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
public abstract interface SendChannel<E>
{
  public abstract boolean close(@Nullable Throwable paramThrowable);
  
  public abstract boolean isClosedForSend();
  
  public abstract boolean isFull();
  
  public abstract boolean offer(E paramE);
  
  public abstract <R> void registerSelectSend(@NotNull SelectInstance<? super R> paramSelectInstance, E paramE, @NotNull Function1<? super Continuation<? super R>, ? extends Object> paramFunction1);
  
  @Nullable
  public abstract Object send(E paramE, @NotNull Continuation<? super Unit> paramContinuation);
  
  @Metadata(bv={1, 0, 2}, k=3, mv={1, 1, 7})
  public static final class DefaultImpls {}
}
