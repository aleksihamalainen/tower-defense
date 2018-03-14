package Main

import javax.swing._
import java.awt._

object Game extends JFrame {
  
  def main(args: Array[String]) = {
    val title = "Tower Defense"
    val width = 800
    val height = 700
    val size = new Dimension(width, height)
    val panel = new GamePanel
    setSize(size)
    setResizable(false)
    setLocationRelativeTo(null)
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
    add(panel)
    setVisible(true)
    
  }
  
}