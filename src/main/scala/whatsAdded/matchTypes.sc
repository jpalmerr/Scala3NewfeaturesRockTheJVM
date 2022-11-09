// Ints, Strings, Lists etc

// BIGInt -> last digit of type Int
//STRING -> last char
// LIST -> last element

def lastDigitOf(number: BigInt): Int = {
  (number % 10).toInt
}

def lastCharOf(string: String): Char = {
  if string.isEmpty then throw new NoSuchElementException
  else string.charAt(string.length - 1)
}

def lastElementOf[T](list: List[T]): T = {
  if list.isEmpty then throw new NoSuchElementException
  else list.last
}

// can we collect these 3 very similar methods into one?

// scala 2? Can't

// scala 3

type ConstituentPartOf[T] = T match
  case BigInt => Int
  case String => Char
  case List[t] => t

val aDigit: ConstituentPartOf[BigInt] = 2 // ok
val aChar: ConstituentPartOf[String] = 'a'
val anElement: ConstituentPartOf[List[Int]] = 42

def lastComponentOf[T](biggerValue: T): ConstituentPartOf[T] = {
  biggerValue match
    case b: BigInt => (b % 10).toInt
    case s: String =>
      if s.isEmpty then throw new NoSuchElementException
      else s.charAt(s.length - 1)
    case l: List[t] =>
      if l.isEmpty then throw new NoSuchElementException
      else l.last
}

val lastDigit = lastComponentOf[BigInt](2357845)
val lastChar = lastComponentOf[String]("scala")
val lastElement = lastComponentOf[List[Int]](List(1, 2, 3))

// why?

// match types are useful to be able to express methods that are returning unrelated types that can be checked at compile time

// why is this different from oop?
// oop -> heirachys: have to start with type Any to catch all => lose compiler help

// why is this different from generics

def lastElementOfList[A](list: List[A]): A = list.last
// match types allow us to be more flexible with return type: don't need a direct relationship

// recursion

type LowestPartOf[T] = T match
  case List[t] => LowestPartOf[t]
  case _ => T

val lastElementOfNestedList: LowestPartOf[List[List[List[Int]]]] = 2

// compiler will also detect infinte recursions or cyclic reference

