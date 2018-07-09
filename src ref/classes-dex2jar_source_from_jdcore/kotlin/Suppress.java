package kotlin;

import java.lang.annotation.Annotation;
import java.lang.annotation.RetentionPolicy;
import kotlin.annotation.AnnotationRetention;

@java.lang.annotation.Retention(RetentionPolicy.SOURCE)
@java.lang.annotation.Target({java.lang.annotation.ElementType.TYPE, java.lang.annotation.ElementType.FIELD, java.lang.annotation.ElementType.METHOD, java.lang.annotation.ElementType.PARAMETER, java.lang.annotation.ElementType.CONSTRUCTOR, java.lang.annotation.ElementType.LOCAL_VARIABLE, java.lang.annotation.ElementType.ANNOTATION_TYPE})
@Metadata(bv={1, 0, 2}, d1={"\000\024\n\002\030\002\n\002\020\033\n\000\n\002\020\021\n\002\020\016\n\000\b\002\030\0002\0020\001B\024\022\022\020\002\032\n\022\006\b\001\022\0020\0040\003\"\0020\004R\021\020\002\032\n\022\006\b\001\022\0020\0040\003¢\006\000¨\006\005"}, d2={"Lkotlin/Suppress;", "", "names", "", "", "kotlin-runtime"}, k=1, mv={1, 1, 9})
@kotlin.annotation.Retention(AnnotationRetention.SOURCE)
@kotlin.annotation.Target(allowedTargets={kotlin.annotation.AnnotationTarget.CLASS, kotlin.annotation.AnnotationTarget.ANNOTATION_CLASS, kotlin.annotation.AnnotationTarget.PROPERTY, kotlin.annotation.AnnotationTarget.FIELD, kotlin.annotation.AnnotationTarget.LOCAL_VARIABLE, kotlin.annotation.AnnotationTarget.VALUE_PARAMETER, kotlin.annotation.AnnotationTarget.CONSTRUCTOR, kotlin.annotation.AnnotationTarget.FUNCTION, kotlin.annotation.AnnotationTarget.PROPERTY_GETTER, kotlin.annotation.AnnotationTarget.PROPERTY_SETTER, kotlin.annotation.AnnotationTarget.TYPE, kotlin.annotation.AnnotationTarget.EXPRESSION, kotlin.annotation.AnnotationTarget.FILE, kotlin.annotation.AnnotationTarget.TYPEALIAS})
public @interface Suppress
{
  String[] names();
}
