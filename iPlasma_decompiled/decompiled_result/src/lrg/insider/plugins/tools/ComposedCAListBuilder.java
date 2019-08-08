/*    */ package classes.lrg.insider.plugins.tools;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.GroupEntity;
/*    */ import lrg.insider.plugins.tools.AbstractCAListBuilder;
/*    */ import lrg.insider.plugins.tools.ComposedCAListBuilder;
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
/*    */ class ComposedCAListBuilder
/*    */   extends AbstractCAListBuilder
/*    */ {
/*    */   private ArrayList<AbstractCAListBuilder> listOfBuilders;
/*    */   
/* 54 */   public ComposedCAListBuilder(ArrayList<AbstractCAListBuilder> aListOfBuilders) { this.listOfBuilders = aListOfBuilders; }
/*    */ 
/*    */   
/*    */   protected GroupEntity getCAGroup(AbstractEntityInterface aClass) {
/* 58 */     GroupEntity resultGroup = new GroupEntity("a group", new ArrayList());
/* 59 */     for (AbstractCAListBuilder crtBuilder : this.listOfBuilders) {
/* 60 */       resultGroup = resultGroup.union(crtBuilder.getCAGroup(aClass));
/*    */     }
/* 62 */     return resultGroup;
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\tools\ComposedCAListBuilder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */