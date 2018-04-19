package Main

import java.awt._
import javax.imageio.ImageIO
import java.io.File

class Block(var x: Int, var y: Int, width: Int, height: Int, var roadID: Int, var grassID: Int) {

  val grassImage = ImageIO.read(new File("resource/grass.png"))
  val roadImage = ImageIO.read(new File("resource/road.png"))
  val endImage = ImageIO.read(new File("resource/cave.png"))

  def draw(g: Graphics) {
    if (grassID == Map.roadEnd) {
      g.drawImage(endImage, x, y, width, height, null)
    } else if (roadID == Map.road) {
      g.drawImage(roadImage, x, y, width, height, null)
    } else if (grassID == Map.grass) {
      g.drawImage(grassImage, x, y, width, height, null)
    }
  }

}