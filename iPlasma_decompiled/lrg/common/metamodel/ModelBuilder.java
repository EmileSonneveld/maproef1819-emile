/*    */ package lrg.common.metamodel;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.ObjectInputStream;
/*    */ import java.util.HashMap;
/*    */ import java.util.Iterator;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.managers.CacheManager;
/*    */ import lrg.common.abstractions.managers.EntityTypeManager;
/*    */ import lrg.common.abstractions.plugins.AbstractPlugin;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class ModelBuilder
/*    */ {
/* 17 */   protected HashMap<String, AbstractEntityInterface> addressMap = new HashMap();
/*    */ 
/*    */   
/*    */   public abstract void buildModel();
/*    */ 
/*    */   
/*    */   public abstract void cleanModel();
/*    */   
/* 25 */   public HashMap<String, AbstractEntityInterface> getAddressMap() { return this.addressMap; }
/*    */ 
/*    */   
/*    */   protected abstract void registerEntityTypes();
/*    */   
/*    */   protected void createEntityTypes() {
/* 31 */     if (EntityTypeManager.loadFromCache()) {
/* 32 */       System.out.println("Load EntityTypes from Cache");
/*    */     } else {
/* 34 */       registerEntityTypes();
/* 35 */       attachOutsiders();
/*    */     } 
/* 37 */     attachPlugins();
/*    */   }
/*    */   
/*    */   private void attachPlugins() {
/* 41 */     if (CacheManager.getDynamicETCache().exists()) {
/*    */       
/* 43 */       try { ObjectInputStream serin = CacheManager.readDynamicETStream();
/*    */ 
/*    */         
/*    */         while (true) {
/* 47 */           AbstractPlugin aPlugin = (AbstractPlugin)serin.readObject();
/* 48 */           System.out.println("SERIAL: " + aPlugin.getDescriptorObject().getName());
/* 49 */           EntityTypeManager.attach(aPlugin);
/*    */         }
/*    */          }
/* 52 */       catch (IOException iOException) {  }
/* 53 */       catch (ClassNotFoundException e)
/* 54 */       { System.out.println("AICI:" + e.toString()); }
/*    */     
/*    */     }
/*    */   }
/*    */   
/*    */   private void attachOutsiders() {
/* 60 */     Loader loader = new Loader("classes");
/* 61 */     Iterator it = loader.getNames().iterator();
/* 62 */     int counter = 0;
/*    */     
/* 64 */     System.out.print("Loading the plugins...\n");
/* 65 */     while (it.hasNext()) {
/* 66 */       AbstractPlugin someCommand = loader.buildFrom((String)it.next());
/* 67 */       if (someCommand != null) {
/* 68 */         counter++;
/* 69 */         EntityTypeManager.attach(someCommand);
/*    */       } 
/*    */     } 
/* 72 */     System.out.println(String.valueOf(counter) + " plugins successfully loaded!\n");
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\common.jar!\lrg\common\metamodel\ModelBuilder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */