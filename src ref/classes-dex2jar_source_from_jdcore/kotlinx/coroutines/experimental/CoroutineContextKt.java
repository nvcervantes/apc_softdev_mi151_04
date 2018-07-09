package kotlinx.coroutines.experimental;

import java.util.concurrent.atomic.AtomicLong;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.coroutines.experimental.CoroutineContext;
import kotlin.coroutines.experimental.CoroutineContext.Key;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000@\n\000\n\002\030\002\n\000\n\002\020\013\n\000\n\002\020\016\n\000\n\002\030\002\n\002\b\002\n\002\020\002\n\002\b\006\n\002\030\002\n\002\b\002\n\002\030\002\n\002\030\002\n\002\b\003\n\002\030\002\n\002\b\004\032\016\020\006\032\0020\0072\006\020\b\032\0020\007\032\b\020\t\032\0020\nH\000\032\022\020\013\032\0020\n2\b\020\f\032\004\030\0010\005H\001\032\022\020\r\032\004\030\0010\0052\006\020\b\032\0020\007H\001\032*\020\016\032\002H\017\"\004\b\000\020\0172\006\020\b\032\0020\0072\f\020\020\032\b\022\004\022\002H\0170\021H\b¢\006\002\020\022\"\016\020\000\032\0020\001X\004¢\006\002\n\000\"\016\020\002\032\0020\003X\004¢\006\002\n\000\"\016\020\004\032\0020\005XT¢\006\002\n\000*8\b\007\020\023\"\0020\0242\0020\024B*\b\025\022\b\b\026\022\004\b\b(\027\022\034\b\030\022\030\b\013B\024\b\031\022\b\b\032\022\004\b\b(\033\022\006\b\034\022\002\b\f¨\006\035"}, d2={"COROUTINE_ID", "Ljava/util/concurrent/atomic/AtomicLong;", "DEBUG", "", "DEBUG_PROPERTY_NAME", "", "newCoroutineContext", "Lkotlin/coroutines/experimental/CoroutineContext;", "context", "resetCoroutineId", "", "restoreContext", "oldName", "updateContext", "withCoroutineContext", "T", "block", "Lkotlin/Function0;", "(Lkotlin/coroutines/experimental/CoroutineContext;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "Here", "Lkotlinx/coroutines/experimental/Unconfined;", "Lkotlin/Deprecated;", "message", "`Here` was renamed to `Unconfined`", "replaceWith", "Lkotlin/ReplaceWith;", "expression", "Unconfined", "imports", "kotlinx-coroutines-core"}, k=2, mv={1, 1, 7})
public final class CoroutineContextKt
{
  private static final AtomicLong COROUTINE_ID;
  private static final boolean DEBUG;
  private static final String DEBUG_PROPERTY_NAME = "kotlinx.coroutines.debug";
  
  static
  {
    try
    {
      str = System.getProperty("kotlinx.coroutines.debug");
    }
    catch (SecurityException localSecurityException)
    {
      String str;
      boolean bool;
      int i;
      label75:
      label87:
      label98:
      label113:
      StringBuilder localStringBuilder;
      for (;;) {}
    }
    str = null;
    bool = false;
    if (str != null)
    {
      i = str.hashCode();
      if (i == 0) {
        break label87;
      }
      if (i == 3551) {
        break label75;
      }
      if (i != 109935) {
        if ((i != 3005871) || (!str.equals("auto"))) {
          break label113;
        }
      }
    }
    else
    {
      break label98;
    }
    if (str.equals("off")) {
      if (str.equals("on")) {
        if (str.equals(""))
        {
          bool = true;
          DEBUG = bool;
          COROUTINE_ID = new AtomicLong();
          return;
        }
      }
    }
    localStringBuilder = new StringBuilder();
    localStringBuilder.append("System property 'kotlinx.coroutines.debug' has unrecognized value '");
    localStringBuilder.append(str);
    localStringBuilder.append('\'');
    throw ((Throwable)new IllegalStateException(localStringBuilder.toString().toString()));
  }
  
  @NotNull
  public static final CoroutineContext newCoroutineContext(@NotNull CoroutineContext paramCoroutineContext)
  {
    Intrinsics.checkParameterIsNotNull(paramCoroutineContext, "context");
    CoroutineContext localCoroutineContext = paramCoroutineContext;
    if (DEBUG) {
      localCoroutineContext = paramCoroutineContext.plus((CoroutineContext)new CoroutineId(COROUTINE_ID.incrementAndGet()));
    }
    return localCoroutineContext;
  }
  
  public static final void resetCoroutineId()
  {
    COROUTINE_ID.set(0L);
  }
  
  @PublishedApi
  public static final void restoreContext(@Nullable String paramString)
  {
    if (paramString != null) {
      Thread.currentThread().setName(paramString);
    }
  }
  
  @PublishedApi
  @Nullable
  public static final String updateContext(@NotNull CoroutineContext paramCoroutineContext)
  {
    Intrinsics.checkParameterIsNotNull(paramCoroutineContext, "context");
    if (!DEBUG) {
      return null;
    }
    CoroutineId localCoroutineId = (CoroutineId)paramCoroutineContext.get((CoroutineContext.Key)CoroutineId.Key);
    if (localCoroutineId != null)
    {
      Thread localThread = Thread.currentThread();
      String str = localThread.getName();
      paramCoroutineContext = (CoroutineName)paramCoroutineContext.get((CoroutineContext.Key)CoroutineName.Key);
      if (paramCoroutineContext != null)
      {
        paramCoroutineContext = paramCoroutineContext.getName();
        if (paramCoroutineContext != null) {}
      }
      else
      {
        paramCoroutineContext = "coroutine";
      }
      StringBuilder localStringBuilder = new StringBuilder(str.length() + paramCoroutineContext.length() + 10);
      localStringBuilder.append(str);
      localStringBuilder.append(" @");
      localStringBuilder.append(paramCoroutineContext);
      localStringBuilder.append('#');
      localStringBuilder.append(localCoroutineId.getId());
      paramCoroutineContext = localStringBuilder.toString();
      Intrinsics.checkExpressionValueIsNotNull(paramCoroutineContext, "StringBuilder(capacity).…builderAction).toString()");
      localThread.setName(paramCoroutineContext);
      return str;
    }
    return null;
  }
  
  public static final <T> T withCoroutineContext(@NotNull CoroutineContext paramCoroutineContext, @NotNull Function0<? extends T> paramFunction0)
  {
    Intrinsics.checkParameterIsNotNull(paramCoroutineContext, "context");
    Intrinsics.checkParameterIsNotNull(paramFunction0, "block");
    paramCoroutineContext = updateContext(paramCoroutineContext);
    try
    {
      paramFunction0 = paramFunction0.invoke();
      return paramFunction0;
    }
    finally
    {
      InlineMarker.finallyStart(1);
      restoreContext(paramCoroutineContext);
      InlineMarker.finallyEnd(1);
    }
  }
}
