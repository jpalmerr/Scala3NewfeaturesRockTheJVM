/*
kinds: types of types
- Ins, String, Person: value level kind
- List, Option, Future: generics
- Monad, Functor: generics of generics
 */

type MyList[A]= List[A]
type MyListV2 = [A] =>> List[A] // type lambda

type MyMap[K, V] = Map[K,V]
type MyMapV2[K,V] = [K, V] =>> Map[K, V]

class Functor[F[_]] { // takes generic
  val functorOption = new Functor[Option]
}

type FunctorV2 = [F[_]] =>> Functor[F]

// so why type lambdas?

// generalizations or simplifications of types

type MySpecialMap = [A] =>> Map[String, A]
val addressBook: MySpecialMap[String] = Map()

class ZIO[R, E, A]

trait Monad[M[_]] {
  def flatMap[A, B](ma: M[A])(transformation: A => M[B]): M[B]
}

class ZIOMonad[R, E] extends Monad[[A] =>> ZIO[R, E, A]] {
  override def flatMap[A, B](ma: ZIO[R, E, A])(transformation: A => ZIO[R, E, B]): ZIO[R, E, B] = ???
}


// exercise: implement a monad data type for either

class EitherMonad[E] extends Monad[[A] =>> Either[E, A]] {
  override def flatMap[A, B](ma: Either[E, A])(transformation: A => Either[E, B]): Either[E, B] = ma match
    case Left(e) => Left(e)
    case Right(a) => transformation(a)
}

// useful for libraries as much as anything
