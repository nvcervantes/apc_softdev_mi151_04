package kotlin.jvm;

import java.lang.annotation.Annotation;
import java.lang.annotation.RetentionPolicy;
import kotlin.Metadata;
import kotlin.annotation.AnnotationRetention;

@java.lang.annotation.Retention(RetentionPolicy.SOURCE)
@java.lang.annotation.Target({java.lang.annotation.ElementType.TYPE, java.lang.annotation.ElementType.METHOD, java.lang.annotation.ElementType.CONSTRUCTOR})
@Metadata(bv={1, 0, 2}, d1={"\000\022\n\002\030\002\n\002\020\033\n\000\n\002\020\b\n\002\b\002\b\002\030\0002\0020\001B\024\022\b\b\002\020\002\032\0020\003\022\b\b\002\020\004\032\0020\003R\t\020\004\032\0020\003¢\006\000R\t\020\002\032\0020\003¢\006\000¨\006\005"}, d2={"Lkotlin/jvm/JvmVersion;", "", "minimum", "", "maximum", "kotlin-stdlib"}, k=1, mv={1, 1, 9})
@kotlin.annotation.Retention(AnnotationRetention.SOURCE)
@kotlin.annotation.Target(allowedTargets={kotlin.annotation.AnnotationTarget.FILE, kotlin.annotation.AnnotationTarget.CLASS, kotlin.annotation.AnnotationTarget.PROPERTY, kotlin.annotation.AnnotationTarget.CONSTRUCTOR, kotlin.annotation.AnnotationTarget.FUNCTION})
public @interface JvmVersion
{
  int maximum() default 100;
  
  int minimum() default 6;
}
