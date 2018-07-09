package kotlinx.coroutines.experimental.channels;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.experimental.internal.AtomicDesc;
import kotlinx.coroutines.experimental.internal.LockFreeLinkedListNode;
import kotlinx.coroutines.experimental.selects.SelectInstance;
import kotlinx.coroutines.experimental.selects.SelectKt;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\0002\n\002\030\002\n\000\n\002\030\002\n\002\b\002\n\002\020\013\n\002\b\005\n\002\020\000\n\002\b\004\n\002\030\002\n\002\b\002\n\002\020\002\n\000\n\002\030\002\n\000\b\026\030\000*\004\b\000\020\0012\b\022\004\022\002H\0010\002B\005¢\006\002\020\003J\025\020\n\032\0020\0132\006\020\f\032\0028\000H\024¢\006\002\020\rJ!\020\016\032\0020\0132\006\020\f\032\0028\0002\n\020\017\032\006\022\002\b\0030\020H\024¢\006\002\020\021J\026\020\022\032\0020\0232\f\020\024\032\b\022\004\022\0028\0000\025H\024R\024\020\004\032\0020\0058DX\004¢\006\006\032\004\b\004\020\006R\024\020\007\032\0020\0058DX\004¢\006\006\032\004\b\007\020\006R\024\020\b\032\0020\0058DX\004¢\006\006\032\004\b\b\020\006R\024\020\t\032\0020\0058DX\004¢\006\006\032\004\b\t\020\006¨\006\026"}, d2={"Lkotlinx/coroutines/experimental/channels/ConflatedChannel;", "E", "Lkotlinx/coroutines/experimental/channels/AbstractChannel;", "()V", "isBufferAlwaysEmpty", "", "()Z", "isBufferAlwaysFull", "isBufferEmpty", "isBufferFull", "offerInternal", "", "element", "(Ljava/lang/Object;)Ljava/lang/Object;", "offerSelectInternal", "select", "Lkotlinx/coroutines/experimental/selects/SelectInstance;", "(Ljava/lang/Object;Lkotlinx/coroutines/experimental/selects/SelectInstance;)Ljava/lang/Object;", "onClosed", "", "closed", "Lkotlinx/coroutines/experimental/channels/Closed;", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
public class ConflatedChannel<E>
  extends AbstractChannel<E>
{
  public ConflatedChannel() {}
  
  protected final boolean isBufferAlwaysEmpty()
  {
    return true;
  }
  
  protected final boolean isBufferAlwaysFull()
  {
    return false;
  }
  
  protected final boolean isBufferEmpty()
  {
    return true;
  }
  
  protected final boolean isBufferFull()
  {
    return false;
  }
  
  @NotNull
  protected Object offerInternal(E paramE)
  {
    Object localObject;
    do
    {
      localObject = super.offerInternal(paramE);
      if (localObject == AbstractChannelKt.OFFER_SUCCESS) {
        return AbstractChannelKt.OFFER_SUCCESS;
      }
      if (localObject != AbstractChannelKt.OFFER_FAILED) {
        break;
      }
      localObject = sendConflated(paramE);
      if (Intrinsics.areEqual(localObject, null)) {
        return AbstractChannelKt.OFFER_SUCCESS;
      }
    } while (!(localObject instanceof Closed));
    return localObject;
    if ((localObject instanceof Closed)) {
      return localObject;
    }
    paramE = new StringBuilder();
    paramE.append("Invalid offerInternal result ");
    paramE.append(localObject);
    throw ((Throwable)new IllegalStateException(paramE.toString().toString()));
  }
  
  @NotNull
  protected Object offerSelectInternal(E paramE, @NotNull SelectInstance<?> paramSelectInstance)
  {
    Intrinsics.checkParameterIsNotNull(paramSelectInstance, "select");
    Object localObject;
    do
    {
      if (getHasReceiveOrClosed())
      {
        localObject = super.offerSelectInternal(paramE, paramSelectInstance);
      }
      else
      {
        localObject = paramSelectInstance.performAtomicTrySelect((AtomicDesc)describeSendConflated(paramE));
        if (localObject == null) {
          localObject = AbstractChannelKt.OFFER_SUCCESS;
        }
      }
      if (localObject == SelectKt.getALREADY_SELECTED()) {
        return SelectKt.getALREADY_SELECTED();
      }
      if (localObject == AbstractChannelKt.OFFER_SUCCESS) {
        return AbstractChannelKt.OFFER_SUCCESS;
      }
    } while (localObject == AbstractChannelKt.OFFER_FAILED);
    if ((localObject instanceof Closed)) {
      return localObject;
    }
    paramE = new StringBuilder();
    paramE.append("Invalid result ");
    paramE.append(localObject);
    throw ((Throwable)new IllegalStateException(paramE.toString().toString()));
  }
  
  protected void onClosed(@NotNull Closed<? super E> paramClosed)
  {
    Intrinsics.checkParameterIsNotNull(paramClosed, "closed");
    conflatePreviousSendBuffered((LockFreeLinkedListNode)paramClosed);
  }
}
