package es.proof.grpc.servers.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import es.proof.grpc.servers.test.config.GrpcServerTestConfig;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
@ContextConfiguration(classes = {GrpcServerTestConfig.class})
public class GrpcServerApplicationTest {

    /*@Autowired
    private CalculatorService calculatorService;

    @Test
    public void findFactorialTest() {
        Input input = Input.newBuilder().setNumber(5).build();
        calculatorService.findFactorial(Mono.just(input))
                .map(Output::getResult)
                .as(StepVerifier::create)
                .expectNext(120L)
                .verifyComplete();
    }

    @Test
    public void getAllFactorsTest() {
        Input input = Input.newBuilder().setNumber(20).build();
        calculatorService.getAllFactors(Mono.just(input))
                .map(Output::getResult)
                .as(StepVerifier::create)
                .expectNext(2L)
                .expectNext(4L)
                .expectNext(5L)
                .expectNext(10L)
                .verifyComplete();
    }

    @Test
    public void sumAllTest() {
        Flux<Input> inputFlux = Flux.just(1, 3, 5, 7)
                .map(i -> Input.newBuilder().setNumber(i).build());
        calculatorService.sumAll(inputFlux)
                .map(Output::getResult)
                .as(StepVerifier::create)
                .expectNext(16L)
                .verifyComplete();
    }

    @Test
    public void findPrimeTest() {
        Flux<Input> inputFlux = Flux.range(1, 10)
                .map(i -> Input.newBuilder().setNumber(i).build());
        calculatorService.findPrime(inputFlux)
                .map(Output::getResult)
                .as(StepVerifier::create)
                .expectNext(2L)
                .expectNext(3L)
                .expectNext(5L)
                .expectNext(7L)
                .verifyComplete();
    }*/
}
