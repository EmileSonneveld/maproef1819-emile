/*    */ package com.birosoft.liquid.skin;
/*    */ 
/*    */ import java.awt.Component;
/*    */ import java.awt.Image;
/*    */ import java.awt.Label;
/*    */ import java.awt.MediaTracker;
/*    */ import java.awt.Toolkit;
/*    */ import java.io.ByteArrayOutputStream;
/*    */ import java.io.InputStream;
/*    */ import java.net.URL;
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
/*    */ public class SecretLoader
/*    */ {
/* 23 */   static Component component = new Label();
/* 24 */   static byte[] buffer = new byte[4096];
/*    */ 
/*    */   
/*    */   static Image loadImage(String fileName) {
/* 28 */     URL url = SecretLoader.class.getResource("/com/birosoft/liquid/icons/" + fileName);
/* 29 */     byte[] byteArray = null;
/*    */ 
/*    */     
/*    */     try {
/* 33 */       InputStream fis = null;
/* 34 */       ByteArrayOutputStream bos = new ByteArrayOutputStream();
/*    */       
/* 36 */       fis = url.openStream();
/*    */       
/* 38 */       int read = fis.read(buffer);
/* 39 */       while (read != -1) {
/*    */         
/* 41 */         bos.write(buffer, 0, read);
/* 42 */         read = fis.read(buffer);
/*    */       } 
/*    */       
/* 45 */       byteArray = bos.toByteArray();
/* 46 */       read = fis.read(byteArray);
/*    */       
/* 48 */       Image img = Toolkit.getDefaultToolkit().createImage(byteArray, 0, byteArray.length);
/*    */       
/* 50 */       MediaTracker tracker = new MediaTracker(component);
/* 51 */       tracker.addImage(img, 0);
/*    */       
/*    */       try {
/* 54 */         tracker.waitForID(0);
/* 55 */       } catch (InterruptedException ignore) {}
/*    */ 
/*    */       
/* 58 */       return img;
/* 59 */     } catch (Throwable t) {
/*    */       
/* 61 */       throw new IllegalArgumentException("File " + fileName + " could not be loaded.");
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\liquidlnf.jar!\com\birosoft\liquid\skin\SecretLoader.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.0.7
 */