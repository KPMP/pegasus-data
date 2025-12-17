# KPMP Pegasus Data API - Endpoint Documentation

## Overview

This document describes all available endpoints in the KPMP Pegasus Data application. The API provides access to kidney-related omics data including gene expression, cell type information, clinical data, and more.

**Base URL:** `http://localhost:8080` (or your configured server URL)

**API Versions:** The application supports both current and 2025 data versions for most endpoints.

---

## API Access Methods

### 1. GraphQL API
- **Endpoint:** `POST /graphql`
- **Content-Type:** `application/json`
- **GraphQL UI:** `GET /graphiql` (interactive explorer)
- **Schema:** Defined in [graphql/knowledge_environment.graphqls](src/main/resources/graphql/knowledge_environment.graphqls)

### 2. REST API
- **Endpoints:** Available at `/v1/*`
- **Content-Type:** `application/json`
- **Swagger/OpenAPI UI:** `GET /swagger-ui.html`
- **OpenAPI JSON:** `GET /v3/api-docs`

---

## REST Endpoints

### Error Management

#### POST `/v1/error`
**Description:** Logs client-side errors to the server for monitoring and debugging

**Request Body:**
```json
{
  "error": "Error message describing the issue",
  "stackTrace": "JavaScript stack trace or error details"
}
```

**Response:**
```json
true
```

**Status Codes:**
- `200 OK` - Error logged successfully
- `400 Bad Request` - Invalid request body
- `500 Internal Server Error` - Server error while logging

---

### Cache Management

#### GET `/v1/clearCache`
**Description:** Clears all application caches including gene expression, umap data, and other cached results. Useful for refreshing data without restarting the application.

**Parameters:** None

**Response:**
```json
{
  "message": "Caches successfully cleared!",
  "cacheNames": ["cache1", "cache2", ...]
}
```

**Status Codes:**
- `200 OK` - Caches cleared successfully
- `500 Internal Server Error` - Error clearing caches

---

## GraphQL Queries

### Overview
All GraphQL queries are accessed via `POST /graphql` endpoint. Use the GraphQL endpoint at `/graphiql` for interactive query testing.

### Data Structure

GraphQL queries return structured data with the following main data types:

- **Gene Expression Data** - Expression values across different omics types
- **Cell Type Hierarchies** - Cell type classifications and clustering information
- **UMAP Plot Data** - Dimensional reduction visualization coordinates
- **Participant Data** - Clinical and experimental information for participants
- **Summary Statistics** - Overview counts and information summaries

---

### Query: `autocomplete`

**Description:** Returns autocomplete suggestions for search terms

**Arguments:**
- `searchTerm` (String, required) - The search term to get suggestions for

**Returns:** `[AutocompleteResult]`

**Example Query:**
```graphql
query {
  autocomplete(searchTerm: "APOL") {
    id
    label
    category
  }
}
```

**Use Case:** Autocomplete dropdown suggestions for gene symbols, cell types, clusters, etc.

---

### Query: `cellTypeHierarchy`

**Description:** Returns the complete cell type hierarchy for current data

**Arguments:** None

**Returns:** `CellTypeHierarchy`

**Example Query:**
```graphql
query {
  cellTypeHierarchy {
    definition
    paths {
      path
      color
    }
  }
}
```

**Use Case:** Display cell type classification tree in the UI

---

### Query: `cellTypeHierarchy2025`

**Description:** Returns the complete cell type hierarchy for 2025 data release

**Arguments:** None

**Returns:** `CellTypeHierarchy`

**Use Case:** Access updated cell type classifications from 2025 data

---

### Query: `geneExpressionSummary` / `geneExpressionSummary2025`

**Description:** Returns expression summary data filtered by data type, gene symbol, cell type, and enrollment category

