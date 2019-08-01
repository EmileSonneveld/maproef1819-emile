/*    */ package classes.lrg.insider.plugins.properties.memoria.classes;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*    */ import lrg.insider.plugins.filters.memoria.variables.IsTemporaryField;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class NTempF
/*    */   extends PropertyComputer
/*    */ {
/* 17 */   public NTempF() { super("NTempF", "Number of Temporary Fields", "class", "numerical"); }
/*    */ 
/*    */ 
/*    */   
/* 21 */   public ResultEntity compute(AbstractEntityInterface aClass) { return new ResultEntity(aClass.contains("attribute group").applyFilter(new IsTemporaryField()).size()); }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\properties\memoria\classes\NTempF.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */