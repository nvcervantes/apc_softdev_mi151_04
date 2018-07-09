package kotlin.jvm.internal;

import kotlin.Metadata;
import kotlin.reflect.KDeclarationContainer;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000\022\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\002\b\003\bf\030\0002\0020\001R\026\020\002\032\006\022\002\b\0030\003X¦\004¢\006\006\032\004\b\004\020\005¨\006\006"}, d2={"Lkotlin/jvm/internal/ClassBasedDeclarationContainer;", "Lkotlin/reflect/KDeclarationContainer;", "jClass", "Ljava/lang/Class;", "getJClass", "()Ljava/lang/Class;", "kotlin-runtime"}, k=1, mv={1, 1, 9})
public abstract interface ClassBasedDeclarationContainer
  extends KDeclarationContainer
{
  @NotNull
  public abstract Class<?> getJClass();
}
