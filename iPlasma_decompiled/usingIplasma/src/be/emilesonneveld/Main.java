package be.emilesonneveld;


import lrg.insider.gui.ui.utils.ProgressBar;
import lrg.common.metamodel.MetaModel;
import lrg.insider.gui.InsiderGUIMain;
import lrg.insider.gui.ui.loader.ModelLoaderUI;
import lrg.insider.metamodel.MemoriaJavaModelBuilder;

public class Main {

    public static void main(String[] args) throws Exception {
        // write your code here
        ProgressBar progress = new ProgressBar("Loading the model ...");
        MetaModel.createFrom(
                /* 122 */             new MemoriaJavaModelBuilder(ModelLoaderUI.getSourcePath(),
                        /* 123 */               ModelLoaderUI.getCachePath(), InsiderGUIMain.getAdditioanClassPath(), progress),
                /* 124 */             ModelLoaderUI.getSourcePath());
    }
}
