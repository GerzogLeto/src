public class Main {
    public static void main(String[] args) throws Exception {
        GetDocumentFromURL getDocumentFromURL = new GetDocumentFromURL();
        getDocumentFromURL.getDataAndWrite("https://moscow.big-book-avto.ru/gruzovye_avtomobili__tehnika/");
        for(int i=2; i<34; i++){
            getDocumentFromURL.getDataAndWrite("https://moscow.big-book-avto.ru/gruzovye_avtomobili__tehnika/" +
                                                    "?page=" + i);
            Thread.sleep(100);
        }
        getDocumentFromURL.getDataAndWrite("https://moscow.big-book-avto.ru/remont-i-obsluzhivanie-gruzovyh-avtomobiley/");
        for(int i=2; i<48; i++){
            getDocumentFromURL.getDataAndWrite("https://moscow.big-book-avto.ru/remont-i-obsluzhivanie-gruzovyh-avtomobiley/" +
                    "?page=" + i);
            Thread.sleep(100);
        }
        getDocumentFromURL.getDataAndWrite("https://moscow.big-book-avto.ru/gazoballonnoe_oborudovanie/");
        for(int i=2; i<7; i++){
            getDocumentFromURL.getDataAndWrite("https://moscow.big-book-avto.ru/gazoballonnoe_oborudovanie/" +
                    "?page=" + i);
            Thread.sleep(100);
        }
    }
}
