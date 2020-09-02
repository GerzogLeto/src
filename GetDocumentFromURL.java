
import java.io.FileWriter;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GetDocumentFromURL {
    public static Elements getElements(Element el, String str){
        return el.getElementsByClass(str);
    }
    public static String processElements (Elements el, String str){
        switch (str) {
            case "blue-link":
                for(Element element: el){
                    return element.text();
                }
            case "tags_view":
                for(Element element: el){
                    return element.text();
                }
            case "b":
                for(Element element: el){
                    return element.text();
                }
            case "address-info-text":
                for(Element element: el){
                    return element.text();
                }
            default:
                return "null";
        }
    }
    public static void writeData(String data, CompanyData cd, String className){
        switch (className) {
            case "blue-link":
                cd.setNameCompany(data);
                break;
            case "tags_view":
                cd.setAboutCompany(data);
                break;
            case "b":
                cd.setNameRubrika(data);
                break;
            case "address-info-text":
                cd.setAdress(data);
                break;
            case "url-links":
                cd.setUrlLink(data);
                break;
        }

    }
    public static void writeData(String[] data, CompanyData cd){
        cd.setNumberPhones(data);
    }
    public static String[] getTelephones (Elements el){
        String[] strArr = new String[el.size()];
        int i = 0;
        for(Element element: el) {
            strArr[i] = element.text();
            i++;
        }
        return strArr;
    }

    public static String getUrlLinks (Element el) throws IOException, Exception {
        String urlLinkAdress = (el.attr("abs:href"));
        Thread.sleep(500);
        Document subDoc = Jsoup.connect(urlLinkAdress).get();
        Element subElement = subDoc.body();//get body
        Elements urlElements = subElement.getElementsByClass("urls");
        String urlAdress = "";
        for (Element urlElrment : urlElements) {
            Elements links = urlElrment.getElementsByTag("a");
            for (Element linkHref : links) {
                urlAdress += linkHref.attr("href");
                urlAdress += " ; ";
            }
        }
        return urlAdress;
    }
    public static String getString (CompanyData companyData){
        String telNumbers = "";
        for(String str: companyData.getNumberPhones()){
            telNumbers = telNumbers + "|" + str;
        }
        String outPut = companyData.getNameRubrika() + "|" +
                        companyData.getNameCompany() + "|" +
                        companyData.getUrlLink() + "|" +
                        companyData.getAdress() + "|" +
                        companyData.getAboutCompany() +
                        telNumbers;
        return outPut;
    }
    public static void writeStringToFile(CompanyData[] arr) throws Exception{
        FileWriter fileWriter = new FileWriter("file.txt");
        fileWriter.write("Rubrika|NameCompamy|URL_links|Adress|AboutCompany|Tel_1|Tel_2|Tel_3|Tel_4|Tel_5|Tel_6\t\n");
        for(CompanyData companyData: arr){
            fileWriter.write(getString(companyData) + "\t\n");
        }
        fileWriter.close();
    }
    public static void main(String[] args) throws IOException, Exception {
        String str = "https://moscow.big-book-avto.ru/gruzovye_avtomobili__tehnika/";
        Document doc = Jsoup.connect(str).get();
        Element body = doc.body();//get body
        Elements details = body.getElementsByClass("details");
        int sizeList = details.size();
        CompanyData[] companyDataArr = new CompanyData[sizeList];
        int i = 0;
        for(Element detail: details){
            companyDataArr[i] = new CompanyData();
            Elements elements = getElements(detail, "blue-link");
            String dataStr = processElements(elements, "blue-link");
            writeData(dataStr, companyDataArr[i],"blue-link");
            elements = getElements(detail, "tags_view");
            for(Element element: elements){
                Elements elements1 = element.getElementsByTag("b");
                String dataStr1 = processElements(elements1, "b");
                writeData(dataStr1, companyDataArr[i],"b");
            }
            dataStr = processElements(elements, "tags_view");
            writeData(dataStr, companyDataArr[i],"tags_view");
            elements = getElements(detail, "address-info-text");
            dataStr = processElements(elements, "address-info-text");
            writeData(dataStr, companyDataArr[i],"address-info-text");
            elements = getElements(detail, "phone-item");
            writeData(getTelephones(elements), companyDataArr[i]);
            for(Element element: getElements(detail, "blue-link")){
                writeData(getUrlLinks(element), companyDataArr[i],"url-links");
            }
            i++;
        }
        writeStringToFile(companyDataArr);
    }
}

