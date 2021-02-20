package ch.epfl.bluebrain.nexus.delta

import akka.actor.ActorSystem
import akka.serialization.SerializationExtension
import akka.testkit.TestKit
import cats.Order
import cats.data.NonEmptySet
import ch.epfl.bluebrain.nexus.delta.TestClass.Key
import io.altoo.akka.serialization.kryo.KryoSerializer
import org.scalatest.TryValues
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpecLike

class KryoSpec extends TestKit(ActorSystem("KryoSpec")) with AnyWordSpecLike
  with Matchers
  with TryValues {

  private val serialization = SerializationExtension(system)
  implicit private val keyOrdering: Order[Key] = Order.by(_.index)

  "A TestClass Kryo serialization" should {
    "succeed" in {
      val test = TestClass(NonEmptySet.of(Key("a", 1), Key("b", 2), Key("c", 3)))

      // Find the Serializer for it
      val serializer = serialization.findSerializerFor(test)
      serializer.getClass.equals(classOf[KryoSerializer]) shouldEqual true

      // Check serialization/deserialization
      val serialized = serialization.serialize(test)
      serialized.isSuccess shouldEqual true

      val deserialized = serialization.deserialize(serialized.get, test.getClass)
      deserialized.success.value shouldEqual test
    }
  }
}
