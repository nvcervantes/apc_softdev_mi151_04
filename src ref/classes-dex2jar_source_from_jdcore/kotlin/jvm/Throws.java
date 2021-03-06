package kotlin.jvm;

import java.lang.annotation.Annotation;
import java.lang.annotation.RetentionPolicy;
import kotlin.Metadata;
import kotlin.annotation.AnnotationRetention;

@java.lang.annotation.Retention(RetentionPolicy.SOURCE)
@java.lang.annotation.Target({java.lang.annotation.ElementType.METHOD, java.lang.annotation.ElementType.CONSTRUCTOR})
@Metadata(bv={1, 0, 2}, d1={"\000\030\n\002\030\002\n\002\020\033\n\000\n\002\020\021\n\002\030\002\n\002\020\003\n\000\b\002\030\0002\0020\001B$\022\"\020\002\032\022\022\016\b\001\022\n\022\006\b\001\022\0020\0050\0040\003\"\n\022\006\b\001\022\0020\0050\004R\031\020\002\032\022\022\016\b\001\022\n\022\006\b\001\022\0020\0050\0040\003¢\006\000¨\006\006"}, d2={"Lkotlin/jvm/Throws;", "", "exceptionClasses", "", "Lkotlin/reflect/KClass;", "", "kotlin-runtime"}, k=1, mv={1, 1, 9})
@kotlin.annotation.Retention(AnnotationRetention.SOURCE)
@kotlin.annotation.Target(allowedTargets={kotlin.annotation.AnnotationTarget.FUNCTION, kotlin.annotation.AnnotationTarget.PROPERTY_GETTER, kotlin.annotation.AnnotationTarget.PROPERTY_SETTER, kotlin.annotation.AnnotationTarget.CONSTRUCTOR})
public @interface Throws
{
  Class<? extends Throwable>[] exceptionClasses();
}
