/*     */ package classes.lrg.insider.plugins.tools.memoria.graphgen;
/*     */ 
/*     */ import java.io.FileWriter;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*     */ import lrg.insider.plugins.tools.memoria.graphgen.Component;
/*     */ import lrg.insider.plugins.tools.memoria.graphgen.Composite;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class Composite
/*     */   extends Component
/*     */ {
/*     */   ArrayList theComponents;
/*     */   
/*     */   public Composite(AbstractEntityInterface anEntity, int depth) {
/* 374 */     super(anEntity, depth);
/* 375 */     this.theComponents = new ArrayList();
/*     */   }
/*     */ 
/*     */   
/*     */   public void addComponentAndExportDot(Component aComponent, FileWriter writer, String edgeColor, boolean isEdgeDirectedOutward) {
/* 380 */     this.theComponents.add(aComponent);
/*     */     
/*     */     try {
/* 383 */       if (isEdgeDirectedOutward)
/* 384 */       { writer.write(String.valueOf(this.theEntity.getName()) + " -> " + aComponent.theEntity.getName() + " [color=" + edgeColor + "];\n"); }
/*     */       else
/* 386 */       { writer.write(String.valueOf(aComponent.theEntity.getName()) + " -> " + this.theEntity.getName() + " [color=" + edgeColor + "];\n"); } 
/* 387 */     } catch (IOException e) {
/*     */       
/* 389 */       e.printStackTrace();
/*     */       return;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void dotFormatExport(FileWriter writer, String edgeColor, boolean isEdgeDirectedOutward) {
/* 399 */     Iterator it = this.theComponents.iterator();
/* 400 */     while (it.hasNext()) {
/* 401 */       Component crtComponent = (Component)it.next();
/*     */       
/*     */       try {
/* 404 */         if (isEdgeDirectedOutward)
/* 405 */         { writer.write(String.valueOf(this.theEntity.getName()) + " -> " + crtComponent.theEntity.getName() + " [color=" + edgeColor + "];\n"); }
/*     */         else
/* 407 */         { writer.write(String.valueOf(crtComponent.theEntity.getName()) + " -> " + this.theEntity.getName() + " [color=" + edgeColor + "];\n"); } 
/* 408 */       } catch (IOException e) {
/*     */         
/* 410 */         e.printStackTrace();
/*     */         return;
/*     */       } 
/* 413 */       crtComponent.dotFormatExport(writer, edgeColor, isEdgeDirectedOutward);
/*     */     } 
/*     */   }
/*     */   
/*     */   public String HTMLExport() {
/* 418 */     String tmp = super.HTMLExport();
/* 419 */     Iterator it = this.theComponents.iterator();
/*     */     
/* 421 */     while (it.hasNext()) {
/* 422 */       Component crtComponent = (Component)it.next();
/* 423 */       for (int i = 0; i < this.depth; ) { tmp = String.valueOf(tmp) + "\t"; i++; }
/* 424 */        tmp = String.valueOf(tmp) + crtComponent.HTMLExport();
/*     */     } 
/* 426 */     return tmp;
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\tools\memoria\graphgen\Composite.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */