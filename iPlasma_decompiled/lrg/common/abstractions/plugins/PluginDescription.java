package lrg.common.abstractions.plugins;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface PluginDescription {
  String name() default "";
  
  String description() default "";
  
  String entityType() default "";
  
  String resultEntityType() default "";
}


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\common.jar!\lrg\common\abstractions\plugins\PluginDescription.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */