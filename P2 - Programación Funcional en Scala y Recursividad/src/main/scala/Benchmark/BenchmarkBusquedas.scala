package Benchmark

import org.scalameter.api._
import org.scalameter.picklers.Implicits._

object BenchmarkBusquedas extends Bench[Double] {

  /* configuration */

  lazy val executor = SeparateJvmsExecutor(
    new Executor.Warmer.Default,
    Aggregator.min,
    new Measurer.Default
  )
  lazy val measurer = new Measurer.Default
  lazy val reporter = new LoggingReporter[Double]
  lazy val persistor = Persistor.None

  /* inputs */

  val sizes = Gen.range("size")(300000, 1500000, 300000)

  val ranges = for {
    size <- sizes
  } yield 0 until size

  /* tests */

  performance of "Range" in {
    measure method "map" config (
      exec.benchRuns -> 5
      ) in {
      using(ranges) in {
        r => r.map(_ + 1)
      }
    }

    measure method "jeje" config (
      exec.benchRuns -> 5
      ) in {
      using(ranges) in {
        r => r.map(_ + 1)
      }
    }
  }


//  def criterioInt: (Int, Int) => Boolean = (a: Int, b: Int) => a > b
//  def criterioDouble: (Double, Double) => Boolean = (a: Double, b: Double) => a > b
//  def criterioChar: (Char, Char) => Boolean = (a: Char, b: Char) => a > b
//
//  val jeje: Array[Int] = Array.fill(50)(Random.nextInt(500))
//  val jeje2 = jeje.sorted
//  val a_buscar = jeje2(Random.nextInt(jeje2.length))
//  println(jeje2.mkString(" "))
//  println(busquedaBinaria(jeje2, a_buscar, criterioInt))

//  val sizes: Gen[Int] = Gen.range("size")(300000, 1500000, 300000)
//  val ranges: Gen[Range] = for {
//    size <- sizes
//  } yield 0 until size
//
//  performance of "Range" in {
//    measure method "map" in {
//      using(ranges) in {
//        r => r.map(_ + 1)
//      }
//    }
//  }
}
