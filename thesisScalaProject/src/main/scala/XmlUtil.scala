import java.io.ByteArrayInputStream

import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.xpath.XPathFactory
import org.w3c.dom.{Document, Node}

import scala.language.postfixOps

import java.io.StringWriter

import javax.xml.transform.{TransformerException, TransformerFactory}
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.stream.StreamResult
import javax.xml.xpath.XPathConstants
import org.w3c.dom.NodeList

object XmlUtil {
  def parseXmlFromString(xmlString: String): Document = {
    val dbFactory = DocumentBuilderFactory.newInstance
    val dBuilder = dbFactory.newDocumentBuilder
    val doc = dBuilder.parse(new ByteArrayInputStream(xmlString.getBytes()))
    doc.getDocumentElement.normalize()
    doc
  }

  def xpathGetNode(doc: Document, xpathQuery: String): Option[Node] = {

    val xPathfactory = XPathFactory.newInstance
    val xpath = xPathfactory.newXPath
    //xpath.setNamespaceContext(ctx)
    val expr = xpath.compile(xpathQuery)
    val nl = expr.evaluate(doc, XPathConstants.NODESET).asInstanceOf[NodeList]
    if (nl.getLength > 0)
      Some(nl.item(0))
    else
      None
  }

  def documentToString(doc: Document): String = {
    val tf = TransformerFactory.newInstance
    var transformer: javax.xml.transform.Transformer = null
    try {
      transformer = tf.newTransformer
      // below code to remove XML declaration
      // transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
      val writer = new StringWriter
      transformer.transform(new DOMSource(doc), new StreamResult(writer))
      val output = writer.getBuffer.toString
      return output
    } catch {
      case e: TransformerException =>
        e.printStackTrace()
    }
    null
  }
}