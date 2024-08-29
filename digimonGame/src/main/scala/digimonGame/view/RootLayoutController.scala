package digimonGame.view

import digimonGame.Game
import scalafx.event.ActionEvent
import scalafx.scene.control.{Alert, ButtonType}
import scalafx.scene.image.{Image, ImageView}
import scalafx.stage.Stage
import scalafxml.core.macros.sfxml

@sfxml
class RootLayoutController {

  var dialogStage : Stage  = null

  def handleSettings(): Unit = {
    Game.showSettingsMenu()
  }

  def handleExit(action: ActionEvent): Unit = {
    val customIcon = new ImageView(new Image(getClass.getResourceAsStream("/digimonGame/image/gameAlert.png")))
    customIcon.setFitHeight(100)
    customIcon.setFitWidth(100)
    val alert = new Alert(Alert.AlertType.Confirmation) {
      initOwner(dialogStage)
      graphic = customIcon
      title = "Confirm Exit"
      headerText = "Are you sure you want to exit?"
      contentText = "All game progress will be lost."
    }
    val result = alert.showAndWait()
    if (result.contains(ButtonType.OK)) {
      System.exit(0)
    }
  }

  def handleReturnMenu(action: ActionEvent): Unit = {
    val alert = new Alert(Alert.AlertType.Confirmation) {
      val customIcon = new ImageView(new Image(getClass.getResourceAsStream("/digimonGame/image/gameAlert.png")))
      customIcon.setFitHeight(100)
      customIcon.setFitWidth(100)
      initOwner(dialogStage)
      graphic = customIcon
      title = "Confirm Return To Menu"
      headerText = "Are you sure you want to return to the main menu?"
      contentText = "All game progress will be lost."
    }
    val result = alert.showAndWait()
    if (result.contains(ButtonType.OK)) {
      Game.showMenu()
    }
  }
}
