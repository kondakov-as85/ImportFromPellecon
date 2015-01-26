package com.mycomp.ImportFromPellecon;

import com.mycomp.ExtUtils.UParser;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.yaml.snakeyaml.Yaml;


import java.net.URL;

/**
 * Created by kondakov on 26.01.2015.
 */
public class MainClass {

    public static void main( String[] args ) throws Exception {

        //UParser parser = new UParser("d:\\pell.xml",true);
        UParser parser = new UParser(new URL("http://pellecon.ru/bitrix/catalog_export/wikimart.php"));
        Element root = parser.getRootel();

        NodeList nl = root.getChildNodes();
        for (int i=0; i<nl.getLength(); i++) {
            Node node = nl.item(i);
            if(node!=null){
                System.out.println(node.getNodeName());
            }

        }
    }

}
