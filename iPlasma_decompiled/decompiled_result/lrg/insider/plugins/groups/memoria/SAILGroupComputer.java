/*    */ package classes.lrg.insider.plugins.groups.memoria;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.plugins.groups.GroupBuilder;
/*    */ import lrg.insider.plugins.groups.memoria.SAILGroupComputer;
/*    */ import lrg.memoria.core.ModelElement;
/*    */ import lrg.sail.model.SAILMeMoJAdapter;
/*    */ 
/*    */ public class SAILGroupComputer extends GroupBuilder {
/*    */   private String filename;
/*    */   private String sailFunctionSignature;
/*    */   
/*    */   public SAILGroupComputer(String name, String description, String entityType, String filename, String sailFunctionSignature) {
/* 15 */     super(name, description, entityType);
/* 16 */     this.filename = filename;
/* 17 */     this.sailFunctionSignature = sailFunctionSignature;
/*    */   }
/*    */   
/*    */   public ArrayList buildGroup(AbstractEntityInterface anEntity) {
/* 21 */     ar = new ArrayList();
/* 22 */     return (ArrayList)SAILMeMoJAdapter.getInstance().execute((ModelElement)anEntity, this.filename, this.sailFunctionSignature);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\groups\memoria\SAILGroupComputer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */