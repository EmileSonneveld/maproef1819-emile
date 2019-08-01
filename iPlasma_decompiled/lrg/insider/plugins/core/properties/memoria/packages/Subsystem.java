/*    */ package classes.lrg.insider.plugins.core.properties.memoria.packages;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*    */ import lrg.memoria.core.Package;
/*    */ 
/*    */ public class Subsystem extends PropertyComputer {
/*  9 */   public Subsystem() { super("Subsystem", "The address of the package", "package", "entity"); }
/*    */ 
/*    */ 
/*    */   
/* 13 */   public ResultEntity compute(AbstractEntityInterface anEntity) { return new ResultEntity(((Package)anEntity).getSubsystem()); }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\core\properties\memoria\packages\Subsystem.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */