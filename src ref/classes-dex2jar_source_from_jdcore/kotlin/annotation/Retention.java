package kotlin.annotation;

import java.lang.annotation.Annotation;
import java.lang.annotation.RetentionPolicy;
import kotlin.Metadata;

@java.lang.annotation.Retention(RetentionPolicy.RUNTIME)
@java.lang.annotation.Target({java.lang.annotation.ElementType.ANNOTATION_TYPE})
@Metadata(bv={1, 0, 2}, d1={"\000\020\n\002\030\002\n\002\020\033\n\000\n\002\030\002\n\000\b\002\030\0002\0020\001B\n\022\b\b\002\020\002\032\0020\003R\t\020\002\032\0020\003¢\006\000¨\006\004"}, d2={"Lkotlin/annotation/Retention;", "", "value", "Lkotlin/annotation/AnnotationRetention;", "kotlin-runtime"}, k=1, mv={1, 1, 9})
@Target(allowedTargets={AnnotationTarget.ANNOTATION_CLASS})
public @interface Retention
{
  AnnotationRetention value() default AnnotationRetention.RUNTIME;
}
