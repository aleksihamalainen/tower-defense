package Main

import java.awt._
import javax.swing._

class GamePanel extends JPanel with Runnable {
  
  val thread = new Thread(this)
  var running = true
  
  Map.createMap
  
  override def paint(g: Graphics) = {
    Map.draw(g) //Draws the map
  }
  
  def run = {    //Game loop
    var initialTime = System.nanoTime()
    val ticks = 60.0
    val ns = 1000000000 / ticks
    var delta = 0.0
    var updates = 0.0
    var frames = 0.0
    var timer = System.currentTimeMillis()
    
    while (running) {
      var now = System.nanoTime()
      delta += (now - initialTime) / ns
      initialTime = now
      if (delta >= 1) {
        updates += 1
        delta -=1
      }
      frames += 1
      if (System.currentTimeMillis() - timer > 1000) {
        timer += 1000
        println(updates + " ticks, " + frames + " fps") //Prints ticks and fps to test that the game is running
        updates = 0
        frames = 0
      }
      repaint()
    }
  }
  thread.start()
  
}