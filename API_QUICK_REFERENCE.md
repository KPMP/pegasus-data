# KPMP Pegasus Data API - Quick Reference

## Documentation Locations

| Resource | URL | Purpose |
|----------|-----|---------|
| **Interactive Swagger UI** | `/swagger-ui.html` | Browse and test REST endpoints |
| **GraphQL IDE** | `/graphiql` | Interactive GraphQL query explorer |
| **OpenAPI JSON** | `/v3/api-docs` | Machine-readable API specification |
| **OpenAPI YAML** | `/v3/api-docs.yaml` | YAML format API specification |
| **This File** | `ENDPOINTS.md` | Complete API documentation |

## Common Tasks

### 1. Find Autocomplete Suggestions
```graphql
POST /graphql
{
  "query": "query { autocomplete(searchTerm: \"APOL\") { id label category } }"
}
```

### 2. Get Cell Type Information
```graphql
POST /graphql
{
  "query": "query { cellTypeHierarchy { definition paths { path color } } }"
}
```

### 3. Query Gene Expression
```graphql
POST /graphql
{
  "query": "query { geneExpressionSummary(dataType: \"singleCell\", geneSymbol: \"APOL1\", cellType: \"\", enrollmentCategory: \"CKD\") { gene cellType mean standardDeviation } }"
}
```

### 4. Get UMAP Plot Data
```graphql
POST /graphql
{
  "query": "query { getUmapPlotData(dataType: \"singleCell\", geneSymbol: \"APOL1\", enrollmentCategory: \"Healthy\") { title xAxisLabel yAxisLabel plotPoints { x y cellType expressionLevel } } }"
}
```

### 5. Get Participant Information
```graphql
POST /graphql
{
  "query": "query { getParticipantClinicalDataset(redcapId: \"12345\") { redcapId age sex } }"
}
```

### 6. Clear Server Caches
```bash
GET /v1/clearCache
```

### 7. Log Frontend Error
```bash
POST /v1/error
Content-Type: application/json

{
  "error": "Failed to load data",
  "stackTrace": "Error at loadGene (app.js:123)"
}
```

## Data Types Quick Reference

| Data Type | Parameter Value | Description |
|-----------|-----------------|-------------|
| Single Cell RNA-seq | `singleCell` | Single-cell gene expression |
| Regional Transcriptomics | `regionalTranscriptomics` | Bulk RNA from kidney regions |
| Regional Proteomics | `regionalProteomics` | Protein abundance in kidney regions |
| Clinical Data | N/A | Participant clinical information |

## Enrollment Categories

- `Healthy` - Healthy control participants
- `CKD` - Chronic Kidney Disease
- `DKD` - Diabetic Kidney Disease
- `ADPKD` - Autosomal Dominant Polycystic Kidney Disease
- `IgAN` - IgA Nephropathy

## Common Kidney Structures (Regional Data)

- `glomerulus` - Filtration unit
- `tubule` - Renal tubule
- `interstitium` - Kidney tissue between structures
- `vasculature` - Blood vessels
- `cortex` - Outer kidney region
- `medulla` - Inner kidney region

## Response Status Codes

| Code | Meaning | Common Causes |
|------|---------|---------------|
| 200 | Success | Request processed successfully |
| 400 | Bad Request | Invalid parameters or query format |
| 404 | Not Found | Requested resource doesn't exist |
| 500 | Server Error | Database or processing error |
| 503 | Unavailable | Server temporarily down |

## Error Response Format

### GraphQL Errors
```json
{
  "errors": [
    {
      "message": "Error description",
      "extensions": {
        "classification": "INTERNAL_ERROR"
      }
    }
  ]
}
```

### REST Errors
```json
{
  "timestamp": "2025-12-17T12:00:00Z",
  "status": 400,
  "error": "Bad Request",
  "message": "Specific error message"
}
```

## Tips for API Usage

1. **Use Specific Parameters**: Narrow down queries with dataType, enrollmentCategory, and other filters to improve performance
2. **Check Available Data Types**: Call `dataTypesForConcept` to see what data exists before querying
3. **Leverage Autocomplete**: Use the autocomplete query to discover valid gene symbols and cell types
4. **Version Awareness**: Most queries have both current and `2025` variants - choose based on your data needs
5. **Cache Management**: Call `/v1/clearCache` after data updates to refresh cached results

## Rate Limiting

Currently not implemented. For production use, recommend adding rate limiting to prevent abuse.

## Authentication

No authentication currently required. For production deployment, recommend:
- Adding API key authentication
- Implementing JWT tokens
- Using API Gateway with OAuth2

## Performance Tips

1. **Caching**: Results are cached using Hazelcast - repeated queries are faster
2. **Query Optimization**: 
   - Provide specific enrollmentCategory values
   - Use dataType parameter to filter results
   - Avoid returning unnecessary fields
3. **Large Result Sets**: Consider paginating queries that return many items

## Testing Your API

### Using cURL
```bash
# Test GraphQL endpoint
curl -X POST http://localhost:8080/graphql \
  -H "Content-Type: application/json" \
  -d '{"query": "query { autocomplete(searchTerm: \"APOL\") { label } }"}'

# Test cache clearing
curl -X GET http://localhost:8080/v1/clearCache
```

### Using Python
```python
import requests
import json

url = "http://localhost:8080/graphql"
query = """
query {
  autocomplete(searchTerm: "APOL") {
    id
    label
    category
  }
}
"""

response = requests.post(url, json={"query": query})
print(response.json())
```

### Using JavaScript
```javascript
const query = `
  query {
    autocomplete(searchTerm: "APOL") {
      id
      label
      category
    }
  }
`;

fetch('http://localhost:8080/graphql', {
  method: 'POST',
  headers: { 'Content-Type': 'application/json' },
  body: JSON.stringify({ query })
})
.then(res => res.json())
.then(data => console.log(data));
```

## Troubleshooting

### Issue: "Unknown argument" in GraphQL
- **Cause**: Parameter name or value is incorrect
- **Solution**: Check the query signature, verify field names match schema

### Issue: Empty results
- **Cause**: No data matches your criteria
- **Solution**: Use autocomplete to find valid values; check data availability with `dataTypesForConcept`

### Issue: 500 Server Error
- **Cause**: Database connection issue or processing error
- **Solution**: Check server logs; verify database is running; try clearing cache

### Issue: Slow responses
- **Cause**: Large result set or missing indexes
- **Solution**: Add more specific parameters; use enrollment category filters; check cache status

## Support

For issues, questions, or documentation improvements:
1. Check the GraphQL schema at `/graphiql`
2. View the Swagger documentation at `/swagger-ui.html`
3. Review server logs for detailed error messages
4. Contact the KPMP team at https://www.kpmp.org
