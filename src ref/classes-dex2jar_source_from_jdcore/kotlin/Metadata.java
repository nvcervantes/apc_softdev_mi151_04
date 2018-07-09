package kotlin;

import java.lang.annotation.Annotation;
import java.lang.annotation.RetentionPolicy;
import kotlin.annotation.AnnotationRetention;

@java.lang.annotation.Retention(RetentionPolicy.RUNTIME)
@java.lang.annotation.Target({java.lang.annotation.ElementType.TYPE})
@Metadata(bv={1, 0, 2}, d1={"\000$\n\002\030\002\n\002\020\033\n\000\n\002\020\b\n\000\n\002\020\025\n\002\b\002\n\002\020\021\n\002\020\016\n\002\b\005\b\002\030\0002\0020\001B\\\022\b\b\002\020\002\032\0020\003\022\b\b\002\020\004\032\0020\005\022\b\b\002\020\006\032\0020\005\022\016\b\002\020\007\032\b\022\004\022\0020\t0\b\022\016\b\002\020\n\032\b\022\004\022\0020\t0\b\022\b\b\002\020\013\032\0020\t\022\b\b\002\020\f\032\0020\t\022\b\b\002\020\r\032\0020\003R\t\020\006\032\0020\005¢\006\000R\017\020\007\032\b\022\004\022\0020\t0\b¢\006\000R\017\020\n\032\b\022\004\022\0020\t0\b¢\006\000R\t\020\002\032\0020\003¢\006\000R\t\020\004\032\0020\005¢\006\000R\t\020\f\032\0020\t¢\006\000R\016\020\r\032\0020\0038\006X\004¢\006\000R\t\020\013\032\0020\t¢\006\000¨\006\016"}, d2={"Lkotlin/Metadata;", "", "k", "", "mv", "", "bv", "d1", "", "", "d2", "xs", "pn", "xi", "kotlin-runtime"}, k=1, mv={1, 1, 9})
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
@kotlin.annotation.Target(allowedTargets={kotlin.annotation.AnnotationTarget.CLASS})
public @interface Metadata
{
  int[] bv() default {};
  
  String[] d1() default {};
  
  String[] d2() default {};
  
  int k() default 1;
  
  int[] mv() default {};
  
  String pn() default "";
  
  int xi() default 0;
  
  String xs() default "";
}
