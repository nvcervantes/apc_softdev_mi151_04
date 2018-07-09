package kotlin.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.experimental.Continuation;
import kotlin.coroutines.experimental.SequenceBuilder;
import kotlin.coroutines.experimental.SequenceBuilderKt;
import kotlin.coroutines.experimental.intrinsics.IntrinsicsKt;
import kotlin.coroutines.experimental.jvm.internal.CoroutineImpl;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000*\n\000\n\002\020\002\n\000\n\002\020\b\n\002\b\002\n\002\020(\n\002\020 \n\002\b\003\n\002\020\013\n\002\b\002\n\002\030\002\n\000\032\030\020\000\032\0020\0012\006\020\002\032\0020\0032\006\020\004\032\0020\003H\000\032H\020\005\032\016\022\n\022\b\022\004\022\002H\b0\0070\006\"\004\b\000\020\b2\f\020\t\032\b\022\004\022\002H\b0\0062\006\020\002\032\0020\0032\006\020\004\032\0020\0032\006\020\n\032\0020\0132\006\020\f\032\0020\013H\000\032D\020\r\032\016\022\n\022\b\022\004\022\002H\b0\0070\016\"\004\b\000\020\b*\b\022\004\022\002H\b0\0162\006\020\002\032\0020\0032\006\020\004\032\0020\0032\006\020\n\032\0020\0132\006\020\f\032\0020\013H\000¨\006\017"}, d2={"checkWindowSizeStep", "", "size", "", "step", "windowedIterator", "", "", "T", "iterator", "partialWindows", "", "reuseBuffer", "windowedSequence", "Lkotlin/sequences/Sequence;", "kotlin-stdlib"}, k=2, mv={1, 1, 9})
public final class SlidingWindowKt
{
  public static final void checkWindowSizeStep(int paramInt1, int paramInt2)
  {
    int i;
    if ((paramInt1 > 0) && (paramInt2 > 0)) {
      i = 1;
    } else {
      i = 0;
    }
    if (i == 0)
    {
      Object localObject;
      if (paramInt1 != paramInt2)
      {
        localObject = new StringBuilder();
        ((StringBuilder)localObject).append("Both size ");
        ((StringBuilder)localObject).append(paramInt1);
        ((StringBuilder)localObject).append(" and step ");
        ((StringBuilder)localObject).append(paramInt2);
        ((StringBuilder)localObject).append(" must be greater than zero.");
        localObject = ((StringBuilder)localObject).toString();
      }
      else
      {
        localObject = new StringBuilder();
        ((StringBuilder)localObject).append("size ");
        ((StringBuilder)localObject).append(paramInt1);
        ((StringBuilder)localObject).append(" must be greater than zero.");
        localObject = ((StringBuilder)localObject).toString();
      }
      throw ((Throwable)new IllegalArgumentException(localObject.toString()));
    }
  }
  
