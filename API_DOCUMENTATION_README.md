# API Documentation Overview

This project includes comprehensive endpoint documentation. Choose the resource that best fits your needs:

## 📚 Documentation Resources

### For Quick Answers
📄 **[API_QUICK_REFERENCE.md](API_QUICK_REFERENCE.md)** (Start here!)
- Quick reference table of all endpoints
- Common task examples with code
- Status codes and error formats
- Performance tips and troubleshooting

### For Detailed Endpoint Information
📖 **[ENDPOINTS.md](ENDPOINTS.md)**
- Complete endpoint reference with full descriptions
- All GraphQL queries documented
- Request/response examples
- Data types reference
- Authentication and error handling details

### For Developers
👨‍💻 **[DEVELOPER_GUIDE.md](DEVELOPER_GUIDE.md)**
- Getting started guide
- GraphQL vs REST comparison
- Best practices and patterns
- Complete code examples
- Troubleshooting guide with solutions

## 🚀 Quick Start

### 1. Start the Application
```bash
./gradlew bootRun
```

### 2. Access Documentation Interfaces

| Resource | URL | Best For |
|----------|-----|----------|
| **Swagger UI** | http://localhost:8080/swagger-ui.html | Exploring REST endpoints |
| **GraphQL IDE** | http://localhost:8080/graphiql | Testing GraphQL queries |
| **OpenAPI Spec** | http://localhost:8080/v3/api-docs | API specification |

### 3. Make Your First Request

#### Using GraphQL:
```bash
curl -X POST http://localhost:8080/graphql \
  -H "Content-Type: application/json" \
  -d '{
    "query": "query { autocomplete(searchTerm: \"APOL\") { label } }"
  }'
```

#### Using REST:
```bash
curl -X GET http://localhost:8080/v1/clearCache
```

## 📋 What This API Provides

### Data Access
- **Gene Expression Data** - Single-cell RNA, regional transcriptomics, regional proteomics
- **Cell Type Hierarchies** - Cell type classifications and clustering
- **UMAP Visualization Data** - Dimensional reduction coordinates
- **Participant Clinical Data** - Patient information and experiment metadata
- **Summary Statistics** - Overview counts and data availability

### API Management
- **GraphQL Endpoint** - Flexible schema-based queries
- **Error Logging** - Frontend error tracking
- **Cache Management** - Clear and refresh cached data

## 🏗️ Architecture

### Two API Approaches

**GraphQL API** (`POST /graphql`)
- Query exactly what you need
- Strongly typed schema
- Self-documenting
- Best for web/mobile applications
- Interactive IDE at `/graphiql`

**REST API** (`/v1/*`)
- Simple HTTP operations
- Fixed response structure
- Standard HTTP semantics
- Best for server-to-server integrations
- Swagger documentation at `/swagger-ui.html`

## 🔍 Finding What You Need

### By Use Case

**I want to...**

- [Retrieve gene expression data](#retrieve-gene-expression-data)
- [Display cell type information](#display-cell-type-information)
- [Get UMAP visualization data](#get-umap-visualization-data)
- [Access participant clinical data](#access-participant-clinical-data)
- [Refresh cached data](#refresh-cached-data)
- [Log frontend errors](#log-frontend-errors)

### Retrieve Gene Expression Data
- **Endpoint**: `POST /graphql` with `geneExpressionSummary` query
- **See**: [ENDPOINTS.md - geneExpressionSummary](ENDPOINTS.md#query-geneexpressionsummary--geneexpressionsummary2025)
- **Example**: [API_QUICK_REFERENCE.md - Query Gene Expression](API_QUICK_REFERENCE.md#3-query-gene-expression)
- **Guide**: [DEVELOPER_GUIDE.md - Example 1](DEVELOPER_GUIDE.md#example-1-gene-expression-analysis-tool)

### Display Cell Type Information
- **Endpoint**: `POST /graphql` with `cellTypeHierarchy` query
- **See**: [ENDPOINTS.md - cellTypeHierarchy](ENDPOINTS.md#query-celltypehierarchy)
- **Example**: [API_QUICK_REFERENCE.md - Get Cell Type Information](API_QUICK_REFERENCE.md#2-get-cell-type-information)

### Get UMAP Visualization Data
- **Endpoint**: `POST /graphql` with `getUmapPlotData` query
- **See**: [ENDPOINTS.md - getUmapPlotData](ENDPOINTS.md#query-getumapplotdata--getumapplotdata2025)
- **Example**: [API_QUICK_REFERENCE.md - Get UMAP Plot Data](API_QUICK_REFERENCE.md#4-get-umap-plot-data)
- **Guide**: [DEVELOPER_GUIDE.md - Example 2](DEVELOPER_GUIDE.md#example-2-umap-visualization)

### Access Participant Clinical Data
- **Endpoint**: `POST /graphql` with `getParticipantClinicalDataset` query
- **See**: [ENDPOINTS.md - getParticipantClinicalDataset](ENDPOINTS.md#query-getparticipantclinicaldataset)
- **Example**: [API_QUICK_REFERENCE.md - Get Participant Information](API_QUICK_REFERENCE.md#5-get-participant-information)

### Refresh Cached Data
- **Endpoint**: `GET /v1/clearCache`
- **See**: [ENDPOINTS.md - Cache Management](ENDPOINTS.md#cache-management)
- **Example**: [API_QUICK_REFERENCE.md - Clear Server Caches](API_QUICK_REFERENCE.md#6-clear-server-caches)

### Log Frontend Errors
- **Endpoint**: `POST /v1/error`
- **See**: [ENDPOINTS.md - Error Management](ENDPOINTS.md#error-management)
- **Example**: [API_QUICK_REFERENCE.md - Log Frontend Error](API_QUICK_REFERENCE.md#7-log-frontend-error)

## 📖 Documentation by Role

### API Consumers (Frontend/Mobile Developers)
1. Start with [API_QUICK_REFERENCE.md](API_QUICK_REFERENCE.md)
2. Visit `/graphiql` to explore available queries
3. Reference [DEVELOPER_GUIDE.md](DEVELOPER_GUIDE.md) for best practices
4. Use [ENDPOINTS.md](ENDPOINTS.md) for detailed parameter information

### Backend/Server Integration
1. Review [ENDPOINTS.md - REST Endpoints](ENDPOINTS.md#rest-endpoints)
2. Check [DEVELOPER_GUIDE.md - Best Practices](DEVELOPER_GUIDE.md#best-practices)
3. Reference error handling sections for retry logic

### DevOps/Infrastructure
1. Check [ENDPOINTS.md - API Status & Monitoring](ENDPOINTS.md#api-status--monitoring)
2. Review cache management endpoints
3. Monitor error logs via `/v1/error` endpoint

### API Developers (Maintaining This API)
1. See code comments in controller classes
2. Review [OpenAPI Configuration](src/main/java/org/kpmp/OpenAPIConfiguration.java)
3. Check Spring GraphQL configuration in [QueryController](src/main/java/org/kpmp/QueryController.java)

## 🔐 Security Notes

**Current Implementation:**
- No authentication required
- CORS enabled for GraphQL

**Recommendations for Production:**
- Implement API key or JWT authentication
- Add rate limiting
- Use HTTPS/TLS
- Implement request validation
- Add API gateway
- Monitor for abuse

See [DEVELOPER_GUIDE.md - Authentication](DEVELOPER_GUIDE.md#authentication) for implementation options.

## 📊 API Statistics

- **GraphQL Queries**: 30+ available queries
- **REST Endpoints**: 2 endpoints
- **Supported Data Types**: Single-cell RNA, Regional Transcriptomics, Regional Proteomics
- **Data Versions**: Current + 2025
- **Response Caching**: Hazelcast-based caching

## 🛠️ Useful Resources

- **Interactive Query Testing**: http://localhost:8080/graphiql
- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **OpenAPI Spec**: http://localhost:8080/v3/api-docs
- **Server Health Check**: http://localhost:8080/actuator (if enabled)

## ⚡ Performance Tips

1. Use specific parameters in queries (dataType, enrollmentCategory)
2. Request only the fields you need
3. Implement client-side caching
4. Use GraphQL variables for dynamic queries
5. Check cache status regularly with `/v1/clearCache`

See [DEVELOPER_GUIDE.md - Performance Optimization](DEVELOPER_GUIDE.md#1-performance-optimization) for details.

## 🆘 Troubleshooting

### Documentation
- See [API_QUICK_REFERENCE.md - Troubleshooting](API_QUICK_REFERENCE.md#troubleshooting)
- See [DEVELOPER_GUIDE.md - Troubleshooting](DEVELOPER_GUIDE.md#troubleshooting)

### Interactive Help
1. Visit `/graphiql` and explore the schema
2. Use `/swagger-ui.html` to understand REST endpoints
3. Check server logs for detailed error messages

## 📝 Updating Documentation

This documentation is maintained alongside the code. When updating the API:

1. Update code comments and JavaDoc
2. Update OpenAPI annotations in controllers
3. Update ENDPOINTS.md with new endpoints
4. Add examples to DEVELOPER_GUIDE.md if adding complex features
5. Update API_QUICK_REFERENCE.md if changing common endpoints

## 📞 Support

For questions or issues:
1. Check the relevant documentation file above
2. Explore the GraphQL schema at `/graphiql`
3. Review Swagger documentation at `/swagger-ui.html`
4. Check server logs for detailed error information
5. Contact KPMP team at https://www.kpmp.org

## 📋 Documentation Files Summary

| File | Purpose | Audience | Length |
|------|---------|----------|--------|
| **API_QUICK_REFERENCE.md** | Quick lookup of common tasks | All users | ~2 min read |
| **ENDPOINTS.md** | Complete endpoint reference | API consumers | ~15 min read |
| **DEVELOPER_GUIDE.md** | Development best practices | Developers | ~20 min read |

---

**Last Updated:** December 17, 2025  
**API Version:** 3.1.0  
**Spring Boot Version:** 3.2.5  
**Java Version:** 21
