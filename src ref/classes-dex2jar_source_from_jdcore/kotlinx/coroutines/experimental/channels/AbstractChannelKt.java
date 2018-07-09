package kotlinx.coroutines.experimental.channels;

import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlinx.coroutines.experimental.internal.Symbol;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000\020\n\000\n\002\020\000\n\002\b\007\n\002\030\002\n\000\"\020\020\000\032\0020\0018\006X\004¢\006\002\n\000\"\020\020\002\032\0020\0018\006X\004¢\006\002\n\000\"\020\020\003\032\0020\0018\006X\004¢\006\002\n\000\"\020\020\004\032\0020\0018\006X\004¢\006\002\n\000\"\020\020\005\032\0020\0018\006X\004¢\006\002\n\000\"\020\020\006\032\0020\0018\006X\004¢\006\002\n\000\"\020\020\007\032\0020\0018\006X\004¢\006\002\n\000\"\020\020\b\032\0020\t8\006X\004¢\006\002\n\000¨\006\n"}, d2={"CLOSE_RESUMED", "", "ENQUEUE_FAILED", "NULL_VALUE", "OFFER_FAILED", "OFFER_SUCCESS", "POLL_FAILED", "SELECT_STARTED", "SEND_RESUMED", "Lkotlinx/coroutines/experimental/internal/Symbol;", "kotlinx-coroutines-core"}, k=2, mv={1, 1, 7})
public final class AbstractChannelKt
{
  @JvmField
  @NotNull
  public static final Object CLOSE_RESUMED = new Symbol("CLOSE_RESUMED");
  @JvmField
  @NotNull
  public static final Object ENQUEUE_FAILED;
  @JvmField
  @NotNull
  public static final Object NULL_VALUE;
  @JvmField
  @NotNull
  public static final Object OFFER_FAILED;
  @JvmField
  @NotNull
  public static final Object OFFER_SUCCESS = new Symbol("OFFER_SUCCESS");
  @JvmField
  @NotNull
  public static final Object POLL_FAILED;
  @JvmField
  @NotNull
  public static final Object SELECT_STARTED;
  @JvmField
  @NotNull
  public static final Symbol SEND_RESUMED = new Symbol("SEND_RESUMED");
  
  static
  {
    OFFER_FAILED = new Symbol("OFFER_FAILED");
    POLL_FAILED = new Symbol("POLL_FAILED");
    ENQUEUE_FAILED = new Symbol("ENQUEUE_FAILED");
    SELECT_STARTED = new Symbol("SELECT_STARTED");
    NULL_VALUE = new Symbol("NULL_VALUE");
  }
}
