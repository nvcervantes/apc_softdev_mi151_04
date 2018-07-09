package kotlinx.coroutines.experimental.channels;

import kotlin.Metadata;
import kotlin.coroutines.experimental.Continuation;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.experimental.selects.SelectInstance;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\0006\n\002\030\002\n\000\n\002\020\000\n\000\n\002\020\013\n\002\b\003\n\002\030\002\n\002\b\006\n\002\020\002\n\002\b\002\n\002\030\002\n\000\n\002\030\002\n\002\030\002\n\002\b\003\bf\030\000*\006\b\000\020\001 \0012\0020\002J\017\020\007\032\b\022\004\022\0028\0000\bH¦\002J\017\020\t\032\004\030\0018\000H&¢\006\002\020\nJ\021\020\013\032\0028\000H¦@ø\001\000¢\006\002\020\fJ\023\020\r\032\004\030\0018\000H¦@ø\001\000¢\006\002\020\fJH\020\016\032\0020\017\"\004\b\001\020\0202\f\020\021\032\b\022\004\022\002H\0200\0222\"\020\023\032\036\b\001\022\004\022\0028\000\022\n\022\b\022\004\022\002H\0200\025\022\006\022\004\030\0010\0020\024H&ø\001\000¢\006\002\020\026JJ\020\027\032\0020\017\"\004\b\001\020\0202\f\020\021\032\b\022\004\022\002H\0200\0222$\020\023\032 \b\001\022\006\022\004\030\0018\000\022\n\022\b\022\004\022\002H\0200\025\022\006\022\004\030\0010\0020\024H&ø\001\000¢\006\002\020\026R\022\020\003\032\0020\004X¦\004¢\006\006\032\004\b\003\020\005R\022\020\006\032\0020\004X¦\004¢\006\006\032\004\b\006\020\005\002\004\n\002\b\t¨\006\030"}, d2={"Lkotlinx/coroutines/experimental/channels/ReceiveChannel;", "E", "", "isClosedForReceive", "", "()Z", "isEmpty", "iterator", "Lkotlinx/coroutines/experimental/channels/ChannelIterator;", "poll", "()Ljava/lang/Object;", "receive", "(Lkotlin/coroutines/experimental/Continuation;)Ljava/lang/Object;", "receiveOrNull", "registerSelectReceive", "", "R", "select", "Lkotlinx/coroutines/experimental/selects/SelectInstance;", "block", "Lkotlin/Function2;", "Lkotlin/coroutines/experimental/Continuation;", "(Lkotlinx/coroutines/experimental/selects/SelectInstance;Lkotlin/jvm/functions/Function2;)V", "registerSelectReceiveOrNull", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
public abstract interface ReceiveChannel<E>
{
  public abstract boolean isClosedForReceive();
  
  public abstract boolean isEmpty();
  
  @NotNull
  public abstract ChannelIterator<E> iterator();
  
  @Nullable
  public abstract E poll();
  
  @Nullable
  public abstract Object receive(@NotNull Continuation<? super E> paramContinuation);
  
  @Nullable
  public abstract Object receiveOrNull(@NotNull Continuation<? super E> paramContinuation);
  
  public abstract <R> void registerSelectReceive(@NotNull SelectInstance<? super R> paramSelectInstance, @NotNull Function2<? super E, ? super Continuation<? super R>, ? extends Object> paramFunction2);
  
  public abstract <R> void registerSelectReceiveOrNull(@NotNull SelectInstance<? super R> paramSelectInstance, @NotNull Function2<? super E, ? super Continuation<? super R>, ? extends Object> paramFunction2);
}
