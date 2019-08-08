/*    */ import com.birosoft.liquid.LiquidLookAndFeel;
/*    */ import java.beans.PropertyChangeEvent;
/*    */ import java.beans.PropertyChangeListener;
/*    */ import java.io.File;
/*    */ import java.lang.reflect.Method;
/*    */ import java.net.URL;
/*    */ import java.net.URLClassLoader;
/*    */ import java.util.jar.JarFile;
/*    */ import java.util.jar.Manifest;
/*    */ import javax.swing.UIManager;
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
/*    */ public class Launcher
/*    */ {
/*    */   static Class array$Ljava$lang$String;
/*    */   
/*    */   public static void main(String[] args) throws Exception {
/* 35 */     System.setProperty("sun.java2d.ddscale", "true");
/* 36 */     UIManager.setLookAndFeel("com.birosoft.liquid.LiquidLookAndFeel");
/*    */     
/* 38 */     UIManager.addPropertyChangeListener(new PropertyChangeListener()
/*    */         {
/*    */           public void propertyChange(PropertyChangeEvent event)
/*    */           {
/* 42 */             Object newLF = event.getNewValue();
/*    */             
/* 44 */             if (!(newLF instanceof LiquidLookAndFeel)) {
/*    */               try {
/* 46 */                 UIManager.setLookAndFeel(new LiquidLookAndFeel());
/* 47 */               } catch (Exception e) {
/* 48 */                 e.printStackTrace();
/*    */               } 
/*    */             }
/*    */           }
/*    */         });
/*    */ 
/*    */     
/* 55 */     Class mainClass = null;
/*    */     
/* 57 */     if (args[0].toLowerCase().endsWith(".jar")) {
/* 58 */       File file = new File(args[0]);
/* 59 */       JarFile jarFile = new JarFile(file);
/*    */       
/* 61 */       Manifest manifest = jarFile.getManifest();
/* 62 */       String mainClassName = manifest.getMainAttributes().getValue("Main-Class");
/* 63 */       URLClassLoader loader = new URLClassLoader(new URL[] { file.toURL() }, Launcher.class.getClassLoader());
/*    */       
/* 65 */       mainClass = Class.forName(mainClassName, true, loader);
/*    */     } else {
/* 67 */       mainClass = Class.forName(args[0]);
/*    */     } 
/*    */     
/* 70 */     Method m = mainClass.getMethod("main", new Class[] { (array$Ljava$lang$String == null) ? (array$Ljava$lang$String = class$("[Ljava.lang.String;")) : array$Ljava$lang$String });
/*    */     
/* 72 */     String[] copyOfArgs = new String[args.length - 1];
/* 73 */     for (int i = 1; i < args.length; i++) {
/* 74 */       copyOfArgs[i - 1] = args[i];
/*    */     }
/* 76 */     m.invoke(mainClass, new Object[] { copyOfArgs });
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\liquidlnf.jar!\Launcher.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.0.7
 */