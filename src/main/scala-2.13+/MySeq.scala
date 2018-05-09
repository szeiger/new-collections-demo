import scala.collection.StrictOptimizedSeqFactory
import scala.collection.immutable.{IndexedSeq, IndexedSeqOps, StrictOptimizedSeqOps}
import scala.collection.mutable.{ArrayBuffer, Builder}

class MySeq[+A](data: Array[AnyRef])
  extends IndexedSeq[A]
    with IndexedSeqOps[A, MySeq, MySeq[A]]
    with StrictOptimizedSeqOps[A, MySeq, MySeq[A]]
{
  def length: Int = data.length
  def apply(i: Int) = data(i).asInstanceOf[A]
}

object MySeq extends StrictOptimizedSeqFactory[MySeq] {
  private[this] val emptySeq = new MySeq[Nothing](new Array[AnyRef](0))
  def empty[A]: MySeq[A] = emptySeq
  def from[A](source: IterableOnce[A]): MySeq[A] =
    new MySeq[A](source.asInstanceOf[IterableOnce[AnyRef]].toArray)
  def newBuilder[A](): Builder[A,MySeq[A]] =
    ArrayBuffer.newBuilder[A]().mapResult(b => new MySeq[A](b.asInstanceOf[ArrayBuffer[AnyRef]].toArray[AnyRef]))
}
