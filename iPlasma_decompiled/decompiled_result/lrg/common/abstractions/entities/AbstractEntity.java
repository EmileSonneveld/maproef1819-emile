/*     */ package lrg.common.abstractions.entities;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Hashtable;
/*     */ import java.util.Iterator;
/*     */ import lrg.common.abstractions.managers.EntityTypeManager;
/*     */ import lrg.common.abstractions.plugins.tools.AbstractEntityTool;
/*     */ 
/*     */ public class AbstractEntity implements AbstractEntityInterface {
/*     */   protected EntityType theType;
/*     */   protected HashMap groupDictionary;
/*     */   protected HashMap propertyDictionary;
/*     */   private Hashtable annotationsMap;
/*     */   
/*     */   public AbstractEntity(EntityType theEntityType) {
/*  17 */     this.annotationsMap = new Hashtable();
/*     */ 
/*     */     
/*  20 */     this.theType = theEntityType;
/*     */   }
/*     */ 
/*     */   
/*  24 */   public AbstractEntity() { this(EntityTypeManager.getEntityTypeForName("abstract entity")); }
/*     */ 
/*     */ 
/*     */   
/*  28 */   public void setEntityType(EntityType entityType) { this.theType = entityType; }
/*     */ 
/*     */ 
/*     */   
/*  32 */   public EntityType getEntityType() { return this.theType; }
/*     */ 
/*     */ 
/*     */   
/*  36 */   public String getName() { return "NONAME(AbstractEntity)"; }
/*     */ 
/*     */ 
/*     */   
/*  40 */   public String toString() { return getName(); }
/*     */ 
/*     */   
/*     */   public void removeProperty(String aDescriptor) {
/*  44 */     if (this.propertyDictionary == null)
/*  45 */       return;  this.propertyDictionary.remove(aDescriptor);
/*     */   }
/*     */   
/*     */   public void addProperty(String aDescriptor, ResultEntity aPropertyResult) {
/*  49 */     if (this.propertyDictionary == null)
/*  50 */       this.propertyDictionary = new HashMap(); 
/*  51 */     this.propertyDictionary.put(aDescriptor, aPropertyResult);
/*     */   }
/*     */   
/*     */   public void addGroup(String aDescriptor, GroupEntity aPropertyResult) {
/*  55 */     if (this.groupDictionary == null)
/*  56 */       this.groupDictionary = new HashMap(); 
/*  57 */     this.groupDictionary.put(aDescriptor, aPropertyResult);
/*  58 */     aPropertyResult.setLock();
/*     */   }
/*     */   
/*     */   public ResultEntity getProperty(String propertyDescriptor) {
/*  62 */     if (this.propertyDictionary == null) this.propertyDictionary = new HashMap();
/*     */     
/*  64 */     ResultEntity computedProperty = (ResultEntity)this.propertyDictionary.get(propertyDescriptor);
/*  65 */     if (computedProperty != null) return computedProperty;
/*     */     
/*  67 */     if (this.theType == null) return null; 
/*  68 */     computedProperty = this.theType.computeProperty(propertyDescriptor, this);
/*     */ 
/*     */ 
/*     */     
/*  72 */     if (computedProperty == null) {
/*  73 */       computedProperty = getAggregatedProperty(propertyDescriptor);
/*     */     }
/*     */     
/*  76 */     if (computedProperty != null) {
/*  77 */       this.propertyDictionary.put(propertyDescriptor, computedProperty);
/*     */     }
/*     */     else {
/*     */       
/*  81 */       computedProperty = getPropertyGroupFromSubparts(propertyDescriptor);
/*     */     } 
/*  83 */     return computedProperty;
/*     */   }
/*     */ 
/*     */   
/*     */   private ResultEntity getPropertyGroupFromSubparts(String propertyDescriptor) {
/*  88 */     ArrayList resultCollection = new ArrayList();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  93 */     Iterator it = EntityTypeManager.getAllSubtypesForName(this.theType.getName()).iterator();
/*  94 */     if (!it.hasNext()) return null;
/*     */     
/*  96 */     while (it.hasNext()) {
/*  97 */       EntityType subType = (EntityType)it.next();
/*     */       
/*  99 */       GroupEntity subTypeGroup = this.theType.buildGroup(String.valueOf(subType.getName()) + " group", this);
/*     */       
/* 101 */       if (subTypeGroup == null)
/* 102 */         continue;  ResultEntity aResult = subTypeGroup.getProperty(propertyDescriptor);
/* 103 */       if (aResult != null) {
/* 104 */         ArrayList tmp = aResult.getValueAsCollection();
/* 105 */         if (tmp != null) {
/* 106 */           resultCollection.addAll(tmp); continue;
/*     */         } 
/* 108 */         resultCollection.add(aResult.getValue());
/*     */       } 
/*     */     } 
/* 111 */     return (resultCollection.size() != 0) ? new ResultEntity(resultCollection) : null;
/*     */   }
/*     */   
/*     */   private ResultEntity getAggregatedProperty(String propertyDescriptor) {
/* 115 */     String[] parts = propertyDescriptor.split("_");
/*     */     
/* 117 */     if (parts.length < 2) return null;
/*     */     
/* 119 */     ResultEntity groupOfResults = getPropertyGroupFromSubparts(parts[1]);
/* 120 */     if (groupOfResults == null) return null;
/*     */     
/* 122 */     return groupOfResults.aggregate(parts[0]);
/*     */   }
/*     */   
/*     */   public GroupEntity getGroup(String groupIdentifier) {
/* 126 */     if (this.groupDictionary == null) this.groupDictionary = new HashMap();
/*     */     
/* 128 */     GroupEntity aGroup = (GroupEntity)this.groupDictionary.get(groupIdentifier);
/*     */     
/* 130 */     if (aGroup != null) return aGroup;
/*     */     
/* 132 */     if (this.theType == null) return new GroupEntity(groupIdentifier, new ArrayList());
/*     */     
/* 134 */     aGroup = this.theType.buildGroup(groupIdentifier, this);
/*     */     
/* 136 */     if (aGroup == null) {
/* 137 */       Iterator it = EntityTypeManager.getAllSubtypesForName(this.theType.getName()).iterator();
/*     */ 
/*     */       
/* 140 */       while (it.hasNext()) {
/* 141 */         EntityType subType = (EntityType)it.next();
/*     */         
/* 143 */         GroupEntity subTypeGroup = this.theType.buildGroup(String.valueOf(subType.getName()) + " group", this);
/*     */         
/* 145 */         if (subTypeGroup != null) {
/* 146 */           GroupEntity groupFromSubtype = subTypeGroup.getGroup(groupIdentifier);
/* 147 */           if (groupFromSubtype != null && groupFromSubtype.size() > 0) {
/* 148 */             if (aGroup == null) {
/* 149 */               aGroup = groupFromSubtype; continue;
/*     */             } 
/* 151 */             aGroup.addAll(groupFromSubtype);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 157 */     if (aGroup != null) {
/* 158 */       aGroup.setName(composeGroupName(groupIdentifier));
/* 159 */       this.groupDictionary.put(groupIdentifier, aGroup);
/* 160 */       aGroup.setLock();
/* 161 */       return aGroup;
/*     */     } 
/*     */     
/* 164 */     return new GroupEntity(groupIdentifier, new ArrayList());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 208 */   public GroupEntity uses(String groupIdentifier) { return getGroup(groupIdentifier); }
/*     */ 
/*     */ 
/*     */   
/* 212 */   public GroupEntity isUsed(String groupIdentifier) { return getGroup(groupIdentifier); }
/*     */ 
/*     */ 
/*     */   
/* 216 */   public GroupEntity contains(String groupIdentifier) { return getGroup(groupIdentifier); }
/*     */ 
/*     */   
/*     */   public AbstractEntity belongsTo(String scopeIdentifier) {
/* 220 */     AbstractEntity currentEntity = this;
/*     */     
/*     */     while (true) {
/* 223 */       AbstractEntity theScope = currentEntity.getProperty("scope");
/* 224 */       if (theScope == null) return null; 
/* 225 */       Object anObject = ((ResultEntity)theScope).getValue();
/* 226 */       if (!(anObject instanceof AbstractEntity)) return null; 
/* 227 */       theScope = (AbstractEntity)anObject;
/* 228 */       if (theScope == null) return null; 
/* 229 */       if (theScope.getEntityType().getName().compareTo(scopeIdentifier) == 0) return theScope; 
/* 230 */       currentEntity = theScope;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/* 235 */   public AbstractEntityTool getTool(String toolIdentifier) { return getEntityType().findEntityTool(toolIdentifier); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 242 */   public void putAnnotation(String addnotationName, Object addnotation) { this.annotationsMap.put(addnotationName, addnotation); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 249 */   public Object getAnnotation(String addnotationName) { return this.annotationsMap.get(addnotationName); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 256 */   public Object removeAnnotation(String annotationName) { return this.annotationsMap.remove(annotationName); }
/*     */ 
/*     */   
/*     */   public String annotationsToString() {
/* 260 */     String result = "";
/* 261 */     Iterator it = this.annotationsMap.keySet().iterator();
/* 262 */     for (; it.hasNext(); result = String.valueOf(result) + it.next() + " ");
/*     */     
/* 264 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 269 */   private String composeGroupName(String groupIdentifier) { return String.valueOf(groupIdentifier) + " of " + getEntityType().getName() + " " + getName(); }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\common.jar!\lrg\common\abstractions\entities\AbstractEntity.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */