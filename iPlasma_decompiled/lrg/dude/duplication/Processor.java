/*     */ package lrg.dude.duplication;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import lrg.common.abstractions.entities.AbstractEntity;
/*     */ import lrg.common.abstractions.entities.ResultEntity;
/*     */ import lrg.common.utils.ProgressObserver;
/*     */ 
/*     */ public class Processor
/*     */   extends Thread
/*     */   implements Subject {
/*     */   private ArrayList observers;
/*     */   private Entity[] entities;
/*     */   private IMethodEntity referenceEntity;
/*     */   private MatrixLineList matrixLines;
/*     */   private VirtualMatrix coolMatrix;
/*     */   private DuplicationList duplicates;
/*     */   
/*     */   public Processor(String path, ProgressObserver po, StringCompareStrategy compareStrategy) {
/*  21 */     this.observers = new ArrayList();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  31 */     this.params = new Parameters(0, 1, 2, false);
/*     */ 
/*     */     
/*  34 */     this.numberOfRawLines = 0L;
/*  35 */     this.numberOfDots = 0L;
/*  36 */     this.numberOfDuplicatedLines = 0L;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  46 */     this.progressObserver = po;
/*  47 */     this.compareStrategy = compareStrategy;
/*  48 */     long start = System.currentTimeMillis();
/*  49 */     DirectoryReader cititorDirector = new DirectoryReader(path);
/*  50 */     ArrayList files = cititorDirector.getFilesRecursive();
/*  51 */     if (files != null) {
/*  52 */       this.entities = new Entity[files.size()];
/*  53 */       for (int i = 0; i < files.size(); i++) {
/*  54 */         File currentFile = (File)files.get(i);
/*     */         
/*  56 */         String shortName = currentFile.getAbsolutePath().substring(path.length() + 1);
/*  57 */         this.entities[i] = new SourceFile(currentFile, shortName);
/*     */       } 
/*     */     } else {
/*  60 */       this.entities = new SourceFile[0];
/*  61 */     }  long stop = System.currentTimeMillis();
/*  62 */     System.out.print("\nDUDE: Got " + this.entities.length + " files in: ");
/*  63 */     System.out.println(String.valueOf(TimeMeasurer.convertTimeToString(stop - start)) + "\n");
/*     */   }
/*     */   private StringCompareStrategy compareStrategy; private Parameters params; private long numberOfRawLines; private long numberOfDots; private long numberOfDuplicatedLines; private ProgressObserver progressObserver;
/*     */   
/*     */   public Processor(Entity[] methods, ProgressObserver po, StringCompareStrategy compareStrategy) {
/*     */     this.observers = new ArrayList();
/*     */     this.params = new Parameters(0, 1, 2, false);
/*     */     this.numberOfRawLines = 0L;
/*     */     this.numberOfDots = 0L;
/*     */     this.numberOfDuplicatedLines = 0L;
/*  73 */     this.progressObserver = po;
/*  74 */     this.compareStrategy = compareStrategy;
/*  75 */     this.entities = methods;
/*  76 */     this.referenceEntity = null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  81 */   public Processor(Entity[] methods, ProgressObserver po) { this(methods, po, new IdenticalCompareStrategy()); }
/*     */ 
/*     */   
/*     */   public Processor(Entity[] methods, IMethodEntity reference, ProgressObserver po) {
/*  85 */     this(methods, po, new IdenticalCompareStrategy());
/*  86 */     this.referenceEntity = reference;
/*     */   }
/*     */ 
/*     */   
/*     */   public void runFromEclipse() {
/*  91 */     if (this.referenceEntity != null) rearrangeEntities(); 
/*  92 */     createMatrixLines();
/*  93 */     if (this.referenceEntity == null) { clusteredSearch(); }
/*  94 */     else { clusteredSearchWithReferenceEntity(); }
/*     */     
/*  96 */     this.numberOfDuplicatedLines = this.matrixLines.countDuplicatedLines();
/*  97 */     attachResultsToReferenceMethod();
/*     */   }
/*     */   
/*     */   public void run() {
/* 101 */     if (this.referenceEntity != null) rearrangeEntities(); 
/* 102 */     createMatrixLines();
/* 103 */     if (this.referenceEntity == null) { clusteredSearch(); }
/* 104 */     else { clusteredSearchWithReferenceEntity(); }
/*     */     
/* 106 */     this.numberOfDuplicatedLines = this.matrixLines.countDuplicatedLines();
/* 107 */     notifyObservers();
/*     */   }
/*     */   
/*     */   private void rearrangeEntities() {
/* 111 */     int referenceIndex = 0;
/* 112 */     if (this.referenceEntity == null)
/* 113 */       return;  IMethodEntity firstEntity = (IMethodEntity)this.entities[0];
/* 114 */     if (firstEntity.getMethod() == this.referenceEntity.getMethod())
/*     */       return; 
/* 116 */     for (referenceIndex = 1; ((IMethodEntity)this.entities[referenceIndex]).getMethod() != this.referenceEntity.getMethod(); referenceIndex++);
/*     */     
/* 118 */     if (referenceIndex >= this.entities.length) { System.out.println("ERROR"); return; }
/*     */     
/* 120 */     this.entities[referenceIndex] = firstEntity;
/* 121 */     this.entities[0] = this.referenceEntity;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void attachResultsToMethods() {
/* 129 */     DuplicationList redundantDups = this.duplicates.getRedundantList();
/* 130 */     if (this.entities == null)
/*     */       return; 
/* 132 */     for (int i = 0; i < this.entities.length; i++) {
/* 133 */       AbstractEntity currentMethod = ((IMethodEntity)this.entities[i]).getMethod();
/* 134 */       DuplicationList currentList = new DuplicationList();
/* 135 */       for (int j = 0; j < redundantDups.size(); j++) {
/* 136 */         Entity anEntity = redundantDups.get(j).getReferenceCode().getEntity();
/* 137 */         if (((IMethodEntity)anEntity).getMethod() == currentMethod) {
/* 138 */           currentList.add(redundantDups.get(j));
/*     */         }
/*     */       } 
/* 141 */       currentMethod.addProperty("#DUPLICATION#", new ResultEntity(currentList));
/*     */     } 
/*     */   }
/*     */   
/*     */   public void attachResultsToReferenceMethod() {
/* 146 */     DuplicationList redundantDups = this.duplicates.getRedundantList();
/* 147 */     if (this.entities == null)
/*     */       return; 
/* 149 */     AbstractEntity currentMethod = this.referenceEntity.getMethod();
/* 150 */     DuplicationList currentList = new DuplicationList();
/* 151 */     for (int j = 0; j < redundantDups.size(); j++) {
/* 152 */       Entity anEntity = redundantDups.get(j).getReferenceCode().getEntity();
/* 153 */       if (((IMethodEntity)anEntity).getMethod() == currentMethod)
/* 154 */         currentList.add(redundantDups.get(j)); 
/*     */     } 
/* 156 */     currentMethod.addProperty("#DUPLICATION#", new ResultEntity(currentList));
/* 157 */     System.out.println(String.valueOf(currentList.size()) + " duplication objects attached to method " + currentMethod.getName());
/*     */   }
/*     */ 
/*     */   
/*     */   private void clusteredSearch() {
/* 162 */     int startingMatrixRow = 0;
/* 163 */     int startingMatrixColumn = 0;
/*     */     
/* 165 */     int noOfEntities = this.entities.length;
/* 166 */     int matrixPieces = noOfEntities * (noOfEntities - 1) / 2 + noOfEntities;
/* 167 */     if (this.progressObserver != null) {
/* 168 */       this.progressObserver.setMaxValue(matrixPieces);
/*     */     }
/* 170 */     this.coolMatrix = new VirtualMatrix(this.matrixLines.size());
/* 171 */     this.duplicates = new DuplicationList();
/* 172 */     System.out.println("NO OF ENTITIES: " + noOfEntities);
/* 173 */     for (int i = 0; i < noOfEntities; i++) {
/* 174 */       int noOfRows = this.entities[i].getNoOfRelevantLines();
/* 175 */       startingMatrixColumn = startingMatrixRow;
/* 176 */       for (int j = i; j < noOfEntities; j++) {
/* 177 */         int noOfColumns = this.entities[j].getNoOfRelevantLines();
/* 178 */         createMatrixCells(startingMatrixRow, noOfRows, startingMatrixColumn, noOfColumns);
/*     */         
/* 180 */         searchDuplicates(startingMatrixRow, noOfRows);
/* 181 */         this.coolMatrix.freeLines(startingMatrixRow, startingMatrixRow + noOfRows);
/* 182 */         startingMatrixColumn += noOfColumns;
/*     */         
/* 184 */         if (this.progressObserver != null) {
/* 185 */           this.progressObserver.increment();
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/* 190 */       startingMatrixRow += noOfRows;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void clusteredSearchWithReferenceEntity() {
/* 198 */     int noOfEntities = this.entities.length;
/*     */     
/* 200 */     this.coolMatrix = new VirtualMatrix(this.matrixLines.size());
/* 201 */     this.duplicates = new DuplicationList();
/* 202 */     System.out.println("NO OF ENTITIES: " + noOfEntities);
/* 203 */     int noOfRows = this.referenceEntity.getNoOfRelevantLines();
/* 204 */     int startingMatrixRow = 0, startingMatrixColumn = startingMatrixRow;
/* 205 */     for (int j = 0; j < noOfEntities; j++) {
/* 206 */       int noOfColumns = this.entities[j].getNoOfRelevantLines();
/* 207 */       createMatrixCells(startingMatrixRow, noOfRows, startingMatrixColumn, noOfColumns);
/*     */       
/* 209 */       searchDuplicates(startingMatrixRow, noOfRows);
/* 210 */       this.coolMatrix.freeLines(startingMatrixRow, startingMatrixRow + noOfRows);
/* 211 */       startingMatrixColumn += noOfColumns;
/* 212 */       if (this.progressObserver != null) this.progressObserver.increment();
/*     */     
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void createMatrixCells(int startingMatrixRow, int rows, int startingMatrixColumn, int columns) {
/* 224 */     int endMatrixRow = startingMatrixRow + rows;
/* 225 */     int endMatrixColumn = startingMatrixColumn + columns;
/* 226 */     for (int i = startingMatrixRow; i < endMatrixRow; i++) {
/* 227 */       String refCode = this.matrixLines.get(i).getCode();
/* 228 */       int start = (startingMatrixRow == startingMatrixColumn) ? (i + 1) : startingMatrixColumn;
/* 229 */       for (int j = start; j < endMatrixColumn; j++) {
/*     */ 
/*     */         
/* 232 */         if (this.compareStrategy.similar(refCode, this.matrixLines.get(j).getCode())) {
/* 233 */           this.coolMatrix.set(i, j, new Boolean(false));
/* 234 */           this.numberOfDots++;
/*     */         } 
/*     */       } 
/*     */     } 
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
/*     */   private void searchDuplicates(int startingMatrixRow, int rows) {
/* 252 */     int endMatrixRow = startingMatrixRow + rows;
/* 253 */     for (int i = startingMatrixRow; i < endMatrixRow; i++) {
/* 254 */       Iterator iterator = this.coolMatrix.iterator(i);
/* 255 */       while (iterator.hasNext()) {
/* 256 */         int j = ((Integer)iterator.next()).intValue();
/*     */         
/* 258 */         if (this.coolMatrix.get(i, j) != null && 
/* 259 */           !this.coolMatrix.get(i, j).booleanValue()) {
/*     */           Duplication newDup;
/* 261 */           if ((newDup = traceDuplication(i, j)) != null)
/*     */           {
/* 263 */             this.duplicates.add(newDup);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
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
/*     */   private boolean validCoordinate(Coordinate reference, int dx, int dy) {
/* 282 */     int oldX = reference.getX();
/* 283 */     int oldY = reference.getY();
/* 284 */     int newX = oldX + dx;
/* 285 */     int newY = oldY + dy;
/* 286 */     if (newX < this.matrixLines.size() && 
/* 287 */       newY < this.matrixLines.size())
/*     */     {
/* 289 */       if (this.matrixLines.get(newX).getEntity() == this.matrixLines.get(oldX).getEntity() && 
/* 290 */         this.matrixLines.get(newY).getEntity() == this.matrixLines.get(oldY).getEntity() && 
/* 291 */         this.coolMatrix.get(newX, newY) != null && 
/* 292 */         !this.coolMatrix.get(newX, newY).booleanValue())
/*     */       {
/* 294 */         return true; } 
/*     */     }
/* 296 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Coordinate getNextCoordinate(Coordinate start, int currentExactSize) {
/* 307 */     if (validCoordinate(start, 1, 1)) {
/* 308 */       return new Coordinate(start.getX() + 1, start.getY() + 1);
/*     */     }
/* 310 */     if (currentExactSize < this.params.getMinExactChunk())
/* 311 */       return null; 
/* 312 */     for (int i = 1; i <= this.params.getMaxLineBias(); i++) {
/* 313 */       if (validCoordinate(start, 1, 1 + i)) {
/* 314 */         return new Coordinate(start.getX() + 1, start.getY() + 1 + i);
/*     */       }
/*     */     } 
/* 317 */     for (int i = 1; i <= this.params.getMaxLineBias(); i++) {
/* 318 */       if (validCoordinate(start, 1 + i, 1)) {
/* 319 */         return new Coordinate(start.getX() + 1 + i, start.getY() + 1);
/*     */       }
/*     */     } 
/* 322 */     for (int i = 1; i <= this.params.getMaxLineBias(); i++) {
/* 323 */       if (validCoordinate(start, 1 + i, 1 + i)) {
/* 324 */         return new Coordinate(start.getX() + 1 + i, start.getY() + 1 + i);
/*     */       }
/*     */     } 
/* 327 */     return null;
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
/*     */   private Duplication traceDuplication(int rowNo, int colNo) {
/* 341 */     CoordinateList coordinates = new CoordinateList();
/* 342 */     Coordinate start = new Coordinate(rowNo, colNo);
/* 343 */     Coordinate end = start;
/* 344 */     Coordinate current = start;
/* 345 */     coordinates.add(current);
/* 346 */     int currentExactChunkSize = 1;
/* 347 */     while ((current = getNextCoordinate(current, currentExactChunkSize)) != null) {
/* 348 */       Coordinate previous = coordinates.get(coordinates.size() - 1);
/*     */ 
/*     */       
/* 351 */       if (current.getX() < start.getY()) {
/* 352 */         coordinates.add(current);
/*     */ 
/*     */         
/* 355 */         int dx = current.getX() - previous.getX();
/* 356 */         int dy = current.getY() - previous.getY();
/* 357 */         if (dx == 1 && dy == 1) {
/* 358 */           currentExactChunkSize++; continue;
/*     */         } 
/* 360 */         currentExactChunkSize = 1;
/*     */       } 
/* 362 */     }  if (currentExactChunkSize < this.params.getMinExactChunk())
/*     */     {
/* 364 */       for (int i = 0; i < currentExactChunkSize; i++) {
/* 365 */         int index = coordinates.size() - 1;
/* 366 */         coordinates.remove(index);
/*     */       } 
/*     */     }
/* 369 */     if (coordinates.size() > 0) {
/* 370 */       end = coordinates.get(coordinates.size() - 1);
/*     */       
/* 372 */       int lengthX = end.getX() - start.getX() + 1;
/* 373 */       int lengthY = end.getY() - start.getY() + 1;
/* 374 */       int length = (lengthX <= lengthY) ? lengthX : lengthY;
/* 375 */       if (length >= this.params.getMinLength())
/* 376 */         return makeDuplication(coordinates, length); 
/*     */     } 
/* 378 */     return null;
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
/*     */   private Duplication makeDuplication(CoordinateList coordinates, int length) {
/* 390 */     String signature = extractSignature(coordinates);
/* 391 */     DuplicationType type = extractType(signature);
/* 392 */     markCoordinates(coordinates);
/*     */     
/* 394 */     Coordinate start = coordinates.get(0);
/* 395 */     Coordinate end = coordinates.get(coordinates.size() - 1);
/*     */     
/* 397 */     MatrixLine referenceStart = this.matrixLines.get(start.getX());
/* 398 */     MatrixLine referenceEnd = this.matrixLines.get(end.getX());
/* 399 */     MatrixLine duplicateStart = this.matrixLines.get(start.getY());
/* 400 */     MatrixLine duplicateEnd = this.matrixLines.get(end.getY());
/* 401 */     CodeFragment referenceCode = new CodeFragment(referenceStart.getEntity(), 
/* 402 */         referenceStart.getRealIndex(), referenceEnd.getRealIndex());
/* 403 */     CodeFragment duplicateCode = new CodeFragment(duplicateStart.getEntity(), 
/* 404 */         duplicateStart.getRealIndex(), duplicateEnd.getRealIndex());
/* 405 */     return new Duplication(referenceCode, duplicateCode, type, signature, length);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void markCoordinates(CoordinateList coordinates) {
/* 416 */     int size = coordinates.size();
/* 417 */     for (int i = 0; i < size; i++) {
/* 418 */       Coordinate current = coordinates.get(i);
/* 419 */       this.coolMatrix.set(current.getX(), current.getY(), new Boolean(true));
/*     */       
/* 421 */       this.matrixLines.get(current.getX()).setDuplicated();
/* 422 */       this.matrixLines.get(current.getY()).setDuplicated();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private DuplicationType extractType(String signature) {
/* 434 */     StringBuffer buffer = new StringBuffer(signature);
/* 435 */     int iModified = buffer.indexOf("M");
/* 436 */     int iDelete = buffer.indexOf("D");
/* 437 */     int iInsert = buffer.indexOf("I");
/* 438 */     if (iModified < 0 && iDelete < 0 && iInsert < 0)
/* 439 */       return DuplicationType.EXACT; 
/* 440 */     if (iModified > -1 && iDelete < 0 && iInsert < 0)
/* 441 */       return DuplicationType.MODIFIED; 
/* 442 */     if (iModified < 0 && iDelete > -1 && iInsert < 0)
/* 443 */       return DuplicationType.DELETE; 
/* 444 */     if (iModified < 0 && iDelete < 0 && iInsert > -1)
/* 445 */       return DuplicationType.INSERT; 
/* 446 */     return DuplicationType.COMPOSED;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String extractSignature(CoordinateList coordinates) {
/* 457 */     StringBuffer signature = new StringBuffer();
/* 458 */     int exactChunkSize = 1;
/* 459 */     char separator = '.';
/* 460 */     for (int i = 1; i < coordinates.size(); i++) {
/* 461 */       Coordinate current = coordinates.get(i);
/* 462 */       Coordinate previous = coordinates.get(i - 1);
/*     */       
/* 464 */       int xBias = current.getX() - previous.getX() - 1;
/* 465 */       int yBias = current.getY() - previous.getY() - 1;
/*     */       
/* 467 */       if (xBias == yBias && xBias == 0) {
/* 468 */         exactChunkSize++;
/*     */       } else {
/* 470 */         signature.append("E" + exactChunkSize);
/* 471 */         exactChunkSize = 1;
/*     */         
/* 473 */         if (xBias == yBias && xBias > 0) {
/* 474 */           signature.append(String.valueOf(separator) + "M" + xBias + separator);
/* 475 */         } else if (xBias > 0) {
/* 476 */           signature.append(String.valueOf(separator) + "D" + xBias + separator);
/* 477 */         } else if (yBias > 0) {
/* 478 */           signature.append(String.valueOf(separator) + "I" + yBias + separator);
/*     */         } 
/*     */       } 
/*     */     } 
/* 482 */     signature.append("E" + exactChunkSize);
/* 483 */     return signature.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MatrixLineList createMatrixLines() {
/* 494 */     long start = System.currentTimeMillis();
/* 495 */     this.matrixLines = new MatrixLineList();
/*     */     
/* 497 */     for (int i = 0; i < this.entities.length; i++) {
/* 498 */       int noOfMatrixLinesBefore = this.matrixLines.size();
/* 499 */       if (this.entities[i] == null) System.out.println("null"); 
/* 500 */       this.matrixLines.addAll(entityToMatrixLines(this.entities[i]));
/* 501 */       int noOfMatrixLinesAfter = this.matrixLines.size();
/* 502 */       this.entities[i].setNoOfRelevantLines(noOfMatrixLinesAfter - noOfMatrixLinesBefore);
/* 503 */       setRelevantLinesForReferenceEntity(this.entities[i]);
/*     */     } 
/* 505 */     long stop = System.currentTimeMillis();
/* 506 */     System.out.print("\nDUDE: Got " + this.matrixLines.size() + " lines of clean code in: ");
/* 507 */     System.out.println(String.valueOf(TimeMeasurer.convertTimeToString(stop - start)) + "\n");
/* 508 */     return this.matrixLines;
/*     */   }
/*     */ 
/*     */   
/*     */   private void setRelevantLinesForReferenceEntity(Entity entity) {
/* 513 */     if (this.referenceEntity == null)
/* 514 */       return;  IMethodEntity reference = this.referenceEntity;
/* 515 */     IMethodEntity crtEntity = (IMethodEntity)entity;
/*     */     
/* 517 */     if (reference.getMethod() == crtEntity.getMethod()) {
/* 518 */       reference.setNoOfRelevantLines(entity.getNoOfRelevantLines());
/*     */     }
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
/*     */   private StringList cleanCode(StringList bruteText) {
/* 531 */     CleaningDecorator cleaner, commonCleaner = new WhiteSpacesCleaner(new NoiseCleaner(null));
/*     */     
/* 533 */     if (!this.params.isConsiderComments()) {
/* 534 */       cleaner = new CommentsCleaner(commonCleaner);
/*     */     } else {
/* 536 */       cleaner = commonCleaner;
/* 537 */     }  return cleaner.clean(bruteText);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private MatrixLineList entityToMatrixLines(Entity entity) {
/* 547 */     StringList code = entity.getCode();
/* 548 */     this.numberOfRawLines += code.size();
/* 549 */     code = cleanCode(code);
/*     */ 
/*     */     
/* 552 */     MatrixLineList matrixLines = new MatrixLineList();
/* 553 */     for (int i = 0; i < code.size(); i++) {
/* 554 */       if (code.get(i).length() > 0) {
/* 555 */         matrixLines.add(new MatrixLine(code.get(i), entity, i + 1));
/*     */       }
/*     */     } 
/* 558 */     return matrixLines;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 568 */   public long getNumberOfRawLines() { return this.numberOfRawLines; }
/*     */ 
/*     */   
/*     */   public long getNumberOfCleanLines() {
/* 572 */     if (this.matrixLines != null) {
/* 573 */       return this.matrixLines.size();
/*     */     }
/* 575 */     return -1L;
/*     */   }
/*     */   
/*     */   public int getNumberOfEntities() {
/* 579 */     if (this.entities != null) {
/* 580 */       return this.entities.length;
/*     */     }
/* 582 */     return -1;
/*     */   }
/*     */ 
/*     */   
/* 586 */   public long getNumberOfDots() { return this.numberOfDots; }
/*     */ 
/*     */ 
/*     */   
/* 590 */   public Duplication[] getSearchResults() { return this.duplicates.toArray(); }
/*     */ 
/*     */ 
/*     */   
/* 594 */   public long getNumberOfDuplicatedLines() { return this.numberOfDuplicatedLines; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 604 */   public int getMatrixLinesLength() { return this.matrixLines.size(); }
/*     */ 
/*     */ 
/*     */   
/* 608 */   public Entity[] testGetEntities() { return this.entities; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 613 */   public void attach(Observer observer) { this.observers.add(observer); }
/*     */ 
/*     */ 
/*     */   
/* 617 */   public void detach(Observer observer) { this.observers.remove(observer); }
/*     */ 
/*     */   
/*     */   public void notifyObservers() {
/* 621 */     Iterator iterator = this.observers.iterator();
/* 622 */     while (iterator.hasNext()) {
/* 623 */       ((Observer)iterator.next()).getDuplication(this);
/*     */     }
/*     */   }
/*     */   
/* 627 */   public void setParams(Parameters params) { this.params = params; }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\dude.jar!\lrg\dude\duplication\Processor.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */