# Customer Management System (Mock 1 Project)

## Project Description

The Customer Management System is a Java-based console application designed to manage customer, product, and order data in a structured, modular environment. This system allows users to load, add, update, delete, validate, and export data across three primary entities: Customers, Products, and Orders. It also includes functionality for logging errors encountered during data processing.

### Project Structure
```
Customer Management System
│
├── controller                  # Handles user interactions and business workflows
│   ├── CustomerController      # Manages operations related to customer data
│   ├── ProductController       # Manages product-related operations, including top sellers
│   └── OrderController         # Manages order-related operations, including order searches
│
├── service                     # Contains core business logic for each data type
│   ├── BaseService             # Abstract base class providing common data handling methods
│   ├── CustomerService         # Handles customer-specific operations
│   ├── ProductService          # Handles product-specific operations
│   └── OrderService            # Handles order-specific operations
│
├── dal                         # Data Access Layer (responsible for file I/O operations)
│   ├── DataLoader              # Loads data from files into the application
│   └── DataWriter              # Writes processed data back to files
│
├── model                       # Defines entities and their interfaces
│   ├── Customer                # Represents a customer entity
│   ├── Product                 # Represents a product entity
│   ├── Order                   # Represents an order entity
│   ├── CSVConvertible          # Interface for CSV data conversion
│   └── Identifiable            # Interface for unique identification of entities
│
├── parser                      # Parses CSV data into model objects
│   ├── CustomerParser          # Parses customer data from CSV
│   ├── ProductParser           # Parses product data from CSV
│   └── OrderParser             # Parses order data from CSV
│
├── utils                       # Contains utility classes for common functionalities
│   └── ErrorLogger             # Logs errors encountered during data processing
│
└── validator                   # Ensures data integrity through validation rules
├── CustomerValidator       # Validates customer data
├── OrderValidator          # Validates order data
└── ProductValidator        # Validates product data
```
1. **`controller`**: Manages interactions between services and the application's main entry points.
    - **CustomerController**: Manages operations related to customer data.
    - **ProductController**: Handles product-related operations, including identifying the top-selling products.
    - **OrderController**: Controls order-related operations, including searching for orders by product ID.

2. **`service`**: Contains business logic for each data type.
    - **BaseService**: An abstract class providing common methods for data handling.
    - **CustomerService**, **ProductService**, **OrderService**: Specific implementations to manage customer, product, and order data.

3. **`dal`** (Data Access Layer): Handles data access and persistence.
    - **DataLoader**: Loads data from files into the application.
    - **DataWriter**: Writes processed data back to files.

4. **`model`**: Represents the main entities in the system.
    - **Customer**, **Product**, and **Order**: Implement `CSVConvertible` and `Identifiable` interfaces, supporting CSV file handling and unique identification.

5. **`parser`**: Provides parsing capabilities to convert CSV data lines into model objects.
    - **CustomerParser**, **ProductParser**, **OrderParser**: Parsers for each entity.

6. **`utils`**: Contains utility classes used across the project.
    - **ErrorLogger**: Logs errors during data processing.

7. **`validator`**: Ensures data validity for each entity type.
    - Validators (**CustomerValidator**, **OrderValidator**, **ProductValidator**) enforce data integrity before processing.

## Installation and Execution

### Prerequisites

- Java 17 or higher

### Installation and Running Steps

1. **Clone the repository**:
   ```bash
   git clone https://github.com/your-repository/customer-management-system.git
   
2. **Open terminal and navigate to the project directory**:
3. **Build**:
   ```bash
   mvn clean package
4. **Navigate to target directory**:
   ```bash
   cd ./target/
5. **Run program**:
   ```bash
   java -jar <jar_file_name> <funtion_code> <absolute_project_path>

## Function Codes and Descriptions
| Function Code | Description                                   |
|---------------|-----------------------------------------------|
| 1             | Load data for Products, Customers, and Orders |
| 2.1           | Add new products                              |
| 2.2           | Update products                               |
| 2.3           | Delete products                               |
| 3.1           | Delete customers                              |
| 3.2           | Add new customers                             |
| 3.3           | Update customers                              |
| 4.1           | Add new orders                                |
| 4.2           | Update orders                                 |
| 4.3           | Delete orders                                 |
| 5.1           | Display top 3 best-selling products           |
| 5.2           | Search orders by product ID                   |

## Data File Structure
The application organizes data files into two main folders:
- **Input Folder**: Contains the original data files.
- **Output Folder**: Stores processed results.

File paths are preconfigured in the `BaseService` class, though they can be modified as needed.

## Usage Scenarios
### Load Data (functionCode=1)
- Loads data from input files and logs any errors detected.

### Add Data (functionCode=2.1, 3.2, 4.1)
- Adds new data entries from files.
- Handles duplicate entries for customers and orders according to specific rules.

### Update Data (functionCode=2.2, 3.3, 4.2)
- Updates existing records with new entries from files.

### Delete Data (functionCode=2.3, 3.1, 4.3)
- Deletes specified records based on entries in files.

### Top 3 Best Sellers (functionCode=5.1)
- Analyzes order data to identify the top 3 best-selling products and outputs the results.

### Order Search by Product ID (functionCode=5.2)
- Searches for orders containing specified product IDs and writes matching orders to the output file.
