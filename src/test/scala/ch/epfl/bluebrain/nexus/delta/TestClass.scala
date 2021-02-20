package ch.epfl.bluebrain.nexus.delta

import cats.Order
import cats.data.NonEmptySet
import ch.epfl.bluebrain.nexus.delta.TestClass.Key

final case class TestClass(value: NonEmptySet[Key])

object TestClass {
  final case class Key(value: String, index: Int)
  object Key {
    implicit val keyOrder: Order[Key] = Order.by(_.index)
  }
}
