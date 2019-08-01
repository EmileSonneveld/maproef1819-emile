package lrg.memoria.importer.recoder;

import lrg.memoria.core.Access;
import lrg.memoria.core.Annotation;
import lrg.memoria.core.AnnotationInstance;
import lrg.memoria.core.ArrayDecorator;
import lrg.memoria.core.Attribute;
import lrg.memoria.core.Body;
import lrg.memoria.core.Call;
import lrg.memoria.core.Class;
import lrg.memoria.core.CodeStripe;
import lrg.memoria.core.File;
import lrg.memoria.core.Function;
import lrg.memoria.core.LocalVariable;
import lrg.memoria.core.Method;
import lrg.memoria.core.Package;
import lrg.memoria.core.Parameter;
import lrg.memoria.core.Primitive;
import lrg.memoria.core.System;
import lrg.memoria.core.Type;
import lrg.memoria.core.Variable;

public interface ModelRepository {
  Class addClass(Object paramObject, String paramString);
  
  Class getClass(Object paramObject);
  
  Package addPackage(Object paramObject, String paramString);
  
  Package getPackage(Object paramObject);
  
  Method addMethod(Object paramObject, String paramString);
  
  Function getMethod(Object paramObject);
  
  Primitive addPrimitiveType(Object paramObject, String paramString);
  
  Primitive getPrimitiveType(Object paramObject);
  
  ArrayDecorator getArrayType(Type paramType, int paramInt);
  
  LocalVariable addLocalVar(Object paramObject, String paramString, CodeStripe paramCodeStripe);
  
  LocalVariable getLocalVar(Object paramObject);
  
  Parameter addParameter(Object paramObject, String paramString);
  
  Parameter getParameter(Object paramObject);
  
  Attribute addAttribute(Object paramObject, String paramString);
  
  Attribute getAttribute(Object paramObject);
  
  Access addAccess(Object paramObject, Variable paramVariable, Body paramBody);
  
  Access addAccess(Object paramObject, Variable paramVariable, CodeStripe paramCodeStripe);
  
  Access getAccess(Object paramObject);
  
  Call addCall(Object paramObject, Method paramMethod, Body paramBody);
  
  Call addCall(Object paramObject, Method paramMethod, CodeStripe paramCodeStripe);
  
  Call getCall(Object paramObject);
  
  File addFile(Object paramObject, String paramString);
  
  File getFile(Object paramObject);
  
  File getCurrentFile();
  
  void setCurrentFile(File paramFile);
  
  Class getCurrentClass();
  
  void setCurrentClass(Class paramClass);
  
  Method getCurrentMethod();
  
  void setCurrentMethod(Method paramMethod);
  
  Package getCurrentPackage();
  
  void setCurrentPackage(Package paramPackage);
  
  Body getCurrentBody();
  
  void setCurrentBody(Body paramBody);
  
  CodeStripe getCurrentStripe();
  
  void setCurrentStripe(CodeStripe paramCodeStripe);
  
  System getSystem();
  
  AnnotationInstance getCurrentAnnotationInstance();
  
  void setCurrentAnnotationInstance(AnnotationInstance paramAnnotationInstance);
  
  Annotation getCurrentAnnotation();
  
  void setCurrentAnnotation(Annotation paramAnnotation);
  
  Annotation getAnnotation(String paramString);
  
  Annotation addAnnotation(Object paramObject, String paramString);
}


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\importer\recoder\ModelRepository.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */