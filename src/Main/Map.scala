package Main

import java.awt._
import scala.io._
import scala.collection.mutable.Buffer

object Map {
  
  val width = 15
  val height = 10
  val blockSize = 52
  
  val blocks = Array.ofDim[Block](width, height)
  
  //val lines = Source.fromFile("resource/map.txt").getLines()
  
  /*def loadMap = {
    for (line <- lines) {
      
    }
  }*/
  
  def createMap = {
    for (x <- 0 until blocks.size) {
      for (y <- 0 until blocks(x).size) {
        blocks(x)(y) = new Block(x*blockSize, y*blockSize, blockSize, blockSize)
      }
    }
  }
  
  def draw(g: Graphics) {
    for (x <- 0 until blocks.size) {
      for (y <- 0 until blocks(x).size) {
        blocks(x)(y).draw(g)
      }
    }
  }
  
}