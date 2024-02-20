<h3>API Generation</h3>
<p>
This API was generated from a Swagger file using the OpenAPI Generator tool.
The Swagger file served as the source of truth for defining the API endpoints,
<br/><b>src/main/resources/openapi</b> <br/>
request/response schemas, and other specifications.
</p>

<h3>Database</h3>
<p>
The data for this API is stored in a PostgreSQL database. The database management system used is Liquibase for version control of the database schema.
</p>
<h3>Docker</h3>
<p>
To set up the PostgreSQL database, a Docker container is utilized. The PostgreSQL image is loaded and configured using Docker.
</p>

<h3>Testing</h3>
<p>
The functionality of this API is tested using Nutrition Response Testing (NRT). NRT is a method used to assess the body's imbalances and determine the appropriate treatment plan.
</p>
<h3>Postman</h3>
<p>
To test all API endpoints, you can import the collection file located at src/main/resources/nrt into Postman. This collection contains pre-configured requests for testing each API endpoint.
</p>
