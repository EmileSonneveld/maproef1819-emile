/*     */ package lrg.common.abstractions.entities;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import lrg.common.abstractions.managers.EntityTypeManager;
/*     */ import lrg.common.abstractions.plugins.filters.FilteringRule;
/*     */ import lrg.common.abstractions.plugins.tools.AbstractGroupTool;
/*     */ import lrg.common.metamodel.MetaModel;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GroupEntity
/*     */   extends AbstractEntity
/*     */ {
/*     */   private ArrayList elements;
/*     */   private String groupName;
/*     */   protected boolean unModifyable = false;
/*     */   
/*  22 */   protected void setLock() { this.unModifyable = true; }
/*     */ 
/*     */   
/*     */   public GroupEntity(String name, EntityType entityType) {
/*  26 */     super(entityType);
/*  27 */     this.elements = new ArrayList();
/*  28 */     this.groupName = name;
/*     */   }
/*     */ 
/*     */   
/*     */   public GroupEntity(String name, ArrayList theEntities) {
/*  33 */     super(EntityTypeManager.getEntityTypeForName("group"));
/*  34 */     this.groupName = name;
/*  35 */     this.elements = theEntities;
/*  36 */     MetaModel aModel = MetaModel.instance();
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/*  41 */     String tmp = new String("[");
/*  42 */     Iterator it = this.elements.iterator();
/*  43 */     while (it.hasNext()) {
/*  44 */       tmp = String.valueOf(tmp) + ((AbstractEntityInterface)it.next()).getName() + " ";
/*     */     }
/*  46 */     return String.valueOf(tmp) + "]";
/*     */   }
/*     */ 
/*     */   
/*  50 */   public String getName() { return this.groupName; }
/*     */ 
/*     */ 
/*     */   
/*  54 */   void setName(String name) { this.groupName = name; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EntityType getEntityTypeOfElements() {
/*  61 */     Iterator it = this.elements.iterator();
/*  62 */     while (it.hasNext()) {
/*  63 */       AbstractEntityInterface tmp = (AbstractEntityInterface)it.next();
/*  64 */       if (tmp == null) {
/*     */         continue;
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  72 */       return tmp.getEntityType();
/*     */     } 
/*     */     
/*  75 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public ResultEntity getProperty(String propertyDescriptor) {
/*  80 */     Iterator it = getElements().iterator();
/*  81 */     ArrayList resultCollection = new ArrayList();
/*     */     
/*  83 */     if (propertyDescriptor.compareTo("Address") == 0) {
/*  84 */       return 
/*  85 */         super.getProperty(propertyDescriptor);
/*     */     }
/*     */     
/*  88 */     while (it.hasNext()) {
/*  89 */       ResultEntity aResult = ((AbstractEntity)it.next()).getProperty(propertyDescriptor);
/*  90 */       if (aResult == null)
/*  91 */         continue;  if (aResult.isCollectionResult()) {
/*  92 */         resultCollection.addAll(aResult.getValueAsCollection()); continue;
/*     */       } 
/*  94 */       resultCollection.add(aResult);
/*     */     } 
/*     */     
/*  97 */     return (resultCollection.size() != 0) ? new ResultEntity(resultCollection) : new ResultEntity(0.0D);
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
/*     */   public GroupEntity getGroup(String groupDescriptor) {
/* 109 */     Iterator it = this.elements.iterator();
/*     */     
/* 111 */     GroupEntity resultGroup = new GroupEntity(String.valueOf(groupDescriptor) + " of (" + getName() + ")", new ArrayList());
/* 112 */     while (it.hasNext()) {
/* 113 */       GroupEntity aGroupEntity = ((AbstractEntityInterface)it.next()).getGroup(groupDescriptor);
/* 114 */       if (aGroupEntity == null)
/* 115 */         continue;  resultGroup.addAll(aGroupEntity);
/*     */     } 
/* 117 */     return resultGroup;
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
/*     */   public GroupEntity applyFilter(FilteringRule theFilter) {
/* 134 */     String nameOfEntityTypeToFilter = theFilter.getDescriptorObject().getEntityTypeName();
/* 135 */     GroupEntity filteredEntities = new GroupEntity(theFilter.createNameForFilteredGroup(this), this.theType);
/*     */     
/* 137 */     Iterator it = this.elements.iterator();
/* 138 */     if (!it.hasNext()) return filteredEntities;
/*     */     
/* 140 */     if (shouldApplyFilterOnSubelemnts(theFilter)) {
/* 141 */       return applyFilterOnSubelements(nameOfEntityTypeToFilter, theFilter);
/*     */     }
/* 143 */     while (it.hasNext()) {
/* 144 */       AbstractEntity crtEntity = (AbstractEntity)it.next();
/* 145 */       if (theFilter.applyFilter(crtEntity)) filteredEntities.add(crtEntity);
/*     */     
/*     */     } 
/* 148 */     return filteredEntities;
/*     */   }
/*     */   
/*     */   private boolean shouldApplyFilterOnSubelemnts(FilteringRule theFilter) {
/* 152 */     String[] nameOfEntityTypeToFilter = theFilter.getDescriptorObject().getAllEntityTypeNames();
/* 153 */     for (int i = 0; i < nameOfEntityTypeToFilter.length; i++) {
/* 154 */       if (getEntityTypeOfElements().getName().compareTo(nameOfEntityTypeToFilter[i]) == 0) return false; 
/*     */     } 
/* 156 */     return true;
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
/*     */   public GroupEntity applyFilter(String filterDescriptor) {
/* 169 */     EntityType elementType = getEntityTypeOfElements();
/* 170 */     if (elementType == null) return this;
/*     */     
/* 172 */     FilteringRule theFilter = (FilteringRule)elementType.findFilteringRule(filterDescriptor);
/* 173 */     if (theFilter == null) return this; 
/* 174 */     return applyFilter(theFilter);
/*     */   }
/*     */   
/*     */   public void runTool(String toolName, Object parameters) {
/* 178 */     EntityType elemType = getEntityTypeOfElements();
/* 179 */     if (elemType == null)
/* 180 */       return;  AbstractGroupTool theTool = elemType.findGroupTool(toolName);
/* 181 */     if (theTool == null)
/* 182 */       return;  theTool.run(this, parameters);
/*     */   }
/*     */   
/*     */   public AbstractEntity belongsTo(String scopeIdentifier) {
/* 186 */     GroupEntity groupOfScopes = new GroupEntity("scopes of " + this.groupName, new ArrayList());
/* 187 */     Iterator it = this.elements.iterator();
/*     */     
/* 189 */     while (it.hasNext()) {
/* 190 */       AbstractEntity crtEntity = (AbstractEntity)it.next();
/* 191 */       if (crtEntity != null) {
/* 192 */         AbstractEntity crtScope = crtEntity.belongsTo(scopeIdentifier);
/* 193 */         if (crtScope != null) groupOfScopes.add(crtScope); 
/*     */       } 
/*     */     } 
/* 196 */     return groupOfScopes;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 201 */   public ArrayList getElements() { return this.elements; }
/*     */ 
/*     */ 
/*     */   
/* 205 */   public AbstractEntityInterface getElementAt(int index) { return (AbstractEntityInterface)this.elements.get(index); }
/*     */ 
/*     */ 
/*     */   
/* 209 */   public int size() { return this.elements.size(); }
/*     */ 
/*     */ 
/*     */   
/* 213 */   public Iterator iterator() { return this.elements.iterator(); }
/*     */ 
/*     */   
/*     */   public void add(AbstractEntityInterface anEntity) {
/* 217 */     if (this.unModifyable) {
/* 218 */       throw new RuntimeException("YOUR ANALYSIS (OR ONE YOU DEPEND ON) CONTAINS AN ERROR - YOU ARE TRYING TO MODIFY A CACHED MODEL GROUP - USE UNION NOT ADD");
/*     */     }
/* 220 */     this.elements.add(anEntity);
/*     */   }
/*     */   
/*     */   public void addAll(GroupEntity aGroup) {
/* 224 */     if (this.unModifyable) {
/* 225 */       throw new RuntimeException("YOUR ANALYSIS (OR ONE YOU DEPEND ON) CONTAINS AN ERROR - YOU ARE TRYING TO MODIFY A CACHED MODEL GROUP - USE UNION NOT ADD");
/*     */     }
/* 227 */     this.elements.addAll(aGroup.elements);
/*     */   }
/*     */   
/*     */   public void addAllDistinct(GroupEntity aGroup) {
/* 231 */     Iterator it = aGroup.getElements().iterator();
/*     */     
/* 233 */     while (it.hasNext()) {
/* 234 */       AbstractEntity crtEntity = (AbstractEntity)it.next();
/* 235 */       if (!isInGroup(crtEntity)) add(crtEntity);
/*     */     
/*     */     } 
/*     */   }
/*     */   
/* 240 */   public boolean isInGroup(AbstractEntityInterface entityToFind) { return (intersect(entityToFind).size() > 0); }
/*     */ 
/*     */   
/*     */   public GroupEntity union(AbstractEntity anElement) {
/* 244 */     ArrayList unionElements = new ArrayList(this.elements);
/*     */     
/* 246 */     unionElements.add(anElement);
/*     */     
/* 248 */     return new GroupEntity(String.valueOf(this.groupName) + " or " + anElement.getName(), unionElements);
/*     */   }
/*     */   
/*     */   public GroupEntity union(GroupEntity otherGroup) {
/* 252 */     ArrayList unionElements = new ArrayList(this.elements);
/*     */     
/* 254 */     unionElements.addAll(otherGroup.elements);
/*     */     
/* 256 */     return new GroupEntity(String.valueOf(this.groupName) + " or " + otherGroup.getName(), unionElements);
/*     */   }
/*     */   
/*     */   public GroupEntity intersect(GroupEntity otherGroup) {
/* 260 */     ArrayList intersectionElements = new ArrayList(this.elements);
/* 261 */     intersectionElements.retainAll(otherGroup.getElements());
/* 262 */     return new GroupEntity(String.valueOf(this.groupName) + " and " + otherGroup.getName(), intersectionElements);
/*     */   }
/*     */   
/*     */   public GroupEntity intersect(AbstractEntityInterface otherEntity) {
/* 266 */     GroupEntity tmp = new GroupEntity(otherEntity.getName(), new ArrayList());
/* 267 */     tmp.add(otherEntity);
/*     */     
/* 269 */     return intersect(tmp);
/*     */   }
/*     */   
/*     */   public GroupEntity exclude(AbstractEntity anEntity) {
/* 273 */     GroupEntity tmp = new GroupEntity(anEntity.getName(), new ArrayList());
/* 274 */     tmp.add(anEntity);
/* 275 */     return exclude(tmp);
/*     */   }
/*     */   
/*     */   public GroupEntity exclude(GroupEntity otherGroup) {
/* 279 */     ArrayList intersectionElements = new ArrayList(this.elements);
/*     */     
/* 281 */     intersectionElements.removeAll(otherGroup.getElements());
/*     */     
/* 283 */     return new GroupEntity(String.valueOf(this.groupName) + " without " + otherGroup.getName(), intersectionElements);
/*     */   }
/*     */   
/*     */   public GroupEntity cartesian(GroupEntity otherGroup) {
/* 287 */     ArrayList cartesianGroup = new ArrayList();
/*     */     
/* 289 */     if (getEntityTypeOfElements() == null || otherGroup.getEntityTypeOfElements() == null) {
/* 290 */       return new GroupEntity("group", cartesianGroup);
/*     */     }
/* 292 */     String entityTypeName = String.valueOf(getEntityTypeOfElements().getName()) + "-" + 
/* 293 */       otherGroup.getEntityTypeOfElements().getName();
/*     */     
/* 295 */     EntityType theEntityType = EntityTypeManager.getEntityTypeForName(entityTypeName);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 301 */     for (Iterator firstIt = this.elements.iterator(); firstIt.hasNext(); ) {
/* 302 */       AbstractEntity firstEntity = (AbstractEntity)firstIt.next();
/* 303 */       for (Iterator secondIt = otherGroup.iterator(); secondIt.hasNext(); ) {
/* 304 */         AbstractEntity secondEntity = (AbstractEntity)secondIt.next();
/* 305 */         GroupEntity currentPair = new GroupEntity("group", theEntityType);
/* 306 */         currentPair.add(firstEntity);
/* 307 */         currentPair.add(secondEntity);
/* 308 */         cartesianGroup.add(currentPair);
/*     */       } 
/*     */     } 
/*     */     
/* 312 */     return new GroupEntity("cartesian of " + getName() + " and " + otherGroup.getName(), cartesianGroup);
/*     */   }
/*     */   
/*     */   public GroupEntity distinct() {
/* 316 */     HashSet distinctElements = new HashSet(this.elements);
/* 317 */     return new GroupEntity("distinct " + this.groupName, new ArrayList(distinctElements));
/*     */   }
/*     */ 
/*     */   
/*     */   private GroupEntity applyFilterOnSubelements(String nameOfEntityTypeToFilter, FilteringRule theFilter) {
/* 322 */     Iterator it = this.elements.iterator();
/*     */ 
/*     */     
/* 325 */     EntityType groupType = EntityTypeManager.getEntityTypeForName(nameOfEntityTypeToFilter);
/*     */     
/* 327 */     GroupEntity filteredEntities = new GroupEntity(theFilter.createNameForFilteredGroup(this), groupType);
/* 328 */     while (it.hasNext()) {
/* 329 */       AbstractEntity crtEntity = (AbstractEntity)it.next();
/* 330 */       GroupEntity subGroup = crtEntity.getGroup(String.valueOf(nameOfEntityTypeToFilter) + " group");
/* 331 */       if (subGroup.applyFilter(theFilter).size() > 0)
/* 332 */         filteredEntities.add(crtEntity); 
/*     */     } 
/* 334 */     return filteredEntities;
/*     */   }
/*     */   
/*     */   public void putAnnotation(String id, Object annotation) {
/* 338 */     Iterator it = this.elements.iterator();
/*     */     
/* 340 */     for (; it.hasNext(); ((AbstractEntity)it.next()).putAnnotation(id, annotation));
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\common.jar!\lrg\common\abstractions\entities\GroupEntity.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */