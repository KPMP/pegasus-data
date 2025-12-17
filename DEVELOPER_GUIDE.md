# KPMP Pegasus Data API - Developer Guide

## Table of Contents
1. [Getting Started](#getting-started)
2. [Authentication](#authentication)
3. [API Endpoints Overview](#api-endpoints-overview)
4. [GraphQL Guide](#graphql-guide)
5. [REST Endpoints](#rest-endpoints)
6. [Best Practices](#best-practices)
7. [Examples](#examples)
8. [Troubleshooting](#troubleshooting)

---

## Getting Started

### Prerequisites
- Endpoint: `http://localhost:8080` (or production URL)
- HTTP client (cURL, Postman, etc.) or language-specific HTTP library
- Basic understanding of REST APIs or GraphQL (for advanced use)

### Available Documentation Interfaces

After starting the server, access these documentation resources:

1. **Swagger/OpenAPI UI** - Best for exploring REST endpoints
   ```
   http://localhost:8080/swagger-ui.html
   ```

2. **GraphQL IDE** - Best for exploring and testing GraphQL queries
   ```
   http://localhost:8080/graphiql
   ```

3. **OpenAPI Specification** - For integration with code generation tools
   ```
   http://localhost:8080/v3/api-docs
   ```

4. **This Documentation** - Complete reference for all endpoints
   ```
   See ENDPOINTS.md
   ```

---

## Authentication

### Current Status
**No authentication is currently required.**

### Production Recommendations
For production deployments, implement one of:

#### Option 1: API Key Authentication
```bash
curl -X POST http://api.example.com/graphql \
  -H "Content-Type: application/json" \
  -H "X-API-Key: your-api-key" \
  -d '{"query": "..."}'
```

#### Option 2: JWT Bearer Token
```bash
curl -X POST http://api.example.com/graphql \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer eyJhbGc..." \
  -d '{"query": "..."}'
```

#### Option 3: OAuth2
Recommended for public-facing APIs with third-party integrations.

---

## API Endpoints Overview

### Two API Types Available

| Aspect | GraphQL | REST |
|--------|---------|------|
| **Endpoint** | `POST /graphql` | `/v1/*` |
| **Data Queries** | All data access | Error logging, cache mgmt |
| **IDE Available** | Yes (`/graphiql`) | Yes (`/swagger-ui.html`) |
| **Flexibility** | Very high (query what you need) | Fixed response structure |
| **Use Case** | Frontend apps, complex queries | Server-to-server, simple ops |

### Quick Comparison

**Choose GraphQL if:**
- You want specific fields only (reduces bandwidth)
- You're building a web/mobile app
- You need complex queries with multiple data types
- You want self-documenting queries

**Choose REST if:**
- You prefer simpler HTTP semantics
- You're doing server-to-server integrations
- You want to use standard HTTP caching headers
- You're new to GraphQL

---

## GraphQL Guide

### What is GraphQL?

GraphQL is a query language that lets you request exactly what data you need. Unlike REST where endpoints return fixed data structures, GraphQL lets you specify fields.

### GraphQL Endpoint

```
POST /graphql
Content-Type: application/json
```

### Basic Query Structure

```graphql
query {
  queryName(arg1: "value1", arg2: "value2") {
    field1
    field2
    nestedObject {
      subfield
    }
  }
}
```

### Example: Simple Query

```graphql
query GetAutocomplete {
  autocomplete(searchTerm: "APOL") {
    id
    label
    category
  }
}
```

### HTTP Request

```bash
curl -X POST http://localhost:8080/graphql \
  -H "Content-Type: application/json" \
  -d '{
    "query": "query { autocomplete(searchTerm: \"APOL\") { id label category } }"
  }'
```

### Response

```json
{
  "data": {
    "autocomplete": [
      {
        "id": "APOL1",
        "label": "APOL1",
        "category": "Gene"
      },
      {
        "id": "APOL2",
        "label": "APOL2",
        "category": "Gene"
      }
    ]
  }
}
```

### Handling Errors

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

### Common Query Patterns

#### 1. Query with Multiple Arguments

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

#### 2. Query Multiple Data Types

```graphql
query {
  current: cellTypeHierarchy {
    definition
  }
  new: cellTypeHierarchy2025 {
    definition
  }
}
```

#### 3. Conditional Queries

```graphql
query GetConcepts($gene: String!, $cluster: String) {
  dataTypesForConcept(geneSymbol: $gene, clusterName: $cluster)
}
```

### Query Variables (Using Variables Instead of Literals)

```bash
curl -X POST http://localhost:8080/graphql \
  -H "Content-Type: application/json" \
  -d '{
    "query": "query GetExpressionData($symbol: String!, $type: String!) { geneExpressionSummary(dataType: $type, geneSymbol: $symbol, cellType: \"\", enrollmentCategory: \"CKD\") { gene mean } }",
    "variables": {
      "symbol": "APOL1",
      "type": "singleCell"
    }
  }'
```

### Using the GraphQL IDE

The interactive GraphQL IDE at `/graphiql` provides:

1. **Query Editor** - Left panel for writing queries
2. **Results Panel** - Right side showing results
3. **Schema Explorer** - Documentation of available queries and types
4. **Auto-completion** - Syntax highlighting and field suggestions
5. **Historical Queries** - Query history for previous requests

**To use it:**
1. Navigate to `http://localhost:8080/graphiql`
2. Type your query in the left panel
3. Press Ctrl+Enter or click the play button
4. View results on the right
5. Use Ctrl+Space for autocomplete suggestions

---

## REST Endpoints

### Base Path
```
/v1/
```

### Endpoints Available

#### 1. Clear Cache

```http
GET /v1/clearCache
```

**Purpose:** Refresh all cached data

**Response:**
```json
{
  "message": "Caches successfully cleared!",
  "cacheNames": ["gene-expression-cache", "umap-cache", "summary-cache"]
}
```

**When to use:**
- After data updates in the database
- Testing to ensure fresh data
- Troubleshooting cache-related issues

#### 2. Log Error

```http
POST /v1/error
Content-Type: application/json
```

**Request Body:**
```json
{
  "error": "Failed to load gene expression data",
  "stackTrace": "Error at loadGeneData (app.js:125)\n  at process.js:90"
}
```

**Response:**
```json
true
```

**When to use:**
- Frontend applications logging errors to server
- Error tracking and monitoring
- Debugging user-reported issues

---

## Best Practices

### 1. Performance Optimization

#### Use Specific Parameters
```graphql
// GOOD: Specific filters
query {
  geneExpressionSummary(
    dataType: "singleCell"
    geneSymbol: "APOL1"
    cellType: ""
    enrollmentCategory: "CKD"
  ) {
    mean
    standardDeviation
  }
}

// AVOID: Broad queries without filters
query {
  geneExpressionSummary(
    dataType: ""
    geneSymbol: ""
    cellType: ""
    enrollmentCategory: ""
  ) {
    ... all fields ...
  }
}
```

#### Select Only Needed Fields
```graphql
// GOOD: Only requested fields
query {
  autocomplete(searchTerm: "APOL") {
    label
  }
}

// AVOID: Requesting unnecessary fields
query {
  autocomplete(searchTerm: "APOL") {
    id
    label
    category
    description
    metadata
    tags
  }
}
```

### 2. Error Handling

Always handle errors in your code:

```javascript
async function queryAPI(query) {
  try {
    const response = await fetch('/graphql', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ query })
    });
    
    const result = await response.json();
    
    // Check for GraphQL errors
    if (result.errors) {
      console.error('GraphQL Error:', result.errors[0].message);
      return null;
    }
    
    return result.data;
  } catch (error) {
    console.error('Network Error:', error);
    return null;
  }
}
```

### 3. Caching Strategy

```javascript
// Cache results locally to reduce API calls
const cache = new Map();

async function getCellTypes(cellType) {
  const cacheKey = `cell-types-${cellType}`;
  
  if (cache.has(cacheKey)) {
    return cache.get(cacheKey);
  }
  
  const data = await queryAPI(`query { getClusterHieararchies(cellType: "${cellType}") { ... } }`);
  cache.set(cacheKey, data);
  return data;
}

// Clear cache after significant time or on manual refresh
function clearLocalCache() {
  cache.clear();
  // Also clear server cache if needed
  fetch('/v1/clearCache');
}
```

### 4. Request/Response Size Management

```graphql
// GOOD: Paginated results (if API supported)
query GetExpression($limit: Int!, $offset: Int!) {
  geneExpressionSummary(
    dataType: "singleCell"
    geneSymbol: "APOL1"
    cellType: ""
    enrollmentCategory: "CKD"
    limit: $limit
    offset: $offset
  ) {
    mean
  }
}

// Fetch first 100 results
variables: { "limit": 100, "offset": 0 }
```

### 5. Monitoring and Logging

```javascript
function logAPIMetrics(query, responseTime, resultSize) {
  console.log({
    timestamp: new Date().toISOString(),
    query: query.substring(0, 50) + '...',
    responseTime: responseTime + 'ms',
    resultSize: resultSize + ' bytes'
  });
}
```

---

## Examples

### Example 1: Gene Expression Analysis Tool

**Goal:** Build a tool to compare gene expression across cell types

```javascript
async function compareGeneExpressionAcrossCellTypes(gene, dataType, enrollmentCategory) {
  const query = `
    query {
      summary: geneExpressionSummary(
        dataType: "${dataType}"
        geneSymbol: "${gene}"
        cellType: ""
        enrollmentCategory: "${enrollmentCategory}"
      ) {
        gene
        cellType
        mean
        standardDeviation
      }
    }
  `;
  
  const response = await fetch('/graphql', {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ query })
  });
  
  const result = await response.json();
  
  if (result.errors) {
    throw new Error(result.errors[0].message);
  }
  
  // Sort by expression level
  return result.data.summary.sort((a, b) => b.mean - a.mean);
}

// Usage
const data = await compareGeneExpressionAcrossCellTypes('APOL1', 'singleCell', 'CKD');
console.table(data);
```

### Example 2: UMAP Visualization

**Goal:** Display gene expression on UMAP coordinates

```javascript
async function generateUMAPVisualization(gene, dataType, enrollmentCategory) {
  const query = `
    query {
      plotData: getUmapPlotData(
        dataType: "${dataType}"
        geneSymbol: "${gene}"
        enrollmentCategory: "${enrollmentCategory}"
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
  `;
  
  const response = await fetch('/graphql', {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ query })
  });
  
  const result = await response.json();
  
  // Use a visualization library like D3.js or Plotly
  visualizeUMAP(result.data.plotData);
}
```

### Example 3: Multi-Version Data Comparison

**Goal:** Compare current vs 2025 data

```javascript
async function compareDataVersions(cellType) {
  const query = `
    query {
      current: getClusterHieararchies(cellType: "${cellType}") {
        clusterName
        clusterNumber
      }
      new: getClusterHieararchies2025(cellType: "${cellType}") {
        clusterName
        clusterNumber
      }
    }
  `;
  
  const response = await fetch('/graphql', {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ query })
  });
  
  const result = await response.json();
  
  return {
    currentVersion: result.data.current,
    newVersion: result.data.new
  };
}
```

### Example 4: Error Handling and Retry Logic

```javascript
async function queryWithRetry(query, maxRetries = 3) {
  for (let attempt = 1; attempt <= maxRetries; attempt++) {
    try {
      const response = await fetch('/graphql', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ query }),
        timeout: 5000
      });
      
      if (!response.ok) {
        throw new Error(`HTTP ${response.status}`);
      }
      
      const result = await response.json();
      
      if (result.errors) {
        throw new Error(result.errors[0].message);
      }
      
      return result.data;
    } catch (error) {
      console.warn(`Attempt ${attempt} failed:`, error.message);
      
      if (attempt === maxRetries) {
        throw error;
      }
      
      // Exponential backoff
      await new Promise(resolve => setTimeout(resolve, Math.pow(2, attempt) * 1000));
    }
  }
}

// Usage
try {
  const data = await queryWithRetry('query { autocomplete(searchTerm: "APOL") { label } }');
  console.log(data);
} catch (error) {
  console.error('Failed after retries:', error);
}
```

### Example 5: Data Export to CSV

```javascript
async function exportGeneExpressionToCSV(gene, dataType, enrollmentCategory) {
  const query = `
    query {
      geneExpressionSummary(
        dataType: "${dataType}"
        geneSymbol: "${gene}"
        cellType: ""
        enrollmentCategory: "${enrollmentCategory}"
      ) {
        gene
        cellType
        mean
        standardDeviation
      }
    }
  `;
  
  const response = await fetch('/graphql', {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ query })
  });
  
  const result = await response.json();
  const data = result.data.geneExpressionSummary;
  
  // Convert to CSV
  const headers = Object.keys(data[0] || {});
  const csvContent = [
    headers.join(','),
    ...data.map(row => headers.map(h => row[h]).join(','))
  ].join('\n');
  
  // Download
  const blob = new Blob([csvContent], { type: 'text/csv' });
  const url = window.URL.createObjectURL(blob);
  const a = document.createElement('a');
  a.href = url;
  a.download = `${gene}-expression.csv`;
  a.click();
}
```

---

## Troubleshooting

### Common Issues and Solutions

#### Issue: "Unknown argument" Error

**Symptom:**
```json
{
  "errors": [{
    "message": "Unknown argument \"geneSymbol\""
  }]
}
```

**Solutions:**
1. Check the GraphQL schema at `/graphiql`
2. Verify argument names match exactly (case-sensitive)
3. Ensure you're using the correct query name

#### Issue: Empty Results

**Symptom:**
```json
{
  "data": {
    "geneExpressionSummary": []
  }
}
```

**Solutions:**
1. Verify gene symbol exists using `autocomplete` query
2. Check data type is valid (e.g., "singleCell", "regionalTranscriptomics")
3. Verify enrollment category is correct (e.g., "CKD", "Healthy")
4. Use `dataTypesForConcept` to check if data exists

#### Issue: 500 Server Error

**Symptom:**
```json
{
  "errors": [{
    "message": "Internal server error"
  }]
}
```

**Solutions:**
1. Check server logs for detailed error
2. Verify database is running and accessible
3. Try clearing cache: `GET /v1/clearCache`
4. Restart the application if needed

#### Issue: Slow Query Performance

**Symptom:** Query takes more than 5 seconds to respond

**Solutions:**
1. Use more specific parameters (add enrollmentCategory, dataType filters)
2. Request only needed fields (don't select unnecessary fields)
3. Check server cache status: `GET /v1/clearCache`
4. Consider implementing client-side caching

#### Issue: CORS Errors in Browser

**Symptom:**
```
Access to XMLHttpRequest has been blocked by CORS policy
```

**Solutions:**
1. Verify CORS is enabled in application configuration
2. Make requests from allowed origins
3. Use server-side proxy for cross-origin requests
4. Check browser console for specific headers needed

---

## Summary

- **GraphQL** is preferred for flexible data queries (use `/graphql`)
- **REST endpoints** for admin operations like cache clearing (`/v1/clearCache`)
- **Swagger UI** provides REST endpoint documentation (`/swagger-ui.html`)
- **GraphQL IDE** provides query documentation and testing (`/graphiql`)
- **Always handle errors** in your code
- **Use specific parameters** for better performance
- **Check availability** using helper queries before main query

For more details, see [ENDPOINTS.md](ENDPOINTS.md) and [API_QUICK_REFERENCE.md](API_QUICK_REFERENCE.md).
