package kotlinx.coroutines.experimental;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.coroutines.experimental.Continuation;
import kotlin.coroutines.experimental.CoroutineContext;
import kotlin.coroutines.experimental.CoroutineContext.Element;
import kotlin.coroutines.experimental.CoroutineContext.Element.DefaultImpls;
import kotlin.coroutines.experimental.CoroutineContext.Key;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.experimental.selects.SelectInstance;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000B\n\002\030\002\n\002\030\002\n\000\n\002\020\013\n\002\b\005\n\002\020\003\n\002\b\002\n\002\030\002\n\000\n\002\030\002\n\002\020\002\n\002\030\002\n\002\b\b\n\002\030\002\n\000\n\002\030\002\n\002\020\000\n\002\b\005\bf\030\000 \0372\0020\001:\002\037 J\024\020\007\032\0020\0032\n\b\002\020\b\032\004\030\0010\tH&J\b\020\n\032\0020\tH&J\"\020\013\032\0020\f2\030\020\r\032\024\022\006\022\004\030\0010\t\022\004\022\0020\0170\016j\002`\020H&J*\020\013\032\0020\f2\030\020\r\032\024\022\006\022\004\030\0010\t\022\004\022\0020\0170\016j\002`\0202\006\020\021\032\0020\003H&J\021\020\022\032\0020\017H¦@ø\001\000¢\006\002\020\023J\021\020\024\032\0020\0002\006\020\025\032\0020\000H\002JB\020\026\032\0020\017\"\004\b\000\020\0272\f\020\030\032\b\022\004\022\002H\0270\0312\034\020\032\032\030\b\001\022\n\022\b\022\004\022\002H\0270\033\022\006\022\004\030\0010\0340\016H&ø\001\000¢\006\002\020\035J\b\020\036\032\0020\003H&R\022\020\002\032\0020\003X¦\004¢\006\006\032\004\b\002\020\004R\022\020\005\032\0020\003X¦\004¢\006\006\032\004\b\005\020\004R\022\020\006\032\0020\003X¦\004¢\006\006\032\004\b\006\020\004\002\004\n\002\b\t¨\006!"}, d2={"Lkotlinx/coroutines/experimental/Job;", "Lkotlin/coroutines/experimental/CoroutineContext$Element;", "isActive", "", "()Z", "isCancelled", "isCompleted", "cancel", "cause", "", "getCompletionException", "invokeOnCompletion", "Lkotlinx/coroutines/experimental/DisposableHandle;", "handler", "Lkotlin/Function1;", "", "Lkotlinx/coroutines/experimental/CompletionHandler;", "onCancelling", "join", "(Lkotlin/coroutines/experimental/Continuation;)Ljava/lang/Object;", "plus", "other", "registerSelectJoin", "R", "select", "Lkotlinx/coroutines/experimental/selects/SelectInstance;", "block", "Lkotlin/coroutines/experimental/Continuation;", "", "(Lkotlinx/coroutines/experimental/selects/SelectInstance;Lkotlin/jvm/functions/Function1;)V", "start", "Key", "Registration", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
public abstract interface Job
  extends CoroutineContext.Element
{
  public static final Key Key = new Key(null);
  
  public abstract boolean cancel(@Nullable Throwable paramThrowable);
  
  @NotNull
  public abstract Throwable getCompletionException();
  
  @NotNull
  public abstract DisposableHandle invokeOnCompletion(@NotNull Function1<? super Throwable, Unit> paramFunction1);
  
  @NotNull
  public abstract DisposableHandle invokeOnCompletion(@NotNull Function1<? super Throwable, Unit> paramFunction1, boolean paramBoolean);
  
  public abstract boolean isActive();
  
  public abstract boolean isCancelled();
  
  public abstract boolean isCompleted();
  
  @Nullable
  public abstract Object join(@NotNull Continuation<? super Unit> paramContinuation);
  
  @Deprecated(level=DeprecationLevel.ERROR, message="Operator '+' on two Job objects is meaningless. Job is a coroutine context element and `+` is a set-sum operator for coroutine contexts. The job to the right of `+` just replaces the job the left of `+`.")
  @NotNull
  public abstract Job plus(@NotNull Job paramJob);
  
  public abstract <R> void registerSelectJoin(@NotNull SelectInstance<? super R> paramSelectInstance, @NotNull Function1<? super Continuation<? super R>, ? extends Object> paramFunction1);
  
  public abstract boolean start();
  
  @Metadata(bv={1, 0, 2}, k=3, mv={1, 1, 7})
  public static final class DefaultImpls
  {
    public static <R> R fold(Job paramJob, @NotNull R paramR, Function2<? super R, ? super CoroutineContext.Element, ? extends R> paramFunction2)
    {
      Intrinsics.checkParameterIsNotNull(paramFunction2, "operation");
      return CoroutineContext.Element.DefaultImpls.fold((CoroutineContext.Element)paramJob, paramR, paramFunction2);
    }
    
    @Nullable
    public static <E extends CoroutineContext.Element> E get(@NotNull Job paramJob, CoroutineContext.Key<E> paramKey)
    {
      Intrinsics.checkParameterIsNotNull(paramKey, "key");
      return CoroutineContext.Element.DefaultImpls.get((CoroutineContext.Element)paramJob, paramKey);
    }
    
    @NotNull
    public static CoroutineContext minusKey(@NotNull Job paramJob, CoroutineContext.Key<?> paramKey)
    {
      Intrinsics.checkParameterIsNotNull(paramKey, "key");
      return CoroutineContext.Element.DefaultImpls.minusKey((CoroutineContext.Element)paramJob, paramKey);
    }
    
    @NotNull
    public static CoroutineContext plus(@NotNull Job paramJob, CoroutineContext paramCoroutineContext)
    {
      Intrinsics.checkParameterIsNotNull(paramCoroutineContext, "context");
      return CoroutineContext.Element.DefaultImpls.plus((CoroutineContext.Element)paramJob, paramCoroutineContext);
    }
    
    @Deprecated(level=DeprecationLevel.ERROR, message="Operator '+' on two Job objects is meaningless. Job is a coroutine context element and `+` is a set-sum operator for coroutine contexts. The job to the right of `+` just replaces the job the left of `+`.")
    @NotNull
    public static Job plus(@NotNull Job paramJob1, Job paramJob2)
    {
      Intrinsics.checkParameterIsNotNull(paramJob2, "other");
      return paramJob2;
    }
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\000\020\n\002\030\002\n\002\030\002\n\002\030\002\n\002\b\004\b\003\030\0002\b\022\004\022\0020\0020\001B\007\b\002¢\006\002\020\003J\025\020\004\032\0020\0022\n\b\002\020\005\032\004\030\0010\002H\002¨\006\006"}, d2={"Lkotlinx/coroutines/experimental/Job$Key;", "Lkotlin/coroutines/experimental/CoroutineContext$Key;", "Lkotlinx/coroutines/experimental/Job;", "()V", "invoke", "parent", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
  public static final class Key
    implements CoroutineContext.Key<Job>
  {
    private Key()
    {
      CoroutineExceptionHandler.Key localKey = CoroutineExceptionHandler.Key;
    }
  }
  
  @Deprecated(message="Replace with `DisposableHandle`", replaceWith=@ReplaceWith(expression="DisposableHandle", imports={}))
  @Metadata(bv={1, 0, 2}, d1={"\000\020\n\002\030\002\n\002\020\000\n\000\n\002\020\002\n\000\bg\030\0002\0020\001J\b\020\002\032\0020\003H'¨\006\004"}, d2={"Lkotlinx/coroutines/experimental/Job$Registration;", "", "unregister", "", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
  public static abstract interface Registration
  {
    @Deprecated(message="Replace with `dispose`", replaceWith=@ReplaceWith(expression="dispose()", imports={}))
    public abstract void unregister();
  }
}
