/*    */ package classes.lrg.insider.plugins.groups.memoria;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.plugins.filters.FilteringRule;
/*    */ import lrg.common.abstractions.plugins.groups.GroupBuilder;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MethodsDependingOnBaseclass
/*    */   extends GroupBuilder
/*    */ {
/* 18 */   public MethodsDependingOnBaseclass() { super("methods depending on base class", "", "class"); }
/*    */ 
/*    */   
/*    */   public ArrayList buildGroup(AbstractEntityInterface aClass) {
/* 22 */     FilteringRule aRule = new FilteringRule("BCD", ">", "method", 0.0D);
/*    */     
/* 24 */     return aClass.contains("method group").applyFilter(aRule).getElements();
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\groups\memoria\MethodsDependingOnBaseclass.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */