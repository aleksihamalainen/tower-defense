package Main

import java.awt._
import scala.io._

object Map {

  val width = 16
  val height = 10
  val blockSize = 50
  val road = 1
  val grass = 0
  val roadEnd = 2

  val blocks = Array.ofDim[Block](height, width)

  def createMap = {
    for (y <- 0 until blocks.size) {
      for (x <- 0 until blocks(y).size) {
        blocks(y)(x) = new Block(x*blockSize, y*blockSize, blockSize, blockSize, 0, 0)
      }
    }
  }

  def draw(g: Graphics) {
    for (y <- 0 until blocks.size) {
      for (x <- 0 until blocks(y).size) {
        blocks(y)(x).draw(g)
      }
    }
  }

}