package kotlin.annotation;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import kotlin.Metadata;

@Retention(RetentionPolicy.RUNTIME)
@java.lang.annotation.Target({java.lang.annotation.ElementType.ANNOTATION_TYPE})
@Metadata(bv={1, 0, 2}, d1={"\000\n\n\002\030\002\n\002\020\033\n\000\b\002\030\0002\0020\001B\000¨\006\002"}, d2={"Lkotlin/annotation/MustBeDocumented;", "", "kotlin-runtime"}, k=1, mv={1, 1, 9})
@Target(allowedTargets={AnnotationTarget.ANNOTATION_CLASS})
public @interface MustBeDocumented {}
