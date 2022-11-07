// union types

val truthOr42: Boolean | Int = 42

def ambivelentMethod(arg: String | Int): String = arg match
  case _: String => "string"
  case _: Int => "number"

// type inference
val stringOrInt: String | Int = if (42 > 0) "a string" else 42

// flow typing -- explicit null checking

type Maybe[T] = T | Null

def handleMaybe(someValue: Maybe[String]): Int =
  if (someValue != null) someValue.length
  else 0

// intersection types

class Animal
trait Carnivore
class Crocodile extends Animal with Carnivore
val carnivoreAnimal: Animal & Carnivore = new Crocodile

// intersection types and the diamond problem

trait Gadget{
  def use(): Unit
}
trait Camera extends Gadget {
  def takePicture(): Unit = println("Smile")

  override def use(): Unit = println("snap")
}

trait Phone extends Gadget:
  def makePhoneCall(): Unit = println("dialing")

  override def use(): Unit = println("ring ring")

def useSmartDevice(cp: Camera & Phone): Unit =
  cp.takePicture()
  cp.makePhoneCall()
  cp.use() // which method ?? -- depends on inheritance order. latest takes priority

class SmallPhone extends Phone with Camera
class BigPhone extends Camera with Phone

useSmartDevice(new SmallPhone) // Smile dialing snap
useSmartDevice(new BigPhone) // Smile dialing ring rin

// intersection types with covariance

trait HostConfig
trait HostController {
  def get: Option[HostConfig]
}
trait PortConfig
trait PortController {
  def get: Option[PortConfig]
}

def getConfig(controller: HostController & PortController): Option[HostConfig & PortConfig] = {
  controller.get
}













