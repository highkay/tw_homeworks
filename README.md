## Conference Track Management

### Prerequisites for Building
* Java 1.7 or above
* Maven (for building)
* JUnit (for test dependencies, will be automatically downloaded by Maven)

### Build

```bash
mvn install
```

This build task compiles the code, builds a jar file in `target` directory, and executes the tests.

### Run

After `mvn install`:

```bash
java -jar /path/to/homeworks-0.0.1-SNAPSHOT.jar
```
will print the usage.

```bash
java -jar /path/to/homeworks-0.0.1-SNAPSHOT.jar -f=/path/to/input_file
```
will print the result.

### Design

After about an hour googling, I find out this is a [bin-packing problem](https://en.wikipedia.org/wiki/Bin_packing_problem). The later work is quite clear, implements the algorithm and improve it. In the algorithm part, after reading the wikipedia and the latest paper metioned, I decide to use ffd and bfd to solve the problem. Everything goes well in the coding. Then I move to the engineering part, I got some troubles. At firt, I try to decouple the business and algorithm code, Session and Track implement the Bin together, I use a facade pattern in Track to wrap the logic in the Session, but it causes a explicit reference related in the different addItem(). I remove the implement of Track and handle it in the algorithm (I name it Solution in this project). Then I meet the creation of Track. It is quite easy to write the code but hard to place them. The problem is solved by adding a factory pattern. Well, it really take me several hours in trading off a better work and I think it fit the rules.
