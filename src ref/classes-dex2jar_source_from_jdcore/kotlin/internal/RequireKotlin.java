package kotlin.internal;

import java.lang.annotation.Annotation;
import java.lang.annotation.RetentionPolicy;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.annotation.AnnotationRetention;

@java.lang.annotation.Retention(RetentionPolicy.SOURCE)
@java.lang.annotation.Target({java.lang.annotation.ElementType.TYPE, java.lang.annotation.ElementType.METHOD, java.lang.annotation.ElementType.CONSTRUCTOR})
@Metadata(bv={1, 0, 2}, d1={"\000$\n\002\030\002\n\002\020\033\n\000\n\002\020\016\n\002\b\002\n\002\030\002\n\000\n\002\030\002\n\000\n\002\020\b\n\000\b\002\030\0002\0020\001B0\022\006\020\002\032\0020\003\022\b\b\002\020\004\032\0020\003\022\b\b\002\020\005\032\0020\006\022\b\b\002\020\007\032\0020\b\022\b\b\002\020\t\032\0020\nR\t\020\t\032\0020\n¢\006\000R\t\020\005\032\0020\006¢\006\000R\t\020\004\032\0020\003¢\006\000R\t\020\002\032\0020\003¢\006\000R\t\020\007\032\0020\b¢\006\000¨\006\013"}, d2={"Lkotlin/internal/RequireKotlin;", "", "version", "", "message", "level", "Lkotlin/DeprecationLevel;", "versionKind", "Lkotlin/internal/RequireKotlinVersionKind;", "errorCode", "", "kotlin-stdlib"}, k=1, mv={1, 1, 9})
@kotlin.annotation.Retention(AnnotationRetention.SOURCE)
@kotlin.annotation.Target(allowedTargets={kotlin.annotation.AnnotationTarget.CLASS, kotlin.annotation.AnnotationTarget.FUNCTION, kotlin.annotation.AnnotationTarget.PROPERTY, kotlin.annotation.AnnotationTarget.CONSTRUCTOR, kotlin.annotation.AnnotationTarget.TYPEALIAS})
@SinceKotlin(version="1.2")
public @interface RequireKotlin
{
  int errorCode() default -1;
  
  DeprecationLevel level() default DeprecationLevel.ERROR;
  
  String message() default "";
  
  String version();
  
  RequireKotlinVersionKind versionKind() default RequireKotlinVersionKind.LANGUAGE_VERSION;
}
