package digimonGame.view

import digimonGame.Game
import scalafx.scene.media.{Media, MediaPlayer}
import scalafx.stage.Stage
import scalafx.util.Duration

import scalafx.beans.property.DoubleProperty

import scalafxml.core.macros.sfxml

@sfxml
class GameSettingsController {

  var dialogStage: Stage = null
  private val MUSIC_FILE_1 = "../audio/musicMC2.mp3"
  private val MUSIC_FILE_2 = "../audio/musicMC.mp3"
  private val media1 = new Media(getClass.getResource(MUSIC_FILE_1).toExternalForm)
  private val media2 = new Media(getClass.getResource(MUSIC_FILE_2).toExternalForm)
  private val mediaPlayer1 = new MediaPlayer(media1)
  private val mediaPlayer2 = new MediaPlayer(media2)

  mediaPlayer1.setOnEndOfMedia(() => mediaPlayer1.seek(Duration.Zero))
  mediaPlayer2.setOnEndOfMedia(() => mediaPlayer2.seek(Duration.Zero))

  private val volume: DoubleProperty = new DoubleProperty(this, "volume", 100.0)

  def initialize(): Unit = {
    mediaPlayer1.volume <== volume / 200.0
    mediaPlayer2.volume <== volume / 100.0
  }

  def handlePlayMusic1(): Unit = {
    mediaPlayer1.play()
  }

  def handlePlayMusic2(): Unit = {
    mediaPlayer2.play()
  }

  def handleStopMusic(): Unit = {
    mediaPlayer1.stop()
    mediaPlayer2.stop()
  }

  def handleReturn(): Unit = {
    Game.showMenu()
  }

  initialize()
}
