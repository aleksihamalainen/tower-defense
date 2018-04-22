package Main

import java.awt.Point
import java.awt.event.KeyListener
import java.awt.event.MouseEvent
import java.awt.event.MouseListener
import java.awt.event.MouseMotionListener
import java.awt.event.KeyEvent

class Mouse extends MouseMotionListener with MouseListener with KeyListener {

  def mouseClicked(e: MouseEvent) = {

  }

  def mousePressed(e: MouseEvent) = {
    Shop.click(e.getButton)
  }

  def mouseReleased(e: MouseEvent) = {

  }

  def mouseEntered(e: MouseEvent) = {

  }

  def mouseExited(e: MouseEvent) = {

  }

  def mouseDragged(e: MouseEvent) = {
    GamePanel.mouse = new Point(e.getX, e.getY)
  }

  def mouseMoved(e: MouseEvent) = {
    GamePanel.mouse = new Point(e.getX, e.getY)
  }
  
  def keyPressed(e: KeyEvent) = {
    
  }
  
  def keyReleased(e: KeyEvent) = {
    
  }
  
  def keyTyped(e: KeyEvent) = {
    
  }

}