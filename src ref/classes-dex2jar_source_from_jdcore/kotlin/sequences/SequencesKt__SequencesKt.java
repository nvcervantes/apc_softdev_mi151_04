package kotlin.sequences;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.internal.InlineOnly;
import kotlin.internal.LowPriorityInOverloadResolution;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000H\n\000\n\002\030\002\n\002\b\002\n\002\030\002\n\002\020(\n\002\b\002\n\002\020\000\n\002\b\002\n\002\030\002\n\002\b\004\n\002\020\021\n\002\b\002\n\002\030\002\n\002\b\004\n\002\020\034\n\002\b\002\n\002\030\002\n\002\020 \n\000\032+\020\000\032\b\022\004\022\002H\0020\001\"\004\b\000\020\0022\024\b\004\020\003\032\016\022\n\022\b\022\004\022\002H\0020\0050\004H\b\032\022\020\006\032\b\022\004\022\002H\0020\001\"\004\b\000\020\002\032&\020\007\032\b\022\004\022\002H\0020\001\"\b\b\000\020\002*\0020\b2\016\020\t\032\n\022\006\022\004\030\001H\0020\004\032<\020\007\032\b\022\004\022\002H\0020\001\"\b\b\000\020\002*\0020\b2\016\020\n\032\n\022\006\022\004\030\001H\0020\0042\024\020\t\032\020\022\004\022\002H\002\022\006\022\004\030\001H\0020\013\032=\020\007\032\b\022\004\022\002H\0020\001\"\b\b\000\020\002*\0020\b2\b\020\f\032\004\030\001H\0022\024\020\t\032\020\022\004\022\002H\002\022\006\022\004\030\001H\0020\013H\007¢\006\002\020\r\032+\020\016\032\b\022\004\022\002H\0020\001\"\004\b\000\020\0022\022\020\017\032\n\022\006\b\001\022\002H\0020\020\"\002H\002¢\006\002\020\021\032\037\020\022\032\b\022\004\022\002H\0020\001\"\004\b\000\020\002*\b\022\004\022\002H\0020\023H\b\032\034\020\022\032\b\022\004\022\002H\0020\001\"\004\b\000\020\002*\b\022\004\022\002H\0020\005\032\034\020\024\032\b\022\004\022\002H\0020\001\"\004\b\000\020\002*\b\022\004\022\002H\0020\001\032C\020\025\032\b\022\004\022\002H\0260\001\"\004\b\000\020\002\"\004\b\001\020\026*\b\022\004\022\002H\0020\0012\030\020\003\032\024\022\004\022\002H\002\022\n\022\b\022\004\022\002H\0260\0050\013H\002¢\006\002\b\027\032)\020\025\032\b\022\004\022\002H\0020\001\"\004\b\000\020\002*\016\022\n\022\b\022\004\022\002H\0020\0300\001H\007¢\006\002\b\031\032\"\020\025\032\b\022\004\022\002H\0020\001\"\004\b\000\020\002*\016\022\n\022\b\022\004\022\002H\0020\0010\001\032@\020\032\032\032\022\n\022\b\022\004\022\002H\0020\034\022\n\022\b\022\004\022\002H\0260\0340\033\"\004\b\000\020\002\"\004\b\001\020\026*\024\022\020\022\016\022\004\022\002H\002\022\004\022\002H\0260\0330\001¨\006\035"}, d2={"Sequence", "Lkotlin/sequences/Sequence;", "T", "iterator", "Lkotlin/Function0;", "", "emptySequence", "generateSequence", "", "nextFunction", "seedFunction", "Lkotlin/Function1;", "seed", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)Lkotlin/sequences/Sequence;", "sequenceOf", "elements", "", "([Ljava/lang/Object;)Lkotlin/sequences/Sequence;", "asSequence", "Ljava/util/Enumeration;", "constrainOnce", "flatten", "R", "flatten$SequencesKt__SequencesKt", "", "flattenSequenceOfIterable", "unzip", "Lkotlin/Pair;", "", "kotlin-stdlib"}, k=5, mv={1, 1, 9}, xi=1, xs="kotlin/sequences/SequencesKt")
class SequencesKt__SequencesKt
{
  public SequencesKt__SequencesKt() {}
  
  @InlineOnly
  private static final <T> Sequence<T> Sequence(Function0<? extends Iterator<? extends T>> paramFunction0)
  {
    (Sequence)new Sequence()
    {
      @NotNull
      public Iterator<T> iterator()
      {
        return (Iterator)$iterator.invoke();
      }
    };
  }
  
  @InlineOnly
  private static final <T> Sequence<T> asSequence(@NotNull Enumeration<T> paramEnumeration)
  {
    return SequencesKt.asSequence(CollectionsKt.iterator(paramEnumeration));
  }
  
  @NotNull
  public static final <T> Sequence<T> asSequence(@NotNull Iterator<? extends T> paramIterator)
  {
    Intrinsics.checkParameterIsNotNull(paramIterator, "$receiver");
    SequencesKt.constrainOnce((Sequence)new Sequence()
    {
      @NotNull
      public Iterator<T> iterator()
      {
        return receiver$0$inlined;
      }
    });
  }
  
  @NotNull
  public static final <T> Sequence<T> constrainOnce(@NotNull Sequence<? extends T> paramSequence)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    if ((paramSequence instanceof ConstrainedOnceSequence)) {
      return paramSequence;
    }
    return (Sequence)new ConstrainedOnceSequence(paramSequence);
  }
  
  @NotNull
  public static final <T> Sequence<T> emptySequence()
  {
    return (Sequence)EmptySequence.INSTANCE;
  }
  
  @NotNull
  public static final <T> Sequence<T> flatten(@NotNull Sequence<? extends Sequence<? extends T>> paramSequence)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    return flatten$SequencesKt__SequencesKt(paramSequence, (Function1)flatten.1.INSTANCE);
  }
  
  private static final <T, R> Sequence<R> flatten$SequencesKt__SequencesKt(@NotNull Sequence<? extends T> paramSequence, Function1<? super T, ? extends Iterator<? extends R>> paramFunction1)
  {
    if ((paramSequence instanceof TransformingSequence)) {
      return ((TransformingSequence)paramSequence).flatten$kotlin_stdlib(paramFunction1);
    }
    return (Sequence)new FlatteningSequence(paramSequence, (Function1)flatten.3.INSTANCE, paramFunction1);
  }
  
  @JvmName(name="flattenSequenceOfIterable")
  @NotNull
  public static final <T> Sequence<T> flattenSequenceOfIterable(@NotNull Sequence<? extends Iterable<? extends T>> paramSequence)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    return flatten$SequencesKt__SequencesKt(paramSequence, (Function1)flatten.2.INSTANCE);
  }
  
  @LowPriorityInOverloadResolution
  @NotNull
  public static final <T> Sequence<T> generateSequence(@Nullable T paramT, @NotNull Function1<? super T, ? extends T> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramFunction1, "nextFunction");
    if (paramT == null) {
      return (Sequence)EmptySequence.INSTANCE;
    }
    (Sequence)new GeneratorSequence((Function0)new Lambda(paramT)
    {
      @Nullable
      public final T invoke()
      {
        return $seed;
      }
    }, paramFunction1);
  }
  
  @NotNull
  public static final <T> Sequence<T> generateSequence(@NotNull Function0<? extends T> paramFunction0)
  {
    Intrinsics.checkParameterIsNotNull(paramFunction0, "nextFunction");
    SequencesKt.constrainOnce((Sequence)new GeneratorSequence(paramFunction0, (Function1)new Lambda(paramFunction0)
    {
      @Nullable
      public final T invoke(@NotNull T paramAnonymousT)
      {
        Intrinsics.checkParameterIsNotNull(paramAnonymousT, "it");
        return $nextFunction.invoke();
      }
    }));
  }
  
  @NotNull
  public static final <T> Sequence<T> generateSequence(@NotNull Function0<? extends T> paramFunction0, @NotNull Function1<? super T, ? extends T> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramFunction0, "seedFunction");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "nextFunction");
    return (Sequence)new GeneratorSequence(paramFunction0, paramFunction1);
  }
  
  @NotNull
  public static final <T> Sequence<T> sequenceOf(@NotNull T... paramVarArgs)
  {
    Intrinsics.checkParameterIsNotNull(paramVarArgs, "elements");
    int i;
    if (paramVarArgs.length == 0) {
      i = 1;
    } else {
      i = 0;
    }
    if (i != 0) {
      return SequencesKt.emptySequence();
    }
    return ArraysKt.asSequence(paramVarArgs);
  }
  
  @NotNull
  public static final <T, R> Pair<List<T>, List<R>> unzip(@NotNull Sequence<? extends Pair<? extends T, ? extends R>> paramSequence)
  {
    Intrinsics.checkParameterIsNotNull(paramSequence, "$receiver");
    ArrayList localArrayList1 = new ArrayList();
    ArrayList localArrayList2 = new ArrayList();
    paramSequence = paramSequence.iterator();
    while (paramSequence.hasNext())
    {
      Pair localPair = (Pair)paramSequence.next();
      localArrayList1.add(localPair.getFirst());
      localArrayList2.add(localPair.getSecond());
    }
    return TuplesKt.to(localArrayList1, localArrayList2);
  }
}
