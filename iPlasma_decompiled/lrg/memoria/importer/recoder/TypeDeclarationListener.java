/*     */ package lrg.memoria.importer.recoder;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Hashtable;
/*     */ import java.util.Stack;
/*     */ import lrg.memoria.core.Class;
/*     */ import lrg.memoria.core.CodeStripe;
/*     */ import lrg.memoria.core.DataAbstraction;
/*     */ import lrg.memoria.core.InheritanceRelation;
/*     */ import lrg.memoria.core.Location;
/*     */ import lrg.memoria.core.ModelElementList;
/*     */ import recoder.java.ProgramElement;
/*     */ import recoder.java.declaration.TypeDeclaration;
/*     */ import recoder.java.declaration.TypeDeclarationContainer;
/*     */ 
/*     */ public abstract class TypeDeclarationListener implements Listener {
/*  16 */   private Stack<Class> containingClassesStack = new Stack();
/*  17 */   private Stack<CodeStripe> oldStripes = new Stack();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void enterModelComponent(ProgramElement pe) {
/*  23 */     ModelRepository mr = DefaultModelRepository.getModelRepository(null);
/*  24 */     this.oldStripes.push(mr.getCurrentStripe());
/*  25 */     mr.setCurrentStripe(null);
/*     */   }
/*     */   protected DataAbstraction buildClassFromDeclaration(TypeDeclaration td) {
/*     */     Class mmmc;
/*  29 */     DefaultModelRepository mr = DefaultModelRepository.getModelRepository(null);
/*     */     
/*  31 */     Class containingClass = mr.getCurrentClass();
/*  32 */     this.containingClassesStack.push(containingClass);
/*  33 */     TypeDeclarationContainer typeDeclarationContainer = td.getParent();
/*  34 */     if (typeDeclarationContainer instanceof New) {
/*  35 */       TypeReference tr = ((New)typeDeclarationContainer).getTypeReference();
/*  36 */       String name = String.valueOf(getNextAnonymousClass(containingClass.getContainedClasses()));
/*  37 */       mmmc = mr.addClass(td, name);
/*  38 */       Class ancestor = ReferenceConverter.getClassType(tr);
/*  39 */       mmmc.addAncestor(ancestor);
/*  40 */       ancestor.addDescendant(mmmc);
/*  41 */       InheritanceRelation rel = new InheritanceRelation(mmmc, ancestor, (byte)0);
/*  42 */       mmmc.addRelationAsDescendent(rel);
/*  43 */       ancestor.addRelationAsAncestor(rel);
/*     */     } else {
/*  45 */       StringBuffer name = new StringBuffer();
/*  46 */       name.append(td.getName());
/*  47 */       mmmc = mr.addClass(td, name.toString());
/*     */     } 
/*  49 */     Location loc = new Location(mr.getCurrentFile());
/*  50 */     loc.setStartLine(td.getFirstElement().getStartPosition().getLine());
/*  51 */     loc.setStartChar(td.getFirstElement().getStartPosition().getColumn());
/*  52 */     loc.setEndLine(td.getLastElement().getEndPosition().getLine());
/*  53 */     loc.setEndChar(td.getLastElement().getEndPosition().getColumn());
/*     */     
/*  55 */     if (mr.getCurrentStripe() != null)
/*  56 */     { mmmc.setLocation(mr.getCurrentStripe().getRelPosOf(loc)); }
/*  57 */     else { mmmc.setLocation(loc); }
/*     */ 
/*     */     
/*  60 */     if (td.isFinal())
/*  61 */       mmmc.setFinal(); 
/*  62 */     if (td.isAbstract())
/*  63 */       mmmc.setAbstract(); 
/*  64 */     if (td.isStatic())
/*  65 */       mmmc.setStatic(); 
/*  66 */     if (td.isInterface())
/*  67 */       mmmc.setInterface(); 
/*  68 */     mmmc.setAccessMode(RecoderToMemojConverter.convertAccessMode(td));
/*  69 */     mmmc.setStatute(1);
/*     */     
/*  71 */     if (mr.getCurrentStripe() != null) {
/*  72 */       mmmc.setScope(mr.getCurrentStripe());
/*  73 */       mr.getCurrentStripe().addScopedElement(mmmc);
/*  74 */     } else if (containingClass != null) {
/*  75 */       mmmc.setScope(containingClass);
/*  76 */       containingClass.addScopedElement(mmmc);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  83 */     mr.setCurrentClass(mmmc);
/*  84 */     return mmmc;
/*     */   }
/*     */   
/*     */   public void leaveModelComponent(ProgramElement pe) {
/*  88 */     ModelRepository mr = DefaultModelRepository.getModelRepository(null);
/*  89 */     Class clazz = mr.getCurrentClass();
/*  90 */     ModelElementList modelElementList = clazz.getContainedClasses();
/*  91 */     Hashtable names = new Hashtable();
/*  92 */     for (int i = 0; i < modelElementList.size(); i++) {
/*  93 */       Class curClass = (Class)modelElementList.get(i);
/*  94 */       String name = curClass.getName();
/*  95 */       Integer index = (Integer)names.get(name);
/*  96 */       if (index == null) {
/*  97 */         names.put(name, new Integer(0));
/*  98 */       } else if (index.equals(new Integer(0))) {
/*  99 */         for (int j = 0; j < modelElementList.size(); j++) {
/* 100 */           Class cls = (Class)modelElementList.get(j);
/* 101 */           if (cls.getName().equals(name)) {
/* 102 */             cls.setName(String.valueOf(1) + name);
/*     */             break;
/*     */           } 
/*     */         } 
/* 106 */         curClass.setName(String.valueOf(2) + name);
/* 107 */         names.put(name, new Integer(2));
/*     */       } else {
/* 109 */         int newIndex = index.intValue() + 1;
/* 110 */         names.put(name, new Integer(newIndex));
/* 111 */         curClass.setName(String.valueOf(newIndex) + name);
/*     */       } 
/*     */     } 
/* 114 */     mr.setCurrentClass((Class)this.containingClassesStack.pop());
/* 115 */     mr.setCurrentStripe((CodeStripe)this.oldStripes.pop());
/*     */   }
/*     */   
/*     */   private int getNextAnonymousClass(ArrayList containedClasses) {
/* 119 */     int num = 1;
/*     */     
/* 121 */     for (int i = 0; i < containedClasses.size(); i++) {
/* 122 */       DataAbstraction currentClass = (DataAbstraction)containedClasses.get(i);
/* 123 */       String name = currentClass.getName();
/*     */       try {
/* 125 */         Integer.parseInt(name);
/* 126 */         num++;
/* 127 */       } catch (NumberFormatException numberFormatException) {}
/*     */     } 
/*     */     
/* 130 */     return num;
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\importer\recoder\TypeDeclarationListener.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */