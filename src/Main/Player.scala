package Main

object Player {
  
  var lives = 1
  var money = 100
  
  def isAlive = {
    Player.lives > 0
  }
  
}