package kotlin;

import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.RetentionPolicy;
import kotlin.annotation.AnnotationRetention;
import kotlin.annotation.MustBeDocumented;

@Documented
@java.lang.annotation.Retention(RetentionPolicy.CLASS)
@java.lang.annotation.Target({})
@Metadata(bv={1, 0, 2}, d1={"\000\026\n\002\030\002\n\002\020\033\n\000\n\002\020\016\n\000\n\002\020\021\n\000\b\002\030\0002\0020\001B\034\022\006\020\002\032\0020\003\022\022\020\004\032\n\022\006\b\001\022\0020\0030\005\"\0020\003R\t\020\002\032\0020\003¢\006\000R\021\020\004\032\n\022\006\b\001\022\0020\0030\005¢\006\000¨\006\006"}, d2={"Lkotlin/ReplaceWith;", "", "expression", "", "imports", "", "kotlin-runtime"}, k=1, mv={1, 1, 9})
@MustBeDocumented
@kotlin.annotation.Retention(AnnotationRetention.BINARY)
@kotlin.annotation.Target(allowedTargets={})
public @interface ReplaceWith
{
  String expression();
  
  String[] imports();
}
