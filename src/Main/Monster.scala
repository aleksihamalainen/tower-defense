package Main

import java.awt._
import javax.imageio.ImageIO
import java.io.File

class Monster extends Rectangle {

  val monsterSize = Map.blockSize
  var inGame = false
  var hasSpawned = false
  val monsterImage = ImageIO.read(new File("resource/monster.png"))
  var up = 0
  var down = 1
  var right = 2
  var left = 3
  var direction = right
  var walk = 0
  var xCoord = 0
  var yCoord = 0
  val maxHealth = 100
  var currentHealth = 100

  def spawn = {
    for (y <- 0 until Map.blocks.size) {
      if (Map.blocks(y)(0).roadID == Map.road) {
        setBounds(Map.blocks(y)(0).xC, Map.blocks(y)(0).yC, monsterSize, monsterSize)
        xCoord = 0
        yCoord = y
      }
    }
    inGame = true
    hasSpawned = true
  }
  
  def deleteMonster = {
    inGame = false
  }

  def move {
    var hasUp = false
    var hasDown = false
    var hasLeft = false
    var hasRight = false
    if (direction == right) {
      x += 1
    } else if (direction == up) {
      y -= 1
    } else if (direction == down) {
      y += 1
    } else if (direction == left) {
      x -= 1
    }

    walk += 1

    if (walk == Map.blockSize) {
      if (direction == right) {
        xCoord += 1
        hasRight = true
      } else if (direction == up) {
        yCoord -= 1
        hasUp = true
      } else if (direction == down) {
        yCoord += 1
        hasDown = true
      } else if (direction == left) {
        xCoord -= 1
        hasLeft = true
      }

      if (!hasUp) {
        if (Map.blocks(yCoord)(xCoord).roadID == Map.road) {
          direction = down
        }
      }
      if (!hasDown) {
        if (Map.blocks(yCoord - 1)(xCoord).roadID == Map.road) {
          direction = up
        }
      }
      if (!hasLeft) {
        if (Map.blocks(yCoord)(xCoord + 1).roadID == Map.road) {
          direction = right
        }
      }
      if (!hasRight) {
        if (Map.blocks(yCoord)(xCoord - 1).roadID == Map.road) {
          direction = left
        }
      }

      if (Map.blocks(yCoord)(xCoord + 1).grassID == Map.roadEnd) {
        deleteMonster
        Player.lives -= 1
      }

      hasUp = false
      hasDown = false
      hasLeft = false
      hasRight = false
      walk = 0
    }
  }
  
  def isDead = {
    if (inGame == true) {
      false
    } else {
      true
    }
  }
  
  def loseHealth = {
    currentHealth -= 1
    if (currentHealth <= 0) {
      deleteMonster
    }
  }

  def draw(g: Graphics) = {
    val healthHeight = 5
    val healthSpace = 2
    g.drawImage(monsterImage, x, y, width, height, null)
    g.setColor(new Color(180, 50, 50))
    g.fillRect(x, y - healthSpace - healthHeight, monsterSize, healthHeight)
    g.setColor(new Color(50, 180, 50))
    g.fillRect(x, y - healthSpace - healthHeight, monsterSize * currentHealth / maxHealth, healthHeight)
    g.setColor(new Color(0, 0, 0))
    g.drawRect(x, y - healthSpace - healthHeight, monsterSize, healthHeight)
  }

}