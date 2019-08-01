package classes.lrg.jMondrian.view;

import java.awt.Graphics;
import java.util.List;

public interface ShapeElementFactory {
  void addRectangle(Object paramObject, String paramString, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, boolean paramBoolean);
  
  void addRectangle(Object paramObject, String paramString, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6);
  
  void addEllipse(Object paramObject, String paramString, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, boolean paramBoolean);
  
  void addEllipse(Object paramObject, String paramString, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6);
  
  void addLine(Object paramObject, String paramString, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, boolean paramBoolean);
  
  void addText(Object paramObject, String paramString1, String paramString2, int paramInt1, int paramInt2, int paramInt3);
  
  void addPolyLine(Object paramObject, String paramString, List<Integer> paramList1, List<Integer> paramList2);
  
  void update(Graphics paramGraphics);
  
  String findStatusInformation(int paramInt1, int paramInt2);
  
  Object findEntity(int paramInt1, int paramInt2);
}


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\jMondrian\view\ShapeElementFactory.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */