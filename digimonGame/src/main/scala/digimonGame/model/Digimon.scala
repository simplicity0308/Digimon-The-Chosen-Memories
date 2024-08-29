package digimonGame.model

class Digimon {
  private var selectedCharacter: String = _

  def setSelectedCharacter(characterType: String): Unit = {
    selectedCharacter = characterType
  }

  def getSelectedCharacter: String = selectedCharacter
}
