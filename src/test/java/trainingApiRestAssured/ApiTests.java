package trainingApiRestAssured;

import models.Product;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ApiTests {


    @Test
    public void getCategories(){
        String endpoint="http://localhost:8888/api_testing/category/read.php";
        var response = given().when().get(endpoint).then();
        response.log().body();
    }
    @Test
    public void getProduct(){
        String endpoint="http://localhost:8888/api_testing/product/read_one.php";
        var response =
            given().
                queryParam("id",18).
            when().
                get(endpoint).
            then();
        response.log().body();
    }
    @Test void createProduct(){
        String endpoint="http://localhost:8888/api_testing/product/create.php";
        String body= "{ \"name\":\"Head set\", \"description\": \"Black head set.\", \"price\":\"50\", \"category_id\":\"3\"}";
        var response =given().body(body).when().post(endpoint).then();
        response.log().body();
    }
    @Test void updateProduct(){
        String endpoint="http://localhost:8888/api_testing/product/update.php";
        String updateBody = "{\"id\":\"20\" ,\"name\":\"Water Bottle\", \"description\": \"Blue water bottle. Holds 1 liter.\", \"price\":\"12\", \"category_id\":\"3\"}";
        var response= given().body(updateBody).when().put(endpoint).then();
        response.log().body();
    }
    @Test void deleteProduct(){
        String endpoint ="http://localhost:8888/api_testing/product/delete.php";
        String deleteBody ="{\"id\":\"24\"}";
        var response = given().body(deleteBody).when().delete(endpoint).then();
        response.log().body();
    }


    ////using the java class  to model the body of an API request is known as Serializing
    @Test void createSerializedProduct(){
        String endpoint="http://localhost:8888/api_testing/product/create.php";
        Product product= new Product(
                "Mobile",
                "Android mobile. 128 GB.",
                333.00,
                3
        );
        var response = given().body(product).when().post(endpoint).then();
        response.log().body();
    }
    @Test void updateSerializedProduct(){
        String endpoint="http://localhost:8888/api_testing/product/update.php";
        Product product =new Product(
                22,
                "Water Bottle",
                "Copper water bottle. Holds 1 liter",
                30.00,
                3,
                "Active Wear - Unisex"
        );
        var response = given().body(product).when().put(endpoint).then();
        response.log().body();
    }
    @Test void createSweatBand(){
        String endpoint="http://localhost:8888/api_testing/product/create.php";
        Product product= new Product(
                "SweatBand",
                "100% cotton. Yellow colour.",
                5.00,
                3
        );
    var response= given().body(product).when().post(endpoint).then();
    response.log().body();
    }
    @Test void updateSweatBand(){
        String endpoint="http://localhost:8888/api_testing/product/update.php";
        Product product= new Product(
                25,
                "SweatBand",
                "100% cotton. Yellow colour.",
                6.00,
                3,
                "Active Wear - Unisex"
        );
        var response= given().body(product).when().put(endpoint).then();
        response.log().body();
    }
    @Test void getSweatband(){
        String endpoint="http://localhost:8888/api_testing/product/read_one.php";
        var response = given().queryParam( "id", 25).when().get(endpoint).then();
        response.log().body();
    }
    @Test void deleteSweatband(){
        String endpoint="http://localhost:8888/api_testing/product/delete.php";
        var response = given().queryParam( "id", 25).when().delete(endpoint).then();
        response.log().body();
    }
    @Test void createMug(){
        String endpoint="http://localhost:8888/api_testing/product/create.php";
        String body="{\"name\": \"Mug\",\"description\": \"Ceramic. Hand painted.Customized\",\"price\":\"6.00\",\"category_id\":\"3\"}";
        var response= given().body(body).when().post(endpoint).then();
        response.log().body();
    }
    @Test void updateMug(){
        String endpoint="http://localhost:8888/api_testing/product/update.php";
        String body="{\"id\":\"26\",\"price\":\"6.00\",\"description\": \"Ceramic. Hand painted. Customizable\"}";
        var response= given().body(body).when().put(endpoint).then();
        response.log().body();
    }
    @Test void getMug(){
        String endpoint="http://localhost:8888/api_testing/product/read_one.php";
        var response = given().queryParam("id",28).when().get(endpoint).then();
        response.log().body();
        given().queryParam("id",28).when().get(endpoint).then().
                assertThat().
                    statusCode(200).
                    body("id",equalTo("28")).
                    body("name", equalTo("Mug")).
                    body("description", equalTo("Ceramic. Hand painted.Customized")).
                    body("price", equalTo("6.00")).
                    body("category_id", equalTo(3)).
                    body("category_name", equalTo("Active Wear - Unisex"));
    }
    @Test void deleteMug(){
        String endpoint="http://localhost:8888/api_testing/product/delete.php";
        String body="{\"id\":\"27\"}";
        var response = given().body(body).when().delete(endpoint).then();
        response.log().body();
        given().body(body).when().delete(endpoint).then().assertThat().statusCode(200);
    }
    @Test void getProductsBody(){
        String endpoint="http://localhost:8888/api_testing/product/read.php";
        given().when().get(endpoint).then().log().body().assertThat().statusCode(200).
                header("Content-Type","application/json; charset=UTF-8").
                body("records.size()",greaterThan(25)).
                body("records.id", everyItem(notNullValue())).
                body("records.name", everyItem(notNullValue())).
                body("records.description", everyItem(notNullValue())).
                body("records.price", everyItem(notNullValue())).
                body("records.category_id", everyItem(notNullValue())).
                body("records.category_name", everyItem(notNullValue())).
                body("records.id[0]", equalTo(29));
    }
    @Test void getProductsHeader(){
        String endpoint="http://localhost:8888/api_testing/product/read.php";
        given().when().get(endpoint).then().log().headers().assertThat().statusCode(200).
                header("Content-Type","application/json; charset=UTF-8").
                body("records.size()",greaterThan(25)).
                body("records.id", everyItem(notNullValue())).
                body("records.name", everyItem(notNullValue())).
                body("records.description", everyItem(notNullValue())).
                body("records.price", everyItem(notNullValue())).
                body("records.category_id", everyItem(notNullValue())).
                body("records.category_name", everyItem(notNullValue())).
                body("records.id[0]", equalTo(29));
    }


    //using the java class to model the body of an API response is known as Deserializing
    //Why would you want to deserialize an API response? To model a body and represent it as a Java object
    @Test void getDeserializedProduct(){
        String endpoint="http://localhost:8888/api_testing/product/read_one.php";
        Product expectedProduct = new Product(
                29,
                "Mug",
                "Ceramic. Hand painted.Customized",
                6.00,
                3,
                "Active Wear - Unisex"
        );
        Product actualProduct = given().queryParam("id","29").when().get(endpoint).
                as(Product.class);   // we want to get the response as java object so, we use .as()
        assertThat(actualProduct, samePropertyValuesAs(expectedProduct));
    }

    @Test void getMultiVitaminProduct(){
        String endpoint="http://localhost:8888/api_testing/product/read_one.php";
        given().queryParam("id", 18).when().get(endpoint).then().log().body().
                assertThat().
                statusCode(200).
                header("Content-Type",equalTo("application/json")).
                body("id",equalTo("18")).
                body("name", equalTo("Multi-Vitamin (90 capsules)")).
                body("description", equalTo("A daily dose of our Multi-Vitamins fulfills a day’s nutritional needs for over 12 vitamins and minerals.")).
                body("price", equalTo("10.00")).
                body("category_id", equalTo(4)).
                body("category_name", equalTo("Supplements"));
    }
}
