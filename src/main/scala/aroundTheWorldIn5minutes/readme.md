# Overview

## Whats new

### Givens

- motivated by overloaded keyword `implicit`

```scala worksheet
// scala 2: implicits

implicit val personOrdering: Ordering[Person] = new Ordering[Person]{
  override def compare(x: Person, y: Person) = x.name.compareTo(y.name)
}

val sortedPeople = people.sorted

// Scala 3: given

given Person[Ordering] with 
  override def compare(x: Person, y: Person) = x.name.compareTo(y.name)
  

def aMethodWithOrdering(people: List[Person])(using ordering: Ordering[Person]): List[Person] = ???
```


```scala worksheet
def methodWithImplicitInt(implicit value: Int): Int = value * 10
def methodWithUsingInt(implicit value: Int): Int = value * 10

given meaningOfLife: Int = 42

methodWithImplicitInt(100) // legal
// methodWithUsingInt(100) // won't compile

methodWithUsingInt(using 100) // ok
```

### Extension Methods

- Another way to replace implicits in scala 2
- replacement for implicit classes

```scala worksheet
// scala 2
implicit class MyRichInteger(number: Int) {
  def isEven: Boolean = number % 2 == 0
}

val isEven = 2.isEven

// scala 3
extension(number: Int)
  def isEven_v2: Boolean = number % 2 == 0
  
val isEven_v2 = 2.isEven_v2
```

```scala worksheet
// generic extensions

extension[A](list: List[A])
  def ends: (A, A) = (list.head, list.last)

List(1, 2, 3, 4).ends
```

- reason1: make apis very expressive
- reason2: enchance/decorate/extend *certain* types with new methods but not others

```scala worksheet
trait Semigroup[A] {
  def combine(x: A, y: A): A
}

extension[A] (list: List[A])
  def combineAll(using semigroup: Semigroup[A]): A =
    list.reduce(semigroup.combine)

given intSemiGroup: Semigroup[Int] with :
  override def combine(x: Int, y: Int) = x + y
  
val sum = List(1, 2, 3, 4).combineAll // ok
List("black", "white").combineAll // fails since no Semigroup[String]
```