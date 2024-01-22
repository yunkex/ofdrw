import org.junit.jupiter.api.Test;
import org.ofdrw.converter.ConvertHelper;
import org.ofdrw.converter.FontLoader;
import org.ofdrw.converter.GeneralConvertException;
import org.ofdrw.converter.export.OFDExporter;
import org.ofdrw.converter.export.PDFExporterPDFBox;
import org.ofdrw.graphics2d.OFDGraphicsDocument;
import org.ofdrw.graphics2d.OFDPageGraphics2D;

import java.awt.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class OFD2PDFTest {


    @Test
    public void convertPdf() {


//        FontLoader.DEBUG = true;
//        // 为不规范的字体名创建映射
//        FontLoader.getInstance()
//                .addAliasMapping("小标宋体", "方正小标宋简体")
//                .addSimilarFontReplaceRegexMapping(".*SimSun.*", "SimSun");
        FontLoader.getCustomInstance().setCustomFontPath("E:\\TEST\\font");
        long start = System.currentTimeMillis();
        try {
            ConvertHelper.toPdf(Paths.get("E:/TEST/1x21.ofd"), Paths.get("E:/TEST/1.pdf"));
            System.out.printf(">> 总计花费: %dms\n", System.currentTimeMillis() - start);
        } catch (GeneralConvertException e) {
            e.printStackTrace();
        }
    }


    /**
     * 验证转换颜色值异常
     */
    @Test
    void testExportCE() throws Exception {
        Path dst = Paths.get("target", "HelloWorld.ofd");
        try (OFDGraphicsDocument doc = new OFDGraphicsDocument(dst)) {
            OFDPageGraphics2D g = doc.newPage(null);
            g.setColor(Color.BLACK);
            g.setFont(new Font("宋体", Font.PLAIN, 7));
            g.drawString("你好OFD Reader & Writer Graphics-2D", 40, 40);
        }
        Path pdfPath = Paths.get("target", "HelloWorld.pdf");
        try (OFDExporter exporter = new PDFExporterPDFBox(dst, pdfPath)) {
            exporter.export();
        }
    }
}
