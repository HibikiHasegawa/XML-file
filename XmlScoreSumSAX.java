import java.io.FileInputStream;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class XmlScoreSumSAX {
    public static void main(String args[]) throws Exception {
        // SAX の準備
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = saxParserFactory.newSAXParser();

        // ハンドラを作成
        ScoreHandler scoreHandler = new ScoreHandler();

        // 文書を読み込む
        saxParser.parse(new FileInputStream("13.input.xml"), scoreHandler);

        System.out.println("Total Scores = " + scoreHandler.totalSum);
    }
}

// ハンドラクラス
class ScoreHandler extends DefaultHandler {
    private int scoreDepth = 0;
    public int totalSum = 0;

    // 要素の開始
    public void startElement(String namespaceURI, String localName, String qName, Attributes attrs) {
        if ("score".equals(qName)) {
            scoreDepth++;
        }
    }

    // 要素の終了
    public void endElement(String namespaceURI, String localName, String qName) {
        if ("score".equals(qName)) {
            scoreDepth--;
        }
    }

    // 文字データ
    public void characters(char[] ch, int start, int length) {
        String str = new String(ch, start, length);
        if (str.trim().length() != 0 && scoreDepth > 0) {
            totalSum += Integer.parseInt(str);
        }
    }
}
