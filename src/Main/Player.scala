package Main

object Player {
  
  var lives = 5
  var money = 100
  var score = 0
  
  def isAlive = {
    Player.lives > 0
  }
  
}