package Main

import java.awt._
import javax.imageio.ImageIO
import java.io.File

class Block(var xC: Int, var yC: Int, width: Int, height: Int, var roadID: Int, var grassID: Int) extends Rectangle {

  val grassImage = ImageIO.read(new File("resource/grass.png"))
  val roadImage = ImageIO.read(new File("resource/road.png"))
  val endImage = ImageIO.read(new File("resource/hole.png"))
  val towerSquareSize = 100 //Changes the shooting distance
  var target = -1
  var shooting = false
  val towerSquare = new Rectangle(xC - towerSquareSize / 2, yC - towerSquareSize / 2, width + towerSquareSize, height + towerSquareSize) //Shooting distance
  setBounds(xC, yC, width, height)

  def draw(g: Graphics) {
    if (grassID == Map.roadEnd) {
      g.drawImage(endImage, xC, yC, width, height, null)
    } else if (grassID == Map.tower1) {
      g.drawImage(grassImage, xC, yC, width, height, null)
      g.drawImage(Shop.towerImage1, xC, yC, width, height, null)
    } else if (grassID == Map.tower2) {
      g.drawImage(grassImage, xC, yC, width, height, null)
      g.drawImage(Shop.towerImage2, xC, yC, width, height, null)
    } else if (grassID == Map.tower3) {
      g.drawImage(grassImage, xC, yC, width, height, null)
      g.drawImage(Shop.towerImage3, xC, yC, width, height, null)
    } else if (grassID == Map.tower4) {
      g.drawImage(grassImage, xC, yC, width, height, null)
      g.drawImage(Shop.towerImage4, xC, yC, width, height, null)
    } else if (roadID == Map.road) {
      g.drawImage(roadImage, xC, yC, width, height, null)
    } else if (grassID == Map.grass) {
      g.drawImage(grassImage, xC, yC, width, height, null)
    }
  }

  def fight = {
    if (target != -1 && towerSquare.intersects(GamePanel.monsters(target))){ //Shooting stops when monster leaves towerSquare
      shooting = true
    } else {
      shooting = false
    }
    if (!shooting) {
      if (grassID == Map.tower1 || grassID == Map.tower2 || grassID == Map.tower2 || grassID == Map.tower3 || grassID == Map.tower4) {
        for (i <- 0 until GamePanel.monsters.size) {
          if (GamePanel.monsters(i).inGame) {
            if (towerSquare.intersects(GamePanel.monsters(i))) { //Monster is shot when it intersects towerSquare
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
        GamePanel.killed += 1
        Shop.getMoney
        shooting = false
        target = -1
        GamePanel.hasEnded
      }
    }
  }

  def shoot(g: Graphics) { //Removing comments makes you see the shooting distance
    if (grassID == Map.tower1 || grassID == Map.tower2 || grassID == Map.tower2 || grassID == Map.tower3 || grassID == Map.tower4) {
      g.setColor(new Color(255, 20, 147))
      g.drawRect(towerSquare.x, towerSquare.y, towerSquare.width, towerSquare.height)
    }
    if (shooting) { //Draws line from tower to enemy depending on tower's color
      if (grassID == Map.tower1) {
        g.setColor(new Color(0, 162, 232))
        g.drawLine(xC + width / 2, yC + height / 2, GamePanel.monsters(target).x + GamePanel.monsters(target).width / 2, GamePanel.monsters(target).y + GamePanel.monsters(target).height / 2)
      } else if (grassID == Map.tower2) {
        g.setColor(new Color(255, 128, 255))
        g.drawLine(xC + width / 2, yC + height / 2, GamePanel.monsters(target).x + GamePanel.monsters(target).width / 2, GamePanel.monsters(target).y + GamePanel.monsters(target).height / 2)
      } else if (grassID == Map.tower3) {
        g.setColor(new Color(63, 72, 204))
        g.drawLine(xC + width / 2, yC + height / 2, GamePanel.monsters(target).x + GamePanel.monsters(target).width / 2, GamePanel.monsters(target).y + GamePanel.monsters(target).height / 2)
      } else if (grassID == Map.tower4) {
        g.setColor(new Color(237, 28, 36))
        g.drawLine(xC + width / 2, yC + height / 2, GamePanel.monsters(target).x + GamePanel.monsters(target).width / 2, GamePanel.monsters(target).y + GamePanel.monsters(target).height / 2)
      }
    }
  }

}