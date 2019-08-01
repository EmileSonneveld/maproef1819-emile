/*     */ package classes.lrg.insider.plugins.visualizations;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import lrg.common.abstractions.entities.AbstractEntity;
/*     */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*     */ import lrg.common.abstractions.entities.GroupEntity;
/*     */ import lrg.insider.plugins.visualizations.MethodInteraction;
/*     */ import lrg.insider.plugins.visualizations.comparators.AttributeNMAVComparator;
/*     */ import lrg.insider.plugins.visualizations.comparators.MethodLOCComparator;
/*     */ import lrg.insider.util.Visualization;
/*     */ import lrg.jMondrian.commands.AbstractFigureDescriptionCommand;
/*     */ import lrg.jMondrian.figures.Figure;
/*     */ import lrg.jMondrian.layouts.FlowLayout;
/*     */ import lrg.jMondrian.painters.RectangleNodePainter;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class null
/*     */   extends AbstractFigureDescriptionCommand
/*     */ {
/*     */   public Figure describe() {
/*  98 */     Figure methodFig = new Figure();
/*  99 */     AbstractEntityInterface aClass = (AbstractEntityInterface)this.receiver;
/* 100 */     GroupEntity interestingElements = new GroupEntity("group", new ArrayList());
/* 101 */     GroupEntity methods = aClass.getGroup("method group");
/* 102 */     GroupEntity usedMethods = methods.exclude((AbstractEntity)theMethod).intersect(allInvolvedMethods);
/* 103 */     interestingElements.addAll(usedMethods);
/* 104 */     Collections.sort(interestingElements.getElements(), new MethodLOCComparator());
/* 105 */     GroupEntity usedAttributes = aClass.getGroup("attribute group").intersect(allInvolvedMethods);
/* 106 */     Collections.sort(usedAttributes.getElements(), new AttributeNMAVComparator());
/* 107 */     interestingElements.addAll(usedAttributes);
/* 108 */     if (methods.intersect(theMethod).size() > 0) interestingElements.add(theMethod); 
/* 109 */     methodFig.nodesUsing(interestingElements.getElements(), (
/* 110 */         new RectangleNodePainter(false))
/* 111 */         .name(Visualization.stringCommand("Name"))
/* 112 */         .width(new Object(this, theMethod))
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 126 */         .height(new Object(this, theMethod))
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 139 */         .color(new Object(this, theMethod, theyCallMe, overridenMethodCalls, iCallThem)));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 156 */     methodFig.layout(new FlowLayout(5.0D, 5.0D, 300.0D));
/* 157 */     return methodFig;
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\visualizations\MethodInteraction$1$1$2.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */