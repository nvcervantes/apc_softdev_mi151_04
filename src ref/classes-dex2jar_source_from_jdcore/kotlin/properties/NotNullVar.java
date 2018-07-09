package kotlin.properties;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000\"\n\002\030\002\n\000\n\002\020\000\n\002\030\002\n\002\b\006\n\002\030\002\n\002\b\002\n\002\020\002\n\002\b\002\b\002\030\000*\b\b\000\020\001*\0020\0022\020\022\006\022\004\030\0010\002\022\004\022\002H\0010\003B\005¢\006\002\020\004J$\020\007\032\0028\0002\b\020\b\032\004\030\0010\0022\n\020\t\032\006\022\002\b\0030\nH\002¢\006\002\020\013J,\020\f\032\0020\r2\b\020\b\032\004\030\0010\0022\n\020\t\032\006\022\002\b\0030\n2\006\020\005\032\0028\000H\002¢\006\002\020\016R\022\020\005\032\004\030\0018\000X\016¢\006\004\n\002\020\006¨\006\017"}, d2={"Lkotlin/properties/NotNullVar;", "T", "", "Lkotlin/properties/ReadWriteProperty;", "()V", "value", "Ljava/lang/Object;", "getValue", "thisRef", "property", "Lkotlin/reflect/KProperty;", "(Ljava/lang/Object;Lkotlin/reflect/KProperty;)Ljava/lang/Object;", "setValue", "", "(Ljava/lang/Object;Lkotlin/reflect/KProperty;Ljava/lang/Object;)V", "kotlin-stdlib"}, k=1, mv={1, 1, 9})
final class NotNullVar<T>
  implements ReadWriteProperty<Object, T>
{
  private T value;
  
  public NotNullVar() {}
  
  @NotNull
  public T getValue(@Nullable Object paramObject, @NotNull KProperty<?> paramKProperty)
  {
    Intrinsics.checkParameterIsNotNull(paramKProperty, "property");
    paramObject = value;
    if (paramObject != null) {
      return paramObject;
    }
    paramObject = new StringBuilder();
    paramObject.append("Property ");
    paramObject.append(paramKProperty.getName());
    paramObject.append(" should be initialized before get.");
    throw ((Throwable)new IllegalStateException(paramObject.toString()));
  }
  
  public void setValue(@Nullable Object paramObject, @NotNull KProperty<?> paramKProperty, @NotNull T paramT)
  {
    Intrinsics.checkParameterIsNotNull(paramKProperty, "property");
    Intrinsics.checkParameterIsNotNull(paramT, "value");
    value = paramT;
  }
}