**Arguments:**
- `dataType` (String, required) - Type of omics data (e.g., "singleCell", "regionalProteomics", "regionalTranscriptomics")
- `geneSymbol` (String) - Gene symbol to query (leave empty if filtering by cell type)
- `cellType` (String) - Cell type to filter by (leave empty if filtering by gene)
- `enrollmentCategory` (String) - Patient enrollment category (e.g., "Healthy", "CKD", "DKD")

**Returns:** `[GeneExpressionSummary]`

**Example Query:**
```graphql
query {
  geneExpressionSummary(
    dataType: "singleCell"
    geneSymbol: "APOL1"
    cellType: ""
    enrollmentCategory: "CKD"
  ) {
    gene
    cellType
    mean
    standardDeviation
  }
}
```

**Use Case:** Retrieve expression statistics for specific genes across cell types or vice versa

---

### Query: `getClusterHieararchies` / `getClusterHieararchies2025`

**Description:** Returns cluster hierarchies for a specific cell type

**Arguments:**
- `cellType` (String, required) - The cell type to get clusters for

**Returns:** `[ClusterHierarchy]`

**Example Query:**
```graphql
query {
  getClusterHieararchies(cellType: "Podocyte") {
    clusterName
    clusterNumber
  }
}
```

**Use Case:** Display subclusters/subdivisions for a given cell type

---

### Query: `getUmapPlotData` / `getUmapPlotData2025`

**Description:** Returns UMAP coordinates for dimensional reduction visualization

**Arguments:**
- `dataType` (String, required) - Type of omics data
- `geneSymbol` (String, required) - Gene to visualize (or empty string for default)
- `enrollmentCategory` (String, required) - Patient enrollment category

**Returns:** `PlotData`

**Example Query:**
```graphql
query {
  getUmapPlotData(
    dataType: "singleCell"
    geneSymbol: "APOL1"
    enrollmentCategory: "Healthy"
  ) {
    title
    xAxisLabel
    yAxisLabel
    plotPoints {
      x
      y
      cellType
      expressionLevel
    }
  }
}
```

**Use Case:** Generate UMAP visualizations with gene expression overlays

---

### Query: `getDataTypeSummaryInformation` / `getDataTypeSummaryInformation2025`

**Description:** Returns summary information about available data types

**Arguments:** None

**Returns:** `[DataTypeSummary]`

**Example Query:**
```graphql
query {
  getDataTypeSummaryInformation {
    dataType
    description
    cellCount
    sampleCount
  }
}
```

**Use Case:** Display available datasets and their statistics

---

### Query: `getSummaryData`

**Description:** Returns overall summary statistics for the atlas

**Arguments:** None

**Returns:** `[DataTypeSummary]`

**Use Case:** Dashboard statistics and overview information

---

### Query: `dataTypesForConcept` / `dataTypesForConcept2025`

**Description:** Returns available data types that contain data for a specific gene or cluster

**Arguments:**
- `geneSymbol` (String) - Gene symbol to query
- `clusterName` (String) - Cluster name to query (provide one or the other)

**Returns:** `[String]` - List of available data types

**Example Query:**
```graphql
query {
  dataTypesForConcept(geneSymbol: "APOL1", clusterName: "")
}
```

**Use Case:** Determine which omics datasets contain data for a particular gene or cluster

---

### Query: `getRTGeneExpressionByEnrollment`

**Description:** Returns regional transcriptomics expression data grouped by enrollment category

**Arguments:**
- `comparisonType` (String, required) - Type of regional comparison
- `geneSymbol` (String, required) - Gene symbol to query

**Returns:** `RTExpressionByEnrollmentCategory`

**Use Case:** Analyze regional expression differences across patient groups

---

### Query: `getRPGeneExpressionByEnrollmentAndProtein`

**Description:** Returns regional proteomics expression data grouped by enrollment category

**Arguments:**
- `geneSymbol` (String, required) - Gene symbol
- `protein` (String, required) - Protein identifier

**Returns:** `RPExpressionByEnrollmentCategory`

**Use Case:** Analyze protein expression across patient enrollment categories

---

### Query: `getRTGeneExpressionByStructure`

**Description:** Returns regional transcriptomics data grouped by anatomical structure

**Arguments:**
- `structure` (String, required) - Kidney anatomical structure (e.g., "glomerulus", "tubule")

**Returns:** `[RTExpressionData]`

**Use Case:** Compare gene expression across different kidney structures

---

### Query: `getRPGeneExpressionByStructure`

**Description:** Returns regional proteomics data grouped by anatomical structure

**Arguments:**
- `structure` (String, required) - Kidney anatomical structure

**Returns:** `[RPExpressionData]`

**Use Case:** Analyze protein expression in specific kidney regions

---

### Query: `getRPGeneExpressionByEnrollment`

**Description:** Returns regional proteomics accession groups by gene symbol and enrollment

**Arguments:**
- `geneSymbol` (String, required) - Gene symbol

**Returns:** `[RPAccessionGroup]`

**Use Case:** Access regional proteomics sample data grouped by gene

---

### Query: `getParticipantClinicalDataset`

**Description:** Returns clinical data for a specific participant

**Arguments:**
- `redcapId` (String, required) - REDCap participant ID

**Returns:** `ParticipantClinicalDataset`

**Example Query:**
```graphql
query {
  getParticipantClinicalDataset(redcapId: "12345") {
    redcapId
    age
    sex
    diagnoses {
      code
      label
    }
  }
}
```

**Use Case:** Retrieve clinical information for a participant

---

### Query: `getDataTypeInformationByParticipant` / `getDataTypeInformationByParticipant2025`

**Description:** Returns experiment type counts for a participant

**Arguments:**
- `redcapId` (String, required) - REDCap participant ID

**Returns:** `ParticipantDataTypeSummary` or `ParticipantDataTypeSummary2025`

**Example Query:**
```graphql
query {
  getDataTypeInformationByParticipant(redcapId: "12345") {
    redcapId
    singleCellCount
    regionalTranscriptomicsCount
    regionalProteomicsCount
  }
}
```

**Use Case:** Display available experiment types for a participant

---

### Query: `getRepoDataTypeInformationByParticipant`

**Description:** Returns repository data type counts for a participant

**Arguments:**
- `redcapId` (String, required) - REDCap participant ID

**Returns:** `ParticipantRepoDataTypeSummary`

**Use Case:** Show available repository data types for a participant

---

### Query: `participantSummaryDataset`

**Description:** Returns summary information for a participant

**Arguments:**
- `redcapId` (String, required) - REDCap participant ID

**Returns:** `ParticipantSummaryDataset`

**Use Case:** Get overview information about a participant

---

### Query: `getTotalParticipantFilesCount`

**Description:** Returns total file count information for a participant

**Arguments:**
- `redcapId` (String, required) - REDCap participant ID

**Returns:** `ParticipantRepoDataTypeInformation`

**Use Case:** Display file counts and repository information

---

### Query: `getEnrollmentCategorySummaryData`

**Description:** Returns summary statistics aggregated by enrollment category

**Arguments:** None

**Returns:** `[ParticipantEnrollmentCategorySummary]`

**Use Case:** Show statistics like participant count per disease category

---

### Query: `getAtlasSummaryRows`

**Description:** Returns comprehensive atlas summary statistics

**Arguments:** None

**Returns:** `AtlasRepoSummaryResult`

**Example Query:**
```graphql
query {
  getAtlasSummaryRows {
    dataTypeInformation {
      label
      participantCount
      sampleCount
    }
  }
}
```

**Use Case:** Dashboard statistics and atlas overview

---

### Query: `getAtlasMessages`

**Description:** Returns informational messages to display to users

**Arguments:** None

**Returns:** `[AtlasMessage]`

**Example Query:**
```graphql
query {
  getAtlasMessages {
    message
    messageType
    displayOrder
  }
}
```

**Use Case:** Display notices, announcements, or important information

---

### Query: `getExperimentalStrategyCountsByParticipant`

**Description:** Returns experimental strategy (sequencing method) counts for a participant

**Arguments:**
- `redcapId` (String, required) - REDCap participant ID

**Returns:** `[ParticipantRepoDataTypeInformation]`

**Use Case:** Display breakdown of experimental strategies used for a participant

---

## Data Types Reference

### Common Return Types

#### AutocompleteResult
```
{
  id: String
  label: String
  category: String
}
```

#### GeneExpressionSummary
```
{
  gene: String
  cellType: String
  mean: Float
  standardDeviation: Float
  enrollmentCategory: String
  dataType: String
}
```

#### PlotData
```
{
  title: String
  xAxisLabel: String
  yAxisLabel: String
  plotPoints: [
    {
      x: Float
      y: Float
      cellType: String
      expressionLevel: Float
    }
  ]
}
```

#### ParticipantClinicalDataset
```
{
  redcapId: String
  age: Int
  sex: String
  diagnoses: [Diagnosis]
  [additional clinical fields]
}
```

#### DataTypeSummary
```
{
  dataType: String
  description: String
  cellCount: Int
  sampleCount: Int
}
```

#### AtlasRepoSummaryResult
```
{
  dataTypeInformation: [
    {
      label: String
      participantCount: Int
      sampleCount: Int
    }
  ]
}
```

---

## Authentication & Security

- **Current Implementation:** No authentication required
- **CORS:** Configured for `/graphql` endpoint
- **Rate Limiting:** Not currently configured (should be added for production)

---

## Error Handling

### GraphQL Errors
Errors are returned in the standard GraphQL error format:

```json
{
  "errors": [
    {
      "message": "Must provide either a cluster or a gene symbol.",
      "extensions": {
        "classification": "INTERNAL_ERROR"
      }
    }
  ]
}
```

### REST Errors
HTTP status codes and error messages in response body:

```json
{
  "timestamp": "2025-12-17T12:00:00Z",
  "status": 400,
  "error": "Bad Request",
  "message": "Invalid parameters"
}
```

---

## Usage Examples

### Example 1: Query Gene Expression Data

```bash
curl -X POST http://localhost:8080/graphql \
  -H "Content-Type: application/json" \
  -d '{
    "query": "query { geneExpressionSummary(dataType: \"singleCell\", geneSymbol: \"APOL1\", cellType: \"\", enrollmentCategory: \"CKD\") { gene cellType mean } }"
  }'
```

### Example 2: Clear Cache

```bash
curl -X GET http://localhost:8080/v1/clearCache
```

### Example 3: Log Frontend Error

```bash
curl -X POST http://localhost:8080/v1/error \
  -H "Content-Type: application/json" \
  -d '{
    "error": "Failed to load gene expression data",
    "stackTrace": "Error at loadData (app.js:123)"
  }'
```

---

## API Status & Monitoring

- **Health Check:** Spring Boot Actuator available (if enabled in application.properties)
- **Swagger/OpenAPI:** Available at `/swagger-ui.html` and `/v3/api-docs`
- **GraphQL IDE:** Available at `/graphiql`

---

## Performance Considerations

1. **Caching:** The application uses Hazelcast for caching gene expression and summary data
2. **Large Result Sets:** Consider adding pagination for queries returning large numbers of results
3. **Query Optimization:** Use specific parameters (enrollmentCategory, dataType) to reduce result sizes

---

## Version Information

- **Current Version:** 3.1.0
- **Spring Boot:** 3.2.5
- **Java:** 21
- **Data Versions:** Current + 2025

---

## Support & Documentation

- **GraphQL Schema:** View at `/graphiql` (interactive documentation)
- **OpenAPI/Swagger:** View at `/swagger-ui.html`
- **Source Code:** See corresponding service classes in `src/main/java/org/kpmp/`
- **GraphQL Definitions:** [knowledge_environment.graphqls](src/main/resources/graphql/knowledge_environment.graphqls)
