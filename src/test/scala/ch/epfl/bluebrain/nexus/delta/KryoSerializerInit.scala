package ch.epfl.bluebrain.nexus.delta


import akka.actor.ExtendedActorSystem
import ch.epfl.bluebrain.nexus.delta.KryoSerializerInit.OrderingKeySerializer
import ch.epfl.bluebrain.nexus.delta.TestClass.Key
import com.esotericsoftware.kryo.io.{Input, Output}
import com.esotericsoftware.kryo.{Kryo, Serializer}
import io.altoo.akka.serialization.kryo.DefaultKryoInitializer
import io.altoo.akka.serialization.kryo.serializer.scala.ScalaKryo

class KryoSerializerInit extends DefaultKryoInitializer {

  override def postInit(kryo: ScalaKryo, system: ExtendedActorSystem): Unit = {
    super.postInit(kryo, system)
    kryo.addDefaultSerializer(classOf[Ordering[Key]], classOf[OrderingKeySerializer])
    kryo.register(classOf[Ordering[Key]], new OrderingKeySerializer)
    ()
  }
}

object KryoSerializerInit {

  class OrderingKeySerializer extends Serializer[Ordering[Key]] {
    override def write(kryo: Kryo, output: Output, `object`: Ordering[Key]): Unit = ()

    override def read(kryo: Kryo, input: Input, `type`: Class[_ <: Ordering[Key]]): Ordering[Key] =
      Key.keyOrder.toOrdering
  }

}

