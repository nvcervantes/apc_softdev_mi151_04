package kotlin;

import java.lang.annotation.Annotation;
import java.lang.annotation.RetentionPolicy;
import kotlin.annotation.AnnotationRetention;

@java.lang.annotation.Retention(RetentionPolicy.CLASS)
@java.lang.annotation.Target({java.lang.annotation.ElementType.ANNOTATION_TYPE})
@Metadata(bv={1, 0, 2}, d1={"\000\034\n\002\030\002\n\002\020\033\n\000\n\002\030\002\n\000\n\002\020\021\n\002\030\002\n\002\b\003\b\002\030\0002\0020\001:\002\007\bB\032\022\b\b\002\020\002\032\0020\003\022\016\b\002\020\004\032\b\022\004\022\0020\0060\005R\017\020\004\032\b\022\004\022\0020\0060\005¢\006\000R\t\020\002\032\0020\003¢\006\000¨\006\t"}, d2={"Lkotlin/Experimental;", "", "level", "Lkotlin/Experimental$Level;", "changesMayBreak", "", "Lkotlin/Experimental$Impact;", "Impact", "Level", "kotlin-stdlib"}, k=1, mv={1, 1, 9})
@kotlin.annotation.Retention(AnnotationRetention.BINARY)
@kotlin.annotation.Target(allowedTargets={kotlin.annotation.AnnotationTarget.ANNOTATION_CLASS})
@SinceKotlin(version="1.3")
public @interface Experimental
{
  Impact[] changesMayBreak() default {Impact.COMPILATION, Impact.LINKAGE, Impact.RUNTIME};
  
  Level level() default Level.ERROR;
  
  @Metadata(bv={1, 0, 2}, d1={"\000\f\n\002\030\002\n\002\020\020\n\002\b\005\b\001\030\0002\b\022\004\022\0020\0000\001B\007\b\002¢\006\002\020\002j\002\b\003j\002\b\004j\002\b\005¨\006\006"}, d2={"Lkotlin/Experimental$Impact;", "", "(Ljava/lang/String;I)V", "COMPILATION", "LINKAGE", "RUNTIME", "kotlin-stdlib"}, k=1, mv={1, 1, 9})
  public static enum Impact
  {
    static
    {
      Impact localImpact1 = new Impact("COMPILATION", 0);
      COMPILATION = localImpact1;
      Impact localImpact2 = new Impact("LINKAGE", 1);
      LINKAGE = localImpact2;
      Impact localImpact3 = new Impact("RUNTIME", 2);
      RUNTIME = localImpact3;
      $VALUES = new Impact[] { localImpact1, localImpact2, localImpact3 };
    }
    
    protected Impact() {}
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\000\f\n\002\030\002\n\002\020\020\n\002\b\004\b\001\030\0002\b\022\004\022\0020\0000\001B\007\b\002¢\006\002\020\002j\002\b\003j\002\b\004¨\006\005"}, d2={"Lkotlin/Experimental$Level;", "", "(Ljava/lang/String;I)V", "WARNING", "ERROR", "kotlin-stdlib"}, k=1, mv={1, 1, 9})
  public static enum Level
  {
    static
    {
      Level localLevel1 = new Level("WARNING", 0);
      WARNING = localLevel1;
      Level localLevel2 = new Level("ERROR", 1);
      ERROR = localLevel2;
      $VALUES = new Level[] { localLevel1, localLevel2 };
    }
    
    protected Level() {}
  }
}
