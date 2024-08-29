package digimonGame.view

import digimonGame.model.Digimon
import scalafx.scene.control.Button
import scalafx.event.ActionEvent
import scalafx.Includes._
import digimonGame.Game
import scalafxml.core.macros.sfxml

@sfxml
class GamePreparationController(
                                 val agumonButton: Button,
                                 val impmonButton: Button,
                                 val gabumonButton: Button,
                                 val palmonButton: Button
                               ){

  val model: Digimon = new Digimon()

  def initialize(): Unit = {
    agumonButton.onAction = (_: ActionEvent) => selectCharacter("Agumon")
    impmonButton.onAction = (_: ActionEvent) => selectCharacter("Impmon")
    gabumonButton.onAction = (_: ActionEvent) => selectCharacter("Gabumon")
    palmonButton.onAction = (_: ActionEvent) => selectCharacter("Palmon")
  }

  def selectCharacter(characterType: String): Unit = {
    model.setSelectedCharacter(characterType)
    Game.showGameInterface(characterType)
  }
  initialize()
}
