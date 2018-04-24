package Main

import java.awt._
import javax.swing._
import java.io.File
import javax.imageio.ImageIO

object GamePanel extends JPanel with Runnable {

  val thread = new Thread(this)
  var running = true
  var mouse = new Point(0, 0)
  var time = 0
  val spawntime = 600
  var killed = 0
  var monsterNumber = 0
  var level = 1
  val maxLevel = 2
  var won = false

  val map1 = new File("maps/map1.txt")
  val map2 = new File("maps/map2.txt")

  Map.createMap
  Load.load(map1)
  Shop.addButton

  var monsters = Array.ofDim[Monster](monsterNumber)

  def monsterAdder = { //Adds monsters to the array
    for (i <- 0 until monsters.size) {
      monsters(i) = new Monster
    }
  }

  def spawner = {
    for (i <- 0 until monsters.size) {
      if (time > spawntime) {
        if (!monsters(i).inGame && !monsters(i).hasSpawned) {
          monsters(i).spawn
          time = 0
        }
      } else {
        time += 1
      }
    }
  }

  def monsterMove = {
    for (i <- 0 until monsters.size) {
      if (monsters(i).inGame) {
        monsters(i).move
      }
    }
  }

  def hasEnded = {
    if (killed >= monsters.size) {
      killed = 0
      won = true
    }
  }

  override def paint(g: Graphics) = {
    g.setColor(new Color(128, 128, 128))
    g.fillRect(0, 0, getWidth, getHeight) //Grey background
    Map.draw(g) //Draws the map
    Shop.draw(g) //Draws the GUI
    for (i <- 0 until monsters.size) {
      if (monsters(i).inGame) {
        monsters(i).draw(g)
      }
    }
    if (!Player.isAlive) {
      val gameOver = ImageIO.read(new File("resource/gameover.png"))
      g.drawImage(gameOver, 0, 0, getWidth, getHeight, null)
    }
    if (won && level == maxLevel) {
      val victory = ImageIO.read(new File("resource/win.png"))
      g.drawImage(victory, 0, 0, getWidth, getHeight, null)
    }
  }

  monsterAdder

  def run = { //Game loop
    var initialTime = System.nanoTime
    val ticks = 60.0
    val ns = 1000000000 / ticks
    var delta = 0.0
    var updates = 0.0
    var frames = 0.0
    var timer = System.currentTimeMillis

    while (running) {
      var now = System.nanoTime
      delta += (now - initialTime) / ns
      initialTime = now
      if (delta >= 1) {
        if (Player.lives > 0 && !won) {
          monsterMove
          spawner
          Map.monsterFight
        } else if (won) {
          if (level < maxLevel) { //Resets player's money and adds monsters when the second map is loaded
            level += 1
            won = false
            Player.money = 100
            Load.load(map2)
            monsterAdder
            Shop.holdsItem = false
          }
        }
        updates += 1
        delta -= 1
      }
      frames += 1
      if (System.currentTimeMillis - timer > 1000) {
        timer += 1000
        println(updates + " ticks, " + frames + " fps") //Prints ticks and fps to test that the game is running
        updates = 0
      }
      repaint()
    }
  }

  addMouseListener(new Mouse)
  addMouseMotionListener(new Mouse)

  thread.start

}