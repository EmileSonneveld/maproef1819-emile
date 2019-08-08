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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class CAListIncludeParameters
/*    */   extends AbstractCAListBuilder
/*    */ {
/* 44 */   protected GroupEntity getCAGroup(AbstractEntityInterface aClass) { return ((GroupEntity)aClass.getGroup("group of variables of this type")
/* 45 */       .applyFilter("is parameter").belongsTo("class"))
/* 46 */       .applyFilter("model class"); }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\tools\CAListIncludeParameters.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */