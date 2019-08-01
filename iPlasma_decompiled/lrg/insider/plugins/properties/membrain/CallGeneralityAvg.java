/*    */ package classes.lrg.insider.plugins.properties.membrain;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ import lrg.common.abstractions.entities.AbstractEntity;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.GroupEntity;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*    */ import lrg.memoria.core.Method;
/*    */ 
/*    */ public class CallGeneralityAvg
/*    */   extends PropertyComputer
/*    */ {
/* 14 */   public CallGeneralityAvg() { super("average call generality", "Average call Generality", "method", "numerical"); }
/*    */ 
/*    */ 
/*    */   
/*    */   public ResultEntity compute(AbstractEntityInterface anEntity) {
/* 19 */     if (!(anEntity instanceof Method))
/*    */     {
/* 21 */       return new ResultEntity(-2.0D);
/*    */     }
/*    */     
/* 24 */     if (((Method)anEntity).isAbstract())
/*    */     {
/* 26 */       return new ResultEntity(-1.0D);
/*    */     }
/*    */     
/* 29 */     GroupEntity aGroup = anEntity.getGroup("call group");
/* 30 */     Iterator it = aGroup.getElements().iterator();
/* 31 */     double result = 0.0D, size = 0.0D;
/* 32 */     while (it.hasNext()) {
/* 33 */       double tmp = ((Double)((AbstractEntity)it.next()).getProperty("call generality").getValue()).doubleValue();
/* 34 */       if (tmp >= 0.0D) {
/* 35 */         size++;
/* 36 */         result += tmp;
/*    */       } 
/*    */     } 
/* 39 */     return new ResultEntity((size == 0.0D) ? 1.0D : (result / size));
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\properties\membrain\CallGeneralityAvg.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */