package digimonGame.view

import digimonGame.Game
import scalafx.stage.Stage
import scalafxml.core.macros.sfxml

@sfxml
class GameInstructionsController {

  var dialogStage : Stage  = null

  def handleReturn(): Unit = {
    Game.showMenu()
  }
}
