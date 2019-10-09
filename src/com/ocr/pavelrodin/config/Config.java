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
 * Provides the setup stocked in the config file.
 */
public class Config implements ConfigInterface {

    /**
     * The number of digits in the given combination.
     */
    private int numberOfDigits;

    /**
     * The number of attempts
     */
    private int numberOfAttempts;

    /**
     * If true, displays the solution
     */
    private boolean devMod;

    /**
     * <b>The constructor.</b>
     * Reads the config file and fetchs the essential data.
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
            expression = "//numberofattempts";
            value = (String) path.evaluate(expression, root);
            numberOfAttempts = Integer.parseInt(value);
            expression = "//devmod";
            value = (String) path.evaluate(expression, root);
            devMod = value.equals("true");
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
     * Provides the number of digits in the given value.
     * @return the number of digits in the given value
     */
    public int getNumberOfDigits() {
        return numberOfDigits;
    }

    /**
     * Provides the number of available attempts
     * @return the number of available attempts
     */
    public int getNumberOfAttempts() {
        return numberOfAttempts;
    }

    /**
     * If true, display the solution
     * @return is the developer mode activated
     */
    public boolean isDevMod() {
        return devMod;
    }
}
