/*    */ package classes.lrg.insider.plugins.commands.memoria;
/*    */ 
/*    */ import java.util.Collection;
/*    */ import lrg.insider.plugins.commands.memoria.ClassMethods;
/*    */ import lrg.memoria.core.Class;
/*    */ import lrg.memoria.core.Method;
/*    */ import lrg.norex.client.NamedCommand;
/*    */ import lrg.norex.common.commands.Command;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ClassMethods
/*    */   extends NamedCommand<Class, Collection>
/*    */ {
/* 15 */   public ClassMethods() { super("ClassMethods", "Returns a class's list of methods", "class"); }
/*    */ 
/*    */ 
/*    */   
/* 19 */   public Collection<Method> execute(Class cls) { return cls.getMethodList(); }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\commands\memoria\ClassMethods.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */