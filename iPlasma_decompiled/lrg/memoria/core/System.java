/*     */ package lrg.memoria.core;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class System
/*     */   extends NamedModelElement
/*     */ {
/*     */   private ModelElementList<Package> packages;
/*     */   private ModelElementList<Subsystem> subsystems;
/*     */   private ModelElementList<Namespace> namespaces;
/*     */   private ModelElementList<ModelElement> failedDepElements;
/*     */   private ModelElementList<Package> cachedNormalPackagesList;
/*     */   private ModelElementList<Namespace> cachedNormalNamespacesList;
/*     */   private Integer systemId;
/*  22 */   private int loadingLevel = 0;
/*  23 */   public String programmingLanguage = "Java";
/*     */ 
/*     */   
/*  26 */   public void setName(String newName) { this.name = newName; }
/*     */ 
/*     */   
/*     */   protected System(System aSystem) {
/*  30 */     super(aSystem);
/*  31 */     this.packages = aSystem.packages;
/*  32 */     this.subsystems = aSystem.subsystems;
/*  33 */     this.namespaces = aSystem.namespaces;
/*  34 */     this.failedDepElements = aSystem.failedDepElements;
/*  35 */     this.systemId = aSystem.systemId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public System(String name) {
/*  42 */     super(name);
/*  43 */     this.packages = new ModelElementList();
/*  44 */     this.subsystems = new ModelElementList();
/*  45 */     this.namespaces = new ModelElementList();
/*  46 */     this.failedDepElements = new ModelElementList();
/*  47 */     setStatute(1);
/*     */   }
/*     */   
/*     */   public void addSubsystem(Subsystem subsys) {
/*  51 */     if (!this.subsystems.contains(subsys)) {
/*  52 */       this.subsystems.add(subsys);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addPackage(Package pack) {
/*  61 */     if (!this.packages.contains(pack)) {
/*  62 */       this.packages.add(pack);
/*  63 */       pack.setSystem(this);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addNamespace(Namespace namespace) {
/*  71 */     if (!this.namespaces.contains(namespace)) {
/*  72 */       this.namespaces.add(namespace);
/*  73 */       namespace.setSystem(this);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  81 */   public void addFailedDepElement(ModelElement elem) { this.failedDepElements.add(elem); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  88 */   public ModelElementList<ModelElement> getFailedDepElementList() { return this.failedDepElements; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  95 */   public void setFailedDepElementList(ModelElementList<ModelElement> fdl) { this.failedDepElements = fdl; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 100 */   public ModelElementList<Subsystem> getSubsystems() { return this.subsystems; }
/*     */ 
/*     */ 
/*     */   
/* 104 */   public ModelElementList<Package> getPackages() { return this.packages; }
/*     */ 
/*     */   
/*     */   public ModelElementList<Package> getNormalPackages() {
/* 108 */     if (this.cachedNormalPackagesList == null) {
/* 109 */       this.cachedNormalPackagesList = new ModelElementList();
/* 110 */       for (Package current : this.packages) {
/* 111 */         if (current.getStatute() == 1)
/* 112 */           this.cachedNormalPackagesList.add(current); 
/*     */       } 
/*     */     } 
/* 115 */     return this.cachedNormalPackagesList;
/*     */   }
/*     */ 
/*     */   
/* 119 */   public ModelElementList<Namespace> getNamespaces() { return this.namespaces; }
/*     */ 
/*     */   
/*     */   public ModelElementList<Namespace> getNormalNamespaces() {
/* 123 */     if (this.cachedNormalNamespacesList == null) {
/* 124 */       this.cachedNormalNamespacesList = new ModelElementList();
/* 125 */       for (Namespace current : this.namespaces) {
/* 126 */         if (current.getStatute() == 1)
/* 127 */           this.cachedNormalNamespacesList.add(current); 
/*     */       } 
/*     */     } 
/* 130 */     return this.cachedNormalNamespacesList;
/*     */   }
/*     */   
/*     */   public ModelElementList<UnnamedNamespace> getUnnamedNamespaces() {
/* 134 */     ModelElementList<UnnamedNamespace> unspl = new ModelElementList<UnnamedNamespace>();
/* 135 */     for (Namespace current : this.namespaces) {
/* 136 */       if (current instanceof UnnamedNamespace)
/* 137 */         unspl.add((UnnamedNamespace)current); 
/*     */     } 
/* 139 */     return unspl;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 147 */   public Integer getSystemId() { return this.systemId; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 155 */   public void setSystemId(Integer systemId) { this.systemId = systemId; }
/*     */ 
/*     */ 
/*     */   
/* 159 */   public void setLoadingLevel(int level) { this.loadingLevel = level; }
/*     */ 
/*     */ 
/*     */   
/* 163 */   public int getLoadingLevel() { return this.loadingLevel; }
/*     */ 
/*     */ 
/*     */   
/* 167 */   public void accept(ModelVisitor v) { v.visitSystem(this); }
/*     */ 
/*     */   
/*     */   public boolean restore() {
/* 171 */     if (super.restore()) {
/* 172 */       this.packages.restore();
/* 173 */       this.namespaces.restore();
/* 174 */       this.failedDepElements.restore();
/* 175 */       return true;
/*     */     } 
/* 177 */     return false;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 181 */     StringBuffer st = new StringBuffer("System:\n");
/* 182 */     for (Namespace current : this.namespaces)
/* 183 */       st.append(current); 
/* 184 */     return new String(st);
/*     */   }
/*     */ 
/*     */   
/* 188 */   public static void unloadSystemFromMemory(System currentSystem) { ModelElementsRepository.deleteModelElementsRepository(currentSystem.getSystemId()); }
/*     */ 
/*     */   
/*     */   public static void serializeToFile(File serialized, System system) {
/*     */     try {
/* 193 */       if (serialized.getParentFile() != null)
/* 194 */         serialized.getParentFile().mkdirs(); 
/* 195 */       serialized.createNewFile();
/* 196 */       ObjectOutputStream serout = new ObjectOutputStream(new FileOutputStream(serialized));
/* 197 */       ModelElementsRepository.setCurrentModelElementsRepository(system.getSystemId());
/* 198 */       serout.writeObject(ModelElementsRepository.getCurrentModelElementsRepository());
/* 199 */       serout.close();
/* 200 */     } catch (IOException e) {
/* 201 */       java.lang.System.err.println("ERROR: Unable to create the cache !");
/* 202 */       e.printStackTrace();
/* 203 */     } catch (OutOfMemoryError e) {
/* 204 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static System loadFromFile(File serialized) {
/*     */     try {
/* 211 */       ObjectInputStream serin = new ObjectInputStream(new FileInputStream(serialized));
/* 212 */       ModelElementsRepository mer = (ModelElementsRepository)serin.readObject();
/* 213 */       int id = ModelElementsRepository.addNewModelElementsRepository(mer);
/* 214 */       serin.close();
/* 215 */       System currentSystem = (System)ModelElementsRepository.getCurrentModelElementsRepository().byElementID(new Long(0L));
/* 216 */       currentSystem.setSystemId(new Integer(id));
/* 217 */       currentSystem.restore();
/* 218 */       return currentSystem;
/* 219 */     } catch (IOException e) {
/* 220 */       java.lang.System.err.println("ERROR: Unable to load from cache !");
/* 221 */       e.printStackTrace();
/* 222 */     } catch (ClassNotFoundException e) {
/* 223 */       java.lang.System.err.println("ERROR: Unable to load from cache !");
/* 224 */       e.printStackTrace();
/*     */     } 
/* 226 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\core\System.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */