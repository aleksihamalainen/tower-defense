package Main

import java.io.File
import java.util.Scanner

object Load {

  def load(path: File) {
    val scanner = new Scanner(path)
    if (path.exists) {
      while (scanner.hasNext) {
        GamePanel.monsterNumber = scanner.nextInt
        for (y <- 0 until Map.blocks.size) {
          for (x <- 0 until Map.blocks(y).size) {
            Map.blocks(y)(x).roadID = scanner.nextInt
          }
        }
        for (y <- 0 until Map.blocks.size) {
          for (x <- 0 until Map.blocks(y).size) {
            Map.blocks(y)(x).grassID = scanner.nextInt
          }
        }
      }
    }
    scanner.close
  }

}