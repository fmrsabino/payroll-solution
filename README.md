# payroll-solution
This project uses the dependencies already provided in the gradle file (Dagger 2 and Gson).
Tests use the JUnit and Mockito Framework.

To run the tests provided
```
gradle :test
```

To execute the main application
```
gradle run
```

The project provides different modules that can be replaced without breaking functionality of the remaining components (eg.: `ResourceLoader` for loading JsonFiles and `Storage` for storing the data provided)

The `Payroll` class offers the API to access and modify the underlying data to the rest of the components of the application.
