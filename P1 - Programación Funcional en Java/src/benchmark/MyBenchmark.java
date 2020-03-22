package benchmark;

import viajantecomercio.Montecarlo;
import viajantecomercio.Problema;
import viajantecomercio.VecinoMasCercano;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

public class MyBenchmark {

    static Problema problema = new Problema("./datos/berlin52.tsp");
    static Montecarlo heuristicaAleatoria = new Montecarlo();
    static VecinoMasCercano heuristicaVecinoMasCercano = new VecinoMasCercano();

    /**
     * main methods
     *
     * @param args
     * @throws Exception
     */
    public static void main(final String[] args) throws Exception {
        Options opt = new OptionsBuilder().include(MyBenchmark.class.getSimpleName()).
                warmupIterations(5).
                measurementIterations(5).
                //measurementTime(TimeValue.milliseconds(1000)).
                        forks(1).
                //result("results.csv").
                        build();
        new Runner(opt).run();
    }


//    @Benchmark
//    //@Fork(value = 1, warmups = 1)
//    //@Warmup(iterations = 1)
//    //@Measurement(iterations = 3)
//    @OutputTimeUnit(TimeUnit.MILLISECONDS)
//    @BenchmarkMode(Mode.AverageTime)
//    public void aleatoriaFuncional() {
//        heuristicaAleatoria.calcularRutaOptima(problema);
//    }
//
//    @Benchmark
//    //@Fork(value = 1, warmups = 1)
//    //@Warmup(iterations = 1)
//    //@Measurement(iterations = 3)
//    @OutputTimeUnit(TimeUnit.MILLISECONDS)
//    @BenchmarkMode(Mode.AverageTime)
//    public void aleatoriaNoFuncional() {
//        heuristicaAleatoria.calcularRutaOptimaNoFuncional(problema);
//    }

    @Benchmark
    //@Fork(value = 1, warmups = 1)
    //@Warmup(iterations = 1)
    //@Measurement(iterations = 3)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @BenchmarkMode(Mode.AverageTime)
    public void vmcNoFuncional() {
        heuristicaVecinoMasCercano.calcularRutaOptimaNoFuncional(problema);
    }

    @Benchmark
    //@Fork(value = 1, warmups = 1)
    //@Warmup(iterations = 1)
    //@Measurement(iterations = 3)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @BenchmarkMode(Mode.AverageTime)
    public void vmcFuncional() {
        heuristicaVecinoMasCercano.calcularRutaOptima(problema);
    }
}