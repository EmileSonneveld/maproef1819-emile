/*    */ package com.birosoft.liquid.skin;
/*    */ 
/*    */ import java.awt.Image;
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
/*    */ public class SkinElement
/*    */ {
/*    */   private String filename;
/*    */   private Image image;
/*    */   
/*    */   public SkinElement(String filename, boolean useAutomaticBitmap) {
/* 28 */     this.filename = filename;
/* 29 */     if (useAutomaticBitmap) {
/*    */       
/* 31 */       this.image = SkinImageCache.getInstance().getAutomaticImage(filename);
/*    */     }
/*    */     else {
/*    */       
/* 35 */       this.image = SkinImageCache.getInstance().getImage(filename);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 45 */   public String getFilename() { return this.filename; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 54 */   public void setFilename(String filename) { this.filename = filename; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 63 */   public Image getImage() { return this.image; }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\liquidlnf.jar!\com\birosoft\liquid\skin\SkinElement.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.0.7
 */