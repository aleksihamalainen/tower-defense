package Main

import javax.swing._
import java.awt._

object Game extends JFrame {
  
  def main(args: Array[String]) = {
    val title = "Tower Defense"
    val width = 800
    val height = 600
    val size = new Dimension(width, height)
    setSize(size)
    setResizable(false)
    setLocationRelativeTo(null)
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
    add(GamePanel)
    setVisible(true)
  }
  
}