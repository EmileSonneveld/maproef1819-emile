/*    */ package lrg.common.abstractions.plugins;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ 
/*    */ public class Descriptor
/*    */   implements Serializable
/*    */ {
/*    */   private String name;
/*    */   private String info;
/*    */   private String[] entityTypeName;
/*    */   
/*    */   public Descriptor(String name, String info, String entityTypeName) {
/* 14 */     this.name = name;
/* 15 */     this.info = info;
/* 16 */     this.entityTypeName = new String[1];
/* 17 */     this.entityTypeName[0] = entityTypeName;
/*    */   }
/*    */ 
/*    */   
/*    */   public Descriptor(String name, String info, String[] listOfEntityTypeNames) {
/* 22 */     this.name = name;
/* 23 */     this.info = info;
/* 24 */     this.entityTypeName = listOfEntityTypeNames;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 29 */   public Descriptor(String name, String entity) { this(name, "", entity); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 35 */   public Descriptor(String name, String[] listOfEntityTypeNames) { this(name, "", listOfEntityTypeNames); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 40 */   public String getName() { return this.name; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 45 */   public String getInfo() { return this.info; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 51 */   public String getEntityTypeName() { return this.entityTypeName[0]; }
/*    */ 
/*    */ 
/*    */   
/* 55 */   public String[] getAllEntityTypeNames() { return this.entityTypeName; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 60 */   public void setName(String newName) { this.name = newName; }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\common.jar!\lrg\common\abstractions\plugins\Descriptor.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */