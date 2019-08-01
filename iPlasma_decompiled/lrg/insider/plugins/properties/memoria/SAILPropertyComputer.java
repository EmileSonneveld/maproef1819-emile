/*    */ package classes.lrg.insider.plugins.properties.memoria;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*    */ import lrg.insider.plugins.properties.memoria.SAILPropertyComputer;
/*    */ import lrg.memoria.core.ModelElement;
/*    */ import lrg.sail.model.SAILMeMoJAdapter;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SAILPropertyComputer
/*    */   extends PropertyComputer
/*    */ {
/*    */   private String filename;
/*    */   private String sailFunctionSignature;
/*    */   
/*    */   public SAILPropertyComputer(String name, String description, String entity, String resultType, String fname, String fsignature) {
/* 21 */     super(name, description, entity, resultType);
/* 22 */     this.filename = fname;
/* 23 */     this.sailFunctionSignature = fsignature;
/*    */   }
/*    */ 
/*    */   
/* 27 */   public ResultEntity compute(AbstractEntityInterface anEntity) { return new ResultEntity(SAILMeMoJAdapter.getInstance().execute((ModelElement)anEntity, this.filename, this.sailFunctionSignature)); }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\properties\memoria\SAILPropertyComputer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */