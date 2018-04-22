package Main

import java.awt.Color
import java.awt.Font
import java.awt.Graphics
import java.awt.Rectangle

object Shop {

  val blockNumber = 5
  val buttons = Array.ofDim[Rectangle](blockNumber)
  val buttonID = Array(Map.tower, 0, 0, 0, Map.trash)
  val prices = Array(50, 0, 0, 0, 0)
  var holdsItem = false
  var heldID = -1
  var realID = -1
  val buttonSize = 58
  val iconSize = 35
  val health = new Rectangle(Map.blocks(0)(1).xC, Map.blocks(9)(0).yC + 50, iconSize, iconSize)
  val coins = new Rectangle(Map.blocks(0)(1).xC, Map.blocks(9)(0).yC + 90, iconSize, iconSize)
  val monsu = new Monster

  def addButton = {
    for (i <- 0 until buttons.size) {
      buttons(i) = new Rectangle(65 * i + 225, Map.blocks(Map.height - 1)(0).yC + Map.blockSize + 8, buttonSize, buttonSize)
    }
  }

  def click(mouseButton: Int) = {
    if (mouseButton == 1) {
      for (i <- 0 until buttons.size) {
        if (buttons(i).contains(GamePanel.mouse)) {
          if (buttonID(i) == Map.trash) { //Delete tower
            holdsItem = false
          } else {
            heldID = buttonID(i)
            realID = i
            holdsItem = true
          }
        }
      }
      if (holdsItem) {
        if (Player.money >= prices(realID)) {
        for (y <- 0 until Map.blocks.size) {
          for (x <- 0 until Map.blocks(y).size) {
            if (Map.blocks(y)(x).contains(GamePanel.mouse)) {
            if (Map.blocks(y)(x).grassID == Map.grass && Map.blocks(y)(x).roadID != Map.road) {
              Map.blocks(y)(x).grassID = heldID
              Player.money -= prices(realID)
            }
            }
          }
          }
        }
      }
    }
  }
  
  def getMoney = {
    Player.money += 10
  }

  def draw(g: Graphics) = {
    for (i <- 0 until buttons.size) {
      if (buttons(i).contains(GamePanel.mouse)) {
        g.setColor(new Color(255, 255, 255, 150))
        g.fillRect(buttons(i).x, buttons(i).y, buttons(i).width, buttons(i).height)
      }
      g.drawImage(monsu.monsterImage, buttons(i).x, buttons(i).y, buttons(i).width, buttons(i).height, null)
    }
    if (holdsItem) {
      g.drawImage(monsu.monsterImage, GamePanel.mouse.x, GamePanel.mouse.y, buttons(0).width, buttons(0).height, null)
    }
    g.drawImage(monsu.monsterImage, health.x, health.y, health.width, health.height, null)
    g.drawImage(monsu.monsterImage, coins.x, coins.y, coins.width, coins.height, null)
    g.setColor(new Color(0, 0, 0))
    g.setFont(new Font("Serif Plain", Font.BOLD, 20))
    g.drawString("" + Player.lives, health.x + health.width + 18, health.y + 20)
    g.drawString("" + Player.money, coins.x + coins.width + 18, coins.y + 20)
  }

}