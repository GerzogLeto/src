
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
            i++;
        }
        for(CompanyData point: companyDataArr){
            System.out.println(point.getNameRubrika());
            System.out.println(point.getNameCompany());
            System.out.println(point.getAboutCompany());
            System.out.println(point.getAdress());
            for(String str2: point.getNumberPhones()){
                System.out.print(str2 + " -###- ");
            }
            System.out.println();
        }
    }
}