  @NotNull
  public static final <T> Iterator<List<T>> windowedIterator(@NotNull final Iterator<? extends T> paramIterator, final int paramInt1, int paramInt2, final boolean paramBoolean1, final boolean paramBoolean2)
  {
    Intrinsics.checkParameterIsNotNull(paramIterator, "iterator");
    if (!paramIterator.hasNext()) {
      return (Iterator)EmptyIterator.INSTANCE;
    }
    SequenceBuilderKt.buildIterator((Function2)new CoroutineImpl(paramInt2, paramInt1)
    {
      int I$0;
      int I$1;
      Object L$0;
      Object L$1;
      Object L$2;
      Object L$3;
      private SequenceBuilder p$;
      
      @NotNull
      public final Continuation<Unit> create(@NotNull SequenceBuilder<? super List<? extends T>> paramAnonymousSequenceBuilder, @NotNull Continuation<? super Unit> paramAnonymousContinuation)
      {
        Intrinsics.checkParameterIsNotNull(paramAnonymousSequenceBuilder, "$receiver");
        Intrinsics.checkParameterIsNotNull(paramAnonymousContinuation, "continuation");
        paramAnonymousContinuation = new 1($step, paramInt1, paramIterator, paramBoolean2, paramBoolean1, paramAnonymousContinuation);
        p$ = paramAnonymousSequenceBuilder;
        return paramAnonymousContinuation;
      }
      
      @Nullable
      public final Object doResume(@Nullable Object paramAnonymousObject, @Nullable Throwable paramAnonymousThrowable)
      {
        paramAnonymousObject = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i;
        Object localObject2;
        Object localObject1;
        Object localObject3;
        Object localObject4;
        Object localObject5;
        int j;
        switch (label)
        {
        default: 
          throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        case 5: 
          paramAnonymousObject = (RingBuffer)L$0;
          i = I$0;
          if (paramAnonymousThrowable == null) {
            break label885;
          }
          throw paramAnonymousThrowable;
        case 4: 
          localObject2 = (RingBuffer)L$1;
          i = I$0;
          localObject1 = (SequenceBuilder)L$0;
          if (paramAnonymousThrowable != null) {
            throw paramAnonymousThrowable;
          }
          paramAnonymousThrowable = this;
          localObject3 = paramAnonymousObject;
          break;
        case 3: 
          localObject3 = (Iterator)L$3;
          localObject1 = L$2;
          localObject2 = (RingBuffer)L$1;
          i = I$0;
          localObject1 = (SequenceBuilder)L$0;
          if (paramAnonymousThrowable != null) {
            throw paramAnonymousThrowable;
          }
          paramAnonymousThrowable = this;
          localObject4 = paramAnonymousObject;
          break;
        case 2: 
          i = I$1;
          paramAnonymousObject = (ArrayList)L$0;
          i = I$0;
          if (paramAnonymousThrowable == null) {
            break label885;
          }
          throw paramAnonymousThrowable;
        case 1: 
          localObject1 = (Iterator)L$3;
          localObject2 = L$2;
          i = I$1;
          localObject5 = (ArrayList)L$1;
          j = I$0;
          localObject3 = (SequenceBuilder)L$0;
          if (paramAnonymousThrowable != null) {
            throw paramAnonymousThrowable;
          }
          localObject4 = paramAnonymousObject;
          localObject2 = this;
          paramAnonymousObject = localObject5;
          break;
        case 0: 
          if (paramAnonymousThrowable != null) {
            throw paramAnonymousThrowable;
          }
          localObject1 = p$;
          i = $step - paramInt1;
          if (i < 0) {
            break label556;
          }
          localObject4 = new ArrayList(paramInt1);
          int k = 0;
          paramAnonymousThrowable = paramIterator;
          localObject2 = this;
          localObject3 = localObject1;
          localObject1 = paramAnonymousThrowable;
          j = i;
          paramAnonymousThrowable = paramAnonymousObject;
          i = k;
          paramAnonymousObject = localObject4;
        }
        while (((Iterator)localObject1).hasNext())
        {
          localObject4 = ((Iterator)localObject1).next();
          if (i > 0)
          {
            i -= 1;
          }
          else
          {
            paramAnonymousObject.add(localObject4);
            if (paramAnonymousObject.size() == paramInt1)
            {
              L$0 = localObject3;
              I$0 = j;
              L$1 = paramAnonymousObject;
              I$1 = i;
              L$2 = localObject4;
              L$3 = localObject1;
              label = 1;
              localObject5 = ((SequenceBuilder)localObject3).yield(paramAnonymousObject, (Continuation)localObject2);
              InlineMarker.mark(2);
              localObject4 = paramAnonymousThrowable;
              if (localObject5 == paramAnonymousThrowable) {
                return paramAnonymousThrowable;
              }
              if (paramBoolean2) {
                paramAnonymousObject.clear();
              } else {
                paramAnonymousObject = new ArrayList(paramInt1);
              }
              i = j;
              paramAnonymousThrowable = (Throwable)localObject4;
            }
          }
        }
        if (((((Collection)paramAnonymousObject).isEmpty() ^ true)) && ((paramBoolean1) || (paramAnonymousObject.size() == paramInt1)))
        {
          I$0 = j;
          L$0 = paramAnonymousObject;
          I$1 = i;
          label = 2;
          paramAnonymousObject = ((SequenceBuilder)localObject3).yield(paramAnonymousObject, (Continuation)localObject2);
          InlineMarker.mark(2);
          if (paramAnonymousObject == paramAnonymousThrowable)
          {
            return paramAnonymousThrowable;
            label556:
            localObject2 = new RingBuffer(paramInt1);
            localObject3 = paramIterator;
            paramAnonymousThrowable = this;
            while (((Iterator)localObject3).hasNext())
            {
              localObject5 = ((Iterator)localObject3).next();
              ((RingBuffer)localObject2).add(localObject5);
              if (((RingBuffer)localObject2).isFull())
              {
                if (paramBoolean2) {
                  localObject4 = (List)localObject2;
                } else {
                  localObject4 = (List)new ArrayList((Collection)localObject2);
                }
                L$0 = localObject1;
                I$0 = i;
                L$1 = localObject2;
                L$2 = localObject5;
                L$3 = localObject3;
                label = 3;
                localObject5 = ((SequenceBuilder)localObject1).yield(localObject4, paramAnonymousThrowable);
                InlineMarker.mark(2);
                localObject4 = paramAnonymousObject;
                if (localObject5 == paramAnonymousObject) {
                  return paramAnonymousObject;
                }
                ((RingBuffer)localObject2).removeFirst($step);
                paramAnonymousObject = localObject4;
              }
            }
            if (paramBoolean1)
            {
              while (((RingBuffer)localObject2).size() > $step)
              {
                if (paramBoolean2) {
                  localObject3 = (List)localObject2;
                } else {
                  localObject3 = (List)new ArrayList((Collection)localObject2);
                }
                L$0 = localObject1;
                I$0 = i;
                L$1 = localObject2;
                label = 4;
                localObject4 = ((SequenceBuilder)localObject1).yield(localObject3, paramAnonymousThrowable);
                InlineMarker.mark(2);
                localObject3 = paramAnonymousObject;
                if (localObject4 == paramAnonymousObject) {
                  return paramAnonymousObject;
                }
                ((RingBuffer)localObject2).removeFirst($step);
                paramAnonymousObject = localObject3;
              }
              if ((true ^ ((Collection)localObject2).isEmpty()))
              {
                I$0 = i;
                L$0 = localObject2;
                label = 5;
                paramAnonymousThrowable = ((SequenceBuilder)localObject1).yield(localObject2, paramAnonymousThrowable);
                InlineMarker.mark(2);
                if (paramAnonymousThrowable == paramAnonymousObject) {
                  return paramAnonymousObject;
                }
              }
            }
          }
        }
        label885:
        return Unit.INSTANCE;
      }
      
      @Nullable
      public final Object invoke(@NotNull SequenceBuilder<? super List<? extends T>> paramAnonymousSequenceBuilder, @NotNull Continuation<? super Unit> paramAnonymousContinuation)
      {
        Intrinsics.checkParameterIsNotNull(paramAnonymousSequenceBuilder, "$receiver");
        Intrinsics.checkParameterIsNotNull(paramAnonymousContinuation, "continuation");
        return ((1)create(paramAnonymousSequenceBuilder, paramAnonymousContinuation)).doResume(Unit.INSTANCE, null);
      }
    });
  }
  
  @NotNull
  public static final <T> Sequence<List<T>> windowedSequence(@NotNull Sequence<? extends T> paramSequence, final int paramInt1, final int paramInt2, final boolean paramBoolean1, final boolean paramBoolean2)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    checkWindowSizeStep(paramInt1, paramInt2);
    (Sequence)new Sequence()
    {
      @NotNull
      public Iterator<List<? extends T>> iterator()
      {
        return SlidingWindowKt.windowedIterator(receiver$0$inlined.iterator(), paramInt1, paramInt2, paramBoolean1, paramBoolean2);
      }
    };
  }
}
