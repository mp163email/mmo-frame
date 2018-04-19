package com.mmo.frame.template.fox;
//import net.sf.json.JSONArray;
//import com.ws.util.io.helper.JsonHelper;

public class LangText implements com.ws.util.exceltemplate.bean.IProtocolBuffer {
	
    /**
     * id
     */
    public int id;

    /**
     * 提示文本
     */
    public String text;


    public void read(java.io.DataInputStream in) throws java.io.IOException {
		int length = 0;
        this.id = in.readInt();
        this.text = in.readUTF();

    }

    public void write(java.io.DataOutputStream out) throws java.io.IOException {
   	    int length = 0;
        out.writeInt(id);
        out.writeUTF(text == null ? "" : text);

    }

    public int getId() {
        return id;
    }
}
