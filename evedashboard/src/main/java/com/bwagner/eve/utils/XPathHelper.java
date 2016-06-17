package com.bwagner.eve.utils;

import java.io.IOException;
import java.io.StringBufferInputStream;
import java.io.StringReader;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.bwagner.eve.domain.EveAPICallResult;

public class XPathHelper {
	
	
	private static DocumentBuilderFactory factory;
	private static DocumentBuilder builder;
	private static XPath helperXPath;
	
	static{
		 try {
				factory = DocumentBuilderFactory.newInstance();
				 builder = factory.newDocumentBuilder();
		
				helperXPath = XPathFactory.newInstance().newXPath();
			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	
	
	/**
	 * <result>
    <key accessMask="134217727" type="Character" expires="">
        <rowset name="characters" key="characterID" columns="characterID,characterName,corporationID,corporationName,allianceID,allianceName,factionID,factionName">
            <row characterID="1655827332" characterName="Hel O'Ween" corporationID="1226284052" corporationName="Men On A Mission" allianceID="0" allianceName="" factionID="0" factionName="" />
        </rowset>
    </key>
</result>
	 * @param apiInfo
	 */
	public static EveAPICallResult parseAPIKeyInfo(String apiInfo){
		EveAPICallResult result = new EveAPICallResult();
		try {
			Document document = builder.parse(new InputSource( new StringReader(apiInfo)));
					
			   Node answerNode = (Node) helperXPath.compile("//result/key").evaluate(document, XPathConstants.NODE);
			   result.setAccessMask(Long.parseLong(answerNode.getAttributes().getNamedItem("accessMask").getNodeValue()));
			   result.setType(answerNode.getAttributes().getNamedItem("type").getNodeValue());
			   //result.setExpires(Date.parse(answerNode.getAttributes().getNamedItem("expires").getNodeValue()));
			   int rhe=2;
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
		   
	}

}
