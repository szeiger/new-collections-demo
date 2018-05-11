import scala.collection.compat._

object Test extends App {
  val xs = List(1,2,3)
  println(xs.to(Vector))

  def f(xs: Int*) = println(xs.getClass)
  f(1,2,3)

  def seq(s: Seq[Int]): Unit = ()
  seq(Array(1,2,3))
}
