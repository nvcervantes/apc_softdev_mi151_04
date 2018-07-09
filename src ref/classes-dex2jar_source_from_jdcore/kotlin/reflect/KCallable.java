package kotlin.reflect;

import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000T\n\002\030\002\n\000\n\002\030\002\n\000\n\002\020\013\n\002\b\b\n\002\020\016\n\002\b\003\n\002\020 \n\002\030\002\n\002\b\003\n\002\030\002\n\002\b\003\n\002\030\002\n\002\b\003\n\002\030\002\n\002\b\005\n\002\020\021\n\002\020\000\n\002\b\002\n\002\020$\n\002\b\002\bf\030\000*\006\b\000\020\001 \0012\0020\002J%\020\"\032\0028\0002\026\020#\032\f\022\b\b\001\022\004\030\0010%0$\"\004\030\0010%H&¢\006\002\020&J#\020'\032\0028\0002\024\020#\032\020\022\004\022\0020\022\022\006\022\004\030\0010%0(H&¢\006\002\020)R\032\020\003\032\0020\0048&X§\004¢\006\f\022\004\b\005\020\006\032\004\b\003\020\007R\032\020\b\032\0020\0048&X§\004¢\006\f\022\004\b\t\020\006\032\004\b\b\020\007R\032\020\n\032\0020\0048&X§\004¢\006\f\022\004\b\013\020\006\032\004\b\n\020\007R\022\020\f\032\0020\rX¦\004¢\006\006\032\004\b\016\020\017R\030\020\020\032\b\022\004\022\0020\0220\021X¦\004¢\006\006\032\004\b\023\020\024R\022\020\025\032\0020\026X¦\004¢\006\006\032\004\b\027\020\030R \020\031\032\b\022\004\022\0020\0320\0218&X§\004¢\006\f\022\004\b\033\020\006\032\004\b\034\020\024R\034\020\035\032\004\030\0010\0368&X§\004¢\006\f\022\004\b\037\020\006\032\004\b \020!¨\006*"}, d2={"Lkotlin/reflect/KCallable;", "R", "Lkotlin/reflect/KAnnotatedElement;", "isAbstract", "", "isAbstract$annotations", "()V", "()Z", "isFinal", "isFinal$annotations", "isOpen", "isOpen$annotations", "name", "", "getName", "()Ljava/lang/String;", "parameters", "", "Lkotlin/reflect/KParameter;", "getParameters", "()Ljava/util/List;", "returnType", "Lkotlin/reflect/KType;", "getReturnType", "()Lkotlin/reflect/KType;", "typeParameters", "Lkotlin/reflect/KTypeParameter;", "typeParameters$annotations", "getTypeParameters", "visibility", "Lkotlin/reflect/KVisibility;", "visibility$annotations", "getVisibility", "()Lkotlin/reflect/KVisibility;", "call", "args", "", "", "([Ljava/lang/Object;)Ljava/lang/Object;", "callBy", "", "(Ljava/util/Map;)Ljava/lang/Object;", "kotlin-runtime"}, k=1, mv={1, 1, 9})
public abstract interface KCallable<R>
  extends KAnnotatedElement
{
  public abstract R call(@NotNull Object... paramVarArgs);
  
  public abstract R callBy(@NotNull Map<KParameter, ? extends Object> paramMap);
  
  @NotNull
  public abstract String getName();
  
  @NotNull
  public abstract List<KParameter> getParameters();
  
  @NotNull
  public abstract KType getReturnType();
  
  @NotNull
  public abstract List<KTypeParameter> getTypeParameters();
  
  @Nullable
  public abstract KVisibility getVisibility();
  
  public abstract boolean isAbstract();
  
  public abstract boolean isFinal();
  
  public abstract boolean isOpen();
  
  @Metadata(bv={1, 0, 2}, k=3, mv={1, 1, 9})
  public static final class DefaultImpls {}
}
