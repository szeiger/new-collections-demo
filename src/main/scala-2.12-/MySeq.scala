import scala.collection.generic._
import scala.collection.IndexedSeqLike
import scala.collection.immutable.IndexedSeq
import scala.collection.mutable.{ArrayBuffer, Builder}

class MySeq[+A](data: Array[AnyRef])
  extends IndexedSeq[A]
    with GenericTraversableTemplate[A, MySeq]
    with IndexedSeqLike[A, MySeq[A]]
{
  def length: Int = data.length
  def apply(i: Int) = data(i).asInstanceOf[A]
  override def companion: GenericCompanion[MySeq] = MySeq
}

object MySeq extends IndexedSeqFactory[MySeq] {
  private[this] val emptySeq = new MySeq[Nothing](new Array[AnyRef](0))
  override def empty[A]: MySeq[A] = emptySeq
  implicit def canBuildFrom[A]: CanBuildFrom[Coll, A, MySeq[A]] =
    ReusableCBF.asInstanceOf[GenericCanBuildFrom[A]]
  def newBuilder[A]: Builder[A, MySeq[A]] =
    ArrayBuffer.newBuilder[A].mapResult(b => new MySeq[A](b.asInstanceOf[ArrayBuffer[AnyRef]].toArray[AnyRef]))
}
