package com.ocr.pavelrodin.config;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.IOException;

/**
 * Provide the setup stocked in the config file.
 */
public class Config {

    /**
     * The number of digits in the given value.
     */
    private int numberOfDigits;

    /**
     * <b>The constructor.</b>
     * Read the config file and fetch the essential data.
     */
    public Config() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            File fileXML = new File("resources/config/config.xml");
            Document xml = builder.parse(fileXML);
            Element root = xml.getDocumentElement();
            XPathFactory xpf = XPathFactory.newInstance();
            XPath path = xpf.newXPath();

            String expression = "//numberofdigits";
            String value = (String) path.evaluate(expression, root);
            numberOfDigits = Integer.parseInt(value);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
    }

    /**
     * Provide the number of digits in the given value.
     * @return the number of digits in the given value
     */
    public int getNumberOfDigits() {
        return numberOfDigits;
    }
}
