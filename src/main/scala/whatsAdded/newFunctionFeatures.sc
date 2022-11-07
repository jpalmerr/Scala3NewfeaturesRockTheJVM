import scala.concurrent.{ExecutionContext, Future}

/**
 * Generics in scala 3
 */


// scala 2 only had generic methods
def processOption[A](option: Option[A]): String = option match
  case Some(value) => s"value"
  case None => "[]"

// in scala 3 we can add generics to function valuew
val processOptionFunc: [A] => Option[A] => String =
  [A] => (option: Option[A]) => option match
    case Some(value) => s"value"
    case None => "[]"

/* Context functions = functions with using args */

// in scala 2 only methods can have context arguments
def methodWithoutContextArg(nonContextArg: Int)(nonContextArg2: String): String = ???
def methodWithContextArg(nonContextArg: Int)(using contextArg: String): String = ???

// in scala 3, vals can to
val functionWithContextArgs: Int => String ?=> String = methodWithContextArg
                                    // ^^ implicit

val increment: ExecutionContext ?=> Int => Future[Int] = x => Future(x * 1000)

val tuples = List((1, 2), (3, 4))

tuples.map((a, b) => a + b) // not possible in scala 2 had to pattern match



