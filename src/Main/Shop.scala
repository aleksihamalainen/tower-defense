package Main

import java.awt.Graphics
import java.awt.Rectangle

object Shop {

  val blockNumber = 5
  val buttons = Array.ofDim[Rectangle](blockNumber)
  val buttonSize = 58
  val space = 3

  def addButton = {
    for (i <- 0 until buttons.size) {
      buttons(i) = new Rectangle(300, Map.blocks(Map.height - 1)(0).y + Map.blockSize + 8, buttonSize, buttonSize)
    }
  }

  def draw(g: Graphics) = {
    for (i <- 0 until buttons.size) {
      g.fillRect(buttons(i).x, buttons(i).y, buttons(i).width, buttons(i).height)
    }
  }

}