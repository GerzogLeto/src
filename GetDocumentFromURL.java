
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GetDocumentFromURL {
    public static void processElements(Elements el, String str){
        switch (str){
            case "blue-link"

        }
    }
    public static Elements processingDomElement(Element el, String str){
        switch (str) {
            case "details":
                return el.getElementsByClass(str);
        }
    }
    public static void main(String[] args) throws IOException, Exception {
        String str = "https://moscow.big-book-avto.ru/gruzovye_avtomobili__tehnika/";
        String[] classes = new String[10];
        classes[0] = "details";
        classes[1] = "blue-link";
        Document doc = Jsoup.connect(str).get();
        Element body = doc.body();//get body
        Elements details = processingDomElement(body, classes[0]);
        

        for (Element detail : details) {
            CompanyData myCompanyData = new CompanyData();
            Elements tagsView = detail.getElementsByClass("tags_view");
            for(Element tagView: tagsView){
                String rubrika = "";
                String aboutComp = "";
                Elements bTags = tagView.getElementsByTag("b");
                for(Element bTag: bTags) {
                    rubrika += bTag.text();
                }
                aboutComp += tagsView.text();
                myCompanyData.setNameRubrika(rubrika);
                myCompanyData.setAboutCompany(aboutComp);
            }
            Elements phoneItems = detail.getElementsByClass("phone-item");
            int i = 0;
            String[] arrPhones  = new String[5];
            for(Element phoneItem: phoneItems){
                arrPhones[i] = phoneItem.text();
                i++;

            }
            String[] arr = new String[i];
            while(i >0){
                arr[i-1] = arrPhones[i-1];
                i--;
            }
            myCompanyData.setNumberPhones(arr);
            System.out.println(myCompanyData.getNameRubrika());
            System.out.println(myCompanyData.getAboutCompany());
            for (String str1: myCompanyData.getNumberPhones()){
                System.out.print(str1 + " #### ");
            }
            System.out.println();
            System.out.println("-//-//-//-");
/*            String urlLinkAdress = str + (detail.attr("href")).substring(1);
            Thread.sleep(2000);
            Document subDoc = Jsoup.connect(urlLinkAdress).get()
            Element subElement = subDoc.body();//get body
            Elements urlElements = subElement.getElementsByClass("urls");
            for(Element urlElrment: urlElements){
                Elements links = urlElrment.getElementsByTag("a");
                String urlAdress = "";
                for(Element linkHref: links) {
                    urlAdress += linkHref.attr("href");
                }
            }*/
        }
    }
}
