package com.mycomp.ImportFromPellecon;

import com.mycomp.ExtUtils.FileUtils;
import com.mycomp.ExtUtils.UParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.net.URL;

/**
 * Created by kondakov on 26.01.2015.
 */
public class MainClass {

    public static void main( String[] args ) throws Exception {
        String xmlUrl = args[0];
        String pathCategory = args[1];
        String pathOffers = args[2];
//        String xmlUrl = "http://pellecon.ru/bitrix/catalog_export/wikimart.php";
//        String pathCategory = "d:\\temp\\category.csv";
//        String pathOffers = "d:\\temp\\offers.csv";
        UParser parser = new UParser(new URL(xmlUrl));
        Element root = parser.getRootel();
        NodeList nl = root.getChildNodes();
        Node categories = UParser.searchNode(root,"categories");
        Node offers = UParser.searchNode(root,"offers");
        NodeList list = categories.getChildNodes();
        FileUtils.writeFile(pathCategory, "id;parentId;Name;\n", false);
        for(int i=0; i<list.getLength(); i++) {
            Node node = list.item(i);
            if(node!=null && node.getAttributes()!=null){
                String id = UParser.paramValue(node,"id");
                String parentId = UParser.paramValue(node,"parentId");
                String name = node.getTextContent();
                FileUtils.writeFile(pathCategory,id+";"+parentId+";"+name+";\n",true);
            }
        }
        list = offers.getChildNodes();
        FileUtils.writeFile(pathOffers, "id;available;Name;url;price;currencyId;categoryId;picture;name;description;\n", false);
        for(int i=0; i<list.getLength(); i++) {
            Node offer = list.item(i);
            if(offer!=null && offer.getAttributes()!=null){
                String id = UParser.paramValue(offer,"id");
                String available = UParser.paramValue(offer,"available");
                String url = UParser.searchNode(offer,"url").getTextContent();
                String price = UParser.searchNode(offer,"price").getTextContent();
                String currencyId = UParser.searchNode(offer,"currencyId").getTextContent();
                String categoryId = UParser.searchNode(offer,"categoryId").getTextContent();
                String picture = UParser.searchNode(offer,"picture").getTextContent();
                String name = UParser.searchNode(offer,"name").getTextContent();
                String description = UParser.searchNode(offer,"description").getTextContent();
                FileUtils.writeFile(pathOffers,id+";"+available+";"+url+";"+price+";"+currencyId+";"+categoryId+";"+picture+";"+name+";"+description+";\n",true);
            }
        }
    }
}
