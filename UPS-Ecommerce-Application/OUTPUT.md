# Application Output – Form & Field E-commerce

This project is a full-stack web application. Its main output is displayed in a browser, not as a single console result.

## 1. Start the backend

Requirements:
- Java 17+
- Maven 3.8+
- MySQL 8+

Open a terminal in `ecommerce/`, start MySQL, and configure your database credentials.

PowerShell:

```powershell
$env:DB_USERNAME = "root"
$env:DB_PASSWORD = "your-mysql-password"
mvn spring-boot:run
```

Expected backend startup result (exact log lines and timing vary):

```text
Spring Boot application starts
Tomcat starts on port 8080
Application is ready to receive requests
API base URL: http://localhost:8080/api
```

## 2. Start the frontend

Open a second terminal in `ecommerce-ui/`:

```powershell
npm install
npm run dev
```

Expected Vite development server output (the port may vary if 5173 is occupied):

```text
VITE ready
Local: http://localhost:5173/
```

Open the local URL in your browser.

## 3. Expected browser output

The storefront is branded **Form & Field** and provides:

- **Shop:** browse products, search by name, and filter by category.
- **Product details:** open an item to view its information and add it to the bag.
- **Shopping bag:** view selected items, change quantities, and see the subtotal.
- **Register / Sign in:** create an account or authenticate.
- **Checkout:** place a demo order while signed in.
- **Orders:** view order history after signing in.

The cart is stored in browser local storage. Backend product, category, account, and order data are served by the Spring Boot API and MySQL.

## 4. Example API responses

With the backend running, open:

```text
GET http://localhost:8080/api/hello
GET http://localhost:8080/api/categories
GET http://localhost:8080/api/products
```

The categories and products endpoints return JSON based on the database contents. If the database is empty, the backend's data seeder may insert its sample catalog.

## 5. Important notes

- The application must be started locally before the browser output can be viewed.
- MySQL credentials must be configured for your machine.
- The exact product cards and API response data depend on the database and seed data.
- This is a demo checkout: it records orders with status `PLACED`; it does not process real payments or arrange delivery.
- These are expected outputs based on the project documentation and code structure. They are not screenshots or logs captured from an actual run.

