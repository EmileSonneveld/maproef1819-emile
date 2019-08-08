/*    */ package classes.lrg.insider.plugins.properties.memoria.classes;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*    */ import lrg.memoria.core.Class;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SCHIZO
/*    */   extends PropertyComputer
/*    */ {
/* 15 */   public SCHIZO() { super("SCHIZO", "Schizofrenia degree of a class", "class", "numerical"); }
/*    */ 
/*    */ 
/*    */   
/*    */   private double loc(AbstractEntityInterface anEntity) {
/* 20 */     double result = 0.0D;
/* 21 */     Iterator it = anEntity.getProperty("LOC").getValueAsCollection().iterator();
/* 22 */     while (it.hasNext()) {
/* 23 */       Object tmp = it.next();
/* 24 */       if (tmp instanceof ResultEntity)
/* 25 */         result += ((Double)((ResultEntity)tmp).getValue()).doubleValue(); 
/*    */     } 
/* 27 */     return result;
/*    */   }
/*    */   
/*    */   private boolean refusedParentBequestInChildren(AbstractEntityInterface anEntity) {
/* 31 */     if (!(anEntity instanceof Class)) return false;
/*    */     
/* 33 */     return (anEntity.getGroup("derived classes").applyFilter("Refused Parent Bequest").size() > 0);
/*    */   }
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 59 */   public ResultEntity compute(AbstractEntityInterface anEntity) { return new ResultEntity(((Class)anEntity).getInnerList().size()); }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\properties\memoria\classes\SCHIZO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */