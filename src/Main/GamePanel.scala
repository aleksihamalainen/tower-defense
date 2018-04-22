package Main

import java.awt._
import javax.swing._
import java.io.File

object GamePanel extends JPanel with Runnable {

  val thread = new Thread(this)
  var running = true
  val leveys = getWidth
  val korkeus = getHeight
  var mouse = new Point(0, 0)
  var time = 0
  val spawntime = 600

  val map = new File("resource/map.txt")

  Map.createMap
  Load.load(map)
  Shop.addButton

  var monsters = Array.ofDim[Monster](10)

  def monsterAdder = {
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
  
  def gameOver = {
    if (!Player.isAlive) {
      println("kuoli saatana")
      System.exit(0)
    }
  }

  override def paint(g: Graphics) = {
    //g.setColor(new Color(220, 220, 220))
    //g.fillRect(0, 0, getWidth, getHeight)
    Map.draw(g) //Draws the map
    Shop.draw(g)
    for (i <- 0 until monsters.size) {
      if (monsters(i).inGame) {
        monsters(i).draw(g)
      }
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
        updates += 1
        delta -= 1
        monsterMove
        spawner
        Map.monsterFight
      }
      frames += 1
      if (System.currentTimeMillis - timer > 1000) {
        timer += 1000
        //println(updates + " ticks, " + frames + " fps") //Prints ticks and fps to test that the game is running
        updates = 0
        gameOver
      }
      repaint()
    }
  }

  addKeyListener(new Mouse)
  addMouseListener(new Mouse)
  addMouseMotionListener(new Mouse)
  thread.start

}