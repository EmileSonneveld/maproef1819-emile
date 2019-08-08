/*     */ package lrg.metrics;
/*     */ import lrg.memoria.core.System;
/*     */ import lrg.metrics.classes.AverageInstanceVariables;
/*     */ import lrg.metrics.classes.CouplingBetweenObjects;
/*     */ import lrg.metrics.classes.InstanceVariables;
/*     */ import lrg.metrics.classes.ProtectedAttributes;
/*     */ import lrg.metrics.classes.PublicBaseClasses;
/*     */ import lrg.metrics.classes.PublicInstanceMethods;
/*     */ import lrg.metrics.classes.StaticAttributes;
/*     */ import lrg.metrics.methods.ClassTypeLocalVars;
/*     */ import lrg.metrics.methods.MaximumNumberOfBranches;
/*     */ import lrg.metrics.methods.NumberOfExceptions;
/*     */ import lrg.metrics.methods.OtherClassTypeVariables;
/*     */ 
/*     */ public class JMetrics {
/*     */   public static void main(String[] args) throws Exception {
/*  17 */     JavaModelLoader model = new JavaModelLoader(args[0], null, null);
/*  18 */     System system = model.getSystem();
/*     */     
/*  20 */     CollectionResult.sourcePath = args[0];
/*  21 */     SystemMeasure.m_path = args[1];
/*     */ 
/*     */     
/*  24 */     ClassTypeLocalVars mm_inc = new ClassTypeLocalVars();
/*  25 */     mm_inc.measure(system).saveResults();
/*  26 */     ExteriorCalls mm_ec = new ExteriorCalls();
/*  27 */     mm_ec.measure(system).saveResults();
/*  28 */     ExteriorCallPaths mm_excp = new ExteriorCallPaths();
/*  29 */     mm_excp.measure(system).saveResults();
/*  30 */     InteriorCallPaths mm_incp = new InteriorCallPaths();
/*  31 */     mm_incp.measure(system).saveResults();
/*  32 */     MaximumNumberOfBranches mm_cn = new MaximumNumberOfBranches();
/*  33 */     mm_cn.measure(system).saveResults();
/*  34 */     ClassTypeParameters mm_ctp = new ClassTypeParameters();
/*  35 */     mm_ctp.measure(system).saveResults();
/*  36 */     DeclaredConstants mm_dc = new DeclaredConstants();
/*  37 */     mm_dc.measure(system).saveResults();
/*  38 */     DirectCalls mm_dca = new DirectCalls();
/*  39 */     mm_dca.measure(system).saveResults();
/*  40 */     DistinctLocalAttributesUses mm_dlau = new DistinctLocalAttributesUses();
/*  41 */     mm_dlau.measure(system).saveResults();
/*  42 */     ExternalAttributesUsed mm_eau = new ExternalAttributesUsed();
/*  43 */     mm_eau.measure(system).saveResults();
/*  44 */     InteriorCalls mm_ic = new InteriorCalls();
/*  45 */     mm_ic.measure(system).saveResults();
/*  46 */     LocalAttributesUses mm_lau = new LocalAttributesUses();
/*  47 */     mm_lau.measure(system).saveResults();
/*  48 */     LocalVariables mm_lv = new LocalVariables();
/*  49 */     mm_lv.measure(system).saveResults();
/*  50 */     NumberOfDecisions mm_nd = new NumberOfDecisions();
/*  51 */     mm_nd.measure(system).saveResults();
/*  52 */     NumberOfExceptions mm_nex = new NumberOfExceptions();
/*  53 */     mm_nex.measure(system).saveResults();
/*  54 */     NumberOfLoops mm_nl = new NumberOfLoops();
/*  55 */     mm_nl.measure(system).saveResults();
/*  56 */     NumberOfParameters mm_np = new NumberOfParameters();
/*  57 */     mm_np.measure(system).saveResults();
/*  58 */     NumberOfStaticCalls mm_nsc = new NumberOfStaticCalls();
/*  59 */     mm_nsc.measure(system).saveResults();
/*  60 */     NumberOfUsedParameters mm_nup = new NumberOfUsedParameters();
/*  61 */     mm_nup.measure(system).saveResults();
/*  62 */     OtherClassTypeParameters mm_octp = new OtherClassTypeParameters();
/*  63 */     mm_octp.measure(system).saveResults();
/*  64 */     OtherClassTypeVariables mm_octv = new OtherClassTypeVariables();
/*  65 */     mm_octv.measure(system).saveResults();
/*  66 */     RaisedExceptions mm_re = new RaisedExceptions();
/*  67 */     mm_re.measure(system).saveResults();
/*     */ 
/*     */     
/*  70 */     InstanceMethods cm_im = new InstanceMethods();
/*  71 */     cm_im.measure(system).saveResults();
/*  72 */     PublicInstanceMethods cm_pim = new PublicInstanceMethods();
/*  73 */     cm_pim.measure(system).saveResults();
/*  74 */     ClassTypeAttributes cm_cta = new ClassTypeAttributes();
/*  75 */     cm_cta.measure(system).saveResults();
/*  76 */     ConstantAttributes cm_ca = new ConstantAttributes();
/*  77 */     cm_ca.measure(system).saveResults();
/*  78 */     FinalAttributes cm_fa = new FinalAttributes();
/*  79 */     cm_fa.measure(system).saveResults();
/*  80 */     InstanceVariables cm_iv = new InstanceVariables();
/*  81 */     cm_iv.measure(system).saveResults();
/*  82 */     PackageAttributes cm_packAttr = new PackageAttributes();
/*  83 */     cm_packAttr.measure(system).saveResults();
/*  84 */     PublicAttributes cm_publAttr = new PublicAttributes();
/*  85 */     cm_publAttr.measure(system).saveResults();
/*  86 */     ProtectedAttributes cm_protAttr = new ProtectedAttributes();
/*  87 */     cm_protAttr.measure(system).saveResults();
/*  88 */     PrivateAttributes cm_privAttr = new PrivateAttributes();
/*  89 */     cm_privAttr.measure(system).saveResults();
/*  90 */     StaticAttributes cm_statAttr = new StaticAttributes();
/*  91 */     cm_statAttr.measure(system).saveResults();
/*  92 */     AverageInstanceVariables cm_aiv = new AverageInstanceVariables();
/*  93 */     cm_aiv.measure(system).saveResults();
/*  94 */     ClassMethods cm_clsMet = new ClassMethods();
/*  95 */     cm_clsMet.measure(system).saveResults();
/*  96 */     DepthOfInheritanceTree cm_dit = new DepthOfInheritanceTree();
/*  97 */     cm_dit.measure(system).saveResults();
/*  98 */     MessageSends cm_ms = new MessageSends();
/*  99 */     cm_ms.measure(system).saveResults();
/* 100 */     ClassVariables cm_cv = new ClassVariables();
/* 101 */     cm_cv.measure(system).saveResults();
/* 102 */     CouplingBetweenObjects cm_cbo = new CouplingBetweenObjects();
/* 103 */     cm_cbo.measure(system).saveResults();
/* 104 */     DataAbstractionCoupling cm_dac = new DataAbstractionCoupling();
/* 105 */     cm_dac.measure(system).saveResults();
/* 106 */     DataAbstractionCoupling_2 cm_dac2 = new DataAbstractionCoupling_2();
/* 107 */     cm_dac2.measure(system).saveResults();
/* 108 */     DataMembers cm_dm = new DataMembers();
/* 109 */     cm_dm.measure(system).saveResults();
/* 110 */     ExteriorCallPaths cm_excp = new ExteriorCallPaths();
/* 111 */     cm_excp.measure(system).saveResults();
/* 112 */     InteriorCallPaths cm_incp = new InteriorCallPaths();
/* 113 */     cm_incp.measure(system).saveResults();
/* 114 */     ExtendedClasses cm_ec = new ExtendedClasses();
/* 115 */     cm_ec.measure(system).saveResults();
/* 116 */     IncludedClasses cm_ic = new IncludedClasses();
/* 117 */     cm_ic.measure(system).saveResults();
/* 118 */     NumberOfChildren cm_noc = new NumberOfChildren();
/* 119 */     cm_noc.measure(system).saveResults();
/* 120 */     ClassProperties cm_cp = new ClassProperties();
/* 121 */     cm_cp.measure(system).saveResults();
/* 122 */     NumberOfInterfaces cm_noi = new NumberOfInterfaces();
/* 123 */     cm_noi.measure(system).saveResults();
/* 124 */     PublicBaseClasses cm_pbc = new PublicBaseClasses();
/* 125 */     cm_pbc.measure(system).saveResults();
/* 126 */     ResponseForAClass cm_rfc = new ResponseForAClass();
/* 127 */     cm_rfc.measure(system).saveResults();
/* 128 */     WeightedMethodCount cm_wmc = new WeightedMethodCount();
/* 129 */     cm_wmc.measure(system).saveResults();
/* 130 */     ClassCoupling cm_cc = new ClassCoupling();
/* 131 */     cm_cc.measure(system).saveResults();
/*     */ 
/*     */     
/* 134 */     AbstractClasses pm_ac = new AbstractClasses();
/* 135 */     pm_ac.measure(system).saveResults();
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\metrics.jar!\lrg\metrics\JMetrics.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */