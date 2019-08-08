/*    */ package lrg.memoria.core;
/*    */ 
/*    */ import java.io.Externalizable;
/*    */ import java.io.IOException;
/*    */ import java.io.ObjectInput;
/*    */ import java.io.ObjectOutput;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Iterator;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ModelElementList<T extends ModelElementRoot>
/*    */   extends ArrayList<T>
/*    */   implements Externalizable
/*    */ {
/*    */   private ArrayList idList;
/*    */   private boolean restored = false;
/*    */   
/*    */   public void writeExternal(ObjectOutput out) throws IOException {
/* 22 */     this.idList = new ArrayList();
/* 23 */     Iterator it = iterator();
/* 24 */     while (it.hasNext()) {
/* 25 */       this.idList.add(((ModelElement)it.next()).getElementID());
/*    */     }
/* 27 */     out.writeObject(this.idList);
/*    */   }
/*    */ 
/*    */   
/* 31 */   public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException { this.idList = (ArrayList)in.readObject(); }
/*    */ 
/*    */   
/*    */   boolean restore() {
/* 35 */     if (this.restored)
/* 36 */       return false; 
/* 37 */     clear();
/* 38 */     Iterator it = this.idList.iterator();
/* 39 */     ModelElementsRepository mer = ModelElementsRepository.getCurrentModelElementsRepository();
/* 40 */     while (it.hasNext()) {
/* 41 */       T elem = (T)mer.byElementID((Long)it.next());
/* 42 */       if (elem == null) {
/* 43 */         byte b = 4;
/*    */       }
/* 45 */       ((ModelElement)elem).restore();
/* 46 */       add(elem);
/*    */     } 
/* 48 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\core\ModelElementList.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */