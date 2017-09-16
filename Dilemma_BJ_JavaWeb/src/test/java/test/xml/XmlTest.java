package test.xml;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class XmlTest {

	@Test
	public void test() {
		try {
			String result = "<?xml version=\"1.0\" encoding=\"GBK\" ?><alipay><is_success>T</is_success></alipay>";
			DocumentBuilderFactory fac = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = fac.newDocumentBuilder();
			Document doc = builder.parse(new InputSource(new StringReader(result)));
			Element root = doc.getDocumentElement();
			NodeList nodeList = root.getChildNodes();

			HashMap<String, String> map = new HashMap<String, String>();
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i);
				map.put(node.getNodeName(), node.getFirstChild().getNodeValue());
			}

		} catch (ParserConfigurationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void test2() {
		try {
			String result = "<?xml version=\"1.0\" encoding=\"GBK\"?><alipay><is_success>F</is_success><error>ILLEGAL_SIGN</error></alipay>";
			DocumentBuilderFactory fac = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = fac.newDocumentBuilder();
			Document doc = builder.parse(new InputSource(new StringReader(result)));
			Element root = doc.getDocumentElement();
			NodeList nodeList = root.getChildNodes();
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i);
				System.out.println("Node " + node.getNodeName() + " : " + node.getNodeValue() + " , first child: " + node.getFirstChild().getNodeValue());
			}

		} catch (ParserConfigurationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
