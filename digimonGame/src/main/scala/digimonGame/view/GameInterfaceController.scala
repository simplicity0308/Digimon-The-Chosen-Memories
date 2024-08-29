package digimonGame.view

import digimonGame.Game

import scalafx.animation.PauseTransition
import scalafx.application.Platform
import scalafx.event.ActionEvent
import scalafxml.core.macros.sfxml
import scalafx.Includes._
import scalafx.animation.TranslateTransition
import scalafx.util.Duration
import scalafx.scene.media.{Media, MediaPlayer}
import scalafx.scene.control.Alert.AlertType
import scalafx.scene.control.{Alert, Button, ButtonType, Dialog, Label, ProgressBar}
import scalafx.scene.image.{Image, ImageView}
import scala.util.Random

@sfxml
class GameInterfaceController(
                               val digimonImageView: ImageView,
                               val affinityBar: ProgressBar,
                               val feed: Button,
                               val drink: Button,
                               val exercise: Button,
                               val pet: Button,
                               val digivolve: Button,
                              val promptLabel: Label
                             ){

  var selectedCharacterType: String = _
  var currentAffinity: Double = 0.0
  var lastAction: String = ""

  def setSelectedCharacterType(CharacterType: String): Unit = {
    selectedCharacterType = CharacterType
    updateImageView()
  }

  def updateImageView(): Unit = {
    val imageURL = s"/digimonGame/image/Game${selectedCharacterType.capitalize}.png"
    val image = new Image(getClass.getResource(imageURL).toString)
    digimonImageView.image = image
  }

  def initialize(): Unit = {
    feed.onAction = (_: ActionEvent) => handleAction("Feed")
    drink.onAction = (_: ActionEvent) => handleAction("Drink")
    exercise.onAction = (_: ActionEvent) => handleAction("Exercise")
    pet.onAction = (_: ActionEvent) => handleAction("Pet")
    digivolve.onAction = (_: ActionEvent) => handleAction("Digivolve")
    digivolve.disable = true
  }

  def handleAction(action: String): Unit = {
    if (currentAffinity >= 30) {
      if (action == "Digivolve") {
        val vibrationTransition = new TranslateTransition(Duration(100), digimonImageView)
        vibrationTransition.fromX = -10
        vibrationTransition.toX = 10
        vibrationTransition.cycleCount = 20
        vibrationTransition.autoReverse = true
        vibrationTransition.play()

        val digivolvedImageURL = s"/digimonGame/image/Game${selectedCharacterType.capitalize}Digivolve.png"
        val digivolvedImage = new Image(getClass.getResource(digivolvedImageURL).toString)
        promptLabel.text = "Congratulations! Your Digimon has digivolved!"
        val audioURLEnd = getClass.getResource("../audio/musicEnd.mp3").toString
        val mediaPlayerEnd = new MediaPlayer(new Media(audioURLEnd))
        mediaPlayerEnd.play()
        digimonImageView.image = digivolvedImage

        val pause = new PauseTransition(Duration(5000))
        val customIcon = new ImageView(new Image(getClass.getResourceAsStream("/digimonGame/image/gameAlert.png")))
        customIcon.setFitHeight(100)
        customIcon.setFitWidth(100)

        pause.onFinished = _ => {
          Platform.runLater(() => {
            val gameEndAlert = new Alert(AlertType.Information)
            gameEndAlert.graphic = customIcon
            gameEndAlert.title = "Game Over"
            gameEndAlert.headerText = "Congratulations! Your Digimon has digivolved!"
            gameEndAlert.contentText = "What would you like to do?"
            val backToMenuButton = new ButtonType("Back to Menu")
            val quitButton = new ButtonType("Quit")
            gameEndAlert.buttonTypes = Seq(backToMenuButton, quitButton)

            val result = gameEndAlert.showAndWait()
            result match {
              case Some(`backToMenuButton`) =>
                Game.showMenu()
              case _ =>
                Platform.exit()
            }
          })
        }
        pause.play()
      } else {
        promptLabel.text = "Can't perform action, digimon is at max affinity."
        val audioURLCant = getClass.getResource("../audio/musicCant.mp3").toString
        val mediaPlayerCant = new MediaPlayer(new Media(audioURLCant))
        mediaPlayerCant.play()
      }
    } else if (action == lastAction) {

      promptLabel.text = s"Can't $action twice in a row. Choose another action."
      val audioURLCant = getClass.getResource("../audio/musicCant.mp3").toString
      val mediaPlayerCant = new MediaPlayer(new Media(audioURLCant))
      mediaPlayerCant.play()
    } else {
      lastAction = action
      val affinityIncrease = Random.nextInt(5) + 1
      currentAffinity = (currentAffinity + affinityIncrease).min(30)
      affinityBar.progress = currentAffinity / 30.0

      val jumpTransition = new TranslateTransition(Duration(100), digimonImageView)
      jumpTransition.fromY = 0
      jumpTransition.toY = -10
      jumpTransition.cycleCount = 2
      jumpTransition.autoReverse = true
      jumpTransition.play()

      promptLabel.text = s"$action performed. Affinity increased by $affinityIncrease."

      val audioURL = getClass.getResource(s"../audio/music$action.mp3").toString
      val mediaPlayer = new MediaPlayer(new Media(audioURL))
      mediaPlayer.play()

      if (currentAffinity >= 30) {
        digivolve.disable = false
      }
    }
  }
  initialize()
}
