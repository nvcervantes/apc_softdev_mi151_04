package kotlin.internal.contracts;

import kotlin.Function;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.internal.ContractsDsl;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000,\n\002\030\002\n\002\020\000\n\000\n\002\030\002\n\002\b\002\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\002\b\002\n\002\030\002\n\000\ba\030\0002\0020\001J&\020\002\032\0020\003\"\004\b\000\020\0042\f\020\005\032\b\022\004\022\002H\0040\0062\b\b\002\020\007\032\0020\bH'J\b\020\t\032\0020\nH'J\022\020\t\032\0020\n2\b\020\013\032\004\030\0010\001H'J\b\020\f\032\0020\rH'¨\006\016"}, d2={"Lkotlin/internal/contracts/ContractBuilder;", "", "callsInPlace", "Lkotlin/internal/contracts/CallsInPlace;", "R", "lambda", "Lkotlin/Function;", "kind", "Lkotlin/internal/contracts/InvocationKind;", "returns", "Lkotlin/internal/contracts/Returns;", "value", "returnsNotNull", "Lkotlin/internal/contracts/ReturnsNotNull;", "kotlin-stdlib"}, k=1, mv={1, 1, 9})
@SinceKotlin(version="1.2")
@ContractsDsl
public abstract interface ContractBuilder
{
  @ContractsDsl
  @NotNull
  public abstract <R> CallsInPlace callsInPlace(@NotNull Function<? extends R> paramFunction, @NotNull InvocationKind paramInvocationKind);
  
  @ContractsDsl
  @NotNull
  public abstract Returns returns();
  
  @ContractsDsl
  @NotNull
  public abstract Returns returns(@Nullable Object paramObject);
  
  @ContractsDsl
  @NotNull
  public abstract ReturnsNotNull returnsNotNull();
  
  @Metadata(bv={1, 0, 2}, k=3, mv={1, 1, 9})
  public static final class DefaultImpls {}
}
