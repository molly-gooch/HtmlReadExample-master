import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class HtmlRead {

    private JPanel panel;
    private JFrame mainframe;


    public static void main(String[] args) {
        HtmlRead html = new HtmlRead();
    }

    public HtmlRead() {


        try {
            URL url = new URL("https://www.milton.edu/");
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(url.openStream())
            );
            String line;

            while ( (line = reader.readLine()) != null ) {
                if(line.contains("href")) {

                    int indexHref = line.indexOf("href") + 6;
                    String newLine = line.substring(indexHref);


                    int end = newLine.indexOf("\"");
                    int oEnd = newLine.indexOf("'");

                        if(end<oEnd && end>-1 || oEnd ==-1){
                            System.out.println(newLine.substring(0, end));
                        }else if(oEnd>-1) {
                            System.out.println(newLine.substring(0, oEnd));
                        }

                }
            }
            reader.close();
        } catch(Exception ex) {
            System.out.println(ex);
        }

    }

}
