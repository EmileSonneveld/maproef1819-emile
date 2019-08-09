package be.emilesonneveld;


import com.github.slugify.Slugify;
//import javafx.application.Application;
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
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.security.Permission;
import java.util.ArrayList;

class Main {
    private static class ExitTrappedException extends SecurityException { }

    private static void forbidSystemExitCall() {
        final SecurityManager securityManager = new SecurityManager() {
            public void checkPermission( Permission permission ) {
                if( "exitVM".equals( permission.getName() ) ) {
                    throw new ExitTrappedException() ;
                }
            }
        } ;
        System.setSecurityManager( securityManager ) ;
    }

    private static void enableSystemExitCall() {
        System.setSecurityManager( null ) ;
    }

    public static void main(String[] args) throws IOException {
        forbidSystemExitCall();

        var outputPath = "C:\\Users\\emill\\Dropbox\\slimmerWorden\\2018-2019-Semester2\\THESIS\\out\\java_pyramid\\";
        var alreadyDone = Util.getFilesFromDirectory(new File(outputPath).toPath());
        var folders = Util.getSrcPaths(new File("D:\\github_java").toPath());
        for (var folder : folders) {

            Slugify slg = new Slugify();
            String slug = slg.slugify(folder);

            if (alreadyDone.contains((outputPath + slug + ".html").replace("\\", "/")))
                continue;

            String html = "no result yet";
            Util.WriteStringToFile(new File(outputPath + slug + ".html"), html);
            try {
                html = calc(folder);

            } catch (Exception e) {

            }
            Util.WriteStringToFile(new File(outputPath + slug + ".html"), html);

        }


        //var srcPaths = Util.getSrcPaths(new File("C:\\Users\\emill\\dev\\SHotDraw").toPath());
//
        //var folder = "C:\\Users\\emill\\dev\\SHotDraw\\JHotDraw_original\\src";
        //var html = calc(folder);
        //Slugify slg = new Slugify();
        //String slug = slg.slugify(folder);
        //Util.WriteStringToFile(new File("C:\\Users\\emill\\Dropbox\\slimmerWorden\\2018-2019-Semester2\\THESIS\\out\\java_pyramid\\" + slug + ".html"), html);
    }

    static String calc(String projectPath) {

        var args = new String[]{projectPath, "OverviewPyramid", "C:\\Users\\emill\\dev\\SHotDraw\\iPlasma.txt"};

        String returnValue = "nothing returned";
        if (args.length < 2) {
            System.out.println("Param Usage: [project source path] [report class name] (result source path)");

            return returnValue;
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
            //JOptionPane.showMessageDialog(StoryTreeUI.instance()
            //                .getTopComponent(), "The model could not be loaded !",
            //        "ERROR", 2);
            return returnValue;
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
            OverviewPyramid tool = (OverviewPyramid) aEntityTool;
            returnValue = tool.buildHTMLPyramid(selectedEntity);

        } catch (RuntimeException exc) {
            System.err.println(String.valueOf(aEntityTool.getToolName()) + " could not be run !");
            exc.printStackTrace();
        }
        return returnValue;
    }
}