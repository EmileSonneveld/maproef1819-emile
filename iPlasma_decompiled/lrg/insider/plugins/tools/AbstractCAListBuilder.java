/*    */ package classes.lrg.insider.plugins.tools;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import lrg.common.abstractions.entities.AbstractEntity;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.GroupEntity;
/*    */ import lrg.insider.plugins.tools.AbstractCAListBuilder;
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
/*    */ abstract class AbstractCAListBuilder
/*    */ {
/*    */   protected abstract GroupEntity getCAGroup(AbstractEntityInterface paramAbstractEntityInterface);
/*    */   
/* 22 */   public ArrayList<AbstractEntityInterface> buildCAListFor(AbstractEntityInterface aClass) { return getCAGroup(aClass).exclude((AbstractEntity)aClass).applyFilter("model class").distinct().getElements(); }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\tools\AbstractCAListBuilder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */