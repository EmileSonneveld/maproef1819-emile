/*    */ package classes.lrg.insider.plugins.properties.memoria.methods;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import lrg.common.abstractions.entities.AbstractEntity;
/*    */ import lrg.insider.plugins.properties.memoria.methods.CoolResultType;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class CoolResultType
/*    */ {
/*    */   private AbstractEntity scopeClass;
/*    */   private AbstractEntity theMethod;
/*    */   private ArrayList instantiatedClasses;
/*    */   
/* 27 */   public CoolResultType(AbstractEntity cls, AbstractEntity mth, ArrayList instCls) { this.scopeClass = cls; this.theMethod = mth; this.instantiatedClasses = instCls; }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 31 */     theString = "[" + this.scopeClass.getName() + ";" + this.theMethod.getName() + "; (";
/*    */     
/* 33 */     for (int i = 0; i < this.instantiatedClasses.size(); i++)
/* 34 */       theString = String.valueOf(theString) + ((AbstractEntity)this.instantiatedClasses.get(i)).getName() + ","; 
/* 35 */     return String.valueOf(theString) + ")]";
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\properties\memoria\methods\CoolResultType.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */