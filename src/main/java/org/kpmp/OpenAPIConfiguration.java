package org.kpmp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

/**
 * OpenAPI/Swagger configuration for the KPMP Pegasus Data API.
 * 
 * Provides API documentation metadata and customization for the Swagger UI
 * and OpenAPI specification generation.
 * 
 * Access the generated documentation at:
 * - Swagger UI: http://localhost:8080/swagger-ui.html
 * - OpenAPI JSON: http://localhost:8080/v3/api-docs
 * - OpenAPI YAML: http://localhost:8080/v3/api-docs.yaml
 */
@Configuration
public class OpenAPIConfiguration {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("KPMP Pegasus Data API")
                        .version("3.1.0")
                        .description("The KPMP (Kidney Precision Medicine Project) Pegasus Data API provides access to " +
                                "comprehensive kidney omics data including single-cell RNA sequencing, regional transcriptomics, " +
                                "regional proteomics, and participant clinical information. The API supports both GraphQL and REST " +
                                "endpoints for flexible data access.\n\n" +
                                "## Key Features\n\n" +
                                "- **GraphQL Queries**: Flexible schema-based queries via POST /graphql\n" +
                                "- **Gene Expression Data**: Access expression data across multiple omics types\n" +
                                "- **Cell Type Information**: Complete cell type hierarchies and clustering\n" +
                                "- **Participant Data**: Clinical datasets and experiment summaries\n" +
                                "- **Visualization Data**: UMAP coordinates and plot data\n" +
                                "- **Multiple Data Versions**: Support for current and 2025 data releases\n\n" +
                                "## Quick Start\n\n" +
                                "### GraphQL Queries\n" +
                                "```\n" +
                                "POST /graphql\n" +
                                "Content-Type: application/json\n\n" +
                                "{\n" +
                                "  \"query\": \"query { autocomplete(searchTerm: \\\"APOL\\\") { label } }\"\n" +
                                "}\n" +
                                "```\n\n" +
                                "### REST Endpoints\n" +
                                "```\n" +
                                "GET /v1/clearCache\n" +
                                "POST /v1/error\n" +
                                "```\n\n" +
                                "## Access Points\n\n" +
                                "- **GraphQL IDE**: GET /graphiql\n" +
                                "- **API Documentation**: GET /swagger-ui.html\n" +
                                "- **OpenAPI Spec**: GET /v3/api-docs")
                        .contact(new Contact()
                                .name("KPMP Team")
                                .url("https://www.kpmp.org"))
                        .license(new License()
                                .name("KPMP Data Use Agreement")
                                .url("https://www.kpmp.org")))
                .addServersItem(new Server()
                        .url("http://localhost:8080")
                        .description("Local Development Server"))
                .addServersItem(new Server()
                        .url("https://pegasus-data.kpmp.org")
                        .description("Production Server"));
    }
}
