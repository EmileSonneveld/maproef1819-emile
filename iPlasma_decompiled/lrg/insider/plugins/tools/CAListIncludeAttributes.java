/*    */ package classes.lrg.insider.plugins.tools;
/*    */ 
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
/*    */ class CAListIncludeAttributes
/*    */   extends AbstractCAListBuilder
/*    */ {
/* 35 */   protected GroupEntity getCAGroup(AbstractEntityInterface aClass) { return ((GroupEntity)aClass.getGroup("group of variables of this type")
/* 36 */       .applyFilter("is attribute").belongsTo("class"))
/* 37 */       .applyFilter("model class"); }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\tools\CAListIncludeAttributes.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */