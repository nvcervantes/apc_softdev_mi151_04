package kotlin.jvm;

import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.RetentionPolicy;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.annotation.AnnotationRetention;
import kotlin.annotation.MustBeDocumented;

@Documented
@java.lang.annotation.Retention(RetentionPolicy.SOURCE)
@java.lang.annotation.Target({})
@Metadata(bv={1, 0, 2}, d1={"\000\020\n\002\030\002\n\002\020\033\n\000\n\002\020\016\n\000\b\002\030\0002\0020\001B\b\022\006\020\002\032\0020\003R\t\020\002\032\0020\003¢\006\000¨\006\004"}, d2={"Lkotlin/jvm/JvmPackageName;", "", "name", "", "kotlin-runtime"}, k=1, mv={1, 1, 9})
@MustBeDocumented
@kotlin.annotation.Retention(AnnotationRetention.SOURCE)
@kotlin.annotation.Target(allowedTargets={kotlin.annotation.AnnotationTarget.FILE})
@SinceKotlin(version="1.2")
public @interface JvmPackageName
{
  String name();
}
