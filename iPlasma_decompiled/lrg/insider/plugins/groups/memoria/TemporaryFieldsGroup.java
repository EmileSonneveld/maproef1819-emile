/*    */ package classes.lrg.insider.plugins.groups.memoria;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.plugins.groups.GroupBuilder;
/*    */ import lrg.insider.plugins.filters.memoria.variables.IsTemporaryField;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TemporaryFieldsGroup
/*    */   extends GroupBuilder
/*    */ {
/* 18 */   public TemporaryFieldsGroup() { super("temporary fields", "", "system"); }
/*    */ 
/*    */ 
/*    */   
/* 22 */   public ArrayList buildGroup(AbstractEntityInterface measuredClass) { return measuredClass.getGroup("attribute group").applyFilter(new IsTemporaryField()).getElements(); }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\groups\memoria\TemporaryFieldsGroup.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */