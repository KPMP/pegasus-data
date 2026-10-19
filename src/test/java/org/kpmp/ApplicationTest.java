package org.kpmp;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import graphql.schema.idl.RuntimeWiring;
import org.junit.jupiter.api.Test;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

class ApplicationTest {

    @Test
    void registersJsonScalarWithManagedGraphqlRuntime() {
        RuntimeWiring.Builder builder = mock(RuntimeWiring.Builder.class);
        RuntimeWiringConfigurer configurer = new Application().runtimeWiringConfigurer();

        configurer.configure(builder);

        verify(builder).scalar(graphql.scalars.ExtendedScalars.Json);
    }
}
