package kotlin.internal.contracts;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.internal.ContractsDsl;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000\026\n\002\030\002\n\002\020\000\n\000\n\002\030\002\n\000\n\002\020\013\n\000\ba\030\0002\0020\001J\021\020\002\032\0020\0032\006\020\004\032\0020\005H§\004¨\006\006"}, d2={"Lkotlin/internal/contracts/SimpleEffect;", "", "implies", "Lkotlin/internal/contracts/ConditionalEffect;", "booleanExpression", "", "kotlin-stdlib"}, k=1, mv={1, 1, 9})
@SinceKotlin(version="1.2")
@ContractsDsl
public abstract interface SimpleEffect
{
  @ContractsDsl
  @NotNull
  public abstract ConditionalEffect implies(boolean paramBoolean);
}
