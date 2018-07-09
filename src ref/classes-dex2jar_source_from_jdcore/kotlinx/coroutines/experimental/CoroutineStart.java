package kotlinx.coroutines.experimental;

import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.coroutines.experimental.Continuation;
import kotlin.coroutines.experimental.CoroutinesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.experimental.intrinsics.UndispatchedKt;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\0008\n\002\030\002\n\002\020\020\n\002\b\002\n\002\020\013\n\002\b\002\n\002\020\002\n\002\b\002\n\002\030\002\n\002\030\002\n\002\020\000\n\002\b\003\n\002\030\002\n\002\030\002\n\002\b\007\b\001\030\0002\b\022\004\022\0020\0000\001B\007\b\002¢\006\002\020\002JC\020\006\032\0020\007\"\004\b\000\020\b2\034\020\t\032\030\b\001\022\n\022\b\022\004\022\002H\b0\013\022\006\022\004\030\0010\f0\n2\f\020\r\032\b\022\004\022\002H\b0\013H\002ø\001\000¢\006\002\020\016J\\\020\006\032\0020\007\"\004\b\000\020\017\"\004\b\001\020\b2'\020\t\032#\b\001\022\004\022\002H\017\022\n\022\b\022\004\022\002H\b0\013\022\006\022\004\030\0010\f0\020¢\006\002\b\0212\006\020\022\032\002H\0172\f\020\r\032\b\022\004\022\002H\b0\013H\002ø\001\000¢\006\002\020\023R\021\020\003\032\0020\0048F¢\006\006\032\004\b\003\020\005j\002\b\024j\002\b\025j\002\b\026j\002\b\027\002\004\n\002\b\t¨\006\030"}, d2={"Lkotlinx/coroutines/experimental/CoroutineStart;", "", "(Ljava/lang/String;I)V", "isLazy", "", "()Z", "invoke", "", "T", "block", "Lkotlin/Function1;", "Lkotlin/coroutines/experimental/Continuation;", "", "completion", "(Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/experimental/Continuation;)V", "R", "Lkotlin/Function2;", "Lkotlin/ExtensionFunctionType;", "receiver", "(Lkotlin/jvm/functions/Function2;Ljava/lang/Object;Lkotlin/coroutines/experimental/Continuation;)V", "DEFAULT", "LAZY", "ATOMIC", "UNDISPATCHED", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
public enum CoroutineStart
{
  static
  {
    CoroutineStart localCoroutineStart1 = new CoroutineStart("DEFAULT", 0);
    DEFAULT = localCoroutineStart1;
    CoroutineStart localCoroutineStart2 = new CoroutineStart("LAZY", 1);
    LAZY = localCoroutineStart2;
    CoroutineStart localCoroutineStart3 = new CoroutineStart("ATOMIC", 2);
    ATOMIC = localCoroutineStart3;
    CoroutineStart localCoroutineStart4 = new CoroutineStart("UNDISPATCHED", 3);
    UNDISPATCHED = localCoroutineStart4;
    $VALUES = new CoroutineStart[] { localCoroutineStart1, localCoroutineStart2, localCoroutineStart3, localCoroutineStart4 };
  }
  
  protected CoroutineStart() {}
  
  public final <T> void invoke(@NotNull Function1<? super Continuation<? super T>, ? extends Object> paramFunction1, @NotNull Continuation<? super T> paramContinuation)
  {
    Intrinsics.checkParameterIsNotNull(paramFunction1, "block");
    Intrinsics.checkParameterIsNotNull(paramContinuation, "completion");
    switch (CoroutineStart.WhenMappings.$EnumSwitchMapping$0[ordinal()])
    {
    default: 
      throw new NoWhenBranchMatchedException();
    case 3: 
      UndispatchedKt.startCoroutineUndispatched(paramFunction1, paramContinuation);
      return;
    case 2: 
      CoroutinesKt.startCoroutine(paramFunction1, paramContinuation);
      return;
    case 1: 
      CancellableKt.startCoroutineCancellable(paramFunction1, paramContinuation);
    }
  }
  
  public final <R, T> void invoke(@NotNull Function2<? super R, ? super Continuation<? super T>, ? extends Object> paramFunction2, R paramR, @NotNull Continuation<? super T> paramContinuation)
  {
    Intrinsics.checkParameterIsNotNull(paramFunction2, "block");
    Intrinsics.checkParameterIsNotNull(paramContinuation, "completion");
    switch (CoroutineStart.WhenMappings.$EnumSwitchMapping$1[ordinal()])
    {
    default: 
      throw new NoWhenBranchMatchedException();
    case 3: 
      UndispatchedKt.startCoroutineUndispatched(paramFunction2, paramR, paramContinuation);
      return;
    case 2: 
      CoroutinesKt.startCoroutine(paramFunction2, paramR, paramContinuation);
      return;
    case 1: 
      CancellableKt.startCoroutineCancellable(paramFunction2, paramR, paramContinuation);
    }
  }
  
  public final boolean isLazy()
  {
    return (CoroutineStart)this == LAZY;
  }
}
