
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
        }

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
            i++;
        }
        for(CompanyData point: companyDataArr){
            System.out.println(point.getNameRubrika());
            System.out.println(point.getNameCompany());
            System.out.println(point.getAboutCompany());
        }
    }
}

