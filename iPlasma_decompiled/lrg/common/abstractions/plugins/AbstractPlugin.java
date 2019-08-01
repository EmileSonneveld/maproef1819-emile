/*    */ package lrg.common.abstractions.plugins;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ 
/*    */ public class AbstractPlugin
/*    */   implements Serializable
/*    */ {
/*    */   private Descriptor theDescriptor;
/*    */   
/* 11 */   public AbstractPlugin(Descriptor aDescriptor) { this.theDescriptor = aDescriptor; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 16 */   public AbstractPlugin(String name, String description, String entity) { this(new Descriptor(name, description, entity)); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 21 */   public AbstractPlugin(String name) { this(new Descriptor(name, "", "")); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 26 */   public Descriptor getDescriptorObject() { return this.theDescriptor; }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\common.jar!\lrg\common\abstractions\plugins\AbstractPlugin.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */