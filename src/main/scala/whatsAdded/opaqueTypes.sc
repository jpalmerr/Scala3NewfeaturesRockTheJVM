object SocialNetwork {
  // some data structures
  opaque type Name = String

  // 1. Companion objects
  object Name {
    def apply(name: String): Name = name
  }

  // 2. extension

  extension (name: Name)
    def length: Int = name.length

  // inside the sn, you can use name & string interchangeably
  def addFriend(person1: Name, person2: Name): Boolean = {
    person1.length == person2.length // get access to entire string api
  }
}

// outside object, Name and String are not related
import SocialNetwork.*

//val name: Name = "James" // won't compile

/* why?
- to restrict access of apis
*/

object Graphics {
  opaque type Colour = Int // in hex
  opaque type ColourFilter <: Colour = Int

  val red: Colour = 0xFF000000
  val green: Colour = 0x00F0000
  val blue: Colour = 0x0000FF00

  val halfTransparency: ColourFilter = 0x88
}

import Graphics.*

// i have the type colour but don't know how its related to int
case class OverlayFilter(c: Colour)

val fadeLayer= OverlayFilter(halfTransparency)

// "programming to interfaces"

// how can we create instances of opaque types + how to access their apis

// 1. Companion objects
val aName = Name.apply("James")

// 2. Extension methods
aName.length




