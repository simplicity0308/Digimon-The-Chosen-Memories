package digimonGame
import digimonGame.view.GameInterfaceController
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene
import scalafx.Includes._
import scalafxml.core.{FXMLLoader, NoDependencyResolver}
import javafx.{scene => jfxs}
import scalafx.scene.image.Image

object Game extends JFXApp {
  val rootResource = getClass.getResource("view/RootLayout.fxml")
  val loader = new FXMLLoader(rootResource, NoDependencyResolver)
  loader.load();
  val roots = loader.getRoot[jfxs.layout.BorderPane]

  stage = new PrimaryStage {
    title = "Digimon: The Chosen Memories"
    icons += new Image("digimonGame/image/gameDigimonLogo.png")
    scene = new Scene {
      root = roots
    }
  }

  def showMenu() = {
    val resource = getClass.getResource("view/GameMenu.fxml")
    val loader = new FXMLLoader(resource, NoDependencyResolver)
    loader.load();
    val roots = loader.getRoot[jfxs.layout.AnchorPane]
    this.roots.setCenter(roots)
  }

  def showSelectDigimon(): Unit = {
    val resource = getClass.getResource("view/GamePreparation.fxml")
    val loader = new FXMLLoader(resource, NoDependencyResolver)
    loader.load();
    val roots = loader.getRoot[jfxs.layout.AnchorPane]
    this.roots.setCenter(roots)
  }

  def showSettingsMenu(): Unit = {
    val resource = getClass.getResource("view/GameSettings.fxml")
    val loader = new FXMLLoader(resource, NoDependencyResolver)
    loader.load();
    val roots = loader.getRoot[jfxs.layout.AnchorPane]
    this.roots.setCenter(roots)
  }

  def showInstructions(): Unit = {
    val resource = getClass.getResource("view/GameInstructions.fxml")
    val loader = new FXMLLoader(resource, NoDependencyResolver)
    loader.load();
    val roots = loader.getRoot[jfxs.layout.AnchorPane]
    this.roots.setCenter(roots)
  }

  def showGameInterface(CharacterType: String): Unit = {
    val resource = getClass.getResource("view/GameInterface.fxml")
    val loader = new FXMLLoader(resource, NoDependencyResolver)
    loader.load()
    val controller = loader.getController[GameInterfaceController#Controller]
    controller.setSelectedCharacterType(CharacterType)
    val roots = loader.getRoot[jfxs.layout.AnchorPane]
    this.roots.setCenter(roots)
  }

  showMenu()
}
