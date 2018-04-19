package com.mmo.frame.template.fox;

import com.ws.util.exceltemplate.bean.TemplateParse;
import com.ws.util.exceltemplate.helper.TemplateParseHelper;

public class ExcelTemplateHelper {

    private static final ExcelTemplateHelper et = new ExcelTemplateHelper();

    private ExcelTemplateHelper() {
    }

    public static ExcelTemplateHelper getInstance() {
        return et;
    }
    
    public static TemplateParse<LangText> getLangText() {
        TemplateParse<LangText> parse = TemplateParseHelper.getTemplateParse(LangText.class);
        return parse;
    }

}
