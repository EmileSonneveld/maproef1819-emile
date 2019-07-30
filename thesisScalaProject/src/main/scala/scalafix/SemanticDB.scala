package scalafix // We must develop in this package to acces some private variables.

import java.io.File
import java.net.URLClassLoader
import java.nio.file.Path

import org.apache.commons.io.FileUtils
import org.apache.commons.io.filefilter.{DirectoryFileFilter, TrueFileFilter}
import scalafix.internal.v1.InternalSemanticDoc
import scalafix.v1.{SemanticDocument, Symbol, SyntacticDocument}
import scalafix.v1._ // for the symbol magic

import scala.collection.mutable.ListBuffer
import scala.meta.{Defn, Tree}
import scala.meta.inputs.Input
import scala.meta.internal.semanticdb.{Locator, SymbolInformation, TextDocument}
import scala.meta.internal.symtab.GlobalSymbolTable
import scala.meta.io.{AbsolutePath, Classpath}

case class DocumentTuple(path: Path, tdoc: TextDocument, sdoc: SemanticDocument)

class SemanticDB(val projectPath: File) {

  private val classPath = {
    val jars = FileUtils.listFiles(projectPath, Array("jar"), true).toArray(new Array[File](0))
      .toList
      .map(f => Classpath(f.getAbsolutePath))
      .reduceOption(_ ++ _)

    val classes = FileUtils.listFilesAndDirs(projectPath, TrueFileFilter.INSTANCE, DirectoryFileFilter.DIRECTORY).toArray(new Array[File](0))
      .toList
      .filter(p => p.isDirectory && !p.getAbsolutePath.contains(".sbt") && p.getAbsolutePath.contains("target") && p.getAbsolutePath.contains("classes"))
      .map(f => Classpath(f.getAbsolutePath))
      .reduceOption(_ ++ _)

    val classPath = ClassLoader.getSystemClassLoader.asInstanceOf[URLClassLoader].getURLs
      .map(url => Classpath(url.getFile.replace("/C:/", "C:/").replace("%20", " "))) // Maybe more url-decoding is needed here
      .reduceOption(_ ++ _)

    (jars ++ classes ++ classPath).reduceOption(_ ++ _).getOrElse(Classpath(""))
  }

  val symbolTable = GlobalSymbolTable(classPath)

  private val documentsFirstPass: Seq[DocumentTuple] = {
    var documents: Seq[DocumentTuple] = List.empty

    // Locator will find all *.semanticdb files
    // textDocuments.documents contains 1 or 2 elements. When 2, one of them doesnt have a MD5 and will be ignored
    Locator.apply(projectPath.toPath)((path, textDocuments) => {
      val tdocs = textDocuments.documents.filter(!_.md5.isEmpty)
      documents ++= tdocs.map(tdoc => {
        val input = Input.File(AbsolutePath(projectPath).resolve(tdoc.uri))
        val syntacticDoc = SyntacticDocument.fromInput(input)
        val sdoc_internal = new InternalSemanticDoc(syntacticDoc, tdoc, symbolTable)

        val sdoc = new SemanticDocument(sdoc_internal)

        new DocumentTuple(path, tdoc, sdoc)
      })
    })

    documents
  }

  // This kind of adds some more high level semantics to the types.
  private def reload(uri: String): DocumentTuple = {
    val document = documentsFirstPass.find(d => d.tdoc.uri.equals(uri)).get

    val input = Input.File(AbsolutePath(projectPath).resolve(document.tdoc.uri))
    val syntacticDoc = SyntacticDocument.fromInput(input)
    val sdoc_internal = new InternalSemanticDoc(syntacticDoc, document.tdoc, symbolTable)

    val sdoc = new SemanticDocument(sdoc_internal)

    new DocumentTuple(document.path, document.tdoc, sdoc)
  }

  val documents: Seq[DocumentTuple] = {
    var documents: ListBuffer[DocumentTuple] = new ListBuffer()
    for (main_doc <- documentsFirstPass) {
      try {
        var test = main_doc.sdoc.tree // Try this, and don't add document if it throws
        documents += reload(main_doc.tdoc.uri)
      } catch {
        case _ =>
          println("Ignored document that throws when parsed" + main_doc.path)
      }
    }
    documents
  }


  def getInfo(symString: String): SymbolInformation = {
    try {
      var opt = symbolTable.info(symString)
      if (!opt.isDefined) return null
      opt.get
    } catch {
      case _: scala.meta.internal.classpath.MissingSymbolException =>
        // ignore
        null
    }
  }

  /**
    * Heavy lifting
    */
  def getClassTraitObjectTree(uniqueName: String): Tree = {
    for (doc <- this.documents) {
      implicit val implicit_ksjndflidbfkurhgb: SemanticDocument = doc.sdoc
      doc.sdoc.tree.collect {

        case clazz: Defn.Class => {
          var clazzSymbol = clazz.symbol
          //var symInfo = this.symbolTable.info(clazzSymbol.value).get
          if (clazzSymbol.value == uniqueName) {
            return clazz
          }
        }
        case clazz: Defn.Object => {
          var clazzSymbol = clazz.symbol
          //var symInfo = this.symbolTable.info(clazzSymbol.value).get
          if (clazzSymbol.value == uniqueName) {
            return clazz
          }
        }
        case clazz: Defn.Trait => {
          var clazzSymbol = clazz.symbol
          //var symInfo = this.symbolTable.info(clazzSymbol.value).get
          if (clazzSymbol.value == uniqueName) {
            return clazz
          }
        }
      }
    }
    return null
  }
}

object SemanticDB {

  /**
    * Proxy to avoid namespace issue
    */
  def getFromSymbolTable(tree: Tree, sdoc: SemanticDocument): Symbol = {
    sdoc.internal.symbol(tree)
  }
}