# Web Scrapper API With Spring Boot.

This repository is an example on how to make web scrapping, in this particular example I am scrapping the Mercado Libre page, where I get the most recent offers and once I got it, return it using the end point "/offers".

As an API we can see the common structure of an API, controller, service, service implementation, and models. 

Abou the service, you can found 2 methods the first method will be the one that transform the html tags into an object "Offer", the it will return a list of Offer objects.

```java
@Override
    public List<Offer> getAllOfers() {
        List<Offer> offers = new ArrayList<>();
        getHTML("https://www.mercadolibre.com.mx/ofertas#nav-header").forEach(
                offer -> offers.add(new Offer(
                        offer.select("p.promotion-item__title").toString().split(">")[1].split("<")[0],
                        offer.select("span.andes-money-amount__fraction").toString().split(">")[1].split("<")[0],
                        offer.select("a.promotion-item__link-container").toString().split("href=")[1].split("\"")[1],
                        offer.select("a.promotion-item__link-container").toString().split("href=")[1].split("\"")[1]
                ))
        );
        return offers;
    }
```

The second method is the responsible for making a get request to the Mercado Libre page and get all their tags as Elements, take in mind that Elements is an object type of the Jsoup library, it extends of ArrayList and we can manipulate it as an ArrayList.

```java
public Elements getHTML(String url) {
        Elements offers = null;
        try {
            offers = Jsoup.connect(url).userAgent("Mozilla/5.0").timeout(100000).get().select("li.promotion-item");
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return offers;
    }
```

Finally we can see the controller that just calls the method getAllOffers and it return a response with a list of offers, make sure to inject the dependencies from the service using the tag @Autowired.

```java
@GetMapping("/offers")
    public List<Offer> getAllOffers() {
        return offerService.getAllOfers();
    }
```

## Libraries

To make it work the library [jsoup: Java HTML Parser](https://mvnrepository.com/artifact/org.jsoup/jsoup/1.17.2) is needed, another dependencies that I have used as a normal API are Spring Web, Lombok and Spring Dev Tools.

```xml
<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-devtools</artifactId>
			<scope>runtime</scope>
			<optional>true</optional>
		</dependency>
		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<optional>true</optional>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>org.jsoup</groupId>
			<artifactId>jsoup</artifactId>
			<version>1.17.2</version>
		</dependency>
	</dependencies>
```

## Usage
Make a get request:
```json
get : http://localhost:8080/api/v1/offers

You should get a JSON file as below.
[
{"title" : "Articulo 2", "price" : "1,000", "url" "url_link": , "image" : "image_link"},
{"title" : "Articulo 1", "price" : "2,300", "url" "url_link": , "image" : "image_link"},
...
]
```

Made by Luis.
