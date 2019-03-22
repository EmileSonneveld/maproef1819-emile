package scalafix

import java.io.File
import java.net.URLClassLoader
import java.nio.file.Path

import org.apache.commons.io.FileUtils
import org.apache.commons.io.filefilter.{DirectoryFileFilter, TrueFileFilter}
import scalafix.internal.v1.InternalSemanticDoc
import scalafix.v1.{SemanticDocument, SyntacticDocument}

import scala.meta.inputs.Input
import scala.meta.internal.semanticdb.{Locator, TextDocument}
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
      .map(url => Classpath(url.getFile.replace("/C:/", "C:/")))
      .reduceOption(_ ++ _)

    (jars ++ classes ++ classPath).reduceOption(_ ++ _).getOrElse(Classpath(""))
  }

  val symbolTable = GlobalSymbolTable(classPath)

  val documents: Seq[DocumentTuple] = {
    var documents: Seq[DocumentTuple] = List.empty

    // Locator will find all *.semanticdb files
    // textDocuments.documents contains 1 or 2 elements. When 2, one of them doesnt have a MD5. This one is ignored
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

  // Todo: Find out what was this for?
  def reload(uri: String): DocumentTuple = {
    val document = documents.find(d => d.tdoc.uri.equals(uri)).get

    val input = Input.File(AbsolutePath(projectPath).resolve(document.tdoc.uri))
    val syntacticDoc = SyntacticDocument.fromInput(input)
    val sdoc_internal = new InternalSemanticDoc(syntacticDoc, document.tdoc, symbolTable)

    val sdoc = new SemanticDocument(sdoc_internal)

    new DocumentTuple(document.path, document.tdoc, sdoc)
  }
}
