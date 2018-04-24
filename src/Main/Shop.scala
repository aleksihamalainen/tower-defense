package Main

import java.awt.Color
import java.awt.Font
import java.awt.Graphics
import java.awt.Rectangle
import javax.imageio.ImageIO
import java.io.File

object Shop {

  val blockNumber = 5
  val buttons = Array.ofDim[Rectangle](blockNumber)
  val buttonID = Array(Map.tower1, Map.tower2, Map.tower3, Map.tower4, Map.trash) //Items in the GUI and their prices are in different arrays with same indices
  val prices = Array(50, 100, 150, 200, 0)
  var holdsItem = false
  var heldID = -1
  var realID = -1
  val buttonSize = 58
  val iconSize = 35
  val health = new Rectangle(Map.blocks(0)(1).xC + 5, Map.blocks(9)(0).yC + 55, iconSize, iconSize)
  val coins = new Rectangle(Map.blocks(0)(1).xC, Map.blocks(9)(0).yC + 90, iconSize, iconSize)
  val coinImage = ImageIO.read(new File("resource/coin.png"))
  val heartImage = ImageIO.read(new File("resource/heart.png"))
  val trashImage = ImageIO.read(new File("resource/trash.png"))
  val towerImage1 = ImageIO.read(new File("resource/blue.png"))
  val towerImage2 = ImageIO.read(new File("resource/pink.png"))
  val towerImage3 = ImageIO.read(new File("resource/darkblue.png"))
  val towerImage4 = ImageIO.read(new File("resource/red.png"))

  def addButton = {
    for (i <- 0 until buttons.size) {
      buttons(i) = new Rectangle(65 * i + 225, Map.blocks(Map.height - 1)(0).yC + Map.blockSize + 18, buttonSize, buttonSize)
    }
  }

  def click(mouseButton: Int) = {
    if (mouseButton == 1) {
      for (i <- 0 until buttons.size) {
        if (buttons(i).contains(GamePanel.mouse)) {
          if (buttonID(i) == Map.trash) { //Deletes tower
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
                if (Map.blocks(y)(x).grassID == Map.grass && Map.blocks(y)(x).roadID != Map.road) { //Towers can't be placed on the road
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
    Player.money += 50 //The amount of money the player gets when an enemy is killed
  }

  def draw(g: Graphics) = {
    for (i <- 0 until buttons.size) { //Makes nice effect when cursor is on top of a button
      if (buttons(i).contains(GamePanel.mouse)) {
        g.setColor(new Color(255, 255, 255, 150))
        g.fillRect(buttons(i).x, buttons(i).y, buttons(i).width, buttons(i).height)
      }
      if (prices(i) > 0 ) { //Writes prices of the towers to the GUI
        g.setColor(new Color(255, 255, 255))
        g.setFont(new Font("Serif Plain", Font.BOLD, 14))
        g.drawString("$" + prices(i), buttons(i).x, buttons(i).y - 3)
      }
      if (buttonID(i) == Map.trash) { //Draws towers and the trash can to the GUI
        g.drawImage(trashImage, buttons(i).x, buttons(i).y, buttons(i).width, buttons(i).height, null)
     } else if (buttonID(i) == Map.tower1) {
        g.drawImage(towerImage1, buttons(i).x, buttons(i).y, buttons(i).width, buttons(i).height, null)
      } else if (buttonID(i) == Map.tower2) {
        g.drawImage(towerImage2, buttons(i).x, buttons(i).y, buttons(i).width, buttons(i).height, null)
      } else if (buttonID(i) == Map.tower3) {
        g.drawImage(towerImage3, buttons(i).x, buttons(i).y, buttons(i).width, buttons(i).height, null)
      } else if (buttonID(i) == Map.tower4) {
        g.drawImage(towerImage4, buttons(i).x, buttons(i).y, buttons(i).width, buttons(i).height, null)
      }
    }
    if (holdsItem) { //Draws item to the cursor when button is clicked
      if (heldID == Map.tower1) {
        g.drawImage(towerImage1, GamePanel.mouse.x, GamePanel.mouse.y, buttons(0).width, buttons(0).height, null)
      } else if (heldID == Map.tower2) {
        g.drawImage(towerImage2, GamePanel.mouse.x, GamePanel.mouse.y, buttons(0).width, buttons(0).height, null)
      } else if (heldID == Map.tower3) {
        g.drawImage(towerImage3, GamePanel.mouse.x, GamePanel.mouse.y, buttons(0).width, buttons(0).height, null)
      } else if (heldID == Map.tower4) {
        g.drawImage(towerImage4, GamePanel.mouse.x, GamePanel.mouse.y, buttons(0).width, buttons(0).height, null)
      }
    }
    g.drawImage(heartImage, health.x, health.y, health.width - 8, health.height - 8, null)
    g.drawImage(coinImage, coins.x, coins.y, coins.width, coins.height, null)
    g.setColor(new Color(255, 255, 255))
    g.setFont(new Font("Serif Plain", Font.BOLD, 20))
    g.drawString("" + Player.lives, health.x + health.width + 18, health.y + 20)
    g.drawString("$" + Player.money, coins.x + coins.width + 18, coins.y + 20)
  }

}