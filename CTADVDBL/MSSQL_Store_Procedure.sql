-- Use BikeStores DB
USE BikeStores
GO

/* 1. Create a stored procedure to register new customers 
and Execute by Creating new random data. */

CREATE PROC spGenerateCustomer (
    @FirstName AS VARCHAR(255),
    @LastName AS VARCHAR(255),
    @Phone AS VARCHAR(25),
    @Email AS VARCHAR(255),
    @Street AS VARCHAR(255),
    @City AS VARCHAR(50),
    @State AS VARCHAR(25),
    @ZipCode AS VARCHAR(5)
) AS 
BEGIN
    -- Insert the new customer into the customers table
    INSERT INTO sales.customers (first_name, last_name, phone, email, street, city, state, zip_code)
    VALUES (@FirstName, @LastName, @Phone, @Email, @Street, @City, @State, @ZipCode)
END
GO

-- Execute to generate the customer
EXEC spGenerateCustomer 
    @FirstName="Felix", 
    @LastName="Macaspac", 
    @Phone="+6391234567", 
    @Email="felixpogi@gmail.com", 
    @Street="Random Stree", 
    @City="Imus",
    @State="Cavite",
    @ZipCode="4103";
GO

-- Output the generated customer
SELECT * from sales.customers
WHERE email = 'felixpogi@gmail.com';
GO

/* 2. Create a stored procedure to register new products 
and Execute by Creating new random data. */

CREATE PROC spGenerateProduct (
    @ProductName AS VARCHAR(255),
    @BrandID AS INT,
    @CategoryID AS INT,
    @ModelYear AS SMALLINT,
    @ListPrice AS DECIMAL(10, 2)
) AS 
BEGIN
    -- Insert the new product into the products table
    INSERT INTO production.products (product_name, brand_id, category_id, model_year, list_price)
    VALUES (@ProductName, @BrandID, @CategoryID, @ModelYear, @ListPrice)
END
GO

-- Execute to generate a product
EXEC spGenerateProduct
    @ProductName="PCX 160",
    @BrandID=1,
    @CategoryID=6,
    @ModelYear=2024,
    @ListPrice=899.92;
GO

-- Output the generated product
SELECT * from production.products
WHERE product_name = 'PCX 160';
GO

/* 3. Create a stored procedure to update any data to an existing store. */

CREATE PROC spUpdateStore (
    @StoreID AS INT,
    @StoreName AS VARCHAR(255),
    @Phone AS VARCHAR(25),
    @Email AS VARCHAR(255),
    @Street AS VARCHAR(255),
    @City AS VARCHAR(255),
    @State AS VARCHAR(10),
    @ZipCode AS VARCHAR(5)
) AS 
BEGIN
    -- Update the existing store in the stores table
    UPDATE sales.stores
    SET 
        store_name = @StoreName,
        phone = @Phone,
        email = @Email,
        street = @Street,
        city = @City,
        state = @State,
        zip_code = @ZipCode
    WHERE store_id = @StoreID
END
GO

-- Execute to update a store
EXEC spUpdateStore
    @StoreID = 3,
    @StoreName = 'Kainan',
    @Phone = '1234567890',
    @Email = 'kainan@example.com',
    @Street = 'Bagong Street',
    @City = 'Imus',
    @State = 'NS',
    @ZipCode = '4103';
GO

-- Output the updated store
SELECT * from sales.stores
WHERE store_id = 3
GO

/* 4. Create a stored procedure to update any data to an existing staff. */

CREATE PROC spUpdateStaff (
    @StaffID AS INT,
    @FirstName AS VARCHAR(50),
    @LastName AS VARCHAR(50),
    @Email AS VARCHAR(255),
    @Phone AS VARCHAR(25),
    @Active AS TINYINT,
    @StoreID AS INT,
    @ManagerID AS INT
) AS 
BEGIN
    -- Update the existing staff in the staffs table
    UPDATE sales.staffs
    SET 
        first_name = @FirstName,
        last_name = @LastName,
        email = @Email,
        phone = @Phone,
        active = @Active,
        store_id = @StoreID,
        manager_Id = @ManagerID
    WHERE staff_id = @StaffID
END
GO

