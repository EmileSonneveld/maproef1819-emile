/*    */ package classes.lrg.insider.plugins.groups.memoria;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntity;
/*    */ import lrg.insider.plugins.groups.memoria.InheritanceRelation;
/*    */ 
/*    */ public class InheritanceRelation extends AbstractEntity {
/*    */   private AbstractEntity subClass;
/*    */   
/*  9 */   public InheritanceRelation(AbstractEntity superC, AbstractEntity subC) { this.subClass = subC; this.superClass = superC; }
/*    */   private AbstractEntity superClass;
/*    */   
/* 12 */   public AbstractEntity getSubClass() { return this.subClass; }
/* 13 */   public AbstractEntity getSuperClass() { return this.superClass; }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\groups\memoria\InheritanceRelation.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */