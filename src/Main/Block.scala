package Main

import java.awt._
import javax.imageio.ImageIO
import java.io.File

class Block(var xC: Int, var yC: Int, width: Int, height: Int, var roadID: Int, var grassID: Int) extends Rectangle {

  val grassImage = ImageIO.read(new File("resource/grass.png"))
  val roadImage = ImageIO.read(new File("resource/road.png"))
  val endImage = ImageIO.read(new File("resource/cave.png"))
  val towerSquareSize = 100
  var target = -1
  var shooting = false
  val towerSquare = new Rectangle(xC - towerSquareSize / 2, yC - towerSquareSize / 2, width + towerSquareSize, height + towerSquareSize)
  setBounds(xC, yC, width, height)

  val monsteri = new Monster

  def draw(g: Graphics) {
    if (grassID == Map.roadEnd) {
      g.drawImage(endImage, xC, yC, width, height, null)
    } else if (grassID == Map.tower) {
      g.drawImage(monsteri.monsterImage, xC, yC, width, height, null)
    } else if (roadID == Map.road) {
      g.drawImage(roadImage, xC, yC, width, height, null)
    } else if (grassID == Map.grass) {
      g.drawImage(grassImage, xC, yC, width, height, null)
    }
  }

  def fight = {
    if (target != -1 && towerSquare.intersects(GamePanel.monsters(target))) {
      shooting = true
    } else {
      shooting = false
    }
    if (!shooting) {
      if (grassID == Map.tower) {
        for (i <- 0 until GamePanel.monsters.size) {
          if (GamePanel.monsters(i).inGame) {
            if (towerSquare.intersects(GamePanel.monsters(i))) {
              shooting = true
              target = i
            }
          }
        }
      }
    }
    if (shooting) {
      GamePanel.monsters(target).loseHealth
      if (GamePanel.monsters(target).isDead) {
        Shop.getMoney
        shooting = false
        target = -1
      }
    }
  }

  def shoot(g: Graphics) {
    if (grassID == Map.tower) {
      g.drawRect(towerSquare.x, towerSquare.y, towerSquare.width, towerSquare.height)
    }
    if (shooting) {
      g.drawLine(xC + width / 2, yC + height / 2, GamePanel.monsters(target).x + GamePanel.monsters(target).width / 2, GamePanel.monsters(target).y + GamePanel.monsters(target).height / 2)
    }
  }

}