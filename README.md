# Dashboard

Simple dashboard application to draw circular progress bar on screen using data from a given endpoint.

I kept the implementation simple and used a minimal SQLite DB containing only the data needed for this.
In reality it would have a lot more, and i would nprobably use a library such as Realm.

## Built With

* [Retrofit](http://square.github.io/retrofit/) - For making async API calls.
* [RxJava](https://github.com/ReactiveX/RxJava) - Reactivex for Java. This is a new one for me, I hadnt used it before so I don't think I have used it to its full potential. It did link in with retrofit nicely though.

## Testing

This proved to be the biggest block and I wasn't able to get this working correctly.

Configuring Kotlin to run tests seems a lot more problematic as it actually sets classes etc as they really should be, e.g final, not public

## Authors

* **Darren Wright** -


## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
# KotlinDashboard
