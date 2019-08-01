/*    */ package classes.lrg.insider.plugins.commands.memoria;
/*    */ 
/*    */ import java.util.Collection;
/*    */ import lrg.insider.plugins.commands.memoria.ClassPackage;
/*    */ import lrg.memoria.core.Class;
/*    */ import lrg.memoria.core.Package;
/*    */ import lrg.norex.client.NamedCommand;
/*    */ import lrg.norex.common.commands.Command;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ClassPackage
/*    */   extends NamedCommand<Class, Package>
/*    */ {
/* 15 */   public ClassPackage() { super("ClassPackage", "Returns the package that a class belongs to.", "class"); }
/*    */ 
/*    */ 
/*    */   
/* 19 */   public Package execute(Class cls) { return cls.getPackage(); }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\commands\memoria\ClassPackage.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */