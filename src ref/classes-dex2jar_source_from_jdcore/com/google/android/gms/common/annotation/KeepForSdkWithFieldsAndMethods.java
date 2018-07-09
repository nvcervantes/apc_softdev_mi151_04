package com.google.android.gms.common.annotation;

import com.google.android.gms.common.internal.Hide;
import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.Target;

@Deprecated
@Documented
@Target({java.lang.annotation.ElementType.TYPE})
@Hide
public @interface KeepForSdkWithFieldsAndMethods {}
