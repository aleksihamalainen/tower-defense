package Main

import java.awt._
import javax.imageio.ImageIO
import java.io.File

class Monster extends Rectangle {

  val monsterSize = Map.blockSize
  var inGame = false
  val monsterImage = ImageIO.read(new File("resource/monster.png"))
  var up = 0
  var down = 1
  var right = 2
  var left = 3
  var direction = right
  var walk = 0
  var xCoord = 0
  var yCoord = 0

  def spawn = {
    for (y <- 0 until Map.blocks.size) {
      if (Map.blocks(y)(0).roadID == Map.road) {
        setBounds(Map.blocks(y)(0).x, Map.blocks(y)(0).y, monsterSize, monsterSize)
        xCoord = 0
        yCoord = y
      }
    }
    inGame = true
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
        inGame = false
      }

      hasUp = false
      hasDown = false
      hasLeft = false
      hasRight = false
      walk = 0
    }
  }

  def draw(g: Graphics) = {
    g.drawImage(monsterImage, x, y, width, height, null)
  }

}