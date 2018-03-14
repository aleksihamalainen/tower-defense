package Main

import java.awt._
import javax.imageio.ImageIO
import java.io.File

class Block(x: Int, y: Int, width: Int, height: Int) {
  
  //val grassImage = ImageIO.read(new File("resource/grass.png"))
  //val roadImage = ImageIO.read(new File("resource/road.png"))
  
  def draw(g: Graphics) {
    g.drawRect(x, y, width, height)
  }
  
}