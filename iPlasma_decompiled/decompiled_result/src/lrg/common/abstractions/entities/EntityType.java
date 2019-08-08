/*     */ package lrg.common.abstractions.entities;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.Set;
/*     */ import lrg.common.abstractions.managers.EntityTypeManager;
/*     */ import lrg.common.abstractions.plugins.AbstractPlugin;
/*     */ import lrg.common.abstractions.plugins.details.AbstractDetail;
/*     */ import lrg.common.abstractions.plugins.filters.FilteringRule;
/*     */ import lrg.common.abstractions.plugins.groups.GroupBuilder;
/*     */ import lrg.common.abstractions.plugins.operators.FilteringOperator;
/*     */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*     */ import lrg.common.abstractions.plugins.tools.AbstractEntityTool;
/*     */ import lrg.common.abstractions.plugins.tools.AbstractGroupTool;
/*     */ import lrg.common.abstractions.plugins.visualization.AbstractVisualization;
/*     */ 
/*     */ public class EntityType
/*     */   implements Serializable
/*     */ {
/*     */   private String name;
/*     */   private String supertypeName;
/*     */   private HashMap propertyComputerDictionary;
/*     */   private HashMap groupBuilderDictionary;
/*     */   private HashMap filteringRuleDictionary;
/*     */   private HashMap filteringOperatorDictionary;
/*     */   private HashMap toolsDictionary;
/*     */   private HashMap visualizationsDictionary;
/*     */   private HashMap detailsDictionary;
/*     */   
/*     */   public EntityType(String name, String supertypeName) {
/*  35 */     this.name = name;
/*  36 */     this.supertypeName = supertypeName;
/*     */     
/*  38 */     this.groupBuilderDictionary = new HashMap();
/*  39 */     this.propertyComputerDictionary = new HashMap();
/*  40 */     this.filteringRuleDictionary = new HashMap();
/*  41 */     this.filteringOperatorDictionary = new HashMap();
/*  42 */     this.toolsDictionary = new HashMap();
/*  43 */     this.visualizationsDictionary = new HashMap();
/*  44 */     this.detailsDictionary = new HashMap();
/*     */   }
/*     */ 
/*     */   
/*  48 */   public EntityType(String name) { this(name, ""); }
/*     */ 
/*     */ 
/*     */   
/*  52 */   public String getName() { return this.name; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  57 */   public String getSupertypeName() { return this.supertypeName; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void attach(AbstractPlugin someCommand) {
/*  63 */     if (someCommand instanceof GroupBuilder || someCommand instanceof lrg.common.abstractions.plugins.groups.GroupEntityBuilder) {
/*  64 */       this.groupBuilderDictionary.put(someCommand.getDescriptorObject().getName(), someCommand);
/*  65 */     } else if (someCommand instanceof PropertyComputer) {
/*  66 */       this.propertyComputerDictionary.put(someCommand.getDescriptorObject().getName(), someCommand);
/*  67 */       if (someCommand instanceof FilteringRule)
/*  68 */         this.filteringRuleDictionary.put(someCommand.getDescriptorObject().getName(), someCommand); 
/*  69 */     } else if (someCommand instanceof AbstractGroupTool || someCommand instanceof AbstractEntityTool) {
/*  70 */       this.toolsDictionary.put(someCommand.getDescriptorObject().getName(), someCommand);
/*  71 */     } else if (someCommand instanceof lrg.common.abstractions.plugins.operators.AggregationOperator) {
/*  72 */       this.filteringRuleDictionary.put(someCommand.getDescriptorObject().getName(), someCommand);
/*  73 */     } else if (someCommand instanceof FilteringOperator) {
/*  74 */       this.filteringOperatorDictionary.put(someCommand.getDescriptorObject().getName(), someCommand);
/*  75 */     } else if (someCommand instanceof AbstractDetail) {
/*  76 */       this.detailsDictionary.put(someCommand.getDescriptorObject().getName(), someCommand);
/*  77 */     } else if (someCommand instanceof AbstractVisualization) {
/*  78 */       this.visualizationsDictionary.put(someCommand.getDescriptorObject().getName(), someCommand);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  84 */   public void unAttach(FilteringRule aRule) { this.filteringRuleDictionary.remove(aRule.getDescriptorObject().getName()); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 105 */   public AbstractPlugin findFilteringRule(String filterDescriptor) { return (AbstractPlugin)this.filteringRuleDictionary.get(filterDescriptor); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 110 */   public AbstractEntityTool findEntityTool(String toolDescriptor) { return (AbstractEntityTool)this.toolsDictionary.get(toolDescriptor); }
/*     */ 
/*     */ 
/*     */   
/* 114 */   public AbstractVisualization findVisualizations(String descriptor) { return (AbstractVisualization)this.visualizationsDictionary.get(descriptor); }
/*     */ 
/*     */ 
/*     */   
/* 118 */   public AbstractDetail findDetails(String descriptor) { return (AbstractDetail)this.detailsDictionary.get(descriptor); }
/*     */ 
/*     */ 
/*     */   
/* 122 */   public AbstractGroupTool findGroupTool(String toolDescriptor) { return (AbstractGroupTool)this.toolsDictionary.get(toolDescriptor); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ResultEntity computeProperty(String propertyDescriptor, AbstractEntityInterface anEntity) {
/* 138 */     PropertyComputer aPropertyComputer = (PropertyComputer)this.propertyComputerDictionary.get(propertyDescriptor);
/* 139 */     if (aPropertyComputer == null) return null;
/*     */     
/* 141 */     return aPropertyComputer.compute(anEntity);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 146 */   public FilteringOperator findFilteringOperator(String filteringOperator) { return (FilteringOperator)this.filteringOperatorDictionary.get(filteringOperator); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 151 */   public PropertyComputer findPropertyComputer(String propertyComputerName) { return (PropertyComputer)this.propertyComputerDictionary.get(propertyComputerName); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GroupEntity buildGroup(String groupDescriptor, AbstractEntityInterface anEntity) {
/* 165 */     GroupBuilder aGroupBuilder = (GroupBuilder)this.groupBuilderDictionary.get(groupDescriptor);
/* 166 */     if (aGroupBuilder == null) return null;
/*     */     
/* 168 */     ArrayList resultGroup = aGroupBuilder.buildGroup(anEntity);
/* 169 */     if (resultGroup == null) return null; 
/* 170 */     return new GroupEntity(groupDescriptor, resultGroup);
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
/*     */   public ArrayList nameAllGroupBuilders() {
/* 182 */     Set namesOfAllGroupBuilders = new HashSet();
/* 183 */     namesOfAllGroupBuilders.addAll(this.groupBuilderDictionary.keySet());
/*     */     
/* 185 */     Iterator it = EntityTypeManager.getAllSubtypesForName(this.name).iterator();
/*     */     
/* 187 */     while (it.hasNext()) {
/* 188 */       EntityType crt = (EntityType)it.next();
/* 189 */       namesOfAllGroupBuilders.addAll(crt.nameAllGroupBuilders());
/*     */     } 
/* 191 */     return sortKeys(namesOfAllGroupBuilders);
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
/* 203 */   public ArrayList nameAllFilteringRules() { return sortKeys(this.filteringRuleDictionary.keySet()); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 208 */   public ArrayList nameAllTools() { return sortKeys(this.toolsDictionary.keySet()); }
/*     */ 
/*     */ 
/*     */   
/* 212 */   public ArrayList nameAllVisualizations() { return sortKeys(this.visualizationsDictionary.keySet()); }
/*     */ 
/*     */ 
/*     */   
/* 216 */   public ArrayList nameAllDetails() { return sortKeys(this.detailsDictionary.keySet()); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 226 */   public ArrayList nameAllPropertyComputers() { return sortKeys(this.propertyComputerDictionary.keySet()); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 231 */   public ArrayList nameAllFilteringOperators() { return sortKeys(this.filteringOperatorDictionary.keySet()); }
/*     */ 
/*     */ 
/*     */   
/*     */   private ArrayList sortKeys(Set setToSort) {
/* 236 */     ArrayList list = new ArrayList(setToSort);
/* 237 */     Collections.sort(list);
/* 238 */     return list;
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\common.jar!\lrg\common\abstractions\entities\EntityType.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */