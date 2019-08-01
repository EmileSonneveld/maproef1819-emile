/*    */ package lrg.memoria.importer.recoder.recoder;
/*    */ 
/*    */ import lrg.memoria.importer.recoder.recoder.io.MemoriaDefaultSourceFileRepository;
/*    */ import recoder.CrossReferenceServiceConfiguration;
/*    */ import recoder.io.SourceFileRepository;
/*    */ import recoder.service.SourceInfo;
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
/*    */ public class MeMoJCCrossReferenceServiceConfiguration
/*    */   extends CrossReferenceServiceConfiguration
/*    */ {
/* 22 */   protected SourceInfo makeSourceInfo() { return new MeMoJCDefaultCrossReferenceSourceInfo(this); }
/*    */ 
/*    */ 
/*    */   
/* 26 */   protected SourceFileRepository makeSourceFileRepository() { return new MemoriaDefaultSourceFileRepository(this); }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\importer\recoder\recoder\MeMoJCCrossReferenceServiceConfiguration.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */