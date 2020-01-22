# MoviesAppMVVM
## In this demo movie app im try to implement MVVM desing pattern (Structure Design pattern) + Repository
### in this project 
* Fetch data from **The Movie DB API**
* Use **Reposiroty** as single source of truth for treating with data
* Use **Dependency Injection** to inject repository 
* Divide movies to three categorites ( Top Rated , Most Popular , Favorite )
* Apply Google Material Desing for Views
* Use database for cache favorite movie for user
### this image is representation for mvvm + data binding
![MVVM](https://user-images.githubusercontent.com/34917869/72855657-d7cac900-3cc0-11ea-9dd6-26ea1bee7f5f.png)

### Libraries that used in this demo project

#### * Retrofit => for fetching data from api
#### * OkHttp => for catch api request traffic
#### * Orhanobut logger => for logging data in logcat
#### * Picasso => for preview images from urls
#### * RxJava 2 => for trating with observable stream
#### * Dagger 2  => for dependency injection
#### * Data binding => for binding data to layout views
#### * Room => for chaching favorite in database
#### * ViewModel ==> for separate data from ui to save it during ui changes
