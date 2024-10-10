import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class HtmlRead {

    public static void main(String[] args) {
        HtmlRead html = new HtmlRead();
    }

    public HtmlRead() {

        try {
            System.out.println();
            System.out.print("hello \n");
            URL url = new URL("https://www.milton.edu/");
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(url.openStream())
            );
            String line;

            while ( (line = reader.readLine()) != null ) {
                if((line.contains("http:") || line.contains("www.")) && line.contains("//")) {

                   // System.out.println("og line: " +line);

                    int indexHttp = line.indexOf("http");
                    int indexWWW = line.indexOf("www.");

                    if(indexHttp>-1){
                        String newLine = line.substring(indexHttp);
                        System.out.println(newLine);
                    }
                    if(indexHttp==-1){
                        String newLine = line.substring(indexWWW);
                        System.out.println(newLine);
                    }

                      //  String newLine = line.substring(indexHttp);
                        //this line above is the issue

                       // int indexWWW = line.indexOf("www.");
                      //  newLine = line.substring(indexWWW);



                    //System.out.println(newLine);


                }
            }
            reader.close();
        } catch(Exception ex) {
            System.out.println(ex);
        }

    }

}
