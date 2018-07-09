package kotlin;

import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import kotlin.annotation.MustBeDocumented;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@java.lang.annotation.Target({java.lang.annotation.ElementType.TYPE, java.lang.annotation.ElementType.METHOD, java.lang.annotation.ElementType.CONSTRUCTOR, java.lang.annotation.ElementType.ANNOTATION_TYPE})
@Metadata(bv={1, 0, 2}, d1={"\000\034\n\002\030\002\n\002\020\033\n\000\n\002\020\016\n\000\n\002\030\002\n\000\n\002\030\002\n\000\b\002\030\0002\0020\001B\034\022\006\020\002\032\0020\003\022\b\b\002\020\004\032\0020\005\022\b\b\002\020\006\032\0020\007R\t\020\006\032\0020\007¢\006\000R\t\020\002\032\0020\003¢\006\000R\t\020\004\032\0020\005¢\006\000¨\006\b"}, d2={"Lkotlin/Deprecated;", "", "message", "", "replaceWith", "Lkotlin/ReplaceWith;", "level", "Lkotlin/DeprecationLevel;", "kotlin-runtime"}, k=1, mv={1, 1, 9})
@MustBeDocumented
@kotlin.annotation.Target(allowedTargets={kotlin.annotation.AnnotationTarget.CLASS, kotlin.annotation.AnnotationTarget.FUNCTION, kotlin.annotation.AnnotationTarget.PROPERTY, kotlin.annotation.AnnotationTarget.ANNOTATION_CLASS, kotlin.annotation.AnnotationTarget.CONSTRUCTOR, kotlin.annotation.AnnotationTarget.PROPERTY_SETTER, kotlin.annotation.AnnotationTarget.PROPERTY_GETTER, kotlin.annotation.AnnotationTarget.TYPEALIAS})
public @interface Deprecated
{
  DeprecationLevel level() default DeprecationLevel.WARNING;
  
  String message();
  
  ReplaceWith replaceWith() default @ReplaceWith(expression="", imports={});
}
