# Test Java Project

## Overview
This project is a simple implementation using **Java 21**, **Spring Boot 3.4.2**, and an **H2 in-memory database**. It models a pricing system for products associated with brands.

## Features
- **H2 Database** for in-memory persistence.
- REST API for querying price details.
- Entity relationships between brands, products, and prices.
- Automatic loading of test data on application startup.

## Requirements
- **Java 21**
- **Maven 3.8+**

## Project Structure
### Entity Relationships
The database consists of three main tables:

1. **Brand** (`brand`): Represents a company/brand.
2. **Product** (`product`): Represents a product, associated with a brand.
3. **Prices** (`prices`): Stores pricing information for products within specific time ranges.

### Entity Classes

#### `PricesEntity`
- Maps to the `prices` table.
- Key fields:
    - `brandId`: References the brand.
    - `productId`: References the product.
    - `startDate` and `endDate`: Define the validity period for the price.
    - `priority`: Defines the priority of the price in case of overlapping ranges.
    - `price`: The actual price of the product.
    - `curr`: The currency of the price (converted via `CurrencyConverter`).

#### `ProductEntity`
- Maps to the `product` table.
- Key fields:
    - `productId`: Unique identifier of the product.
    - `brand`: The associated brand.
    - `productName`: The name of the product.

#### `BrandEntity`
- Maps to the `brand` table.
- Key fields:
    - `brandId`: Unique identifier of the brand.
    - `brandName`: The name of the brand.

## Database Configuration
The application uses an **H2 in-memory database**. No additional setup is required. Test data is automatically loaded on startup from the `data.sql` file.

**It was added two indexes in `schema.sql` to improve the query performance of the search in the project. In case of a new query check if necesary add another.**

### H2 Console Access
You can access the H2 database console at:
```
http://localhost:8080/h2-console
```
- **JDBC URL:** `jdbc:h2:mem:testdb`
- **Username:** `sa`

## Test Data
The application preloads the following sample data:

### Brands
| brandId | brandName |
|---------|-----------|
| 1       | BrandB    |
| 0       | BrandA    |

### Products
| productId | brandId | productName     |
|-----------|---------|-----------------|
| 35455     | 1       | Producto BrandB |

### Prices
| priceId | productId | brandId | startDate           | endDate             | priceList | priority | price  | curr |
|---------|-----------|---------|---------------------|---------------------|-----------|----------|--------|------|
| 1       | 35455     | 1       | 2020-06-14 00:00:00 | 2020-12-31 23:59:59 | 1         | 0        | 35.50  | EUR  |
| 2       | 35455     | 1       | 2020-06-14 15:00:00 | 2020-06-14 18:30:00 | 2         | 1        | 25.45  | EUR  |
| 3       | 35455     | 1       | 2020-06-15 00:00:00 | 2020-06-15 11:00:00 | 3         | 1        | 30.50  | EUR  |
| 4       | 35455     | 1       | 2020-06-15 16:00:00 | 2020-12-31 23:59:59 | 4         | 1        | 38.95  | EUR  |

## How to Run
1. Clone the repository.
2. Build the project using Maven:
   ```bash
   mvn clean install
   ```
3. Run the application:
   ```bash
   mvn spring-boot:run
   ```
4. The application will be available at `http://localhost:8080`.

## API Endpoints
### Query Price by Date, Product, and Brand
This endpoint retrieves the price of a product for a specific brand and date.

**GET** `/v1/price`

#### Request Parameters:
- `date` (required): The date to query prices (format: `yyyy-MM-dd HH:mm:ss`).
- `productId` (required): The product ID.
- `brandId` (required): The brand ID.

#### Example CURL Request:
```http
curl -X GET "http://localhost:8080/v1/price?dateTime=2020-06-14T10:00:00&productId=35455&brandName=BrandB" -H "Content-Type: application/json"
```

#### Example Response:
```json
{
  "brandName": "BrandB",
  "productId": 35455,
  "price": 35.50,
  "curr": "EUR"
}
```

## Unit Testing
The project includes unit tests and Integration test for the logic of the project. Run tests using:
```bash
mvn test
```
## Code Coverage with JaCoCo
How to Generate Coverage Report
1. run:
```bash
mvn test
```
2. Open the report:
```bash
target/site/jacoco/index.html
```
## Future Improvements
- Add Swagger for API documentation.
- Implement more comprehensive exception handling.
- Extend test coverage for edge cases.
- Remove H2 and use an non memory DB

