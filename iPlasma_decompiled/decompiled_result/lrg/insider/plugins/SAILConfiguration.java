/*    */ package classes.lrg.insider.plugins;
/*    */ 
/*    */ import java.io.BufferedReader;
/*    */ import java.io.FileInputStream;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStreamReader;
/*    */ import java.util.StringTokenizer;
/*    */ import lrg.common.abstractions.managers.EntityTypeManager;
/*    */ import lrg.insider.plugins.SAILConfiguration;
/*    */ import lrg.insider.plugins.groups.memoria.SAILGroupComputer;
/*    */ import lrg.insider.plugins.properties.memoria.SAILPropertyComputer;
/*    */ 
/*    */ 
/*    */ public class SAILConfiguration
/*    */ {
/*    */   private String fileName;
/*    */   private String isA;
/*    */   private String propName;
/*    */   private String propDescription;
/*    */   
/* 21 */   public SAILConfiguration(String fileName) { this.fileName = fileName; }
/*    */   private String propEntity; private String propFileName; private String propFuncSignature;
/*    */   private String propResultType;
/*    */   
/*    */   public void configure() throws IOException {
/* 26 */     BufferedReader flux_in = new BufferedReader(new InputStreamReader(new FileInputStream(this.fileName)));
/*    */     
/*    */     String line;
/*    */     
/* 30 */     while ((line = flux_in.readLine()) != null) {
/* 31 */       processLine(line);
/*    */     }
/*    */   }
/*    */   
/*    */   private void processLine(String line) {
/*    */     try {
/* 37 */       StringTokenizer st = new StringTokenizer(line, "\t");
/*    */       
/* 39 */       this.isA = st.nextToken();
/* 40 */       this.propName = st.nextToken();
/* 41 */       this.propDescription = st.nextToken();
/* 42 */       this.propEntity = st.nextToken();
/* 43 */       this.propResultType = st.nextToken();
/* 44 */       this.propFileName = st.nextToken();
/*    */ 
/*    */       
/* 47 */       StringTokenizer sign = new StringTokenizer(st.nextToken(), "(");
/*    */       
/* 49 */       this.propFuncSignature = sign.nextToken();
/* 50 */       String param = sign.nextToken();
/* 51 */       this.propFuncSignature = String.valueOf(this.propFuncSignature) + param.substring(0, param.length() - 1);
/*    */       
/* 53 */       System.out.println(String.valueOf(this.propName) + " " + this.propDescription + " " + this.propEntity + " " + this.propFileName + " " + this.propFuncSignature);
/* 54 */       createAnalyse(this.isA, this.propName, this.propDescription, this.propEntity, this.propResultType, this.propFileName, this.propFuncSignature);
/* 55 */     } catch (Exception e) {
/* 56 */       System.err.println(e);
/*    */     } 
/*    */   }
/*    */   
/*    */   private void createAnalyse(String isA, String name, String description, String entity, String resultType, String fileName, String signature) {
/* 61 */     if (isA.equals("property")) {
/* 62 */       EntityTypeManager.attach(new SAILPropertyComputer(name, description, entity, resultType, fileName, signature));
/* 63 */     } else if (isA.equals("group")) {
/* 64 */       EntityTypeManager.attach(new SAILGroupComputer(name, description, entity, fileName, signature));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\SAILConfiguration.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */