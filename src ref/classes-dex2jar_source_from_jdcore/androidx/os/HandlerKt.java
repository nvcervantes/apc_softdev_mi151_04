package androidx.os;

import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\0002\n\000\n\002\030\002\n\002\030\002\n\000\n\002\020\t\n\000\n\002\020\000\n\000\n\002\030\002\n\002\020\002\n\002\b\004\n\002\030\002\n\002\b\002\n\002\030\002\n\000\0321\020\000\032\0020\001*\0020\0022\006\020\003\032\0020\0042\n\b\002\020\005\032\004\030\0010\0062\016\b\004\020\007\032\b\022\004\022\0020\t0\bH\b\032$\020\n\032\0020\t*\0020\0022\006\020\013\032\0020\0012\b\020\005\032\004\030\0010\0062\006\020\f\032\0020\004\0321\020\n\032\0020\001*\0020\0022\006\020\r\032\0020\0162\n\b\002\020\005\032\004\030\0010\0062\016\b\004\020\007\032\b\022\004\022\0020\t0\bH\b\0329\020\n\032\0020\001*\0020\0022\006\020\017\032\0020\0042\006\020\020\032\0020\0212\n\b\002\020\005\032\004\030\0010\0062\016\b\004\020\007\032\b\022\004\022\0020\t0\bH\b\0321\020\n\032\0020\001*\0020\0022\006\020\f\032\0020\0042\n\b\002\020\005\032\004\030\0010\0062\016\b\004\020\007\032\b\022\004\022\0020\t0\bH\b¨\006\022"}, d2={"postAtTime", "Ljava/lang/Runnable;", "Landroid/os/Handler;", "uptimeMillis", "", "token", "", "action", "Lkotlin/Function0;", "", "postDelayed", "runnable", "delayInMillis", "duration", "Ljava/time/Duration;", "amount", "unit", "Ljava/util/concurrent/TimeUnit;", "core-ktx_release"}, k=2, mv={1, 1, 9})
public final class HandlerKt
{
  @NotNull
  public static final Runnable postAtTime(@NotNull Handler paramHandler, long paramLong, @Nullable Object paramObject, @NotNull Function0<Unit> paramFunction0)
  {
    paramFunction0 = (Runnable)new Runnable()
    {
      public final void run()
      {
        $action.invoke();
      }
    };
    paramHandler.postAtTime(paramFunction0, paramObject, paramLong);
    return paramFunction0;
  }
  
  @NotNull
  public static final Runnable postDelayed(@NotNull Handler paramHandler, long paramLong, @Nullable Object paramObject, @NotNull Function0<Unit> paramFunction0)
  {
    paramFunction0 = (Runnable)new Runnable()
    {
      public final void run()
      {
        $action.invoke();
      }
    };
    postDelayed(paramHandler, paramFunction0, paramObject, paramLong);
    return paramFunction0;
  }
  
  @NotNull
  public static final Runnable postDelayed(@NotNull Handler paramHandler, long paramLong, @NotNull TimeUnit paramTimeUnit, @Nullable Object paramObject, @NotNull Function0<Unit> paramFunction0)
  {
    paramLong = paramTimeUnit.toMillis(paramLong);
    paramTimeUnit = (Runnable)new Runnable()
    {
      public final void run()
      {
        $action.invoke();
      }
    };
    postDelayed(paramHandler, paramTimeUnit, paramObject, paramLong);
    return paramTimeUnit;
  }
  
  @RequiresApi(26)
  @NotNull
  public static final Runnable postDelayed(@NotNull Handler paramHandler, @NotNull Duration paramDuration, @Nullable Object paramObject, @NotNull Function0<Unit> paramFunction0)
  {
    long l = paramDuration.toMillis();
    paramDuration = (Runnable)new Runnable()
    {
      public final void run()
      {
        $action.invoke();
      }
    };
    postDelayed(paramHandler, paramDuration, paramObject, l);
    return paramDuration;
  }
  
  public static final void postDelayed(@NotNull Handler paramHandler, @NotNull Runnable paramRunnable, @Nullable Object paramObject, long paramLong)
  {
    paramRunnable = Message.obtain(paramHandler, paramRunnable);
    obj = paramObject;
    paramHandler.sendMessageDelayed(paramRunnable, paramLong);
  }
}
