package kotlin.jvm.internal;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.reflect.KDeclarationContainer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000 \n\002\030\002\n\002\030\002\n\002\b\002\n\002\020\000\n\000\n\002\030\002\n\000\n\002\020\002\n\002\b\002\b\027\030\0002\0020\001B\005¢\006\002\020\002J\n\020\003\032\004\030\0010\004H\026J\b\020\005\032\0020\006H\026J\022\020\007\032\0020\b2\b\020\t\032\004\030\0010\004H\026¨\006\n"}, d2={"Lkotlin/jvm/internal/MutableLocalVariableReference;", "Lkotlin/jvm/internal/MutablePropertyReference0;", "()V", "get", "", "getOwner", "Lkotlin/reflect/KDeclarationContainer;", "set", "", "value", "kotlin-runtime"}, k=1, mv={1, 1, 9})
@SinceKotlin(version="1.1")
public class MutableLocalVariableReference
  extends MutablePropertyReference0
{
  public MutableLocalVariableReference() {}
  
  @Nullable
  public Object get()
  {
    LocalVariableReferencesKt.access$notSupportedError();
    throw null;
  }
  
  @NotNull
  public KDeclarationContainer getOwner()
  {
    LocalVariableReferencesKt.access$notSupportedError();
    throw null;
  }
  
  public void set(@Nullable Object paramObject)
  {
    LocalVariableReferencesKt.access$notSupportedError();
    throw null;
  }
}
