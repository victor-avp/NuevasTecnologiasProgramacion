package benchmark;

import ochoreinas.Buscador;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import viajantecomercio.Montecarlo;
import viajantecomercio.Problema;
import viajantecomercio.VecinoMasCercano;

import java.util.concurrent.TimeUnit;

/**
 * Benchmark de las soluciones implementadas
 * Se miden los tiempos tanto de las variantes imperativas como funcionales,
 * utilizando 5 iteraciones de calentamiento y otras 5 para medición de tiempos
 */
public class MyBenchmark {

    // Problemas: TSP (berlin52) con MC y VMC, así como problema 8-Reinas
    static Problema problemaTSP = new Problema("./datos/berlin52.tsp");
    static Montecarlo heuristicaAleatoria = new Montecarlo();
    static VecinoMasCercano heuristicaVecinoMasCercano = new VecinoMasCercano();
    static Buscador buscador8Reinas = new Buscador(8);

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


    /**
     * Benchmark de implementación funcional para TSP-MC
     */
    @Benchmark
    //@Fork(value = 1, warmups = 1)
    //@Warmup(iterations = 1)
    //@Measurement(iterations = 3)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @BenchmarkMode(Mode.AverageTime)
    public void aleatoriaFuncional() {
        heuristicaAleatoria.calcularRutaOptima(problemaTSP);
    }

    /**
     * Benchmark de implementación no funcional para TSP-MC
     */
    @Benchmark
    //@Fork(value = 1, warmups = 1)
    //@Warmup(iterations = 1)
    //@Measurement(iterations = 3)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @BenchmarkMode(Mode.AverageTime)
    public void aleatoriaNoFuncional() {
        heuristicaAleatoria.calcularRutaOptimaNoFuncional(problemaTSP);
    }

    /**
     * Benchmark de implementación funcional para TSP-VMC
     */
    @Benchmark
    //@Fork(value = 1, warmups = 1)
    //@Warmup(iterations = 1)
    //@Measurement(iterations = 3)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @BenchmarkMode(Mode.AverageTime)
    public void vmcFuncional() {
        heuristicaVecinoMasCercano.calcularRutaOptima(problemaTSP);
    }

    /**
     * Benchmark de implementación no funcional para TSP-VMC
     */
    @Benchmark
    //@Fork(value = 1, warmups = 1)
    //@Warmup(iterations = 1)
    //@Measurement(iterations = 3)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @BenchmarkMode(Mode.AverageTime)
    public void vmcNoFuncional() {
        heuristicaVecinoMasCercano.calcularRutaOptimaNoFuncional(problemaTSP);
    }

    /**
     * Benchmark de implementación funcional para 8-reinas
     */
    @Benchmark
    //@Fork(value = 1, warmups = 1)
    //@Warmup(iterations = 1)
    //@Measurement(iterations = 3)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @BenchmarkMode(Mode.AverageTime)
    public void ochoReinasFuncional() {
        buscador8Reinas.resolver();
    }

    /**
     * Benchmark de implementación no funcional para 8-reinas
     */
    @Benchmark
    //@Fork(value = 1, warmups = 1)
    //@Warmup(iterations = 1)
    //@Measurement(iterations = 3)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @BenchmarkMode(Mode.AverageTime)
    public void ochoReinasNoFuncional() {
        buscador8Reinas.resolverNoFuncional();
    }
}