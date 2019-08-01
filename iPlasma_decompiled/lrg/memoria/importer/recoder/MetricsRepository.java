package lrg.memoria.importer.recoder;

public interface MetricsRepository {
  void resetAll();
  
  void addComments(int paramInt);
  
  int getCommentsNumber();
  
  void addDecision();
  
  int getDecisions();
  
  void addLoop();
  
  int getLoops();
  
  void addException();
  
  int getExceptions();
  
  void updateNestingLevel(int paramInt);
  
  int getMaxNestingLevel();
  
  int getCyclomatic();
  
  void addLogicalAnd();
  
  void addLogicalOr();
  
  void addStatements(int paramInt);
  
  int getNumberOfStatements();
  
  void addExit();
  
  int getNumberOfExits();
}


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\importer\recoder\MetricsRepository.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */