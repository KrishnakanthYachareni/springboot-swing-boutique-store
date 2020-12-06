# Boutique-Store-Application
A Java app with Swing?!  Have we travelled in time back to the 90's??  We sure have for this blog post.  Join me for a walk down memory lane with some modern twists.
The company MA Scene Inc. is a small local boutique store specializing in selling modern style
clothing and accessories. The currency is in Canadian dollars. They requested a desktop application
to view and sell the items in the store by the sales associates. All the items have a bar code
associated with it, stock numbers (quantity of the item available), price, title (name of item), colour,
text description, and sizes (if applicable). Before the final purchase, a full order list with prices and a
tax amount of 13% is added to the total. The pay now (doesn’t have to really work but a pop up
window saying “Purchased” is sufficient). Finally, the order history is stored in the system to be
viewed at any time with a timestamp of purchase by sales or administrator.

## How to Run it
This is a Spring Boot standalone application, so your typical `spring-boot:run` maven goal will do the trick. 
Since it's using an in-memory database, there's no additional setup required for that.

````html
<!-- H2 Memory Properties -->
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.username=sa
spring.datasource.password=
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
spring.jpa.show-sql=true
spring.h2.console.settings.web-allow-others=true
## Temporary db creation.
spring.jpa.hibernate.ddl-auto=create-drop
spring.datasource.initialization-mode=always
````
## Preloaded Login Credentials
1. Admin Login:
    ```html
      Username: admin
      Password: admin
    ```
2. Sale/User Login:
    ```html
       Username: user
       Password: user
    ```
## Sample Screens:
1. **Login Screen**
<img src="https://github.com/KrishnakanthYachareni/springboot-swing-boutique-store/blob/master/site/Samples/Login_Screen.png" alt="">

2. **Store Front Screen**
<img src="https://github.com/KrishnakanthYachareni/springboot-swing-boutique-store/blob/master/site/Samples/Store_Front_Screen.png" alt="">

3. **Store Backend(Admin) Screen**
<img src="https://github.com/KrishnakanthYachareni/springboot-swing-boutique-store/blob/master/site/Samples/Store_BackEnd_Screen.png" alt="">

4. **Add Item to Store Screen**
<img src="https://github.com/KrishnakanthYachareni/springboot-swing-boutique-store/blob/master/site/Samples/AddItemToStore_Screen.png" alt="">
