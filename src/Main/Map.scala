package Main

import java.awt._
import scala.io._

object Map {

  val width = 16
  val height = 10
  val blockSize = 50
  
  val grass = 0
  val road = 1
  val roadEnd = 2
  val trash = 3
  val tower1 = 4
  val tower2 = 5
  val tower3 = 6
  val tower4 = 7

  val blocks = Array.ofDim[Block](height, width)

  def createMap = {
    for (y <- 0 until blocks.size) {
      for (x <- 0 until blocks(y).size) {
        blocks(y)(x) = new Block(x * blockSize, y * blockSize, blockSize, blockSize, 0, 0)
      }
    }
  }
  
  def monsterFight = {
    for (y <- 0 until blocks.size) {
      for (x <- 0 until blocks(y).size) {
        blocks(y)(x).fight
      }
    }
  }

  def draw(g: Graphics) { //Draws monsters and the shooting lines
    for (y <- 0 until blocks.size) {
      for (x <- 0 until blocks(y).size) {
        blocks(y)(x).draw(g)
      }
    }
    for (y <- 0 until blocks.size) {
      for (x <- 0 until blocks(y).size) {
        blocks(y)(x).shoot(g)
      }
    }
  }

}