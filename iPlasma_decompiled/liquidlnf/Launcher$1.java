/*    */ import com.birosoft.liquid.LiquidLookAndFeel;
/*    */ import java.beans.PropertyChangeEvent;
/*    */ import java.beans.PropertyChangeListener;
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
/*    */ class null
/*    */   implements PropertyChangeListener
/*    */ {
/*    */   public void propertyChange(PropertyChangeEvent event) {
/* 42 */     Object newLF = event.getNewValue();
/*    */     
/* 44 */     if (!(newLF instanceof LiquidLookAndFeel))
/*    */       try {
/* 46 */         UIManager.setLookAndFeel(new LiquidLookAndFeel());
/* 47 */       } catch (Exception e) {
/* 48 */         e.printStackTrace();
/*    */       }  
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\liquidlnf.jar!\Launcher$1.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.0.7
 */