-- Execute to update a staff data
EXEC spUpdateStaff
    @StaffID = 3,
    @FirstName = 'Felix',
    @LastName = 'Macaspac',
    @Email = 'felixpogi@gmail.com',
    @Phone = '+6392913921',
    @Active = 0,
    @StoreID = 2,
    @ManagerID = 5;
GO

-- Output the updated staff data
SELECT * from sales.staffs
WHERE staff_id = 3
GO


/* 5. Create a stored procedure to remove a record of a customer. */

CREATE PROC spRemoveCustomer (@CustomerID AS INT) AS 
BEGIN 
    DELETE from sales.customers
    WHERE customer_id = @CustomerID;
END
GO

-- Execute to remove a customerID
EXEC spRemoveCustomer @CustomerID = 1
GO

-- Output the updated customers list
SELECT * from sales.customers
GO

/* 6. Create a stored procedure to remove a record of a product. */

CREATE PROC spRemoveProduct (@ProductID AS INT) AS 
BEGIN 
    DELETE from production.products
    WHERE product_id = @ProductID;
END
GO

-- Execute to remove a product
EXEC spRemoveProduct @ProductID = 1
GO

-- Output the updated products list
SELECT * from production.products
GO

/* 7. Create a stored procedure where the user can view the product name, model year, 
price including categories name and brand name. */

CREATE PROC spViewProduct (@ProductID AS INT) AS
BEGIN
    SELECT 
        p.product_name, 
        p.model_year, 
        p.list_price, 
        c.category_name,
        b.brand_name
    FROM 
        production.products p
    INNER JOIN 
        production.categories c ON p.category_id = c.category_id
    INNER JOIN 
        production.brands b ON p.brand_id = b.brand_id
    WHERE 
        p.product_id = @ProductID
END
GO 

-- Execute to view the product name, model year, price including categories name and brand name.
EXEC spViewProduct @ProductID = 2;
GO

/* 8. Create a stored procedure where the user can view the customer first and last name, 
and purchased product and when was it shipped. */

CREATE PROC spViewCustomerPurchases (@CustomerID INT) AS
BEGIN
    SELECT 
        c.first_name, 
        c.last_name, 
        p.product_name, 
        o.shipped_date
    FROM 
        sales.customers c
    INNER JOIN 
        sales.orders o ON c.customer_id = o.customer_id
    INNER JOIN 
        sales.order_items oi ON o.order_id = oi.order_id
    INNER JOIN 
        production.products p ON oi.product_id = p.product_id
    WHERE 
        c.customer_id = @CustomerID
END

-- Execute to view the customer first and last name, and purchased product and when was it shipped.
EXEC spViewCustomerPurchases @CustomerID = 2
GO

/* 9. Create a stored procedure where the user can view the store name 
and address where a customers purchased by searching customer last name. */

CREATE PROC spViewStoreByCustomerLastName (@LastName VARCHAR(50)) AS
BEGIN
    SELECT 
        s.store_name, 
        s.street, 
        s.city, 
        s.state, 
        s.zip_code
    FROM 
        sales.stores s
    INNER JOIN 
        sales.orders o ON s.store_id = o.store_id
    INNER JOIN 
        sales.customers c ON o.customer_id = c.customer_id
    WHERE 
        c.last_name = @LastName
END
GO

-- Execute to view the store name and address where a customers purchased by searching customer last name.
EXEC spViewStoreByCustomerLastName @LastName="Bryan";
GO

/* 10. Create a stored procedure where the user can view the whose staff assist the customer in making a purchase 
by showing the full of the staff and customer with purchased item. */

CREATE PROC spViewStaffCustomerPurchases AS
BEGIN
    SELECT 
        s.first_name + ' ' + s.last_name AS staff_name, 
        c.first_name + ' ' + c.last_name AS customer_name, 
        p.product_name
    FROM 
        sales.staffs s
    INNER JOIN 
        sales.orders o ON s.store_id = o.store_id
    INNER JOIN 
        sales.customers c ON o.customer_id = c.customer_id
    INNER JOIN 
        sales.order_items oi ON o.order_id = oi.order_id
    INNER JOIN 
        production.products p ON oi.product_id = p.product_id
END
GO

-- Execute to view whose staff assist the customer and also show the product name
EXEC spViewStaffCustomerPurchases
GO