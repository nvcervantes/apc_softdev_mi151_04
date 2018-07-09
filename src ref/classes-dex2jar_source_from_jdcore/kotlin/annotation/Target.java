package kotlin.annotation;

import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import kotlin.Metadata;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@java.lang.annotation.Target({java.lang.annotation.ElementType.ANNOTATION_TYPE})
@Metadata(bv={1, 0, 2}, d1={"\000\024\n\002\030\002\n\002\020\033\n\000\n\002\020\021\n\002\030\002\n\000\b\002\030\0002\0020\001B\024\022\022\020\002\032\n\022\006\b\001\022\0020\0040\003\"\0020\004R\021\020\002\032\n\022\006\b\001\022\0020\0040\003¢\006\000¨\006\005"}, d2={"Lkotlin/annotation/Target;", "", "allowedTargets", "", "Lkotlin/annotation/AnnotationTarget;", "kotlin-runtime"}, k=1, mv={1, 1, 9})
@MustBeDocumented
@Target(allowedTargets={AnnotationTarget.ANNOTATION_CLASS})
public @interface Target
{
  AnnotationTarget[] allowedTargets();
}
