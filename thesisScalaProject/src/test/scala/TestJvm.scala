import java.io.File

import org.scalatest.FunSuite

class TestJvm extends FunSuite {

  // File will always give backslashes, and no trailing slash

  test("file1") {
    var f = new File("C:\\github_download")
    assert(f.toString == "C:\\github_download")
  }
  test("file2") {
    var f = new File("C:\\github_download\\")
    assert(f.toString == "C:\\github_download")
  }
  test("file3") {
    var f = new File("C:/github_download/")
    assert(f.toString == "C:\\github_download")
  }
}
