### RetailCo Code Challenge: MyRetail API

This api exclusively focuses/allows for the update and retrieval of product price information via endpoints.

#### Set up


| Tool      | Version |
| ----------- | ----------- |
| Java      |12.0.2       |
| SpringBoot   | 2.4.5        |
| MySQL   | 4.4.4        |
| Apache Tomcat   | 4.0.0        |
| Postman   |         |

In order to run this application:

* Please ensure that you have at the minimum the technologies mentioned in the table installed.

* Next, clone or download then and unzip the file containing the project's source code.

* Update the ```application.properties``` file located at this location: ```retailapi\src\main\resources``` with your MySQl database login information and/or your server port(8098 is the default port).

* Once sure that MySQL is properly connected to the application, you can run the ```SchemaCreationScript``` file to generate a database/schema.

* Run the ```RetailapiApplication``` file located at ``` retailapi\src\main\java```.

#### Endpoints

Using Postman, you'll be able to access the different Endpoints outlined below and test the RetailApi application.


* GET: ```{localhost:server.port}/api/products```  gives you a JSON list of all products present in the database.

* GET: ```{localhost:server.port}/api/products/product?productId=#value```  gives you a JSON objects detailing information about the ```productId``` variable passed in as a parameter.
```
{
    "category": [
        {
            "category": "School"
        },
        {
            "category": "Clothing"
        }
    ],
    "productID": 7,
    "productName": "Shoes",
    "unit": "units",
    "stockQuantity": 30,
    "pricePerUnit": 545
}

```

* GET: ```{localhost:server.port}/api/products/sold``` gives you a JSON list of all the products sold.The result data is sorted by quantity.

* GET: ```{localhost:server.port}/api/products/sold/daterange?startDate={yyyy-MM-dd}&endDate={yyyy-MM-dd}&refDate={yyyy-MM-dd}&resultType=day``` Takes up to 4 parameters (startDate, endDate and refDate). This endpoint returns  a JSON list of all the products sold within a date range. The ```refDate``` combined the ```resultType``` parameter allow the user to specify which type of breakdown they'd like to see (by Month, Week, or Day), and the ```refDate``` parameter helps narrow down the day/week/month from the range.

* POST: ```{localhost:server.port}/api/products/create``` takes a JSON body containing file details (see code block below) and creates a Product object. 

```

{
  "category": [
    {
      "category": "School"
    },
    {
    	"category":"Clothing"
    }
  ],
  "productName": "TestProduct",
  "unit": "Lbs",
  "stockQuantity": 34.0,
  "pricePerUnit": 1.0
}

```


* GET: ```{localhost:server.port}/api/orders```  gives you a JSON list of all orders that have been made in the database.

* GET: ```{localhost:server.port}/api/orders/order?orderId=#VALUE``` takes the ```orderId``` parameter and returns a JSON object including information about the OrderID (ProductID and Quantities).

* GET: ```{localhost:server.port}/api/orders/customer?customerId=#VALUE``` takes the ```customerId``` parameter and returns a sorted by date JSON list of all orders that have been made by the CustomerId.
  
* GET: ```{localhost:server.port}/api/orders/create```  consumes a JSON Body and creates/persist an Order object in the DB. A sample body to include in the request API is attached below.

```

{
  "customerID":2,
  "orderDate": "2021-05-11",
  "productsOrdered": [
    {
    	"3":24
    },
    {
    	"2":25
    }
  ],
  "orderStatus": ""
}

```

### TODO:

    1 - Create a service layer to handle logic (ex: quantity when purchase made, and status change).
    2- Create full CRUD functionality for each DAO.
    3- Complete the README and Document Code.
    4- Improve Api/SQL queries security