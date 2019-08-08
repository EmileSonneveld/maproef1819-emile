package be.emilesonneveld;


import javafx.application.Application;
import lrg.common.abstractions.entities.AbstractEntityInterface;
import lrg.common.abstractions.entities.GroupEntity;
import lrg.common.abstractions.plugins.tools.AbstractEntityTool;
import lrg.insider.gui.ui.stories.StoryTreeUI;
import lrg.insider.gui.ui.utils.ProgressBar;
import lrg.common.metamodel.MetaModel;
import lrg.insider.gui.InsiderGUIMain;
import lrg.insider.gui.ui.loader.ModelLoaderUI;
import lrg.insider.metamodel.Address;
import lrg.insider.metamodel.MemoriaJavaModelBuilder;
import lrg.insider.plugins.tools.OverviewPyramid;

import javax.swing.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws Exception {
        System.out.println("Working Directory = " +
                System.getProperty("user.dir"));
        lrg.insider.gui.InsiderGUIMain.main(new String[]{});
        //lrg.insider.plugins.tools.OverviewPyramid pyr = new lrg.insider.plugins.tools.OverviewPyramid();
        //System.out.println(pyr.getToolName());
    }
}


class InsiderTextMain {
    public static void main(String[] args) {

        args = new String[]{"C:\\Users\\emill\\dev\\SHotDraw\\JHotDraw_original\\src", "OverviewPyramid", "C:\\Users\\emill\\dev\\SHotDraw\\iPlasma.txt"};

        if (args.length < 2) {
            System.out.println("Param Usage: [project source path] [report class name] (result source path)");

            return;
        }
        String strSourcePath = args[0];
        String strReportName = "lrg.insider.plugins.tools." + args[1];
        String strReportPath = (args.length > 2) ? args[2] : "";

        ProgressBar progress = new ProgressBar("Loading the model ...");
        try {
            MetaModel.createFrom(new MemoriaJavaModelBuilder(strSourcePath,
                    ModelLoaderUI.getCachePath(),
                    InsiderGUIMain.getAdditioanClassPath(), progress), strSourcePath);

        } catch (Exception e2) {
            e2.printStackTrace();
            JOptionPane.showMessageDialog(StoryTreeUI.instance()
                            .getTopComponent(), "The model could not be loaded !",
                    "ERROR", 2);
            return;
        } finally {
            progress.close();
        }

        ArrayList dummyList = new ArrayList();
        dummyList.add(MetaModel.instance().findEntityByAddress(Address.buildForRoot()));
        AbstractEntityInterface selectedEntity = (new GroupEntity(Address.buildForRoot(), dummyList)).getElementAt(0);

        ArrayList paramList = new ArrayList();
        paramList.add(strReportPath);

        AbstractEntityTool aEntityTool = null;
        try {
            Class classReport = Class.forName(strReportName);
            aEntityTool = (AbstractEntityTool) classReport.newInstance();
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(StoryTreeUI.instance().getTopComponent(), "Class " + strReportName + " not found!", "EXCEPTION", 2);
            e.printStackTrace();
        } catch (InstantiationException instExc) {
            System.out.println("Class " + strReportName + " is a non-instantiable class !");
            instExc.printStackTrace();
        } catch (IllegalAccessException illegalExc) {
            System.out.println("Class " + strReportName + " or its nullary constructor is not accessible !");
            illegalExc.printStackTrace();
        }


        try {
            aEntityTool.run(selectedEntity, paramList);
        } catch (RuntimeException exc) {
            System.err.println(String.valueOf(aEntityTool.getToolName()) + " could not be run !");
            exc.printStackTrace();
        }
    }
}