package kotlinx.coroutines.experimental.internal;

import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000,\n\000\n\002\020\000\n\002\b\b\n\002\020\b\n\002\b\n\n\002\030\002\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\002\n\002\030\002\n\000\032\020\020\024\032\0060\025j\002`\026*\0020\001H\001\"\034\020\000\032\0020\0018\000X\004¢\006\016\n\000\022\004\b\002\020\003\032\004\b\004\020\005\"\034\020\006\032\0020\0018\000X\004¢\006\016\n\000\022\004\b\007\020\003\032\004\b\b\020\005\"\026\020\t\032\0020\n8\000XT¢\006\b\n\000\022\004\b\013\020\003\"\034\020\f\032\0020\0018\000X\004¢\006\016\n\000\022\004\b\r\020\003\032\004\b\016\020\005\"\016\020\017\032\0020\001X\004¢\006\002\n\000\"\026\020\020\032\0020\n8\000XT¢\006\b\n\000\022\004\b\021\020\003\"\026\020\022\032\0020\n8\000XT¢\006\b\n\000\022\004\b\023\020\003*\034\020\027\032\004\b\000\020\030\"\b\022\004\022\002H\0300\0312\b\022\004\022\002H\0300\031*\f\b\002\020\032\"\0020\0252\0020\025*\034\020\033\032\004\b\000\020\030\"\b\022\004\022\002H\0300\0342\b\022\004\022\002H\0300\034¨\006\035"}, d2={"ALREADY_REMOVED", "", "ALREADY_REMOVED$annotations", "()V", "getALREADY_REMOVED", "()Ljava/lang/Object;", "CONDITION_FALSE", "CONDITION_FALSE$annotations", "getCONDITION_FALSE", "FAILURE", "", "FAILURE$annotations", "LIST_EMPTY", "LIST_EMPTY$annotations", "getLIST_EMPTY", "REMOVE_PREPARED", "SUCCESS", "SUCCESS$annotations", "UNDECIDED", "UNDECIDED$annotations", "unwrap", "Lkotlinx/coroutines/experimental/internal/LockFreeLinkedListNode;", "Lkotlinx/coroutines/experimental/internal/Node;", "AddLastDesc", "T", "Lkotlinx/coroutines/experimental/internal/LockFreeLinkedListNode$AddLastDesc;", "Node", "RemoveFirstDesc", "Lkotlinx/coroutines/experimental/internal/LockFreeLinkedListNode$RemoveFirstDesc;", "kotlinx-coroutines-core"}, k=2, mv={1, 1, 7})
public final class LockFreeLinkedListKt
{
  @NotNull
  private static final Object ALREADY_REMOVED = new Symbol("ALREADY_REMOVED");
  @NotNull
  private static final Object CONDITION_FALSE = new Symbol("CONDITION_FALSE");
  public static final int FAILURE = 2;
  @NotNull
  private static final Object LIST_EMPTY = new Symbol("LIST_EMPTY");
  private static final Object REMOVE_PREPARED = new Symbol("REMOVE_PREPARED");
  public static final int SUCCESS = 1;
  public static final int UNDECIDED = 0;
  
  @NotNull
  public static final Object getALREADY_REMOVED()
  {
    return ALREADY_REMOVED;
  }
  
  @NotNull
  public static final Object getCONDITION_FALSE()
  {
    return CONDITION_FALSE;
  }
  
  @NotNull
  public static final Object getLIST_EMPTY()
  {
    return LIST_EMPTY;
  }
  
  @PublishedApi
  @NotNull
  public static final LockFreeLinkedListNode unwrap(@NotNull Object paramObject)
  {
    Intrinsics.checkParameterIsNotNull(paramObject, "$receiver");
    if ((paramObject instanceof Removed)) {
      return ref;
    }
    return (LockFreeLinkedListNode)paramObject;
  }
}
