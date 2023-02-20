package es.proof.grpc.clients.test.karateTest;


import com.intuit.karate.KarateOptions;
import com.intuit.karate.junit5.Karate;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;


@RunWith(JUnitPlatform.class)
@KarateOptions(features = "classpath:features")
public class StatusTestRunner {

    @Karate.Test
    Karate statusSimpleTest() {
        return Karate.run("StatusSimpleTest").relativeTo(getClass());
    }

    @Karate.Test
    Karate statusTest() {
        return Karate.run("StatusTest").relativeTo(getClass());
    }

}