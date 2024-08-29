package digimonGame.view


import digimonGame.Game
import scalafx.event.ActionEvent
import scalafx.scene.control.{Alert, ButtonType}
import scalafx.scene.image.ImageView
import scalafx.stage.Stage
import scalafxml.core.macros.sfxml
import scalafx.scene.image.Image

@sfxml
class GameMenuController() {

  var dialogStage : Stage  = null

  def handleGameBegin(): Unit = {
    Game.showSelectDigimon()
  }

  def handleSettings(): Unit = {
    Game.showSettingsMenu()
  }

  def handleInstructions(): Unit = {
    Game.showInstructions()
  }

  def handleExit(action: ActionEvent): Unit = {
    val alert = new Alert(Alert.AlertType.Confirmation){
      val customIcon = new ImageView(new Image(getClass.getResourceAsStream("/digimonGame/image/gameAlert.png")))
      customIcon.setFitHeight(100)
      customIcon.setFitWidth(100)

      initOwner(dialogStage)
      graphic = customIcon
      title = "Confirm Exit"
      headerText = "Are you sure you want to exit?"
      contentText = "Thanks for playing the game."
    }

    val result = alert.showAndWait()
    if (result.contains(ButtonType.OK)) {
      System.exit(0)
    }
  }
}

